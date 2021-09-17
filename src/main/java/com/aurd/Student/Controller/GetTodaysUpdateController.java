package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Response.LatestUpdateResponse;
import com.aurd.Student.Repository.*;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;
import com.google.gson.Gson;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;

import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


@Path("/getTodaysUpdate")
public class GetTodaysUpdateController {

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository currentAffairRepository;

    @Inject
    NotesRepository notesRepository;

    @Inject
    StudentPostRepository postRepository;

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
    TeacherRepository teacherRepository;

    @GET
    @Produces({MediaType.APPLICATION_JSON})






    @Transactional
    public LatestUpdateResponse getTodayUpdate(@QueryParam ("instID") long instID,
                                               @QueryParam ("studId") long studId){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(instID);
        System.out.println(studId);

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR ,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59 );


       ArrayList<BlogEntity> blogList= getBlog(instID,studId,
               sdf.format(now.getTime()),sdf.format(calendar.getTime()));

        ArrayList<CurrentAffairEntity> currentAffairList = getCurrentAffair(instID,studId,
                sdf.format(now.getTime()),sdf.format(calendar.getTime()));

        ArrayList<StudentPostEntity> postList = getStudentPost(instID,studId, sdf.format(now.getTime()),
                sdf.format(calendar.getTime()));




    LatestUpdateResponse response = new LatestUpdateResponse();

    response.setMessage("Get Latest Updates Successful");
    response.setStatus(true);
    response.setErrorCode(0);
    response.setBlogList(blogList);
    response.setCurrentAffairArrayList(currentAffairList);
//    response.setNotesList(notesList);
    response.setPostList(postList);



        return  response;


    }




    ArrayList getBlog(long id,long studId,String start,String end){
        ArrayList<BlogEntity> arrayList = new ArrayList<>();
        String blogQuery = "SELECT * from `blog` where created_on BETWEEN ? AND ? AND inst_id = ? ;";
        Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
        blog.setParameter(1,start);
        blog.setParameter(2,end);
        blog.setParameter(3,id);
        ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();
        blogList.forEach(blogModel -> {
            BlogEntity blogEntity = new Gson().fromJson(new Gson().toJson(blogModel),BlogEntity.class);
          TeacherModel teacherModel = teacherRepository.find("id",blogModel.getAdded_by()).firstResult();
          blogEntity.setName(teacherModel.getFname());


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

    ArrayList getCurrentAffair(long id,long studId,String start,String end){

        ArrayList<CurrentAffairEntity> currentAffairList =  new ArrayList<>();


        String caQuery = "SELECT * from `current_affairs` where created_at BETWEEN ? AND ? AND inst_id = ? ;";
        Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                CurrentAffairModel.class);
        currentAffair.setParameter(1,start);
        currentAffair.setParameter(2,end);
        currentAffair.setParameter(3,id);
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


    ArrayList getStudentPost(long id,long studId,String start,String end){

        String studentPostQuery = "SELECT student_post_demo.id,student_post_demo.description," +
                "student_post_demo.pic,student_post_demo.post_status,student_post_demo.added_by,\n" +
                "student_post_demo.added_on, students.fname FROM `student_post_demo` " +
                "INNER JOIN students ON students.id=student_post_demo.added_by " +
                "WHERE student_post_demo.added_on BETWEEN ? and ? AND student_post_demo.inst_id = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,start);
        studentPost.setParameter(2,end);
        studentPost.setParameter(3,id);
        ArrayList<StudentPostEntity> postList = new ArrayList<>();

        try{
            ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.getResultList();

            tempPostList.forEach(objects -> {
                StudentPostEntity postModel = new StudentPostEntity();
                postModel.setId(Long.parseLong(objects[0].toString()));
                postModel.setDescription(objects[1].toString());
                postModel.setPic(objects[2].toString());
                postModel.setPostStatus(Integer.parseInt(objects[3].toString()));
                postModel.setAdded_by(Integer.parseInt(objects[4].toString()));
                postModel.setAdded_on(Timestamp.valueOf(objects[5].toString()));
                postModel.setName(objects[6].toString());

                String commentQuery = "SELECT COUNT(*) FROM `student_posts_commented` WHERE post_id =?";
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


//      getLikes(postModel,Long.parseLong(objects[0].toString()),studId);


                postList.add(postModel);
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        return  postList;

    }



}
