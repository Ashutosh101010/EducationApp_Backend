package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.AudioBook;
import com.aurd.Student.Model.Entity.Book;
import com.aurd.Student.Model.Entity.PurchaseAudioBook;
import com.aurd.Student.Model.Entity.PurchaseBook;
import com.aurd.Student.Model.Request.BuyPhysicalBookRequest;
import com.aurd.Student.Model.Request.PurchaseAudioBookRequest;
import com.aurd.Student.Model.Response.BuyPhysicalBookResponse;
import com.aurd.Student.Model.Response.PurchaseAudioBookResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@ApplicationScoped
public class PurchaseAudioBookRepository implements PanacheRepository<PurchaseAudioBook> {


    @Inject
    AudioBookRepository audioBookRepository;
    @Transactional
    public PurchaseAudioBookResponse purchaseAudioBook(PurchaseAudioBookRequest request)
    {
        AudioBook book= audioBookRepository.find("id",request.getAudioBookId()).firstResult();
        PurchaseAudioBookResponse response=new PurchaseAudioBookResponse();
        PurchaseAudioBook purchaseBook=new PurchaseAudioBook();
        purchaseBook.setAudiobooksid(request.getAudioBookId());
//        purchaseBook.setAddress(request.getAddress());
        purchaseBook.setPurchase_amount(request.getPurchase_amount());
        purchaseBook.setStud_id(request.getStudId());
        purchaseBook.setPhone(request.getPhone());
        purchaseBook.setTotal_amount(Long.valueOf(book.getPrice()));
        purchaseBook.setDiscounted_amount(Long.valueOf(book.getDiscount_price()));
        purchaseBook.setMethod(request.getMethod());
        purchaseBook.setTrans_id(request.getTransactionId());
        purchaseBook.setCreated_on(new Timestamp(System.currentTimeMillis()));


        persist(purchaseBook);
        response.setMessage("Purchase Audio Book");
        response.setStatus(true);
        response.setErrorCode(0);
        return response;
    }


}
