package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetExploreDataResponse;
import com.aurd.Student.Model.Response.LatestUpdateResponse;
import com.aurd.Student.Repository.*;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/getExploreData")
public class GetExploreDataController {

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository currentAffairRepository;

    @Inject
    StudentPostRepository postRepository;

    @Inject
    NotesRepository notesRepository;

    @Inject
    QuizRepository quizRepository;

    @Inject
    StudentPostCommentRepository commentRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @Inject
    Blog_Comment_Repository blogCommentRepository;

    @Inject
    Current_Affair_Comment_Repository caCommentRepository;

    @Inject
    CurrentAffairLikeDislikeRepository currentAffairLikeDislikeRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)



    @Transactional

    public GetExploreDataResponse getData(@QueryParam("instID") Long id,
                                          @QueryParam("studId") long studId){



        ArrayList<BlogEntity> blogList = getBlog(id.intValue(),studId);


        ArrayList<CurrentAffairEntity> currentAffairList = getCurrentAffair(id.intValue(),studId);
        ArrayList<StudentPostEntity> postList = getPost(studId,id.intValue());


        ArrayList<NotesModel> notesList = notesRepository.getNotesList(id.intValue());


        GetQuizRequest request = new GetQuizRequest();
        request.setInst_id(id);
        request.setType("Quiz");
//        ArrayList<QuizModel> quizList = quizRepository.getQuizzes(request);


        GetExploreDataResponse response = new GetExploreDataResponse();
        response.setBlogList(blogList);
        response.setNotesList(notesList);
        response.setPostList(postList);
        response.setCurrentAffairArrayList(currentAffairList);
//        response.setQuizList(quizList);
        response.setErrorCode(0);
        response.setStatus(true);
        response.setMessage("Fetch Explore Data Success");


       return response;
    }




    ArrayList getBlog(int id,long studId){

        ArrayList<BlogEntity> arrayList = new ArrayList<>();
        String blogQuery = "SELECT * from `blog` where inst_id = ? ;";
        Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
        blog.setParameter(1,id);
        ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();
        blogList.forEach(blogModel -> {

            BlogEntity blogEntity = new Gson().fromJson(new Gson().toJson(blogModel),BlogEntity.class);

            String commentQuery = "SELECT COUNT(*) FROM `blog_commented` WHERE blog_id =? ";
            Query comment = blogCommentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,blogModel.getId());
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();

            blogEntity.setComment(commentCount.longValue());



            String likeQuery = "SELECT * FROM `blog_liked` WHERE blog_id =?";
            Query like = currentAffairLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,blogModel.getId());
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if(studId == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    blogEntity.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            blogEntity.setLike(likeCount.longValue());



            arrayList.add(blogEntity);

        });

        return  arrayList;
    }

    ArrayList getCurrentAffair(int id,long studId){
        String caQuery = "SELECT * from `current_affairs` where  inst_id = ? ;";
        Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                CurrentAffairModel.class);
        currentAffair.setParameter(1,id);
        ArrayList<CurrentAffairEntity> currentAffairList =  new ArrayList<>();

        ArrayList<CurrentAffairModel> caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();
        caList.forEach(currentAffairModel -> {
            CurrentAffairEntity entity = new Gson().fromJson(new Gson().toJson(currentAffairModel),CurrentAffairEntity.class);

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


            currentAffairList.add(entity);


        });

        return  currentAffairList;
    }



    ArrayList getPost(long studId,int id){

        String studentPostQuery = "SELECT student_post_demo.id,student_post_demo.description," +
                "student_post_demo.pic,student_post_demo.post_status,student_post_demo.added_by,\n" +
                "student_post_demo.added_on, students.fname FROM `student_post_demo` " +
                "INNER JOIN students ON students.id=student_post_demo.added_by " +
                "WHERE student_post_demo.inst_id = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,id);

        ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        tempPostList.forEach(objects -> {
            StudentPostEntity postModel = new StudentPostEntity();
            postModel.setId(Long.parseLong(objects[0].toString()));
            postModel.setDescription(objects[1].toString());
            postModel.setPic(objects[2].toString());
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
                if(studId == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    postModel.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            postModel.setLike(likeCount.longValue());

            postList.add(postModel);
        });

        return  postList;
    }

}
