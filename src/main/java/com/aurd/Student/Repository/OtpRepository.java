package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Otp;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OtpRepository implements PanacheRepository<Otp> {
}
