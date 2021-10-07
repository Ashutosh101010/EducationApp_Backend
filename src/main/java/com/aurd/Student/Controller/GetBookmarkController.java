package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetBookMarkRequest;
import com.aurd.Student.Model.Response.GetBookMarkResponse;
import com.aurd.Student.Repository.*;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/students/getBookMark")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetBookmarkController {


    @Inject
    BookMarkRepository bookMarkRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository currentAffairRepository;

    @Inject
    StudentPostRepository postRepository;

    @Inject
    Current_Affair_Comment_Repository caCommentRepository;

    @Inject
    CurrentAffairLikeDislikeRepository currentAffairLikeDislikeRepository;

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @Inject
    StudentRepository studentRepository;




    @POST


    @Transactional

    public GetBookMarkResponse getBookmarkedData(GetBookMarkRequest request){

        ArrayList<BookMarkModel> arrayList  = (ArrayList<BookMarkModel>)
                bookMarkRepository.find("added_by",request.getStudent_id()).list();
        ArrayList blogList = new ArrayList();
        ArrayList caList = new ArrayList();
        ArrayList postList = new ArrayList();

        arrayList.forEach(bookMarkModel -> {
            if(bookMarkModel.getType().equals("post")){
               StudentPostEntity postModel =
                       getStudentPost(bookMarkModel.getPost_id(),bookMarkModel.getAdded_by());

             StudentModel model = studentRepository.find("id",postModel.getAdded_by().longValue()).firstResult();

               postModel.setAdded(true);
               postModel.setName(model.getFname());
                postList.add(postModel);
            }else if(bookMarkModel.getType().equals("currentAffair")){
               CurrentAffairEntity currentAffairModel =
                       getCurrentAffair(bookMarkModel.getPost_id(),bookMarkModel.getAdded_by());
               currentAffairModel.setAdded(true);
               caList.add(currentAffairModel);
            }
        });

        GetBookMarkResponse response = new GetBookMarkResponse();
        response.setCaList(caList);
        response.setpList(postList);
        response.setStatusCode(0);
        response.setStatus(true);
        return  response;
    }




    BlogModel getBlogData(long id){
        BlogModel blogModel = blogRepository.find("id",id).firstResult();
        return  blogModel;
    }


    StudentPostEntity getStudentPost(long id,long studId){

     StudentPostModel postModel  = postRepository.find("id",id).firstResult();
     StudentPostEntity postEntity = new Gson().fromJson(new Gson().toJson(postModel),StudentPostEntity.class);

        String commentQuery = "SELECT COUNT(*) FROM `student_posts_commented` WHERE post_id =?";
        Query comment = commentRepository.getEntityManager().createNativeQuery(commentQuery);
        comment.setParameter(1,postModel.getId());
        Integer commentCount = ((Number) comment.getSingleResult()).intValue();
        postEntity.setComment(commentCount.longValue());



        String likeQuery = "SELECT * FROM `student_posts_liked` WHERE post_id =?";
        Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
        like.setParameter(1,postModel.getId());
        ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
        likeList.forEach(likeObject -> {
            if(studId == Long.parseLong(likeObject[1].toString())){
                System.out.println("Liked");
                postEntity.setLiked(true);
            }
        });

        Integer likeCount =  likeList.size();
        postEntity.setLike(likeCount.longValue());

        return postEntity;
    }



    CurrentAffairEntity getCurrentAffair(long id,long studId){
        CurrentAffairModel currentAffairModel =
                currentAffairRepository.find("id",id).firstResult();

        CurrentAffairEntity entity = new Gson().fromJson(new Gson().
                toJson(currentAffairModel),CurrentAffairEntity.class);


        TeacherModel teacherModel= teacherRepository.findTeacher(entity.getAdded_by().longValue());
        entity.setName(teacherModel.getFname());

        String commentQuery = "SELECT COUNT(*) FROM `current_affairs_comments` WHERE current_affair_id =? ";
        Query comment = caCommentRepository.getEntityManager().createNativeQuery(commentQuery);
        comment.setParameter(1,currentAffairModel.getId());
        Integer commentCount = ((Number) comment.getSingleResult()).intValue();
        entity.setComment(commentCount.longValue());


        String likeQuery = "SELECT * FROM `current_affairs_liked` WHERE current_affair_id =?";
        Query like = currentAffairLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
        like.setParameter(1,currentAffairModel.getId());
        ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
        likeList.forEach(likeObject -> {
            if(studId == Long.parseLong(likeObject[1].toString())){
                System.out.println("Liked");
                entity.setLiked(true);
            }
        });

        Integer likeCount =  likeList.size();
        entity.setLike(likeCount.longValue());

        return  entity;
    }


}
