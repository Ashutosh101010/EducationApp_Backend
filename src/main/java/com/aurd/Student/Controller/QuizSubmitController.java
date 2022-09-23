package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.ResultEntity;
import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Path("/submitQuiz")
public class QuizSubmitController {


    @Inject
    Quiz_Submit_Repository repository;



    @Inject
    Get_QuestionID_Repository questionID_repository;

    @Inject
    QuizQuestionRepository quizQuestionRepository;

    @Inject
    QuizRepository quizRepository;

    @Inject
    ResultRepository resultRepository;

    @Inject StudentRepository studentRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)



    public Result_Response submitQuiz(QuizSubmitRequest request){
        System.out.println(request);
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

            Integer quizId = request.getQuizId().intValue();
            Long studentId = request.getStudentId();
            Long instId = request.getInstId();

            ArrayList<Quiz_Question_Map_Model> quizQuestionIDList  =
                    (ArrayList<Quiz_Question_Map_Model>) questionID_repository.
                            getQuestion(quizId);

            System.out.println(quizQuestionIDList.size());
            ArrayList<SubjectModel> subjectList=new ArrayList<>();
            for(Quiz_Question_Map_Model model :quizQuestionIDList){

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


            QuizModel quizModel = quizRepository.find("quiz_id",
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
                Quiz_Submit_Model quiz_submit_model=request.getArrayList().get(i);
                Quiz_Question_Model  quizQuestion = quizQuestionRepository.
                        getQuestions(quiz_submit_model.getQues_id());

                if(quiz_submit_model.getAns().equals(quizQuestion.getAnswer()))
                {
                    correctAns++;
                    marksObtained = marksObtained + quizModel.getMarks_per_ques();


                }
                else{
                    wrongAns++;
                    if(quizModel.getNegative_marking()!=null && !quizModel.getNegative_marking().equals("0"))
                    {
                       double num= Integer.parseInt(quizModel.getNegative_marking().split("/")[0]);
                       double den= Integer.parseInt(quizModel.getNegative_marking().split("/")[1]);

                       marksObtained=marksObtained-((num/den)*quizModel.getMarks_per_ques());

                    }


                }

            }


            System.out.println("Marks Obtained"+marksObtained);
            skippedAns=quizModel.getTotal_ques()-request.getArrayList().size();



            System.out.println("correct ans============="+correctAns);
            System.out.println("wrong ans         "+wrongAns);




            SaveResultModel resultModel = new SaveResultModel();
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


            if(quizModel.getCutoff()!=null){
                resultModel.setCut_off(quizModel.getCutoff());

                if(marksObtained>quizModel.getCutoff()){
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
            response.setResult(resultModel);
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
    public void insertData(SaveResultModel resultModel){
        resultRepository.persistAndFlush(resultModel);
    }
}
