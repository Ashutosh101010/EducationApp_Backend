package com.aurd.Student.Repository;

import com.aurd.Student.Model.BeanClass.BookMarkEntity;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class QuizRepository implements PanacheRepository<QuizModel> {

    public ArrayList getQuizzes(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = new ArrayList();

        try {
            System.out.println(new Gson().toJson(request));


            if(request.getFilter().isEmpty()){
              arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2",
                        request.getInst_id(),request.getType()).list();

              return arrayList;

            }else if(request.getFilter().equals("free")){
                arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2 and price=?3",
                        request.getInst_id(),request.getType(),0).list();


                return  arrayList;
            }else if(request.getFilter().equals("paid")){


                String string = "SELECT quiz_id,type,title,discription," + "pic,price ,inst_id FROM `quiz_master` " +
                        "WHERE price>0 and type =? and inst_id = ?";

                Query query = getEntityManager().createNativeQuery(string);
                query.setParameter(1,request.getType());
                query.setParameter(2,request.getInst_id());


                ArrayList<Object[]> objectList = (ArrayList<Object[]>) query.getResultList();
                ArrayList<QuizModel> tempList = new ArrayList<>();


                objectList.forEach(objects -> {

                            QuizModel quizModel = new QuizModel();
                            quizModel.setQuiz_id(Integer.parseInt(objects[0].toString()));
                            quizModel.setType(objects[1].toString());
                            quizModel.setTitle(objects[2].toString());
                            quizModel.setDiscription(objects[3].toString());
                            quizModel.setPic(objects[4].toString());
                            quizModel.setPrice(Integer.parseInt(objects[5].toString()));

                            quizModel.setInst_id(Long.parseLong(objects[6].toString()));


                            tempList.add(quizModel);
                        });



                return  tempList;

            }

            return  arrayList;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
        }


    }

    public ArrayList getTestSeries(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = new ArrayList();

        List<QuizModel> list =  find("inst_id=?1 and type =?2",
                request.getInst_id(),request.getType()).list();

        arrayList.addAll(list);

        return  arrayList;

    }

    public ArrayList getMonthlyTest(long instID,String type){

        ArrayList<QuizModel> arrayList = new ArrayList();

        List<QuizModel> list =  find("inst_id=?1 and type =?2",
               instID,type).list();

        arrayList.addAll(list);

        return  arrayList;

    }



    public ArrayList getAllUpdates(long instID){
        ArrayList<QuizModel> arrayList = (ArrayList<QuizModel>) list("inst_id",instID);
        return  arrayList;
    }
}
