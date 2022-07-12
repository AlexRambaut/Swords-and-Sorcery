package com.sylkana.swordsandsorcery.mana.data;

import com.sylkana.swordsandsorcery.mana.network.PacketSyncManaToClient;
import com.sylkana.swordsandsorcery.setup.Messaging;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;

public class ManaManager extends SavedData {

    private int counter = 0;

    @Nonnull
    public static ManaManager get(Level level) {
        if (level.isClientSide) {
            throw new RuntimeException("Warning: Client-side!");
        }
        DimensionDataStorage storage = ((ServerLevel) level).getDataStorage();
        return storage.computeIfAbsent(ManaManager::new, ManaManager::new, "manamanager");
    }


    public void tick(Level level) {
        counter--;
        if (counter <= 0) {
            // Once per second
            counter = 20;
            // Sync mana to the players
            for (Player player : level.players()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    int playerCurrentMana = serverPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(PlayerMana::getCurrentMana)
                            .orElse(-1);
                    int playerMaxMana = serverPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(PlayerMana::getMaxMana)
                            .orElse(-1);
                    int playerManaRegen = serverPlayer.getCapability(PlayerManaProvider.PLAYER_MANA)
                            .map(PlayerMana::getManaRegen)
                            .orElse(-1);
                    // Mana Regen
                    int scaledManaRegenBonus = (int)(playerMaxMana*0.01);
                    serverPlayer.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                        if ((playerCurrentMana + playerManaRegen + scaledManaRegenBonus) > playerMaxMana) {
                            playerMana.setCurrentMana(playerMaxMana);
                        } else if ((playerCurrentMana < playerMaxMana) && ((playerCurrentMana + playerManaRegen + scaledManaRegenBonus) <= playerMaxMana)) {
                            playerMana.setCurrentMana(playerCurrentMana + playerManaRegen + scaledManaRegenBonus);
                        }
                    });
                    Messaging.sendToPlayer(new PacketSyncManaToClient(playerCurrentMana, playerMaxMana, playerManaRegen), serverPlayer);
                }
            }
        }
    }

    public ManaManager() {
    }

    public ManaManager(CompoundTag compoundTag) {

    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        return compoundTag;
    }
}
