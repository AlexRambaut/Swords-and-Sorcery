package com.sylkana.swordsandsorcery.mana.network;

import com.sylkana.swordsandsorcery.mana.ManaConfig;
import com.sylkana.swordsandsorcery.mana.data.ManaManager;
import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketCastSpell {

    public static final String MESSAGE_NOT_ENOUGH_MANA = "message.notenoughmana";

    public PacketCastSpell() {
    }

    public PacketCastSpell(FriendlyByteBuf buffer) {
    }

    public void toBytes(FriendlyByteBuf buffer) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                if (playerMana.getCurrentMana() < ManaConfig.TEST_SPELL_COST.get()) {
                    player.sendMessage(new TranslatableComponent(MESSAGE_NOT_ENOUGH_MANA).withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                } else {
                    playerMana.setCurrentMana(playerMana.getCurrentMana() - ManaConfig.TEST_SPELL_COST.get());
                    playerMana.applyScaledMaxManaIncrease();
                }
            });
        });
        return true;
    }
}
