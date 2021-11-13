package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.ResultEntity;
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

@Path("/quiz/getQuizResult")
public class GetQuizResultController {

    @Inject
    ResultRepository resultRepository;

    @Inject
    Get_QuestionID_Repository questionRepository;

    @Inject
    Quiz_Submit_Repository quizSubmitRepository;

    @Inject
    Quiz_Section_Repository section_repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    QuizRepository quizRepository;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public  Result_Response getResult(GetQuizResultRequest request){

        ArrayList<LeaderBoardModel> leaderBoardList = new ArrayList<>();

        ArrayList<SaveResultModel> arrayList1=resultRepository.getResultList(
                request.getQuizID(),request.getInstID());

        SaveResultModel saveResultModel  =
                resultRepository.find("quiz_id=?1 and stud_id=?2 and inst_id =?3",
                        request.getQuizID(),request.getStudID(),request.getInstID()).firstResult();

        for(int i=0;i<arrayList1.size();i++){
            SaveResultModel resultModel=arrayList1.get(i);

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

        QuizModel quizModel = quizRepository.find("quiz_id",
                request.getQuizID()).firstResult();

        ArrayList<TopicAnalysisModel> topicList =new ArrayList<>();


        ArrayList<Quiz_Submit_Model> quizSubmitModels=
                quizSubmitRepository.getStudentPracticeTestResult
                        (Math.toIntExact(request.getInstID()),request.getStudID(),request.getQuizID());

        System.out.println("Quiz Subbmmited length"+quizSubmitModels.size());



        ArrayList<SubjectModel> subjects=new ArrayList<>();
        ArrayList<Quiz_Question_Map_Model> questions=questionRepository.getQuestion(request.getQuizID());

        System.out.println("Questions main List Size"+questions.size());

        System.out.println("Question List Size"+ questions.size());
        for (Quiz_Question_Map_Model question: questions) {
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
            for (Quiz_Question_Map_Model model:questions) {
                if(model.getSubject_id()==subjectModel.getId())
                {
                    subjectTotalMarks+=model.getMarks();
                    totalQuestion+=1;

                }



            }
            System.out.println("Total Marks"+subjectTotalMarks);
            analysisModel.setTotalMarks(subjectTotalMarks);

            for (Quiz_Submit_Model quiz_submit_model:quizSubmitModels) {




                Quiz_Question_Model  quizQuestion = quizQuestionRepository.
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
                                    ((num/den)*quizModel.getMarks_per_ques());

                        }
                    }

                }

            }


          Quiz_Section_Model sectionModel  = section_repository.find("quiz_id =?1 and subject_id=?2",
                    request.getQuizID().intValue(),subjectModel.getId()).firstResult();


            analysisModel.setSubject(subjectModel.getSubject());
            analysisModel.setMarksObtained(subjectObtainedMarks);

            percent = (((double) correctAns) *100)/totalQuestion;


            analysisModel.setPercent(percent);
            analysisModel.setQuestions(totalQuestion);
            analysisModel.setCorrectAns(correctAns);
            analysisModel.setWrongAns(wrongAns);


            if(sectionModel.getSubject_cutoff()!=0){
                analysisModel.setCutOff(sectionModel.getSubject_cutoff());
                if(sectionModel.getSubject_cutoff()<subjectObtainedMarks){
                    analysisModel.setStatus("Pass");
                }else{
                    analysisModel.setStatus("Fail");
                }
            }else{
                analysisModel.setCutOff(0);
                analysisModel.setStatus("Pass");
            }

            skipped=quizModel.getTotal_ques()-quizSubmitModels.size();

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
         response.setResult(saveResultModel);
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
