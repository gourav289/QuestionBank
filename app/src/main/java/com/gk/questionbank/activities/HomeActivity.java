package com.gk.questionbank.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.gk.questionbank.R;
import com.gk.questionbank.base.BaseActivtiy;
import com.gk.questionbank.callbacks.FragmentCallBacks;
import com.gk.questionbank.enums.FragmentOperation;
import com.gk.questionbank.fragments.AddFragment;
import com.gk.questionbank.fragments.HomeFragment;

public class HomeActivity extends BaseActivtiy implements FragmentCallBacks {

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeFragment mFragment=new HomeFragment();
        addFragmentHome(mFragment,false);
        mFragment.setFragmentListener(this);
    }

    @Override
    public void onNewFragment(FragmentOperation operation, Fragment fragment) {
        if(operation==FragmentOperation.ADD)
            addFragmentHome(fragment,true);
        else if(operation==FragmentOperation.REPLACE)
            replaceFragmentHome(fragment,true);

        if(fragment instanceof AddFragment)
            ((AddFragment)fragment).setFragmentListener(this);
    }
}
