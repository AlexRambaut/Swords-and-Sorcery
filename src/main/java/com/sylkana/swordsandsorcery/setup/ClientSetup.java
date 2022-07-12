package com.sylkana.swordsandsorcery.setup;

import com.sylkana.swordsandsorcery.mana.client.KeyBindings;
import com.sylkana.swordsandsorcery.mana.client.KeyInputHandler;
import com.sylkana.swordsandsorcery.mana.client.ManaOverlay;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
        KeyBindings.init();
        OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);

        ItemBlockRenderTypes.setRenderLayer(SnSRegistry.BLACK_LOTUS.get(), RenderType.cutout());
    }
}
