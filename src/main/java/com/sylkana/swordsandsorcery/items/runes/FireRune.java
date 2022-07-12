package com.sylkana.swordsandsorcery.items.runes;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class FireRune extends BaseRuneItem {

    public FireRune(Properties pProperties) {
        super(pProperties);
    }

    public MobEffect getMobEffect (){
        return MobEffects.FIRE_RESISTANCE;
    }

    public Enchantment getEnchantment() {
        return Enchantments.FIRE_ASPECT;
    }

}
