package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SnSItemTags extends ItemTagsProvider {

    public SnSItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, SwordsAndSorcery.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(Tags.Items.ORES)
                .add(SnSRegistry.SILVER_ORE_ITEM.get())
                .add(SnSRegistry.DEEPSLATE_SILVER_ORE_ITEM.get())
                .add(SnSRegistry.MANA_CRYSTAL_ORE_ITEM.get())
                .add(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE_ITEM.get());

        tag(Tags.Items.INGOTS)
                .add(SnSRegistry.SILVER_INGOT.get());

    }

    @Override
    public String getName() {
        return "SnS Tags";
    }
}
