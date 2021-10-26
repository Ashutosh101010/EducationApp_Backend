package com.aurd.Student.Controller;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.aurd.Student.Model.Entity.Index_Model;
import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Request.AddStudentPostRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.IndexRepository;
import com.aurd.Student.Repository.StudentPostRepository;
import com.google.gson.Gson;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import static  com.aurd.Service.s3;

@Path("/addStudentPost")
public class AddStudentPostController
{

    @Inject
    StudentPostRepository repository;

    @Inject
    IndexRepository indexRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public GeneralResponse  addStudentPost(@MultipartForm MultipartFormDataInput inputRequest) throws IOException {




        final String bucketName = "educationapp";

        GeneralResponse response = new GeneralResponse();
        try {
            StudentPostModel studentPostModel = new StudentPostModel();
            System.out.println(inputRequest.getFormDataMap());
            Map<String, List<InputPart>> input = inputRequest.getFormDataMap();
            java.util.Date date = new java.util.Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            AddStudentPostRequest request = new AddStudentPostRequest();
            request.setAdded_on(timestamp);
            request.setPost_approved_on(timestamp);
            request.setPost_approved_by(0);
            request.setInst_id(Integer.parseInt(input.get("inst_id").get(0).getBodyAsString()));
            request.setAdded_by(Integer.parseInt(input.get("added_by").get(0).getBodyAsString()));
            request.setPost_status(Integer.parseInt(input.get("post_status").get(0).getBodyAsString()));
//            request.setPic(input.get("pic").get(0).getBody(InputStream.class,null));
//

            if(input.get("pic")==null){
                request.setPic(null);
                studentPostModel.setPic(null);
            }else{
                request.setPic(input.get("pic").get(0).getBody(InputStream.class,null));
                System.out.println(request);
                byte imageBytes[] = request.getPic().readAllBytes();
                System.out.println(imageBytes);
                System.out.println(imageBytes.length);

                InputStream inputStream = new ByteArrayInputStream(imageBytes);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(imageBytes.length);

                String ImageId = String.valueOf(System.currentTimeMillis());
                s3.putObject(bucketName, ImageId, inputStream,metadata );

                System.out.println(ImageId);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                studentPostModel.setPic(ImageId);
            }

            if(input.get("description")!=null){
                request.setDiscription(input.get("description").get(0).getBodyAsString());
                studentPostModel.setDescription(request.getDiscription());
            }else{
                studentPostModel.setDescription(null);
            }






//            studentPostModel.setPost_approved_by(request.getPost_approved_by());
            studentPostModel.setPost_status(request.getPost_status());
            studentPostModel.setAdded_by(request.getAdded_by());
            studentPostModel.setInst_id(request.getInst_id());


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            studentPostModel.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
//            studentPostModel.setUpdated_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));

            System.out.println(new Gson().toJson(studentPostModel));

            repository.addStudentPost(studentPostModel);

            Index_Model index_model = new Index_Model();
            index_model.setPost_id((int) studentPostModel.getId());
            index_model.setInst_id((long) studentPostModel.getInst_id());
            index_model.setType("post");
            index_model.setCreated_on(studentPostModel.getAdded_on());
            indexRepository.persist(index_model);

            response.seterrorCode(0);
            response.setStatus(true);
            response.setMessage("Student Post Added Successfully");


        }catch (Exception e){

            response.seterrorCode(1);
            response.setStatus(false);
            response.setMessage("Failure");
        }




        return response;


    }


}
