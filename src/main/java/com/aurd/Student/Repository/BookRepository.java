package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {
}
