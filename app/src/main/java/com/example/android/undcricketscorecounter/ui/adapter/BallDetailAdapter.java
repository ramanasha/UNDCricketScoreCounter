package com.example.android.undcricketscorecounter.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.undcricketscorecounter.R;
import com.example.android.undcricketscorecounter.databinding.BallDetailListItemBinding;

import java.util.List;

public class BallDetailAdapter
        extends RecyclerView.Adapter<BallDetailAdapter.BallDetailViewHolder> {
    private List<String> mBallList;

    public BallDetailAdapter(List<String> ball) {
        mBallList = ball;
    }

    @NonNull
    @Override
    public BallDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BallDetailListItemBinding binding = BallDetailListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BallDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BallDetailViewHolder holder, int position) {
        int resourceId;
        if (mBallList.get(position).equals("W")) {
            resourceId = R.drawable.wicket_circle;
        } else if (TextUtils.isEmpty(mBallList.get(position))) {
            resourceId = R.drawable.run_circle;
        } else {
            resourceId = R.drawable.dot_ball_circle;
        }
        holder.binding.tvBallDetail.setText(mBallList.get(position));
        holder.binding.tvBallDetail.setBackgroundResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return mBallList == null ? 0 : mBallList.size();
    }

    // Update the RecyclerView data set
    public void swapData(List<String> ballList) {
        mBallList = ballList;
        notifyDataSetChanged();
    }

    // Clear data set of Recycler View
    public void clearData() {
        mBallList.clear();
        notifyDataSetChanged();
    }

    /**
     * ViewHolder
     */
    class BallDetailViewHolder extends RecyclerView.ViewHolder {

        private final BallDetailListItemBinding binding;

        BallDetailViewHolder(BallDetailListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
