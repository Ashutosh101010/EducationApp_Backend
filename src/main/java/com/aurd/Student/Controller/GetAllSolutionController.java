package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.SolutionEntity;
import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Request.GetSolutionRequest;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.QuizQuestionRepository;
import com.aurd.Student.Repository.Quiz_Submit_Repository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getSolution")
public class GetAllSolutionController{

    @Inject
    Quiz_Submit_Repository repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Result_Response getAllSolution(GetSolutionRequest request){
        ArrayList<SolutionEntity> solution = new ArrayList<>();


       ArrayList<Quiz_Submit_Model> arrayList = repository.getStudentPracticeTestResult(
               request.getInst_id(),request.getStud_id(),request.getQuiz_id());

       for(Quiz_Submit_Model model: arrayList){
           Quiz_Question_Model question_model  = quizQuestionRepository.getQuestions(model.getQues_id());
           SolutionEntity entity = new SolutionEntity();
           entity.setQuestion(question_model.getQuestion());
           entity.setAnswer(question_model.getAnswer());
           entity.setMyAnswer(model.getAns());

           solution.add(entity);

       }

        Result_Response response = new Result_Response();

        if(!solution.isEmpty()){

            response.setSolutions(solution);
            response.setMessage("Get All Solution Success");
            response.setErrorCode(0);
            response.setStatus(true);
       }else{
            response.setMessage("Get All Solution Failure");
            response.setErrorCode(1);
            response.setStatus(false);
        }

        return  response;
    }

}

