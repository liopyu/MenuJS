package net.liopyu.menujs.util;

import net.liopyu.menujs.builders.AbstractContainerBuilder;
import net.liopyu.menujs.builders.widget.AbstractWidgetBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;

public class ContextUtils {
    public static class RenderBackgroundContext {
        public final AbstractContainerScreen<?> screen;
        public final GuiGraphics guiGraphics;

        public RenderBackgroundContext(AbstractContainerScreen<?> screen, GuiGraphics guiGraphics) {
            this.screen = screen;
            this.guiGraphics = guiGraphics;
        }
    }

    public static class NarrationContext {
        public final AbstractContainerScreen<?> screen;
        public final boolean onlyNarrateNew;

        public NarrationContext(AbstractContainerScreen<?> screen, boolean onlyNarrateNew) {
            this.screen = screen;
            this.onlyNarrateNew = onlyNarrateNew;
        }
    }

    public static class NarrationStateContextW {
        public final AbstractWidget widget;
        public final NarrationElementOutput narrationElementOutput;

        public NarrationStateContextW(AbstractWidget widget, NarrationElementOutput narrationElementOutput) {
            this.widget = widget;
            this.narrationElementOutput = narrationElementOutput;
        }
    }

    public static class NarrationStateContext {
        public final AbstractContainerScreen<?> screen;
        public final NarrationElementOutput narrationElementOutput;

        public NarrationStateContext(AbstractContainerScreen<?> screen, NarrationElementOutput narrationElementOutput) {
            this.screen = screen;
            this.narrationElementOutput = narrationElementOutput;
        }
    }

    public static class TooltipRenderPassContext {
        public final AbstractContainerScreen<?> screen;
        public final List<FormattedCharSequence> tooltip;
        public final ClientTooltipPositioner positioner;
        public final boolean override;

        public TooltipRenderPassContext(AbstractContainerScreen<?> screen, List<FormattedCharSequence> tooltip, ClientTooltipPositioner positioner, boolean override) {
            this.screen = screen;
            this.tooltip = tooltip;
            this.positioner = positioner;
            this.override = override;
        }
    }


    public static class FilesDropContext {
        public final AbstractContainerScreen<?> screen;
        public final List<Path> packs;

        public FilesDropContext(AbstractContainerScreen<?> screen, List<Path> packs) {
            this.screen = screen;
            this.packs = packs;
        }
    }

    public static class ResizeContext {
        public final AbstractContainerScreen<?> screen;
        public final Minecraft minecraft;
        public final int width;
        public final int height;

        public ResizeContext(AbstractContainerScreen<?> screen, Minecraft minecraft, int width, int height) {
            this.screen = screen;
            this.minecraft = minecraft;
            this.width = width;
            this.height = height;
        }
    }

    public static class MenuItemStackContext {
        public final AbstractContainerMenu menu;
        public final ItemStack itemStack;

        public MenuItemStackContext(AbstractContainerMenu menu, ItemStack itemStack) {
            this.menu = menu;
            this.itemStack = itemStack;
        }
    }

    public static class InsertTextContext {
        public final AbstractContainerScreen<?> screen;
        public final String text;
        public final boolean overwrite;

        public InsertTextContext(AbstractContainerScreen<?> screen, String text, boolean overwrite) {
            this.screen = screen;
            this.text = text;
            this.overwrite = overwrite;
        }
    }

    public static class RemoveWidgetContext {
        public final AbstractContainerScreen<?> screen;
        public final GuiEventListener listener;

        public RemoveWidgetContext(AbstractContainerScreen<?> screen, GuiEventListener listener) {
            this.screen = screen;
            this.listener = listener;
        }
    }

    public static class FocusChangeContext {
        public final AbstractContainerScreen<?> screen;
        public final ComponentPath path;

        public FocusChangeContext(AbstractContainerScreen<?> screen, ComponentPath path) {
            this.screen = screen;
            this.path = path;
        }
    }

    public static class InitialFocusContext {
        public final AbstractContainerScreen<?> screen;
        public final GuiEventListener listener;

        public InitialFocusContext(AbstractContainerScreen<?> screen, GuiEventListener listener) {
            this.screen = screen;
            this.listener = listener;
        }
    }

    public static class SlotClickedContext {
        public final AbstractContainerScreen<?> screen;
        public final Slot slot;
        public final int slotId;
        public final int mouseButton;
        public final ClickType clickType;

        public SlotClickedContext(AbstractContainerScreen<?> screen, Slot slot, int slotId, int mouseButton, ClickType clickType) {
            this.screen = screen;
            this.slot = slot;
            this.slotId = slotId;
            this.mouseButton = mouseButton;
            this.clickType = clickType;
        }
    }

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

    public static class PlayerIndexContext {
        public final Player player;
        public final int index;
        public final AbstractContainerMenu menu;

        public PlayerIndexContext(Player player, int index, AbstractContainerMenu menu) {
            this.player = player;
            this.index = index;
            this.menu = menu;
        }
    }

    public static class ContainerMenuContext {
        public final AbstractContainerMenu menu;
        public final Container container;

        public ContainerMenuContext(AbstractContainerMenu menu, Container container) {
            this.menu = menu;
            this.container = container;
        }
    }

    public static class PlayerMenuContext {
        public final Player player;
        public final AbstractContainerMenu menu;

        public PlayerMenuContext(Player player, AbstractContainerMenu menu) {
            this.player = player;
            this.menu = menu;
        }
    }

    public static class IndexContext {
        public final AbstractContainerMenu menu;
        public final int index;

        public IndexContext(int index, AbstractContainerMenu menu) {
            this.menu = menu;
            this.index = index;
        }
    }

    public static class MenuBuilderContext<T extends AbstractContainerMenu> {
        public final AbstractContainerMenu menu;
        public final AbstractContainerBuilder<T> builder;
        public final @Nullable MenuType<?> pMenuType;
        public final int pContainerId;
        public final Inventory playerInventory;

        public MenuBuilderContext(AbstractContainerMenu menu, AbstractContainerBuilder<T> builder, @Nullable MenuType<?> pMenuType, int pContainerId, Inventory playerInventory) {
            this.menu = menu;
            this.builder = builder;
            this.pMenuType = pMenuType;
            this.pContainerId = pContainerId;
            this.playerInventory = playerInventory;
        }
    }

    public static class ScreenBuilderContext {
        public final AbstractContainerBuilder<? extends AbstractContainerMenu> builder;
        public final AbstractContainerScreen<? extends AbstractContainerMenu> screen;
        public final AbstractContainerMenu menu;
        public final Inventory playerInventory;
        public final Component title;

        public ScreenBuilderContext(AbstractContainerScreen<? extends AbstractContainerMenu> screen, AbstractContainerBuilder<? extends AbstractContainerMenu> builder, AbstractContainerMenu menu, Inventory playerInventory, Component title) {

            this.builder = builder;
            this.screen = screen;
            this.menu = menu;
            this.playerInventory = playerInventory;
            this.title = title;
        }
    }

    public static class ValidClickButtonContext {
        public final AbstractWidget widget;
        public final int button;

        public ValidClickButtonContext(AbstractWidget widget, int button) {
            this.widget = widget;
            this.button = button;
        }
    }

    public static class PlayDownSoundContext {
        public final AbstractWidget widget;
        public final SoundManager handler;

        public PlayDownSoundContext(AbstractWidget widget, SoundManager handler) {
            this.widget = widget;
            this.handler = handler;
        }
    }

    public static class SetFocusedContext {
        public final AbstractWidget widget;
        public final boolean focused;

        public SetFocusedContext(AbstractWidget widget, boolean focused) {
            this.widget = widget;
            this.focused = focused;
        }
    }

    public static class MouseClickedContextW {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;
        public final int button;

        public MouseClickedContextW(AbstractWidget widget, double mouseX, double mouseY, int button) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.button = button;
        }
    }

    public static class MouseClickedContext {
        public final AbstractContainerScreen<?> screen;
        public final double mouseX;
        public final double mouseY;
        public final int button;

        public MouseClickedContext(AbstractContainerScreen<?> screen, double mouseX, double mouseY, int button) {
            this.screen = screen;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.button = button;
        }
    }

    public static class HasClickedOutsideContext {
        public final AbstractContainerScreen<?> screen;
        public final double mouseX;
        public final double mouseY;
        public final int guiLeft;
        public final int guiTop;
        public final int mouseButton;

        public HasClickedOutsideContext(AbstractContainerScreen<?> screen, double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
            this.screen = screen;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.guiLeft = guiLeft;
            this.guiTop = guiTop;
            this.mouseButton = mouseButton;
        }
    }

    public static class MouseDraggedContext {
        public final AbstractContainerScreen<?> screen;
        public final double mouseX;
        public final double mouseY;
        public final int button;
        public final double dragX;
        public final double dragY;

        public MouseDraggedContext(AbstractContainerScreen<?> screen, double mouseX, double mouseY, int button, double dragX, double dragY) {
            this.screen = screen;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.button = button;
            this.dragX = dragX;
            this.dragY = dragY;
        }
    }

    public static class IsHoveringContext {
        public final AbstractContainerScreen<?> screen;
        public final int x;
        public final int y;
        public final int width;
        public final int height;
        public final double mouseX;
        public final double mouseY;

        public IsHoveringContext(AbstractContainerScreen<?> screen, int x, int y, int width, int height, double mouseX, double mouseY) {
            this.screen = screen;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class KeyPressedContext {
        public final AbstractContainerScreen<?> screen;
        public final int keyCode;
        public final int scanCode;
        public final int modifiers;

        public KeyPressedContext(AbstractContainerScreen<?> screen, int keyCode, int scanCode, int modifiers) {
            this.screen = screen;
            this.keyCode = keyCode;
            this.scanCode = scanCode;
            this.modifiers = modifiers;
        }
    }

    public static class CheckHotbarKeyPressedContext {
        public final AbstractContainerScreen<?> screen;
        public final int keyCode;
        public final int scanCode;

        public CheckHotbarKeyPressedContext(AbstractContainerScreen<?> screen, int keyCode, int scanCode) {
            this.screen = screen;
            this.keyCode = keyCode;
            this.scanCode = scanCode;
        }
    }

    public static class LabelRenderContext {
        public final AbstractContainerScreen<?> screen;
        public final GuiGraphics guiGraphics;
        public final int mouseX;
        public final int mouseY;

        public LabelRenderContext(AbstractContainerScreen<?> screen, GuiGraphics guiGraphics, int mouseX, int mouseY) {
            this.screen = screen;
            this.guiGraphics = guiGraphics;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class TooltipRenderContext {
        public final AbstractContainerScreen<?> screen;
        public final GuiGraphics guiGraphics;
        public final int x;
        public final int y;

        public TooltipRenderContext(AbstractContainerScreen<?> screen, GuiGraphics guiGraphics, int x, int y) {
            this.screen = screen;
            this.guiGraphics = guiGraphics;
            this.x = x;
            this.y = y;
        }
    }

    public static class ScreenRenderContextW {
        public final AbstractWidget widget;
        public final GuiGraphics guiGraphics;
        public final float partialTick;
        public final int mouseX;
        public final int mouseY;

        public ScreenRenderContextW(AbstractWidget widget, GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
            this.widget = widget;
            this.guiGraphics = guiGraphics;
            this.partialTick = partialTick;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class ScreenRenderContext {
        public final AbstractContainerScreen<? extends AbstractContainerMenu> screen;
        public final GuiGraphics guiGraphics;
        public final float partialTick;
        public final int mouseX;
        public final int mouseY;

        public ScreenRenderContext(AbstractContainerScreen<? extends AbstractContainerMenu> screen, GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
            this.screen = screen;
            this.guiGraphics = guiGraphics;
            this.partialTick = partialTick;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class MouseMovedContext {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;

        public MouseMovedContext(AbstractWidget widget, double mouseX, double mouseY) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class ClickedContextW {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;

        public ClickedContextW(AbstractWidget widget, double mouseX, double mouseY) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class IsMouseOverContextW {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;

        public IsMouseOverContextW(AbstractWidget widget, double mouseX, double mouseY) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class KeyPressedContextW {
        public final AbstractWidget widget;
        public final int keyCode;
        public final int scanCode;
        public final int modifiers;

        public KeyPressedContextW(AbstractWidget widget, int keyCode, int scanCode, int modifiers) {
            this.widget = widget;
            this.keyCode = keyCode;
            this.scanCode = scanCode;
            this.modifiers = modifiers;
        }
    }

    public static class KeyReleasedContextW {
        public final AbstractWidget widget;
        public final int keyCode;
        public final int scanCode;
        public final int modifiers;

        public KeyReleasedContextW(AbstractWidget widget, int keyCode, int scanCode, int modifiers) {
            this.widget = widget;
            this.keyCode = keyCode;
            this.scanCode = scanCode;
            this.modifiers = modifiers;
        }
    }

    public static class NextFocusPathContextW {
        public final AbstractWidget widget;
        public final FocusNavigationEvent event;

        public NextFocusPathContextW(AbstractWidget widget, FocusNavigationEvent event) {
            this.widget = widget;
            this.event = event;
        }
    }

    public static class CharTypedContextW {
        public final AbstractWidget widget;
        public final char codePoint;
        public final int modifiers;

        public CharTypedContextW(AbstractWidget widget, char codePoint, int modifiers) {
            this.widget = widget;
            this.codePoint = codePoint;
            this.modifiers = modifiers;
        }
    }

    public static class MouseScrolledContextW {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;
        public final double delta;

        public MouseScrolledContextW(AbstractWidget widget, double mouseX, double mouseY, double delta) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.delta = delta;
        }
    }

    public static class IsHoveredOrFocusedContextW {
        public final AbstractWidget widget;

        public IsHoveredOrFocusedContextW(AbstractWidget widget) {
            this.widget = widget;
        }
    }

    public static class MouseDraggedContextW {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;
        public final int button;
        public final double dragX;
        public final double dragY;

        public MouseDraggedContextW(AbstractWidget widget, double mouseX, double mouseY, int button, double dragX, double dragY) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.button = button;
            this.dragX = dragX;
            this.dragY = dragY;
        }
    }

    public static class MouseMoveContext {
        public final AbstractContainerScreen<?> screen;
        public final double mouseX;
        public final double mouseY;

        public MouseMoveContext(AbstractContainerScreen<?> screen, double mouseX, double mouseY) {
            this.screen = screen;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class MenuItemContext {
        public final AbstractContainerScreen<? extends AbstractContainerMenu> screen;
        public final AbstractContainerMenu menu;
        public final int slotIndex;
        public final ItemStack itemStack;

        public MenuItemContext(AbstractContainerScreen<? extends AbstractContainerMenu> screen, AbstractContainerMenu menu, int slotIndex, ItemStack itemStack) {
            this.screen = screen;
            this.menu = menu;
            this.slotIndex = slotIndex;
            this.itemStack = itemStack;
        }
    }

    public static class ItemScreenContext {
        public final AbstractContainerScreen<?> screen;
        public final ItemStack item;

        public ItemScreenContext(AbstractContainerScreen<?> screen, ItemStack item) {
            this.screen = screen;
            this.item = item;
        }
    }

    public static class ItemSlotContext {
        public final AbstractContainerMenu menu;
        public final ItemStack item;
        public final Slot slot;

        public ItemSlotContext(AbstractContainerMenu menu, ItemStack item, Slot slot) {
            this.menu = menu;
            this.item = item;
            this.slot = slot;
        }
    }

    public static class DataChangedContext {
        public final AbstractContainerMenu menu;
        public final AbstractContainerScreen<? extends AbstractContainerMenu> screen;
        public final int dataId;
        public final int dataValue;

        public DataChangedContext(AbstractContainerMenu menu, AbstractContainerScreen<? extends AbstractContainerMenu> screen, int dataId, int dataValue) {
            this.menu = menu;
            this.screen = screen;
            this.dataId = dataId;
            this.dataValue = dataValue;
        }
    }

    public static class ContainerUpdateContext {
        public final AbstractContainerMenu menu;
        public final int stateId;
        public final List<ItemStack> items;
        public final ItemStack carried;

        public ContainerUpdateContext(AbstractContainerMenu menu, int stateId, List<ItemStack> items, ItemStack carried) {
            this.menu = menu;
            this.stateId = stateId;
            this.items = items;
            this.carried = carried;
        }
    }

    public static class IndexDataContext {
        public final AbstractContainerMenu menu;
        public final int index;
        public final int data;

        public IndexDataContext(AbstractContainerMenu menu, int index, int data) {
            this.menu = menu;
            this.index = index;
            this.data = data;
        }
    }

    public static class MenuSlotContext {
        public final AbstractContainerMenu menu;
        public final Slot slot;

        public MenuSlotContext(AbstractContainerMenu menu, Slot slot) {
            this.menu = menu;
            this.slot = slot;
        }
    }

    public static class TransferStackContext {
        public final AbstractContainerMenu menu;
        public final ItemStack stack;
        public final int startIndex;
        public final int endIndex;
        public final boolean reverseDirection;

        public TransferStackContext(AbstractContainerMenu menu, ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
            this.menu = menu;
            this.stack = stack;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.reverseDirection = reverseDirection;
        }
    }

    public static class DragContext {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;
        public final double dragX;
        public final double dragY;

        public DragContext(AbstractWidget widget, double mouseX, double mouseY, double dragX, double dragY) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.dragX = dragX;
            this.dragY = dragY;
        }
    }

    public static class OnClickContext {
        public final AbstractWidget widget;
        public final double mouseX;
        public final double mouseY;

        public OnClickContext(AbstractWidget widget, double mouseX, double mouseY) {
            this.widget = widget;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public static class WidgetInitContext {
        public final AbstractWidgetBuilder builder;
        public final AbstractContainerBuilder<?> menuBuilder;
        public final int x;
        public final int y;
        public final int width;
        public final int height;
        public final Component message;

        public WidgetInitContext(AbstractWidgetBuilder builder, AbstractContainerBuilder<?> menuBuilder, int x, int y, int width, int height, Component message) {
            this.builder = builder;
            this.menuBuilder = menuBuilder;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.message = message;
        }
    }

}
