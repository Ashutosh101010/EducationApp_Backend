package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Banners;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BannerImageRepository implements PanacheRepository<Banners> {
}
