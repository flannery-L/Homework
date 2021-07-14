package com.example.exp2;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.exp2.R;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    @NonNull
    private List<TestData> mDataset = new ArrayList<>();
    private IOnItemClickListener mItemClickListener;

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        holder.onBind(position,mDataset.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (mItemClickListener != null) {
                  mItemClickListener.onItemCLick(position, mDataset.get(position));
              }
          }
      }
        );
        holder.setOnLongClickListener(new View.OnLongClickListener()  {
            @Override
            public boolean onLongClick(View v){
                if(mItemClickListener!=null){
                    mItemClickListener.onItemLongCLick(position, mDataset.get(position));
                }
                return false;
            }

        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public interface IOnItemClickListener {

        void onItemCLick(int position, TestData data);

        void onItemLongCLick(int position, TestData data);
    }
    public MyAdapter(List<TestData> myDataset) {
        mDataset.addAll(myDataset);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void addData(int position, TestData data) {
        mDataset.add(position, data);
        notifyItemInserted(position);
        if (position != mDataset.size()) {
            //刷新改变位置item下方的所有Item的位置,避免索引错乱
            notifyItemRangeChanged(position, mDataset.size() - position);
        }
    }

    public void removeData(int position) {
        if (null != mDataset && mDataset.size() > position) {
            mDataset.remove(position);
            notifyItemRemoved(position);
            if (position != mDataset.size()) {
                //刷新改变位置item下方的所有Item的位置,避免索引错乱
                notifyItemRangeChanged(position, mDataset.size() - position);
            }
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvindex;
        private TextView tvtitle;
        private TextView tvhot;
        private ImageView fire;
        private View contentView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contentView=itemView;
            tvindex=itemView.findViewById(R.id.tv_index);
            tvtitle=itemView.findViewById(R.id.tv_title);
            tvhot=itemView.findViewById(R.id.tv_hot);
            fire=itemView.findViewById(R.id.imagefire);
        }
        public void onBind(int position,TestData testData){
            tvindex.setText(new StringBuilder().append(position).append(". ").toString());
            tvtitle.setText(testData.title);
            tvhot.setText(testData.hot);
            if(position<3){
                tvindex.setTextColor(Color.parseColor("#FFD700"));
            }
            else{
                tvindex.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        public void setOnClickListener(View.OnClickListener listener){
            if(listener!=null){
                contentView.setOnClickListener(listener);
            }
        }
        public void setOnLongClickListener(View.OnLongClickListener listener){
            if(listener!=null){
                contentView.setOnLongClickListener(listener);
            }
        }
    }
}
