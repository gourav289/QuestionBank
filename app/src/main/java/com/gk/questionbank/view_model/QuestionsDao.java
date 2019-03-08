package com.gk.questionbank.view_model;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionsDao {

    @Insert
    void insert(Questions questions);

    @Query("SELECT * FROM questions")
    LiveData<List<Questions>> getAllQuestions();

    @Query("SELECT * FROM questions WHERE question LIKE :q")
    LiveData<List<Questions>> getFilteredQuestions(String q);

}
