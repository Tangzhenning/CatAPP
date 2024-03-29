package com.demo.lucar.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.lucar.R;
import com.demo.lucar.bean.SearchBean;
import com.demo.lucar.inter.OnRvItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.FLViewHolder> {

    private List<SearchBean> mBeans;
    private OnRvItemClickListener mOnRvItemClickListener;


    public void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        mOnRvItemClickListener = onRvItemClickListener;
    }

    public List<SearchBean> getBeans() {
        return mBeans;
    }

    public void setBeans(List<SearchBean> beans) {
        mBeans = beans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FLViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new FLViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FLViewHolder flViewHolder, int position) {
        flViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mBeans == null ? 0 : mBeans.size();
    }


    class FLViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.iv_love)
        ImageView mIvLove;
        private int mPosition;
        private SearchBean mSearchBean;

        public FLViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (mOnRvItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnRvItemClickListener.onRvItemClick(itemView, mPosition, mSearchBean);
                    }
                });
                mIvLove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnRvItemClickListener.onRvItemClick(mIvLove, mPosition, mSearchBean);
                    }
                });
            }
        }

        public void setData(int position) {
            mPosition = position;
            mSearchBean = mBeans.get(position);
            mTvName.setText(mSearchBean.name);
//            Glide.with(itemView.getContext()).load(mSearchBean.wikipedia_url).error(R.mipmap.error).into(mIvIcon);
        }

    }
}
