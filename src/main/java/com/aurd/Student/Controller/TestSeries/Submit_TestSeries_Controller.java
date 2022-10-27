package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;
import com.aurd.Student.Model.BeanClass.SolutionEntity;
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

    @Inject
    TestSeriesQuestionMapRepository testSeriesQuestionMapRepository;

    @Inject
    TestSeriesQuestionOptionModelRepository testSeriesQuestionOptionModelRepository;


//    @Inject StudentRepository studentRepository;

    @Inject
    QuizRepository seriesRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)



    public Result_Response submitQuiz(QuizSubmitRequest request){
        System.out.println(new Gson().toJson(request));
        Result_Response response = new Result_Response();

        List<TestSeriesSubmitModel> submitModels  = repository.submitStudentQuizResponses(request);


        List<SolutionEntity> solutions=new ArrayList<>();

        if(submitModels!=null){


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

                    TestSeriesQuestion quizQuestion = testSeriesQuestionRepository.
                            getQuestions(testSeriesSubmitModel.getQues_id());
                    if(testSeriesSubmitModel.getAns().equals(quizQuestion.getAnswer()))
                    {
                        correctAns.getAndIncrement();
                        marksObtained.set(marksObtained.get() + Double.parseDouble(testModel.getMarks_per_ques()));
                    }
                    else {
                        wrongAns.getAndIncrement();
                        if (testModel.getNegative_marking() != null && !testModel.getNegative_marking().isEmpty() && !testModel.getNegative_marking().equals("0")) {
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




            testSeriesModelList.forEach(practiseTestSeriesModel -> {
                if(resultListMap.containsKey(Long.valueOf(practiseTestSeriesModel.getId())))
                {
                    TestSeriesResult seriesResult=resultListMap.get(Long.valueOf(practiseTestSeriesModel.getId()));

                    TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
                    topicAnalysisModel.setSubject(getTestSeriesModel(testSeriesModelList,seriesResult.getQuiz_id()).getTest());

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


                    resultModel.setTime(seriesResult.getTime());
                    topicList.add(topicAnalysisModel);
                }else{


                    TopicAnalysisModel topicAnalysisModel=new TopicAnalysisModel();
                    topicAnalysisModel.setSubject(getTestSeriesModel(testSeriesModelList, (long) practiseTestSeriesModel.getId()).getTest());

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
                    resultModel.setTime(request.getTime());
                    topicList.add(topicAnalysisModel);
                }

            });
            resultListMap.forEach((aLong, testSeriesResult) -> {


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


                for(int i=0;i<submitModels.size();i++)
                {
                    TestSeriesSubmitModel testSeriesSubmitModel=submitModels.get(i);

                    if(  testSeriesSubmitModel.getQues_id()==quizQuestionEntity.getQues_id()){
                        entity.setMarkForReview(submitModels.get(i).getMarkForReview());
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
            response.setMessage("Quiz submitted Successfully");
            response.setResult(new Gson().fromJson(new Gson().toJson(resultModel),SaveResultModel.class));
            response.setSolutions((ArrayList<SolutionEntity>) solutions);
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
        System.out.println("id........"+id);

        for (PractiseTestSeriesModel model:
             testSeriesModelList) {
            System.out.println(model);
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

}
