package net.liopyu.menujs.client;

import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.MenuJS;
import net.liopyu.menujs.builders.AbstractContainerBuilder;
import net.liopyu.menujs.builders.menu.menujs.AbstractContainerBuilderJS;
import net.liopyu.menujs.client.screens.menujs.AbstractContainerScreenJS;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Map;

import static net.liopyu.menujs.builders.AbstractContainerBuilder.thisList;
import static net.liopyu.menujs.builders.AbstractMenuTypeJS.getScreenForBuilder;

@Mod.EventBusSubscriber(modid = MenuJS.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    public static void init() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(ClientEventHandler::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        for (Map.Entry<ResourceLocation, AbstractContainerBuilder<?>> entry : thisList.entrySet()) {
            event.enqueueWork(() -> {
                MenuScreens.<AbstractContainerMenu, AbstractContainerScreen<AbstractContainerMenu>>register(
                        entry.getValue().get(),
                        (pMenu, pPlayerInventory, pTitle) ->
                                UtilsJS.cast(getScreenForBuilder(UtilsJS.cast(entry.getValue()), pMenu, pPlayerInventory, pTitle))
                );
            });
        }
    }
}
