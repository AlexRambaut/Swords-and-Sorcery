package com.sylkana.swordsandsorcery.datagen;

import com.sylkana.swordsandsorcery.SwordsAndSorcery;
import com.sylkana.swordsandsorcery.mana.client.KeyBindings;
import com.sylkana.swordsandsorcery.mana.network.PacketCastSpell;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.sylkana.swordsandsorcery.setup.ModSetup.TAB_NAME;

public class SnSLanguageProvider extends LanguageProvider {

    public SnSLanguageProvider(DataGenerator generator, String locale) {
        super(generator, SwordsAndSorcery.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + TAB_NAME, "Swords and Sorcery");

        add(SnSRegistry.SILVER_ORE.get(), "Silver Ore");
        add(SnSRegistry.DEEPSLATE_SILVER_ORE.get(), "Deepslate Silver Ore");
        add(SnSRegistry.RAW_SILVER.get(), "Raw Silver");
        add(SnSRegistry.SILVER_INGOT.get(), "Silver Ingot");
        add(SnSRegistry.MANA_CRYSTAL_ORE.get(), "Mana Crystal Ore");
        add(SnSRegistry.DEEPSLATE_MANA_CRYSTAL_ORE.get(), "Deepslate Mana Crystal Ore");
        add(SnSRegistry.MANA_CRYSTAL.get(), "Mana Crystal");
        add(SnSRegistry.DEPLETED_MANA_CRYSTAL.get(), "Depleted Mana Crystal");
        add(SnSRegistry.MANABLADE.get(), "Manablade");
        add(SnSRegistry.ARCANE_SHIELD.get(), "Arcane Shield");
        add(SnSRegistry.FOCUSING_RUNE.get(), "Focusing Rune");
        add(SnSRegistry.MANA_REGEN.get(), "Mana Regeneration");
        add(SnSRegistry.BLACK_LOTUS.get(), "Black Lotus");
//        add(SnSRegistry.POTTED_BLACK_LOTUS.get(), "Potted Black Lotus");
        add(KeyBindings.SNS_KEY_CATEGORIES, "Swords and Sorcery");
        add(KeyBindings.KEY_CAST_SPELL, "Cast Spell");
        add(PacketCastSpell.MESSAGE_NOT_ENOUGH_MANA, "Not enough mana!");
        add("item.minecraft.potion.effect.mana_potion", "Mana Potion");
    }
}
