package com.sylkana.swordsandsorcery.mana.client;

import com.sylkana.swordsandsorcery.mana.network.PacketCastSpell;
import com.sylkana.swordsandsorcery.setup.Messaging;
import net.minecraftforge.client.event.InputEvent;

public class KeyInputHandler {

    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.castSpellKeyMapping.consumeClick()) {
            Messaging.sendToServer(new PacketCastSpell());
        }
    }
}
