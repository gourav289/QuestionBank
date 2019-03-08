package com.gk.questionbank.callbacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gk.questionbank.enums.FragmentOperation;

public interface FragmentCallBacks {
    public void onNewFragment(FragmentOperation operation, Fragment fragment,boolean addToStack);


}
