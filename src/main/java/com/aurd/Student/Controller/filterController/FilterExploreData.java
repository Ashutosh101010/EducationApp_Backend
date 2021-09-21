package com.aurd.Student.Controller.filterController;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.Response.GetExploreDataResponse;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Path("/filterExploreFeed")
public class FilterExploreData {

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


    @Inject
    BlogLikedRepository blogLikedRepository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    NotesComentRepository notesComentRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)



    @Transactional

    public GetExploreDataResponse getData(@QueryParam("instId") Long instId,
                                          @QueryParam("studId") long studId,
                                          @QueryParam("date") String date){

        String start = date+" 00:00:00";
        String end = date+" 23:59:59";


        ArrayList<BlogEntity> blogList = getBlog(instId.intValue(),studId,start,end);


        ArrayList<CurrentAffairEntity> currentAffairList = getCurrentAffair(instId.intValue(),studId,start,end);
        ArrayList<StudentPostEntity> postList = getPost(studId,instId.intValue(),start,end);

        ArrayList<NotesEntity> notesList = getNotes(studId,instId,start,end);





        GetExploreDataResponse response = new GetExploreDataResponse();
        response.setBlogList(blogList);
        response.setCurrentAffairArrayList(currentAffairList);
        response.setPostList(postList);
        response.setNotesList(notesList);
        response.setMessage("Get Data Success");
        response.setErrorCode(0);
        response.setStatus(true);
        return  response;
    }




    ArrayList getBlog(int id,long studId,String start,String end){


        ArrayList<BlogEntity> arrayList = new ArrayList<>();
        String blogQuery = "SELECT * from `blog` where created_on BETWEEN ? AND ? AND inst_id = ? ;";
        Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
        blog.setParameter(1,start);
        blog.setParameter(2,end);
        blog.setParameter(3,id);
        ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();
        blogList.forEach(blogModel -> {

            BlogEntity blogEntity = new Gson().fromJson(new Gson().toJson(blogModel),BlogEntity.class);

            TeacherModel teacherModel = teacherRepository.find("id",
                    blogEntity.getAdded_by()).firstResult();
            blogEntity.setName(teacherModel.getFname());



            String commentQuery = "SELECT COUNT(*) FROM `blog_commented` WHERE blog_id =? ";
            Query comment = blogCommentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,blogModel.getId());
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();

            blogEntity.setComment(commentCount.longValue());



            String likeQuery = "SELECT * FROM `blog_liked` WHERE blog_id =?";
            Query like = blogLikedRepository.getEntityManager().createNativeQuery(likeQuery);
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

    ArrayList getCurrentAffair(int id,long studId,String start,String end){
        String caQuery = "SELECT * from `current_affairs` where  inst_id = ? and created_at BETWEEN ? AND ?";
        Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                CurrentAffairModel.class);
        currentAffair.setParameter(1,id);
        currentAffair.setParameter(2,start);
        currentAffair.setParameter(3,end);
        ArrayList<CurrentAffairEntity> currentAffairList =  new ArrayList<>();

        ArrayList<CurrentAffairModel> caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();
        caList.forEach(currentAffairModel -> {
            CurrentAffairEntity entity = new Gson().fromJson(new Gson().toJson(currentAffairModel),CurrentAffairEntity.class);
            TeacherModel teacherModel = teacherRepository.find("id",
                    currentAffairModel.getAdded_by().longValue()).firstResult();

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


            currentAffairList.add(entity);


        });

        return  currentAffairList;
    }

    ArrayList getPost(long studId,int id,String start,String end){

        String studentPostQuery = "SELECT student_post_demo.id,student_post_demo.description," +
                "student_post_demo.pic,student_post_demo.post_status,student_post_demo.added_by,\n" +
                "student_post_demo.added_on, students.fname FROM `student_post_demo` " +
                "INNER JOIN students ON students.id=student_post_demo.added_by " +
                "WHERE student_post_demo.inst_id = ? and  student_post_demo.added_on BETWEEN ? and ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,id);
        studentPost.setParameter(2,start);
        studentPost.setParameter(3,end);

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


    ArrayList getNotes(long studId,long id,String start,String end){
        ArrayList<NotesEntity> arrayList = new ArrayList<>();
        String notesQuery = "SELECT * from `notes` where inst_id = ? and created_at between ? and  ?";
        Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery, NotesModel.class);
        notes.setParameter(1,id);
        notes.setParameter(2,start);
        notes.setParameter(3,end);

        ArrayList<NotesModel> notesList = (ArrayList<NotesModel>) notes.getResultList();
        notesList.forEach(notesModel -> {
            NotesEntity entity = new Gson().fromJson(new Gson().toJson(notesModel),NotesEntity.class);

            TeacherModel teacherModel = teacherRepository.find("id",
                    entity.getCreated_by().longValue()).firstResult();

            entity.setTeacherName(teacherModel.getFname());

            String commentQuery = "SELECT COUNT(*) FROM `notes_comment` WHERE notes_id =? ";
            Query comment = notesComentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,notesModel.getId());
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            entity.setComment(commentCount.longValue());


            String likeQuery = "SELECT * FROM `notes_liked` WHERE notes_id =?";
            Query like = notesLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1,notesModel.getId());
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if(studId == Long.parseLong(likeObject[1].toString())){
                    System.out.println("Liked");
                    entity.setLiked(true);
                }
            });

            Integer likeCount =  likeList.size();
            entity.setLike(likeCount.longValue());


            arrayList.add(entity);


        });

        return  arrayList;

    }

}
