package com.aurd.Student.Controller;


import com.aurd.Student.Constant.Constants;
import com.aurd.Student.Model.Request.CreateOrderRazorPayRequest;
import com.aurd.Student.Model.Response.CreateOrderRazorPayResponse;
import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/createRazorPayOrder")
public class CreateRazorPayOrderController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public CreateOrderRazorPayResponse createOrder(CreateOrderRazorPayRequest request) throws RazorpayException {

        CreateOrderRazorPayResponse response = new CreateOrderRazorPayResponse();
        RazorpayClient razorpayClient = new RazorpayClient(Constants.razorPay_Key_Id,Constants.razorPay_Key_Secret);
        try {

            String receiptId = request.getUserId()+System.currentTimeMillis();

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", request.getAmount()*100); // amount in the smallest currency unit
            orderRequest.put("currency", request.getCurrency());
            orderRequest.put("receipt", receiptId);

            Order order = razorpayClient.Orders.create(orderRequest);
            System.out.println(new Gson().toJson(order));

            response.setMessage("Create order success");
            response.setStatus(true);
            response.setErrorCode(0);
            response.setOrderId(order.get("id"));
            response.setReceiptId(receiptId);
        } catch (RazorpayException e) {
            // Handle Exception
            System.out.println(e.getMessage());
            response.setStatus(false);
            response.setMessage(e.getMessage());
            response.setErrorCode(1);

        }


        return  response;
    }
}
