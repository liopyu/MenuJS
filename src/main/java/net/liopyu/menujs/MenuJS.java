package net.liopyu.menujs;

import com.mojang.logging.LogUtils;
import net.liopyu.menujs.client.ClientEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(MenuJS.MODID)
public class MenuJS {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "menujs";

    public MenuJS() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientEventHandler.init();
        }
    }
}
