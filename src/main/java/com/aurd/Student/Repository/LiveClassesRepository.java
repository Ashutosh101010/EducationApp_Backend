package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.LiveClassModel;
import com.aurd.Student.Model.Request.UpDateRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class LiveClassesRepository implements PanacheRepository<LiveClassModel> {


}
