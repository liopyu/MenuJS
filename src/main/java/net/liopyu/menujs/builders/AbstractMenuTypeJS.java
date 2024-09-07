package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.builders.menu.menujs.AbstractContainerBuilderJS;
import net.liopyu.menujs.builders.menu.vanilla.ChestMenuBuilderJS;
import net.liopyu.menujs.client.screens.menujs.AbstractContainerScreenJS;
import net.liopyu.menujs.client.screens.vanilla.ChestMenuScreenJS;
import net.liopyu.menujs.menus.menujs.AbstractMenuContainerJS;
import net.liopyu.menujs.menus.vanilla.ChestMenuJS;
import net.liopyu.menujs.util.ContextUtils;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import static net.liopyu.menujs.util.MenuJSHelperClass.convertObjectToDesired;

public class AbstractMenuTypeJS<B extends AbstractContainerMenu> {
    private final AbstractContainerBuilder<?> builder;

    public <T extends AbstractContainerBuilder<B>> AbstractMenuTypeJS(T builder) {
        this.builder = builder;
    }

    public MenuType get() {
        return new MenuType<>((pContainerId, pPlayerInventory) ->
                getMenuForBuilder(builder, pContainerId, pPlayerInventory),
                FeatureFlags.VANILLA_SET);
    }

    public static AbstractContainerMenu getMenuForBuilder(AbstractContainerBuilder<?> builder, int pContainerId, Inventory pPlayerInventory) {
        if (builder instanceof AbstractContainerBuilderJS abstractContainerBuilderJS) {
            return new AbstractMenuContainerJS(UtilsJS.cast(abstractContainerBuilderJS), abstractContainerBuilderJS.get(), pContainerId, pPlayerInventory);
        } else if (builder instanceof ChestMenuBuilderJS chestMenuBuilderJS) {
            return new ChestMenuJS(chestMenuBuilderJS, chestMenuBuilderJS.get(), pContainerId, pPlayerInventory, chestMenuBuilderJS.getContainer(), chestMenuBuilderJS.getRows());
        }
        throw new IllegalStateException("Could not find builder instance for: " + builder.id);
    }

    public static AbstractContainerScreen<?> getScreenForBuilder(AbstractContainerBuilder builder, AbstractContainerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        if (builder instanceof AbstractContainerBuilderJS abstractContainerBuilderJS) {
            return new AbstractContainerScreenJS<>(UtilsJS.cast(abstractContainerBuilderJS), pMenu, pPlayerInventory, pTitle);
        } else if (builder instanceof ChestMenuBuilderJS chestMenuBuilderJS) {
            return new ChestMenuScreenJS<>(chestMenuBuilderJS, UtilsJS.cast(pMenu), pPlayerInventory, pTitle);
        }
        throw new IllegalStateException("Could not find screen instance for: " + builder.id);
    }
}