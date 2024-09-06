package net.liopyu.menujs.client;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.MenuJS;
import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.container.AbstractMenuContainerBuilderJS;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;

import java.util.Map;

@Mod.EventBusSubscriber(modid = MenuJS.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    public static void init() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(ClientEventHandler::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        for (Map.Entry<ResourceLocation, AbstractMenuContainerBuilder<?>> entry : AbstractMenuContainerBuilderJS.thisList.entrySet()) {
            event.enqueueWork(() -> {
                MenuScreens.<AbstractContainerMenu, AbstractContainerScreenJS<AbstractContainerMenu>>register(
                        entry.getValue().get(),
                        (pMenu, pPlayerInventory, pTitle) ->
                                new AbstractContainerScreenJS<>(UtilsJS.cast(entry.getValue()), pMenu, pPlayerInventory, pTitle)
                );
            });
        }
    }
}
