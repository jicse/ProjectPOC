package com.balaji.projectpoc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balaji.projectpoc.R;
import com.balaji.projectpoc.model.Country;
import com.balaji.projectpoc.model.Row;
import com.bumptech.glide.Glide;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private Country data;
    private Context context;

    public CountryAdapter(Context context, Country data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder holder, int position) {
        Row row = data.getRows().get(position);
        holder.click(row, listener);

        holder.tvTitle.setText(row.getTitle());
        holder.tvDesc.setText(row.getDescription());

        Glide.with(context)
                .load(row.getImageHref())
                .placeholder(R.drawable.place)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.getRows().size();
    }

    public interface OnItemClickListener {
        void onClick(Row row);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDesc;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvDesc = itemView.findViewById(R.id.desc);
            image = itemView.findViewById(R.id.img);
        }

        public void click(final Row row, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(row);
                }
            });
        }
    }
}
