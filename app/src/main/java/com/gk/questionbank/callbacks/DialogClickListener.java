package com.gk.questionbank.callbacks;

import com.gk.questionbank.enums.RVClickType;

public interface DialogClickListener<T> {
    public void onDialogClick(T model, RVClickType clickType);
}
