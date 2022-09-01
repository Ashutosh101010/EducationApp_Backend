package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.BooksMedia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookMediaRepository implements PanacheRepository<BooksMedia> {
}
