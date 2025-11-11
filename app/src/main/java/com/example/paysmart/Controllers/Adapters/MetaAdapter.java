package com.example.paysmart.Controllers.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paysmart.Models.Meta;
import com.example.paysmart.R;

import java.util.List;

public class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.GoalViewHolder> {

    private List<Meta> metas;

    public MetaAdapter(List<Meta> metas) {
        this.metas = metas;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goal, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Meta meta = metas.get(position);

        holder.tvGoalName.setText(meta.getNome());
        holder.tvGoalDescription.setText(meta.getDescricao());
        holder.tvProgressValue.setText("R$ " + meta.getValorAtual() + " / R$ " + meta.getValorMeta());

        int progress = (int)((meta.getValorAtual() * 100f) / meta.getValorMeta());
        if(progress > 100) progress = 100;
        holder.progressGoal.setProgress(progress);
        holder.tvProgressPercent.setText(progress + "% COMPLETO");

        holder.progressGoal.getProgressDrawable().setColorFilter(meta.getCor(), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.imgGoalIcon.setImageResource(meta.getIconeId());
    }

    @Override
    public int getItemCount() {
        return metas.size();
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


