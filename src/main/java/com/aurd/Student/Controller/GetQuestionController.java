package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;
import com.aurd.Student.Model.Entity.Question_Option_Model;
import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.GetQuestionRequest;
import com.aurd.Student.Model.Response.GetQuestionResponse;
import com.aurd.Student.Repository.Get_QuestionID_Repository;
import com.aurd.Student.Repository.QuizQuestionRepository;
import com.aurd.Student.Repository.Quiz_Question_Option_Repository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getQuestions")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GetQuestionController {


    @Inject
    Get_QuestionID_Repository questionID_repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    Quiz_Question_Option_Repository quiz_question_option_repository;

    @POST

    @Transactional


    public GetQuestionResponse getQuestions(GetQuestionRequest request) {

        String string = "SELECT quiz_ques_map.quiz_id,quiz_ques_map.ques_id,quiz_ques_map.marks, quiz_question_master.question," +
                "quiz_question_master.answer,quiz_question_master.ans_description," +
                "quiz_question_master.pic, quiz_question_master.question_type," +
                "quiz_question_master.subject_id,question_option_master.option_id," +
                "question_option_master.option_discription FROM quiz_ques_map " +
                "INNER JOIN quiz_question_master ON quiz_question_master.question_id = quiz_ques_map.ques_id " +
                "INNER JOIN question_option_master ON question_option_master.question_id = quiz_question_master.question_id WHERE quiz_ques_map.quiz_id=?";

        GetQuestionResponse response = new GetQuestionResponse();


        Query query = questionID_repository.getEntityManager().createNativeQuery(string);
        query.setParameter(1, request.getQuiz_id());

        ArrayList<Object[]> arrayList = (ArrayList<Object[]>) query.getResultList();
        ArrayList<QuizQuestionEntity> questionList = new ArrayList<>();


        arrayList.forEach(objects -> {
            QuizQuestionEntity questionEntity = getQuestionWithOption(questionList, Long.parseLong(objects[1].toString()));

            System.out.println(Long.parseLong(objects[1].toString()));
            System.out.println(objects[3].toString());

            if (questionEntity.getQues_id() == Long.parseLong(objects[1].toString())) {

           // if(questionEntity==null){

                questionEntity = new QuizQuestionEntity();
                questionEntity.setQues_id(Long.parseLong(objects[1].toString()));
                questionEntity.setMarks(Long.parseLong(objects[2].toString()));
                questionEntity.setQuestion(objects[3].toString());
                questionEntity.setAnswer(objects[4].toString());
                questionEntity.setAns_description(objects[5].toString());
                questionEntity.setQuestion_type(objects[7].toString());


                ArrayList<Question_Option_Model> optionList = new ArrayList<>();
                           Question_Option_Model model = new Question_Option_Model();
                           model.setOption_id(Long.parseLong(objects[9].toString()));
                          model.setOption_discription(objects[10].toString());
                           optionList.add(model);
                           questionEntity.setOptions(optionList);

                   questionList.add(questionEntity);





                /////////////////////////

                // arrayList.forEach(objects -> {

                //     QuizQuestionEntity questionEntity = getQuestionWithOption(questionList,Long.parseLong(objects[1].toString()));

                //    if(questionEntity==null){
                //         questionEntity = new QuizQuestionEntity();
                //          questionEntity.setQues_id(Long.parseLong(objects[1].toString()));
                //          questionEntity.setMarks(Long.parseLong(objects[2].toString()));
                //          questionEntity.setQuestion(objects[3].toString());
                //          questionEntity.setAnswer(objects[4].toString());
                //          questionEntity.setAns_description(objects[5].toString());
                //          questionEntity.setQuestion_type(objects[7].toString());


                //           ArrayList<Question_Option_Model> optionList = new ArrayList<>();
                //           Question_Option_Model model = new Question_Option_Model();
                //           model.setOption_id(Long.parseLong(objects[9].toString()));
                //           model.setOption_discription(objects[10].toString());
                //           optionList.add(model);
                //           questionEntity.setOptions(optionList);

                //    questionList.add(questionEntity);
                //         }
                //     });


///////////////////////////////////

                //     QuizQuestionEntity model;
                //   Quiz_Question_Model quizQuestionModel;


                //   ArrayList<QuizQuestionEntity> arrayList = new ArrayList<>();
                //  ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =

                //         (ArrayList<Quiz_Question_Map_Model>) questionID_repository.getQuestionID(request.getQuiz_id());

                // System.out.println(quizQuestionIDList.size());


                // for(int i=0;i<quizQuestionIDList.size();i++){

                //    System.out.println(quizQuestionIDList.get(i).getQues_id());

                //    model = new QuizQuestionEntity();
                //    quizQuestionModel = quizQuestionRepository.getQuestions(quizQuestionIDList.get(i).getQues_id());
                //    System.out.println(quizQuestionModel.getQuestion_id());
                //    System.out.println(quizQuestionModel.getQuestion());

                //    model.setQues_id(quizQuestionIDList.get(i).getQues_id());
                //   model.setQuiz_id(quizQuestionIDList.get(i).getQuiz_id());
                //   model.setQuestion(quizQuestionModel.getQuestion());
                //   model.setQuestion_type(quizQuestionModel.getQuestion_type());
                //   model.setMarks(quizQuestionIDList.get(i).getMarks());
                //   model.setAnswer(quizQuestionModel.getAnswer());
                //   model.setAns_description(quizQuestionModel.getAns_description());
                //   model.setAdded_on(quizQuestionModel.getAdded_on());
                //   model.setPic(quizQuestionModel.getPic());

                //   ArrayList<Question_Option_Model> optionList =  quiz_question_option_repository.getOptions(quizQuestionIDList.get(i).getQues_id());
                //   model.setOptions(optionList);

                //  arrayList.add(model);

                //   }


                response.setErrorCode(0);
                response.setMessage("Get question success");
                response.setStatus(true);
                // response.setArrayList(arrayList);
                response.setArrayList(questionList);


            }


        });
        return response;
    }

    QuizQuestionEntity getQuestionWithOption(ArrayList<QuizQuestionEntity> arrayList,Long ques_Id){
        QuizQuestionEntity entity = null;
        System.out.println("arraylist size================="+arrayList.size());
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getQues_id() == ques_Id){
                entity = arrayList.get(i);
                break;
            }
        }
        return entity;

    }
    }
