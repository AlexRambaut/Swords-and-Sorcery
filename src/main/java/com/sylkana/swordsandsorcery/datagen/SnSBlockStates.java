package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SnSBlockStates extends BlockStateProvider {

    public SnSBlockStates(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, SwordsAndSorcery.MODID, helper);
    }

    // Generates blockstates json based on the names in SnSRegistry
    @Override
    protected void registerStatesAndModels() {
        simpleBlock(SnSRegistry.SILVER_ORE.get());
        simpleBlock(SnSRegistry.DEEPSLATE_SILVER_ORE.get());
        simpleBlock(SnSRegistry.MANA_CRYSTAL_ORE.get());
        simpleBlock(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE.get());
        simpleBlock(SnSRegistry.FOCUSING_RUNE.get());
        simpleBlock(SnSRegistry.BLACK_LOTUS.get());
//        simpleBlock(SnSRegistry.POTTED_BLACK_LOTUS.get());
    }
}
