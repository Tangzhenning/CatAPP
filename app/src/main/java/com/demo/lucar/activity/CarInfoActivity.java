package com.demo.lucar.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.lucar.R;
import com.demo.lucar.bean.ImageBean;
import com.demo.lucar.bean.SearchBean;
import com.demo.lucar.dao.SearchDao;
import com.demo.lucar.utils.Api;
import com.demo.lucar.utils.GsonUtil;
import com.demo.lucar.utils.HttpUtils;
import com.demo.lucar.utils.ToastUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CarInfoActivity extends AppCompatActivity {


    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_temperament)
    TextView mTvTemperament;
    @BindView(R.id.tv_origin)
    TextView mTvOrigin;
    @BindView(R.id.tv_life_span)
    TextView mTvLifeSpan;
    @BindView(R.id.tv_dog_friendly)
    TextView mTvDogFriendly;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_wikipedia)
    TextView mTvWikipedia;
    @BindView(R.id.tv_wikipedia_input)
    TextView mTvWikipediaInput;
    @BindView(R.id.iv_love)
    ImageView mIvLove;

    private ProgressDialog mProgressdialog;
    private SearchBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        ButterKnife.bind(this);
        mProgressdialog = new ProgressDialog(this);
        mProgressdialog.setMessage("loading");
        mProgressdialog.setCancelable(false);
        showProgressdialog();
        mBean = (SearchBean) getIntent().getSerializableExtra("bean");

        HashMap<String, String> param = new HashMap<>();
        param.put("breed_ids", mBean.id);
        HttpUtils.getInstance(this).get(Api.URL_GETIMAGE, param, new HttpUtils.MyCallback() {
            @Override
            public void success(String res) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressdialog();
                        mTvId.setText("id：" + mBean.id);
                        mTvWikipediaInput.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        if (mBean != null) {
                            mTvName.setText("name：" + mBean.name);
                            mTvTemperament.setText("temperament：" + mBean.temperament);
                            mTvOrigin.setText("origin：" + mBean.origin);
                            mTvLifeSpan.setText("life_span：" + mBean.life_span);
                            mTvDogFriendly.setText("dog_friendly：" + mBean.dog_friendly);
                            mTvDescription.setText("description：" + mBean.description);
                            mTvWikipedia.setText("wikipedia_url：");
                            mTvWikipediaInput.setText(mBean.wikipedia_url);
                            if (SearchDao.query(mBean.id) == null) {
                                Glide.with(CarInfoActivity.this).load(R.mipmap.love).error(R.mipmap.error).into(mIvLove);
                            } else {
                                Glide.with(CarInfoActivity.this).load(R.mipmap.love_yes).error(R.mipmap.error).into(mIvLove);
                            }
                        }
                        List<ImageBean> objectList = GsonUtil.getObjectList(res, ImageBean.class);
                        if (objectList != null && objectList.size() > 0) {
                            ImageBean imageBean = objectList.get(0);
                            Glide.with(CarInfoActivity.this).load(imageBean.url).error(R.mipmap.error).into(mIvIcon);
                        }
                    }
                });

            }

            @Override
            public void failed(IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressdialog();
                        ToastUtils.showMes(CarInfoActivity.this, "error");
                        finish();
                    }
                });

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

    @OnClick(R.id.iv_love)
    public void onViewClicked() {
        SearchBean query = SearchDao.query(mBean.id);
        if (query == null) {
            showLoveDialog(mBean);
        } else {
            showQxLoveDialog(query);
        }
    }

    private void showLoveDialog(SearchBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add " + bean.name + " To Favourite ?");
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SearchDao.add(bean);
                ToastUtils.showMes(CarInfoActivity.this, "This Cat has been add in Favourite...");
                Glide.with(CarInfoActivity.this).load(R.mipmap.love_yes).error(R.mipmap.error).into(mIvLove);
            }
        });
        builder.show();
    }

    private void showQxLoveDialog(SearchBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                ToastUtils.showMes(CarInfoActivity.this, "This Cat has been deleted...");
                Glide.with(CarInfoActivity.this).load(R.mipmap.love).error(R.mipmap.error).into(mIvLove);
            }
        });
        builder.show();
    }

}
