package net.liopyu.menujs.client;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.widgets.AbstractWidgetBuilder;
import net.liopyu.menujs.util.ContextUtils;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.Music;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;
import static net.liopyu.menujs.util.MenuJSHelperClass.convertObjectToDesired;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@OnlyIn(Dist.CLIENT)
public class AbstractContainerScreenJS<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> implements ContainerListener {
    private final AbstractMenuContainerBuilder<T> builder;
    private final Inventory playerInventory;

    public AbstractContainerScreenJS(AbstractMenuContainerBuilder<T> builder, T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.builder = builder;
        this.playerInventory = pPlayerInventory;
        for (AbstractWidget widget : builder.renderableWidgets) {
            this.addRenderableWidget(widget);
        }
    }

    public AbstractMenuContainerBuilder<T> getBuilder() {
        return builder;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    protected void init() {
        super.init();
        this.menu.addSlotListener(this);
        var context = new ContextUtils.ScreenBuilderContext<>(this, builder, getMenu(), getPlayerInventory(), getTitle());
        consumerCallback(builder.onScreenInit, context, "Error in " + menuName() + "builder for field: onScreenInit.");
    }

    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }

    public String menuName() {
        return this.builder.id.toString();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        if (builder.renderBg != null) {
            final ContextUtils.ScreenRenderContext<T> context = new ContextUtils.ScreenRenderContext<>(this, guiGraphics, v, i, i1);
            consumerCallback(builder.renderBg, context, "Error in " + menuName() + "builder for field: renderBg.");
        }
    }

    @Override
    public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
        if (builder.onScreenSlotChanged != null) {
            final ContextUtils.MenuItemContext<T> context = new ContextUtils.MenuItemContext<>(this, getMenu(), i, itemStack);
            consumerCallback(builder.onScreenSlotChanged, context, "Error in " + menuName() + "builder for field: onScreenSlotChanged.");
        }
    }

    @Override
    public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int i1) {
        if (builder.onDataChanged != null) {
            final ContextUtils.DataChangedContext<T> context = new ContextUtils.DataChangedContext<>(getMenu(), this, i, i1);
            consumerCallback(builder.onDataChanged, context, "Error in " + menuName() + "builder for field: onDataChanged.");
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        var context = new ContextUtils.ScreenRenderContext<>(this, pGuiGraphics, pPartialTick, pMouseX, pMouseY);
        if (builder.render != null) {
            consumerCallback(builder.render, context, "Error in " + menuName() + "builder for field: render.");
        } else super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (builder.onRender != null) {
            consumerCallback(builder.onRender, context, "Error in " + menuName() + "builder for field: onRender.");
        }
    }

    @Override
    protected void renderTooltip(GuiGraphics pGuiGraphics, int pX, int pY) {
        var context = new ContextUtils.TooltipRenderContext(this, pGuiGraphics, pX, pY);
        if (builder.renderTooltip != null) {
            consumerCallback(builder.renderTooltip, context, "Error in " + menuName() + "builder for field: renderTooltip.");
        } else {
            super.renderTooltip(pGuiGraphics, pX, pY);
        }

        if (builder.onRenderTooltip != null) {
            consumerCallback(builder.renderTooltip, context, "Error in " + menuName() + "builder for field: onRenderTooltip.");
        }
    }


    @Override
    protected List<Component> getTooltipFromContainerItem(ItemStack pStack) {
        if (builder.setTooltipFromContainerItem != null) {
            try {
                var context = new ContextUtils.ItemScreenContext(this, pStack);
                var obj = convertObjectToDesired(builder.setTooltipFromContainerItem.apply(context), "list");
                if (obj != null) {
                    return (List<Component>) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for setTooltipFromContainerItem from menu: " + menuName() + ". Value: " + obj + ". Must be a List of Components. Defaulting to super method: " + super.getTooltipFromContainerItem(pStack));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field setTooltipFromContainerItem: " + menuName() + ".", e);
            }
        }
        return super.getTooltipFromContainerItem(pStack);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        var context = new ContextUtils.LabelRenderContext(this, pGuiGraphics, pMouseX, pMouseY);
        if (builder.renderLabels != null) {
            consumerCallback(builder.renderLabels, context, "Error in " + menuName() + "builder for field: renderLabels.");
        } else {
            super.renderLabels(pGuiGraphics, pMouseX, pMouseY);
        }

        if (builder.onRenderLabels != null) {
            consumerCallback(builder.onRenderLabels, context, "Error in " + menuName() + "builder for field: onRenderLabels.");
        }
    }


    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (builder.mouseClicked != null) {
            try {
                var context = new ContextUtils.MouseClickedContext(this, pMouseX, pMouseY, pButton);
                var obj = convertObjectToDesired(builder.mouseClicked.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseClicked from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseClicked(pMouseX, pMouseY, pButton));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseClicked: " + menuName() + ".", e);
            }
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }


    @Override
    protected boolean hasClickedOutside(double pMouseX, double pMouseY, int pGuiLeft, int pGuiTop, int pMouseButton) {
        if (builder.hasClickedOutside != null) {
            try {
                var context = new ContextUtils.HasClickedOutsideContext(this, pMouseX, pMouseY, pGuiLeft, pGuiTop, pMouseButton);
                var obj = convertObjectToDesired(builder.hasClickedOutside.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for hasClickedOutside from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.hasClickedOutside(pMouseX, pMouseY, pGuiLeft, pGuiTop, pMouseButton));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field hasClickedOutside: " + menuName() + ".", e);
            }
        }
        return super.hasClickedOutside(pMouseX, pMouseY, pGuiLeft, pGuiTop, pMouseButton);
    }


    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (builder.mouseDragged != null) {
            try {
                var context = new ContextUtils.MouseDraggedContext(this, pMouseX, pMouseY, pButton, pDragX, pDragY);
                var obj = convertObjectToDesired(builder.mouseDragged.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseDragged from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseDragged: " + menuName() + ".", e);
            }
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }


    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (builder.mouseReleased != null) {
            try {
                var context = new ContextUtils.MouseClickedContext(this, pMouseX, pMouseY, pButton);
                var obj = convertObjectToDesired(builder.mouseReleased.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for mouseReleased from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.mouseReleased(pMouseX, pMouseY, pButton));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field mouseReleased: " + menuName() + ".", e);
            }
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }


    @Override
    public void clearDraggingState() {
        if (builder.clearDraggingState != null) {
            consumerCallback(builder.clearDraggingState, this, "Error in " + menuName() + "builder for field: clearDraggingState.");
        } else {
            super.clearDraggingState();
        }

        if (builder.onClearDraggingState != null) {
            consumerCallback(builder.onClearDraggingState, this, "Error in " + menuName() + "builder for field: onClearDraggingState.");
        }
    }


    @Override
    protected boolean isHovering(int pX, int pY, int pWidth, int pHeight, double pMouseX, double pMouseY) {
        if (builder.isHovering != null) {
            try {
                var context = new ContextUtils.IsHoveringContext(this, pX, pY, pWidth, pHeight, pMouseX, pMouseY);
                var obj = convertObjectToDesired(builder.isHovering.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for isHovering from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field isHovering: " + menuName() + ".", e);
            }
        }
        return super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY);
    }


    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        var context = new ContextUtils.SlotClickedContext(this, pSlot, pSlotId, pMouseButton, pType);
        if (builder.slotClicked != null) {
            consumerCallback(builder.slotClicked, context, "Error in " + menuName() + "builder for field: slotClicked.");
        } else {
            super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
        }

        if (builder.onSlotClicked != null) {
            consumerCallback(builder.onSlotClicked, context, "Error in " + menuName() + "builder for field: onSlotClicked.");
        }
    }


    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (builder.keyPressed != null) {
            try {
                var context = new ContextUtils.KeyPressedContext(this, pKeyCode, pScanCode, pModifiers);
                var obj = convertObjectToDesired(builder.keyPressed.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for keyPressed from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.keyPressed(pKeyCode, pScanCode, pModifiers));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field keyPressed: " + menuName() + ".", e);
            }
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }


    @Override
    protected boolean checkHotbarKeyPressed(int pKeyCode, int pScanCode) {
        if (builder.checkHotbarKeyPressed != null) {
            try {
                var context = new ContextUtils.CheckHotbarKeyPressedContext(this, pKeyCode, pScanCode);
                var obj = convertObjectToDesired(builder.checkHotbarKeyPressed.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for checkHotbarKeyPressed from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.checkHotbarKeyPressed(pKeyCode, pScanCode));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field checkHotbarKeyPressed: " + menuName() + ".", e);
            }
        }
        return super.checkHotbarKeyPressed(pKeyCode, pScanCode);
    }


    @Override
    public boolean isPauseScreen() {
        if (builder.isPauseScreen != null) {
            try {
                var obj = convertObjectToDesired(builder.isPauseScreen.apply(this), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for isPauseScreen from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.isPauseScreen());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field isPauseScreen: " + menuName() + ".", e);
            }
        }
        return super.isPauseScreen();
    }


    @Override
    protected void containerTick() {
        if (builder.containerTick != null) {
            consumerCallback(builder.containerTick, this, "Error in " + menuName() + "builder for field: containerTick.");
        } else {
            super.containerTick();
        }

        if (builder.onContainerTick != null) {
            consumerCallback(builder.onContainerTick, this, "Error in " + menuName() + "builder for field: onContainerTick.");
        }
    }


    @Override
    public @Nullable Slot getSlotUnderMouse() {
        if (builder.getSlotUnderMouse != null) {
            try {
                var obj = builder.getSlotUnderMouse.apply(this);
                if (obj instanceof Slot) {
                    return (Slot) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for getSlotUnderMouse from menu: " + menuName() + ". Value: " + obj + ". Must be a Slot or null. Defaulting to super method: " + super.getSlotUnderMouse());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field getSlotUnderMouse: " + menuName() + ".", e);
            }
        }
        return super.getSlotUnderMouse();
    }


    @Override
    public int getGuiLeft() {
        if (builder.getGuiLeft != null) {
            try {
                var obj = convertObjectToDesired(builder.getGuiLeft.apply(this), "integer");
                if (obj != null) {
                    return (int) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for getGuiLeft from menu: " + menuName() + ". Value: " + obj + ". Must be an integer. Defaulting to super method: " + super.getGuiLeft());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field getGuiLeft: " + menuName() + ".", e);
            }
        }
        return super.getGuiLeft();
    }


    @Override
    public int getGuiTop() {
        if (builder.getGuiTop != null) {
            try {
                var obj = convertObjectToDesired(builder.getGuiTop.apply(this), "integer");
                if (obj != null) {
                    return (int) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for getGuiTop from menu: " + menuName() + ". Value: " + obj + ". Must be an integer. Defaulting to super method: " + super.getGuiTop());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field getGuiTop: " + menuName() + ".", e);
            }
        }
        return super.getGuiTop();
    }


    @Override
    public int getXSize() {
        if (builder.xSize != null) {
            return builder.xSize;
        }
        return super.getXSize();
    }

    @Override
    public int getYSize() {
        if (builder.ySize != null) {
            return builder.ySize;
        }
        return super.getYSize();
    }


    @Override
    public void onClose() {
        if (builder.close != null) {
            consumerCallback(builder.close, this, "Error in " + menuName() + "builder for field: close.");
        } else {
            super.onClose();
        }

        if (builder.onClose != null) {
            consumerCallback(builder.onClose, this, "Error in " + menuName() + "builder for field: onClose.");
        }
    }


    @Override
    public Component getTitle() {
        if (builder.title != null) {
            return builder.title;
        }
        return super.getTitle();
    }


    @Override
    public Component getNarrationMessage() {
        if (builder.narrationMessage != null) {
            return builder.narrationMessage;
        }
        return super.getNarrationMessage();
    }


    @Override
    protected void setInitialFocus(GuiEventListener pListener) {
        var context = new ContextUtils.InitialFocusContext(this, pListener);
        if (builder.onSetInitialFocus != null) {
            consumerCallback(builder.onSetInitialFocus, context, "Error in " + menuName() + "builder for field: onSetInitialFocus.");
        }

        if (builder.setInitialFocus != null) {
            consumerCallback(builder.setInitialFocus, context, "Error in " + menuName() + "builder for field: setInitialFocus.");
        } else {
            super.setInitialFocus(pListener);
        }
    }


    @Override
    protected void changeFocus(ComponentPath pPath) {
        var context = new ContextUtils.FocusChangeContext(this, pPath);
        if (builder.onChangeFocus != null) {
            consumerCallback(builder.onChangeFocus, context, "Error in " + menuName() + "builder for field: onChangeFocus.");
        }

        if (builder.changeFocus != null) {
            consumerCallback(builder.changeFocus, context, "Error in " + menuName() + "builder for field: changeFocus.");
        } else {
            super.changeFocus(pPath);
        }
    }


    @Override
    public boolean shouldCloseOnEsc() {
        if (builder.shouldCloseOnEsc != null) {
            return builder.shouldCloseOnEsc;
        }
        return super.shouldCloseOnEsc();
    }


    @Override
    protected <t extends GuiEventListener & Renderable & NarratableEntry> t addRenderableWidget(t pWidget) {
        return super.addRenderableWidget(pWidget);
    }

    @Override
    protected <t extends Renderable> t addRenderableOnly(t pRenderable) {
        return super.addRenderableOnly(pRenderable);
    }

    @Override
    protected <t extends GuiEventListener & NarratableEntry> t addWidget(t pListener) {
        return super.addWidget(pListener);
    }

    @Override
    protected void removeWidget(GuiEventListener pListener) {
        var context = new ContextUtils.RemoveWidgetContext(this, pListener);
        if (builder.onRemoveWidget != null) {
            consumerCallback(builder.onRemoveWidget, context, "Error in " + menuName() + "builder for field: onRemoveWidget.");
        }

        if (builder.removeWidget != null) {
            consumerCallback(builder.removeWidget, context, "Error in " + menuName() + "builder for field: removeWidget.");
        } else {
            super.removeWidget(pListener);
        }
    }


    @Override
    protected void clearWidgets() {
        if (builder.onClearWidgets != null) {
            consumerCallback(builder.onClearWidgets, this, "Error in " + menuName() + "builder for field: onClearWidgets.");
        }

        if (builder.clearWidgets != null) {
            consumerCallback(builder.clearWidgets, this, "Error in " + menuName() + "builder for field: clearWidgets.");
        } else {
            super.clearWidgets();
        }
    }


    @Override
    protected void insertText(String pText, boolean pOverwrite) {
        var context = new ContextUtils.InsertTextContext(this, pText, pOverwrite);
        if (builder.insertText != null) {
            consumerCallback(builder.insertText, context, "Error in " + menuName() + "builder for field: insertText.");
        } else {
            super.insertText(pText, pOverwrite);
        }

        if (builder.onInsertText != null) {
            consumerCallback(builder.onInsertText, context, "Error in " + menuName() + "builder for field: onInsertText.");
        }
    }


    @Override
    public boolean handleComponentClicked(@Nullable Style pStyle) {
        return super.handleComponentClicked(pStyle);
    }

    @Override
    protected void rebuildWidgets() {
        if (builder.rebuildWidgets != null) {
            consumerCallback(builder.rebuildWidgets, this, "Error in " + menuName() + "builder for field: rebuildWidgets.");
        } else {
            super.rebuildWidgets();
        }

        if (builder.onRebuildWidgets != null) {
            consumerCallback(builder.onRebuildWidgets, this, "Error in " + menuName() + "builder for field: onRebuildWidgets.");
        }
    }


    @Override
    public List<? extends GuiEventListener> children() {
        return super.children();
    }

    @Override
    public void added() {
        if (builder.added != null) {
            consumerCallback(builder.added, this, "Error in " + menuName() + "builder for field: added.");
        } else {
            super.added();
        }

        if (builder.onAdded != null) {
            consumerCallback(builder.onAdded, this, "Error in " + menuName() + "builder for field: onAdded.");
        }
    }


    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        var context = new ContextUtils.RenderBackgroundContext(this, pGuiGraphics);

        if (builder.renderBackground != null) {
            consumerCallback(builder.renderBackground, context, "Error in " + menuName() + "builder for field: renderBackground.");
        } else {
            super.renderBackground(pGuiGraphics);
        }

        if (builder.onRenderBackground != null) {
            consumerCallback(builder.onRenderBackground, context, "Error in " + menuName() + "builder for field: onRenderBackground.");
        }
    }


    @Override
    public void renderDirtBackground(GuiGraphics pGuiGraphics) {
        var context = new ContextUtils.RenderBackgroundContext(this, pGuiGraphics);

        if (builder.renderDirtBackground != null) {
            consumerCallback(builder.renderDirtBackground, context, "Error in " + menuName() + "builder for field: renderDirtBackground.");
        } else {
            super.renderDirtBackground(pGuiGraphics);
        }

        if (builder.onRenderDirtBackground != null) {
            consumerCallback(builder.onRenderDirtBackground, context, "Error in " + menuName() + "builder for field: onRenderDirtBackground.");
        }
    }


    @Override
    protected void repositionElements() {
        if (builder.repositionElements != null) {
            consumerCallback(builder.repositionElements, this, "Error in " + menuName() + "builder for field: repositionElements.");
        } else {
            super.repositionElements();
        }

        if (builder.onRepositionElements != null) {
            consumerCallback(builder.onRepositionElements, this, "Error in " + menuName() + "builder for field: onRepositionElements.");
        }
    }


    @Override
    public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
        var context = new ContextUtils.ResizeContext(this, pMinecraft, pWidth, pHeight);

        if (builder.resize != null) {
            consumerCallback(builder.resize, context, "Error in " + menuName() + "builder for field: resize.");
        } else {
            super.resize(pMinecraft, pWidth, pHeight);
        }

        if (builder.onResize != null) {
            consumerCallback(builder.onResize, context, "Error in " + menuName() + "builder for field: onResize.");
        }
    }


    @Override
    protected boolean isValidCharacterForName(String pText, char pCharTyped, int pCursorPos) {
        return super.isValidCharacterForName(pText, pCharTyped, pCursorPos);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return super.isMouseOver(pMouseX, pMouseY);
    }

    @Override
    public void onFilesDrop(List<Path> pPacks) {
        var context = new ContextUtils.FilesDropContext(this, pPacks);

        if (builder.onFilesDrop != null) {
            consumerCallback(builder.onFilesDrop, context, "Error in " + menuName() + "builder for field: onFilesDrop.");
        } else {
            super.onFilesDrop(pPacks);
        }

        if (builder.filesDrop != null) {
            consumerCallback(builder.filesDrop, context, "Error in " + menuName() + "builder for field: filesDrop.");
        }
    }


    @Override
    public void afterMouseMove() {
        if (builder.afterMouseMove != null) {
            consumerCallback(builder.afterMouseMove, this, "Error in " + menuName() + "builder for field: afterMouseMove.");
        } else {
            super.afterMouseMove();
        }

        if (builder.onAfterMouseMove != null) {
            consumerCallback(builder.onAfterMouseMove, this, "Error in " + menuName() + "builder for field: onAfterMouseMove.");
        }
    }


    @Override
    public void afterMouseAction() {
        if (builder.afterMouseAction != null) {
            consumerCallback(builder.afterMouseAction, this, "Error in " + menuName() + "builder for field: afterMouseAction.");
        } else {
            super.afterMouseAction();
        }

        if (builder.onAfterMouseAction != null) {
            consumerCallback(builder.onAfterMouseAction, this, "Error in " + menuName() + "builder for field: onAfterMouseAction.");
        }
    }


    @Override
    public void afterKeyboardAction() {
        if (builder.afterKeyboardAction != null) {
            consumerCallback(builder.afterKeyboardAction, this, "Error in " + menuName() + "builder for field: afterKeyboardAction.");
        } else {
            super.afterKeyboardAction();
        }

        if (builder.onAfterKeyboardAction != null) {
            consumerCallback(builder.onAfterKeyboardAction, this, "Error in " + menuName() + "builder for field: onAfterKeyboardAction.");
        }
    }


    @Override
    public void handleDelayedNarration() {
        if (builder.handleDelayedNarration != null) {
            consumerCallback(builder.handleDelayedNarration, this, "Error in " + menuName() + "builder for field: handleDelayedNarration.");
        } else {
            super.handleDelayedNarration();
        }

        if (builder.onHandleDelayedNarration != null) {
            consumerCallback(builder.onHandleDelayedNarration, this, "Error in " + menuName() + "builder for field: onHandleDelayedNarration.");
        }
    }


    @Override
    public void triggerImmediateNarration(boolean pOnlyNarrateNew) {
        var context = new ContextUtils.NarrationContext(this, pOnlyNarrateNew);

        if (builder.triggerImmediateNarration != null) {
            consumerCallback(builder.triggerImmediateNarration, context, "Error in " + menuName() + "builder for field: triggerImmediateNarration.");
        } else {
            super.triggerImmediateNarration(pOnlyNarrateNew);
        }

        if (builder.onTriggerImmediateNarration != null) {
            consumerCallback(builder.onTriggerImmediateNarration, context, "Error in " + menuName() + "builder for field: onTriggerImmediateNarration.");
        }
    }

    @Override
    protected boolean shouldNarrateNavigation() {
        return super.shouldNarrateNavigation();
    }

    @Override
    protected void updateNarrationState(NarrationElementOutput p_169396_) {
        var context = new ContextUtils.NarrationStateContext(this, p_169396_);

        if (builder.updateNarrationState != null) {
            consumerCallback(builder.updateNarrationState, context, "Error in " + menuName() + "builder for field: updateNarrationState.");
        } else {
            super.updateNarrationState(p_169396_);
        }

        if (builder.onUpdateNarrationState != null) {
            consumerCallback(builder.onUpdateNarrationState, context, "Error in " + menuName() + "builder for field: onUpdateNarrationState.");
        }
    }

    @Override
    protected void updateNarratedWidget(NarrationElementOutput pNarrationElementOutput) {
        var context = new ContextUtils.NarrationStateContext(this, pNarrationElementOutput);

        if (builder.updateNarratedWidget != null) {
            consumerCallback(builder.updateNarratedWidget, context, "Error in " + menuName() + "builder for field: updateNarratedWidget.");
        } else {
            super.updateNarratedWidget(pNarrationElementOutput);
        }

        if (builder.onUpdateNarratedWidget != null) {
            consumerCallback(builder.onUpdateNarratedWidget, context, "Error in " + menuName() + "builder for field: onUpdateNarratedWidget.");
        }
    }


    @Override
    public void narrationEnabled() {
        if (builder.narrationEnabled != null) {
            consumerCallback(builder.narrationEnabled, this, "Error in " + menuName() + "builder for field: narrationEnabled.");
        } else {
            super.narrationEnabled();
        }

        if (builder.onNarrationEnabled != null) {
            consumerCallback(builder.onNarrationEnabled, this, "Error in " + menuName() + "builder for field: onNarrationEnabled.");
        }
    }


    @Override
    public void setTooltipForNextRenderPass(List<FormattedCharSequence> pTooltip, ClientTooltipPositioner pPositioner, boolean pOverride) {
        var context = new ContextUtils.TooltipRenderPassContext(this, pTooltip, pPositioner, pOverride);

        if (builder.setTooltipForNextRenderPass != null) {
            consumerCallback(builder.setTooltipForNextRenderPass, context, "Error in " + menuName() + "builder for field: setTooltipForNextRenderPass.");
        } else {
            super.setTooltipForNextRenderPass(pTooltip, pPositioner, pOverride);
        }

        if (builder.onSetTooltipForNextRenderPass != null) {
            consumerCallback(builder.onSetTooltipForNextRenderPass, context, "Error in " + menuName() + "builder for field: onSetTooltipForNextRenderPass.");
        }
    }


    @Override
    public ScreenRectangle getRectangle() {
        return super.getRectangle();
    }

    @Nullable
    @Override
    public Music getBackgroundMusic() {
        return super.getBackgroundMusic();
    }

    @Nullable
    @Override
    public GuiEventListener getFocused() {
        return super.getFocused();
    }

    @Override
    public Optional<GuiEventListener> getChildAt(double pMouseX, double pMouseY) {
        return super.getChildAt(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public boolean isFocused() {
        return super.isFocused();
    }

    @Override
    public void setFocused(@Nullable GuiEventListener pListener) {
        var context = new ContextUtils.RemoveWidgetContext(this, pListener);

        if (builder.setFocused != null) {
            consumerCallback(builder.setFocused, context, "Error in " + menuName() + "builder for field: setFocused.");
        } else {
            super.setFocused(pListener);
        }

        if (builder.onSetFocused != null) {
            consumerCallback(builder.onSetFocused, context, "Error in " + menuName() + "builder for field: onSetFocused.");
        }
    }

    @Nullable
    @Override
    public ComponentPath getCurrentFocusPath() {
        return super.getCurrentFocusPath();
    }

    @Override
    public void magicalSpecialHackyFocus(@Nullable GuiEventListener pEventListener) {
        var context = new ContextUtils.RemoveWidgetContext(this, pEventListener);

        if (builder.magicalSpecialHackyFocus != null) {
            consumerCallback(builder.magicalSpecialHackyFocus, context, "Error in " + menuName() + "builder for field: magicalSpecialHackyFocus.");
        } else {
            super.magicalSpecialHackyFocus(pEventListener);
        }

        if (builder.onMagicalSpecialHackyFocus != null) {
            consumerCallback(builder.onMagicalSpecialHackyFocus, context, "Error in " + menuName() + "builder for field: onMagicalSpecialHackyFocus.");
        }
    }


    @Nullable
    @Override
    public ComponentPath nextFocusPath(FocusNavigationEvent pEvent) {
        return super.nextFocusPath(pEvent);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        var context = new ContextUtils.MouseMoveContext(this, pMouseX, pMouseY);

        if (builder.mouseMoved != null) {
            consumerCallback(builder.mouseMoved, context, "Error in " + menuName() + "builder for field: mouseMoved.");
        } else {
            super.mouseMoved(pMouseX, pMouseY);
        }

        if (builder.onMouseMoved != null) {
            consumerCallback(builder.onMouseMoved, context, "Error in " + menuName() + "builder for field: onMouseMoved.");
        }
    }


    @Override
    public int getTabOrderGroup() {
        return super.getTabOrderGroup();
    }
}
