package com.example.firebaseconnector.UserApplicationLayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;


import java.util.List;

public class PlanPageAdapter extends RecyclerView.Adapter<PlanPageAdapter.ViewHolder> {
	private final List<String> mData;

	public PlanPageAdapter(List<String> data) {
		this.mData = data;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.plan_day_layout, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.textView.setText(mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		TextView textView;

		ViewHolder(View view) {
			super(view);
			textView = view.findViewById(R.id.textView);
		}
	}
}
