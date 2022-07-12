package com.sylkana.swordsandsorcery.mana.client;

public class ClientManaData {

    private static int currentMana;
    private static int maxMana;
    private static int manaRegen;

    public static void set(int currentMana, int maxMana, int manaRegen) {
        ClientManaData.currentMana = currentMana;
        ClientManaData.maxMana = maxMana;
        ClientManaData.manaRegen = manaRegen;
    }

    public static int getCurrentMana() {
        return currentMana;
    }

    public static int getMaxMana() {
        return maxMana;
    }

    public static int getManaRegen() {
        return manaRegen;
    }

}
