package com.example.finanquest;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ConquistaAdapter extends RecyclerView.Adapter<ConquistaAdapter.ViewHolder> {
    private List<Conquista> conquistas;

    public ConquistaAdapter(List<Conquista> conquistas) {
        this.conquistas = conquistas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conquista, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Conquista c = conquistas.get(position);
        holder.titulo.setText(c.getTitulo());
        holder.descricao.setText(c.getDescricao());

        if (!c.isDesbloqueada()) {
            holder.itemView.setBackgroundResource(R.drawable.bg_card_locked);
            holder.itemView.setAlpha(0.5f);
            holder.titulo.setTextColor(Color.parseColor("#6F6F9A"));
            holder.descricao.setTextColor(Color.parseColor("#555577"));
        } else {
            holder.itemView.setAlpha(1f);
            switch (c.getTitulo()) {
                case "PRIMEIRO SALÁRIO":
                    holder.itemView.setBackgroundResource(R.drawable.bg_card_purple);
                    holder.titulo.setTextColor(Color.parseColor("#C896FF"));
                    break;
                case "ECONOMIZADOR":
                    holder.itemView.setBackgroundResource(R.drawable.bg_card_purple);
                    holder.titulo.setTextColor(Color.parseColor("#C896FF"));
                    break;
                case "META ALCANÇADA":
                    holder.itemView.setBackgroundResource(R.drawable.bg_card_purple);
                    holder.titulo.setTextColor(Color.parseColor("#C896FF"));
                    break;
                default:
                    holder.itemView.setBackgroundResource(R.drawable.bg_card_locked);
                    holder.titulo.setTextColor(Color.WHITE);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return conquistas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descricao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tvTituloConquista);
            descricao = itemView.findViewById(R.id.tvDescricaoConquista);
        }
    }
}
