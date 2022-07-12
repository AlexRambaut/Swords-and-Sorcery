package com.sylkana.swordsandsorcery.worldgen.ores;

import net.minecraftforge.common.ForgeConfigSpec;

public class OresConfig {

    public static ForgeConfigSpec.IntValue OVERWORLD_VEINSIZE;
    public static ForgeConfigSpec.IntValue OVERWORLD_AMOUNT;
    public static ForgeConfigSpec.IntValue DEEPSLATE_VEINSIZE;
    public static ForgeConfigSpec.IntValue DEEPSLATE_AMOUNT;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for ore generation").push("ores");

        OVERWORLD_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in stone")
                .defineInRange("overworldVeinsize", 5, 1, Integer.MAX_VALUE);
        OVERWORLD_AMOUNT = COMMON_BUILDER
                .comment("Amount of veins of our ore in stone")
                .defineInRange("overworldAmount", 3, 1, Integer.MAX_VALUE);
        DEEPSLATE_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in deepslate")
                .defineInRange("deepslateVeinsize", 5, 1, Integer.MAX_VALUE);
        DEEPSLATE_AMOUNT = COMMON_BUILDER
                .comment("Amount of veins of our ore in deepslate")
                .defineInRange("deepslateAmount", 3, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
}
