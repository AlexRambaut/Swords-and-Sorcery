package com.sylkana.swordsandsorcery.setup;

import com.sylkana.swordsandsorcery.blocks.FocusingRuneBlock;
import com.sylkana.swordsandsorcery.items.ArcaneShield;
import com.sylkana.swordsandsorcery.items.ManaCrystal;
import com.sylkana.swordsandsorcery.items.Manablade;
import com.sylkana.swordsandsorcery.items.SnSTiers;
import com.sylkana.swordsandsorcery.mana.effects.ManaRegenEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.sylkana.swordsandsorcery.SwordsAndSorcery.MODID;

public class SnSRegistry {

    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MOB_EFFECTS.register(eventBus);
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        POTIONS.register(eventBus);
    }

    // Effects
    public static final RegistryObject<MobEffect> MANA_REGEN = MOB_EFFECTS.register("mana_regen", () -> new ManaRegenEffect(MobEffectCategory.BENEFICIAL, 3124687));

    // Common Properties
    public static final Item.Properties COMMON_ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);
    public static final BlockBehaviour.Properties ORE_BLOCK_PROPERTIES = Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F);
    public static final BlockBehaviour.Properties DEEPSLATE_ORE_BLOCK_PROPERTIES = Block.Properties.of(Material.STONE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE);

    // BlockItem Creator
    public static <B extends Block> RegistryObject<Item> makeItemFromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), COMMON_ITEM_PROPERTIES));
    }

    // Simple Blocks
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> SILVER_ORE_ITEM = makeItemFromBlock(SILVER_ORE);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore", () -> new Block(DEEPSLATE_ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> DEEPSLATE_SILVER_ORE_ITEM = makeItemFromBlock(DEEPSLATE_SILVER_ORE);
    public static final RegistryObject<Block> MANA_CRYSTAL_ORE = BLOCKS.register("mana_crystal_ore", () -> new Block(ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MANA_CRYSTAL_ORE_ITEM = makeItemFromBlock(MANA_CRYSTAL_ORE);
    public static final RegistryObject<Block> DEEPSLATE_MANA_CRYSTAL_ORE = BLOCKS.register("deepslate_mana_crystal_ore", () -> new Block(DEEPSLATE_ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> DEEPSLATE_MANA_CRYSTAL_ORE_ITEM = makeItemFromBlock(DEEPSLATE_MANA_CRYSTAL_ORE);

    // Simple Items
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(COMMON_ITEM_PROPERTIES));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(COMMON_ITEM_PROPERTIES));

    // Mana Crystal
    public static final RegistryObject<Item> MANA_CRYSTAL = ITEMS.register("mana_crystal", () -> new ManaCrystal(COMMON_ITEM_PROPERTIES));
    public static final RegistryObject<Item> DEPLETED_MANA_CRYSTAL = ITEMS.register("depleted_mana_crystal", () -> new Item(COMMON_ITEM_PROPERTIES));

    // Flowers
    public static final RegistryObject<Block> BLACK_LOTUS = BLOCKS.register("black_lotus", () -> new FlowerBlock(MobEffects.GLOWING, 4, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistryObject<Item> BLACK_LOTUS_ITEM = makeItemFromBlock(BLACK_LOTUS);
//    public static final RegistryObject<FlowerPotBlock> POTTED_BLACK_LOTUS = BLOCKS.register("potted_black_lotus", () -> new FlowerPotBlock(null, BLACK_LOTUS, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION)));

    // Focusing Rune
    public static final RegistryObject<Block> FOCUSING_RUNE = BLOCKS.register("focusing_rune", FocusingRuneBlock::new);
    public static final RegistryObject<Item> FOCUSING_RUNE_ITEM = makeItemFromBlock(FOCUSING_RUNE);

    // Weapons
    public static final RegistryObject<Item> MANABLADE = ITEMS.register("manablade", () -> new Manablade(SnSTiers.MANABLADE, 4, 2f, new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1)));

    // Arcane Shield
    public static final RegistryObject<Item> ARCANE_SHIELD = ITEMS.register("arcane_shield", () -> new ArcaneShield(ArmorMaterials.DIAMOND, EquipmentSlot.OFFHAND, new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1)));

    // Potions
    public static final RegistryObject<Potion> MANA_POTION = POTIONS.register("mana_potion",
            () -> new Potion(new MobEffectInstance(SnSRegistry.MANA_REGEN.get(), 200, 0)));

}
