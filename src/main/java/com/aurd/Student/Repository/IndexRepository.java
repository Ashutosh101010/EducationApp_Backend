package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Index_Model;
import com.aurd.Student.Model.Request.GetIndexRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped

public class IndexRepository implements PanacheRepository<Index_Model> {


}
