package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.util.UtilsJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.liopyu.menujs.builders.menu.menujs.AbstractContainerBuilderJS;
import net.liopyu.menujs.builders.menu.vanilla.ChestMenuBuilderJS;
import net.liopyu.menujs.builders.widget.AbstractWidgetBuilder;
import net.liopyu.menujs.client.screens.menujs.AbstractContainerScreenJS;
import net.liopyu.menujs.client.screens.vanilla.ChestMenuScreenJS;
import net.liopyu.menujs.menus.menujs.AbstractMenuContainerJS;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public abstract class AbstractContainerBuilder<T extends AbstractContainerMenu> extends BuilderBase<MenuType<T>> {
    public static Map<ResourceLocation, AbstractContainerBuilder<?>> thisList = new HashMap<>();
    public final List<Slot> slotList = new ArrayList<>();
    public final List<ContainerData> containerSlotList = new ArrayList<>();
    public final List<DataSlot> dataSlotList = new ArrayList<>();
    public final List<AbstractWidget> renderableWidgets = new ArrayList<>();
    @HideFromJS
    public Integer xSize;
    @HideFromJS
    public Integer ySize;
    public SimpleContainer container;
    public transient Function<ContextUtils.PlayerIndexContext, Object> setQuickMoveStack;
    public transient Function<ContextUtils.PlayerMenuContext, Object> setStillValid;
    public transient Function<ContextUtils.IndexContext, Object> isValidSlotIndex;
    public transient Consumer<ContextUtils.MenuBuilderContext<T>> onMenuInit;
    public transient Consumer<ContextUtils.ScreenBuilderContext> onScreenInit;
    public transient Consumer<ContextUtils.DataChangedContext> onDataChanged;
    public transient Consumer<ContextUtils.MenuItemContext> onScreenSlotChanged;
    public transient Consumer<ContextUtils.ContainerMenuContext> onMenuSlotChanged;
    public transient Consumer<ContextUtils.ContainerMenuContext> setMenuSlotChanged;
    public transient Consumer<ContextUtils.ScreenRenderContext> renderBg;
    public transient Function<ContextUtils.PlayerIndexContext, Object> clickMenuButton;
    public transient Consumer<ContextUtils.SlotClickContext> onClicked;
    public transient Function<ContextUtils.ItemSlotContext, Object> setCanTakeItemForPickAll;
    public transient Consumer<ContextUtils.PlayerMenuContext> onItemRemoved;
    public transient Consumer<ContextUtils.PlayerMenuContext> setItemRemoved;
    public transient Consumer<ContextUtils.ContainerUpdateContext> onInitializeContents;
    public transient Consumer<ContextUtils.IndexDataContext> onSetData;
    public transient Function<ContextUtils.TransferStackContext, Object> moveItemStackTo;
    public transient Function<ContextUtils.MenuSlotContext, Object> canDragTo;
    public transient Consumer<ContextUtils.MenuItemStackContext> setCarried;
    public transient Function<T, Object> getCarried;
    public transient Boolean shouldCloseOnEsc;
    public transient Component narrationMessage;
    public transient Component title;
    public transient Function<ContextUtils.HasClickedOutsideContext, Object> hasClickedOutside;
    public transient Function<ContextUtils.MouseDraggedContext, Object> mouseDragged;
    public transient Function<ContextUtils.MouseClickedContext, Object> mouseReleased;
    public transient Function<ContextUtils.IsHoveringContext, Object> isHovering;
    public transient Function<ContextUtils.KeyPressedContext, Object> keyPressed;
    public transient Function<ContextUtils.CheckHotbarKeyPressedContext, Object> checkHotbarKeyPressed;
    public transient Function<AbstractContainerScreen<?>, Object> isPauseScreen;
    public transient Function<AbstractContainerScreen<?>, Object> getSlotUnderMouse;
    public transient Function<AbstractContainerScreen<?>, Object> getGuiLeft;
    public transient Function<AbstractContainerScreen<?>, Object> getGuiTop;
    public transient Function<ContextUtils.MouseClickedContext, Object> mouseClicked;
    public transient Function<ContextUtils.ItemScreenContext, Object> setTooltipFromContainerItem;
    public transient Consumer<ContextUtils.InitialFocusContext> onSetInitialFocus;
    public transient Consumer<ContextUtils.InitialFocusContext> setInitialFocus;
    public transient Consumer<AbstractContainerScreen<?>> onClearWidgets;
    public transient Consumer<AbstractContainerScreen<?>> clearWidgets;
    public transient Consumer<ContextUtils.InsertTextContext> insertText;
    public transient Consumer<ContextUtils.InsertTextContext> onInsertText;
    public transient Consumer<AbstractContainerScreen<?>> rebuildWidgets;
    public transient Consumer<AbstractContainerScreen<?>> onRebuildWidgets;
    public transient Consumer<AbstractContainerScreen<?>> added;
    public transient Consumer<AbstractContainerScreen<?>> onAdded;
    public transient Consumer<ContextUtils.RemoveWidgetContext> onRemoveWidget;
    public transient Consumer<ContextUtils.RemoveWidgetContext> removeWidget;
    public transient Consumer<ContextUtils.FocusChangeContext> onChangeFocus;
    public transient Consumer<ContextUtils.FocusChangeContext> changeFocus;
    public transient Consumer<ContextUtils.RenderBackgroundContext> renderBackground;
    public transient Consumer<ContextUtils.RenderBackgroundContext> onRenderBackground;
    public transient Consumer<ContextUtils.ResizeContext> resize;
    public transient Consumer<ContextUtils.ResizeContext> onResize;
    public transient Consumer<ContextUtils.FilesDropContext> onFilesDrop;
    public transient Consumer<ContextUtils.FilesDropContext> filesDrop;
    public transient Consumer<AbstractContainerScreen<?>> afterMouseAction;
    public transient Consumer<AbstractContainerScreen<?>> onAfterMouseAction;
    public transient Consumer<AbstractContainerScreen<?>> handleDelayedNarration;
    public transient Consumer<AbstractContainerScreen<?>> onHandleDelayedNarration;
    public transient Consumer<ContextUtils.NarrationContext> triggerImmediateNarration;
    public transient Consumer<ContextUtils.NarrationContext> onTriggerImmediateNarration;
    public transient Consumer<ContextUtils.NarrationStateContext> updateNarrationState;
    public transient Consumer<ContextUtils.NarrationStateContext> onUpdateNarrationState;
    public transient Consumer<ContextUtils.NarrationStateContext> updateNarratedWidget;
    public transient Consumer<ContextUtils.NarrationStateContext> onUpdateNarratedWidget;
    public transient Consumer<AbstractContainerScreen<?>> narrationEnabled;
    public transient Consumer<AbstractContainerScreen<?>> onNarrationEnabled;
    public transient Consumer<ContextUtils.RemoveWidgetContext> magicalSpecialHackyFocus;
    public transient Consumer<ContextUtils.RemoveWidgetContext> onMagicalSpecialHackyFocus;
    public transient Consumer<ContextUtils.MouseMoveContext> mouseMoved;
    public transient Consumer<ContextUtils.MouseMoveContext> onMouseMoved;
    public transient Consumer<ContextUtils.RemoveWidgetContext> setFocused;
    public transient Consumer<ContextUtils.RemoveWidgetContext> onSetFocused;
    public transient Consumer<ContextUtils.TooltipRenderPassContext> setTooltipForNextRenderPass;
    public transient Consumer<ContextUtils.TooltipRenderPassContext> onSetTooltipForNextRenderPass;
    public transient Consumer<AbstractContainerScreen<?>> afterKeyboardAction;
    public transient Consumer<AbstractContainerScreen<?>> onAfterKeyboardAction;
    public transient Consumer<AbstractContainerScreen<?>> afterMouseMove;
    public transient Consumer<AbstractContainerScreen<?>> onAfterMouseMove;
    public transient Consumer<AbstractContainerScreen<?>> repositionElements;
    public transient Consumer<AbstractContainerScreen<?>> onRepositionElements;
    public transient Consumer<ContextUtils.RenderBackgroundContext> renderDirtBackground;
    public transient Consumer<ContextUtils.RenderBackgroundContext> onRenderDirtBackground;
    public transient Consumer<AbstractContainerScreen<?>> onClose;
    public transient Consumer<AbstractContainerScreen<?>> close;
    public transient Consumer<AbstractContainerScreen<?>> onContainerTick;
    public transient Consumer<AbstractContainerScreen<?>> containerTick;
    public transient Consumer<ContextUtils.SlotClickedContext> onSlotClicked;
    public transient Consumer<ContextUtils.SlotClickedContext> slotClicked;
    public transient Consumer<ContextUtils.TooltipRenderContext> onRenderTooltip;
    public transient Consumer<ContextUtils.TooltipRenderContext> renderTooltip;
    public transient Consumer<ContextUtils.LabelRenderContext> onRenderLabels;
    public transient Consumer<ContextUtils.LabelRenderContext> renderLabels;
    public transient Consumer<AbstractContainerScreen<?>> onClearDraggingState;
    public transient Consumer<AbstractContainerScreen<?>> clearDraggingState;
    public transient Consumer<ContextUtils.ScreenRenderContext> onRender;
    public transient Consumer<ContextUtils.ScreenRenderContext> render;
    private T menu;

    public AbstractContainerBuilder(ResourceLocation i) {
        super(i);
        thisList.put(i, this);
        this.setStillValid = t -> true;
    }

    public AbstractContainerBuilder<T> newRenderableWidget(int x, int y, int width, int height, Component message, Consumer<AbstractWidgetBuilder> widgetBuilder) {
        var WBuilder = new AbstractWidgetBuilder(this);
        widgetBuilder.accept(WBuilder);
        renderableWidgets.add(WBuilder.build(x, y, width, height, message));
        return this;
    }

    public AbstractContainerBuilder<T> setShouldCloseOnEsc(boolean shouldClose) {
        this.shouldCloseOnEsc = shouldClose;
        return this;
    }

    public AbstractContainerBuilder<T> setNarrationMessage(Component narrationMessage) {
        this.narrationMessage = narrationMessage;
        return this;
    }

    public AbstractContainerBuilder<T> setTitle(Component title) {
        this.title = title;
        return this;
    }

    public AbstractContainerBuilder<T> setXSize(int size) {
        this.xSize = size;
        return this;
    }

    public AbstractContainerBuilder<T> setYSize(int size) {
        this.ySize = size;
        return this;
    }

    public AbstractContainerBuilder<T> hasClickedOutside(Function<ContextUtils.HasClickedOutsideContext, Object> arg) {
        this.hasClickedOutside = arg;
        return this;
    }

    public AbstractContainerBuilder<T> mouseDragged(Function<ContextUtils.MouseDraggedContext, Object> arg) {
        this.mouseDragged = arg;
        return this;
    }

    public AbstractContainerBuilder<T> mouseReleased(Function<ContextUtils.MouseClickedContext, Object> arg) {
        this.mouseReleased = arg;
        return this;
    }

    public AbstractContainerBuilder<T> isHovering(Function<ContextUtils.IsHoveringContext, Object> arg) {
        this.isHovering = arg;
        return this;
    }

    public AbstractContainerBuilder<T> keyPressed(Function<ContextUtils.KeyPressedContext, Object> arg) {
        this.keyPressed = arg;
        return this;
    }

    public AbstractContainerBuilder<T> checkHotbarKeyPressed(Function<ContextUtils.CheckHotbarKeyPressedContext, Object> arg) {
        this.checkHotbarKeyPressed = arg;
        return this;
    }

    public AbstractContainerBuilder<T> isPauseScreen(Function<AbstractContainerScreen<?>, Object> arg) {
        this.isPauseScreen = arg;
        return this;
    }

    public AbstractContainerBuilder<T> getSlotUnderMouse(Function<AbstractContainerScreen<?>, Object> arg) {
        this.getSlotUnderMouse = arg;
        return this;
    }

    public AbstractContainerBuilder<T> getGuiLeft(Function<AbstractContainerScreen<?>, Object> arg) {
        this.getGuiLeft = arg;
        return this;
    }

    public AbstractContainerBuilder<T> getGuiTop(Function<AbstractContainerScreen<?>, Object> arg) {
        this.getGuiTop = arg;
        return this;
    }

    public AbstractContainerBuilder<T> mouseClicked(Function<ContextUtils.MouseClickedContext, Object> arg) {
        this.mouseClicked = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setTooltipFromContainerItem(Function<ContextUtils.ItemScreenContext, Object> arg) {
        this.setTooltipFromContainerItem = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onSetInitialFocus(Consumer<ContextUtils.InitialFocusContext> arg) {
        this.onSetInitialFocus = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setInitialFocus(Consumer<ContextUtils.InitialFocusContext> arg) {
        this.setInitialFocus = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onClearWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.onClearWidgets = arg;
        return this;
    }

    public AbstractContainerBuilder<T> clearWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.clearWidgets = arg;
        return this;
    }

    public AbstractContainerBuilder<T> insertText(Consumer<ContextUtils.InsertTextContext> arg) {
        this.insertText = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onInsertText(Consumer<ContextUtils.InsertTextContext> arg) {
        this.onInsertText = arg;
        return this;
    }

    public AbstractContainerBuilder<T> rebuildWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.rebuildWidgets = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRebuildWidgets(Consumer<AbstractContainerScreen<?>> arg) {
        this.onRebuildWidgets = arg;
        return this;
    }

    public AbstractContainerBuilder<T> added(Consumer<AbstractContainerScreen<?>> arg) {
        this.added = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onAdded(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAdded = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRemoveWidget(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.onRemoveWidget = arg;
        return this;
    }

    public AbstractContainerBuilder<T> removeWidget(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.removeWidget = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onChangeFocus(Consumer<ContextUtils.FocusChangeContext> arg) {
        this.onChangeFocus = arg;
        return this;
    }

    public AbstractContainerBuilder<T> changeFocus(Consumer<ContextUtils.FocusChangeContext> arg) {
        this.changeFocus = arg;
        return this;
    }

    public AbstractContainerBuilder<T> renderBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.renderBackground = arg;
        return this;
    }

    public AbstractContainerBuilder<T> resize(Consumer<ContextUtils.ResizeContext> arg) {
        this.resize = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onResize(Consumer<ContextUtils.ResizeContext> arg) {
        this.onResize = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onFilesDrop(Consumer<ContextUtils.FilesDropContext> arg) {
        this.onFilesDrop = arg;
        return this;
    }

    public AbstractContainerBuilder<T> filesDrop(Consumer<ContextUtils.FilesDropContext> arg) {
        this.filesDrop = arg;
        return this;
    }

    public AbstractContainerBuilder<T> afterMouseAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.afterMouseAction = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onAfterMouseAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAfterMouseAction = arg;
        return this;
    }

    public AbstractContainerBuilder<T> handleDelayedNarration(Consumer<AbstractContainerScreen<?>> arg) {
        this.handleDelayedNarration = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onHandleDelayedNarration(Consumer<AbstractContainerScreen<?>> arg) {
        this.onHandleDelayedNarration = arg;
        return this;
    }

    public AbstractContainerBuilder<T> triggerImmediateNarration(Consumer<ContextUtils.NarrationContext> arg) {
        this.triggerImmediateNarration = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onTriggerImmediateNarration(Consumer<ContextUtils.NarrationContext> arg) {
        this.onTriggerImmediateNarration = arg;
        return this;
    }

    public AbstractContainerBuilder<T> updateNarrationState(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.updateNarrationState = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onUpdateNarrationState(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.onUpdateNarrationState = arg;
        return this;
    }

    public AbstractContainerBuilder<T> updateNarratedWidget(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.updateNarratedWidget = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onUpdateNarratedWidget(Consumer<ContextUtils.NarrationStateContext> arg) {
        this.onUpdateNarratedWidget = arg;
        return this;
    }

    public AbstractContainerBuilder<T> narrationEnabled(Consumer<AbstractContainerScreen<?>> arg) {
        this.narrationEnabled = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onNarrationEnabled(Consumer<AbstractContainerScreen<?>> arg) {
        this.onNarrationEnabled = arg;
        return this;
    }

    public AbstractContainerBuilder<T> magicalSpecialHackyFocus(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.magicalSpecialHackyFocus = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onMagicalSpecialHackyFocus(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.onMagicalSpecialHackyFocus = arg;
        return this;
    }

    public AbstractContainerBuilder<T> mouseMoved(Consumer<ContextUtils.MouseMoveContext> arg) {
        this.mouseMoved = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onMouseMoved(Consumer<ContextUtils.MouseMoveContext> arg) {
        this.onMouseMoved = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setFocused(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.setFocused = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onSetFocused(Consumer<ContextUtils.RemoveWidgetContext> arg) {
        this.onSetFocused = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setTooltipForNextRenderPass(Consumer<ContextUtils.TooltipRenderPassContext> arg) {
        this.setTooltipForNextRenderPass = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onSetTooltipForNextRenderPass(Consumer<ContextUtils.TooltipRenderPassContext> arg) {
        this.onSetTooltipForNextRenderPass = arg;
        return this;
    }

    public AbstractContainerBuilder<T> afterKeyboardAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.afterKeyboardAction = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onAfterKeyboardAction(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAfterKeyboardAction = arg;
        return this;
    }

    public AbstractContainerBuilder<T> afterMouseMove(Consumer<AbstractContainerScreen<?>> arg) {
        this.afterMouseMove = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onAfterMouseMove(Consumer<AbstractContainerScreen<?>> arg) {
        this.onAfterMouseMove = arg;
        return this;
    }

    public AbstractContainerBuilder<T> repositionElements(Consumer<AbstractContainerScreen<?>> arg) {
        this.repositionElements = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRepositionElements(Consumer<AbstractContainerScreen<?>> arg) {
        this.onRepositionElements = arg;
        return this;
    }

    public AbstractContainerBuilder<T> renderDirtBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.renderDirtBackground = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRenderDirtBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.onRenderDirtBackground = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRenderBackground(Consumer<ContextUtils.RenderBackgroundContext> arg) {
        this.onRenderBackground = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onClose(Consumer<AbstractContainerScreen<?>> arg) {
        this.onClose = arg;
        return this;
    }

    public AbstractContainerBuilder<T> close(Consumer<AbstractContainerScreen<?>> arg) {
        this.close = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onContainerTick(Consumer<AbstractContainerScreen<?>> arg) {
        this.onContainerTick = arg;
        return this;
    }

    public AbstractContainerBuilder<T> containerTick(Consumer<AbstractContainerScreen<?>> arg) {
        this.containerTick = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onSlotClicked(Consumer<ContextUtils.SlotClickedContext> arg) {
        this.onSlotClicked = arg;
        return this;
    }

    public AbstractContainerBuilder<T> slotClicked(Consumer<ContextUtils.SlotClickedContext> arg) {
        this.slotClicked = arg;
        return this;
    }

    public AbstractContainerBuilder<T> renderTooltip(Consumer<ContextUtils.TooltipRenderContext> arg) {
        this.renderTooltip = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRenderTooltip(Consumer<ContextUtils.TooltipRenderContext> arg) {
        this.onRenderTooltip = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRenderLabels(Consumer<ContextUtils.LabelRenderContext> arg) {
        this.onRenderLabels = arg;
        return this;
    }

    public AbstractContainerBuilder<T> renderLabels(Consumer<ContextUtils.LabelRenderContext> arg) {
        this.renderLabels = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onClearDraggingState(Consumer<AbstractContainerScreen<?>> arg) {
        this.onClearDraggingState = arg;
        return this;
    }

    public AbstractContainerBuilder<T> clearDraggingState(Consumer<AbstractContainerScreen<?>> arg) {
        this.clearDraggingState = arg;
        return this;
    }

    public AbstractContainerBuilder<T> render(Consumer<ContextUtils.ScreenRenderContext> arg) {
        this.render = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onRender(Consumer<ContextUtils.ScreenRenderContext> arg) {
        this.onRender = arg;
        return this;
    }

    public AbstractContainerBuilder<T> getCarried(Function<T, Object> arg) {
        this.getCarried = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setCarried(Consumer<ContextUtils.MenuItemStackContext> arg) {
        this.setCarried = arg;
        return this;
    }

    public AbstractContainerBuilder<T> canDragTo(Function<ContextUtils.MenuSlotContext, Object> arg) {
        this.canDragTo = arg;
        return this;
    }

    public AbstractContainerBuilder<T> moveItemStackTo(Function<ContextUtils.TransferStackContext, Object> arg) {
        this.moveItemStackTo = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onSetData(Consumer<ContextUtils.IndexDataContext> arg) {
        this.onSetData = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onInitializeContents(Consumer<ContextUtils.ContainerUpdateContext> arg) {
        this.onInitializeContents = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setMenuSlotChanged(Consumer<ContextUtils.ContainerMenuContext> arg) {
        this.setMenuSlotChanged = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onMenuSlotChanged(Consumer<ContextUtils.ContainerMenuContext> arg) {
        this.onMenuSlotChanged = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setItemRemoved(Consumer<ContextUtils.PlayerMenuContext> arg) {
        this.setItemRemoved = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onItemRemoved(Consumer<ContextUtils.PlayerMenuContext> arg) {
        this.onItemRemoved = arg;
        return this;
    }

    public AbstractContainerBuilder<T> setCanTakeItemForPickAll(Function<ContextUtils.ItemSlotContext, Object> arg) {
        this.setCanTakeItemForPickAll = arg;
        return this;
    }

    public AbstractContainerBuilder<T> onClicked(Consumer<ContextUtils.SlotClickContext> consumer) {
        this.onClicked = consumer;
        return this;
    }

    public AbstractContainerBuilder<T> clickMenuButton(Function<ContextUtils.PlayerIndexContext, Object> consumer) {
        this.clickMenuButton = consumer;
        return this;
    }

    public AbstractContainerBuilder<T> renderBg(Consumer<ContextUtils.ScreenRenderContext> consumer) {
        this.renderBg = consumer;
        return this;
    }

    public AbstractContainerBuilder<T> onScreenSlotChanged(Consumer<ContextUtils.MenuItemContext> consumer) {
        this.onScreenSlotChanged = consumer;
        return this;
    }

    public AbstractContainerBuilder<T> onDataChanged(Consumer<ContextUtils.DataChangedContext> consumer) {
        this.onDataChanged = consumer;
        return this;
    }

    public AbstractContainerBuilder<T> onScreenInit(Consumer<ContextUtils.ScreenBuilderContext> init) {
        this.onScreenInit = init;
        return this;
    }

    public AbstractContainerBuilder<T> onMenuInit(Consumer<ContextUtils.MenuBuilderContext<T>> init) {
        this.onMenuInit = init;
        return this;
    }

    public AbstractContainerBuilder<T> addDataSlot(DataSlot slot) {
        this.dataSlotList.add(slot);
        return this;
    }

    public AbstractContainerBuilder<T> addContainerData(ContainerData slot) {
        this.containerSlotList.add(slot);
        return this;
    }

    public SimpleContainer getContainer() {
        return container;
    }

    public AbstractContainerBuilder<T> setContainer() {
        this.container = new SimpleContainer();
        return this;
    }

    public AbstractContainerBuilder<T> setContainer(int pSize) {
        this.container = new SimpleContainer(pSize);
        return this;
    }
/*
    public AbstractMenuContainerBuilder<T> setContainer(ItemStack... pItems) {
        this.container = new SimpleContainer(pItems);
        return this;
    }*/

    public AbstractContainerBuilder<T> addSlot(int pSlot, int pX, int pY) {
        if (this.container == null) {
            this.setContainer();
        }
        this.slotList.add(new Slot(this.container, pSlot, pX, pY));
        return this;
    }

    public AbstractContainerBuilder<T> addSlot(Slot slot) {
        this.slotList.add(slot);
        return this;
    }

    public AbstractContainerBuilder<T> isValidSlotIndex(Function<ContextUtils.IndexContext, Object> function) {
        this.isValidSlotIndex = function;
        return this;
    }

    public AbstractContainerBuilder<T> setStillValid(Function<ContextUtils.PlayerMenuContext, Object> function) {
        this.setStillValid = function;
        return this;
    }

    public AbstractContainerBuilder<T> setQuickMoveStack(Function<ContextUtils.PlayerIndexContext, Object> stack) {
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

    @Override
    public MenuType<T> createObject() {
        return new AbstractMenuTypeJS<>(this).get();
    }
}
