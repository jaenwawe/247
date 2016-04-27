package com.jaenwawe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Activity_MasterDetails extends AppCompatActivity {
    ProductData product;

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
}

