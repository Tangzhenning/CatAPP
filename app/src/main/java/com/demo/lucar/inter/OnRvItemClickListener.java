package com.demo.lucar.inter;

import android.view.View;

public interface OnRvItemClickListener<T> {

    void onRvItemClick(View view, int position, T obj);
}
