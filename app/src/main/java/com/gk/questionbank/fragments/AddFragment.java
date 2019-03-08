package com.gk.questionbank.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gk.questionbank.R;
import com.gk.questionbank.activities.HomeActivity;
import com.gk.questionbank.base.BaseFragment;
import com.gk.questionbank.callbacks.FragmentCallBacks;
import com.gk.questionbank.enums.FragmentOperation;
import com.gk.questionbank.view_model.Questions;

public class AddFragment extends BaseFragment {
    private FragmentCallBacks mFragmentCallBacks;

    private EditText edQuestion;
    private EditText edAnswer;
    private Button btnSave;

    private View view;
    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getIds();
        setListeners();
    }

    public void setFragmentListener(FragmentCallBacks mFragmentCallBacks){
        this.mFragmentCallBacks=mFragmentCallBacks;
    }

    private void getIds(){
        edQuestion=view.findViewById(R.id.ed_question);
        edAnswer=view.findViewById(R.id.ed_answer);
        btnSave=view.findViewById(R.id.btn_save);
    }

    private void setListeners(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Questions questions=getData();
                if(getData()!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(HomeActivity.QUESTIONS_DATA,questions );
                    Fragment mFragment = new HomeFragment();
                    mFragment.setArguments(bundle);
                    if (mFragmentCallBacks != null)
                        mFragmentCallBacks.onNewFragment(FragmentOperation.REPLACE, mFragment, false);

                }
            }
        });
    }

    private Questions getData(){
        if(TextUtils.isEmpty(edQuestion.getText().toString().trim())){
            Toast.makeText(getActivity(), "Enter Question", Toast.LENGTH_SHORT).show();
            return null;
        } else if (TextUtils.isEmpty(edAnswer.getText().toString().trim())) {

            Toast.makeText(getActivity(), "Enter Answer", Toast.LENGTH_SHORT).show();
            return null;
        }else{
            Questions questions=new Questions(edQuestion.getText().toString().trim(),edAnswer.getText().toString().trim());
            return questions;
        }
    }

}
