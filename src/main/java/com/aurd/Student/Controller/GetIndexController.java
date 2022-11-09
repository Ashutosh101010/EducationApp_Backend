package com.aurd.Student.Controller;


import com.aurd.Student.Constant.Constants;
import com.aurd.Student.Model.BeanClass.*;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetIndexRequest;
import com.aurd.Student.Model.Response.GetIndexResponse;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;
import org.apache.james.mime4j.dom.datetime.DateTime;


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
import java.util.List;


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
    AudioNotesRepository audioNotesRepository;


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

    @Inject
    StudentCourseRepository studentCourseRepository;

    @Inject
    ResultRepository resultRepository;

    @Inject
    AdminRepository adminRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)


    @Transactional
    public GetIndexResponse getIndex(GetIndexRequest request) throws ParseException {

        System.out.println(new Gson().toJson(request));

        List<Index_Model> indexList = new ArrayList<>();
        ArrayList<Object[]> list = null;
        ArrayList<Object> vList = new ArrayList<>();


            System.out.println(request.getFilter());
            System.out.println("Some filter entry");


            Query query=null;
            String startDate="";
            String endDate="";
            if(request.getDate()!=null && !request.getDate().isEmpty()){
                startDate=request.getDate()+" 00:00:00";
                endDate=request.getDate()+" 23:59:59";
            }
            else{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                startDate="2000/01/01 00:00:00";
                endDate=formatter.format(new Date())+" 23:59:59";
            }
            if(request.getFilter()!=null && !request.getFilter().isEmpty())
            {
                query=indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model " +
                        "Index_Model where Index_Model.inst_id=:instId and Index_Model.created_on>=:startDate and Index_Model.created_on<=:endDate and Index_Model.type=:type order by Index_Model.id desc ");
                query.setParameter("instId",request.getInst_id());
                query.setParameter("startDate",new Date(startDate));
                query.setParameter("endDate",new Date(endDate));
                query.setParameter("type",request.getFilter());

            }
           else
            {
                query=indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model " +
                        "Index_Model where Index_Model.inst_id=:instId and Index_Model.created_on>=:startDate and Index_Model.created_on<=:endDate order by Index_Model.id desc ");
                query.setParameter("instId",request.getInst_id());
                query.setParameter("startDate",new Date(startDate));
                query.setParameter("endDate",new Date(endDate));

            }



           query.setFirstResult(request.getPage()*request.getPageCount());
           query.setMaxResults(request.getPageCount());

           indexList=query.getResultList();


           indexList.forEach(index_model -> {
               if(index_model.getType().equals(Constants.indexType.blog.name()))
               {
                   BlogEntity blogEntity=getBlogs(index_model,request);
                   blogEntity.setIndexId(index_model.getId());
                   blogEntity.setTimeStamp(index_model.getTimeStamp());
                   vList.add(blogEntity);
               }
               else if (index_model.getType().equals(Constants.indexType.current_affair.name())) {
                   CurrentAffairEntity entity = getCurrentAffair(index_model, request);
                   entity.setIndexId(index_model.getId());
                   entity.setTimeStamp(entity.getCreated_at().getTime());
                   vList.add(entity);
               }
               else if (index_model.getType().equals(Constants.indexType.notes.name())) {
                   NotesEntity notesEntity = getNotes(index_model, request);
                   notesEntity.setIndexId(index_model.getId());
                   notesEntity.setTimeStamp(notesEntity.getCreated_at().getTime());
                   vList.add(notesEntity);
               }
               else if (index_model.getType().equals(Constants.indexType.video.name())) {
                   VideoEntity videoEntity = getVideos(index_model, request);
                   videoEntity.setIndexId(index_model.getId());
                   videoEntity.setTimeStamp(videoEntity.getCreated_at().getTime());
                   vList.add(videoEntity);
               }
               else if (index_model.getType().equals(Constants.indexType.doubt.name())) {
                   StudentPostEntity entity = getPost(index_model, request);
                   if (entity.getPostStatus() == 1) {
                       entity.setIndexId(index_model.getId());
                       entity.setTimeStamp(index_model.getCreated_on().getTime());
                       vList.add(entity);
                   }
               }
               else if (index_model.getType().equals(Constants.indexType.quiz.name())) {
                   QuizEntity quizEntity = getQuizzes(index_model, request);
                   quizEntity.setIndexId(index_model.getId());
                   quizEntity.setTimeStamp(index_model.getCreated_on().getTime());
                   vList.add(quizEntity);
               }
               else if (index_model.getType().equals(Constants.indexType.test.name())) {
                   QuizEntity quizEntity = getAllTest(index_model, request);
                   quizEntity.setIndexId(index_model.getId());
                   quizEntity.setTimeStamp(quizEntity.getAdded_on().getTime());
                   vList.add(quizEntity);
               }
               else if (index_model.getType().equals(Constants.indexType.audio.name())) {
                   AudioEntity audioEntity = getAudio(index_model, request);
                   audioEntity.setIndexId(index_model.getId());
                   audioEntity.setTimeStamp(audioEntity.getCreated_at().getTime());
                   vList.add(audioEntity);
               }

           });

//        if(request.getFilter()!=null && !request.getFilter().isEmpty())
//            {
//                Query query=null;
//                if(request.getLastId()!=null && !request.getLastId().isEmpty())
//                {
//                     query =  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId and " +
//                            "Index_Model.type=:type and Index_Model.created_on < : lastId and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ");
//                    query.setParameter("instId",request.getInst_id());
//                    query.setParameter("type",request.getFilter());
//                    query.setParameter("lastId",new Date(Long.valueOf(request.getLastId())));
//
////                    arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());
//
//                }
//                else{
//                     query=  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId and " +
//                            "Index_Model.type=:type and Index_Model.created_on between :startDate and :endDate order by Index_Model.created_on desc ")
//                            .setParameter("instId",request.getInst_id())
//                            .setParameter("type",request.getFilter());
//
//
//                }
//
//                if(request.getDate()!=null && !request.getDate().isEmpty())
//                {
////                    Date startDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime());
//                   String startDate = request.getDate()+" 00:00:00";
//                    System.out.println(startDate);
//                    query.setParameter("startDate",new Date(formatter.parse(startDate).getTime()));
//                //    query.setParameter("startDate",formatter.parse(request.getDate()+" 00:00:00"));
////                    Date endDate=new Date(formatter.parse(request.getDate()+" 23:59:59").getTime());
//                    String endDate = request.getDate()+" 23:59:59";
//                    System.out.println(endDate);
//                    query.setParameter("endDate",new Date(formatter.parse(endDate).getTime()));
//
//                }
//                else{
//                    query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
//                    query.setParameter("endDate",new Date(System.currentTimeMillis()));
//                }
//
//                if(request.getPageCount()>0)
//                {
//                    query.setMaxResults(request.getPageCount());
//                }
//                arrayList=new ArrayList<>(query.getResultList());
//            }
//            else{
//            Query query=null;
//                if(request.getLastId()!=null && !request.getLastId().isEmpty())
//                {
//                     query =  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId " +
//                            " and Index_Model.created_on < : lastId and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ");
//                    query.setParameter("instId",request.getInst_id());
//                    query.setParameter("lastId",new Date(Long.valueOf(request.getLastId())));
//
//                }
//                else{
//                     query=  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId " +
//                            " and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ")
//                            .setParameter("instId",request.getInst_id());
//
//                }
//            if(request.getDate()!=null && !request.getDate().isEmpty())
//            {
//
//                Date startDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime());
//                System.out.println(startDate);
//                query.setParameter("startDate",startDate);
//
//                Date endDate=new Date(formatter.parse(request.getDate()+" 23:59:59").getTime());
//                System.out.println(endDate);
//                query.setParameter("endDate",endDate);
//
//            }
//            else{
//                query.setParameter("startDate",new Date(formatter.parse("2000-01-01"+" 00:00:00").getTime()));
//                query.setParameter("endDate",new Date(System.currentTimeMillis()));
//            }
//            if(request.getPageCount()>0)
//            {
//                query.setMaxResults(request.getPageCount());
//            }
//            arrayList=new ArrayList<>(query.getResultList());
//            }

//         System.out.println(arrayList.size());

//         arrayList.forEach(model -> {
//try {
//    model.setTimeStamp(model.getCreated_on().getTime());
//    if (model.getType().equals(Constants.indexType.blog.name())) {
//
//        BlogEntity blogEntity = getBlogs(model, request);
//        blogEntity.setIndexId(model.getId());
//        blogEntity.setTimeStamp(blogEntity.getCreated_on().getTime());
//        vList.add(blogEntity);
//
//
//    }
//    else if (model.getType().equals(Constants.indexType.current_affair.name())) {
//        CurrentAffairEntity entity = getCurrentAffair(model, request);
//        entity.setIndexId(model.getId());
//        entity.setTimeStamp(entity.getCreated_at().getTime());
//        vList.add(entity);
//
//
//    }
//    else if (model.getType().equals(Constants.indexType.notes.name())) {
//        NotesEntity notesEntity = getNotes(model, request);
//        notesEntity.setIndexId(model.getId());
//        notesEntity.setTimeStamp(notesEntity.getCreated_at().getTime());
//        vList.add(notesEntity);
//
//    }
//    else if (model.getType().equals(Constants.indexType.video.name())) {
//        VideoEntity videoEntity = getVideos(model, request);
//
//        videoEntity.setIndexId(model.getId());
//        videoEntity.setTimeStamp(videoEntity.getCreated_at().getTime());
//        vList.add(videoEntity);
//
//    }
//    else if (model.getType().equals(Constants.indexType.post.name())) {
//        StudentPostEntity entity = getPost(model, request);
//        if (entity.getPostStatus() == 1) {
//            entity.setIndexId(model.getId());
//            entity.setTimeStamp(model.getCreated_on().getTime());
//            vList.add(entity);
//        }
//    }
//    else if (model.getType().equals(Constants.indexType.quiz.name())) {
//        QuizEntity quizEntity = getQuizzes(model, request);
//        quizEntity.setIndexId(model.getId());
//        quizEntity.setTimeStamp(model.getCreated_on().getTime());
//        vList.add(quizEntity);
//    }
//    else if (model.getType().equals(Constants.indexType.test.name())) {
//        QuizEntity quizEntity = getAllTest(model, request);
//        quizEntity.setIndexId(model.getId());
//        quizEntity.setTimeStamp(quizEntity.getAdded_on().getTime());
//        vList.add(quizEntity);
//    }
//    else if (model.getType().equals(Constants.indexType.audio.name())) {
//        AudioEntity audioEntity = getAudio(model, request);
//
//        audioEntity.setIndexId(model.getId());
//        audioEntity.setTimeStamp(audioEntity.getCreated_at().getTime());
//        vList.add(audioEntity);
//    }
//
//}catch (Exception e)
//{
//    e.printStackTrace();
//}

//         });




        GetIndexResponse getIndexResponse= new GetIndexResponse();
        getIndexResponse.setIndex(indexList);
        getIndexResponse.setMessage("Get Index Successfully");
        getIndexResponse.setStatus(true);
        getIndexResponse.setErrorCode(0);
        getIndexResponse.setObjList(vList);


        return getIndexResponse;
    }



    BlogEntity getBlogs(Index_Model model,GetIndexRequest request){
        System.out.println("Get Blogs");


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
            if(request.getStudId()!=0){
                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                    System.out.println("Liked");
                    blogEntity.setLiked(true);
                }
            }

        });

        Integer likeCount = likeList.size();
        blogEntity.setLike(likeCount.longValue());


       if(model.getAdded_by().equals("employee")){
           TeacherModel teacherModel = teacherRepository.find("id",
                   blogEntity.getAdded_by().longValue()).firstResult();
           blogEntity.setName(teacherModel.getFname());
           if(teacherModel.getProfile()!=null){
               blogEntity.setImage(teacherModel.getProfile());
           }
       }else if(model.getAdded_by().equals("admin")){
           AdminModel adminModel = adminRepository.find("id",blogEntity.getAdded_by().intValue())
                   .firstResult();
           if(adminModel.getProfile()!=null){
               blogEntity.setImage(adminModel.getProfile());
           }
           blogEntity.setName(adminModel.getName());

       }




        return  blogEntity;
    }


    CurrentAffairEntity getCurrentAffair(Index_Model model,GetIndexRequest request){
        System.out.println("Get Current Affair");
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
            if(request.getStudId()!=0){
                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                    System.out.println("Liked");
                    caEntity.setLiked(true);
                }
            }

        });


        Integer likeCount = likeList.size();
        caEntity.setLike(likeCount.longValue());

        BookMarkModel bookMarkModel = bookMarkRepository.find("type=?1 and post_id=?2" +
                " and added_by =?3","currentAffair",currentAffairModel.getId(),request.getStudId() ).firstResult();

        if(bookMarkModel!=null){
            caEntity.setAdded(true);
        }else{
            caEntity.setAdded(false);
        }



        if(model.getAdded_by().equals("employee")){
            TeacherModel teacherModel = teacherRepository.find("id",
                    caEntity.getAdded_by().longValue()).firstResult();
            caEntity.setName(teacherModel.getFname());
            if(teacherModel.getProfile()!=null){
                caEntity.setImage(teacherModel.getProfile());
            }
        }else if(model.getAdded_by().equals("admin")){
            AdminModel adminModel = adminRepository.find("id",caEntity.getAdded_by().intValue())
                    .firstResult();
            caEntity.setName(adminModel.getName());
            if(adminModel.getProfile()!=null){
                caEntity.setImage(adminModel.getProfile());
            }

        }








        return caEntity;

    }

    NotesEntity getNotes(Index_Model model,GetIndexRequest request){
        System.out.println("Get Notes");
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
            if(request.getStudId()!=0){
                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                    System.out.println("Liked");
                    entity.setLiked(true);
                }
            }

        });

        Integer likeCount = likeList.size();
        entity.setLike(likeCount.longValue());
        entity.setType("notes");

        SubjectModel subjectModel = subjectRepository.
                find("id", notesModel.getSubject_id().intValue()).firstResult();
        entity.setSubject(subjectModel.getSubject());

        CourseModel courseModel = coursesRepository.find("id",
                notesModel.getCourse_id()).firstResult();
        entity.setCourse(courseModel.getCourse());

        if(model.getAdded_by().equals("employee")){
            TeacherModel teacherModel = teacherRepository.find("id",
                    entity.getCreated_by().longValue()).firstResult();
            entity.setTeacherName(teacherModel.getFname());

            if(teacherModel.getProfile()!=null){
                entity.setImage(teacherModel.getProfile());
            }

        }else if(model.getAdded_by().equals("admin")){
            AdminModel adminModel = adminRepository.find("id",
                    entity.getCreated_by().intValue()).firstResult();
            entity.setTeacherName(adminModel.getName());

            if(adminModel.getProfile()!=null){
                entity.setImage(adminModel.getProfile());
            }

        }

      StudentCourseModel scModel = studentCourseRepository.find("userId=?1 and courseId=?2",Long.valueOf(request.getStudId()), ((int) courseModel.getId())).firstResult();
        if(scModel!=null){
            entity.setPurchased(true);
        }else{
            entity.setPurchased(false);
        }


        TopicModel topicModel = topicsRepository.find("id",
                notesModel.getTopicId().longValue()).firstResult();
        entity.setTopic(topicModel.getTopic());



        return  entity;

    }

    StudentPostEntity getPost(Index_Model model, GetIndexRequest request){
        System.out.println("Get Post");


        String studentPostQuery = "SELECT student_posts.id,student_posts.description," +
                "student_posts.pic,student_posts.post_status,student_posts.added_by,\n" +
                "student_posts.added_on, students.fname, students.profile FROM `student_posts` " +
                " INNER JOIN students ON students.id=student_posts.added_by " +
                "WHERE student_posts.inst_id = ? and student_posts.id = ?";
        Query studentPost = postRepository.getEntityManager().createNativeQuery(studentPostQuery);
        studentPost.setParameter(1, request.getInst_id());
        studentPost.setParameter(2,model.getPost_id());
        StudentPostEntity postModel = new StudentPostEntity();
        ArrayList<Object[]> tempPostList = (ArrayList<Object[]>) studentPost.getResultList();
        tempPostList.forEach(objects -> {

            postModel.setId(Long.parseLong(objects[0].toString()));
            if(objects[1]==null){
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


            if(objects[7]!=null){
                postModel.setImage(objects[7].toString());
            }


            String likeQuery = "SELECT * FROM `student_posts_liked` WHERE post_id =?";
            Query like = likedRepository.getEntityManager().createNativeQuery(likeQuery);
            like.setParameter(1, postModel.getId());
            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
            likeList.forEach(likeObject -> {
                if(request.getStudId()!=0){
                    if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
                        System.out.println("Liked");
                        postModel.setLiked(true);
                    }
                }

            });

            Integer likeCount = likeList.size();
            postModel.setLike(likeCount.longValue());


            BookMarkModel bookMarkModel = bookMarkRepository.find("type= ?1 and post_id=?2  and added_by =?3","post",postModel.getId(),request.getStudId()).firstResult();
            if(bookMarkModel!=null){
                postModel.setAdded(true);
            }else{
                postModel.setAdded(false);
            }
//            ArrayList<BookMarkModel> arrayList = (ArrayList<BookMarkModel>)
//                    bookMarkRepository.list("type=?1 and post_id=?2",
//                            "post",postModel.getId());
//            arrayList.forEach(bookMarkModel -> {
//
//                if(request.getStudId()!=0){
//                    if(bookMarkModel.getAdded_by()==request.getStudId()){
//                        postModel.setAdded(true);
//                    }else{
//                        postModel.setAdded(false);
//                    }
//                }
//
//            });

            postModel.setType("post");

        });

        return postModel;

    }

    QuizEntity getQuizzes(Index_Model model, GetIndexRequest request){
        System.out.println("Get Quiz");
//        Long val = Long.valueOf(model.getPost_id());
        QuizModel quizModel =  quizRepository.find("id =?1 and inst_id = ?2 and type = ?3",
                model.getPost_id(),request.getInst_id().intValue(),"Quiz").firstResult();


        SaveResultModel resultModel = resultRepository.find("quiz_id =?1 and inst_id =?2 and stud_id =?3",
                quizModel.getQuiz_id().longValue(),request.getInst_id(),request.getStudId()).firstResult();




        QuizEntity entity= new Gson().fromJson(new Gson().toJson(quizModel),QuizEntity.class);
        if(resultModel==null){
            entity.setAttempt(false);
        }else{
            entity.setAttempt(true);
        }



        entity.setType("quiz");

        return  entity;
    }

    VideoEntity getVideos(Index_Model model, GetIndexRequest request){
        System.out.println("Get Videos");
        Long val = Long.valueOf(model.getPost_id());

       VideoModel videoModel = videoLectureRepository.find("inst_id =?1 and id =?2",
               request.getInst_id().intValue(),val).firstResult();
       VideoEntity entity = new Gson().fromJson(new Gson().toJson(videoModel),VideoEntity.class);



       if(videoModel.getCourse_id()!=null){
           CourseModel courseModel = coursesRepository.find("id",
                   videoModel.getCourse_id().longValue()).firstResult();
              entity.setCourse(courseModel.getCourse());

              entity.setCourse_id(videoModel.getCourse_id().intValue());
       }

      StudentCourseModel scModel = studentCourseRepository.find("UserId=?1 and courseId=?2",Long.valueOf(request.getStudId()),videoModel.getCourse_id().intValue()).firstResult();
       if(scModel!=null){
           entity.setPurchased(true);
       }else{
           entity.setPurchased(false);
       }


       if(videoModel.getTopicId()!=null){
            TopicModel topicModel = topicsRepository.find("id",
                    videoModel.getTopicId().longValue()).firstResult();
            entity.setTopic(topicModel.getTopic());
        }
        if(videoModel.getSubject_id()!=null){

            SubjectModel subjectModel = subjectRepository.find("id",
                    videoModel.getSubject_id().intValue()).firstResult();
            entity.setSubject(subjectModel.getSubject());

        }




        entity.setType("video");

       if(model.getAdded_by().equals("employee")){


           TeacherModel teacherModel = teacherRepository.find("id",
                   videoModel.getCreated_by()).firstResult();
           entity.setTeacherName(teacherModel.getFname());
           if(teacherModel.getProfile()!=null){
               entity.setImage(teacherModel.getProfile());
           }
       }else if(model.getAdded_by().equals("admin")){
           AdminModel adminModel = adminRepository.find("id",
                   Long.valueOf(videoModel.getCreated_by()).intValue()).firstResult();
           entity.setTeacherName(adminModel.getName());
           if(adminModel.getProfile()!=null){
               entity.setImage(adminModel.getProfile());
           }
       }

        return  entity;
    }
    AudioEntity getAudio(Index_Model model, GetIndexRequest request){
        System.out.println("Get Audio");
        Long val = (long) model.getPost_id();

       Audio audioModel = audioNotesRepository.find("inst_id =?1 and id =?2",
               request.getInst_id().intValue(),val).firstResult();
       AudioEntity entity = new Gson().fromJson(new Gson().toJson(audioModel),AudioEntity.class);



       if(audioModel.getCourse_id()!=null){
           CourseModel courseModel = coursesRepository.find("id",
                   audioModel.getCourse_id().longValue()).firstResult();
              entity.setCourse(courseModel.getCourse());

              entity.setCourse_id(audioModel.getCourse_id());
       }

      StudentCourseModel scModel = studentCourseRepository.find("UserId=?1 and courseId=?2",Long.valueOf(request.getStudId()),Long.valueOf(audioModel.getCourse_id())).firstResult();
       if(scModel!=null){
           entity.setPurchased(true);
       }else{
           entity.setPurchased(false);
       }


       if(audioModel.getTopicId()!=null){
            TopicModel topicModel = topicsRepository.find("id",
                    audioModel.getTopicId()).firstResult();
            entity.setTopic(topicModel.getTopic());
        }
        if(audioModel.getSubject_id()!=null){

            SubjectModel subjectModel = subjectRepository.find("id",
                    audioModel.getSubject_id().intValue()).firstResult();
            entity.setSubject(subjectModel.getSubject());

        }




        entity.setType(Constants.indexType.audio.name());

       if(model.getAdded_by().equals("employee")){


           TeacherModel teacherModel = teacherRepository.find("id",
                   audioModel.getCreated_by()).firstResult();
           entity.setTeacherName(teacherModel.getFname());
           if(teacherModel.getProfile()!=null){
               entity.setImage(teacherModel.getProfile());
           }
       }else if(model.getAdded_by().equals("admin")){
           AdminModel adminModel = adminRepository.find("id",
                   Long.valueOf(audioModel.getCreated_by()).intValue()).firstResult();
           entity.setTeacherName(adminModel.getName());
           if(adminModel.getProfile()!=null){
               entity.setImage(adminModel.getProfile());
           }
       }

        return  entity;
    }


    QuizEntity getAllTest(Index_Model model, GetIndexRequest request) {
        System.out.println("Get Test Series");
        System.out.println(model.getPost_id());
        //  System.out.println(request.getInst_id());

        Long val = Long.valueOf(model.getPost_id());

        QuizModel quizModel = quizRepository.find("inst_id=?1 and quiz_id = ?2 and type = ?3 ", request.getInst_id().intValue(), val.intValue(), "Monthly Test").firstResult();
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


        SaveResultModel resultModel = resultRepository.find("quiz_id =?1 and inst_id =?2 and stud_id =?3",
                quizModel.getQuiz_id().longValue(),request.getInst_id(),request.getStudId()).firstResult();

        if (resultModel == null) {
            entity.setAttempt(false);
        }else{
            entity.setAttempt(true);
        }


        entity.setType("practiseTest");


        return entity;
    }




}
