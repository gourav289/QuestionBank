package com.gk.questionbank.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gk.questionbank.R;
import com.gk.questionbank.activities.HomeActivity;
import com.gk.questionbank.base.BaseFragment;
import com.gk.questionbank.callbacks.FragmentCallBacks;
import com.gk.questionbank.view_model.Questions;
import com.gk.questionbank.view_model.QuestionsViewModel;

public class QuestionDetailsFragment extends BaseFragment {
    private View view;
    private Questions questions;
    private TextView txtQuestion;
    private TextView txtAnswer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            questions=bundle.getParcelable(HomeActivity.QUESTIONS_DATA);
        }

    }

    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_question_details,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getIds();
        setData();
    }

    private void getIds(){
        txtQuestion=view.findViewById(R.id.txt_question);
        txtAnswer=view.findViewById(R.id.txt_answer);
    }

    private void setData(){
        if (questions!=null){
            txtQuestion.setText(questions.getQuestion());
            txtAnswer.setText(questions.getAnswer());
        }
    }
}
