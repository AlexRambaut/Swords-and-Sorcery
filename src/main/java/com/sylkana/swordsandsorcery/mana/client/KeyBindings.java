package com.sylkana.swordsandsorcery.mana.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBindings {

    public static final String SNS_KEY_CATEGORIES = "key.categories.sns";
    public static final String KEY_CAST_SPELL = "key.castSpell";

    public static KeyMapping castSpellKeyMapping;

    public static void init() {
        castSpellKeyMapping = new KeyMapping(KEY_CAST_SPELL, KeyConflictContext.IN_GAME,
                InputConstants.getKey("key.keyboard.period"), SNS_KEY_CATEGORIES);
        ClientRegistry.registerKeyBinding(castSpellKeyMapping);
    }
}
