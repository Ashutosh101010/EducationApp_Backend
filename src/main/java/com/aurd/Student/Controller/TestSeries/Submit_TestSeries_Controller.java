package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
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
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Path("/testSeries/submitTestSeries")

public class Submit_TestSeries_Controller {


    @Inject
    TestSeriesSubmitRepository repository;

    @Inject
    TestSeriesQuestionRepository testSeriesQuestionRepository;


    @Inject
    TestSeriesResultRepository resultRepository;

    @Inject
    PractiseTestSeriesRepository practiseTestSeriesRepository;

//    @Inject StudentRepository studentRepository;

    @Inject
    QuizRepository seriesRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)



    public Result_Response submitQuiz(QuizSubmitRequest request){
        System.out.println(new Gson().toJson(request));
        Result_Response response = new Result_Response();

        boolean value = repository.submitStudentQuizResponses(request);
        System.out.println(value);


        if(value==true){


            Long studentId = request.getStudentId();
            Long instId = request.getInstId();

            List<PractiseTestSeriesModel> testSeriesModelList=practiseTestSeriesRepository.find("series_id=?1 and test=?2",request.getQuizId(),request.getTestName()).list();

            ArrayList<Long> sectionIdList=new ArrayList<>();
            testSeriesModelList.forEach(practiseTestSeriesModel -> {
                System.out.println(practiseTestSeriesModel.getId());
                sectionIdList.add((long) practiseTestSeriesModel.getId());
            });

//            List<TestSeriesQuestionMap> quizQuestionIDList  =
//
//                    testSeriesQuestionMapRepository.
//                            getQuestionID(sectionIdList);


            QuizModel seriesModel=seriesRepository.find("quiz_id",request.getQuizId().intValue()).firstResult();

            Map<Long,TestSeriesResult> resultListMap=new HashMap<>();

            Map<Long,ArrayList<TestSeriesSubmitModel>> courseMap=new HashMap<>();

            request.getArrayList().forEach(quiz_submit_model -> {
                Gson gson=new Gson();

                TestSeriesSubmitModel testSeriesSubmitModel=gson.fromJson(gson.toJson(quiz_submit_model),TestSeriesSubmitModel.class);
                if(courseMap.containsKey(testSeriesSubmitModel.getQuiz_id()))
                {
                    courseMap.get(testSeriesSubmitModel.getQuiz_id()).add(testSeriesSubmitModel);
                }
                else{
                    courseMap.put(testSeriesSubmitModel.getQuiz_id(),new ArrayList<>());
                    courseMap.get(testSeriesSubmitModel.getQuiz_id()).add(testSeriesSubmitModel);
                }

            });

            System.out.println(courseMap.toString());


            courseMap.forEach((aLong, testSeriesSubmitModels) -> {
                PractiseTestSeriesModel testModel=getTestSeriesModel(testSeriesModelList,testSeriesSubmitModels.get(0).getQuiz_id());

                TestSeriesResult resultModel = new TestSeriesResult();
                resultModel.setTotal_marks((Double.parseDouble(testModel.getMarks_per_ques()) *testModel.getNumOfQuiz()));
                AtomicReference<Double> marksObtained = new AtomicReference<>(0.0);
                AtomicLong correctAns = new AtomicLong();
                AtomicLong wrongAns = new AtomicLong();
                int skippedAns = 0;
                testSeriesSubmitModels.forEach(testSeriesSubmitModel -> {


//
                    TestSeriesQuestion quizQuestion = testSeriesQuestionRepository.
                            getQuestions(testSeriesSubmitModel.getQues_id());
                    if(testSeriesSubmitModel.getAns().equals(quizQuestion.getAnswer()))
                    {
                        correctAns.getAndIncrement();
                        marksObtained.set(marksObtained.get() + Double.parseDouble(testModel.getMarks_per_ques()));


                    }
                    else {
                        wrongAns.getAndIncrement();
                        if (testModel.getNegative_marking() != null && !testModel.getNegative_marking().equals("0")) {
                            double num = Integer.parseInt(testModel.getNegative_marking().split("/")[0]);
                            double den = Integer.parseInt(testModel.getNegative_marking().split("/")[1]);

                            marksObtained.set(marksObtained.get() - ((num / den) * Double.parseDouble(testModel.getMarks_per_ques())));

                        }
                    }
//// spell mistake in quiz as ques
//





//                    if(seriesModel.getCutoff()!=null){
//                        resultModel.setCut_off(seriesModel.getCutoff());
//
//                        if(marksObtained> seriesModel.getCutoff()){
//                            resultModel.setStatus("pass");
//                        }else{
//                            resultModel.setStatus("fail");
//                        }
//                    }else{
//                        resultModel.setStatus("pass");
//                        resultModel.setCut_off(0);
//                    }





                });
                Integer numOfQues=testModel.getNumOfQuiz();
                    skippedAns=numOfQues-testSeriesSubmitModels.size();

                resultModel.setStud_id(studentId);
                resultModel.setInst_id(instId);
                resultModel.setQuiz_id(testModel.getId());
                resultModel.setSkipped(skippedAns);
                resultModel.setTime(request.getTime());


                resultModel.setMarks_obtained(marksObtained.get());
                resultModel.setWrong_ans(wrongAns.get());
                resultModel.setCorrect_ans(correctAns.get());

                double  percent = (marksObtained.get() *100)/resultModel.getTotal_marks();
                resultModel.setPercent(percent);

                resultModel.setStatus("pass");
                insertData(resultModel);



                resultListMap.put(resultModel.getQuiz_id(),resultModel);
                System.out.println(resultModel.getQuiz_id());
                System.out.println(resultModel);

            });

            System.out.println(resultListMap.toString());
            List<TopicAnalysisModel> topicList=new ArrayList<>();


            SaveResultModel resultModel=new SaveResultModel();
            resultModel.setStud_id(studentId);
            resultModel.setInst_id(instId);
            resultModel.setQuiz_id(seriesModel.getQuiz_id());
//
            resultModel.setTime(request.getTime());



            AtomicInteger totalSkipped= new AtomicInteger(0);
            AtomicDouble totalMarksObtained= new AtomicDouble(0);
            AtomicInteger totalMarks= new AtomicInteger(0);
            AtomicInteger totalWrongAns=new AtomicInteger(0);;
            AtomicInteger totalCorrectAns=new AtomicInteger(0);
            AtomicDouble totalPercent=new AtomicDouble(0);


            resultListMap.forEach((aLong, testSeriesResult) -> {
                TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
                topicAnalysisModel.setSubject(getTestSeriesModel(testSeriesModelList,testSeriesResult.getQuiz_id()).getTest());

                totalSkipped.getAndAdd(testSeriesResult.getSkipped());
                totalMarksObtained.getAndAdd(testSeriesResult.getMarks_obtained());
                totalMarks.getAndAdd((int) testSeriesResult.getTotal_marks());
                totalWrongAns.getAndAdd((int) testSeriesResult.getWrong_ans());
                totalCorrectAns.getAndAdd((int) testSeriesResult.getCorrect_ans());
                totalPercent.getAndAdd(testSeriesResult.getPercent());

                topicAnalysisModel.setTotalMarks(testSeriesResult.getTotal_marks());
                topicAnalysisModel.setSkipped(testSeriesResult.getSkipped());
                topicAnalysisModel.setMarksObtained(testSeriesResult.getMarks_obtained());
                topicAnalysisModel.setWrongAns((int) testSeriesResult.getWrong_ans());
                topicAnalysisModel.setCorrectAns((int) testSeriesResult.getCorrect_ans());
                topicAnalysisModel.setPercent(testSeriesResult.getPercent());



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
            response.setMessage("Quiz submitted Successfully");
            response.setResult(new Gson().fromJson(new Gson().toJson(resultModel),SaveResultModel.class));
//            response.setResultList(leaderBoardArrayList);
            response.setTopics(topicList);

            System.out.println(response);
        }else{
            response.setErrorCode(1);
            response.setStatus(false);
            response.setMessage("Quiz Submission Failure");

        }


        return  response;


    }


    @Transactional
    public void insertData(TestSeriesResult resultModel){
        resultRepository.persistAndFlush(resultModel);
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

}
