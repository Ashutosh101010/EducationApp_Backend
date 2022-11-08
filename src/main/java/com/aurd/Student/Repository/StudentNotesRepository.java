package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.StudentNotes;
import com.aurd.Student.Model.Request.AddNotesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.sql.Timestamp;
@ApplicationScoped
public class StudentNotesRepository implements PanacheRepository<StudentNotes> {

    @Transactional
    public void addStudentNotes(AddNotesRequest request){

            StudentNotes studentNotes = new StudentNotes();
            studentNotes.setNote(request.getNote());
            studentNotes.setAddedOn(new Timestamp(System.currentTimeMillis()));
            studentNotes.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
            studentNotes.setInstId(request.getInstId());
            studentNotes.setStudId(request.getStudId());
            studentNotes.setVidId(request.getVidId());
            persist(studentNotes);
    }


}
