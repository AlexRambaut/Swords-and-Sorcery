package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SnSItemModels extends ItemModelProvider {

    public SnSItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, SwordsAndSorcery.MODID, helper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(SnSRegistry.SILVER_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/silver_ore"));
        withExistingParent(SnSRegistry.DEEPSLATE_SILVER_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_silver_ore"));
        withExistingParent(SnSRegistry.MANA_CRYSTAL_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/mana_crystal_ore"));
        withExistingParent(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE_ITEM.get().getRegistryName().getPath(), modLoc("block/deepslate_mana_crystal_ore"));
        withExistingParent(SnSRegistry.FOCUSING_RUNE_ITEM.get().getRegistryName().getPath(), modLoc("block/focusing_rune"));

        withExistingParent(SnSRegistry.BLACK_LOTUS_ITEM.get().getRegistryName().getPath(), modLoc("block/black_lotus"));

        singleTexture(SnSRegistry.RAW_SILVER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/raw_silver"));
        singleTexture(SnSRegistry.SILVER_INGOT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/silver_ingot"));
        singleTexture(SnSRegistry.MANA_CRYSTAL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/mana_crystal"));
        singleTexture(SnSRegistry.DEPLETED_MANA_CRYSTAL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("item/depleted_mana_crystal"));
        singleTexture(SnSRegistry.MANABLADE.get().getRegistryName().getPath(),
                mcLoc("item/handheld"),
                "layer0", modLoc("item/manablade"));
        singleTexture(SnSRegistry.ARCANE_SHIELD.get().getRegistryName().getPath(),
                mcLoc("item/handheld"),
                "layer0", modLoc("item/arcane_shield"));
    }
}
