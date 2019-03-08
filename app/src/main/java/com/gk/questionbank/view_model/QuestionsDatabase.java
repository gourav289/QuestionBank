package com.gk.questionbank.view_model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Questions.class},version = 1)
public abstract class QuestionsDatabase extends RoomDatabase {

    public abstract QuestionsDao questionsDao();

    private static volatile QuestionsDatabase questionsDatabaseInstance;

    static QuestionsDatabase getInstance(Context context){
        if(questionsDatabaseInstance==null){
            synchronized (QuestionsDatabase.class){
                if(questionsDatabaseInstance==null){
                    questionsDatabaseInstance= Room.databaseBuilder(context.getApplicationContext(),QuestionsDatabase.class,"question_bank_db").build();
                }
            }
        }
        return questionsDatabaseInstance;
    }
}
