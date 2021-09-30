package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.GetStudentPostRequest;
import com.aurd.Student.Model.Response.GetStudentPostResponse;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.StudentPostLikedRepository;
import com.aurd.Student.Repository.StudentPostRepository;
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
import java.util.ArrayList;

@Path("/getStudentActivity")
public class GetStudentPostsController {

    @Inject
    StudentPostRepository postRepository;
    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public GetStudentPostResponse getPosts(GetStudentPostRequest request){

//        ArrayList<StudentPostModel> arrayList= repository.getStudentPosts(request);
        GetStudentPostResponse response = new GetStudentPostResponse();
//        if(arrayList.isEmpty()){
//            response.setErrorCode(1);
//            response.setStatus(false);
//            response.setMessage("No Post Found");
//        }else {
//            response.setPosts(arrayList);
//            response.setErrorCode(0);
//            response.setStatus(true);
//            response.setMessage("Get Post Success");
//        }


        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` " +
                "INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ? and student_posts.added_by = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,request.getInst_id());
        studentPost.setParameter(2,request.getStud_id());

        ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        tempPostList.forEach(objects -> {
            StudentPostEntity postModel = new StudentPostEntity();
            postModel.setId(Long.parseLong(objects[0].toString()));
            postModel.setDescription(objects[1].toString());
          //  postModel.setPic(objects[2].toString());
            postModel.setPostStatus(Integer.parseInt(objects[3].toString()));
            postModel.setAdded_by(Integer.parseInt(objects[4].toString()));
           postModel.setAdded_on(Timestamp.valueOf(objects[5].toString()));
            postModel.setName(objects[6].toString());

            String commentQuery = "SELECT COUNT(*) FROM `student_posts_commented` WHERE post_id =? ";
            Query comment = commentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,postModel.getId());
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            postModel.setComment(commentCount.longValue());


            String likeQuery = "SELECT * FROM `student_posts_liked` WHERE post_id =?";
            Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,postModel.getId());
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if(request.getStud_id() == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    postModel.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            postModel.setLike(likeCount.longValue());

            ArrayList<Student_Posts_Commented> arrayList = (ArrayList<Student_Posts_Commented>)
                    commentRepository.list("added_by",request.getStud_id());

            System.out.println(arrayList.size());


           arrayList.forEach(student_posts_commented -> {
             if( student_posts_commented.getPost_id()!=postModel.getId()){
               System.out.println("----------------"+student_posts_commented.getPost_id());

               String string = "SELECT student_posts.id,student_posts.description,student_posts.pic,student_posts.post_status,student_posts.added_by," +
                       " student_posts.added_on FROM `student_posts` INNER JOIN students ON students.id=student_posts.added_by INNER JOIN student_posts_commented ON " +
                       "student_posts_commented.added_by=students.id WHERE student_posts_commented.post_id=?";
               Query query = postRepository.getEntityManager().createNativeQuery(string);
               query.setParameter(1,student_posts_commented.getPost_id());
               ArrayList<Object[]> pList=(ArrayList<Object[]>) query.getResultList();
                 ArrayList<StudentPostModel> arrayList1 = new ArrayList<>();
                 pList.forEach(objects1 -> {
                             StudentPostModel Model = new StudentPostModel();
                             Model.setId(Long.parseLong(objects[0].toString()));
                             Model.setDescription(objects[1].toString());

                             if(Model.getPic()==null|| Model.getPic().equals("")){
                             Model.setPic("");
                             }else {
                             System.out.println(Model.getPic());

                            Model.setPic(Model.getPic());
                     }
                           //  Model.setPic(objects[2].toString());
                             Model.setPost_status(Integer.parseInt(objects[3].toString()));
                             Model.setAdded_by(Integer.parseInt(objects[4].toString()));
                             Model.setAdded_on(Timestamp.valueOf(objects[5].toString()));




                             // StudentPostModel model =   postRepository.find("id", student_posts_commented.getPost_id()).firstResult();
                             // StudentPostEntity entity = new StudentPostEntity();
                             //  entity.setId(Long.parseLong(objects[0].toString()));
                             //   model.setId(Long.parseLong(objects[0].toString()));
                             //   model.setDescription(objects[1].toString());
                             //    model.setPic(objects[2].toString());
                             //   model.setPost_status(Integer.parseInt(objects[3].toString()));
                             //  model.setAdded_by(Integer.parseInt(objects[4].toString()));
                             //   model.setAdded_on(Timestamp.valueOf(objects[5].toString()));

                     arrayList1.add(Model);
                         });


               }
           });


            postList.add(postModel);
        });


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


}
