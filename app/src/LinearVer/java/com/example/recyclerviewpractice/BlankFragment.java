package com.example.recyclerviewpractice;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1.modified the height of the item
 * 2.item display direction
 * */
public class BlankFragment extends Fragment {

    private mAdapter adapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment, container, false);
    }

    /*onActivityCreated is deprecated*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView title = view.findViewById(R.id.tVtitle);
        /**
         * BuildConfig.VersionName 在build varient 有重新定義
         * 所以 run後就正常了
         */
        title.setText((BuildConfig.VERSION_NAME.split("-"))[1]);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL); //default: vertical
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(initData());
        recyclerView.setAdapter(adapter);

    }


    private List<String> initData(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }



    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<String> id;
        public mAdapter(List<String> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            view.getLayoutParams().height = viewGroup.getHeight() / 3; //dynamic modify the height per item
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