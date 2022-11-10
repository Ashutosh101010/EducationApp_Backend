package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Entity.Student_Posts_Liked_Model;
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
import java.util.List;

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

        String que="select StudentPostModel from StudentPostModel StudentPostModel where StudentPostModel.inst_id=:instId and StudentPostModel.added_by=:studId and StudentPostModel.post_status=:status order by StudentPostModel.added_on desc";
        Query studentPost = postRepository.getEntityManager().createQuery(que);
        studentPost.setParameter("instId",request.getInst_id());
        studentPost.setParameter("studId",request.getStud_id().intValue());
        studentPost.setParameter("status",1);


     List<StudentPostModel> tempPostList =  studentPost.getResultList();

        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        tempPostList.forEach(objects -> {
            StudentPostEntity postModel = new StudentPostEntity();
            postModel.setId(objects.getId());
            postModel.setDescription(objects.getDescription());
            if(objects.getPic()==null)
            {
                postModel.setPic("");
            }else{
                postModel.setPic(objects.getPic());
            }

            postModel.setPostStatus(objects.getPost_status());
            postModel.setAdded_by(objects.getAdded_by());
           postModel.setAdded_on(objects.getAdded_on());

            postModel.setTimeStamp(objects.getAdded_on().getTime());


            StudentModel studentModel = studentRepository.find("id"
                    ,postModel.getAdded_by().longValue()).firstResult();

//            objects.setStudentModel(studentModel);
            postModel.setImage(studentModel.getProfile());
            postModel.setName(studentModel.getFname());

            Integer commentCount = getCommentCount(postModel);
            postModel.setComment(commentCount.longValue());



          List<Student_Posts_Liked_Model> likeList =getLiked(((int) objects.getId()));
            likeList.forEach(likeObject -> {
                if(request.getStud_id().longValue() == studentModel.getId().longValue()){
                    postModel.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            postModel.setLike(likeCount.longValue());

            postList.add(postModel);







        });

//        getPostCommented(request,postList);


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


    List<Student_Posts_Liked_Model> getLiked(Integer postId){
        String likeQuery = "SELECT Student_Posts_Liked_Model from Student_Posts_Liked_Model Student_Posts_Liked_Model WHERE Student_Posts_Liked_Model.post_id =:postId";
        Query like = likedRepository.getEntityManager().createQuery(likeQuery);
        like.setParameter("postId",postId);
        return  like.getResultList();
    }






//    ArrayList getPostCommented(GetStudentPostRequest request,ArrayList<StudentPostEntity> postModel){
//        postModel.forEach(studentPostEntity -> {
//
//            List<Student_Posts_Commented> commenttList=  commentRepository.list("post_id",studentPostEntity.getId().intValue());
//
//
//        });
//
//
//         arrayList = (ArrayList<Student_Posts_Commented>)
//                commentRepository.list("added_by",request.getStud_id());
//
//
//        arrayList.forEach(student_posts_commented -> {
//
//            if( ! postModel.contains(student_posts_commented.getPost_id())){
//                System.out.println("----------------"+student_posts_commented.getPost_id());
////                System.out.println("-------"+postModel.getId());
//
//                long id = Long.valueOf(student_posts_commented.getPost_id());
//
//                StudentPostModel ps = postRepository.find("id",
//                        id).firstResult();
//
//                System.out.println(ps.getAdded_by());
//
//                 = new Gson().fromJson(new Gson().toJson(ps),
//                        StudentPostEntity.class);
//
//                StudentModel sm = studentRepository.find("id",en.getAdded_by().longValue())
//                        .firstResult();
//
//                en.setName(sm.getFname());
//                en.setTimeStamp(ps.getAdded_on().getTime());
//                en.setImage(sm.getProfile());
//
//                Integer count = getCommentCount(en);
//                en.setComment(count.longValue());
//
//                List<Student_Posts_Liked_Model> like = getLiked(student_posts_commented.getPost_id());
//                like.forEach(likeObject -> {
//                    if(request.getStud_id().longValue() == sm.getId().longValue()){
//                        System.out.println("Liked");
//                        en.setLiked(true);
//                    }
//                });
//
//                Integer likec =  like.size();
//                en.setLike(likec.longValue());
//
//                arrayList.add(en);
//            }
//
//        });
//
//
//
//        System.out.println(arrayList.size());
//
//
//        return  arrayList;
//    }

}
