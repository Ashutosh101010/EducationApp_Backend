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

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;
import com.aurd.Student.Model.BeanClass.SolutionEntity;
import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetQuizResultRequest;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.*;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.gson.Gson;

import javax.inject.Inject;
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
import java.util.concurrent.atomic.AtomicReference;

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
    TestSeriesQuestionMapRepository testSeriesQuestionMapRepository;


    @Inject
    QuizRepository seriesRepository;

    @Inject
    TestSeriesQuestionRepository testSeriesQuestionRepository;

    @Inject
    TestSeriesQuestionOptionModelRepository testSeriesQuestionOptionModelRepository;

    @Inject
    TestSeriesSubmitRepository submitRepository;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)


    public Result_Response getResult(GetQuizResultRequest request){

        List<TopicAnalysisModel> topicList=new ArrayList<>();
        ArrayList<SolutionEntity> solutions = new ArrayList<>();

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





        AtomicInteger totalSkipped= new AtomicInteger(0);
        AtomicDouble totalMarksObtained= new AtomicDouble(0);
        AtomicInteger totalMarks= new AtomicInteger(0);
        AtomicInteger totalWrongAns=new AtomicInteger(0);;
        AtomicInteger totalCorrectAns=new AtomicInteger(0);
        AtomicDouble totalPercent=new AtomicDouble(0);




        testSeriesModelList.forEach(practiseTestSeriesModel -> {

            TestSeriesResult seriesResult=getResultModel(resultList, Long.valueOf(practiseTestSeriesModel.getId()));
            if(seriesResult==null)
            {
                TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
                topicAnalysisModel.setSubject(getTestSeriesModel(testSeriesModelList, Long.valueOf(practiseTestSeriesModel.getId())).getCourseName());

                totalSkipped.getAndAdd(practiseTestSeriesModel.getNumOfQuiz());
                totalMarksObtained.getAndAdd(0);
                totalMarks.getAndAdd((int) Double.parseDouble(practiseTestSeriesModel.getMarks_per_ques())*practiseTestSeriesModel.getNumOfQuiz());
                totalWrongAns.getAndAdd(0);
                totalCorrectAns.getAndAdd(0);
                totalPercent.getAndAdd(0);

                topicAnalysisModel.setTotalMarks(Double.parseDouble(practiseTestSeriesModel.getMarks_per_ques())*practiseTestSeriesModel.getNumOfQuiz());
                topicAnalysisModel.setSkipped(0);
                topicAnalysisModel.setMarksObtained(0);
                topicAnalysisModel.setWrongAns(0);
                topicAnalysisModel.setCorrectAns(0);
                topicAnalysisModel.setPercent(0);
                topicAnalysisModel.setStatus("pass");


                topicList.add(topicAnalysisModel);

            }
            else{
                TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
                topicAnalysisModel.setSubject(getTestSeriesModel(testSeriesModelList,seriesResult.getQuiz_id()).getCourseName());

                totalSkipped.getAndAdd(seriesResult.getSkipped());
                totalMarksObtained.getAndAdd(seriesResult.getMarks_obtained());
                totalMarks.getAndAdd((int) seriesResult.getTotal_marks());
                totalWrongAns.getAndAdd((int) seriesResult.getWrong_ans());
                totalCorrectAns.getAndAdd((int) seriesResult.getCorrect_ans());
                totalPercent.getAndAdd(seriesResult.getPercent());

                topicAnalysisModel.setTotalMarks(seriesResult.getTotal_marks());
                topicAnalysisModel.setSkipped(seriesResult.getSkipped());
                topicAnalysisModel.setMarksObtained(seriesResult.getMarks_obtained());
                topicAnalysisModel.setWrongAns((int) seriesResult.getWrong_ans());
                topicAnalysisModel.setCorrectAns((int) seriesResult.getCorrect_ans());
                topicAnalysisModel.setPercent(seriesResult.getPercent());
                topicAnalysisModel.setStatus("pass");
                resultModel.setTime(seriesResult.getTime());
                topicList.add(topicAnalysisModel);
            }




        });
        resultList.forEach(testSeriesResult -> {


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



        ArrayList<QuizQuestionEntity> questions = new ArrayList<>();
        List<TestSeriesQuestionMap> quizQuestionIDList  =

                testSeriesQuestionMapRepository.
                        getQuestionID(sectionIdList);


        List<TestSeriesSubmitModel> submitList = submitRepository.getStudentPracticeTestResults(
                request.getInstID().intValue(),request.getStudID(),sectionIdList);


        for(int i=0;i<quizQuestionIDList.size();i++){


            QuizQuestionEntity model = new QuizQuestionEntity();
            TestSeriesQuestion testSeriesQuestion = testSeriesQuestionRepository.
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
            model.setSubject(getTestSeriesCourseNameFromSubject(testSeriesModelList, ((int) model.getQuiz_id())));

            ArrayList<TestSeriesQuestionOptionModel> optionList =  testSeriesQuestionOptionModelRepository.
                    getOptions(quizQuestionIDList.get(i).getQues_id());
            model.setOptions(optionList);

            questions.add(model);

        }

        questions.forEach(quizQuestionEntity -> {

            SolutionEntity entity = new SolutionEntity();

            ArrayList<Question_Option_Model> optionList =
                    testSeriesQuestionOptionModelRepository.
                            getOptions(quizQuestionEntity.getQues_id());

            entity.setOptions(optionList);
            entity.setQuestion(quizQuestionEntity.getQuestion());
            entity.setAnswer(quizQuestionEntity.getAnswer());

            entity.setSubject(quizQuestionEntity.getSubject());
            if (quizQuestionEntity.getAns_description() == null) {
                entity.setDescription("");
            }else{
                entity.setDescription(quizQuestionEntity.getAns_description());
            }


            for(int i=0;i<submitList.size();i++)
            {
                TestSeriesSubmitModel testSeriesSubmitModel=submitList.get(i);

                if(  testSeriesSubmitModel.getQues_id()==quizQuestionEntity.getQues_id()){
                    entity.setMarkForReview(submitList.get(i).getMarkForReview());
                    entity.setSkipped(0);
                }else{
                    entity.setMarkForReview(0);
                    entity.setSkipped(1);
                }



                if(testSeriesSubmitModel.getQues_id()==quizQuestionEntity.getQues_id())
                {
                    entity.setMyAnswer(testSeriesSubmitModel.getAns());
                    break;
                }
            }

            if(entity.getMyAnswer()==null)
            {
                entity.setMyAnswer("");
            }




            solutions.add(entity);



        });



        response.setErrorCode(0);
        response.setStatus(true);
        response.setMessage("Get Result");
        response.setResult(new Gson().fromJson(new Gson().toJson(resultModel),SaveResultModel.class));
//            response.setResultList(leaderBoardArrayList);
        response.setTopics(topicList);
        response.setSolutions(solutions);

        System.out.println(response);
return response;
    }

    public PractiseTestSeriesModel getTestSeriesModel(List<PractiseTestSeriesModel> testSeriesModelList,Long id)
    {
        for (PractiseTestSeriesModel model:
                testSeriesModelList) {
            if(model.getId()==id)
            {
                return  model;
            }
        }

        return null;
    }

    public String getTestSeriesCourseNameFromSubject(  List<PractiseTestSeriesModel> testSeriesModels,Integer id)
    {
        for(int i=0;i<testSeriesModels.size();i++)
        {
            if(id ==testSeriesModels.get(i).getId())
            {
                return testSeriesModels.get(i).getCourseName();
            }
        }
        return "";
    }

    public TestSeriesResult getResultModel(List<TestSeriesResult> list,Long id){
        AtomicReference<TestSeriesResult> testSeriesResult=new AtomicReference<>(null);
        list.forEach(testSeriesResult1 -> {
            if(testSeriesResult1.getQuiz_id()==id)
            {
                testSeriesResult.set(testSeriesResult1);
            }
        });

        return testSeriesResult.get();
    }

}
