package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.City;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped

public class CityRepository  implements PanacheRepository<City> {


//   public List getCity(String state_id) {
//        List<CityModel> list = list("state_id", state_id);
//
//
//
//      return list;
//
//
//    }


}