package com.demo.lucar.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.demo.lucar.R;
import com.demo.lucar.adapter.SearchAdapter;
import com.demo.lucar.bean.SearchBean;
import com.demo.lucar.dao.SearchDao;
import com.demo.lucar.inter.OnRvItemClickListener;
import com.demo.lucar.utils.Api;
import com.demo.lucar.utils.GsonUtil;
import com.demo.lucar.utils.HttpUtils;
import com.demo.lucar.utils.KeyboardUtil;
import com.demo.lucar.utils.ToastUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchFragment extends Fragment {

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.rv)
    RecyclerView mRv;
    Unbinder unbinder;
    private SearchAdapter mAdapter;
    private ProgressDialog mProgressdialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        mProgressdialog = new ProgressDialog(getContext());
        mProgressdialog.setMessage("loading");
        mProgressdialog.setCancelable(false);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SearchAdapter();
        mAdapter.setOnRvItemClickListener(new OnRvItemClickListener<SearchBean>() {
            @Override
            public void onRvItemClick(View view, int position, SearchBean bean) {
                if (view.getId() == R.id.iv_love) {
                    showLoveDialog(bean);
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

    private void showLoveDialog(SearchBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add " + bean.name + " To Favourite ?");
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SearchBean query = SearchDao.query(bean.id);
                if (query == null) {
                    SearchDao.add(bean);
                }
                ToastUtils.showMes(getContext(), "This Cat has been add in Favourite...");
            }
        });
        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_search)
    public void onViewClicked() {
        showProgressdialog();
        KeyboardUtil.hide(getActivity(), mRv);
        String name = mEtSearch.getText().toString().trim();
        HashMap<String, String> param = new HashMap<>();
        param.put("q", name);
        HttpUtils.getInstance(getContext()).get(Api.URL_SEARCH, param, new HttpUtils.MyCallback() {
            @Override
            public void success(String res) throws IOException {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissProgressdialog();
                            List<SearchBean> objectList = GsonUtil.getObjectList(res, SearchBean.class);
                            ToastUtils.showLog(objectList.size() + "Requiring Data : " + res);
                            mAdapter.setBeans(objectList);
                            if (objectList == null || objectList.size() == 0) {
                                ToastUtils.showMes(getContext(), "No Result! Please enter correct Name!");
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(IOException e) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissProgressdialog();
                        }
                    });
                }
            }
        });
    }


    public void showProgressdialog() {
        mProgressdialog.show();
    }

    public void dismissProgressdialog() {
        if (mProgressdialog.isShowing())
            mProgressdialog.dismiss();
    }
}
