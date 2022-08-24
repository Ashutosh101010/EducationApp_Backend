package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.AudioBook;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AudioBookRepository implements PanacheRepository<AudioBook> {


}
