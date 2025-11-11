package com.example.paysmart.Controllers.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paysmart.Models.Transacao;
import com.example.paysmart.R;

import java.util.List;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.ViewHolder> {

    private List<Transacao> transacaoList;

    public TransacaoAdapter(List<Transacao> transacaoList) {
        this.transacaoList = transacaoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transacao transacao = transacaoList.get(position);
        holder.tvName.setText(transacao.getTitulo());
        holder.tvType.setText(transacao.getTipo());
        holder.tvValue.setText(transacao.getQuantia());
        holder.tvDate.setText(transacao.getData());
    }

    @Override
    public int getItemCount() {
        return transacaoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType, tvValue, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvValue = itemView.findViewById(R.id.tvValue);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
