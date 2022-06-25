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
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/testSeries/getResult")
public class GetPractiseTest_Result_Controller {

    @Inject
    TestSeriesResultRepository resultRepository;

    @Inject
    TestSeriesQuestionMapRepository questionRepository;

    @Inject
    TestSeriesSubmitRepository quizSubmitRepository;

//    @Inject
//    Quiz_Section_Repository section_repository;

    @Inject
    TestSeriesQuestionRepository quizQuestionRepository;

    @Inject
    PractiseTestSeriesRepository quizRepository;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public Result_Response getResult(GetQuizResultRequest request){

        System.out.println("------------");
        System.out.println(new Gson().toJson(request));

        ArrayList<LeaderBoardModel> leaderBoardList = new ArrayList<>();

        ArrayList<TestSeriesResult> arrayList1=resultRepository.getResultList(
                request.getQuizID(),request.getInstID());

        TestSeriesResult saveResultModel  =
                resultRepository.find("quiz_id=?1 and stud_id=?2 and inst_id =?3",
                        request.getQuizID(),request.getStudID(),request.getInstID()).firstResult();

        for(int i=0;i<arrayList1.size();i++){
            TestSeriesResult resultModel=arrayList1.get(i);

            LeaderBoardModel leaderBoardModel = new Gson().fromJson(new Gson().toJson(resultModel),
                    LeaderBoardModel.class);
            leaderBoardModel.setPercent((i+1/arrayList1.size())*100);
            if(saveResultModel.getId()== resultModel.getId())
            {
                saveResultModel.setPercent((resultModel.getMarks_obtained()/resultModel.getTotal_marks())*100);
            }
            leaderBoardModel.setName(resultModel.getStudentModel().getFname());
            leaderBoardModel.setImage(resultModel.getStudentModel().getProfile());
            leaderBoardList.add(leaderBoardModel);
        }
//                (ArrayList<SaveResultModel>) resultRepository.list("quiz_id=?1 and" +
//                                " inst_id =?2 order by marks_obtained DESC",
//                       request.getQuizID(),request.getInstID());

//
//        arrayList1.forEach(saveResultModel -> {
//
//        });

        PractiseTestSeriesModel quizModel = quizRepository.find("id",
                request.getQuizID().intValue()).firstResult();

        ArrayList<TopicAnalysisModel> topicList =new ArrayList<>();


        ArrayList<TestSeriesSubmitModel> quizSubmitModels=
                quizSubmitRepository.getStudentPracticeTestResult
                        (Math.toIntExact(request.getInstID()),request.getStudID(),request.getQuizID());

        System.out.println("Quiz Subbmmited length"+quizSubmitModels.size());



        ArrayList<SubjectModel> subjects=new ArrayList<>();
        ArrayList<TestSeriesQuestionMap> questions=questionRepository.getQuestion(request.getQuizID());

        System.out.println("Questions main List Size"+questions.size());

        System.out.println("Question List Size"+ questions.size());
        for (TestSeriesQuestionMap question: questions) {
            boolean exists=false;

            if(question.getSubject_id()!=0){

                for(SubjectModel subject:subjects)
                {
                    if(subject.getId()==question.getSubject_id())
                    {
                        exists=true;
                    }
                }



                if(!exists)
                {
                    subjects.add(question.getSubjectModel());
                }
            }


        }


        for (SubjectModel subjectModel:subjects) {
            TopicAnalysisModel analysisModel = new TopicAnalysisModel();
            int subjectTotalMarks=0;
            Double subjectObtainedMarks=0.0;
            int totalQuestion=0;
            int correctAns = 0;
            int wrongAns =0;
            int skipped = 0;
            Double percent = 0.0;
            for (TestSeriesQuestionMap model:questions) {
                if(model.getSubject_id()==subjectModel.getId())
                {
                    subjectTotalMarks+=model.getMarks();
                    totalQuestion+=1;

                }



            }
            System.out.println("Total Marks"+subjectTotalMarks);
            analysisModel.setTotalMarks(subjectTotalMarks);

            for (TestSeriesSubmitModel quiz_submit_model:quizSubmitModels) {




                TestSeriesQuestion  quizQuestion = quizQuestionRepository.
                        getQuestions(quiz_submit_model.getQues_id());






                if(quiz_submit_model.getSubjectId()==subjectModel.getId())
                {
                    subjectObtainedMarks+=quiz_submit_model.getMarks_ob();



                    if(quiz_submit_model.getAns().equals(quizQuestion.getAnswer()))
                    {
                        correctAns++;
                        subjectObtainedMarks = subjectObtainedMarks + quiz_submit_model.getMarks_ob();
                    }
                    else{
                        wrongAns++;
                        if(quizModel.getNegative_marking()!=null &&
                                !quizModel.getNegative_marking().equals("0"))
                        {
                            double num= Integer.parseInt(quizModel.getNegative_marking().split("/")[0]);
                            double den= Integer.parseInt(quizModel.getNegative_marking().split("/")[1]);

                            System.out.println("negative "+num +" "+den +"  "+num/den);
                            subjectObtainedMarks=subjectObtainedMarks-
                                    ((num/den)*Double.parseDouble(quizModel.getMarks_per_ques()));

                        }
                    }

                }

            }


//            Quiz_Section_Model sectionModel  = section_repository.find("quiz_id =?1 and subject_id=?2",
//                    request.getQuizID().intValue(),subjectModel.getId()).firstResult();


            analysisModel.setSubject(subjectModel.getSubject());
            analysisModel.setMarksObtained(subjectObtainedMarks);

            percent = (((double) correctAns) *100)/totalQuestion;


            analysisModel.setPercent(percent);
            analysisModel.setQuestions(totalQuestion);
            analysisModel.setCorrectAns(correctAns);
            analysisModel.setWrongAns(wrongAns);


//            if(sectionModel.getSubject_cutoff()!=0){
//                analysisModel.setCutOff(sectionModel.getSubject_cutoff());
//                if(sectionModel.getSubject_cutoff()<subjectObtainedMarks){
//                    analysisModel.setStatus("Pass");
//                }else{
//                    analysisModel.setStatus("Fail");
//                }
//            }else{
                analysisModel.setCutOff(0);
                analysisModel.setStatus("Pass");
//            }

            skipped=quizModel.getNumOfQuiz()-quizSubmitModels.size();

            analysisModel.setSkipped(skipped);


            topicList.add(analysisModel);
        }









        Result_Response response = new Result_Response();
        if(saveResultModel==null){
            response.setStatus(false);
            response.setMessage("Get Result Failure");
            response.setErrorCode(1);
        }else{
            response.setStatus(true);
            response.setMessage("Get Result Success");
            response.setErrorCode(0);
            response.setResult(new Gson().fromJson(new Gson().toJson(saveResultModel),SaveResultModel.class));
            response.setResultList(leaderBoardList);
            response.setTopics(topicList);
        }




        return response;


//        ResultEntity result = new ResultEntity();
//        long totalMarks = 0;
//        long marksObtained = 0;
//        long correctAns =0;
//        long wrongAns = 0;
//
//        ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =
//                (ArrayList<Quiz_Question_Map_Model>) questionID_repository.
//                        getQuestionID(request.getQuizID());
//
//        System.out.println(quizQuestionIDList.size());
//        Quiz_Question_Model quizQuestionModel;
//        for(Quiz_Question_Map_Model model :quizQuestionIDList){
//            totalMarks = totalMarks+model.getMarks();
//        }
//
//
//        result.setTotalMarks(totalMarks);
//
//        ArrayList<Quiz_Submit_Model> arrayList = quiz_submit_repository.getStudentPracticeTestResult
//                (request.getInstID().intValue(),request.getStudID(),request.getQuizID());
//        for(Quiz_Submit_Model quizSubmitModel :arrayList){
//            marksObtained = marksObtained + quizSubmitModel.getMarks_ob();
//
//            quizQuestionModel = quizQuestionRepository.getQuestions(quizSubmitModel.getQues_id());
//            if(quizQuestionModel.getQuestion_id()==quizSubmitModel.getQues_id()){
//                if(quizQuestionModel.getAnswer().equals(quizSubmitModel.getAns())){
//                    correctAns = correctAns+1;
//                }else{
//                    wrongAns = wrongAns+1;
//                }
//            }
//        }
//        result.setMarksObtained(marksObtained);
//
//       QuizModel quizModel = quizRepository.find("quiz_id",request.getQuizID()).firstResult();
//       result.setCutOff(quizModel.getCutoff());
//        result.setCorrectAnswered(correctAns);
//        result.setWrongAnswered(wrongAns);
//
//        Result_Response response = new Result_Response();
//        if(result!=null){
//            response.setErrorCode(0);
//            response.setStatus(true);
//            response.setMessage("Get Quiz Result Successfully");
////            response.setResultEntity(result);
//        }else {
//            response.setErrorCode(1);
//            response.setStatus(false);
//            response.setMessage("Get Quiz Result Fail");
//
//        }
//        return  response;
//


    }


}
