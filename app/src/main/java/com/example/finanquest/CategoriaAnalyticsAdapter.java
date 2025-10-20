package com.example.finanquest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriaAnalyticsAdapter extends RecyclerView.Adapter<CategoriaAnalyticsAdapter.ViewHolder> {

    private List<CategoriaAnalytics> categorias;

    public CategoriaAnalyticsAdapter(List<CategoriaAnalytics> categorias) {
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria_analytics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriaAnalytics categoria = categorias.get(position);
        holder.nomeCategoria.setText(categoria.getNome());
        holder.porcentagemCategoria.setText(String.format("%.2f%%", categoria.getPorcentagem()));

        switch (categoria.getNome().toLowerCase()) {
            case "assinatura":
                holder.iconCategoria.setImageResource(R.drawable.ic_subscriptions);
                holder.iconCategoria.setColorFilter(0xFFC77DFF);
                break;
            case "compras online":
                holder.iconCategoria.setImageResource(R.drawable.ic_shopping_cart);
                holder.iconCategoria.setColorFilter(0xFF00B4FF);
                break;
            case "despesa":
                holder.iconCategoria.setImageResource(R.drawable.outline_attach_money_24);
                holder.iconCategoria.setColorFilter(0xFF66BB6A);
                break;
            case "transporte":
                holder.iconCategoria.setImageResource(R.drawable.ic_car);
                holder.iconCategoria.setColorFilter(0xFFFF4D4D);
                break;
            case "credito":
                holder.iconCategoria.setImageResource(R.drawable.ic_credit_card);
                holder.iconCategoria.setColorFilter(0xFF66BB6A);
                break;
            default:
                holder.iconCategoria.setImageResource(R.drawable.outline_attach_money_24);
                holder.iconCategoria.setColorFilter(0xFF7B5FC0);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconCategoria;
        TextView nomeCategoria;
        TextView porcentagemCategoria;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconCategoria = itemView.findViewById(R.id.iconCategoria);
            nomeCategoria = itemView.findViewById(R.id.nomeCategoria);
            porcentagemCategoria = itemView.findViewById(R.id.porcentagemCategoria);
        }
    }
}
