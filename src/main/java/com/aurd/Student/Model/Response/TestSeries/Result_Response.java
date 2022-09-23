package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.BeanClass.ResultEntity;
import com.aurd.Student.Model.BeanClass.SolutionEntity;
import com.aurd.Student.Model.BeanClass.TopicAnalysisModel;
import com.aurd.Student.Model.Entity.LeaderBoardModel;
import com.aurd.Student.Model.Entity.SaveResultModel;

import java.util.ArrayList;
import java.util.List;

public class Result_Response {
    String message;
    int errorCode;
    boolean status;
    SaveResultModel result;
    ArrayList<SolutionEntity> solutions = new ArrayList<>();
    ArrayList<LeaderBoardModel> resultList = new ArrayList<>();

 List<TopicAnalysisModel> topics = new ArrayList<>();

    public List<TopicAnalysisModel> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicAnalysisModel> topics) {
        this.topics = topics;
    }

    public void setTopics(ArrayList<TopicAnalysisModel> topics) {
        this.topics = topics;
    }

    public ArrayList<LeaderBoardModel> getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList<LeaderBoardModel> resultList) {
        this.resultList = resultList;
    }

    public ArrayList<SolutionEntity> getSolutions() {
        return solutions;
    }

    public void setSolutions(ArrayList<SolutionEntity> solutions) {
        this.solutions = solutions;
    }

    public SaveResultModel getResult() {
        return result;
    }

    public void setResult(SaveResultModel result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result_Response{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", status=" + status +
                ", result=" + result +
                ", solutions=" + solutions +
                ", resultList=" + resultList +
                ", topics=" + topics +
                '}';
    }
}
