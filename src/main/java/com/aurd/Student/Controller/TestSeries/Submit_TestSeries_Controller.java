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
                sectionIdList.add(Long.valueOf(practiseTestSeriesModel.getId()));
            });

//            List<TestSeriesQuestionMap> quizQuestionIDList  =
//
//                    testSeriesQuestionMapRepository.
//                            getQuestionID(sectionIdList);


            QuizModel seriesModel=seriesRepository.find("id",request.getQuizId()).firstResult();

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



            courseMap.forEach((aLong, testSeriesSubmitModels) -> {
                testSeriesSubmitModels.forEach(testSeriesSubmitModel -> {
                    PractiseTestSeriesModel testModel=getTestSeriesModel(testSeriesModelList,testSeriesSubmitModel.getQuiz_id());
                    long totalMarks = (long) Integer.parseInt(testModel.getMarks_per_ques()) *testModel.getNumOfQuiz();
                    Double marksObtained = 0.0;
                    long correctAns =0;
                    long wrongAns = 0;
                    int skippedAns = 0;
                    TestSeriesQuestion quizQuestion = testSeriesQuestionRepository.
                            getQuestions(testSeriesSubmitModel.getQues_id());
                    if(testSeriesSubmitModel.getAns().equals(quizQuestion.getAnswer()))
                    {
                        correctAns++;
                        marksObtained = marksObtained + seriesModel.getMarks_per_ques();


                    }
                    else {
                        wrongAns++;
                        if (seriesModel.getNegative_marking() != null && !seriesModel.getNegative_marking().equals("0")) {
                            double num = Integer.parseInt(seriesModel.getNegative_marking().split("/")[0]);
                            double den = Integer.parseInt(seriesModel.getNegative_marking().split("/")[1]);

                            marksObtained = marksObtained - ((num / den) * seriesModel.getMarks_per_ques());

                        }
                    }
// spell mistake in quiz as ques
                    Integer numOfQues=testModel.getNumOfQuiz();
                    skippedAns=numOfQues-testSeriesSubmitModels.size();

                    TestSeriesResult resultModel = new TestSeriesResult();
                    resultModel.setStud_id(studentId);
                    resultModel.setInst_id(instId);
                    resultModel.setQuiz_id(testSeriesSubmitModel.getQuiz_id());
                    resultModel.setSkipped(skippedAns);
                    resultModel.setTime(request.getTime());


                    resultModel.setMarks_obtained(marksObtained);
                    resultModel.setTotal_marks(totalMarks);
                    resultModel.setWrong_ans(wrongAns);
                    resultModel.setCorrect_ans(correctAns);

                    double  percent = (marksObtained *100)/totalMarks;
                    resultModel.setPercent(percent);


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



                    insertData(resultModel);


                    resultListMap.put(resultModel.getQuiz_id(),resultModel);

                });
            });

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


            testSeriesModelList.forEach(practiseTestSeriesModel -> {
                TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
                topicAnalysisModel.setSubject(practiseTestSeriesModel.getTest());
                TestSeriesResult topicResult=resultListMap.get(((long) practiseTestSeriesModel.getId()));

                totalSkipped.getAndAdd(topicResult.getSkipped());
                totalMarksObtained.getAndAdd(resultModel.getMarks_obtained());
                totalMarks.getAndAdd((int) resultModel.getTotal_marks());
                totalWrongAns.getAndAdd((int) resultModel.getWrong_ans());
                totalCorrectAns.getAndAdd((int) resultModel.getCorrect_ans());
                totalPercent.getAndAdd(resultModel.getPercent());

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
            resultModel.setPercent(totalPercent.get()/resultListMap.keySet().size());
            resultModel.setCut_off(seriesModel.getCutoff());






            response.setErrorCode(0);
            response.setStatus(true);
            response.setMessage("Quiz submitted Successfully");
            response.setResult(new Gson().fromJson(new Gson().toJson(resultModel),SaveResultModel.class));
//            response.setResultList(leaderBoardArrayList);
            response.setTopics(topicList);
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
