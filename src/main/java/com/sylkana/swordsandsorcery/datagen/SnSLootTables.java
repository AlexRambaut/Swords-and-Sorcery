package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;

public class SnSLootTables extends BaseLootTableProvider {

    public SnSLootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        lootTables.put(SnSRegistry.SILVER_ORE.get(),
                createFortuneSilkTable("silver_ore", SnSRegistry.SILVER_ORE.get(), SnSRegistry.RAW_SILVER.get(), 1, 3));
        lootTables.put(SnSRegistry.DEEPSLATE_SILVER_ORE.get(),
                createFortuneSilkTable("deepslate_silver_ore", SnSRegistry.DEEPSLATE_SILVER_ORE.get(), SnSRegistry.RAW_SILVER.get(), 1, 3));
        lootTables.put(SnSRegistry.MANA_CRYSTAL_ORE.get(),
                createFortuneSilkTable("mana_crystal_ore", SnSRegistry.MANA_CRYSTAL_ORE.get(), SnSRegistry.MANA_CRYSTAL.get(), 1, 2));
        lootTables.put(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE.get(),
                createFortuneSilkTable("deepslate_mana_crystal_ore", SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE.get(), SnSRegistry.MANA_CRYSTAL.get(), 1, 2));
        lootTables.put(SnSRegistry.FOCUSING_RUNE.get(),
                createSimpleTable("focusing_rune", SnSRegistry.FOCUSING_RUNE.get()));
        lootTables.put(SnSRegistry.BLACK_LOTUS.get(),
                createSimpleTable("black_lotus", SnSRegistry.BLACK_LOTUS.get()));
    }
}
