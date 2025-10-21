package com.example.finanquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finanquest.Goals.GoalsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ConquistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conquistas);

        // Para manter o ícone ativo
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_achievements);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_achievements) {
                Toast.makeText(this, "Conquistas", Toast.LENGTH_SHORT).show();
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
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, PerfilActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }

            return false;
        });


        RecyclerView recycler = findViewById(R.id.recyclerConquistas);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        List<Conquista> lista = new ArrayList<>();
        lista.add(new Conquista("PRIMEIRO SALÁRIO", "Recebeu sua primeira receita", true));
        lista.add(new Conquista("ECONOMIZADOR", "Economizou por 3 meses seguidos", true));
        lista.add(new Conquista("META ALCANÇADA", "Completou sua primeira meta financeira", true));
        lista.add(new Conquista("INVESTIDOR INICIANTE", "Fez seu primeiro investimento", false));
        lista.add(new Conquista("MILIONÁRIO", "Acumule R$ 1.000.000", false));

        ConquistaAdapter adapter = new ConquistaAdapter(lista);
        recycler.setAdapter(adapter);
    }
}
