package com.sylkana.swordsandsorcery.items;

import com.sylkana.swordsandsorcery.mana.data.PlayerMana;
import com.sylkana.swordsandsorcery.mana.data.PlayerManaProvider;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ManaCrystal extends Item {

    public ManaCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLivingEntity instanceof Player player) {
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                int currentMax = playerMana.getMaxMana();
                playerMana.setMaxMana(currentMax + 100);
            });
        }
        return new ItemStack(SnSRegistry.DEPLETED_MANA_CRYSTAL.get());
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
