package com.sylkana.swordsandsorcery.mana.effects;

import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import com.sylkana.swordsandsorcery.mana.network.PacketExtraRegenMana;
import com.sylkana.swordsandsorcery.setup.Messaging;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ManaRegenEffect extends MobEffect {

    public ManaRegenEffect(MobEffectCategory pCategory, int pColor) {
        super(MobEffectCategory.BENEFICIAL, 3124687);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(!pLivingEntity.level.isClientSide && pLivingEntity instanceof Player player) {
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                Messaging.sendToServer(new PacketExtraRegenMana(1));
            });
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

}
