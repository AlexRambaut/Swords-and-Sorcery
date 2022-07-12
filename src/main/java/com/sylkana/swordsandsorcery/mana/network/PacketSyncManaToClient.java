package com.sylkana.swordsandsorcery.mana.network;

import com.sylkana.swordsandsorcery.mana.client.ClientManaData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSyncManaToClient {

    private final int currentMana;
    private final int maxMana;
    private final int manaRegen;

    public PacketSyncManaToClient(int currentMana, int maxMana, int manaRegen) {
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.manaRegen = manaRegen;
    }

    public PacketSyncManaToClient(FriendlyByteBuf buffer) {
        currentMana = buffer.readInt();
        maxMana = buffer.readInt();
        manaRegen = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(currentMana);
        buffer.writeInt(maxMana);
        buffer.writeInt(manaRegen);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientManaData.set(currentMana, maxMana, manaRegen);
        });
        return true;
    }
}
