package com.example.paysmart.Controllers.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paysmart.Models.Goal;
import com.example.paysmart.R;

import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {

    private List<Goal> goals;

    public GoalAdapter(List<Goal> goals) {
        this.goals = goals;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goal, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Goal goal = goals.get(position);

        holder.tvGoalName.setText(goal.getName());
        holder.tvGoalDescription.setText(goal.getDescription());
        holder.tvProgressValue.setText("R$ " + goal.getCurrentValue() + " / R$ " + goal.getTargetValue());

        int progress = (int)((goal.getCurrentValue() * 100f) / goal.getTargetValue());
        if(progress > 100) progress = 100;
        holder.progressGoal.setProgress(progress);
        holder.tvProgressPercent.setText(progress + "% COMPLETO");

        holder.progressGoal.getProgressDrawable().setColorFilter(goal.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.imgGoalIcon.setImageResource(goal.getIconResId());
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    static class GoalViewHolder extends RecyclerView.ViewHolder {
        TextView tvGoalName, tvGoalDescription, tvProgressValue, tvProgressPercent;
        ProgressBar progressGoal;
        ImageView imgGoalIcon;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGoalName = itemView.findViewById(R.id.tvGoalName);
            tvGoalDescription = itemView.findViewById(R.id.tvGoalDescription);
            tvProgressValue = itemView.findViewById(R.id.tvProgressValue);
            tvProgressPercent = itemView.findViewById(R.id.tvProgressPercent);
            progressGoal = itemView.findViewById(R.id.progressGoal);
            imgGoalIcon = itemView.findViewById(R.id.imgGoalIcon);
        }
    }
}


