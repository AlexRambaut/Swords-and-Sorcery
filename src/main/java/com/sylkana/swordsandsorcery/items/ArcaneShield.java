package com.sylkana.swordsandsorcery.items;

import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import com.sylkana.swordsandsorcery.mana.network.PacketSyncManaToClient;
import com.sylkana.swordsandsorcery.setup.Messaging;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static net.minecraft.world.InteractionHand.OFF_HAND;

public class ArcaneShield extends ArmorItem {

    public ArcaneShield(ArmorMaterial pMaterial, EquipmentSlot equipmentSlot, Properties pProperties) {
        super(pMaterial, EquipmentSlot.OFFHAND, pProperties);
    }

    public static void hideInHand(RenderHandEvent event) {
        if (event.getItemStack().sameItem(new ItemStack(SnSRegistry.ARCANE_SHIELD.get()))) {
            if (event.getHand().equals(OFF_HAND)) {
                event.setCanceled(true);
            }
        }
    }

    public static void onTakeDamage(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof Player player) {
            if (player.getInventory().getItem(40).sameItem(new ItemStack(SnSRegistry.ARCANE_SHIELD.get()))) {
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                    float amount = event.getAmount();
                    float damageInMana = amount * 10;
                    float currentMana = (float) playerMana.getCurrentMana();
                    if (damageInMana > currentMana) {
                        event.setAmount((damageInMana - currentMana) / 10);
                        playerMana.setCurrentMana(0);
                    } else {
                        playerMana.setCurrentMana((int) (currentMana - damageInMana));
                        event.setAmount(0);
                        event.setCanceled(true);
                    }
                    Messaging.sendToPlayer(new PacketSyncManaToClient(playerMana.getCurrentMana(), playerMana.getMaxMana(), playerMana.getManaRegen()), (ServerPlayer) player);
                });
            }
        }
    }

}
