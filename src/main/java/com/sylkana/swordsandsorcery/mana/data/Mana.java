package com.sylkana.swordsandsorcery.mana.data;

public class Mana {

    private int currentMana;
    private int maxMana;
    private int manaRegen;

    public Mana(int currentMana, int maxMana, int manaRegen) {
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.manaRegen = manaRegen;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(int manaRegen) {
        this.manaRegen = manaRegen;
    }

}
