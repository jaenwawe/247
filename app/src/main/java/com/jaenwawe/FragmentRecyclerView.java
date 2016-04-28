package com.jaenwawe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.util.HashMap;


/**
 * Created by Jae Nwawe on 2/25/2016.
 */
public class FragmentRecyclerView extends Fragment implements Activity_MasterDetails.OnDetailItemSelectedListener{
    RecyclerView mRecyclerView; //Jae added
    ProductData productData; //Jae Added to accomplish pg 19

    private OnRowClick mDetailRowListener;
    RecyclerView.LayoutManager mLayoutManager;
    MyRecyclerViewAdapter mRecyclerViewAdapter;
    private MyRecyclerViewAdapter mListener;
    private OnItemClickListenerFragementtoActivity mDetailViewListener;
    private static final String ARG_SECTION_NUMBER = "sectionNumber";

    public static FragmentRecyclerView newInstance(int sectionNumber) {
        FragmentRecyclerView fragment = new FragmentRecyclerView();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onDetailItemSelectedListener(int position, HashMap<String, ?> movie) {

        //call master_detail activity
    }
    public interface OnItemClickListenerFragementtoActivity {
        void onItemClick(View v, int position);
    }
    public interface OnRowClick {
        void onItemSelected(HashMap<String, ?> Product, View sharedImage);
        //void onItemClick(View v, int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRowClick) {
            mDetailRowListener = (OnRowClick) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRowClick");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        productData = new ProductData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (menu.findItem(R.id.action_search) == null)
            inflater.inflate(R.menu.menu_recycle_search, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView search= (SearchView) MenuItemCompat.getActionView(item);

        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query){
                    int position = productData.findFirst(query);
                    System.out.println("Found! :" + position);
                    if(position >= 0)
                        mRecyclerView.scrollToPosition(position);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query){
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.trash_can:
                Toast.makeText(getActivity(), "Clicked trash can", Toast.LENGTH_LONG).show();
                return true;
            case R.id.fragment_item:
                Toast.makeText(getActivity(), "Clicked Fragment Item", Toast.LENGTH_LONG).show();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recycleview, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mLayoutManager = new LinearLayoutManager((getActivity()));
        mRecyclerView.setLayoutManager(mLayoutManager);

        //bind productData populate cards in the current activity
        mRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(), productData.getProductList());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mDetailRowListener.onItemSelected(productData.getItem(position), v);
            }
            @Override
            public void onItemLongClick(View v, int position) {
                mDetailViewListener.onItemClick(v, position);
            }

            @Override
            public void onOverflowMenuClick(View v, final int position) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_duplicate:
                                productData.addItem(position);
                                mRecyclerViewAdapter.notifyItemInserted(position + 1);
                                return true;
                            case R.id.item_delete:
                                productData.removeItem(position);
                                mRecyclerViewAdapter.notifyItemRemoved(position);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.contextual_popup_menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });
        return rootView;
    }
}