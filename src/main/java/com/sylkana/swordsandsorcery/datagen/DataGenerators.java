package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SwordsAndSorcery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new SnSRecipes(generator));
            generator.addProvider(new SnSLootTables(generator));
            SnSBlockTags blockTags = new SnSBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new SnSItemTags(generator, blockTags, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            generator.addProvider(new SnSBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new SnSBlockModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new SnSItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new SnSLanguageProvider(generator, "en_us"));
        }
    }

}
