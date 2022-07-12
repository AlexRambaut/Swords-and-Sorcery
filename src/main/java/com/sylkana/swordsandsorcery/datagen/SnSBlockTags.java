package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SnSBlockTags extends BlockTagsProvider {

    public SnSBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, SwordsAndSorcery.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SnSRegistry.SILVER_ORE.get())
                .add(SnSRegistry.DEEPSLATE_SILVER_ORE.get())
                .add(SnSRegistry.MANA_CRYSTAL_ORE.get())
                .add(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE.get())
                .add(SnSRegistry.FOCUSING_RUNE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(SnSRegistry.SILVER_ORE.get())
                .add(SnSRegistry.DEEPSLATE_SILVER_ORE.get())
                .add(SnSRegistry.MANA_CRYSTAL_ORE.get())
                .add(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE.get())
                .add(SnSRegistry.FOCUSING_RUNE.get());

        tag(Tags.Blocks.ORES)
                .add(SnSRegistry.SILVER_ORE.get())
                .add(SnSRegistry.DEEPSLATE_SILVER_ORE.get())
                .add(SnSRegistry.MANA_CRYSTAL_ORE.get());
    }

    @Override
    public String getName() {
        return "SnS Tags";
    }
}
