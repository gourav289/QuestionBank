package com.gk.questionbank.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.gk.questionbank.R;
import com.gk.questionbank.activities.HomeActivity;
import com.gk.questionbank.adapters.QuestionsAdapter;
import com.gk.questionbank.base.BaseFragment;
import com.gk.questionbank.callbacks.DialogClickListener;
import com.gk.questionbank.callbacks.FragmentCallBacks;
import com.gk.questionbank.callbacks.RecyclerListener;
import com.gk.questionbank.enums.FragmentOperation;
import com.gk.questionbank.enums.RVClickType;
import com.gk.questionbank.utils.CommonDialogs;
import com.gk.questionbank.view_model.Questions;
import com.gk.questionbank.view_model.QuestionsViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements RecyclerListener<Questions>, DialogClickListener<Questions> {

//    @BindView(R.id.rv_questions)
    RecyclerView rvQuestions;
//    @BindView(R.id.ed_search)
    EditText edSearch;
    FloatingActionButton fabAdd;
    FragmentCallBacks mFragmentCallBacks;
    private View view;
    private QuestionsAdapter questionsAdapter;

    private Questions questionsData;
    private QuestionsViewModel questionsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionsViewModel= ViewModelProviders.of(getActivity()).get(QuestionsViewModel.class);
        if(savedInstanceState==null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                questionsData = bundle.getParcelable(HomeActivity.QUESTIONS_DATA);
                if (questionsData != null) {
                    questionsViewModel.insert(questionsData);
                    Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }

    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getIds();
        setListeners();

        questionsAdapter=new QuestionsAdapter(getActivity(),this);
        rvQuestions.setAdapter(questionsAdapter);
        rvQuestions.setLayoutManager(new LinearLayoutManager(getActivity()));

//        questionsViewModel.getAllQuestions().observe(getActivity(), new Observer<List<Questions>>() {
//            @Override
//            public void onChanged(@Nullable List<Questions> questions) {
//                questionsAdapter.notifyData(questions);
//            }
//        });
        String searchKey="%"+edSearch.getText().toString().trim()+"%";
        getFilteredData(searchKey);

    }

    public void setFragmentListener(FragmentCallBacks mFragmentCallBacks){
        this.mFragmentCallBacks=mFragmentCallBacks;
    }

    private void getIds(){
        rvQuestions=view.findViewById(R.id.rv_questions);
        fabAdd=view.findViewById(R.id.fab_add);
        edSearch=view.findViewById(R.id.ed_search);
    }

    private void setListeners(){
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddClick();
            }
        });

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionsAdapter.notifySearchedText(edSearch.getText().toString().trim());
                String searchKey="%"+edSearch.getText().toString().trim()+"%";
                getFilteredData(searchKey);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getFilteredData(String text){
        questionsViewModel.getFilteredList(text).observe(getActivity(), new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable List<Questions> questions) {
                questionsAdapter.notifyData(questions);
            }
        });
    }

//    @OnClick(R.id.fab_add)
    public void onAddClick(){
        Fragment mFragment=new AddFragment();
        if (mFragmentCallBacks!=null)
            mFragmentCallBacks.onNewFragment(FragmentOperation.REPLACE,mFragment,true);
    }


    @Override
    public void onClick(Questions model, RVClickType clickType) {
        if(clickType==RVClickType.CLICK) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(HomeActivity.QUESTIONS_DATA, model);
            Fragment mFragment = new QuestionDetailsFragment();
            mFragment.setArguments(bundle);
            if (mFragmentCallBacks != null)
                mFragmentCallBacks.onNewFragment(FragmentOperation.REPLACE, mFragment, true);
        }else if(clickType==RVClickType.EDIT){

        }else if (clickType==RVClickType.DELETE){
            CommonDialogs.showDialog(getActivity(),getString(R.string.delete_message),clickType,model,this);
        }
    }

    @Override
    public void onDialogClick(Questions model, RVClickType clickType) {
        if(clickType==RVClickType.DELETE)
            questionsViewModel.delete(model);
    }
}
