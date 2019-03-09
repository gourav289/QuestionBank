package com.gk.questionbank.callbacks;

import com.gk.questionbank.enums.RVClickType;

public interface RecyclerListener<T> {
    public void onClick(T model, RVClickType clickType);
}
