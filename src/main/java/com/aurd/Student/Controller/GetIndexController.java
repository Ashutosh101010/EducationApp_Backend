package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.*;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetIndexRequest;
import com.aurd.Student.Model.Response.GetIndexResponse;
import com.aurd.Student.Repository.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Path("/getIndex")

public class GetIndexController {

    @Inject
    IndexRepository indexRepository;

    @Inject
    BlogRepository blogRepository;

    @Inject
    CurrentAffairRepository caRepository;

    @Inject
    NotesRepository notesRepository;

    @Inject
    NotesLikeDislikeRepository notesLikeDislikeRepository;

    @Inject
    BlogLikedRepository blogLikedRepository;

    @Inject
    TeacherRepository teacherRepository;


    @Inject
    CurrentAffairLikeDislikeRepository currentAffairLikeDislikeRepository;

    @Inject
    VideoLectureRepository videoLectureRepository;

    @Inject
    SubjectRepository subjectRepository;
//
//    @Inject
//    SubSubject_Repository subSubject_repository;

    @Inject
    TopicsRepository topicsRepository;

    @Inject
    CoursesRepository coursesRepository;


    @Inject
    StudentPostRepository postRepository;

    @Inject
    StudentPostLikedRepository likedRepository;

    @Inject
    BookMarkRepository bookMarkRepository;

    @Inject
    QuizRepository quizRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional
    public GetIndexResponse getIndex(GetIndexRequest request) throws ParseException {


        ArrayList<Index_Model> arrayList = new ArrayList<>();
        ArrayList<Object[]> list = null;
        ArrayList<Object> vList = new ArrayList<>();
        int maxResultCount =5;

            System.out.println(request.getFilter());
            System.out.println("Some filter entry");


        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(request.getFilter()!=null && !request.getFilter().isEmpty())
            {
                Query query=null;
                if(request.getLastId()!=null && !request.getLastId().isEmpty())
                {
                     query =  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId and " +
                            "Index_Model.type=:type and Index_Model.created_on < : lastId and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ");
                    query.setParameter("instId",request.getInst_id());
                    query.setParameter("type",request.getFilter());
                    query.setParameter("lastId",new Date(Long.valueOf(request.getLastId())));

//                    arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());

                }
                else{
                     query=  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId and " +
                            "Index_Model.type=:type and Index_Model.created_on between :startDate and :endDate order by Index_Model.created_on desc ")
                            .setParameter("instId",request.getInst_id())
                            .setParameter("type",request.getFilter());


                }

                if(request.getDate()!=null && !request.getDate().isEmpty())
                {
                    Date startDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime());
                    System.out.println(startDate);
                    query.setParameter("startDate",startDate);
//                    query.setParameter("startDate",formatter.parse(request.getDate()+" 00:00:00"));
                    Date endDate=new Date(formatter.parse(request.getDate()+" 23:59:59").getTime());
                    System.out.println(endDate);
                    query.setParameter("endDate",endDate);

                }
                else{
                    query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
                    query.setParameter("endDate",new Date(System.currentTimeMillis()));
                }

                arrayList=new ArrayList<>(query.setMaxResults(maxResultCount).getResultList());
            }
            else{
            Query query=null;
                if(request.getLastId()!=null && !request.getLastId().isEmpty())
                {
                     query =  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId " +
                            " and Index_Model.created_on < : lastId and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ");
                    query.setParameter("instId",request.getInst_id());
                    query.setParameter("lastId",new Date(Long.valueOf(request.getLastId())));


                }
                else{
                     query=  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId " +
                            " and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ")
                            .setParameter("instId",request.getInst_id());

                }
            if(request.getDate()!=null && !request.getDate().isEmpty())
            {
                Date startDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime()-86400000);
                System.out.println(startDate);
                query.setParameter("startDate",startDate);

                Date endDate=new Date(formatter.parse(request.getDate()+" 23:59:59").getTime()+86400000);
                System.out.println(endDate);
                query.setParameter("endDate",endDate);

            }
            else{
                query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
                query.setParameter("endDate",new Date(System.currentTimeMillis()));
            }

            arrayList=new ArrayList<>(query.setMaxResults(maxResultCount).getResultList());
            }





         System.out.println(arrayList.size());

         arrayList.forEach(model -> {

             model.setTimeStamp(model.getCreated_on().getTime());
             if(model.getType().equals("blog")){
             //    Long value = Long.valueOf(model.getPost_id());

                 BlogEntity blogEntity = getBlogs(model,request);
                 blogEntity.setIndexId(model.getId());
                 blogEntity.setTimeStamp(blogEntity.getCreated_on().getTime());
                 vList.add(blogEntity);


             } else if(model.getType().equals("current_affair")){
                 CurrentAffairEntity entity = getCurrentAffair(model,request);
                 entity.setIndexId(model.getId());
                 entity.setTimeStamp(entity.getCreated_at().getTime());
                 vList.add(entity);


             }else if(model.getType().equals("notes")){
                 NotesEntity notesEntity = getNotes(model,request);
                 notesEntity.setIndexId(model.getId());
                 notesEntity.setTimeStamp(notesEntity.getCreated_at().getTime());
                 vList.add(notesEntity);

             }else if(model.getType().equals("video")){
                 VideoEntity videoEntity = getVideos(model,request);
                 videoEntity.setIndexId(model.getId());
                 videoEntity.setTimeStamp(videoEntity.getCreated_at().getTime());
                 vList.add(videoEntity);

             }else if(model.getType().equals("post")){
                 StudentPostEntity entity= getPost(model,request);
                 entity.setIndexId(model.getId());
                 entity.setTimeStamp(model.getCreated_on().getTime());
                 vList.add(entity);
             }else if(model.getType().equals("quiz")){
                 QuizEntity quizEntity = getQuizzes(model,request);
                 quizEntity.setIndexId(model.getId());
                 quizEntity.setTimeStamp(model.getCreated_on().getTime());
                 vList.add(quizEntity);
             }
             else if(model.getType().equals("practiceTest"))
             {
                 QuizEntity quizEntity =getAllTestSeries(model,request);
                 quizEntity.setIndexId(model.getId());
                 quizEntity.setTimeStamp(quizEntity.getAdded_on().getTime());
                 vList.add(quizEntity);
             }

         });

        GetIndexResponse getIndexResponse= new GetIndexResponse();
        getIndexResponse.setIndex(arrayList);
        getIndexResponse.setMessage("Get Index Successfully");
        getIndexResponse.setStatus(true);
        getIndexResponse.setErrorCode(0);
        getIndexResponse.setObjList(vList);


        return getIndexResponse;
    }



    BlogEntity getBlogs(Index_Model model,GetIndexRequest request){


        Long val = Long.valueOf(model.getPost_id());
        Long inst = request.getInst_id();
      BlogModel bm =   blogRepository.find("id=?1 and inst_id=?2",
              val,inst).firstResult();

      BlogEntity blogEntity = new Gson().fromJson(new Gson().toJson(bm),BlogEntity.class);
        blogEntity.setType("blog");
        String likeQuery = "SELECT * FROM `blog_liked` WHERE blog_id =? ";
        Query like = blogLikedRepository.getEntityManager().createNativeQuery(likeQuery);
        like.setParameter(1, blogEntity.getId());
        ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
        likeList.forEach(likeObject -> {
            if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                System.out.println("Liked");
                blogEntity.setLiked(true);
            }
        });

        Integer likeCount = likeList.size();
        blogEntity.setLike(likeCount.longValue());


        TeacherModel teacherModel = teacherRepository.find("id",
                blogEntity.getAdded_by().longValue()).firstResult();
        blogEntity.setName(teacherModel.getFname());




        return  blogEntity;
    }


    CurrentAffairEntity getCurrentAffair(Index_Model model,GetIndexRequest request){
        Long val = Long.valueOf(model.getPost_id());
        Long inst = request.getInst_id();
      CurrentAffairModel currentAffairModel=  caRepository.
              find("id=?1 and inst_id =?2",
                      val,inst.intValue()).firstResult();
      CurrentAffairEntity caEntity = new Gson().fromJson
              (new Gson().toJson(currentAffairModel),CurrentAffairEntity.class);

      caEntity.setType("currentAffair");
      caEntity.setTimeStamp(currentAffairModel.getTime().getTime());


        String likeQuery = "SELECT * FROM `current_affairs_liked` WHERE current_affair_id =? ";
        Query like = currentAffairLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
        like.setParameter(1, caEntity.getId());
        ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
        likeList.forEach(likeObject -> {
            if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                System.out.println("Liked");
                caEntity.setLiked(true);
            }
        });


        Integer likeCount = likeList.size();
        caEntity.setLike(likeCount.longValue());


        TeacherModel teacherModel = teacherRepository.find("id",
                caEntity.getAdded_by().longValue()).firstResult();
        caEntity.setName(teacherModel.getFname());



        return caEntity;

    }


    NotesEntity getNotes(Index_Model model,GetIndexRequest request){
        Long val = Long.valueOf(model.getPost_id());

        NotesModel notesModel =   notesRepository.find("id =?1 and inst_id =?2",
             val,request.getInst_id().intValue()).firstResult();
     NotesEntity entity = new Gson().fromJson(new Gson().toJson(notesModel),NotesEntity.class);
     entity.setName(notesModel.getName());

        String likeQuery = "SELECT * FROM `notes_liked` WHERE notes_id =? ";
        Query like = notesLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
        like.setParameter(1, entity.getId());
        ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
        likeList.forEach(likeObject -> {
            if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                System.out.println("Liked");
                entity.setLiked(true);
            }
        });

        Integer likeCount = likeList.size();
        entity.setLike(likeCount.longValue());
        entity.setType("notes");

        SubjectModel subjectModel = subjectRepository.
                find("id", notesModel.getSubject_id().longValue()).firstResult();
        entity.setSubject(subjectModel.getSubject());

        CourseModel courseModel = coursesRepository.find("id",
                Long.parseLong(notesModel.getCourse_id())).firstResult();
        entity.setCourse(courseModel.getCourse());

        TeacherModel teacherModel = teacherRepository.find("id",
                entity.getCreated_by().longValue()).firstResult();
        entity.setTeacherName(teacherModel.getFname());

        TopicModel topicModel = topicsRepository.find("id",
                notesModel.getTopicId().longValue()).firstResult();
        entity.setTopic(topicModel.getTopic());



        return  entity;

    }


    StudentPostEntity getPost(Index_Model model, GetIndexRequest request){
        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname FROM `student_posts` " +
                "INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ? and student_posts.id = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1, request.getInst_id());
        studentPost.setParameter(2,model.getPost_id());
//        studentPost.setParameter(3,1);
        StudentPostEntity postModel = new StudentPostEntity();
        ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        tempPostList.forEach(objects -> {

            postModel.setId(Long.parseLong(objects[0].toString()));
            if(objects[1].toString()==null){
                postModel.setDescription("");
            }else{
                postModel.setDescription(objects[1].toString());
            }

            postModel.setDescription(objects[1].toString());

            if( objects[2]==null){
                postModel.setPic("");
            }else{
                postModel.setPic(objects[2].toString());
            }



            postModel.setPostStatus(Integer.parseInt(objects[3].toString()));
            postModel.setAdded_by(Integer.parseInt(objects[4].toString()));
            postModel.setAdded_on(Timestamp.valueOf(objects[5].toString()));
            postModel.setName(objects[6].toString());

            String likeQuery = "SELECT * FROM `student_posts_liked` WHERE post_id =?";
            Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1, postModel.getId());
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                    System.out.println("Liked");
                    postModel.setLiked(true);
                }
            });

            Integer likeCount = likeList.size();
            postModel.setLike(likeCount.longValue());


            ArrayList<BookMarkModel> arrayList = (ArrayList<BookMarkModel>)
                    bookMarkRepository.list("type=?1 and post_id=?2",
                            "post",postModel.getId());
            arrayList.forEach(bookMarkModel -> {

                if(bookMarkModel.getAdded_by()==request.getStudId()){
                    postModel.setAdded(true);
                }else{
                    postModel.setAdded(false);
                }
            });

            postModel.setType("post");

        });

        return postModel;

    }



    QuizEntity getQuizzes(Index_Model model, GetIndexRequest request){
        Long val = Long.valueOf(model.getPost_id());
        QuizModel quizModel =  quizRepository.find("id =?1 and inst_id = ?2 and type = ?3",
                val,request.getInst_id().intValue(),"Quiz").firstResult();

        QuizEntity entity= new Gson().fromJson(new Gson().toJson(quizModel),QuizEntity.class);

        return  entity;
    }

    VideoEntity getVideos(Index_Model model, GetIndexRequest request){
        Long val = Long.valueOf(model.getPost_id());

       VideoModel videoModel = videoLectureRepository.find("inst_id =?1 and id =?2", request.getInst_id().intValue(),val).firstResult();
       VideoEntity entity = new Gson().fromJson(new Gson().toJson(videoModel),VideoEntity.class);

       entity.setType("video");

        TeacherModel teacherModel = teacherRepository.find("id",
                videoModel.getTeacher_id()).firstResult();
        entity.setTeacherName(teacherModel.getFname());

        return  entity;
    }


    QuizEntity getAllTestSeries(Index_Model model, GetIndexRequest request) {

        System.out.println(model.getPost_id());
        //  System.out.println(request.getInst_id());

        Long val = Long.valueOf(model.getPost_id());

        QuizModel quizModel = quizRepository.find("inst_id=?1 and quiz_id = ?2 and type = ?3 ", request.getInst_id().intValue(), val, "Monthly Test").firstResult();
        QuizEntity entity = new QuizEntity();
        entity.setQuiz_id(quizModel.getQuiz_id());
        entity.setInst_id(quizModel.getInst_id());

        if (quizModel.getSubject_id() == null) {
            entity.setSubject_id(0);
        } else entity.setSubject_id(quizModel.getSubject_id());

        entity.setSub_subject_id(quizModel.getSub_subject_id());
        entity.setCourse_id(quizModel.getCourse_id());
        entity.setDiscription(quizModel.getDiscription());
        entity.setPic(quizModel.getPic());
        entity.setPrice(quizModel.getPrice());

        entity.setTitle(quizModel.getTitle());
        entity.setAdded_by(quizModel.getAdded_by());
        entity.setAdded_on(quizModel.getAdded_on());
        entity.setInstruction(quizModel.getInstruction());
        entity.setUpdated_by(quizModel.getUpdated_by());

        if(quizModel.getMarks_per_ques()==null){
            entity.setMarks_per_ques(0);
        }else entity.setMarks_per_ques(quizModel.getMarks_per_ques());

        if(quizModel.getCutoff()==null){
            entity.setCutoff(null);
        }else entity.setCutoff(quizModel.getCutoff());

        if(quizModel.getQuiz_type()==null){
            entity.setQuiz_type("");
        }else  entity.setQuiz_type(quizModel.getQuiz_type());


        entity.setTotal_ques(quizModel.getTotal_ques());
        entity.setTest_start(quizModel.getTest_start());
        entity.setTime(quizModel.getTime());
        entity.setTest_end(quizModel.getTest_end());



        entity.setType("Practise Test");


        return entity;
    }


}
