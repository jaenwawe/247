package com.jaenwawe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);//content_main2

              if(savedInstanceState==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMapTwo, new FragmentGoogleMap().newInstance())
                    .commit();
        }
    }
}