package com.reseau.link.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reseau.link.R;


/**
 * @author by xiongyan on 2017/11/29.
 */

public class SelectedDialogAdapter extends
        RecyclerView.Adapter<SelectedDialogAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private String[] mSelectedArray;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public SelectedDialogAdapter(Context context, String[] array) {
        mInflater = LayoutInflater.from(context);
        mSelectedArray = array;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.selected_dialog_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.seletedDialogItemTv = (TextView) view.findViewById(R.id.selected_dialog_item_tv);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String str = mSelectedArray[position];
        holder.seletedDialogItemTv.setText(str);
        if (mSelectedArray.length == 1) {
            holder.seletedDialogItemTv.setBackgroundResource(R.drawable.dialog_item_bg_only);
        } else if (position == 0) {
            holder.seletedDialogItemTv.setBackgroundResource(R.drawable.select_dialog_item_bg_top);
        } else if (position == mSelectedArray.length - 1) {
            holder.seletedDialogItemTv.setBackgroundResource(R.drawable.select_dialog_item_bg_buttom);
        } else {
            holder.seletedDialogItemTv.setBackgroundResource(R.drawable.select_dialog_item_bg_center);
        }
        if (mOnItemClickListener != null) {
            holder.seletedDialogItemTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
            holder.seletedDialogItemTv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mSelectedArray.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView seletedDialogItemTv;
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
