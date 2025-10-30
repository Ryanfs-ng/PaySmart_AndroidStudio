package com.example.paysmart.Views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.paysmart.R;

public class NovaMetaDialog extends DialogFragment {

    public interface NovaMetaListener {
        void onMetaCriada(String titulo, String descricao, int atual, int alvo);
    }

    private NovaMetaListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NovaMetaListener) {
            listener = (NovaMetaListener) context;
        } else {
            throw new RuntimeException(context.toString() + " deve implementar NovaMetaListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_nova_meta, null);

        EditText edtTitulo = view.findViewById(R.id.edtTitulo);
        EditText edtDescricao = view.findViewById(R.id.edtDescricao);
        EditText edtAtual = view.findViewById(R.id.edtAtual);
        EditText edtAlvo = view.findViewById(R.id.edtAlvo);
        Button btnSalvar = view.findViewById(R.id.btnSalvar);

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(view)
                .create();

        btnSalvar.setOnClickListener(v -> {
            String titulo = edtTitulo.getText().toString();
            String descricao = edtDescricao.getText().toString();
            int atual = edtAtual.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtAtual.getText().toString());
            int alvo = edtAlvo.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtAlvo.getText().toString());

            if (listener != null) {
                listener.onMetaCriada(titulo, descricao, atual, alvo);
            }
            dialog.dismiss();
        });

        return dialog;
    }
}
