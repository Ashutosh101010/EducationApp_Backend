package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.GetStudentPostRequest;
import com.aurd.Student.Model.Response.GetStudentPostResponse;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.StudentPostLikedRepository;
import com.aurd.Student.Repository.StudentPostRepository;
import com.aurd.Student.Repository.StudentRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Path("/getStudentActivity")
public class GetStudentPostsController {

    @Inject
    StudentPostRepository postRepository;
    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @Inject
    StudentRepository studentRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public GetStudentPostResponse getPosts(GetStudentPostRequest request){

        GetStudentPostResponse response = new GetStudentPostResponse();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();

        String lastId;
        if ( request.getLastId()==null || request.getLastId().equals("")) {
            lastId = sdf.format(calendar.getTime());
        } else {
            lastId = request.getLastId();
        }


        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` " +
                "INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ? and student_posts.added_by = ? and " +
                "student_posts.post_status =? " +
                " ORDER BY added_on DESC";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,request.getInst_id());
        studentPost.setParameter(2,request.getStud_id().longValue());
        studentPost.setParameter(3,1);
//        studentPost.setParameter(3,lastId);

        ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.setMaxResults(5).getResultList();
        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        tempPostList.forEach(objects -> {
            StudentPostEntity postModel = new StudentPostEntity();
            postModel.setId(Long.parseLong(objects[0].toString()));
            postModel.setDescription(objects[1].toString());
            if(objects[2]==null){
                postModel.setPic("");
            }else{
                postModel.setPic(objects[2].toString());
            }
            postModel.setPostStatus(Integer.parseInt(objects[3].toString()));
            postModel.setAdded_by(Integer.parseInt(objects[4].toString()));
           postModel.setAdded_on(Timestamp.valueOf(objects[5].toString()));
            postModel.setName(objects[6].toString());
            postModel.setTimeStamp(Timestamp.valueOf(objects[5].toString()).getTime());


            StudentModel studentModel = studentRepository.find("id"
                    ,postModel.getAdded_by().longValue()).firstResult();

            postModel.setImage(studentModel.getProfile());


            Integer commentCount = getCommentCount(postModel);
            postModel.setComment(commentCount.longValue());



            ArrayList<Object[]> likeList =getLiked(postModel);
            likeList.forEach(likeObject -> {
                if(request.getStud_id() == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    postModel.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            postModel.setLike(likeCount.longValue());

            postList.add(postModel);







        });

        getPostCommented(request,postList);


        if(postList.isEmpty()){
            response.setMessage("No Post Found");
            response.setStatus(false);
            response.setErrorCode(1);

        }else{
            response.setPosts(postList);
            response.setMessage("Post found success");
            response.setErrorCode(0);
            response.setStatus(true);
        }


        return  response;
    }



    Integer getCommentCount(StudentPostEntity postEntity){
        String commentQuery = "SELECT COUNT(*) FROM `student_posts_commented` WHERE post_id =? ";
        Query comment = commentRepository.getEntityManager().createNativeQuery(commentQuery);
        comment.setParameter(1,postEntity.getId());
        Integer commentCount = ((Number) comment.getSingleResult()).intValue();
        return  commentCount;
    }


    ArrayList getLiked(StudentPostEntity postModel){
        String likeQuery = "SELECT * FROM `student_posts_liked` WHERE post_id =?";
        Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
        like.setParameter(1,postModel.getId());
        ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
        return likeList;
    }






    ArrayList getPostCommented(GetStudentPostRequest request,ArrayList<StudentPostEntity> postModel){
        ArrayList<Student_Posts_Commented> arrayList = (ArrayList<Student_Posts_Commented>)
                commentRepository.list("added_by",request.getStud_id());


        arrayList.forEach(student_posts_commented -> {

            if( ! postModel.contains(student_posts_commented.getPost_id())){
                System.out.println("----------------"+student_posts_commented.getPost_id());
//                System.out.println("-------"+postModel.getId());

                long id = Long.valueOf(student_posts_commented.getPost_id());

                StudentPostModel ps = postRepository.find("id",
                        id).firstResult();

                System.out.println(ps.getAdded_by());

                StudentPostEntity en = new Gson().fromJson(new Gson().toJson(ps),
                        StudentPostEntity.class);

                StudentModel sm = studentRepository.find("id",en.getAdded_by().longValue())
                        .firstResult();

                en.setName(sm.getFname());
                en.setTimeStamp(ps.getAdded_on().getTime());
                en.setImage(sm.getProfile());

                Integer count = getCommentCount(en);
                en.setComment(count.longValue());

                ArrayList<Object[]> like = getLiked(en);
                like.forEach(likeObject -> {
                    if(request.getStud_id() == Long.parseLong(likeObject[1].toString())){
                        System.out.println("Liked");
                        en.setLiked(true);
                    }
                });

                Integer likec =  like.size();
                en.setLike(likec.longValue());

                postModel.add(en);
            }

        });



        System.out.println(arrayList.size());


        return  arrayList;
    }

}
