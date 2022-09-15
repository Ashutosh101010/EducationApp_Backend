package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Book;
import com.aurd.Student.Model.Entity.PurchaseBook;
import com.aurd.Student.Model.Entity.PurchaseTestModel;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.BuyPhysicalBookRequest;
import com.aurd.Student.Model.Request.PurchaseTestSeriesRequest;
import com.aurd.Student.Model.Response.BuyPhysicalBookResponse;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
@ApplicationScoped

public class PurchaseTestRepository implements PanacheRepository<PurchaseTestModel> {


    @Inject
    QuizRepository quizRepository;
    public boolean Purchase(PurchaseTestSeriesRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        System.out.println(sdf.format(now.getTime()));

        request.setCreated_on(Timestamp.valueOf(sdf.format(now.getTime())));

        PurchaseTestModel  purchaseTestModel = new Gson().fromJson(new Gson().toJson(request), PurchaseTestModel.class);
        persist(purchaseTestModel);
        return true;
    }

    @Transactional
    public GeneralResponse buyTestSeries(PurchaseTestSeriesRequest request)
    {
//        Book book= repository.find("id",request.getBookId()).firstResult();

        QuizModel quizModel=quizRepository.find("quiz_id",request.getTest_seriesId()).firstResult();
        GeneralResponse response=new GeneralResponse();
        PurchaseTestModel  purchaseSeries=new PurchaseTestModel();
        purchaseSeries.setTest_seriesId(request.getTest_seriesId());

        purchaseSeries.setPurchase_amount(request.getPurchase_amount());
        purchaseSeries.setStud_id(request.getStud_id());
        purchaseSeries.setPhone(request.getPhone());
        purchaseSeries.setTotal_amount(Long.valueOf(quizModel.getPrice()));
        purchaseSeries.setDiscounted_amount(Long.valueOf(quizModel.getPrice()));
        purchaseSeries.setMethod(request.getMethod());
        purchaseSeries.setTrans_id(request.getTransactionId());
        purchaseSeries.setCreated_on(new Timestamp(System.currentTimeMillis()));
        purchaseSeries.setDetails("");
//        purchaseSeries.setDiscount(0L);


        persist(purchaseSeries);
        response.setMessage("Purchase Test Series");
        response.setStatus(true);
        response.seterrorCode(0);
        return response;
    }

}
