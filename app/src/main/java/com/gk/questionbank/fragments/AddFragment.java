package com.gk.questionbank.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gk.questionbank.R;
import com.gk.questionbank.base.BaseFragment;
import com.gk.questionbank.callbacks.FragmentCallBacks;

public class AddFragment extends BaseFragment {
    private FragmentCallBacks mFragmentCallBacks;
    @Override
    public View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add,container,false);
    }

    public void setFragmentListener(FragmentCallBacks mFragmentCallBacks){
        this.mFragmentCallBacks=mFragmentCallBacks;
    }
}
