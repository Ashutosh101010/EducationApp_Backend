package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetTodaysUpdateRequest;
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
import javax.ws.rs.POST;
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

    @Inject
    NotesCommentRepository notesComentRepository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    SubjectRepository subjectRepository;

    @Inject
    SubSubject_Repository subSubject_repository;

    @Inject
    TopicsRepository topicsRepository;

    @Inject
    BannerImageRepository imageRepository;

    @Inject
    QuizRepository quizRepository;





    @POST
    @Produces({MediaType.APPLICATION_JSON})







    @Transactional
    public LatestUpdateResponse getTodayUpdate(GetTodaysUpdateRequest request){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Calendar now = Calendar.getInstance();
        String date = sdf.format(now.getTime());
        String start = date + " 00:00:00";
        String end = date + " 23:59:59";

//
//        Calendar now = Calendar.getInstance();
////        System.out.println(now.getTime());
//        now.set(Calendar.HOUR, 0);
//        now.set(Calendar.MINUTE, 0);
//        now.set(Calendar.SECOND, 0);
//        now.set(Calendar.HOUR_OF_DAY,0);
////        now.set(Calendar.MILLISECOND, 0);
//
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR ,23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 59);
//        calendar.set(Calendar.MILLISECOND, 999);
//
//        System.out.println(sdf.format(now.getTime()));
//        System.out.println(sdf.format(calendar.getTime()));


       ArrayList<BlogEntity> blogList= getBlog(request.getInstID(),request.getStudId(),start,end
               );

        ArrayList<CurrentAffairEntity> currentAffairList = getCurrentAffair(request.getInstID(),request.getStudId(),
                start,end);

        ArrayList<StudentPostEntity> postList = getStudentPost(request.getInstID(),request.getStudId(),
                start,end);


        ArrayList<NotesEntity>  notesList = getNotes(request.getInstID(),request.getStudId(),
                start,end);

        ArrayList<QuizModel> quizList = getQuiz(request.getInstID(),request.getStudId(),start,end);


        ArrayList<Banners> bannerList = (ArrayList<Banners>)
                imageRepository.list("inst_id",request.getInstID());



    LatestUpdateResponse response = new LatestUpdateResponse();

    response.setMessage("Get Latest Updates Successful");
    response.setStatus(true);
    response.setErrorCode(0);
    response.setBlogList(blogList);
    response.setCurrentAffairArrayList(currentAffairList);
    response.setNotesList(notesList);
    response.setPostList(postList);
    response.setImageList(bannerList);
    response.setQuizList(quizList);



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
            System.out.println("Blog model add by="+blogModel.getAdded_by());
            BlogEntity blogEntity = new Gson().fromJson(new Gson().toJson(blogModel),BlogEntity.class);
          TeacherModel teacherModel = teacherRepository.find("id",blogEntity.getAdded_by().longValue()).firstResult();
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
        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` " +
                "INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.added_on BETWEEN ? and ? AND " +
                "student_posts.inst_id = ? and  student_posts.post_status=1";
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



    ArrayList getNotes(long id,long studId,String start,String end){

        ArrayList<NotesEntity> arrayList = new ArrayList<>();



        String notesQuery = "SELECT * from `notes` where inst_id = ? and notes.created_at between ? and ?;";
        Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery, NotesModel.class);
        notes.setParameter(1,id);
        notes.setParameter(2,start);
        notes.setParameter(3,end);

        ArrayList<NotesModel> notesList = (ArrayList<NotesModel>) notes.getResultList();
        notesList.forEach(notesModel -> {
            NotesEntity entity = new Gson().fromJson(new Gson().toJson(notesModel),NotesEntity.class);

           SubjectModel subjectModel = (SubjectModel) subjectRepository.
                   find("id",notesModel.getSubject_id().longValue()).firstResult();

           entity.setSubject(subjectModel.getSubject());

           CourseModel courseModel = coursesRepository.find("id",Long.parseLong(notesModel.getCourse_id())).firstResult();
           entity.setCourse(courseModel.getCourse());

//           SubSubjectModel subSubjectModel = subSubject_repository.find("id",
//                   Long.parseLong(notesModel.getSub_subject_id())).firstResult();
//           entity.setSubSubject(subSubjectModel.getSub_subject());

           TopicModel topicModel = topicsRepository.find("id",
                   notesModel.getTopicId().longValue()).firstResult();
           entity.setTopic(topicModel.getTopic());
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

    private ArrayList<QuizModel> getQuiz(long instID, long studId, String start, String end) {

        ArrayList<QuizModel> quizList =  new ArrayList<>();

        String string = "SELECT * from quiz_master where inst_id = ? and added_on between ? and ? ";

        Query query = quizRepository.getEntityManager().createNativeQuery(string, QuizModel.class);
        query.setParameter(1, instID);
        query.setParameter(2, start);
        query.setParameter(3, end);
        quizList = (ArrayList<QuizModel>) query.getResultList();


        return quizList;

}
    }







