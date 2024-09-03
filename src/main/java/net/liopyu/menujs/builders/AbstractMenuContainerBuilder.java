package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractMenuContainerBuilder<T extends AbstractContainerMenu> extends BuilderBase<MenuType<T>> {
    private T menu;
    public static List<AbstractMenuContainerBuilder<?>> thisList = new ArrayList<>();
    public final List<Slot> slotList = new ArrayList<>();
    public final List<ContainerData> containerSlotList = new ArrayList<>();
    public final List<DataSlot> dataSlotList = new ArrayList<>();
    public transient SimpleContainer container;
    public transient Function<ContextUtils.PlayerIndexContext, Object> setQuickMoveStack;
    public transient Function<ContextUtils.PlayerMenuContext, Object> setStillValid;
    public transient Function<ContextUtils.IndexContext, Object> isValidSlotIndex;
    public transient Consumer<ContextUtils.MenuBuilderContext<T>> onMenuInit;
    public transient Consumer<ContextUtils.ScreenBuilderContext<T>> onScreenInit;
    public transient Consumer<ContextUtils.DataChangedContext<T>> onDataChanged;
    public transient Consumer<ContextUtils.MenuItemContext<T>> onScreenSlotChanged;
    public transient Consumer<ContextUtils.ContainerMenuContext> onMenuSlotChanged;
    public transient Consumer<ContextUtils.ContainerMenuContext> setMenuSlotChanged;
    public transient Consumer<ContextUtils.ScreenRenderContext<T>> renderBg;
    public transient Function<ContextUtils.PlayerIndexContext,Object> clickMenuButton;
    public transient Consumer<ContextUtils.SlotClickContext> onClicked;
    public transient Function<ContextUtils.ItemSlotContext,Object> setCanTakeItemForPickAll;
    public transient Consumer<ContextUtils.PlayerMenuContext> onItemRemoved;
    public transient Consumer<ContextUtils.PlayerMenuContext> setItemRemoved;
    public transient Consumer<ContextUtils.ContainerUpdateContext> onInitializeContents;
    public transient Consumer<ContextUtils.IndexDataContext> onSetData;
    public transient Function<ContextUtils.TransferStackContext,Object> moveItemStackTo;
    public transient Function<ContextUtils.MenuSlotContext,Object> canDragTo;
    public transient Consumer<ContextUtils.MenuItemStackContext> setCarried;
    public transient Function<T,Object> getCarried;
    public transient Inventory playerInventory;

    public AbstractMenuContainerBuilder(ResourceLocation i) {
        super(i);
        thisList.add(this);
    }

    public T getMenu() {
        return menu;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public AbstractMenuContainerBuilder<T> getCarried(Function<T,Object> arg) {
        this.getCarried = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setCarried(Consumer<ContextUtils.MenuItemStackContext> arg) {
        this.setCarried = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> canDragTo(Function<ContextUtils.MenuSlotContext,Object> arg) {
        this.canDragTo = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> moveItemStackTo(Function<ContextUtils.TransferStackContext,Object> arg) {
        this.moveItemStackTo = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onSetData(Consumer<ContextUtils.IndexDataContext> arg) {
        this.onSetData = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onInitializeContents(Consumer<ContextUtils.ContainerUpdateContext> arg) {
        this.onInitializeContents = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setMenuSlotChanged(Consumer<ContextUtils.ContainerMenuContext> arg) {
        this.setMenuSlotChanged = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onMenuSlotChanged(Consumer<ContextUtils.ContainerMenuContext> arg) {
        this.onMenuSlotChanged = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setItemRemoved(Consumer<ContextUtils.PlayerMenuContext> arg) {
        this.setItemRemoved = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onItemRemoved(Consumer<ContextUtils.PlayerMenuContext> arg) {
        this.onItemRemoved = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setCanTakeItemForPickAll(Function<ContextUtils.ItemSlotContext,Object> arg) {
        this.setCanTakeItemForPickAll = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onClicked(Consumer<ContextUtils.SlotClickContext> consumer) {
        this.onClicked = consumer;
        return this;
    }
    public AbstractMenuContainerBuilder<T> clickMenuButton(Function<ContextUtils.PlayerIndexContext,Object> consumer) {
        this.clickMenuButton = consumer;
        return this;
    }
    public AbstractMenuContainerBuilder<T> renderBg(Consumer<ContextUtils.ScreenRenderContext<T>> consumer) {
        this.renderBg = consumer;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onScreenSlotChanged(Consumer<ContextUtils.MenuItemContext<T>> consumer) {
        this.onScreenSlotChanged = consumer;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onDataChanged(Consumer<ContextUtils.DataChangedContext<T>> consumer) {
        this.onDataChanged = consumer;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onScreenInit(Consumer<ContextUtils.ScreenBuilderContext<T>> init) {
        this.onScreenInit = init;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onMenuInit(Consumer<ContextUtils.MenuBuilderContext<T>> init) {
        this.onMenuInit = init;
        return this;
    }
    public AbstractMenuContainerBuilder<T> addDataSlot(DataSlot slot) {
        this.dataSlotList.add(slot);
        return this;
    }
    public AbstractMenuContainerBuilder<T> addContainerData(ContainerData slot) {
        this.containerSlotList.add(slot);
        return this;
    }

    public void setContainer() {
        this.container = new SimpleContainer();
    }
    public void setContainer(int pSize) {
        this.container = new SimpleContainer(pSize);
    }
    public void setContainer(ItemStack... pItems) {
        this.container = new SimpleContainer(pItems);
    }

    public AbstractMenuContainerBuilder<T> addSlot(int pSlot, int pX, int pY) {
        this.slotList.add(new Slot(this.container, pSlot,pX,pY));
        return this;
    }
    public AbstractMenuContainerBuilder<T> addSlot(Slot slot) {
        this.slotList.add(slot);
        return this;
    }
    public AbstractMenuContainerBuilder<T> isValidSlotIndex(Function<ContextUtils.IndexContext, Object> function) {
        this.isValidSlotIndex = function;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setStillValid(Function<ContextUtils.PlayerMenuContext, Object> function) {
        this.setStillValid = function;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setQuickMoveStack(Function<ContextUtils.PlayerIndexContext, Object> stack) {
            this.setQuickMoveStack = stack;
            return this;
    }
    @Override
    public RegistryInfo<MenuType> getRegistryType() {
        return RegistryInfo.MENU;
    }

}
