package com.jaenwawe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jae Nwawe on 2/24/2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Map<String, ?>> mDataset;
    private Context mContext;
    OnItemClickListener mItemClickListener;
    private Map<View, YouTubeThumbnailLoader> mLoaders;

    public MyRecyclerViewAdapter(Context myContext, List<Map<String, ?>> myDataset) {
        mContext = myContext;
        mDataset = myDataset;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);

        void onOverflowMenuClick(View v, final int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        switch (viewType) {
            case 0:
                v = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.fragment_row_above, parent, false);
                break;

            case 1:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_row_above, parent, false);
                break;

            default:
                v = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.fragment_row_above, parent, false);
                break;
        }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?> Product = mDataset.get(position);
        holder.bindProductData(Product);
    }

    public void duplicateItem(int position) {
        HashMap<String, ?> movie = (HashMap<String, ?>) mDataset.get(position);
        mDataset.add(position, (HashMap<String, ?>) movie.clone());
        HashMap<String, ?> itemMap =
                (HashMap<String, ?>) movie.get(position + 1);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void removeItem(int position) {
        mDataset.remove(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        double priceType = (Double) mDataset.get(position).get("price");
        if (priceType > 8)
            type = 1;
        else type = 0;

        return type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;
        public ImageView vOverflow;

        public void bindProductData(Map<String, ?> Product) {
            vTitle.setText((String) Product.get("name"));
            vDescription.setText((String) Product.get("description"));
            vIcon.setImageResource((Integer) Product.get("image"));
        }

        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.icon);
            vTitle = (TextView) v.findViewById(R.id.title);
            vDescription = (TextView) v.findViewById(R.id.description);
            vOverflow = (ImageView) v.findViewById(R.id.selection);

            if (vOverflow != null) {
                vOverflow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onOverflowMenuClick(v, getAdapterPosition());
                        }
                    }
                });

                v.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(v, getAdapterPosition());
                        }
                    }
                }));

                v.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mItemClickListener != null)
                            mItemClickListener.onItemLongClick(v, getAdapterPosition());

                        return true;
                    }
                });
            }
        }
    }
}

