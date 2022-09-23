package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Report;
import com.aurd.Student.Model.Request.ReportRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class ReportRepository implements PanacheRepository<Report> {


    @Transactional
    public void addReport(ReportRequest request){
        Report report=new Report();
        report.setQuesId(request.getQuesId());
        report.setQuizId(request.getQuizId());
        report.setFeedback(request.getFeedback());
        report.setQuizType(request.getQuizType());
        report.setTestName(request.getTestName());

        persist(report);
    }

}
