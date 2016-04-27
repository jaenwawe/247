package com.jaenwawe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        // RecyclerViewFragment.OnListItemSelectedListener,
{
    MenuItem menuItemAboutMe;
    MenuItem menuItemonHand;
    MenuItem menuItemPending;
    MenuItem menuItemUnordered;
    MenuItem menuItemYouTube;
    MenuItem menuItemMaps;
    MenuItem menuItemCamera;
    ProductData product = new ProductData();
    int position = 0;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
                    //.addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.itemAboutMe:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_main_nav, Fragment_AboutMe.newInstance("1", "2"))
                        .addToBackStack(null)
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
