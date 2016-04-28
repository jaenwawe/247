package com.jaenwawe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import se.emilsjolander.flipview.FlipView;

public class ActivityFlipView extends AppCompatActivity {

    public static final String PRODUCT_ID = "PRODUCT_ID";
    private static final int MENU_ITEM_LOGOUT = 1001;
    private static String webUrl = "https://www.amazon.com/";
    private static String email = "jnwawe@syr.edu";
    private CoordinatorLayout coordinatorLayout;
    private List<Product> products = DataProvider.productList;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_flipview);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_flipview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_flipview);
        setSupportActionBar(toolbar);


        android.support.design.widget.FloatingActionButton fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.container_details_flip_view);

                String[] addresses = {email};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Information request");
                intent.putExtra(Intent.EXTRA_TEXT, "Please send some information!");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, R.string.fab_statement, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        ProductListAdapter adapter = new ProductListAdapter(
                this, R.layout.content_flipview, products);

        FlipView lv = (FlipView) findViewById(R.id.flip_view);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listview_menu, menu);

        menu.add(0, MENU_ITEM_LOGOUT, 1001, R.string.logout);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Snackbar.make(coordinatorLayout,
                        "You selected settings", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_web:
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
                return true;
            case R.id.action_cart:
                Snackbar.make(coordinatorLayout,
                        "You selected the Shopping Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            case MENU_ITEM_LOGOUT:
                Snackbar.make(coordinatorLayout,
                        "You selected Logout", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}