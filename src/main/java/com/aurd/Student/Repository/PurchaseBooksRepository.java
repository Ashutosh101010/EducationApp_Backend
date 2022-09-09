package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Book;
import com.aurd.Student.Model.Entity.PurchaseBook;
import com.aurd.Student.Model.Request.BuyPhysicalBookRequest;
import com.aurd.Student.Model.Response.BuyPhysicalBookResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
public class PurchaseBooksRepository implements PanacheRepository<PurchaseBook> {

    @Inject
    BookRepository repository;


    @Transactional
    public BuyPhysicalBookResponse buyPhysicalBook(BuyPhysicalBookRequest request)
    {
       Book book= repository.find("id",request.getBookId()).firstResult();
        BuyPhysicalBookResponse response=new BuyPhysicalBookResponse();
        PurchaseBook purchaseBook=new PurchaseBook();
        purchaseBook.setBooksid(request.getBookId());
        purchaseBook.setAddress(request.getAddress());
        purchaseBook.setPurchase_amount(request.getPurchase_amount());
        purchaseBook.setStud_id(request.getStudId());
        purchaseBook.setPhone(request.getPhone());
        purchaseBook.setTotal_amount(Long.valueOf(book.getPrice()));
        purchaseBook.setDiscounted_amount(Long.valueOf(book.getDiscount_price()));
        purchaseBook.setMethod(request.getMethod());
        purchaseBook.setTrans_id(request.getTransactionId());


        persist(purchaseBook);
        response.setMessage("Purchase Book");
        response.setStatus(true);
        response.setErrorCode(0);
        return response;
    }

}
