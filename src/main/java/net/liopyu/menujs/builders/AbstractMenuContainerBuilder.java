package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
@SuppressWarnings("unused")
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
    public Boolean shouldCloseOnEsc;

    public AbstractMenuContainerBuilder<T> setShouldCloseOnEsc(boolean shouldClose) {
        this.shouldCloseOnEsc = shouldClose;
        return this;
    }

    public Component narrationMessage;

    public AbstractMenuContainerBuilder<T> setNarrationMessage(Component narrationMessage) {
        this.narrationMessage = narrationMessage;
        return this;
    }

    public Component title;

    public AbstractMenuContainerBuilder<T> setTitle(Component title) {
        this.title = title;
        return this;
    }

    public Integer xSize;
    public Integer ySize;

    public AbstractMenuContainerBuilder<T> setXSize(int size) {
        this.xSize = size;
        return this;
    }

    public AbstractMenuContainerBuilder<T> setYSize(int size) {
        this.ySize = size;
        return this;
    }

    public transient Function<ContextUtils.HasClickedOutsideContext, Object> hasClickedOutside;

    public AbstractMenuContainerBuilder<T> hasClickedOutside(Function<ContextUtils.HasClickedOutsideContext, Object> arg) {
        this.hasClickedOutside = arg;
        return this;
    }
    public transient Function<ContextUtils.MouseDraggedContext, Object> mouseDragged;

    public AbstractMenuContainerBuilder<T> mouseDragged(Function<ContextUtils.MouseDraggedContext, Object> arg) {
        this.mouseDragged = arg;
        return this;
    }
    public transient Function<ContextUtils.MouseClickedContext, Object> mouseReleased;

    public AbstractMenuContainerBuilder<T> mouseReleased(Function<ContextUtils.MouseClickedContext, Object> arg) {
        this.mouseReleased = arg;
        return this;
    }
    public transient Function<ContextUtils.IsHoveringContext, Object> isHovering;

    public AbstractMenuContainerBuilder<T> isHovering(Function<ContextUtils.IsHoveringContext, Object> arg) {
        this.isHovering = arg;
        return this;
    }
    public transient Function<ContextUtils.KeyPressedContext, Object> keyPressed;

    public AbstractMenuContainerBuilder<T> keyPressed(Function<ContextUtils.KeyPressedContext, Object> arg) {
        this.keyPressed = arg;
        return this;
    }
    public transient Function<ContextUtils.CheckHotbarKeyPressedContext, Object> checkHotbarKeyPressed;

    public AbstractMenuContainerBuilder<T> checkHotbarKeyPressed(Function<ContextUtils.CheckHotbarKeyPressedContext, Object> arg) {
        this.checkHotbarKeyPressed = arg;
        return this;
    }
    public transient Function<AbstractContainerScreen<?>, Object> isPauseScreen;

    public AbstractMenuContainerBuilder<T> isPauseScreen(Function<AbstractContainerScreen<?>, Object> arg) {
        this.isPauseScreen = arg;
        return this;
    }
    public transient Function<AbstractContainerScreen<?>, Object> getSlotUnderMouse;

    public AbstractMenuContainerBuilder<T> getSlotUnderMouse(Function<AbstractContainerScreen<?>, Object> arg) {
        this.getSlotUnderMouse = arg;
        return this;
    }
    public transient Function<AbstractContainerScreen<?>, Object> getGuiLeft;

    public AbstractMenuContainerBuilder<T> getGuiLeft(Function<AbstractContainerScreen<?>, Object> arg) {
        this.getGuiLeft = arg;
        return this;
    }
    public transient Function<AbstractContainerScreen<?>, Object> getGuiTop;

    public AbstractMenuContainerBuilder<T> getGuiTop(Function<AbstractContainerScreen<?>, Object> arg) {
        this.getGuiTop = arg;
        return this;
    }

    public transient Function<ContextUtils.MouseClickedContext, Object> mouseClicked;

    public AbstractMenuContainerBuilder<T> mouseClicked(Function<ContextUtils.MouseClickedContext, Object> arg) {
        this.mouseClicked = arg;
        return this;
    }

    public transient Function<ContextUtils.ItemScreenContext, Object> setTooltipFromContainerItem;


    public AbstractMenuContainerBuilder<T> setTooltipFromContainerItem(Function<ContextUtils.ItemScreenContext, Object> arg) {
        this.setTooltipFromContainerItem = arg;
        return this;
    }
    public transient Consumer<ContextUtils.InitialFocusContext> onSetInitialFocus;
    public transient Consumer<ContextUtils.InitialFocusContext> setInitialFocus;

    public AbstractMenuContainerBuilder<T> onSetInitialFocus(Consumer<ContextUtils.InitialFocusContext> arg) {
        this.onSetInitialFocus = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> setInitialFocus(Consumer<ContextUtils.InitialFocusContext> arg) {
        this.setInitialFocus = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> onClearWidgets;
    public transient Consumer<AbstractContainerScreen<?>> clearWidgets;

    public AbstractMenuContainerBuilder<T> onClearWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.onClearWidgets = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> clearWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.clearWidgets = arg;
        return this;
    }
    public transient Consumer<ContextUtils.InsertTextContext> insertText;
    public transient Consumer<ContextUtils.InsertTextContext> onInsertText;

    public AbstractMenuContainerBuilder<T> insertText(Consumer<ContextUtils.InsertTextContext> arg) {
        this.insertText = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onInsertText(Consumer<ContextUtils.InsertTextContext> arg) {
        this.onInsertText = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> rebuildWidgets;
    public transient Consumer<AbstractContainerScreen<?>> onRebuildWidgets;

    public AbstractMenuContainerBuilder<T> rebuildWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.rebuildWidgets = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onRebuildWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.onRebuildWidgets = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> added;
    public transient Consumer<AbstractContainerScreen<?>> onAdded;

    public AbstractMenuContainerBuilder<T> added(Consumer<AbstractContainerScreen<?>> arg) {
        this.added = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onAdded(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAdded = arg;
        return this;
    }

    public transient Consumer<ContextUtils.RemoveWidgetContext> onRemoveWidget;
    public transient Consumer<ContextUtils.RemoveWidgetContext> removeWidget;

    public AbstractMenuContainerBuilder<T> onRemoveWidget(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.onRemoveWidget = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> removeWidget(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.removeWidget = arg;
        return this;
    }

    public transient Consumer<ContextUtils.FocusChangeContext> onChangeFocus;
    public transient Consumer<ContextUtils.FocusChangeContext> changeFocus;

    public AbstractMenuContainerBuilder<T> onChangeFocus(Consumer<ContextUtils.FocusChangeContext> arg) {
        this.onChangeFocus = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> changeFocus(Consumer<ContextUtils.FocusChangeContext> arg) {
        this.changeFocus = arg;
        return this;
    }
    public transient Consumer<ContextUtils.RenderBackgroundContext> renderBackground;
    public transient Consumer<ContextUtils.RenderBackgroundContext> onRenderBackground;

    public AbstractMenuContainerBuilder<T> renderBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.renderBackground = arg;
        return this;
    }
    public transient Consumer<ContextUtils.ResizeContext> resize;
    public transient Consumer<ContextUtils.ResizeContext> onResize;

    public AbstractMenuContainerBuilder<T> resize(Consumer<ContextUtils.ResizeContext> arg) {
        this.resize = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onResize(Consumer<ContextUtils.ResizeContext> arg) {
        this.onResize = arg;
        return this;
    }
    public transient Consumer<ContextUtils.FilesDropContext> onFilesDrop;
    public transient Consumer<ContextUtils.FilesDropContext> filesDrop;

    public AbstractMenuContainerBuilder<T> onFilesDrop(Consumer<ContextUtils.FilesDropContext> arg) {
        this.onFilesDrop = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> filesDrop(Consumer<ContextUtils.FilesDropContext> arg) {
        this.filesDrop = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> afterMouseAction;
    public transient Consumer<AbstractContainerScreen<?>> onAfterMouseAction;

    public AbstractMenuContainerBuilder<T> afterMouseAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.afterMouseAction = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onAfterMouseAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAfterMouseAction = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> handleDelayedNarration;
    public transient Consumer<AbstractContainerScreen<?>> onHandleDelayedNarration;

    public AbstractMenuContainerBuilder<T> handleDelayedNarration(Consumer<AbstractContainerScreen<?>> arg) {
        this.handleDelayedNarration = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onHandleDelayedNarration(Consumer<AbstractContainerScreen<?>> arg) {
        this.onHandleDelayedNarration = arg;
        return this;
    }
    public transient Consumer<ContextUtils.NarrationContext> triggerImmediateNarration;
    public transient Consumer<ContextUtils.NarrationContext> onTriggerImmediateNarration;

    public AbstractMenuContainerBuilder<T> triggerImmediateNarration(Consumer<ContextUtils.NarrationContext> arg) {
        this.triggerImmediateNarration = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onTriggerImmediateNarration(Consumer<ContextUtils.NarrationContext> arg) {
        this.onTriggerImmediateNarration = arg;
        return this;
    }
    public transient Consumer<ContextUtils.NarrationStateContext> updateNarrationState;
    public transient Consumer<ContextUtils.NarrationStateContext> onUpdateNarrationState;

    public AbstractMenuContainerBuilder<T> updateNarrationState(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.updateNarrationState = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onUpdateNarrationState(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.onUpdateNarrationState = arg;
        return this;
    }
    public transient Consumer<ContextUtils.NarrationStateContext> updateNarratedWidget;
    public transient Consumer<ContextUtils.NarrationStateContext> onUpdateNarratedWidget;

    public AbstractMenuContainerBuilder<T> updateNarratedWidget(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.updateNarratedWidget = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onUpdateNarratedWidget(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.onUpdateNarratedWidget = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> narrationEnabled;
    public transient Consumer<AbstractContainerScreen<?>> onNarrationEnabled;

    public AbstractMenuContainerBuilder<T> narrationEnabled(Consumer<AbstractContainerScreen<?>> arg) {
        this.narrationEnabled = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onNarrationEnabled(Consumer<AbstractContainerScreen<?>> arg) {
        this.onNarrationEnabled = arg;
        return this;
    }
    public transient Consumer<ContextUtils.RemoveWidgetContext> magicalSpecialHackyFocus;
    public transient Consumer<ContextUtils.RemoveWidgetContext> onMagicalSpecialHackyFocus;

    public AbstractMenuContainerBuilder<T> magicalSpecialHackyFocus(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.magicalSpecialHackyFocus = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onMagicalSpecialHackyFocus(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.onMagicalSpecialHackyFocus = arg;
        return this;
    }
    public transient Consumer<ContextUtils.MouseMoveContext> mouseMoved;
    public transient Consumer<ContextUtils.MouseMoveContext> onMouseMoved;

    public AbstractMenuContainerBuilder<T> mouseMoved(Consumer<ContextUtils.MouseMoveContext> arg) {
        this.mouseMoved = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onMouseMoved(Consumer<ContextUtils.MouseMoveContext> arg) {
        this.onMouseMoved = arg;
        return this;
    }

    public transient Consumer<ContextUtils.RemoveWidgetContext> setFocused;
    public transient Consumer<ContextUtils.RemoveWidgetContext> onSetFocused;

    public AbstractMenuContainerBuilder<T> setFocused(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.setFocused = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onSetFocused(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.onSetFocused = arg;
        return this;
    }

    public transient Consumer<ContextUtils.TooltipRenderPassContext> setTooltipForNextRenderPass;
    public transient Consumer<ContextUtils.TooltipRenderPassContext> onSetTooltipForNextRenderPass;

    public AbstractMenuContainerBuilder<T> setTooltipForNextRenderPass(Consumer<ContextUtils.TooltipRenderPassContext> arg) {
        this.setTooltipForNextRenderPass = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onSetTooltipForNextRenderPass(Consumer<ContextUtils.TooltipRenderPassContext> arg) {
        this.onSetTooltipForNextRenderPass = arg;
        return this;
    }

    public transient Consumer<AbstractContainerScreen<?>> afterKeyboardAction;
    public transient Consumer<AbstractContainerScreen<?>> onAfterKeyboardAction;

    public AbstractMenuContainerBuilder<T> afterKeyboardAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.afterKeyboardAction = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onAfterKeyboardAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAfterKeyboardAction = arg;
        return this;
    }

    public transient Consumer<AbstractContainerScreen<?>> afterMouseMove;
    public transient Consumer<AbstractContainerScreen<?>> onAfterMouseMove;

    public AbstractMenuContainerBuilder<T> afterMouseMove(Consumer<AbstractContainerScreen<?>> arg) {
        this.afterMouseMove = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onAfterMouseMove(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAfterMouseMove = arg;
        return this;
    }

    public transient Consumer<AbstractContainerScreen<?>> repositionElements;
    public transient Consumer<AbstractContainerScreen<?>> onRepositionElements;

    public AbstractMenuContainerBuilder<T> repositionElements(Consumer<AbstractContainerScreen<?>> arg) {
        this.repositionElements = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onRepositionElements(Consumer<AbstractContainerScreen<?>> arg) {
        this.onRepositionElements = arg;
        return this;
    }

    public transient Consumer<ContextUtils.RenderBackgroundContext> renderDirtBackground;
    public transient Consumer<ContextUtils.RenderBackgroundContext> onRenderDirtBackground;

    public AbstractMenuContainerBuilder<T> renderDirtBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.renderDirtBackground = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onRenderDirtBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.onRenderDirtBackground = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onRenderBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.onRenderBackground = arg;
        return this;
    }

    public transient Consumer<AbstractContainerScreen<?>> onClose;
    public transient Consumer<AbstractContainerScreen<?>> close;

    public AbstractMenuContainerBuilder<T> onClose(Consumer<AbstractContainerScreen<?>> arg) {
        this.onClose = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> close(Consumer<AbstractContainerScreen<?>> arg) {
        this.close = arg;
        return this;
    }

    public transient Consumer<AbstractContainerScreen<?>> onContainerTick;
    public transient Consumer<AbstractContainerScreen<?>> containerTick;

    public AbstractMenuContainerBuilder<T> onContainerTick(Consumer<AbstractContainerScreen<?>> arg) {
        this.onContainerTick = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> containerTick(Consumer<AbstractContainerScreen<?>> arg) {
        this.containerTick = arg;
        return this;
    }

    public transient Consumer<ContextUtils.SlotClickedContext> onSlotClicked;
    public transient Consumer<ContextUtils.SlotClickedContext> slotClicked;

    public AbstractMenuContainerBuilder<T> onSlotClicked(Consumer<ContextUtils.SlotClickedContext> arg) {
        this.onSlotClicked = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> slotClicked(Consumer<ContextUtils.SlotClickedContext> arg) {
        this.slotClicked = arg;
        return this;
    }

    public transient Consumer<ContextUtils.TooltipRenderContext> onRenderTooltip;
    public transient Consumer<ContextUtils.TooltipRenderContext> renderTooltip;

    public AbstractMenuContainerBuilder<T> renderTooltip(Consumer<ContextUtils.TooltipRenderContext> arg) {
        this.renderTooltip = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> onRenderTooltip(Consumer<ContextUtils.TooltipRenderContext> arg) {
        this.onRenderTooltip = arg;
        return this;
    }
    public transient Consumer<ContextUtils.LabelRenderContext> onRenderLabels;
    public transient Consumer<ContextUtils.LabelRenderContext> renderLabels;

    public AbstractMenuContainerBuilder<T> onRenderLabels(Consumer<ContextUtils.LabelRenderContext> arg) {
        this.onRenderLabels = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> renderLabels(Consumer<ContextUtils.LabelRenderContext> arg) {
        this.renderLabels = arg;
        return this;
    }
    public transient Consumer<AbstractContainerScreen<?>> onClearDraggingState;
    public transient Consumer<AbstractContainerScreen<?>> clearDraggingState;

    public AbstractMenuContainerBuilder<T> onClearDraggingState(Consumer<AbstractContainerScreen<?>> arg) {
        this.onClearDraggingState = arg;
        return this;
    }

    public AbstractMenuContainerBuilder<T> clearDraggingState(Consumer<AbstractContainerScreen<?>> arg) {
        this.clearDraggingState = arg;
        return this;
    }


    public transient Consumer<ContextUtils.ScreenRenderContext<T>> onRender;
    public transient Consumer<ContextUtils.ScreenRenderContext<T>> render;
    public AbstractMenuContainerBuilder<T> render(Consumer<ContextUtils.ScreenRenderContext<T>> arg) {
        this.render = arg;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onRender(Consumer<ContextUtils.ScreenRenderContext<T>> arg) {
        this.onRender = arg;
        return this;
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
    public T getMenu() {
        return menu;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

}
