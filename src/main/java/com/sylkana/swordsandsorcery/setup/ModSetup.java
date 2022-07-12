package com.sylkana.swordsandsorcery.setup;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.items.ArcaneShield;
import com.sylkana.swordsandsorcery.items.BetterBrewingRecipe;
import com.sylkana.swordsandsorcery.items.Manablade;
import com.sylkana.swordsandsorcery.mana.data.ManaEvents;
import com.sylkana.swordsandsorcery.worldgen.ores.Ores;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(modid = SwordsAndSorcery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "swordsandsorcery";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.AMETHYST_SHARD);
        }
    };

    public static void setup() {
        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.addListener(Ores::onBiomeLoadingEvent);
        // Different listener because of generics
        eventBus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
        eventBus.addListener(ManaEvents::onPlayerCloned);
        eventBus.addListener(ManaEvents::onRegisterCapabilities);
        eventBus.addListener(ManaEvents::onWorldTick);
        eventBus.addListener(ArcaneShield::onTakeDamage);
        eventBus.addListener(ArcaneShield::hideInHand);
        eventBus.addListener(Manablade::applyExtraManaDamage);
    }

    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ores.registerConfiguredFeatures();
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                    SnSRegistry.BLACK_LOTUS_ITEM.get(), SnSRegistry.MANA_POTION.get()));
        });
        Messaging.register();
    }
}
