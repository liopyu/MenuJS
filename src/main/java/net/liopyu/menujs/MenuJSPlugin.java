package net.liopyu.menujs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.liopyu.menujs.builders.menu.menujs.AbstractContainerBuilderJS;
import net.liopyu.menujs.builders.menu.vanilla.ChestMenuBuilderJS;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;

public class MenuJSPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        RegistryInfo.MENU.addType("menujs:basic", AbstractContainerBuilderJS.class, AbstractContainerBuilderJS::new);
        RegistryInfo.MENU.addType("minecraft:chest", ChestMenuBuilderJS.class, ChestMenuBuilderJS::new);
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("InventoryScreen", InventoryScreen.class);
        event.add("Slot", Slot.class);
        event.add("SimpleContainer", SimpleContainer.class);
    }
}
