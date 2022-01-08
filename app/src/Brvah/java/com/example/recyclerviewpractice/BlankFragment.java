package com.example.recyclerviewpractice;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.recyclerviewpractice.card.CardAdapter;
import com.example.recyclerviewpractice.card.CardData;
import com.example.recyclerviewpractice.expand.ContentExpandData;
import com.example.recyclerviewpractice.expand.ExpandNodeAdapter;
import com.example.recyclerviewpractice.expand.FootExpandData;
import com.example.recyclerviewpractice.expand.SectionExpandData;
import com.example.recyclerviewpractice.loadmore.LoadMoreAdapter;
import com.example.recyclerviewpractice.loadmore.LoadMoreView;
import com.example.recyclerviewpractice.loadmore.PageLoader;
import com.example.recyclerviewpractice.node.NodeAdapter;
import com.example.recyclerviewpractice.node.NodeData;
import com.example.recyclerviewpractice.group.multidata.GroupAdapter;
import com.example.recyclerviewpractice.group.multidata.GroupMultiData;
import com.example.recyclerviewpractice.group.variabeWH.SpannedGridLayoutManager;
import com.example.recyclerviewpractice.shortcut.ShortcutAdapter;
import com.example.recyclerviewpractice.shortcut.ShortcutData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 2019 constraintlayout practice
 * 依照nestScrollView 練習測試bravh adapter
 *
 * 引入動畫來加載item
 */
public class BlankFragment extends Fragment {
    private String TAG = BlankFragment.class.getSimpleName();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.own_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.tVtitle);
        textView.setText((BuildConfig.VERSION_NAME.split("-"))[1]);//get the suffix from version name
        initCardAdapter();
        initShortcutAdapter();
        initGroupAdapter();
        initNodeAdapter();
        initNodeExpandAdapter();
        initLoadMoreAdapter();
    }


    private List<CardData> initData(){
        List<CardData> data = new LinkedList<>();
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new CardData(id, "0000",null, Color.CYAN));
        }
        return data;
    }
    private List<ShortcutData> initShortcut(){
        List<ShortcutData> data = new LinkedList<>();
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new ShortcutData(id, "0000",null, null, Color.CYAN));
        }
        return data;
    }
    //make header merge into the ** view-model and ** multi-group
    private List<GroupMultiData> initAd(){
        List<GroupMultiData> data = new LinkedList<>();
        //add header
        data.add(new GroupMultiData("header", "111",null, GroupMultiData.HEADER));
        for (String id: Arrays.asList(getResources().getStringArray(R.array.id))){
            data.add(new GroupMultiData(id, "0000","http://"));
        }
        Log.e(TAG, "size:"+data.size());
        return data;
    }

    private List<NodeData> initNodes(){
        List<NodeData> data = new LinkedList<>();
        List<String> resources = Arrays.asList(getResources().getStringArray(R.array.id));
        for (int j = 0 ; j < 2; j++){
            List<NodeData> contents = new LinkedList<>();
            for (int i = 0 ; i < resources.size(); i++){
                //add footer?
                BaseNode footer = i == (resources.size() -1)
                        ? new NodeData("footer", null, NodeData.FOOTER)
                        : null;
                //add items
                contents.add(new NodeData(j+"-"+resources.get(i), footer, NodeData.ITEM_TXT));
            }

            //add header
            NodeData header = new NodeData("header "+j, contents, NodeData.HEADER, null);
            data.add(header);
        }

        Log.e(TAG, "size:"+data.size());
        return data;
    }

    private List<SectionExpandData> initExpandNodes(int times){
        List<SectionExpandData> data = new LinkedList<>();
        List<String> resources = Arrays.asList(getResources().getStringArray(R.array.id));

        for (int j = 0 ; j < times; j ++){
            List<ContentExpandData> contents = new LinkedList<>();

            for (int i = 0 ; i < resources.size(); i++){
                //add footer?
                BaseNode footer = i == (resources.size() -1)
                        ? new FootExpandData("footer")
                        : null;
                //add items
                contents.add(new ContentExpandData(j+" :"+resources.get(i), footer));
            }

            //add header
            SectionExpandData header = new SectionExpandData("header "+j, contents);
            data.add(header);
        }

        Log.e(TAG, "expand size:"+data.size());
        return data;
    }

    private void initCardAdapter(){
        TextView title = view.findViewById(R.id.cardTitle);
        RecyclerView cardRecyclerView = view.findViewById(R.id.cardRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),4,
                LinearLayoutManager.VERTICAL, false);
        title.setText(manager.getOrientation() == LinearLayoutManager.HORIZONTAL
                ? "Card Horizontal"
                : "Card Vertical"
        );
        cardRecyclerView.setLayoutManager(manager);
        cardRecyclerView.setHasFixedSize(true);
        cardRecyclerView.setNestedScrollingEnabled(false);
        CardAdapter adapter = new CardAdapter(initData());
        cardRecyclerView.setAdapter(adapter);
    }

    private void initShortcutAdapter(){
        TextView title = view.findViewById(R.id.shortcutTitle);
        RecyclerView shortcutRecyclerView = view.findViewById(R.id.shortcutRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),2,
                LinearLayoutManager.HORIZONTAL, false);
        title.setText(manager.getOrientation() == LinearLayoutManager.HORIZONTAL
                ? "Shortcut Horizontal"
                : "Shortcut Vertical"
        );
        shortcutRecyclerView.setLayoutManager(manager);
        shortcutRecyclerView.setHasFixedSize(true);
        shortcutRecyclerView.setNestedScrollingEnabled(false);//減少黏著 nestscroll view
        ShortcutAdapter adapter = new ShortcutAdapter(initShortcut());
        //PATCH 加載時的動畫
        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        adapter.setAnimationEnable(true);
        shortcutRecyclerView.setAdapter(adapter);
    }

    //modify width or height only
    /*private void initGroupAdapter(){
        RecyclerView groupRecyclerView = view.findViewById(R.id.groupRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),4,
                LinearLayoutManager.VERTICAL, false);
        groupRecyclerView.setLayoutManager(manager);
        groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setNestedScrollingEnabled(false);
        GroupAdapter adapter = new GroupAdapter(initAd());
        groupRecyclerView.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//must after setLayoutManager(manager)
            @Override
            public int getSpanSize(int position) {//** in multi-group, which is the header
                //中間那格2,2的不規則分佈
                switch (position){
                    case 0://header
                        return 4;
                    case 1:
                        return 3;
                    case 3:
                    case 4:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
    }*/
    //for modify width and height
    private void initGroupAdapter(){
        TextView title = view.findViewById(R.id.groupTitle);
        RecyclerView groupRecyclerView = view.findViewById(R.id.groupRecycler);
        //the order is from the top to the end
        SpannedGridLayoutManager manager = new SpannedGridLayoutManager(
                 position -> {
                     Log.e(TAG,"here qq");
                     switch (position){
                         case 0://header
                             return new SpannedGridLayoutManager.SpanInfo(4, 1);
                         case 1:
                             return new SpannedGridLayoutManager.SpanInfo(2, 2);
                         case 2:
                             return new SpannedGridLayoutManager.SpanInfo(2, 1);
                         default:
                             return new SpannedGridLayoutManager.SpanInfo(1, 1);

                     }
                },
                4, // number of columns
                1f // how big is default item
        );
        title.setText(manager.canScrollHorizontally()
                ? "Group Horizontal"
                : "Group Vertical"
        );
        groupRecyclerView.setLayoutManager(manager);
        groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setNestedScrollingEnabled(false);
        GroupAdapter adapter = new GroupAdapter(initAd());
        groupRecyclerView.setAdapter(adapter);
    }

    private void initNodeAdapter() {
        TextView title = view.findViewById(R.id.nodeTitle);
        RecyclerView nodeRecyclerView = view.findViewById(R.id.nodeRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        title.setText(
                manager.canScrollHorizontally()
                ? "Node Horizontal"
                : "Node Vertical"
        );
        nodeRecyclerView.setLayoutManager(manager);
        nodeRecyclerView.setHasFixedSize(true);
        nodeRecyclerView.setNestedScrollingEnabled(false);//減少黏著 nestscroll view
        NodeAdapter adapter = new NodeAdapter();
        adapter.setList(initNodes());
        nodeRecyclerView.setAdapter(adapter);
    }

    /**
     * ExpandNodeAdapter 實踐
     * NOTE 會不斷添增的RV 不能設定hasFixSize, 其RV的高會被限制，會有資料無法呈現
     */
    private void initNodeExpandAdapter() {
        TextView title = view.findViewById(R.id.nodeExpandTitle);
        RecyclerView nodeRecyclerView = view.findViewById(R.id.nodeExpandRecycler);
        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        title.setText(
                manager.canScrollHorizontally()
                        ? "Expand Node Horizontal"
                        : "Expand Node Vertical"
        );
        nodeRecyclerView.setLayoutManager(manager);
        nodeRecyclerView.setHasFixedSize(false);
        nodeRecyclerView.setNestedScrollingEnabled(true);
        ExpandNodeAdapter adapter = new ExpandNodeAdapter();
        adapter.setList(initExpandNodes(3));
        nodeRecyclerView.setAdapter(adapter);
    }

    /**
     * <p name="test result">
     *     Adapter 在創建時
     *      1.不塞入第一組資料
     *      hasFixSize = T
     *          @前提 autoLoadMore = F/T & loadMoreToLoading(V)
     *          loadMoreToLoading() >> OnLoadMoreListener
     *          @result 匯入第一組資料
     *          ------------------------------------------
     *          @前提 autoLoadMore = F/T & loadMoreToLoading(X)
     *          @result 不匯入資料
     *          ------------------------------------------
     *      hasFixSize = F
     *          @前提 autoLoadMore = F & loadMoreToLoading(V)
     *          loadMoreToLoading() >> OnLoadMoreListener
     *          @result 匯入第一組資料
     *          ------------------------------------------
     *          @前提 autoLoadMore = T & loadMoreToLoading(V)
     *          @result 匯入所有資料
     *          ------------------------------------------
     *          @前提 autoLoadMore = F/T & loadMoreToLoading(X)
     *          @result 不匯入資料
     *          ------------------------------------------
     *
     *      2.塞入第一組資料在Adapter's constructor
     *      hasFixSize = T
     *          @前提 autoLoadMore = F & loadMoreToLoading(V)
     *          loadMoreToLoading() >> OnLoadMoreListener
     *          @result 匯入第一組第二組資料
     *          -----------------------------------------
     *          @前提 autoLoadMore = T & loadMoreToLoading(V)
     *          loadMoreToLoading() >> OnLoadMoreListener >> loadMoreToLoading() >> OnLoadMoreListener
     *          @result 匯入第一組第二組第三組資料
     *          -----------------------------------------
     *          @前提 autoLoadMore = F & loadMoreToLoading(X)
     *          @result 匯入第一組
     *          -----------------------------------------
     *          @前提 autoLoadMore = T & loadMoreToLoading(X)
     *          @result 匯入第一組第二組第三組資料
     *          -----------------------------------------
            hasFixSize = F
     *          @前提 autoLoadMore = F & loadMoreToLoading(V)
     *          loadMoreToLoading() >> OnLoadMoreListener
     *          @result 匯入第一組第二組資料
     *          -----------------------------------------
     *          @前提 autoLoadMore = T & loadMoreToLoading(V)
     *          loadMoreToLoading() >> OnLoadMoreListener >> loadMoreToLoading() >> OnLoadMoreListener
     *          @result 匯入所有資料
     *          -----------------------------------------
     *          @前提 autoLoadMore = F & loadMoreToLoading(X)
     *          @result 匯入第一組
     *          -----------------------------------------
     *          @前提 autoLoadMore = T & loadMoreToLoading(X)
     *          @result 匯入所有資料
     *          -----------------------------------------
     * </p>
     *
     * NOTE 會不斷添增的RV 不能設定hasFixSize, 但要注意autoLoadMore的關係
       PATCH setEnableLoadMore(),setAutoLoadMore(F),不invoke loadMoreToLoading()
        皆不會觸發listener
     *
       PATCH: 滑動頁面到threshold >> request server request next page >> loadMoreToLoading();
        若設定autoLoadMore, 則會自動顯示“loading ..."，不需要額外書寫
     */
    private void initLoadMoreAdapter() {
        TextView title = view.findViewById(R.id.loadMoreTitle);
        RecyclerView loadMoreRecyclerView = view.findViewById(R.id.loadMoreRecycler);
        //the order is from the top to the end
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        title.setText(
                manager.canScrollHorizontally()
                        ? "Load More Horizontal"
                        : "Load More Vertical"
        );
        loadMoreRecyclerView.setLayoutManager(manager);
        loadMoreRecyclerView.setHasFixedSize(false);
        loadMoreRecyclerView.setNestedScrollingEnabled(false);//減少黏著 nestscroll view

        PageLoader loader = new PageLoader();
        //LoadMoreAdapter adapter = new LoadMoreAdapter();
        LoadMoreAdapter adapter = new LoadMoreAdapter(loader.loadData(getContext()));
        adapter.getLoadMoreModule().setLoadMoreView(new LoadMoreView());
        loadMoreRecyclerView.setAdapter(adapter);//需在設定load more 之前

        Log.d(TAG, "initLoadMoreAdapter: set adapter ready");
        Log.d(TAG, "initLoadMoreAdapter: isLoading="+adapter.getLoadMoreModule().isLoading());

        // 设置加载更多监听事件
        // NOTE 點擊要加載 or autoLoadMore = true >> isLoading() >> OnLoadMoreListener
        //  必定要添加listener 才會改變load more view
        adapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            Log.d(TAG, "initLoadMoreAdapter: load more listener");

            // 这里的作用是防止下拉刷新的时候还可以上拉加载, 在swipeRefresh 時要設成false
            adapter.getLoadMoreModule().setEnableLoadMore(true);
            if (adapter.getLoadMoreModule().isEnableLoadMore()){
                //PATCH: load fail
                List<String> list = loader.loadData(getContext());
                Log.d(TAG, "initLoadMoreAdapter: load list");

                //NOTE 一定要存入arrayList
                if (loader.isFirstPage()){
                    adapter.setList(list);
                }else{
                    adapter.addData(list);
                }
            }

            //重載完畢
            if (loader.getPageId() >= (loader.getPageSize()-1)){//加載到最後一頁
                adapter.getLoadMoreModule().loadMoreEnd();
            } else {
                adapter.getLoadMoreModule().loadMoreComplete();
            }
        });

        // PATCH default = true
         adapter.getLoadMoreModule().setAutoLoadMore(false);
        //adapter.getLoadMoreModule().loadMoreToLoading();
        adapter.getLoadMoreModule().loadMoreFail();
        //adapter.getLoadMoreModule().loadMoreComplete();
        //adapter.getLoadMoreModule().loadMoreEnd();

        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        adapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(true);

    }
}
