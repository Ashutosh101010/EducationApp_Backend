package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.KeyNotes;

import com.aurd.Student.Model.Request.AddKeyNotes;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@ApplicationScoped

public class AddKeyNotesRepository implements PanacheRepository<KeyNotes> {

//


    public boolean addKeyNotesRequest(AddKeyNotes request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        System.out.println(sdf.format(now.getTime()));



        request.setAdded_on(Timestamp.valueOf(sdf.format(now.getTime())));

        KeyNotesModel keyNotesModel = new Gson().fromJson(new Gson().toJson(request),KeyNotesModel.class);
        persist(keyNotesModel);
        return true;
    }
}
