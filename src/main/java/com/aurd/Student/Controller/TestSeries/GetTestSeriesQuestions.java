package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;


import com.aurd.Student.Model.Entity.*;

import com.aurd.Student.Model.Request.GetQuestionRequest;
import com.aurd.Student.Model.Response.GetQuestionResponse;
import com.aurd.Student.Repository.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/testSeries/getQuestions")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetTestSeriesQuestions {
    @Inject
    TestSeriesQuestionMapRepository testSeriesQuestionMapRepository;

    @Inject
    TestSeriesQuestionRepository testSeriesQuestionRepository;

    @Inject
    TestSeriesQuestionOptionModelRepository testSeriesQuestionOptionModelRepository;

    @Inject
    PractiseTestSeriesRepository practiseTestSeriesRepository;

    @POST



    public GetQuestionResponse getQuestions(GetQuestionRequest request) {


        GetQuestionResponse response = new GetQuestionResponse();
        QuizQuestionEntity model;
        TestSeriesQuestion testSeriesQuestion;


        List<PractiseTestSeriesModel> testSeriesModelList=practiseTestSeriesRepository.find("series_id=?1 and test=?2",request.getQuiz_id(),request.getTestName()).list();

        response.setSectionList(testSeriesModelList);
        ArrayList<Long> sectionIdList=new ArrayList<>();
        testSeriesModelList.forEach(practiseTestSeriesModel -> {
            System.out.println(practiseTestSeriesModel.getId());
            sectionIdList.add(Long.valueOf(practiseTestSeriesModel.getId()));
        });

        ArrayList<QuizQuestionEntity> arrayList = new ArrayList<>();
        List<TestSeriesQuestionMap> quizQuestionIDList  =

                testSeriesQuestionMapRepository.
                        getQuestionID(sectionIdList);

        for(int i=0;i<quizQuestionIDList.size();i++){


            model = new QuizQuestionEntity();
            testSeriesQuestion = testSeriesQuestionRepository.
                    getQuestions(quizQuestionIDList.get(i).getQues_id());

            model.setQues_id(quizQuestionIDList.get(i).getQues_id());
            model.setQuiz_id(quizQuestionIDList.get(i).getQuiz_id());
            model.setQuestion(testSeriesQuestion.getQuestion());
            model.setQuestion_type(testSeriesQuestion.getQuestion_type());
            model.setMarks(quizQuestionIDList.get(i).getMarks());
            model.setAnswer(testSeriesQuestion.getAnswer());
            model.setAns_description(testSeriesQuestion.getAns_description());
            model.setAdded_on(testSeriesQuestion.getAdded_on());
            model.setPic(testSeriesQuestion.getPic());

            ArrayList<TestSeriesQuestionOptionModel> optionList =  testSeriesQuestionOptionModelRepository.
                    getOptions(quizQuestionIDList.get(i).getQues_id());
            model.setOptions(optionList);

            arrayList.add(model);

        }



        response.setMessage("Get Question Success");
        response.setErrorCode(0);
        response.setStatus(true);
        response.setArrayList(arrayList);

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

    @Path("/{series_id}")
  @GET
    public GetQuestionResponse getQuestionsBySeries( @PathParam("series_id") String seriesId) {




        QuizQuestionEntity model;
        TestSeriesQuestion testSeriesQuestion;


        ArrayList<QuizQuestionEntity> arrayList = new ArrayList<>();
//        ArrayList<TestSeriesQuestionMap> quizQuestionIDList  =
//
//                (ArrayList<TestSeriesQuestionMap>) testSeriesQuestionMapRepository.
//                        getQuestionID(request.getQuiz_id());

//        System.out.println("List size==="+quizQuestionIDList.size());

        List<TestSeriesQuestion> list=testSeriesQuestionRepository.find("series",Integer.parseInt(seriesId)).list();


        for (TestSeriesQuestion testSeriesQuestion1:list) {
            model = new QuizQuestionEntity();
            model.setQues_id(testSeriesQuestion1.getQuestion_id());
//            model.setQuiz_id(testSeriesQuestion1);
            model.setQuestion(testSeriesQuestion1.getQuestion());
            model.setQuestion_type(testSeriesQuestion1.getQuestion_type());
            model.setMarks(testSeriesQuestion1.getMarks());
            model.setAnswer(testSeriesQuestion1.getAnswer());
            model.setAns_description(testSeriesQuestion1.getAns_description());
            model.setAdded_on(testSeriesQuestion1.getAdded_on());
            model.setPic(testSeriesQuestion1.getPic());

            ArrayList<TestSeriesQuestionOptionModel> optionList =  testSeriesQuestionOptionModelRepository.
                    getOptions(testSeriesQuestion1.getQuestion_id());
            model.setOptions(optionList);

            arrayList.add(model);
        }



        GetQuestionResponse response = new GetQuestionResponse();
        response.setMessage("Get Question Success");
        response.setErrorCode(0);
        response.setStatus(true);
        response.setArrayList(arrayList);

        return response;
    }

}
