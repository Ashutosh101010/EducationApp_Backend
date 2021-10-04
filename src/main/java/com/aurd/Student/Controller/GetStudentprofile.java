package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.GetStudentDetailRequest;
import com.aurd.Student.Model.Response.StudentProfileResponse;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.StudentPostLikedRepository;
import com.aurd.Student.Repository.StudentPostRepository;
import com.aurd.Student.Repository.StudentRepository;

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

@Path("/getStudentProfile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetStudentprofile {

    @Inject
    StudentRepository studentRepository;

    @Inject
    StudentPostRepository postRepository;

    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @POST
    @Transactional

    public StudentProfileResponse getProfile(GetStudentDetailRequest request){

      StudentModel studentModel = studentRepository.find("id",request.getId()).firstResult();


        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` " +
                "INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ? and student_posts.added_by = ? ORDER  BY added_on DESC ";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,studentModel.getInst_id());
        studentPost.setParameter(2,request.getId());

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


                    Integer commentCount = getCommentCount(postModel);
                    postModel.setComment(commentCount.longValue());


                    ArrayList<Object[]> likeList = getLiked(postModel);
                    likeList.forEach(likeObject -> {
                        if (request.getId() == Long.parseLong(likeObject[1].toString())) {
                            System.out.println("Liked");
                            postModel.setLiked(true);
                        }
                    });

                    Integer likeCount = likeList.size();
                    postModel.setLike(likeCount.longValue());

                    postList.add(postModel);
                });



            StudentProfileResponse response = new StudentProfileResponse();
        response.setStudent(studentModel);
        response.setList(postList);
        response.setErrorCode(0);
        response.setStatus(true);

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

}
