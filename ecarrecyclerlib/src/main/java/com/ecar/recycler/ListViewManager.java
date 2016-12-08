package com.ecar.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import com.ecar.recycler.adapter.Action;
import com.ecar.recycler.adapter.RecyclerAdapter;

/*************************************
 * 功能： listview管理器
 * 创建者： kim_tony
 * 创建日期：2016/12/8
 * 版权所有：深圳市亿车科技有限公司
 *************************************/

public class ListViewManager<T> {
    public RefreshRecyclerView refreshRecyclerView;
    public RecyclerAdapter<T> recyclerAdapter;
    public View header;
    public View footer;
    public View emptyView;//空页面


    Datas datas;//获取数据后的动作
    public int page = 1;// 页数
    public int size = 10;//每页数量


    /****************************************
     * 方法描述： 狗仔方法
     *
     * @param
     * @return
     ****************************************/
    public ListViewManager(Context context, final RefreshRecyclerView refreshRecyclerView, final RecyclerAdapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
        this.refreshRecyclerView = refreshRecyclerView;
        this.refreshRecyclerView.setAdapter(this.recyclerAdapter);
        refreshRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        refreshRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.page = 1;
        datas = new Datas() {
            @Override
            public void onRefresh(List list) {
                if(list==null||list.isEmpty()){
                   if(page==1){
                       recyclerAdapter.clear();
                   }
                }else{
                    recyclerAdapter.clear();
                    recyclerAdapter.addAll(list);
                }
                refreshRecyclerView.dismissSwipeRefresh();

                page = 1;

            }

            @Override
            public void onGetMore(List list) {
                recyclerAdapter.addAll(list);
                if (list == null || list.isEmpty() || list.size() < size) {
                    refreshRecyclerView.showNoMore();
                }
            }
        };
    }

    public void setDatas(Datas datas) {
        this.datas = datas;
    }

    /****************************************
     * 方法描述： 没数据时显示的view
     *
     * @param
     * @return
     ****************************************/
    public ListViewManager setEmptyView(View view) {
        refreshRecyclerView.setEmptyView(view);
        return this;
    }

    /****************************************
     * 方法描述：设置header
     *
     * @param
     * @return
     ****************************************/
    public void setHeader(View view) {
        recyclerAdapter.setHeader(view);
    }

    /****************************************
     * 方法描述：设置booter
     *
     * @param
     * @return
     ****************************************/
    public void setFooter(View view) {
        recyclerAdapter.setFooter(view);
    }

    /****************************************
     * 方法描述：设置圈圈的颜色
     *
     * @param
     * @return
     ****************************************/
    public void setTopColor(int... colors) {
        refreshRecyclerView.setSwipeRefreshColors(colors);
    }


    public void initData(List list) {
        refreshRecyclerView.showSwipeRefresh();
        getData(true, list);
    }


    //下拉刷新
    public void setTopRefresh(Action action) {
        refreshRecyclerView.setRefreshAction(action);
    }

    //上拉加载
    public void setMoreData(Action action) {
        refreshRecyclerView.setLoadMoreAction(action);
    }

    /****************************************
     方法描述：是否允许下拉刷新
     @param
     @return
     ****************************************/
    public void setEnableRefresh(boolean enable) {
        refreshRecyclerView.getSwipeRefreshLayout().setEnabled(enable);
    }


    /****************************************
     * 方法描述： 获取数据
     *
     * @param isRefresh 是否刷新  list需要刷新的数据
     * @return
     ****************************************/
    public void getData(final boolean isRefresh, final List list) {

        refreshRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRefresh) {
                    datas.onRefresh(list);
                } else {
                    datas.onGetMore(list);
                }
            }
        }, 1500);
    }


    public interface Datas {
        void onRefresh(List list);   //下拉刷新

        void onGetMore(List list); //上拉加载
    }

    /****************************************
     方法描述： 是否允许下拉加载
     @param    enableMore true 允许
     @return
     ****************************************/
    public void setEnableMore(boolean enableMore){
        recyclerAdapter.hideStatusView(!enableMore);
    }

}
