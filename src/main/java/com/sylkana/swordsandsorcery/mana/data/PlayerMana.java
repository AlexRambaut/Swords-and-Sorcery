package com.sylkana.swordsandsorcery.mana.data;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {

    private int currentMana;
    private int maxMana;
    private int manaRegen;

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

    public void applyScaledMaxManaIncrease(){this.maxMana += (int)(this.maxMana*0.01);}

    public void copyFrom(PlayerMana source) {
        currentMana = source.currentMana;
    }

    // NBT Handlers
    public void saveNBTData(CompoundTag compoundTag) {
        compoundTag.putInt("currentmana", currentMana);
        compoundTag.putInt("maxmana", maxMana);
        compoundTag.putInt("manaregen", manaRegen);
    }

    public void loadNBTData(CompoundTag compoundTag) {
        currentMana = compoundTag.getInt("currentmana");
        maxMana = compoundTag.getInt("maxmana");
        manaRegen = compoundTag.getInt("manaregen");
    }
}
