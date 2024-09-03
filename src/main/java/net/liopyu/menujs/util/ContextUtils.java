package net.liopyu.menujs.util;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.AbstractMenuContainerBuilderJS;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ContextUtils {
    public static class SlotClickContext {
        public final AbstractContainerMenu menu;
        public final int slotId;
        public final int button;
        public final ClickType clickType;
        public final Player player;

        public SlotClickContext(AbstractContainerMenu menu, int slotId, int button, ClickType clickType, Player player) {
            this.menu = menu;
            this.slotId = slotId;
            this.button = button;
            this.clickType = clickType;
            this.player = player;
        }
    }
    public static class PlayerIndexContext{
        public final Player player;
        public final int index;
        public final AbstractContainerMenu menu;

        public PlayerIndexContext(Player player, int index, AbstractContainerMenu menu) {
            this.player = player;
            this.index = index;
            this.menu = menu;
        }
    }
    public static class StillValidContext{
        public final Player player;
        public final AbstractContainerMenu menu;

        public StillValidContext(Player player, AbstractContainerMenu menu) {
            this.player = player;
            this.menu = menu;
        }
    }
    public static class IndexContext{
        public final AbstractContainerMenu menu;
        public final int index;

        public IndexContext(int index,AbstractContainerMenu menu) {
            this.menu = menu;
            this.index = index;
        }
    }
    public static class MenuBuilderContext<T extends AbstractContainerMenu>{
        public final AbstractMenuContainerBuilder<T> builder;
        public final  @Nullable MenuType<?> pMenuType;
        public final int pContainerId;
        public final  Inventory playerInventory;

        public MenuBuilderContext(AbstractMenuContainerBuilder<T> builder, @Nullable MenuType<?> pMenuType, int pContainerId, Inventory playerInventory) {
            this.builder = builder;
            this.pMenuType = pMenuType;
            this.pContainerId = pContainerId;
            this.playerInventory = playerInventory;
        }
    }
    public static class ScreenBuilderContext<T extends AbstractContainerMenu>{
        public final AbstractMenuContainerBuilder<T> builder;
        public final AbstractContainerScreen<T> screen;
        public final T menu;
        public final  Inventory playerInventory;
        public final Component title;

        public ScreenBuilderContext(AbstractContainerScreen<T> screen,AbstractMenuContainerBuilder<T> builder,  T menu, Inventory playerInventory, Component title) {

            this.builder = builder;
            this.screen = screen;
            this.menu = menu;
            this.playerInventory = playerInventory;
            this.title = title;
        }
    }
    public static class ScreenRenderContext<T extends AbstractContainerMenu> {
        public final AbstractContainerScreen<T> screen;
        public final GuiGraphics guiGraphics;
        public final float partialTick;
        public final int mouseX;
        public final int mouseY;

        public ScreenRenderContext(AbstractContainerScreen<T> screen, GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
            this.screen = screen;
            this.guiGraphics = guiGraphics;
            this.partialTick = partialTick;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }
    public static class MenuItemContext<T extends AbstractContainerMenu> {
        public final AbstractContainerScreen<T> screen;
        public final T menu;
        public final int slotIndex;
        public final ItemStack itemStack;

        public MenuItemContext(AbstractContainerScreen<T> screen, T menu, int slotIndex, ItemStack itemStack) {
            this.screen = screen;
            this.menu = menu;
            this.slotIndex = slotIndex;
            this.itemStack = itemStack;
        }
    }
    public static class DataChangedContext<T extends AbstractContainerMenu> {
        public final T menu;
        public final AbstractContainerScreen<T> screen;
        public final int dataId;
        public final int dataValue;

        public DataChangedContext(T menu, AbstractContainerScreen<T> screen, int dataId, int dataValue) {
            this.menu = menu;
            this.screen = screen;
            this.dataId = dataId;
            this.dataValue = dataValue;
        }
    }



}
