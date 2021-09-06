package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Response.LatestUpdateResponse;
import com.aurd.Student.Repository.*;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;

import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Produces({MediaType.APPLICATION_JSON})




    @Transactional
    public LatestUpdateResponse getTodayUpdate(@QueryParam ("instID") long instID){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR ,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59 );

        String blogQuery = "SELECT * from `blog` where created_on BETWEEN ? AND ? AND inst_id = ? ;";
        Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
        blog.setParameter(1,"2021-03-13 00:00:01");
        blog.setParameter(2,"2021-03-13 23:59:59");
        blog.setParameter(3,instID);
        ArrayList<BlogModel> blogList = (ArrayList<BlogModel>) blog.getResultList();


        String caQuery = "SELECT * from `current_affairs` where created_at BETWEEN ? AND ? AND inst_id = ? ;";
        Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                CurrentAffairModel.class);
        currentAffair.setParameter(1,"2021-03-13 00:00:01");
        currentAffair.setParameter(2,"2021-03-13 23:59:59");
        currentAffair.setParameter(3,instID);
        ArrayList<CurrentAffairModel> caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();


        String notesQuery = "SELECT notes.name, notes.file,notes.created_at, topics.topic, employees.fname, employees.id" +
                " FROM notes INNER JOIN topics ON topics.id=notes.topicId INNER JOIN employees ON employees.id=notes.teacher_id" +
                " WHERE notes.inst_id = ? AND notes.created_at BETWEEN ? AND ? ";
        Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery);
        notes.setParameter(1,54);
        notes.setParameter(2,"2021-02-01 00:00:00");
        notes.setParameter(3,"2021-02-01 23:59:59");
        ArrayList<Object[]> tempList = (ArrayList<Object[]>) notes.getResultList();
        ArrayList<NotesEntity> notesList = new ArrayList<>();
        tempList.forEach(objects ->{
            NotesEntity notesEntity = new NotesEntity();
            notesEntity.setName(objects[0].toString());
            notesEntity.setFile(objects[1].toString());
            notesEntity.setTopic(objects[3].toString());
            notesEntity.setTeacherName(objects[4].toString());
            notesEntity.setTeacher_id(Integer.parseInt(objects[5].toString()));

            notesList.add(notesEntity);
        });


        String studentPostQuery = "SELECT student_posts.id,student_posts.discription,student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.added_on BETWEEN ? and ? AND student_posts.inst_id = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1,"2021-05-25 00:00:00");
        studentPost.setParameter(2,"2021-05-25 23:59:59");
        studentPost.setParameter(3,53);
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
       comment.setParameter(1,12);
      Integer commentCount = ((Number) comment.getSingleResult()).intValue();
      postModel.setComment(commentCount.longValue());


          String likeQuery = "SELECT COUNT(*) FROM `student_posts_liked` WHERE post_id =? ";
          Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
          like.setParameter(1,13);
          Integer likeCount = ((Number) like.getSingleResult()).intValue();
          postModel.setLike(likeCount.longValue());

         postList.add(postModel);
      });

    LatestUpdateResponse response = new LatestUpdateResponse();

    response.setMessage("Get Latest Updates Successful");
    response.setStatus(true);
    response.setErrorCode(0);
    response.setBlogList(blogList);
    response.setCurrentAffairList(caList);
    response.setNotesList(notesList);
    response.setPostList(postList);



        return  response;


    }


}
