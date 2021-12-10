package com.example.recyclerviewpractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BlankFragment extends Fragment {
    private mAdapter adapter;
    int spancount = 3;
    private String TAG = BlankFragment.class.getSimpleName();

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
        final List<Products.Product> products = new Products().initiData(initTitle());
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
       /* ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
        params.height=100;
        recyclerView.setLayoutParams(params);
        */

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spancount, StaggeredGridLayoutManager.VERTICAL);

//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { //modify the width of Header
//            @Override
//            public int getSpanSize(int position) {
//                int span =((position == 0)? 1 : spancount);
//                Log.e(TAG, "set span size; position:"+ position+"; span:"+span);
//                return span;
//            }
//        });
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(products);
        recyclerView.setAdapter(adapter);
    }

    private List<String> initTitle(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }



    //getItemViewType   》 onCreateViewHolder  》Holder 》onBindViewHolder
    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<Products.Product> id;
        public mAdapter(List<Products.Product> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            System.out.println("CreateViewHolder");
            View view = (viewtype == 1) ?
                    getLayoutInflater().inflate(R.layout.header, viewGroup,false):
                    getLayoutInflater().inflate(R.layout.item, viewGroup, false);

            /*can not arrange in onCreateViewHolder here
             * view.getLayoutParams().height = (int)(Math.random()*9)+1;
             * */
            view.setTag(viewtype == 1);

            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            System.out.println("onBindViewHolder");

            switch (holder.getItemViewType()){
                case 1:
                    holder.name.setText(id.get(position).name);
                    holder.name.getLayoutParams().height = 100;
                    break;
                case 2:
                    holder.name.setText(id.get(position).name);
                    //random height will present the greatest difference between gridlayout and staggergrid
                    holder.name.getLayoutParams().height = (int)(Math.random()*990)+1;
                    break;
            }
        }


        @Override
        public int getItemViewType(int position) {
            System.out.println("getItemViewType");
            return id.get(position).viewtype;
        }

        @Override
        public int getItemCount() {
            return id.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView name;
            public Holder(@NonNull View itemView) {
                super(itemView);
                System.out.println("Holder");

                if ((boolean)itemView.getTag()) {
                    name = itemView.findViewById(R.id.tVheader);
                }else {
                    name = itemView.findViewById(R.id.tVitem);
                }
            }
        }
    }
}