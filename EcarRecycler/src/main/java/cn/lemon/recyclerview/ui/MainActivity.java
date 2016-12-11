package cn.lemon.recyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecar.recycler.ListViewManager;
import com.ecar.recycler.RefreshRecyclerView;
import com.ecar.recycler.adapter.Action;

import java.util.ArrayList;
import java.util.Arrays;

import cn.lemon.recyclerview.R;
import cn.lemon.recyclerview.ui.bean.Consumption;


public class MainActivity extends AppCompatActivity {

    private RefreshRecyclerView mRecyclerView;
    private CardRecordAdapter mAdapter;
    private ListViewManager listViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ImageView)findViewById(R.id.imageView1)).setVisibility(View.GONE);

        mRecyclerView = (RefreshRecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CardRecordAdapter(this);
        listViewManager = new ListViewManager(this, mRecyclerView, mAdapter);
//        添加Header
        final TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        textView.setTextSize(16);
        textView.setGravity(Gravity.CENTER);
        textView.setText("我是header");
        listViewManager.setHeader(textView);

        //添加footer
        final TextView footer = new TextView(this);
        footer.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        footer.setTextSize(16);
        footer.setGravity(Gravity.CENTER);
        footer.setText("我是Footer");
        listViewManager.setFooter(footer);

        //顶部颜色
        listViewManager.setTopColor(getResources().getColor(R.color.blue_light),
                getResources().getColor(R.color.blue_light),
                getResources().getColor(R.color.grey));

        //下拉刷新
        listViewManager.setTopRefresh(new Action() {
            @Override
            public void onAction() {
                listViewManager.page=1;
                listViewManager.getData(true, new ArrayList());
            }
        });

        //上拉加载
        listViewManager.setMoreData(new Action() {
            @Override
            public void onAction() {
                listViewManager.getData(false, Arrays.asList(getVirtualData()));
                listViewManager.page++;
            }
        });

        //是否允许上拉加载
        listViewManager.setEnableMore(true);
        //是否允许下拉刷新
        listViewManager.setEnableRefresh(true);

        // 设置listview为空的时候显示的view
//        listViewManager.setEmptyView(findViewById(R.id.iv_empty));


        //初始化数据
        listViewManager.initData(Arrays.asList(getVirtualData()));


    }


    public Consumption[] getVirtualData() {
        if (listViewManager.page < 3) {
            return new Consumption[]{
                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
//                    new Consumption("Demo1", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼")
            };
        } else {
            return new Consumption[]{

            };
        }


    }
}
