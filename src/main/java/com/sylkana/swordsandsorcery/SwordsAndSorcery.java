package com.sylkana.swordsandsorcery;

import com.mojang.logging.LogUtils;
import com.sylkana.swordsandsorcery.setup.ClientSetup;
import com.sylkana.swordsandsorcery.setup.Config;
import com.sylkana.swordsandsorcery.setup.ModSetup;
import com.sylkana.swordsandsorcery.setup.SnSRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SwordsAndSorcery.MODID)
public class SwordsAndSorcery {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "swordsandsorcery";

    public SwordsAndSorcery() {

        ModSetup.setup();
        // Register the DeferredRegistry
        SnSRegistry.init();
        Config.register();

        // The mod's event bus, to allow subscription to Mod specific events
        IEventBus snsbus = FMLJavaModLoadingContext.get().getModEventBus();
        snsbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> snsbus.addListener(ClientSetup::init));
    }
}
