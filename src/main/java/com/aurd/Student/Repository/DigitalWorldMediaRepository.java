package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.DigitalWorlMedia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DigitalWorldMediaRepository implements PanacheRepository<DigitalWorlMedia> {
}
