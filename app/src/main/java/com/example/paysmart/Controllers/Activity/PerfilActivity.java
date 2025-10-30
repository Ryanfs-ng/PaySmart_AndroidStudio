package com.example.paysmart.Controllers.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paysmart.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {

    private TextView tvNome, tvNivel, tvTotalGuardado, tvMetas, tvConquistas, tvNumeroCartao;
    private ImageView imgPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Para manter o ícone ativo
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_profile);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_profile) {
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (id == R.id.nav_analytics) {
                startActivity(new Intent(this, AnalyticsActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (id == R.id.nav_goals) {
                startActivity(new Intent(this, GoalsActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (id == R.id.nav_achievements) {
                startActivity(new Intent(this, InvestimentoActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            }

            return false;
        });

        tvNome = findViewById(R.id.tvNome);
        tvTotalGuardado = findViewById(R.id.tvTotalGuardado);
        tvMetas = findViewById(R.id.tvMetas);
        tvConquistas = findViewById(R.id.tvConquistas);
        imgPerfil = findViewById(R.id.imgPerfil);

        TextView tvNumeroCartao1 = findViewById(R.id.tvNumeroCartao1);
        TextView tvValidade = findViewById(R.id.tvValidade);
        TextView tvCvv = findViewById(R.id.tvCvv);

        // Exemplo de dados mockados
        tvNome.setText("João Silva");
        tvTotalGuardado.setText("0010");
        tvMetas.setText("57580773-2");
        tvConquistas.setText("0370");

        tvNumeroCartao1.setText("**********************");
        tvValidade.setText("12/28");
        tvCvv.setText("***");

        imgPerfil.setImageResource(R.drawable.ic_person);
    }
}
