package com.example.paysmart.Controllers.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paysmart.*;
import com.example.paysmart.Controllers.Adapters.CategoriaAnalyticsAdapter;
import com.example.paysmart.Models.CategoriaAnalytics;
import com.example.paysmart.Models.Transaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        // Para manter o ícone ativo
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_analytics);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_analytics) {
                return true;
            } else if (id == R.id.nav_home) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
            } else if (id == R.id.nav_goals) {
                startActivity(new Intent(this, GoalsActivity.class));
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


        pieChart = findViewById(R.id.pieChart);

        List<Transaction> transactions = (List<Transaction>) getIntent().getSerializableExtra("transactions");

        // Agrupa os valores por tipo de categoria
        Map<String, Float> categoriaSomas = new HashMap<>();

        for (Transaction t : transactions) {
            String categoria = t.getType();
            String valorStr = t.getAmount().replace("R$", "")
                    .replace("+", "").replace("-", "")
                    .replace(",", ".").trim();

            float valor = 0f;
            try {
                valor = Float.parseFloat(valorStr);
            } catch (NumberFormatException ignored) {}

            // Ignora receitas (mostramos apenas gastos)
            if (!t.getAmount().contains("-")) continue;

            categoriaSomas.put(categoria, categoriaSomas.getOrDefault(categoria, 0f) + valor);
        }

        // Cria as entradas do gráfico
        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Float> entry : categoriaSomas.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(new int[]{
                Color.parseColor("#C77DFF"),
                Color.parseColor("#FF4D4D"),
                Color.parseColor("#00B4FF"),
                Color.parseColor("#00FF7F"),
                Color.parseColor("#FFD700"),
                Color.parseColor("#6A5ACD")
        });
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        dataSet.setDrawValues(false);
        pieChart.setDrawEntryLabels(false);

        // Configura o gráfico
        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.parseColor("#0F0E2E"));
        pieChart.setCenterText("Gastos");
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setEntryLabelColor(Color.WHITE);

        Legend legend = pieChart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(12f);
        legend.setForm(Legend.LegendForm.CIRCLE);

        pieChart.animateY(1400);
        pieChart.invalidate();

        RecyclerView recyclerCategorias = findViewById(R.id.recyclerCategoriasAnalytics);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this));

// Soma total dos gastos
        float total = 0f;
        for (float valor : categoriaSomas.values()) {
            total += valor;
        }

// Cria lista com porcentagens
        List<CategoriaAnalytics> listaCategorias = new ArrayList<>();
        for (Map.Entry<String, Float> entry : categoriaSomas.entrySet()) {
            float porcentagem = (entry.getValue() / total) * 100f;
            listaCategorias.add(new CategoriaAnalytics(entry.getKey(), porcentagem));
        }

// Define o adapter
        CategoriaAnalyticsAdapter categoriaAdapter = new CategoriaAnalyticsAdapter(listaCategorias);
        recyclerCategorias.setAdapter(categoriaAdapter);



    }


}
