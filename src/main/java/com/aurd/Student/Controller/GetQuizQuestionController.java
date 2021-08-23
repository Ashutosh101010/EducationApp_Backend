package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;
import com.aurd.Student.Model.Entity.Question_Option_Model;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Request.GetQuizQuestionRequest;
import com.aurd.Student.Model.Response.GetQuizQuestionResponse;
import com.aurd.Student.Repository.Get_QuestionID_Repository;
import com.aurd.Student.Repository.QuizQuestionRepository;
import com.aurd.Student.Repository.Quiz_Question_Option_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/getQuizQuestions")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GetQuizQuestionController {


    @Inject
    Get_QuestionID_Repository questionID_repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    Quiz_Question_Option_Repository quiz_question_option_repository;
    @POST

    @Transactional


    public GetQuizQuestionResponse getQuestions(GetQuizQuestionRequest request){

        QuizQuestionEntity model;
        Quiz_Question_Model quizQuestionModel;


        ArrayList<QuizQuestionEntity> arrayList = new ArrayList<>();
       ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =

               (ArrayList<Quiz_Question_Map_Model>) questionID_repository.getQuestionID(request.getQuiz_id());


       for(int i=0;i<quizQuestionIDList.size();i++){

           System.out.println(quizQuestionIDList.get(i).getQues_id());

           model = new QuizQuestionEntity();
           quizQuestionModel = quizQuestionRepository.getQuestions(quizQuestionIDList.get(i).getQues_id());
           model.setQues_id(quizQuestionIDList.get(i).getQues_id());
           model.setQuiz_id(quizQuestionIDList.get(i).getQuiz_id());
           model.setQuestion(quizQuestionModel.getQuestion());
           model.setQuestion_type(quizQuestionModel.getQuestion_type());
           model.setMarks(quizQuestionIDList.get(i).getMarks());
           model.setAnswer(quizQuestionModel.getAnswer());
           model.setAns_description(quizQuestionModel.getAns_description());
           model.setAdded_on(quizQuestionModel.getAdded_on());
           model.setPic(quizQuestionModel.getPic());

           ArrayList<Question_Option_Model> optionList =  quiz_question_option_repository.getOptions(quizQuestionIDList.get(i).getQues_id());
           model.setOptions(optionList);

           arrayList.add(model);

       }


        GetQuizQuestionResponse response = new GetQuizQuestionResponse();
       response.setErrorCode(0);
       response.setMessage("Get question success");
       response.setStatus(true);
       response.setArrayList(arrayList);
       return response;

    }



}
