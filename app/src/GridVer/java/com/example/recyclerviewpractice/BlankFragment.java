package com.example.recyclerviewpractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
        init(view);
    }

    private List<String> initTitle(){
        return new LinkedList<>(Arrays.asList(getResources().getStringArray(R.array.id)));
    }

    private void init(View view){
        final List<Products.Product> products = new Products().initiData(initTitle());
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
       /* ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
        params.height=100;
        recyclerView.setLayoutParams(params);
        */

        GridLayoutManager manager = new GridLayoutManager(getContext(),spancount);

        /**
         * PATCH: 列出 adapter.id.get(position).viewtype
         * 針對header 的viewType 做修飾
         */
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { //modify the width of Header
            @Override
            public int getSpanSize(int position) {
                int span = position == 0 ? spancount : 1;//notice this logic
                Log.d(TAG, "set span size; position:"+ position+"; span:"+span);
                return span;
            }
        });
        recyclerView.setLayoutManager(manager);
        adapter = new mAdapter(products);
        recyclerView.setAdapter(adapter);
    }



    //getItemViewType   》 onCreateViewHolder  》Holer 》onBindViewHolder
    class mAdapter extends RecyclerView.Adapter<mAdapter.Holder>{
        List<Products.Product> id;
        public mAdapter(List<Products.Product> id) { this.id = id;}

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            View view = (viewtype == 1) ?
                    getLayoutInflater().inflate(R.layout.title, viewGroup,false):
                    getLayoutInflater().inflate(R.layout.item, viewGroup, false);
            view.getLayoutParams().height = (viewGroup.getHeight()) / 3;

            /* will be overlap
            view.getLayoutParams().width = (viewtype == 1) ? (viewGroup.getWidth())* spancount : viewGroup.getWidth();
            * */
            view.setTag(viewtype == 1);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.name.setText(id.get(position).name);
        }


        @Override
        public int getItemViewType(int position) {
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
                if ((boolean)itemView.getTag()) {
                    name = itemView.findViewById(R.id.tVbrand);
                }else {
                    name = itemView.findViewById(R.id.tVitem);
                }
            }
        }
    }

}