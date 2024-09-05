package net.liopyu.menujs.client;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.MenuJS;
import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.container.AbstractMenuContainerBuilderJS;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = MenuJS.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    public static void init() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(ClientEventHandler::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        for (AbstractMenuContainerBuilder<?> builder : AbstractMenuContainerBuilderJS.thisList) {
            event.enqueueWork(() -> {
                MenuScreens.<AbstractContainerMenu, AbstractContainerScreenJS<AbstractContainerMenu>>register(
                        builder.get(),
                        (pMenu, pPlayerInventory, pTitle) ->
                                new AbstractContainerScreenJS<>(UtilsJS.cast(builder), pMenu, pPlayerInventory, pTitle)
                );
            });
        }
    }
}
