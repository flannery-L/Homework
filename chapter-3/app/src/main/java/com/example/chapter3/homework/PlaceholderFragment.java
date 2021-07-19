package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View v;
    private LottieAnimationView animationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        v=inflater.inflate(R.layout.fragment_placeholder, container, false);
        initView();
        Log.i("oncreateview","success");
        //return inflater.inflate(R.layout.fragment_placeholder, container, false);
        return v;
    }
    private void initView(){
        animationView=v.findViewById(R.id.animation_view);
        animationView.playAnimation();
        recyclerView=v.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        //layoutManager=new LinearLayoutManager(this);
        layoutManager=new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter=new MyAdapter(getData());

       // myAdapter=new MyAdapter(data);
        recyclerView.setAdapter(myAdapter);


        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLACK);
       recyclerView.addItemDecoration(itemDecoration);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        //动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
        recyclerView.setAlpha(0);
        Log.i("initview","execute");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator adptAnimator = ObjectAnimator.ofFloat(recyclerView,
                        "alpha", 0, 1f);

                ObjectAnimator adptAnimator2 = ObjectAnimator.ofFloat(animationView,
                        "alpha", 1, 0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(adptAnimator,adptAnimator2);
                animatorSet.start();
                animationView.pauseAnimation();
            }
        }, 5000);
        Log.i("onactivitycreated","success");
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        @NonNull
        private List<TestData> mDataset = new ArrayList<>();

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new MyAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
            holder.onBind(position, mDataset.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public MyAdapter(List<TestData> myDataset) {
            mDataset=myDataset;
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

        class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView name;
            private TextView content;
            private TextView time;
            private ImageView image;
            private View contentView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                contentView=itemView;
                name=itemView.findViewById(R.id.name);
                image=itemView.findViewById(R.id.friendimage);
                content=itemView.findViewById(R.id.content);
                time=itemView.findViewById(R.id.time);
            }
            public void onBind(int position,TestData testData){

                name.setText(testData.getName());
                content.setText(testData.content);
                time.setText(testData.time);
                image.setImageResource(testData.getImageID());
            }

        }
    }


        public List<TestData> getData() {
            List<TestData> result = new ArrayList();
            result.add(new TestData("Fire", R.drawable.fire,"在吗？","刚刚"));
            result.add(new TestData("Girl", R.drawable.qiqi,"刚吃了一个大瓜","刚刚"));
            result.add(new TestData("BOY", R.drawable.titi,"...","21:05"));
            result.add(new TestData("西瓜", R.drawable.watermelon,"终于放假啦！","22:10"));
            result.add(new TestData("Apple", R.drawable.apple,"[动画表情]","15:30"));
            result.add(new TestData("三伏", R.drawable.fire,"三伏领取了你的红包","11:10"));
            result.add(new TestData("老虎", R.drawable.titi,"[图片]","9:00"));
            result.add(new TestData("暴雨", R.drawable.watermelon,"[动画表情]","昨天"));
            result.add(new TestData("国菜", R.drawable.apple,"好呀","昨天"));
            result.add(new TestData("猫", R.drawable.qiqi,"晚安！","昨天"));
            result.add(new TestData("IU真好看", R.drawable.qiqi,"拜拜","昨天"));
            return result;
        }

    }

