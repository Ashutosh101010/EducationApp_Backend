package com.aurd.Student.Controller;



import com.aurd.Student.Model.BeanClass.BookMarkEntity;
import com.aurd.Student.Model.Entity.BookMarkModel;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.GetBookMarkRequest;
import com.aurd.Student.Model.Response.GetBookMarkResponse;
import com.aurd.Student.Repository.BookMarkRepository;
import com.aurd.Student.Repository.StudentPostCommentRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;



@Path("/getBookMark")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetBookMarkController {


    @Inject
    BookMarkRepository repository;

    @Inject
    StudentPostCommentRepository commentRepository;
    @Transactional
    @POST


    public GetBookMarkResponse getBook(GetBookMarkRequest request) {

        String bookMark ="SELECT student_posts_saved.post_id, student_posts.discription, " +
                "student_posts.pic, student_posts.post_status, student_posts.added_by, student_posts.added_on," +
                " students.fname FROM `student_posts_saved` INNER JOIN student_posts ON" +
                " student_posts.id = student_posts_saved.post_id " +
                "INNER JOIN students ON students.id = student_posts.added_by " +
                "WHERE student_posts_saved.added_by = ?";

        Query query = repository.getEntityManager().createNativeQuery(bookMark);
        query.setParameter(1,request.getStudent_id());
        ArrayList<Object[]> arrayList = (ArrayList<Object[]>) query.getResultList();

        ArrayList<BookMarkEntity> list = new ArrayList<>();
        arrayList.forEach(objects -> {
            BookMarkEntity entity = new BookMarkEntity();
            entity.setId(Long.parseLong(objects[0].toString()));
            entity.setDiscription(objects[1].toString());
            entity.setPic(objects[2].toString());
            entity.setPost_status(Integer.parseInt(objects[3].toString()));
            entity.setAdded_by(Long.parseLong(objects[4].toString()));
            entity.setAdded_on(Timestamp.valueOf(objects[5].toString()));
            entity.setName(objects[6].toString());

            String commentQuery = "SELECT COUNT(*) FROM `student_posts_commented` WHERE post_id =? ";
            Query comment = commentRepository.getEntityManager().createNativeQuery(commentQuery);
            comment.setParameter(1,Long.parseLong(objects[0].toString()));
            Integer commentCount = ((Number) comment.getSingleResult()).intValue();
            entity.setCommentCount(commentCount.longValue());
            list.add(entity);
        });

        GetBookMarkResponse getBookResponse = new GetBookMarkResponse();
        getBookResponse.setBook(list);
        getBookResponse.setMessage("Get BookMark Successfully");
        getBookResponse.setStatus(true);
        getBookResponse.setStatusCode(0);

        return getBookResponse;
    }



}
