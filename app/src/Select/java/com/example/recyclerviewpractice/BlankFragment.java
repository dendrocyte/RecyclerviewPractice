package com.example.recyclerviewpractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView title = view.findViewById(R.id.tVtitle);
        title.setText((BuildConfig.VERSION_NAME.split("-"))[1]);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL); //default: vertical
        recyclerView.setLayoutManager(manager);
        mAdapter adapter = new mAdapter(initData());
        recyclerView.setAdapter(adapter);

    }

    private List<String> initData(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }



    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<String> id;
        int selected = 0;
        public mAdapter(List<String> id) { this.id = id;}
        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            view.getLayoutParams().height = viewGroup.getHeight() / 3; //dynamic modify the height per carditem
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final Holder holder, final int position) {
            holder.name.setText(id.get(position));
            int color = selected == position ? R.color.purple_200 : R.color.purple_500;
            holder.frame.setBackgroundColor(getResources().getColor(color));
            holder.frame.setOnClickListener(v -> {
                int rangeStart = Math.min(position, selected);
                int rangeCount = Math.abs(position-selected);
                selected = holder.getLayoutPosition();
                notifyItemRangeChanged(rangeStart, rangeCount+1);//count: 頭尾都要算
            });
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView name;
            CardView frame;
            public Holder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tVitem);
                frame = itemView.findViewById(R.id.card);
            }
        }
    }
}