package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.QuizSection;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Quiz_Section_Repository implements PanacheRepository<QuizSection> {
}
