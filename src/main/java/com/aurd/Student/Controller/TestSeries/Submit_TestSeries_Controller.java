package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
import com.aurd.Student.Model.Request.testseries.Submit_PracticeTest_Request;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.*;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/testSeries/submitTestSeries")

public class Submit_TestSeries_Controller {


    @Inject
    TestSeriesSubmitRepository repository;



    @Inject
    TestSeriesQuestionMapRepository testSeriesQuestionMapRepository;

    @Inject
    TestSeriesQuestionRepository testSeriesQuestionRepository;

    @Inject
    PractiseTestSeriesRepository quizRepository;

    @Inject
    TestSeriesResultRepository resultRepository;

//    @Inject StudentRepository studentRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)



    public Result_Response submitQuiz(QuizSubmitRequest request){
        System.out.println(new Gson().toJson(request));
        Result_Response response = new Result_Response();

        boolean value = repository.submitStudentQuizResponses(request);
        System.out.println(value);


        if(value==true){

            long totalMarks = 0;
            Double marksObtained = 0.0;
            long correctAns =0;
            long wrongAns = 0;
            int skippedAns = 0;

            ArrayList<TopicAnalysisModel> tList = new ArrayList<>();


//            System.out.println("Quiz ID "+request.getArrayList().get(0).getQuiz_id());

            int quizId = request.getQuizId().intValue();
            Long studentId = request.getStudentId();
            Long instId = request.getInstId();

            ArrayList<TestSeriesQuestionMap> quizQuestionIDList  =
                    (ArrayList<TestSeriesQuestionMap>) testSeriesQuestionMapRepository.
                            getQuestion(quizId);

            System.out.println(quizQuestionIDList.size());
            ArrayList<SubjectModel> subjectList=new ArrayList<>();
            for(TestSeriesQuestionMap model :quizQuestionIDList){

                System.out.println("Total Marks"+ model.getMarks());
                System.out.println("Quiz Question Map Model"+new Gson().toJson(model));
                totalMarks = totalMarks+model.getMarks();

                if(model.getSubject_id()!=0){

                    boolean exists=false;
                    for (SubjectModel subjectModel:subjectList) {
                        if(subjectModel.getId()==model.getSubject_id())
                        {
                            exists=true;
                        }
                    }

                    if(!exists)
                    {
                        subjectList.add(model.getSubjectModel());
                    }
                }

            }


            PractiseTestSeriesModel quizModel = quizRepository.find("id",
                    quizId).firstResult();



//            for (SubjectModel subjectModel:subjectList) {
//                TopicAnalysisModel analysisModel = new TopicAnalysisModel();
//                int subjectTotalMarks=0;
//                int subjectObtainedMarks=0;
//                int totalQuestion=0;
//                for (Quiz_Question_Map_Model model:quizQuestionIDList) {
//                    if(model.getSubject_id()==subjectModel.getId())
//                    {
//                        subjectTotalMarks+=model.getMarks();
//                        totalQuestion+=1;
//                    }
//                }
//                analysisModel.setTotalMarks(subjectTotalMarks);
//
//                for (Quiz_Submit_Model quiz_submit_model:request.getArrayList()) {
//                    if(quiz_submit_model.getSubjectId().equals(String.valueOf(subjectModel.getId())))
//                    {
//                        subjectObtainedMarks+=quiz_submit_model.getMarks_ob();
//                    }
//                }
//                analysisModel.setSubject(subjectModel.getSubject());
//                analysisModel.setMarksObtained(subjectObtainedMarks);
//                analysisModel.setPercent(String.valueOf((subjectObtainedMarks/subjectTotalMarks)*100));
//                analysisModel.setQuestions(totalQuestion);
//
//                tList.add(analysisModel);
//            }


            for(int i=0;i<request.getArrayList().size();i++)
            {
                TestSeriesSubmitModel quiz_submit_model= new Gson().fromJson(new Gson().toJson(request.getArrayList().get(i)),TestSeriesSubmitModel.class);
                TestSeriesQuestion quizQuestion = testSeriesQuestionRepository.
                        getQuestions(quiz_submit_model.getQues_id());

                if(quiz_submit_model.getAns().equals(quizQuestion.getAnswer()))
                {
                    correctAns++;
                    marksObtained = marksObtained + Double.parseDouble(quizModel.getMarks_per_ques());


                }
                else{
                    wrongAns++;
                    if(quizModel.getNegative_marking()!=null && !quizModel.getNegative_marking().equals("0"))
                    {
                        double num= Integer.parseInt(quizModel.getNegative_marking().split("/")[0]);
                        double den= Integer.parseInt(quizModel.getNegative_marking().split("/")[1]);

                        marksObtained=marksObtained-((num/den)*Double.parseDouble(quizModel.getMarks_per_ques()));

                    }


                }

            }


            System.out.println("Marks Obtained"+marksObtained);
            skippedAns=quizModel.getNumOfQuiz()-request.getArrayList().size();



            System.out.println("correct ans============="+correctAns);
            System.out.println("wrong ans         "+wrongAns);




            TestSeriesResult resultModel = new TestSeriesResult();
            resultModel.setStud_id(studentId);
            resultModel.setInst_id(instId);
            resultModel.setQuiz_id(quizId);
            resultModel.setSkipped(skippedAns);
            resultModel.setTime(request.getTime());





            resultModel.setMarks_obtained(marksObtained);
            resultModel.setTotal_marks(totalMarks);
            resultModel.setWrong_ans(wrongAns);
            resultModel.setCorrect_ans(correctAns);

            /// temporarily commented dont delete it

            double  percent = (marksObtained *100)/totalMarks;
            resultModel.setPercent(percent);


            if(quizModel.getCut_off()!=null){
                resultModel.setCut_off(quizModel.getCut_off());

                if(marksObtained>quizModel.getCut_off()){
                    resultModel.setStatus("pass");
                }else{
                    resultModel.setStatus("fail");
                }
            }else{
                resultModel.setStatus("pass");
                resultModel.setCut_off(0);
            }


            System.out.println(new Gson().toJson(resultModel));


            insertData(resultModel);


            response.setErrorCode(0);
            response.setStatus(true);
            response.setMessage("Quiz submitted Successfully");
            response.setResult(new Gson().fromJson(new Gson().toJson(resultModel),SaveResultModel.class));
//            response.setResultList(leaderBoardArrayList);
            response.setTopics(tList);
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
}
