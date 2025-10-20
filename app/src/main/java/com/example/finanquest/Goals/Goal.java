package com.example.finanquest.Goals;

public class Goal {
    private String name;
    private String description;
    private int currentValue;
    private int targetValue;
    private int color;
    private int iconResId;

    public Goal(String name, String description, int currentValue, int targetValue, int color, int iconResId) {
        this.name = name;
        this.description = description;
        this.currentValue = currentValue;
        this.targetValue = targetValue;
        this.color = color;
        this.iconResId = iconResId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public int getColor() {
        return color;
    }

    public int getIconResId() {
        return iconResId;
    }

    // Setters opcionais caso queira atualizar os valores depois
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
