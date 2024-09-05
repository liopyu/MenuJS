package net.liopyu.menujs.builders.widgets;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.client.widgets.AbstractWidgetJS;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractWidgetBuilder {
    private final AbstractMenuContainerBuilder<?> menuBuilder;

    public AbstractWidgetBuilder(AbstractMenuContainerBuilder<?> builder){
        this.menuBuilder = builder;
    }
    public transient Function<ContextUtils.IsMouseOverContextW, Object> isMouseOver;

    public AbstractWidgetBuilder setIsMouseOver(Function<ContextUtils.IsMouseOverContextW, Object> arg) {
        this.isMouseOver = arg;
        return this;
    }
    public transient Function<ContextUtils.CharTypedContextW, Object> charTyped;

    public AbstractWidgetBuilder setCharTyped(Function<ContextUtils.CharTypedContextW, Object> arg) {
        this.charTyped = arg;
        return this;
    }
    public transient Function<ContextUtils.NextFocusPathContextW, Object> nextFocusPath;

    public AbstractWidgetBuilder setNextFocusPath(Function<ContextUtils.NextFocusPathContextW, Object> arg) {
        this.nextFocusPath = arg;
        return this;
    }

    public transient Function<AbstractWidget, Object> getCurrentFocusPath;

    public AbstractWidgetBuilder setGetCurrentFocusPath(Function<AbstractWidget, Object> arg) {
        this.getCurrentFocusPath = arg;
        return this;
    }

    public transient Function<ContextUtils.KeyPressedContextW, Object> keyPressed;

    public AbstractWidgetBuilder setKeyPressed(Function<ContextUtils.KeyPressedContextW, Object> arg) {
        this.keyPressed = arg;
        return this;
    }
    public transient Function<ContextUtils.KeyReleasedContextW, Object> keyReleased;

    public AbstractWidgetBuilder setKeyReleased(Function<ContextUtils.KeyReleasedContextW, Object> arg) {
        this.keyReleased = arg;
        return this;
    }

    public transient Function<ContextUtils.IsHoveredOrFocusedContextW, Object> isHoveredOrFocused;

    public AbstractWidgetBuilder setIsHoveredOrFocused(Function<ContextUtils.IsHoveredOrFocusedContextW, Object> arg) {
        this.isHoveredOrFocused = arg;
        return this;
    }
    public transient Function<ContextUtils.MouseScrolledContextW, Object> mouseScrolled;

    public AbstractWidgetBuilder setMouseScrolled(Function<ContextUtils.MouseScrolledContextW, Object> arg) {
        this.mouseScrolled = arg;
        return this;
    }

    public transient Function<ContextUtils.MouseDraggedContextW, Object> mouseDragged;

    public AbstractWidgetBuilder setMouseDragged(Function<ContextUtils.MouseDraggedContextW, Object> arg) {
        this.mouseDragged = arg;
        return this;
    }
    public transient Function<ContextUtils.ClickedContextW, Object> clicked;

    public AbstractWidgetBuilder setClicked(Function<ContextUtils.ClickedContextW, Object> arg) {
        this.clicked = arg;
        return this;
    }

    public transient Function<ContextUtils.ValidClickButtonContext, Object> isValidClickButton;

    public AbstractWidgetBuilder setIsValidClickButton(Function<ContextUtils.ValidClickButtonContext, Object> arg) {
        this.isValidClickButton = arg;
        return this;
    }

    public transient Function<ContextUtils.MouseClickedContextW, Object> mouseClicked;

    public AbstractWidgetBuilder mouseClicked(Function<ContextUtils.MouseClickedContextW, Object> arg) {
        this.mouseClicked = arg;
        return this;
    }
    public transient Function<ContextUtils.MouseClickedContextW, Object> mouseReleased;

    public AbstractWidgetBuilder mouseReleased(Function<ContextUtils.MouseClickedContextW, Object> arg) {
        this.mouseReleased = arg;
        return this;
    }


    public transient Consumer<ContextUtils.ValidClickButtonContext> onValidClickButton;

    public AbstractWidgetBuilder setOnValidClickButton(Consumer<ContextUtils.ValidClickButtonContext> arg) {
        this.onValidClickButton = arg;
        return this;
    }
    public transient Consumer<ContextUtils.SetFocusedContext> onSetFocused;
    public transient Consumer<ContextUtils.SetFocusedContext> setFocused;

    public AbstractWidgetBuilder onSetFocused(Consumer<ContextUtils.SetFocusedContext> arg) {
        this.onSetFocused = arg;
        return this;
    }

    public AbstractWidgetBuilder setFocused(Consumer<ContextUtils.SetFocusedContext> arg) {
        this.setFocused = arg;
        return this;
    }
    public transient Consumer<ContextUtils.MouseMovedContext> onMouseMoved;
    public transient Consumer<ContextUtils.MouseMovedContext> mouseMoved;

    public AbstractWidgetBuilder onMouseMoved(Consumer<ContextUtils.MouseMovedContext> arg) {
        this.onMouseMoved = arg;
        return this;
    }

    public AbstractWidgetBuilder setMouseMoved(Consumer<ContextUtils.MouseMovedContext> arg) {
        this.mouseMoved = arg;
        return this;
    }

    public transient Consumer<ContextUtils.PlayDownSoundContext> onPlayDownSound;
    public transient Consumer<ContextUtils.PlayDownSoundContext> playDownSound;

    public AbstractWidgetBuilder onPlayDownSound(Consumer<ContextUtils.PlayDownSoundContext> arg) {
        this.onPlayDownSound = arg;
        return this;
    }

    public AbstractWidgetBuilder setPlayDownSound(Consumer<ContextUtils.PlayDownSoundContext> arg) {
        this.playDownSound = arg;
        return this;
    }


    public transient Consumer<ContextUtils.MouseClickedContextW> onMouseReleased;

    public AbstractWidgetBuilder setOnMouseReleased(Consumer<ContextUtils.MouseClickedContextW> arg) {
        this.onMouseReleased = arg;
        return this;
    }

    public transient Consumer<ContextUtils.DragContext> onDrag;

    public AbstractWidgetBuilder setOnDrag(Consumer<ContextUtils.DragContext> arg) {
        this.onDrag = arg;
        return this;
    }

    public transient Consumer<ContextUtils.WidgetInitContext> onInit;
    public AbstractWidgetBuilder onInit(Consumer<ContextUtils.WidgetInitContext> arg) {
        this.onInit = arg;
        return this;
    }
    public transient Consumer<ContextUtils.OnClickContext> onRelease;

    public AbstractWidgetBuilder setOnRelease(Consumer<ContextUtils.OnClickContext> arg) {
        this.onRelease = arg;
        return this;
    }

    public transient Consumer<ContextUtils.OnClickContext> onClick;

    public AbstractWidgetBuilder onClick(Consumer<ContextUtils.OnClickContext> arg) {
        this.onClick = arg;
        return this;
    }

    public transient Consumer<ContextUtils.ScreenRenderContextW> onRender;
    public transient Consumer<ContextUtils.ScreenRenderContextW> render;
    public AbstractWidgetBuilder render(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.render = arg;
        return this;
    }
    public AbstractWidgetBuilder onRender(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.onRender = arg;
        return this;
    }
    public transient Consumer<ContextUtils.ScreenRenderContextW> onRenderWidget;
    public transient Consumer<ContextUtils.ScreenRenderContextW> renderWidget;
    public AbstractWidgetBuilder renderWidget(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.renderWidget = arg;
        return this;
    }
    public AbstractWidgetBuilder onRenderWidget(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.onRenderWidget = arg;
        return this;
    }

    public transient Consumer<ContextUtils.NarrationStateContextW> updateWidgetNarration;
    public transient Consumer<ContextUtils.NarrationStateContextW> onUpdateWidgetNarration;

    public AbstractWidgetBuilder updateWidgetNarration(Consumer<ContextUtils.NarrationStateContextW> arg) {
        this.updateWidgetNarration = arg;
        return this;
    }

    public AbstractWidgetBuilder onUpdateWidgetNarration(Consumer<ContextUtils.NarrationStateContextW> arg) {
        this.onUpdateWidgetNarration = arg;
        return this;
    }
    
    public AbstractWidgetJS build(int x, int y, int width, int height, Component message) {
        return new AbstractWidgetJS(this,menuBuilder,x, y, width, height, message);
    }
    public AbstractMenuContainerBuilder<?> getMenuBuilder() {
        return menuBuilder;
    }
}
