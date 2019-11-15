package com.demo.lucar.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.demo.lucar.R;
import com.demo.lucar.utils.HttpUtils;
import com.demo.lucar.utils.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.ll_love)
    LinearLayout mLlLove;

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragments = new ArrayList<>();
        mFragments.add(new SearchFragment());
        mFragments.add(new LoveFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
    }

    @OnClick({R.id.ll_search, R.id.ll_love})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                mVp.setCurrentItem(0);
                mLlSearch.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                mLlLove.setBackgroundColor(Color.TRANSPARENT);
//                HttpUtils.getInstance(this).get("https://api.thecatapi.com/v1/images/search?breed_ids=beng", new HttpUtils.MyCallback() {
//                    @Override
//                    public void success(String res) throws IOException {
//                        ToastUtils.showLog("success " + res);
//                    }
//
//                    @Override
//                    public void failed(IOException e) {
//                        ToastUtils.showLog("failed");
//                    }
//                });
                break;
            case R.id.ll_love:
                mVp.setCurrentItem(1);
                mLlSearch.setBackgroundColor(Color.TRANSPARENT);
                mLlLove.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
