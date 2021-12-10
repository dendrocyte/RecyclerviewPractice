package com.example.recyclerviewpractice;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyiling on 2018/10/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class Products {


    public List<Product> initiData(List<String> titles){
        Log.d("Product", "initiData: "+titles);
        List<Product> products = new ArrayList<>();
        for (String title : titles){
            Product product = new Product(title, 1);
            products.add(product);
            for (String content : initContent(title)){
                Product catlog = new Product(content, 2);
                products.add(catlog);
            }
        }
        return products;
    }

    private static List<String> initContent(String titile){
        List<String> contents = new ArrayList<>();
        for (int i = 1 ; i < 4 ; i++){
            contents.add(titile+"."+i);
        }
        return contents;
    }

    public class Product {
        String name;
        int viewtype;


        public Product(String name, int viewtype) {
            this.name = name;
            this.viewtype = viewtype;
        }
    }

}
