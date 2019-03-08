package com.gk.questionbank.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gk.questionbank.R;


public abstract class BaseActivtiy extends AppCompatActivity {

//    private Unbinder unbinder;
    public abstract int setLayout();
    //Commit
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
//        unbinder=ButterKnife.bind(this);
    }


    public void addFragmentHome(Fragment mFragment,boolean backStack){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_home,mFragment);
        if(backStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void replaceFragmentHome(Fragment mFragment,boolean backStack){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_home,mFragment);
        if(backStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(unbinder!=null)
//        unbinder.unbind();
    }
}
