package com.example.recyclerviewpractice;

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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BlankFragment extends Fragment {
    private mAdapter adapter;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tVtitle);
        textView.setText((BuildConfig.VERSION_NAME.split("-"))[1]);//get the suffix from version name
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);

        //the order is from the top to the end
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d("onScrollStateChanged: ","state="+newState);
                /**
                 * //静止,没有滚动
                 * public static final int SCROLL_STATE_IDLE = 0;
                 *
                 * //正在被外部拖拽,一般为用户正在用手指滚动
                 * public static final int SCROLL_STATE_DRAGGING = 1;
                 *
                 * //自动滚动开始
                 * public static final int SCROLL_STATE_SETTLING = 2;
                 */
                /**
                 * RecyclerView.canScrollVertically(1)的值表示是否能向下滚动，false表示已经滚动到底部
                 * RecyclerView.canScrollVertically(-1)的值表示是否能向上滚动，false表示已经滚动到顶部
                 */

            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

            }
        });

        //測試 scroll 到指定位置, OnScrollStateChanged 的state 改變
        //make a time dela
        recyclerView.postDelayed(() -> {
            //自主scroll 到特定位置
            recyclerView.scrollToPosition(5);
        }, 6000);

        recyclerView.setHasFixedSize(true);
        adapter = new mAdapter(initTitle());
        recyclerView.setAdapter(adapter);
    }

    private List<String> initTitle(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }

    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<String> id;
        public mAdapter(List<String> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            view.getLayoutParams().height = 500;
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.name.setText(id.get(position));
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView name;
            public Holder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tVitem);
            }
        }
    }
}
