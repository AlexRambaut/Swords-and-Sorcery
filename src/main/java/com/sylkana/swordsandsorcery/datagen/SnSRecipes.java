package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class SnSRecipes extends RecipeProvider {

    public SnSRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(SnSRegistry.SILVER_ORE_ITEM.get()),
                SnSRegistry.SILVER_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_ore", has(SnSRegistry.SILVER_ORE_ITEM.get()))
                .save(consumer, "silver_ingot_from_ore_block");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(SnSRegistry.DEEPSLATE_SILVER_ORE_ITEM.get()),
                SnSRegistry.SILVER_INGOT.get(), 1.0f, 100)
                .unlockedBy("has_deepslate_ore", has(SnSRegistry.DEEPSLATE_SILVER_ORE_ITEM.get()))
                .save(consumer, "silver_ingot_from_deepslate_ore_block");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(SnSRegistry.RAW_SILVER.get()),
                SnSRegistry.SILVER_INGOT.get(), 0.0f, 100)
                .unlockedBy("has_raw", has(SnSRegistry.RAW_SILVER.get()))
                .save(consumer, "silver_ingot_from_raw_silver");

        ShapelessRecipeBuilder.shapeless(Items.BLACK_DYE)
                .requires(SnSRegistry.BLACK_LOTUS_ITEM.get())
                .unlockedBy("has_item", has(SnSRegistry.BLACK_LOTUS_ITEM.get()))
                .save(consumer, "black_dye_from_black_lotus");


    }
}
