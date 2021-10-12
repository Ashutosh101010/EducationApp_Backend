package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.BookMarkModel;
import com.aurd.Student.Model.Request.AddBookmarkRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.BookMarkRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;

@Path("/students/addBookmark")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddBookmarkController {

    @Inject
    BookMarkRepository repository;
    @POST

    @Transactional
    public GeneralResponse addBookmark(AddBookmarkRequest request){

        System.out.println(new Gson().toJson(request));

        BookMarkModel bookMarkModel = new Gson().fromJson(new Gson().
                toJson(request),BookMarkModel.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();


        bookMarkModel.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));

      //  repository.persist(bookMarkModel);

        GeneralResponse response = new GeneralResponse();

        if (request.getOperation() == 1) {
           repository.persist(bookMarkModel);

         response.setStatusCode(0);
         response.setMessage("Add Book mark Success");
         response.setStatus(true);

        } else if (request.getOperation() == 2) {

        String string = "DELETE FROM student_posts_saved WHERE post_id=? and added_by=? and type=?";

        Query query = getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getPost_id());
        query.setParameter(2,request.getAdded_by());
        query.setParameter(3,request.getType());

        query.executeUpdate();

        response.setMessage("Delete book mark");
        response.setStatus(true);
        response.setStatusCode(0);
        } else {
            response.setMessage("Unable to proceed");
            response.setStatus(false);
            response.setStatusCode(1);
        }

        return  response;

    }

}
