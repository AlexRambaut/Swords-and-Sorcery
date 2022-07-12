package com.sylkana.swordsandsorcery.worldgen.ores;

import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Ores {

    public static Holder<PlacedFeature> OVERWORLD_OREGEN;
    public static Holder<PlacedFeature> DEEPSLATE_OREGEN;

    public static void registerConfiguredFeatures() {

        OreConfiguration overworldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, SnSRegistry.SILVER_ORE.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        OVERWORLD_OREGEN = registerPlacedFeature("silver_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig),
                CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));


        OreConfiguration deepslateConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SnSRegistry.DEEPSLATE_SILVER_ORE.get().defaultBlockState(), OresConfig.DEEPSLATE_VEINSIZE.get());
        DEEPSLATE_OREGEN = registerPlacedFeature("deepslate_silver_ore", new ConfiguredFeature<>(Feature.ORE, deepslateConfig),
                CountPlacement.of(OresConfig.DEEPSLATE_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_OREGEN);
        }
    }

}
