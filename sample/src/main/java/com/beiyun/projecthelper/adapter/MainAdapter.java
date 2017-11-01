package com.beiyun.projecthelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.beiyun.library.util.Apps;
import com.beiyun.projecthelper.R;

import java.util.ArrayList;

/**
 * Created by beiyun on 2017/11/1.
 * 
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {


    private ArrayList<String> mData;

    public MainAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = Apps.getLayoutInflater().inflate(R.layout.adapter_main, null, true);
        itemView.setLayoutParams(new FrameLayout.LayoutParams(-1,-2));
        return new MainHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        if(mData == null) return;
        final String data = mData.get(position);
        holder.text.setText(data);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemClickListener != null){
                    mItemClickListener.onItemClick(position,data);
                }
            }
        });

    }

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position, String data);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MainHolder extends RecyclerView.ViewHolder{

        TextView text;

        public MainHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
