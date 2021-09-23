package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetExploreRequest;
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


    @Inject
    BlogLikedRepository blogLikedRepository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    NotesCommentRepository notesComentRepository;

    @Inject
    SubjectRepository subjectRepository;

    @Inject
    SubSubject_Repository subSubject_repository;

    @Inject
    TopicsRepository topicsRepository;

    @Inject
    CoursesRepository coursesRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)



    @Transactional

    public GetExploreDataResponse getData(GetExploreRequest request){

        System.out.println(request.getFilter());
        System.out.println(request.getDate());
        ArrayList<BlogEntity> blogList = new ArrayList<>();
        ArrayList<CurrentAffairEntity> currentAffairList = new ArrayList<>();
        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        ArrayList<NotesEntity> notesList = new ArrayList<>();


        if(!request.getFilter().isEmpty()){
            System.out.println(request.getFilter());
            System.out.println("Some filter entry");

            if(request.getFilter().equals("blogs")){
                blogList = getBlog(request.getInstId().intValue(),request.getStudId(),"","");
            }else if(request.getFilter().equals("currentAffair")){
                currentAffairList = getCurrentAffair(request.getInstId().intValue(),
                        request.getStudId(),"","");
            }else if(request.getFilter().equals("studentPost")){
                postList = getPost(request.getStudId(),request.getInstId().intValue(),"","");
            }else if(request.getFilter().equals("notes")){
                notesList = getNotes(request.getStudId(),request.getInstId().intValue(),"","");
            }
        }else if(!request.getDate().isEmpty()){
            System.out.println(request.getDate());
            System.out.println("Some date entry");
            String start = request.getDate()+" 00:00:00";
            String end = request.getDate()+" 23:59:59";
            blogList = getBlog(request.getInstId().intValue(),request.getStudId(),start,end);
            currentAffairList = getCurrentAffair(request.getInstId().intValue(),
                    request.getStudId(),start,end);
            postList = getPost(request.getStudId(),request.getInstId().intValue(),start,end);
            notesList = getNotes(request.getStudId(),request.getInstId().intValue(),start,end);

        }else{
            System.out.println("Everything is empty");

            blogList = getBlog(request.getInstId().intValue(),request.getStudId(),"","");

            currentAffairList = getCurrentAffair(request.getInstId().intValue(),request.getStudId(),"","");
            postList = getPost(request.getStudId(),request.getInstId().intValue(),"","");

            notesList = getNotes(request.getStudId(),request.getInstId().intValue(),"","");

        }


//
//        if(request.getFilter().equals("all")||request.getFilter().isEmpty()||
//                request.getFilter().equals("")
//            ||  request.getDate().isEmpty()||request.getDate().equals("")){
//
//           }else {
//
//            System.out.println("Some filter entry");
//
//            if(request.getDate()!=null ||!request.getDate().equals("")||!request.getDate().isEmpty()){
//
//
//            }
//            if(request.getFilter()!=null ||!request.getFilter().equals("")||
//                    !request.getFilter().isEmpty()){
//
//            }
//
//
//
//
//
//
//        }




//        GetQuizRequest request = new GetQuizRequest();
//        request.setInst_id(id);
//        request.setType("Quiz");
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




    ArrayList getBlog(int id,long studId,String start,String end){

        ArrayList<BlogEntity> arrayList = new ArrayList<>();
        ArrayList<BlogModel> blogList;
        if(start.equals("")&&end.equals("")){
            String blogQuery = "SELECT * from `blog` where inst_id = ? ;";
            Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
            blog.setParameter(1,id);
            blogList = (ArrayList<BlogModel>) blog.getResultList();
        }else{
            String blogQuery = "SELECT * from `blog` where inst_id = ? and  blog.created_on between ? and ?;";
            Query blog = blogRepository.getEntityManager().createNativeQuery(blogQuery, BlogModel.class);
            blog.setParameter(1,id);
            blog.setParameter(2,start);
            blog.setParameter(3,end);
            blogList = (ArrayList<BlogModel>) blog.getResultList();
        }



        blogList.forEach(blogModel -> {

            BlogEntity blogEntity = new Gson().fromJson(new Gson().toJson(blogModel),BlogEntity.class);

            TeacherModel teacherModel = teacherRepository.find("id",
                    blogEntity.getAdded_by().longValue()).firstResult();
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
        ArrayList<CurrentAffairEntity> currentAffairList =  new ArrayList<>();
        ArrayList<CurrentAffairModel> caList;
        if(start.equals("") &&end.equals("")){
            String caQuery = "SELECT * from `current_affairs` where  inst_id = ? ;";
            Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                    CurrentAffairModel.class);
            currentAffair.setParameter(1,id);

            caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();

        }else{
            String caQuery = "SELECT * from `current_affairs` where  inst_id = ? and  created_at between ? and ?;";
            Query currentAffair = currentAffairRepository.getEntityManager().createNativeQuery(caQuery,
                    CurrentAffairModel.class);
            currentAffair.setParameter(1,id);
            currentAffair.setParameter(2,start);
            currentAffair.setParameter(3,end);

            caList = (ArrayList<CurrentAffairModel>) currentAffair.getResultList();

        }


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

        ArrayList<StudentPostEntity> postList = new ArrayList<>();
        ArrayList<Object[]> tempPostList;
        if(start.equals("")&&end.equals("")){
            String studentPostQuery = "SELECT student_post_demo.id,student_post_demo.description," +
                    "student_post_demo.pic,student_post_demo.post_status,student_post_demo.added_by,\n" +
                    "student_post_demo.added_on, students.fname FROM `student_post_demo` " +
                    "INNER JOIN students ON students.id=student_post_demo.added_by " +
                    "WHERE student_post_demo.inst_id = ?";
            Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
            studentPost.setParameter(1,id);

           tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        }else{
            String studentPostQuery = "SELECT student_post_demo.id,student_post_demo.description," +
                    "student_post_demo.pic,student_post_demo.post_status,student_post_demo.added_by,\n" +
                    "student_post_demo.added_on, students.fname FROM `student_post_demo` " +
                    "INNER JOIN students ON students.id=student_post_demo.added_by " +
                    "WHERE student_post_demo.inst_id =? and  student_post_demo.added_on between ? and ?";
            Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
            studentPost.setParameter(1,id);
            studentPost.setParameter(2,start);
            studentPost.setParameter(3,end);

            tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        }


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
        ArrayList<NotesModel> notesList;
        if(start.equals("") && end.equals("")){
            String notesQuery = "SELECT * from `notes` where inst_id = ? ;";
            Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery, NotesModel.class);
            notes.setParameter(1,id);

            notesList  = (ArrayList<NotesModel>) notes.getResultList();
        }else{
            String notesQuery = "SELECT * from `notes` where inst_id = ? and created_at between ? and ?;";
            Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery, NotesModel.class);
            notes.setParameter(1,id);
            notes.setParameter(2,start);
            notes.setParameter(3,end);

            notesList  = (ArrayList<NotesModel>) notes.getResultList();
        }



      notesList.forEach(notesModel -> {
          NotesEntity entity = new Gson().fromJson(new Gson().toJson(notesModel),NotesEntity.class);

          SubjectModel subjectModel = (SubjectModel) subjectRepository.
                  find("id",notesModel.getSubject_id().longValue()).firstResult();
          entity.setSubject(subjectModel.getSubject());

          CourseModel courseModel = coursesRepository.find("id",Long.parseLong(notesModel.getCourse_id())).firstResult();
          entity.setCourse(courseModel.getCourse());

          TeacherModel teacherModel = teacherRepository.find("id",
                  entity.getCreated_by().longValue()).firstResult();
          entity.setTeacherName(teacherModel.getFname());

          TopicModel topicModel = topicsRepository.find("id",
                  notesModel.getTopicId().longValue()).firstResult();
          entity.setTopic(topicModel.getTopic());

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
