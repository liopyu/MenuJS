package net.liopyu.menujs;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MenuJS.MODID)
public class MenuJS {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "menujs";

    public MenuJS() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
