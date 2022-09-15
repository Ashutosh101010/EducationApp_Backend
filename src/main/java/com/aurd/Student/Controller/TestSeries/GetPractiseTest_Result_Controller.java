package com.aurd.Student.Controller.TestSeries;
//
//import com.aurd.Student.Model.BeanClass.ResultEntity;
//import com.aurd.Student.Model.Entity.Quiz_Question_Model;
//import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
//import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
//import com.aurd.Student.Model.Request.testseries.Get_PracticeTest_Result_Request;
//import com.aurd.Student.Model.Response.TestSeries.Result_Response;
//import com.aurd.Student.Repository.Get_QuestionID_Repository;
//import com.aurd.Student.Repository.TestSeries_Repository;
//import com.aurd.Student.Repository.QuizQuestionRepository;
//import com.aurd.Student.Repository.Quiz_Submit_Repository;
//
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//

import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.GetQuizResultRequest;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.*;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/testSeries/getResult")
public class GetPractiseTest_Result_Controller {

    @Inject
    TestSeriesResultRepository resultRepository;

    @Inject
    TestSeriesQuestionMapRepository questionRepository;

    @Inject
    TestSeriesSubmitRepository quizSubmitRepository;

    @Inject
    PractiseTestSeriesRepository practiseTestSeriesRepository;

    @Inject
    QuizRepository seriesRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public Result_Response getResult(GetQuizResultRequest request){

        List<TopicAnalysisModel> topicList=new ArrayList<>();

        Result_Response response = new Result_Response();


        QuizModel seriesModel=seriesRepository.find("id",request.getQuizID().intValue()).firstResult();


        List<PractiseTestSeriesModel> testSeriesModelList=practiseTestSeriesRepository.find("series_id=?1 and test=?2",request.getQuizID(),request.getTestName()).list();

        ArrayList<Long> sectionIdList=new ArrayList<>();
        testSeriesModelList.forEach(practiseTestSeriesModel -> {
            System.out.println(practiseTestSeriesModel.getId());
            sectionIdList.add(Long.valueOf(practiseTestSeriesModel.getId()));
        });

        List<TestSeriesResult> resultList=resultRepository.resultList(request,sectionIdList);

        Map<Long,TestSeriesResult> resultListMap=new HashMap<>();

        resultList.forEach(testSeriesResult -> {
            resultListMap.put(testSeriesResult.getQuiz_id(),testSeriesResult);
        });
        SaveResultModel resultModel=new SaveResultModel();
        resultModel.setStud_id(request.getStudID());
        resultModel.setInst_id(request.getInstID());
        resultModel.setQuiz_id(seriesModel.getQuiz_id());
//
//        resultModel.setTime(request.getTime());




        AtomicInteger totalSkipped= new AtomicInteger(0);
        AtomicDouble totalMarksObtained= new AtomicDouble(0);
        AtomicInteger totalMarks= new AtomicInteger(0);
        AtomicInteger totalWrongAns=new AtomicInteger(0);;
        AtomicInteger totalCorrectAns=new AtomicInteger(0);
        AtomicDouble totalPercent=new AtomicDouble(0);


        testSeriesModelList.forEach(practiseTestSeriesModel -> {
            TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
            topicAnalysisModel.setSubject(practiseTestSeriesModel.getTest());
            if(resultListMap.containsKey((long) practiseTestSeriesModel.getId()))
            {
                TestSeriesResult topicResult=resultListMap.get(((long) practiseTestSeriesModel.getId()));


                totalSkipped.getAndAdd(topicResult.getSkipped());
                totalMarksObtained.getAndAdd(topicResult.getMarks_obtained());
                totalMarks.getAndAdd((int) topicResult.getTotal_marks());
                totalWrongAns.getAndAdd((int) topicResult.getWrong_ans());
                totalCorrectAns.getAndAdd((int) topicResult.getCorrect_ans());
                totalPercent.getAndAdd(topicResult.getPercent());

            }
            else{
                totalSkipped.getAndAdd(practiseTestSeriesModel.getNumOfQuiz());
                totalMarksObtained.getAndAdd(0);
                totalMarks.getAndAdd(0);
                totalWrongAns.getAndAdd(0);
                totalCorrectAns.getAndAdd(0);
                totalPercent.getAndAdd(0);

            }


            topicAnalysisModel.setQuestions(practiseTestSeriesModel.getNumOfQuiz());
            topicAnalysisModel.setPercent(resultModel.getPercent());
            topicAnalysisModel.setSkipped(resultModel.getSkipped());
            topicAnalysisModel.setCorrectAns((int) resultModel.getCorrect_ans());
            topicAnalysisModel.setWrongAns((int) resultModel.getWrong_ans());
            topicAnalysisModel.setCorrectAns((int) resultModel.getCorrect_ans());
            topicAnalysisModel.setTotalMarks((int) resultModel.getTotal_marks());
            topicAnalysisModel.setMarksObtained(resultModel.getMarks_obtained());

            topicList.add(topicAnalysisModel);



        });

        resultModel.setSkipped(totalSkipped.get());

        resultModel.setMarks_obtained(totalMarksObtained.get());
        resultModel.setTotal_marks(totalMarks.get());
        resultModel.setWrong_ans(totalWrongAns.get());
        resultModel.setCorrect_ans(totalCorrectAns.get());
        if(resultListMap.keySet().size()==0)
        {
            resultModel.setPercent(0);
        }else{
            resultModel.setPercent(totalPercent.get()/resultListMap.keySet().size());
        }



        try {
            resultModel.setCut_off(seriesModel.getCutoff());
        }catch (Exception e)
        {
            resultModel.setCut_off(0);
        }






        response.setErrorCode(0);
        response.setStatus(true);
        response.setMessage("Get Result");
        response.setResult(new Gson().fromJson(new Gson().toJson(resultModel),SaveResultModel.class));
//            response.setResultList(leaderBoardArrayList);
        response.setTopics(topicList);

return response;
    }


}
