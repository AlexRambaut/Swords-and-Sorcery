package com.sylkana.swordsandsorcery.blocks;

import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import com.sylkana.swordsandsorcery.mana.network.PacketExtraRegenMana;
import com.sylkana.swordsandsorcery.mana.network.PacketSyncManaToClient;
import com.sylkana.swordsandsorcery.setup.Messaging;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class FocusingRuneBlock extends Block {

    private int counter = 0;

    public FocusingRuneBlock() {
        super(Properties.of(Material.AMETHYST)
                .sound(SoundType.AMETHYST)
                .strength(3f)
                .lightLevel(state -> state.getValue(BlockStateProperties.ENABLED) ? 14 : 0)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(BlockStateProperties.ENABLED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(BlockStateProperties.ENABLED, false);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        counter--;
        if (counter <= 0) {
            counter = 20;
            if(!pLevel.isClientSide()){
                if (pEntity instanceof LivingEntity) {
                    if (pEntity instanceof Player player) {
                        player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                            Messaging.sendToServer(new PacketExtraRegenMana(10));
                            Messaging.sendToPlayer(new PacketSyncManaToClient(playerMana.getCurrentMana(), playerMana.getMaxMana(), playerMana.getManaRegen()), (ServerPlayer)player);
                            pLevel.setBlock(pPos, pState.setValue(BlockStateProperties.ENABLED, Boolean.TRUE),3);
                        });
                    }
                }
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
