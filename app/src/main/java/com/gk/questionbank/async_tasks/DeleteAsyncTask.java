package com.gk.questionbank.async_tasks;

import android.os.AsyncTask;

import com.gk.questionbank.view_model.Questions;
import com.gk.questionbank.view_model.QuestionsDao;

public class DeleteAsyncTask extends AsyncTask<Questions,Void,Void> {
    QuestionsDao questionsDao;

    public DeleteAsyncTask(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    protected Void doInBackground(Questions... questions) {
        questionsDao.delete(questions[0]);
        return null;
    }
}
