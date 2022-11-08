package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.KeyNotes;

import com.aurd.Student.Model.Request.AddKeyNotesRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;

@ApplicationScoped

public class KeyNotesRepository implements PanacheRepository<KeyNotes> {


    public void addKeyNote(AddKeyNotesRequest request) {
        KeyNotes keyNotes=new KeyNotes();
        keyNotes.setAddedOn(new Timestamp(System.currentTimeMillis()));
        keyNotes.setCategory(request.getCategory());
        keyNotes.setDescription(request.getDescription());
        keyNotes.setTitle(request.getTitle());
        keyNotes.setInstId(request.getInstId());
        keyNotes.setLiveClassId(request.getLiveClassId());
        persist(keyNotes);
    }
}
