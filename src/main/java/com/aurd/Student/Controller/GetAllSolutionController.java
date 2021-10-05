package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;
import com.aurd.Student.Model.BeanClass.SolutionEntity;
import com.aurd.Student.Model.Entity.Question_Option_Model;
import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.GetSolutionRequest;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.Get_QuestionID_Repository;
import com.aurd.Student.Repository.QuizQuestionRepository;
import com.aurd.Student.Repository.Quiz_Question_Option_Repository;
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
    Get_QuestionID_Repository questionID_repository;


    @Inject
    QuizQuestionRepository quizQuestionRepository;
    @Inject
    Quiz_Question_Option_Repository quiz_question_option_repository;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Result_Response getAllSolution(GetSolutionRequest request){
        ArrayList<SolutionEntity> solution = new ArrayList<>();

        ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =

                (ArrayList<Quiz_Question_Map_Model>) questionID_repository.
                        getQuestionID(request.getQuiz_id());


        ArrayList<Quiz_Submit_Model> arrayList = repository.getStudentPracticeTestResult(
               request.getInst_id(),request.getStud_id(),request.getQuiz_id());


        quizQuestionIDList.forEach(quiz_question_map_model -> {
            SolutionEntity entity = new SolutionEntity();
            Quiz_Question_Model question_model  = quizQuestionRepository.
                    getQuestions(quiz_question_map_model.getQues_id());
            ArrayList<Question_Option_Model> optionList =
                    quiz_question_option_repository.
                            getOptions(question_model.getQuestion_id());


            for(int i=0;i<arrayList.size();i++){


                System.out.println("QuestionID   from data ====="+quiz_question_map_model.getQues_id());
                System.out.println("Question id from student"+arrayList.get(i).getQues_id());

                if(quiz_question_map_model.getQues_id() == arrayList.get(i).getQues_id()){
                    entity.setQuestion(question_model.getQuestion());
                    entity.setAnswer(question_model.getAnswer());
                    System.out.println(arrayList.get(i).getAns());
                    entity.setMyAnswer(arrayList.get(i).getAns());
                    entity.setOptions(optionList);

                }else{
                    entity.setOptions(optionList);
                    entity.setQuestion(question_model.getQuestion());
                    entity.setAnswer(question_model.getAnswer());
                    entity.setMyAnswer("");
                }
            }



            solution.add(entity);

        });

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

