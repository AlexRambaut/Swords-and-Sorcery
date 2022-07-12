package com.sylkana.swordsandsorcery.items;

import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import com.sylkana.swordsandsorcery.mana.network.PacketSyncManaToClient;
import com.sylkana.swordsandsorcery.setup.Messaging;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Manablade extends SwordItem {

    private static final float maxManaDamageBonus = 5;

    public Manablade(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public static void applyExtraManaDamage(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                float amount = event.getAmount();
                float damageInMana = maxManaDamageBonus * 10;
                int currentMana = playerMana.getCurrentMana();
                if (currentMana >= damageInMana) {
                    event.setAmount(amount + maxManaDamageBonus);
                    playerMana.setCurrentMana(currentMana - (int)damageInMana);
                } else {
                    event.setAmount(amount + ((float)currentMana)/10);
                    playerMana.setCurrentMana(0);
                }
                Messaging.sendToPlayer(new PacketSyncManaToClient(playerMana.getCurrentMana(), playerMana.getMaxMana(), playerMana.getManaRegen()), (ServerPlayer)player);
            });
        }
    }

}
