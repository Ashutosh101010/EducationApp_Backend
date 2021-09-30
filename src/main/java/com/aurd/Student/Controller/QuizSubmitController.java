package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.ResultEntity;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Entity.SaveResultModel;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/submitQuiz")
public class QuizSubmitController {


    @Inject
    Quiz_Submit_Repository repository;



    @Inject
    Get_QuestionID_Repository questionID_repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    QuizRepository quizRepository;

    @Inject
    ResultRepository resultRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public Result_Response submitQuiz(QuizSubmitRequest request){
        System.out.println(request);
        Result_Response response = new Result_Response();

       boolean value = repository.submitStudentQuizResponses(request);
        if(value==true){

            long totalMarks = 0;
            long marksObtained = 0;
            long correctAns =0;
            long wrongAns = 0;


            ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =
                    (ArrayList<Quiz_Question_Map_Model>) questionID_repository.
                            getQuestionID(request.getArrayList().get(0).getQuiz_id());

            System.out.println(quizQuestionIDList.size());
            Quiz_Question_Model quizQuestionModel;
            for(Quiz_Question_Map_Model model :quizQuestionIDList){
                System.out.println("Total Marks"+ model.getMarks());

                totalMarks = totalMarks+model.getMarks();
            }




            ArrayList<Quiz_Submit_Model> arrayList = repository.getStudentPracticeTestResult
                    (request.getArrayList().get(0).getInst_id(),
                            request.getArrayList().get(0).getStud_id(),
                            request.getArrayList().get(0).getQuiz_id());
            for(Quiz_Submit_Model quizSubmitModel :arrayList){
                marksObtained = marksObtained + quizSubmitModel.getMarks_ob();

                quizQuestionModel = quizQuestionRepository.getQuestions(quizSubmitModel.getQues_id());
                if(quizQuestionModel.getQuestion_id()==quizSubmitModel.getQues_id()){
                    if(quizQuestionModel.getAnswer().equals(quizSubmitModel.getAns())){
                        correctAns = correctAns+1;
                    }else{
                        wrongAns = wrongAns+1;
                    }
                }
            }

            QuizModel quizModel = quizRepository.find("quiz_id",
                    request.getArrayList().get(0).getQuiz_id()).firstResult();



            SaveResultModel resultModel = new SaveResultModel();
            resultModel.setStud_id(request.getArrayList().get(0).getStud_id());
            resultModel.setInst_id(request.getArrayList().get(0).getInst_id());
            resultModel.setQuiz_id(request.getArrayList().get(0).getQuiz_id());



            if(quizModel.getNegative_marking()!=null && Integer.parseInt(quizModel.getNegative_marking())!=0){
                System.out.println(quizModel.getNegative_marking());

                String[] frac = quizModel.getNegative_marking().split("/");
              int  num = Integer.parseInt(frac[0]);
               int den = Integer.parseInt(frac[1]);

                marksObtained = marksObtained *(num%den)*wrongAns;
                long v = marksObtained *(num/den) *wrongAns;
                System.out.println("========"+marksObtained);
                System.out.println("*********"+v);
            }




            resultModel.setMarks_obtained(marksObtained);



            resultModel.setTotal_marks(totalMarks);
            resultModel.setWrong_ans(wrongAns);
            resultModel.setCorrect_ans(correctAns);

            if(quizModel.getCutoff()!=null){
                resultModel.setCut_off(quizModel.getCutoff());

                if(marksObtained>quizModel.getCutoff()){
                    resultModel.setStatus("pass");
                }else{
                    resultModel.setStatus("fail");
                }
            }else{
                resultModel.setStatus("pass");
                resultModel.setCut_off(0);
            }







            resultRepository.persist(resultModel);


            response.setErrorCode(0);
            response.setStatus(true);
            response.setMessage("Your Quiz is submitted..");
            response.setResult(resultModel);
        }else{
            response.setErrorCode(1);
            response.setStatus(false);
            response.setMessage("Quiz Submission Failure");

        }


        return  response;


    }
}
