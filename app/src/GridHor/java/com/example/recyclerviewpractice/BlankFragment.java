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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 1.the item display and arrangement direction
 * 2.add the divisor and item's margin modification
 * */
public class BlankFragment extends Fragment {
    private mAdapter adapter;
    private String TAG = BlankFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView textView = view.findViewById(R.id.tVtitle);
        textView.setText((BuildConfig.VERSION_NAME.split("-"))[1]);//get the suffix from version name
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        //the order is from the top to the end
        GridLayoutManager manager = new GridLayoutManager(getContext(),3,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        /* we can add different decoration at the same time*/
        //add divisor
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        //modify the margin
        recyclerView.addItemDecoration(new MarginDecoation(5));//customization
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
            /* modify the height
            *
            * order: recyclerView.addItemDecoration >> view.getLayoutParams().height
            * view.getLayoutParams().height = (viewGroup.getHeight()) / 2;
            NOTE:高度若掌握不對，會把Divider蓋在後面
              * addItemDecoration >> onCreateViewHolder
              * Divider 和 item 是不相綁的
            * */
            view.getLayoutParams().height = (viewGroup.getHeight()) / 4;
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Log.e(TAG,"BindViewHolder" );
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
