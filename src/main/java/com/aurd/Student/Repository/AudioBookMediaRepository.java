package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.AudioBookMedia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AudioBookMediaRepository implements PanacheRepository<AudioBookMedia> {
}