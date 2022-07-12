package com.sylkana.swordsandsorcery.mana.network;

import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketExtraRegenMana {

    private final int extraManaRegen;

    public PacketExtraRegenMana(int extraManaRegen) {
        this.extraManaRegen = extraManaRegen;
    }

    public PacketExtraRegenMana(FriendlyByteBuf buffer) {
        extraManaRegen = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(extraManaRegen);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                int playerCurrentMana = playerMana.getCurrentMana();
                int playerMaxMana = playerMana.getMaxMana();
                if ((playerCurrentMana + extraManaRegen) > playerMaxMana) {
                    playerMana.setCurrentMana(playerMaxMana);
                } else if ((playerCurrentMana < playerMaxMana) && ((playerCurrentMana + extraManaRegen) <= playerMaxMana)) {
                    playerMana.setCurrentMana(playerCurrentMana + extraManaRegen);
                }
            });
        });
        return true;
    }
}
