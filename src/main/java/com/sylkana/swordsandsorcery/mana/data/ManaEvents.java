package com.sylkana.swordsandsorcery.mana.data;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ManaEvents {

    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(SwordsAndSorcery.MODID, "properties"), new PlayerManaProvider());
            }
        }
    }

    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                event.getPlayer().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMana.class);
    }

    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        // Beware of being client side
        if (event.world.isClientSide) {
            return;
        }
        // Two tick events per second; ignoring one
        if (event.phase == TickEvent.Phase.START) {
            return;
        }
        ManaManager manaManager = ManaManager.get(event.world);
        manaManager.tick(event.world);
    }

}
