package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.StudentPost;
import com.aurd.Student.Model.Entity.StudentPostsCommented;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@ApplicationScoped
public class StudentPostCommentRepository implements PanacheRepository<StudentPostsCommented> {

//
//    public ArrayList<Student_Posts_Commented> getComment(GetCommentRequest request) {
//
//
//      //  Query query = getEntityManager().createQuery("select Student_Posts_Commented from Student_Posts_Commented post join" +
//      //          " StudentModel student on post.added_by=student.id where post.post_id=:postId");
//
//        Query query=getEntityManager().createQuery("select Student_Posts_Commented from Student_Posts_Commented post" +
//                " left outer join TeacherModel Teacher on post.added_by=Teacher.id" +
//                " left  outer join StudentModel Students on Students.id=post.added_by where post.post_id=:postId");
//
//        query.setParameter("postId", request.getPost_id());
//
//        return (ArrayList<Student_Posts_Commented>) query.getResultList();
//    }
//
//    public boolean addPostCommentRequest(AddPostCommentRequest request) {
//        Student_Posts_Commented studentPostsCommented = new Gson().fromJson(new Gson().toJson(request),
//                Student_Posts_Commented.class);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//
//        studentPostsCommented.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
//
//
//        studentPostsCommented.setType("student");
//        persist(studentPostsCommented);
//        return true;
//
//    }


}