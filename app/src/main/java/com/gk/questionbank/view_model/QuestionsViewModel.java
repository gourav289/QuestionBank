package com.gk.questionbank.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gk.questionbank.async_tasks.DeleteAsyncTask;
import com.gk.questionbank.async_tasks.InsertAsyncTask;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel {

    private QuestionsDao questionsDao;
    private QuestionsDatabase questionsDB;
    public QuestionsViewModel(@NonNull Application application) {
        super(application);
        questionsDB=QuestionsDatabase.getInstance(application);
        questionsDao=questionsDB.questionsDao();

    }


    public void insert(Questions questions){
        new InsertAsyncTask(questionsDao).execute(questions);
    }

    public void delete(Questions questions){
        new DeleteAsyncTask(questionsDao).execute(questions);
    }

    public LiveData<List<Questions>> getAllQuestions(){
        return questionsDao.getAllQuestions();
    }

    public LiveData<List<Questions>> getFilteredList(String keyword){
        return questionsDao.getFilteredQuestions(keyword);
    }
}
