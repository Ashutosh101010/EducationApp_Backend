package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Index;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped

public class IndexRepository implements PanacheRepository<Index> {


}
