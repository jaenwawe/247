package com.jaenwawe;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    MenuItem menuItemAboutMe;
    MenuItem menuItemonHand;
    MenuItem menuItemPending;
    MenuItem menuItemUnordered;
    MenuItem menuItemYouTube;
    MenuItem menuItemMaps;
    MenuItem menuItemCamera;

    ProductData product = new ProductData();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar_bottom_nav);
        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        //items activity_navigation_drawer.xml
        menuItemAboutMe = (MenuItem) findViewById(R.id.itemAboutMe);
        menuItemonHand = (MenuItem) findViewById(R.id.itemOnHand);
        menuItemPending = (MenuItem) findViewById(R.id.itemInventoryPending);
        menuItemUnordered = (MenuItem) findViewById(R.id.itemInventoryUnordered);
        menuItemYouTube = (MenuItem) findViewById(R.id.itemYouTube);
        menuItemMaps = (MenuItem) findViewById(R.id.itemGoogleMap);
        menuItemCamera = (MenuItem) findViewById(R.id.itemCamera);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
       // navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        // drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_main_nav, Fragment_AboutMe.newInstance("1", "2"))
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(MainActivity.this, getString(R.string.main_orientation_toast), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.main_landscape_orientation_toast), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.itemAboutMe:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_main_nav, Fragment_AboutMe.newInstance("1", "2"))
                        .commit();
                break;
            case R.id.itemOnHand:
                Intent intentOnHand = new Intent(getApplicationContext(), Activity_ViewPager.class);
                intentOnHand.putExtra(Intent.EXTRA_TEXT, (String) product.getItem(position).get("name"));
                startActivity(intentOnHand);
                break;

            case R.id.itemInventoryPending:
                Intent intentInventoryPending = new Intent(getApplicationContext(), ActivityFlipView.class);
                startActivity(intentInventoryPending);
                break;


            case R.id.itemInventoryUnordered:

                Intent intentInventoryUnOrdered = new Intent(getApplicationContext(), ActivityRecyclerView.class);
                startActivity(intentInventoryUnOrdered);
                break;

            case R.id.itemYouTube:
                Intent intentYoutube = new Intent(getApplicationContext(), YouTubeActivity.class);
                intentYoutube.putExtra(YouTubeActivity.VIDEO_ID, "inED9IicIpc");
                startActivity(intentYoutube);
                break;

            case R.id.itemGoogleMap:
                final Intent mapIntent = new Intent(this, MapsActivity.class);
                startActivity(mapIntent);
                break;
            case R.id.itemCamera:
                Intent intentCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, 0);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}