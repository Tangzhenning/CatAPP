package com.demo.lucar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.lucar.R;
import com.demo.lucar.adapter.LoveAdapter;
import com.demo.lucar.bean.SearchBean;
import com.demo.lucar.dao.SearchDao;
import com.demo.lucar.inter.OnRvItemClickListener;
import com.demo.lucar.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoveFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    Unbinder unbinder;
    private LoveAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_love, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new LoveAdapter();
        mAdapter.setOnRvItemClickListener(new OnRvItemClickListener<SearchBean>() {
            @Override
            public void onRvItemClick(View view, int position, SearchBean bean) {
                if (view.getId() == R.id.iv_love) {
                    showQxLoveDialog(bean);
                } else {
                    Intent intent = new Intent(getContext(), CarInfoActivity.class);
                    intent.putExtra("bean", bean);
                    startActivity(intent);
                }
            }

        });
        mRv.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mAdapter.setBeans(SearchDao.getAll());
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mAdapter.setBeans(SearchDao.getAll());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showQxLoveDialog(SearchBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete " + bean.name + " From Favourite ?");
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SearchDao.delete(bean);
                ToastUtils.showMes(getContext(), "This Cat has been deleted...");
                mAdapter.setBeans(SearchDao.getAll());
            }
        });
        builder.show();
    }
}
