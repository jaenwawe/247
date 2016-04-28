package com.jaenwawe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class Activity_MasterDetails extends AppCompatActivity {
    ProductData product;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__master_details);
        product = new ProductData();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, FragmentRecyclerView.newInstance(0))
                    .commit();
        }
    }

    public void onListItemSelectedListener(int position, HashMap<String, ?> product) {
        if (mTwoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, Fragment_MasterDetail.newInstance(product))
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, Fragment_MasterDetail.newInstance(product))
                    .commit();
        }
    }



    public interface OnDetailItemSelectedListener {
        public void onDetailItemSelectedListener(int position, HashMap<String, ?> product);
    }
}

