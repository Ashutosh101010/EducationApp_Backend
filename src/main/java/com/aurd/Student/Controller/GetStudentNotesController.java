package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.StudentNotesEntity;
import com.aurd.Student.Model.Entity.StudentNotesModel;
import com.aurd.Student.Model.Request.GetStudentNotesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.StudentNotesResponse;
import com.aurd.Student.Repository.StudentNotesRepository;
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
import java.util.Calendar;
import java.util.Date;

@Path("/students/getStudentNotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetStudentNotesController {

    @Inject
    StudentNotesRepository repository;
    @POST
//    @Transactional

    public StudentNotesResponse getStudNotes(GetStudentNotesRequest request) throws ParseException {

        System.out.println(new Gson().toJson(request));

        ArrayList<StudentNotesEntity> arrayList =new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Query query =null;

//        if ( request.getLastId()==null || request.getLastId().equals("")) {
//
//            String string = "SELECT student_notes.id,student_notes.note,student_notes.added_on,student_notes.title," +
//                    "student_notes.vid_id FROM student_notes WHERE student_notes.stud_id=? " +
//                    "and student_notes.inst_id = ? ORDER BY added_on DESC ";
//
//
//             query = repository.getEntityManager().createNativeQuery(string);
//            query.setParameter(1,request.getStud_id());
//            query.setParameter(2,request.getInst_id());
//
//
//       }
//        else {
////            String lastId;
////            lastId = String.valueOf(Timestamp.valueOf(request.getLastId()));
//
//
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//            Date parsedDate = dateFormat.parse(request.getLastId());
//            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//
//
//            String string = "SELECT student_notes.id,student_notes.note,student_notes.added_on,student_notes.title," +
//                    "student_notes.vid_id FROM student_notes WHERE student_notes.stud_id=? " +
//                    "and student_notes.inst_id = ? and student_notes.added_on<? ORDER BY added_on DESC ";
//
//
//            query = repository.getEntityManager().createNativeQuery(string);
//            query.setParameter(1,request.getStud_id());
//            query.setParameter(2,request.getInst_id());
//            query.setParameter(3,timestamp);
//       }
        String string = "SELECT student_notes.id,student_notes.note,student_notes.added_on,student_notes.title," +
                "student_notes.vid_id FROM student_notes WHERE student_notes.stud_id=? " +
                "and student_notes.inst_id = ? ORDER BY added_on DESC ";


        query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getStud_id());
        query.setParameter(2,request.getInst_id());


        ArrayList<Object[]> objList = (ArrayList<Object[]>) query.getResultList();
        System.out.println(objList.size());
        objList.forEach(objects -> {

            StudentNotesEntity model = new StudentNotesEntity();
            model.setId(Long.parseLong(objects[0].toString()));
            model.setNote(objects[1].toString());
            model.setAdded_on(Timestamp.valueOf(objects[2].toString()));
            model.setTitle(objects[3].toString());

//            model.setVid_id(Long.parseLong(objects[4].toString()));
//            model.setName(objects[5].toString());
//            model.setThumb(objects[6].toString());
//            model.setVideo_type(objects[7].toString());
//            model.setVideo(objects[8].toString());
//            model.setTopic_id(Long.parseLong(objects[9].toString()));
//            model.setDescription(objects[10].toString());
//            model.setTopic(objects[11].toString());

            System.out.println(new Gson().toJson(model));
            arrayList.add(model);

        });

        System.out.println(arrayList.size());



       StudentNotesResponse response = new StudentNotesResponse();
       response.setNotes(arrayList);
       response.setMessage("Get student notes successfully");
       response.setStatus(true);
       response.seterrorCode(0);

        return  response;

    }

}
