package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentCommentPostEntity;
import com.aurd.Student.Model.Request.StudentPostCommentRequest;
import com.aurd.Student.Model.Response.StudentPostCommentResponse;
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

import static org.hibernate.loader.Loader.SELECT;

@Path("/getStudentPostComment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GetStudentPostCommentController {

    @Inject
    StudentPostCommentRepository repository;
    @Transactional
    @POST


    public StudentPostCommentResponse getComment(StudentPostCommentRequest request) {
        ArrayList<StudentCommentPostEntity> arrayList = new ArrayList();



       String string ="   SELECT student_posts_commented.comment ,student_posts_commented.comment_id,\n" +
               "                student_posts_commented.post_id, student_posts_commented.added_on,students.fname \n" +
               "        FROM student_posts_commented INNER JOIN students ON students.id = student_posts_commented.added_by \n" +
               "        WHERE student_posts_commented.post_id =?";

        Query query = repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1,request.getPost_id());

        ArrayList<Object[]> list = (ArrayList<Object[]>) query.getResultList();
        list.forEach(objects -> {
            StudentCommentPostEntity studentCommentPostEntity = new StudentCommentPostEntity();
            studentCommentPostEntity.setComment(objects[0].toString());
            studentCommentPostEntity.setComment_id(Integer.parseInt(objects[1].toString()));
            studentCommentPostEntity.setPost_id(Integer.parseInt(objects[2].toString()));
            studentCommentPostEntity.setAdded_on(Timestamp.valueOf(objects[3].toString()));
            studentCommentPostEntity.setFname(objects[4].toString());

             arrayList.add(studentCommentPostEntity);

        });

        StudentPostCommentResponse getCommentResponse = new StudentPostCommentResponse();
        getCommentResponse.setComments(arrayList);
        getCommentResponse.setMessage("Get Comment Successfully");
        getCommentResponse.setStatus(true);
        getCommentResponse.setStatusCode(0);

        return getCommentResponse;
    }
}
