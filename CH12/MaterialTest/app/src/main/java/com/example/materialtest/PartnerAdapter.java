package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {

    private Context mContext;

    private List<Partner> mPartnerList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.partner_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Partner partner = mPartnerList.get(position);
                Intent intent = new Intent(mContext, PartnerActivity.class);
                intent.putExtra(PartnerActivity.PARTNER_NAME, partner.getName());
                intent.putExtra(PartnerActivity.PARTNER_IMAGE_ID, partner.getImageId());
                intent.putExtra(PartnerActivity.PARTNER_PROFILE_ID, partner.getProfileId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partner partner = mPartnerList.get(position);
        holder.partnerName.setText(partner.getName());
        Glide.with(mContext).load(partner.getImageId()).into(holder.partnerImage);
    }

    @Override
    public int getItemCount() {
        return mPartnerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView partnerImage;
        TextView partnerName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            partnerImage = (ImageView) itemView.findViewById(R.id.partner_image);
            partnerName = (TextView) itemView.findViewById(R.id.partner_name);
        }
    }
    public PartnerAdapter(List<Partner> partnerList) {
        mPartnerList = partnerList;
    }


}
