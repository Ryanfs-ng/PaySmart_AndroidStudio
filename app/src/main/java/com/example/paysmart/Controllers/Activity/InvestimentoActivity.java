package com.example.paysmart.Controllers.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paysmart.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InvestimentoActivity extends AppCompatActivity {

    private PieChart pieChart;
    private TextView totalInvestidoText;
    private LinearLayout investmentsContainer;
    private ArrayList<PieEntry> entries = new ArrayList<>();
    private float totalInvestido = 32450f;

    private LinearLayout dynamicInvestmentsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimentos);

        pieChart = findViewById(R.id.investmentChart);
        totalInvestidoText = findViewById(R.id.totalInvestidoText);
        investmentsContainer = findViewById(R.id.investmentsContainer);
        dynamicInvestmentsContainer = findViewById(R.id.dynamicInvestmentsContainer);

        setupChart();
        setupBottomNavigation();

        totalInvestidoText.setText("R$ 32.450,00");

        FloatingActionButton btnAdd = findViewById(R.id.btnAddInvestment);
        btnAdd.setOnClickListener(v -> showAddInvestmentDialog());
    }

    private void setupChart() {
        entries.clear();
        entries.add(new PieEntry(1250f, "Tesouro Direto"));
        entries.add(new PieEntry(2740f, "Ações"));
        entries.add(new PieEntry(3890f, "Criptomoedas"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(
                Color.parseColor("#1E3A5F"),
                Color.parseColor("#324C7C"),
                Color.parseColor("#4B6A9B")
        );
        dataSet.setValueTextColor(Color.parseColor("#E0E0E0"));
        dataSet.setValueTextSize(13f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleColor(Color.parseColor("#1E1E1E"));
        pieChart.setTransparentCircleColor(Color.parseColor("#1E1E1E"));
        pieChart.setEntryLabelColor(Color.parseColor("#E0E0E0"));
        pieChart.getLegend().setEnabled(false);
        pieChart.animateY(1200);
        pieChart.invalidate();
    }

    private void addInvestmentCard(String name, String rendimentoText, String valorText) {
        // container principal do card
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.setMargins(0, 0, 0, dpToPx(12));
        card.setLayoutParams(cardParams);
        card.setPadding(dpToPx(16), dpToPx(12), dpToPx(16), dpToPx(12));
        card.setBackgroundColor(Color.parseColor("#2B2B2B"));
        card.setGravity(Gravity.CENTER_VERTICAL);

        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.outline_attach_money_24);
        icon.setMinimumWidth(20);
        icon.setColorFilter(Color.parseColor("#1E3A5F"));
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(dpToPx(40), dpToPx(40));
        iconParams.setMarginEnd(dpToPx(12));
        icon.setLayoutParams(iconParams);
        card.addView(icon);

        LinearLayout texts = new LinearLayout(this);
        texts.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams textsParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        texts.setLayoutParams(textsParams);

        TextView tvName = new TextView(this);
        tvName.setText(name);
        tvName.setTextColor(Color.parseColor("#E0E0E0"));
        tvName.setTextSize(16);
        tvName.setTypeface(null, Typeface.BOLD);

        TextView tvReturn = new TextView(this);
        tvReturn.setText(rendimentoText);
        tvReturn.setTextColor(Color.parseColor("#6B7280"));
        tvReturn.setTextSize(14);

        texts.addView(tvName);
        texts.addView(tvReturn);
        card.addView(texts);

        TextView tvValue = new TextView(this);
        tvValue.setText(valorText);
        tvValue.setTextColor(Color.parseColor("#1E3A5F"));
        tvValue.setTypeface(null, Typeface.BOLD);
        tvValue.setTextSize(14);
        card.addView(tvValue);

        dynamicInvestmentsContainer.addView(card);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private void showAddInvestmentDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_investment);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        EditText inputName = dialog.findViewById(R.id.inputInvestmentName);
        EditText inputReturn = dialog.findViewById(R.id.inputInvestmentReturn);
        EditText inputValue = dialog.findViewById(R.id.inputInvestmentValue);
        Button btnSave = dialog.findViewById(R.id.btnSaveInvestment);

        btnSave.setOnClickListener(v -> {
            String name = inputName.getText().toString().trim();
            String rendimentoStr = inputReturn.getText().toString().trim().replace(",", ".");
            String valorStr = inputValue.getText().toString().trim().replace(",", ".");

            if (name.isEmpty() || rendimentoStr.isEmpty() || valorStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            float rendimento;
            float valor;
            try {
                rendimento = Float.parseFloat(rendimentoStr);
                valor = Float.parseFloat(valorStr);
            } catch (NumberFormatException ex) {
                Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            // adiciona ao gráfico
            entries.add(new PieEntry(valor, name));
            totalInvestido += valor;
            updateChart();

            totalInvestidoText.setText(String.format("R$ %.2f", totalInvestido));

            // cria card visual
            addInvestmentCard(name, String.format("Rendimento: %.2f%% a.a", rendimento), String.format("+R$ %.2f", valor));

            dialog.dismiss();
            Toast.makeText(this, "Investimento adicionado!", Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }

    private void updateChart() {
        float somaTotal = 0f;
        for (PieEntry e : entries) {
            somaTotal += e.getValue();
        }

        ArrayList<PieEntry> normalizedEntries = new ArrayList<>();
        for (PieEntry e : entries) {
            float percentual = (e.getValue() / somaTotal) * 100f;
            normalizedEntries.add(new PieEntry(percentual, e.getLabel()));
        }

        PieDataSet dataSet = new PieDataSet(normalizedEntries, "");
        dataSet.setColors(
                Color.parseColor("#1E3A5F"),
                Color.parseColor("#324C7C"),
                Color.parseColor("#4B6A9B"),
                Color.parseColor("#5C8ACF"),
                Color.parseColor("#7899E2")
        );
        dataSet.setValueTextColor(Color.parseColor("#E0E0E0"));
        dataSet.setValueTextSize(13f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setUsePercentValues(true);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_achievements);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
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
                startActivity(new Intent(this, MetasActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            } else if (id == R.id.nav_achievements) {
                Toast.makeText(this, "Investimentos", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, PerfilActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
            }

            return false;
        });


    }
}
