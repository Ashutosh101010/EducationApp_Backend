package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.ResultEntity;
import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.GetQuizResultRequest;
import com.aurd.Student.Model.Response.TestSeries.Result_Response;
import com.aurd.Student.Repository.*;

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


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public  Result_Response getResult(GetQuizResultRequest request){


        ArrayList<SaveResultModel> arrayList1=
                (ArrayList<SaveResultModel>) resultRepository.list("quiz_id=?1 and" +
                                " inst_id =?2 order by marks_obtained DESC",
                       request.getQuizID(),request.getInstID());

//
//        arrayList1.forEach(saveResultModel -> {
//
//        });


        ArrayList<TopicAnalysisModel> topicList =new ArrayList<>();
        SaveResultModel saveResultModel  =
             resultRepository.find("quiz_id=?1 and stud_id=?2 and inst_id =?3",
                     request.getQuizID(),request.getStudID(),request.getInstID()).firstResult();

        ArrayList<Quiz_Submit_Model> quizSubmitModels=quizSubmitRepository.getStudentPracticeTestResult(Math.toIntExact(request.getInstID()),request.getStudID(),request.getQuizID());

        ArrayList<SubjectModel> subjects=new ArrayList<>();
        ArrayList<Quiz_Question_Map_Model> questions=questionRepository.getQuestionID(request.getQuizID());

        for (Quiz_Question_Map_Model question: questions) {
            boolean exists=false;

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





        for (SubjectModel subjectModel:subjects) {
            TopicAnalysisModel analysisModel = new TopicAnalysisModel();
            int subjectTotalMarks=0;
            int subjectObtainedMarks=0;
            int totalQuestion=0;
            for (Quiz_Question_Map_Model model:questions) {
                if(model.getSubject_id()==subjectModel.getId())
                {
                    subjectTotalMarks+=model.getMarks();
                    totalQuestion+=1;
                }
            }
            analysisModel.setTotalMarks(subjectTotalMarks);

            for (Quiz_Submit_Model quiz_submit_model:quizSubmitModels) {
                if(quiz_submit_model.getSubjectId().equals(String.valueOf(subjectModel.getId())))
                {
                    subjectObtainedMarks+=quiz_submit_model.getMarks_ob();
                }
            }
            analysisModel.setSubject(subjectModel.getSubject());
            analysisModel.setMarksObtained(subjectObtainedMarks);
            analysisModel.setPercent(String.valueOf((subjectObtainedMarks/subjectTotalMarks)*100));
            analysisModel.setQuestions(totalQuestion);
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
         response.setResultList(arrayList1);
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
