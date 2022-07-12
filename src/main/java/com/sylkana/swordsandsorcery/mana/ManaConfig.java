package com.sylkana.swordsandsorcery.mana;

import net.minecraftforge.common.ForgeConfigSpec;

public class ManaConfig {

    public static ForgeConfigSpec.IntValue MANA_HUD_X;
    public static ForgeConfigSpec.IntValue MANA_HUD_Y;
    public static ForgeConfigSpec.IntValue MANA_HUD_COLOR;
    public static ForgeConfigSpec.IntValue TEST_SPELL_COST;


    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Settings for mana").push("mana");
        SERVER_BUILDER.pop();
    }

    public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER) {
        CLIENT_BUILDER.comment("Settings for mana HUD").push("mana");

        TEST_SPELL_COST = CLIENT_BUILDER
                .comment("Cost of the test spell")
                .defineInRange("testSpell", 10, 0, Integer.MAX_VALUE);
        MANA_HUD_X = CLIENT_BUILDER
                .comment("X location of the mana display")
                .defineInRange("manaHudX", 10, -1, Integer.MAX_VALUE);
        MANA_HUD_Y = CLIENT_BUILDER
                .comment("Y location of the mana display")
                .defineInRange("manaHudY", 10, -1, Integer.MAX_VALUE);
        MANA_HUD_COLOR = CLIENT_BUILDER
                .comment("Color of the mana display")
                .defineInRange("manaHudColor", 0xffffffff, Integer.MIN_VALUE, Integer.MAX_VALUE);

        CLIENT_BUILDER.pop();
    }
}
