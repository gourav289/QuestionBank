package com.gk.questionbank.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

//    private Unbinder unbinder;

    public abstract View setLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=setLayout(inflater, container, savedInstanceState);
//        unbinder= ButterKnife.bind(this,view);
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (unbinder!=null)
//            unbinder.unbind();
    }
}
