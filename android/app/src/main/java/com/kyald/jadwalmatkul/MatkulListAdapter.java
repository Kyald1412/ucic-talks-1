package com.kyald.jadwalmatkul;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kyald.jadwalmatkul.model.MatkulData;
import com.kyald.jadwalmatkul.model.MatkulDataResponse;

import java.util.List;

public class MatkulListAdapter
        extends RecyclerView.Adapter<MatkulListAdapter.ViewHolder> {

    private final List<MatkulData> mValues;

    public MatkulListAdapter(List<MatkulData> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matkul_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getId());
        holder.mContentView.setText(mValues.get(position).getHari() + " - " + mValues.get(position).getMatkul());
        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mContentView = (TextView) view.findViewById(R.id.content);

        }
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MatkulData item = ( MatkulData) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, MatkulDetailActivity.class);
            intent.putExtra(MatkulDetailActivity.ARG_ITEM_ID, item.getId());
            intent.putExtra(MatkulDetailActivity.ARG_HARI, item.getHari());
            intent.putExtra(MatkulDetailActivity.ARG_HARI_ID, item.getId_hari());
            intent.putExtra(MatkulDetailActivity.ARG_MATKUL, item.getMatkul());

            context.startActivity(intent);
        }
    };

}