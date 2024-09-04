package net.liopyu.menujs.client.widgets;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.widgets.AbstractWidgetBuilder;
import net.liopyu.menujs.util.ContextUtils;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;
import static net.liopyu.menujs.util.MenuJSHelperClass.convertObjectToDesired;

public class AbstractWidgetJS extends AbstractWidget {

    private final AbstractWidgetBuilder builder;
    private final AbstractMenuContainerBuilder<?> menuBuilder;

    public AbstractWidgetJS(AbstractWidgetBuilder builder, AbstractMenuContainerBuilder<?> menuBuilder, int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.builder = builder;
        this.menuBuilder = menuBuilder;
        var context = new ContextUtils.WidgetInitContext(builder,menuBuilder,x,y,width,height,message);
        consumerCallback(builder.onInit,context,"Error in " + menuName() + "builder for field: onInit.");
    }
    public String menuName(){
        return this.menuBuilder.id.toString();
    }
    public AbstractWidgetBuilder getBuilder() {
        return builder;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int i, int i1, float v) {
        if (builder.renderWidget != null) {
            final ContextUtils.ScreenRenderContextW context = new ContextUtils.ScreenRenderContextW(this,guiGraphics,v,i,i1);
            consumerCallback(builder.renderWidget, context, "Error in " + menuName() + "builder for field: renderWidget.");
        }
    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput p_169396_) {
        var context = new ContextUtils.NarrationStateContextW(this, p_169396_);
        if (builder.updateWidgetNarration != null) {
            consumerCallback(builder.updateWidgetNarration, context, "Error in " + menuName() + "builder for field: updateWidgetNarration.");
        }

        if (builder.onUpdateWidgetNarration != null) {
            consumerCallback(builder.onUpdateWidgetNarration, context, "Error in " + menuName() + "builder for field: onUpdateWidgetNarration.");
        }
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        var context = new ContextUtils.ScreenRenderContextW(this,pGuiGraphics,pPartialTick,pMouseX,pMouseY);
        if (builder.render != null) {
            consumerCallback(builder.render, context, "Error in " + menuName() + "builder for field: render.");
        }else super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (builder.onRender != null) {
            consumerCallback(builder.onRender, context, "Error in " + menuName() + "builder for field: onRender.");
        }
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        if (builder.onClick != null) {
            var context = new ContextUtils.OnClickContext(this, pMouseX, pMouseY);
            consumerCallback(builder.onClick, context, "Error in " + menuName() + " builder for field: onClick.");
        }
        super.onClick(pMouseX, pMouseY);
    }



    @Override
    public void onRelease(double pMouseX, double pMouseY) {
        if (builder.onRelease != null) {
            var context = new ContextUtils.OnClickContext(this, pMouseX, pMouseY);
            consumerCallback(builder.onRelease, context, "Error in " + menuName() + " builder for field: onRelease.");
        }
        super.onRelease(pMouseX, pMouseY);
    }

    @Override
    protected void onDrag(double pMouseX, double pMouseY, double pDragX, double pDragY) {
        if (builder.onDrag != null) {
            var context = new ContextUtils.DragContext(this, pMouseX, pMouseY, pDragX, pDragY);
            consumerCallback(builder.onDrag, context, "Error in " + menuName() + " builder for field: onDrag.");
        }
        super.onDrag(pMouseX, pMouseY, pDragX, pDragY);
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
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (builder.onMouseReleased != null) {
            var context = new ContextUtils.MouseClickedContextW(this, pMouseX, pMouseY, pButton);
            consumerCallback(builder.onMouseReleased, context, "Error in " + menuName() + " builder for field: onMouseReleased.");
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }


    @Override
    protected boolean isValidClickButton(int pButton) {
        if (builder.onValidClickButton != null) {
            var context = new ContextUtils.ValidClickButtonContext(this, pButton);
            consumerCallback(builder.onValidClickButton, context, "Error in " + menuName() + " builder for field: onValidClickButton.");
        }
        return super.isValidClickButton(pButton);
    }


    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    protected boolean clicked(double pMouseX, double pMouseY) {
        return super.clicked(pMouseX, pMouseY);
    }

    @Nullable
    @Override
    public ComponentPath nextFocusPath(FocusNavigationEvent pEvent) {
        return super.nextFocusPath(pEvent);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        return super.isMouseOver(pMouseX, pMouseY);
    }

    @Override
    public void playDownSound(SoundManager pHandler) {
        super.playDownSound(pHandler);
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public void setWidth(int pWidth) {
        super.setWidth(pWidth);
    }

    @Override
    public void setHeight(int value) {
        super.setHeight(value);
    }

    @Override
    public void setAlpha(float pAlpha) {
        super.setAlpha(pAlpha);
    }

    @Override
    public void setMessage(Component pMessage) {
        super.setMessage(pMessage);
    }

    @Override
    public Component getMessage() {
        return super.getMessage();
    }

    @Override
    public boolean isFocused() {
        return super.isFocused();
    }

    @Override
    public boolean isHovered() {
        return super.isHovered();
    }

    @Override
    public boolean isHoveredOrFocused() {
        return super.isHoveredOrFocused();
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void setFocused(boolean pFocused) {
        super.setFocused(pFocused);
    }

    @Override
    public int getFGColor() {
        return super.getFGColor();
    }

    @Override
    public void setFGColor(int color) {
        super.setFGColor(color);
    }

    @Override
    public void clearFGColor() {
        super.clearFGColor();
    }

    @Override
    public NarrationPriority narrationPriority() {
        return super.narrationPriority();
    }

    @Override
    protected void defaultButtonNarrationText(NarrationElementOutput pNarrationElementOutput) {
        super.defaultButtonNarrationText(pNarrationElementOutput);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int pX) {
        super.setX(pX);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int pY) {
        super.setY(pY);
    }

    @Override
    public void visitWidgets(Consumer<AbstractWidget> pConsumer) {
        super.visitWidgets(pConsumer);
    }

    @Override
    public ScreenRectangle getRectangle() {
        return super.getRectangle();
    }

    @Override
    public int getTabOrderGroup() {
        return super.getTabOrderGroup();
    }

    @Override
    public void setTabOrderGroup(int pTabOrderGroup) {
        super.setTabOrderGroup(pTabOrderGroup);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Nullable
    @Override
    public ComponentPath getCurrentFocusPath() {
        return super.getCurrentFocusPath();
    }

    @Override
    public void setPosition(int pX, int pY) {
        super.setPosition(pX, pY);
    }
}