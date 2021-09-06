package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.ResultEntity;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.GetQuizResultRequest;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.Get_QuestionID_Repository;
import com.aurd.Student.Repository.QuizQuestionRepository;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.Quiz_Submit_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/quiz/getQuizResult")
public class GetQuizResultController {

    @Inject
    Quiz_Submit_Repository quiz_submit_repository;

    @Inject
    Get_QuestionID_Repository questionID_repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    QuizRepository quizRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public  Result_Response getResult(GetQuizResultRequest request){
        ResultEntity result = new ResultEntity();
        long totalMarks = 0;
        long marksObtained = 0;
        long correctAns =0;
        long wrongAns = 0;



//        ArrayList<QuizQuestionEntity> arrayList = new ArrayList<>();
        ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =
                (ArrayList<Quiz_Question_Map_Model>) questionID_repository.getQuestionID(request.getQuizID());

        System.out.println(quizQuestionIDList.size());
        Quiz_Question_Model quizQuestionModel;
        for(Quiz_Question_Map_Model model :quizQuestionIDList){
            totalMarks = totalMarks+model.getMarks();
        }


        result.setTotalMarks(totalMarks);

        ArrayList<Quiz_Submit_Model> arrayList = quiz_submit_repository.getStudentPracticeTestResult
                (request.getInstID().intValue(),request.getStudID(),request.getQuizID());
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
        result.setMarksObtained(marksObtained);

       QuizModel quizModel = quizRepository.find("quiz_id",request.getQuizID()).firstResult();
       result.setCutOff(quizModel.getCutoff());
        result.setCorrectAnswered(correctAns);
        result.setWrongAnswered(wrongAns);

        Result_Response response = new Result_Response();
        if(result!=null){
            response.setErrorCode(0);
            response.setStatus(true);
            response.setMessage("Get Quiz Result Successfully");
            response.setResultEntity(result);
        }else {
            response.setErrorCode(1);
            response.setStatus(false);
            response.setMessage("Get Quiz Result Fail");

        }
        return  response;



    }


}
