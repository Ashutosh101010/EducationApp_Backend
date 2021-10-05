package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.*;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetIndexRequest;
import com.aurd.Student.Model.Response.GetIndexResponse;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

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

    @Inject
    SubSubject_Repository subSubject_repository;

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

            System.out.println(request.getFilter());
            System.out.println("Some filter entry");

//            if(request.getFilter().equals("blog")){
//                String string="SELECT * FROM `index_data` WHERE inst_id=? and " +
//                        " type = ? ORDER By created_on DESC";
//                Query query = indexRepository.getEntityManager().createNativeQuery(string);
//
//                query.setParameter(1,request.getInst_id());
//
//                list = (ArrayList<Object[]>) query.getResultList();
//            }else if(request.getFilter().equals("currentAffair")){
//                String string="SELECT * FROM `index_data` WHERE inst_id=? and  type = ? ORDER By created_on DESC";
//                Query query = indexRepository.getEntityManager().createNativeQuery(string);
//                query.setParameter(1,request.getInst_id());
//                query.setParameter(2,"current_affair");
//
//
//                list = (ArrayList<Object[]>) query.getResultList();
//            }else if(request.getFilter().equals("notes")){
//                String string="SELECT * FROM `index_data` WHERE inst_id=? and  type = ? " +
//                        "ORDER By created_on DESC";
//                Query query = indexRepository.getEntityManager().createNativeQuery(string);
//                query.setParameter(1,request.getInst_id());
//                query.setParameter(2,"notes");
//
//
//                list = (ArrayList<Object[]>) query.getResultList();
//            }
//            else if(request.getFilter().equals("post")){
//                String string="SELECT * FROM `index_data` WHERE inst_id=? and  type = ? " +
//                        "ORDER By created_on DESC";
//                Query query = indexRepository.getEntityManager().createNativeQuery(string);
//                query.setParameter(1,request.getInst_id());
//                query.setParameter(2,"post");
//
//
//                list = (ArrayList<Object[]>) query.getResultList();
//            }
//            else if(request.getFilter().equals("video")){
//                String string="SELECT * FROM `index_data` WHERE inst_id=? and  type = ? " +
//                        "ORDER By created_on DESC";
//                Query query = indexRepository.getEntityManager().createNativeQuery(string);
//                query.setParameter(1,request.getInst_id());
//                query.setParameter(2,"video");
//
//
//                list = (ArrayList<Object[]>) query.getResultList();
//            } else if(request.getFilter().equals("quiz")){
//                String string="SELECT * FROM `index_data` WHERE inst_id=? and  type = ? " +
//                        "ORDER By created_on DESC";
//                Query query = indexRepository.getEntityManager().createNativeQuery(string);
//                query.setParameter(1,request.getInst_id());
//                query.setParameter(2,"quiz");
//
//
//                list = (ArrayList<Object[]>) query.getResultList();
//            }
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(request.getFilter()!=null && !request.getFilter().isEmpty())
            {
                Query query=null;
                if(request.getLastId()!=-1)
                {
                     query =  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId and " +
                            "Index_Model.type=:type and Index_Model.id < : lastId and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ");
                    query.setParameter("instId",request.getInst_id());
                    query.setParameter("type",request.getFilter());
                    query.setParameter("lastId",request.getLastId());



//                    arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());

                }
                else{
                     query=  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId and " +
                            "Index_Model.type=:type and Index_Model.created_on between :startDate and :endDate order by Index_Model.created_on desc ")
                            .setParameter("instId",request.getInst_id())
                            .setParameter("type",request.getFilter());
//                    query.setParameter("endDate",request.getDate()+" 23:59:59");
//                    if(request.getDate()!=null && !request.getDate().isEmpty())
//                    {
//                        query.setParameter("startDate",request.getDate()+" 00:00:00");
//
//
//                    }
//                    else{
//                        query.setParameter("startDate","2000-01-01"+" 00:00:00");
//                    }


                }

                if(request.getDate()!=null && !request.getDate().isEmpty())
                {
                    Date startDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime()-86400000);
                    System.out.println(startDate);
                    query.setParameter("startDate",startDate);
//                    query.setParameter("startDate",formatter.parse(request.getDate()+" 00:00:00"));
                    Date endDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime()+86400000);
                    System.out.println(endDate);
                    query.setParameter("endDate",endDate);

                }
                else{
                    query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
                    query.setParameter("endDate",new Date(System.currentTimeMillis()));
                }

                arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());
            }
            else{
            Query query=null;
                if(request.getLastId()!=-1)
                {
                     query =  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId " +
                            " and Index_Model.id < : lastId and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ");
                    query.setParameter("instId",request.getInst_id());
                    query.setParameter("lastId",request.getLastId());
//                    query.setParameter("endDate",formatter.parse(request.getDate()+" 23:59:59"));

//                    if(request.getDate()!=null && !request.getDate().isEmpty())
//                    {
//                        query.setParameter("startDate",formatter.parse(request.getDate()+" 00:00:00"));
//
//
//                    }
//                    else{
//                        query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
//                    }
//
//                    arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());

                }
                else{
                     query=  indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model where Index_Model.inst_id = : instId " +
                            " and Index_Model.created_on between :startDate and : endDate order by Index_Model.created_on desc ")
                            .setParameter("instId",request.getInst_id());

//                    if(request.getDate()!=null && !request.getDate().isEmpty())
//                    {
//                        query.setParameter("endDate",formatter.parse(request.getDate()+" 23:59:59"));
//
//
//                    }
//                    else{
//                        query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
//                    }
//
//                    arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());
                }
            if(request.getDate()!=null && !request.getDate().isEmpty())
            {
                Date startDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime()-86400000);
                System.out.println(startDate);
                query.setParameter("startDate",startDate);

                Date endDate=new Date(formatter.parse(request.getDate()+" 00:00:00").getTime()+86400000);
                System.out.println(endDate);
                query.setParameter("endDate",endDate);

            }
            else{
                query.setParameter("startDate",formatter.parse("2000-01-01"+" 00:00:00"));
                query.setParameter("endDate",new Date(System.currentTimeMillis()));
            }

            arrayList=new ArrayList<>(query.setMaxResults(10).getResultList());
            }






//        else if(!request.getDate().isEmpty()){
//            System.out.println(request.getDate());
//            System.out.println("Some date entry");
//            String start = request.getDate()+" 00:00:00";
//            String end = request.getDate()+" 23:59:59";
//            String string="SELECT * FROM `index_data` WHERE inst_id=? and created_on BETWEEN ? AND ? " +
//                    "ORDER By created_on DESC";
//            Query query = indexRepository.getEntityManager().createNativeQuery(string);
//            query.setParameter(1,request.getInst_id());
//            query.setParameter(2,start);
//            query.setParameter(3,end);
//
//            list = (ArrayList<Object[]>) query.getResultList();
//        }else{
//            System.out.println("empty data filter");
//
////            String string="SELECT * FROM `index_data` WHERE inst_id=?  ORDER By created_on DESC";
////            Query query = indexRepository.getEntityManager().createNativeQuery(string);
////            query.setParameter(1,request.getInst_id());
//
//            if(request.getLastId()!=-1)
//            {
//                arrayList=  new ArrayList<>(indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model  where Index_Model.inst_id = :insId  and Index_Model.id< : lastId order by Index_Model.created_on DESC  ")
//                        .setParameter("insId",Long.valueOf(request.getInst_id()))
//                        .setParameter("lastId",request.getLastId())
//                        .setMaxResults(10).getResultList());
//            }
//     else{
//                arrayList=  new ArrayList<>(indexRepository.getEntityManager().createQuery("select Index_Model from Index_Model Index_Model  where Index_Model.inst_id = :insId  order by Index_Model.created_on DESC  ")
//                        .setParameter("insId",Long.valueOf(request.getInst_id()))
//                        .setMaxResults(10).getResultList());
//            }
//
//

//            list = (ArrayList<Object[]>) query.getResultList();
//        }





//         list.forEach(objects -> {
//                     Index_Model model = new Index_Model();
//                     model.setId(Integer.parseInt(objects[0].toString()));
//                     model.setPost_id(Integer.parseInt(objects[1].toString()));
//                     model.setCreated_on(Timestamp.valueOf(objects[2].toString()));
//                     model.setType(objects[3].toString());
//                     arrayList.add(model);
//
//         });

         System.out.println(arrayList.size());

         arrayList.forEach(model -> {

             if(model.getType().equals("blog")){
             //    Long value = Long.valueOf(model.getPost_id());

                 BlogEntity blogEntity = getBlogs(model,request);
                 blogEntity.setIndexId(model.getId());
                 vList.add(blogEntity);


             } else if(model.getType().equals("current_affair")){
                 CurrentAffairEntity entity = getCurrentAffair(model,request);
                 entity.setIndexId(model.getId());
                 vList.add(entity);


             }else if(model.getType().equals("notes")){
                 NotesEntity notesEntity = getNotes(model,request);
                 notesEntity.setIndexId(model.getId());
                 vList.add(notesEntity);


             }else if(model.getType().equals("video")){
                 VideoEntity videoEntity = getVideos(model,request);
                 videoEntity.setIndexId(model.getId());
                 vList.add(videoEntity);

             }else if(model.getType().equals("post")){
                 StudentPostEntity entity= getPost(model,request);
                 entity.setIndexId(model.getId());
                 vList.add(entity);
             }else if(model.getType().equals("quiz")){
                 QuizEntity quizEntity = getQuizzes(model,request);
                 quizEntity.setIndexId(model.getId());
                 vList.add(quizEntity);
             }
             else if(model.getType().equals("practiceTest"))
             {
                 QuizEntity quizEntity =getAllTestSeries(model,request);
                 quizEntity.setIndexId(model.getId());
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



//        ArrayList <Object[]>blogList = (ArrayList<Object[]>) blog.getResultList();

//        System.out.println(blogList.size());

//        blogList.forEach(objects1 -> {
//            BlogEntity blogModel = new BlogEntity();
//            blogModel.setId(Long.parseLong(objects1[0].toString()));
//            blogModel.setTitle(objects1[1].toString());
//            blogModel.setDescription(objects1[2].toString());
//            blogModel.setAdded_by(Integer.parseInt(objects1[3].toString()));
//            blogModel.setCreated_on(Timestamp.valueOf(objects1[4].toString()));
//            blogModel.setInst_id(Long.parseLong(objects1[6].toString()));
//            blogModel.setTags(objects1[7].toString());
//            blogModel.setThumbnail(objects1[8].toString());
//            blogModel.setType("blog");
//
//            String likeQuery = "SELECT * FROM `blog_liked` WHERE blog_id =? ";
//            Query like = blogLikedRepository.getEntityManager().createNativeQuery(likeQuery);
//            like.setParameter(1, blogModel.getId());
//            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
//            likeList.forEach(likeObject -> {
//                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
//                    System.out.println("Liked");
//                    blogModel.setLiked(true);
//                }
//            });
//
//            Integer likeCount = likeList.size();
//            blogModel.setLike(likeCount.longValue());
//
//
//            TeacherModel teacherModel = teacherRepository.find("id",
//                    blogModel.getAdded_by().longValue()).firstResult();
//            blogModel.setName(teacherModel.getFname());
//
////            return blogModel;
//        });

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


//        String caQuery = "SELECT * from `current_affairs` where  inst_id = ?  and id=?";
//        Query currentAffair = caRepository.getEntityManager().createNativeQuery(caQuery);
//        currentAffair.setParameter(1, request.getInst_id());
//        currentAffair.setParameter(2,model.getPost_id());

//        ArrayList<Object[]> caList =(ArrayList<Object[]>) currentAffair.getResultList();
//
//        caList.forEach(objects1 -> {
//            CurrentAffairEntity caModal = new CurrentAffairEntity();
//            caModal.setId(Long.parseLong(objects1[0].toString()));
//            caModal.setTitle(objects1[1].toString());
//            caModal.setDescription(objects1[2].toString());
//            caModal.setCreated_at(Timestamp.valueOf(objects1[3].toString()));
//            caModal.setAdded_by(Integer.parseInt(objects1[5].toString()));
//            caModal.setInst_id(Integer.parseInt(objects1[6].toString()));
//            caModal.setThumbnail(objects1[7].toString());
//            caModal.setType("currentAffair");
//
//            String likeQuery = "SELECT * FROM `current_affairs_liked` WHERE current_affair_id =? ";
//            Query like = currentAffairLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
//            like.setParameter(1, caModal.getId());
//            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
//            likeList.forEach(likeObject -> {
//                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
//                    System.out.println("Liked");
//                    caModal.setLiked(true);
//                }
//            });
//
//            Integer likeCount = likeList.size();
//            caModal.setLike(likeCount.longValue());
//
//
//        });

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



////        String notesQuery = "SELECT * from `notes` where inst_id = ? and id=?";
////        Query notes = notesRepository.getEntityManager().createNativeQuery(notesQuery);
////        notes.setParameter(1,request.getInst_id());
////        notes.setParameter(2,model.getPost_id());
//
//        ArrayList<Object[]> notesList = (ArrayList<Object[]>) notes.getResultList();
//
//        notesList.forEach(objects -> {
//            NotesEntity notesEntity =new NotesEntity();
//            notesEntity.setId(Long.parseLong(objects[0].toString()));
//            notesEntity.setName(objects[1].toString());
//            notesEntity.setFile(objects[2].toString());
//            notesEntity.setTopicId(Integer.parseInt(objects[3].toString()));
//            notesEntity.setCreated_at(Timestamp.valueOf(objects[4].toString()));
//            notesEntity.setInst_id(Integer.parseInt(objects[6].toString()));
//            notesEntity.setCreated_by(Integer.parseInt(objects[7].toString()));
//            notesEntity.setTopic(objects[8].toString());
//            notesEntity.setComment(Long.parseLong(objects[9].toString()));
//            notesEntity.setFee_type(objects[12].toString());
//
//            notesEntity.setType("notes");
//
//
//            String likeQuery = "SELECT * FROM `notes_liked` WHERE notes_id =? ";
//            Query like = notesLikeDislikeRepository.getEntityManager().createNativeQuery(likeQuery);
//            like.setParameter(1, notesEntity.getId());
//            ArrayList<Object[]> likeList = (ArrayList<Object[]>) like.getResultList();
//            likeList.forEach(likeObject -> {
//                if (request.getStudId() == Long.parseLong(likeObject[1].toString())) {
//                    System.out.println("Liked");
//                    notesEntity.setLiked(true);
//                }
//            });
//
//            Integer likeCount = likeList.size();
//            notesEntity.setLike(likeCount.longValue());
//            vList.add(notesEntity);
//
//
//        });

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


        // QuizModel quizModel = quizRepository.find("id =?1 and inst_id =?2 and type = ?3",val,request.getInst_id(),"Monthly Test").firstResult();
//        QuizEntity entity = new Gson().fromJson(new Gson().toJson(quizModel),QuizEntity.class);
//        quizModel.setType("practise_test");

        //            System.out.println(new Gson().toJson(quizModel));
//
//        String string = "SELECT * from quiz_master where inst_id=? and  type=?";
//        Query query = quizRepository.getEntityManager().createNativeQuery(string, QuizModel.class);
//        query.setParameter(1, request.getInst_id());
//        query.setParameter(2, "Monthly Test");
//       QuizModel quizModel = (QuizModel) query.getSingleResult();
//
//
//       ArrayList<Object[]> practiseList = (ArrayList<Object[]>) query.getResultList();
//
//         practiseList.forEach(objects -> {
//           quizModel.setQuiz_id(Long.parseLong(objects[0].toString()));
//           quizModel.setSubject_id(Integer.parseInt(objects[1].toString()));
//           quizModel.setCourse_id(Integer.parseInt(objects[3].toString()));
//           quizModel.setTitle(objects[5].toString());
//           quizModel.setDiscription(objects[6].toString());
//           quizModel.setPic(objects[8].toString());
//           quizModel.setTest_start(Timestamp.valueOf(objects[10].toString()));
//           quizModel.setTest_duration(objects[11].toString());
//           quizModel.setAdded_on(Timestamp.valueOf(objects[12].toString()));
//
//
//         });




//        QuizEntity entity;


//        practiseList.forEach(objects -> {
//            QuizEntity entity= new QuizEntity();
//
//            entity.setQuiz_id(Long.parseLong(objects[0].toString()));
//            entity.setSubject_id(Integer.parseInt(objects[1].toString()));
//            entity.setCourse_id(Integer.parseInt(objects[2].toString()));
//            entity.setInst_id(Integer.parseInt(objects[3].toString()));
//            entity.setDiscription(objects[4].toString());
//            entity.setTitle(objects[5].toString());
//            entity.setCutoff(Long.parseLong(objects[6].toString()));
//            entity.setAdded_by(Long.parseLong(objects[7].toString()));
//
//
//         return entity;
//
//        });
        return entity;
    }


}
