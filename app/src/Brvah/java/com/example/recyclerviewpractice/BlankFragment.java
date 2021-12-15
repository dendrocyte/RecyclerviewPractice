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

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.example.recyclerviewpractice.card.CardAdapter;
import com.example.recyclerviewpractice.card.CardData;
import com.example.recyclerviewpractice.expand.ContentExpandData;
import com.example.recyclerviewpractice.expand.ExpandNodeAdapter;
import com.example.recyclerviewpractice.expand.FootExpandData;
import com.example.recyclerviewpractice.expand.SectionExpandData;
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
 *
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
     * NOTE recyclerview 要的空間要夠大，要不然會打不開
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
        nodeRecyclerView.setHasFixedSize(true);
        nodeRecyclerView.setNestedScrollingEnabled(true);
        ExpandNodeAdapter adapter = new ExpandNodeAdapter();
        adapter.setList(initExpandNodes(3));
        nodeRecyclerView.setAdapter(adapter);
    }

}
