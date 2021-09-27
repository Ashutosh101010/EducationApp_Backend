package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Request.GetCourseSyllabusRequest;
import com.aurd.Student.Model.Response.GetBlogResponse;
import com.aurd.Student.Repository.BlogRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

@Path("/courses/getSyllabus")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GetCourseSyllabus {

    @Inject
    BlogRepository repository;
    @Transactional
    @POST

    public GetBlogResponse getCourse(GetCourseSyllabusRequest request){


        String string = "SELECT blog.id, blog.title,blog.description,blog.added_by," +
                " blog.inst_id,blog.tags,blog.thumbnail,blog.created_on,course_blog.blog," +
                "course_blog.created_at FROM course_blog" +
                " INNER JOIN blog ON blog.id=course_blog.blog WHERE course_blog.course=?";

        Query query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getCourse_id());
        ArrayList<Object[]> arrayList = (ArrayList<Object[]>) query.getResultList();

        ArrayList<BlogModel> list = new ArrayList<>();
        arrayList.forEach(objects -> {
            BlogModel entity = new BlogModel();

            System.out.println(objects[6].toString());

            entity.setId(Long.parseLong(objects[0].toString()));
            entity.setTitle(objects[1].toString());
            entity.setDescription(objects[2].toString());
            entity.setAdded_by(Integer.parseInt(objects[3].toString()));
            entity.setInst_id(Integer.parseInt(objects[4].toString()));
            entity.setTags(objects[5].toString());
            entity.setThumbnail(objects[6].toString());
            entity.setCreated_on(Timestamp.valueOf(objects[7].toString()));

            list.add(entity);
        });

//
//          ArrayList<BlogModel> arrayList1 = repository.getCourse(request);

        GetBlogResponse getBlogResponse= new GetBlogResponse();
        getBlogResponse.setBlogs(list);
        getBlogResponse.setMessage("Get Syllabus Successfully");
        getBlogResponse.setStatus(0);
        getBlogResponse.setStatusCode(true);


        return getBlogResponse;

    }

}