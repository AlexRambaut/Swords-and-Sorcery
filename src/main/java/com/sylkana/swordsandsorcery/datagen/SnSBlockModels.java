package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SnSBlockModels extends BlockModelProvider {

    public SnSBlockModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, SwordsAndSorcery.MODID, helper);
    }

    @Override
    protected void registerModels() {
        cross(SnSRegistry.BLACK_LOTUS.get().getRegistryName().getPath(), modLoc("block/black_lotus"));
    }
}
