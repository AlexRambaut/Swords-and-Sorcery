package com.sylkana.swordsandsorcery.mana.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerMana> PLAYER_MANA = CapabilityManager.get(new CapabilityToken<>(){});

    private PlayerMana playerMana = null;
    private final LazyOptional<PlayerMana> optional = LazyOptional.of(this::createPlayerMana);

    @Nonnull
    private PlayerMana createPlayerMana() {
        if (playerMana == null) {
            playerMana = new PlayerMana();
            playerMana.setMaxMana(100);
            playerMana.setManaRegen(1);
        }
        return playerMana;
    }


    // Capability Handlers
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_MANA) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return getCapability(cap);
    }


    // NBT Handlers
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        createPlayerMana().saveNBTData(compoundTag);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        createPlayerMana().loadNBTData(compoundTag);
    }
}
