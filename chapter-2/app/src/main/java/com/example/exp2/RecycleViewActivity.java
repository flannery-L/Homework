package com.example.exp2;

import androidx.appcompat.app.AppCompatActivity;
import com.example.exp2.MyAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.exp2.TestData;
import com.example.exp2.TestDataSet;
import com.example.exp2.LinearItemDecoration;
import com.example.exp2.RecycleViewActivity;
public class RecycleViewActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener{
    private static final String TAG="TAG";
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initView();
    }
    private void initView(){
        recyclerView=findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(TestDataSet.getData());
        myAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(myAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
    }
    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(RecycleViewActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        myAdapter.addData(position + 1, new TestData("新增头条", "0w"));
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(RecycleViewActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        myAdapter.removeData(position);
    }
}

