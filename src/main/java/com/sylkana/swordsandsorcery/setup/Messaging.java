package com.sylkana.swordsandsorcery.setup;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.mana.network.PacketCastSpell;
import com.sylkana.swordsandsorcery.mana.network.PacketExtraRegenMana;
import com.sylkana.swordsandsorcery.mana.network.PacketSyncManaToClient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messaging {

    private static SimpleChannel INSTANCE;

    private static int packetID = 0;

    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel network = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SwordsAndSorcery.MODID, "messaging"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = network;

        network.messageBuilder(PacketSyncManaToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncManaToClient::new)
                .encoder(PacketSyncManaToClient::toBytes)
                .consumer(PacketSyncManaToClient::handle)
                .add();

        network.messageBuilder(PacketCastSpell.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketCastSpell::new)
                .encoder(PacketCastSpell::toBytes)
                .consumer(PacketCastSpell::handle)
                .add();

        network.messageBuilder(PacketExtraRegenMana.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketExtraRegenMana::new)
                .encoder(PacketExtraRegenMana::toBytes)
                .consumer(PacketExtraRegenMana::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
