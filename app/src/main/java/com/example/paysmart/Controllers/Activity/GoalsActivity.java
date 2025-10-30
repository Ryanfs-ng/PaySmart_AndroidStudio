package com.example.paysmart.Controllers.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paysmart.Controllers.Adapters.GoalAdapter;
import com.example.paysmart.Models.Goal;
import com.example.paysmart.R;
import com.example.paysmart.Views.NovaMetaDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity implements NovaMetaDialog.NovaMetaListener {


    private RecyclerView recyclerGoals;
    private GoalAdapter goalAdapter;
    private List<Goal> goals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        // Para manter o ícone ativo
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_goals);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_goals) {
                Toast.makeText(this, "Conquistas", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            } else if (id == R.id.nav_analytics) {
                startActivity(new Intent(this, AnalyticsActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            } else if (id == R.id.nav_achievements) {
                startActivity(new Intent(this, InvestimentoActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, PerfilActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }

            return false;
        });

        recyclerGoals = findViewById(R.id.recyclerGoals);
        FloatingActionButton btnAddGoal = findViewById(R.id.btnAddGoal);

        // Inicializa a lista
        goals = new ArrayList<>();

        // Cria o adapter e configura o RecyclerView
        goalAdapter = new GoalAdapter(goals);
        recyclerGoals.setLayoutManager(new LinearLayoutManager(this));
        recyclerGoals.setAdapter(goalAdapter);

        goals.add(new Goal(
                "Comprar novo laptop",
                "Guardar dinheiro para um laptop novo",
                200,
                1000,
                Color.parseColor("#4AA3FF"),
                R.drawable.ic_laptop
        ));

        goals.add(new Goal(
                "Viajar com amigos",
                "Economizar para viagem de fim de ano",
                500,
                2000,
                Color.parseColor("#4AA3FF"),
                R.drawable.ic_travel
        ));

        goalAdapter.notifyDataSetChanged();

        // Configura o botão de adicionar meta
        btnAddGoal.setOnClickListener(v -> {
            NovaMetaDialog dialog = new NovaMetaDialog();
            dialog.show(getSupportFragmentManager(), "NovaMetaDialog");
        });
    }
    // Modelo da meta criada
    public void onMetaCriada(String titulo, String descricao, int atual, int alvo) {
        Goal novaMeta = new Goal(
                titulo.isEmpty() ? "Nova Meta" : titulo,
                descricao.isEmpty() ? "Descrição da meta" : descricao,
                atual,
                alvo <= 0 ? 1000 : alvo,
                Color.parseColor("#4AA3FF"),
                R.drawable.ic_goal_flag
        );
        goals.add(novaMeta);
        goalAdapter.notifyItemInserted(goals.size() - 1);
        recyclerGoals.scrollToPosition(goals.size() - 1);
    }
}
