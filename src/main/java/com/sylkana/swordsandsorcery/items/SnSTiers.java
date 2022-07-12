package com.sylkana.swordsandsorcery.items;

import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class SnSTiers {
    public static final ForgeTier MANABLADE = new ForgeTier(
            3,
            1561,
            2f,
            3f,
            50,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(SnSRegistry.MANA_CRYSTAL.get()));
}
