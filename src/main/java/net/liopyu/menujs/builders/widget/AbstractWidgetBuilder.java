package net.liopyu.menujs.builders.widget;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.liopyu.menujs.builders.AbstractContainerBuilder;
import net.liopyu.menujs.client.widgets.AbstractWidgetJS;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractWidgetBuilder {
    private final AbstractContainerBuilder<?> menuBuilder;
    public transient Function<ContextUtils.IsMouseOverContextW, Object> isMouseOver;
    public transient Function<ContextUtils.CharTypedContextW, Object> charTyped;
    public transient Function<ContextUtils.NextFocusPathContextW, Object> nextFocusPath;
    public transient Function<AbstractWidget, Object> getCurrentFocusPath;
    public transient Function<ContextUtils.KeyPressedContextW, Object> keyPressed;
    public transient Function<ContextUtils.KeyReleasedContextW, Object> keyReleased;
    public transient Function<ContextUtils.IsHoveredOrFocusedContextW, Object> isHoveredOrFocused;
    public transient Function<ContextUtils.MouseScrolledContextW, Object> mouseScrolled;
    public transient Function<ContextUtils.MouseDraggedContextW, Object> mouseDragged;
    public transient Function<ContextUtils.ClickedContextW, Object> clicked;
    public transient Function<ContextUtils.ValidClickButtonContext, Object> isValidClickButton;
    public transient Function<ContextUtils.MouseClickedContextW, Object> mouseClicked;
    public transient Function<ContextUtils.MouseClickedContextW, Object> mouseReleased;
    public transient Consumer<ContextUtils.ValidClickButtonContext> onValidClickButton;
    public transient Consumer<ContextUtils.SetFocusedContext> onSetFocused;
    public transient Consumer<ContextUtils.SetFocusedContext> setFocused;
    public transient Consumer<ContextUtils.MouseMovedContext> onMouseMoved;
    public transient Consumer<ContextUtils.MouseMovedContext> mouseMoved;
    public transient Consumer<ContextUtils.PlayDownSoundContext> onPlayDownSound;
    public transient Consumer<ContextUtils.PlayDownSoundContext> playDownSound;
    public transient Consumer<ContextUtils.MouseClickedContextW> onMouseReleased;
    public transient Consumer<ContextUtils.DragContext> onDrag;
    public transient Consumer<ContextUtils.WidgetInitContext> onInit;
    public transient Consumer<ContextUtils.OnClickContext> onRelease;
    public transient Consumer<ContextUtils.OnClickContext> onClick;
    public transient Consumer<ContextUtils.ScreenRenderContextW> onRender;
    public transient Consumer<ContextUtils.ScreenRenderContextW> render;
    public transient Consumer<ContextUtils.ScreenRenderContextW> onRenderWidget;
    public transient Consumer<ContextUtils.ScreenRenderContextW> renderWidget;
    public transient Consumer<ContextUtils.NarrationStateContextW> updateWidgetNarration;
    public transient Consumer<ContextUtils.NarrationStateContextW> onUpdateWidgetNarration;

    public AbstractWidgetBuilder(AbstractContainerBuilder<?> builder) {
        this.menuBuilder = builder;
    }

    public AbstractWidgetBuilder setIsMouseOver(Function<ContextUtils.IsMouseOverContextW, Object> arg) {
        this.isMouseOver = arg;
        return this;
    }

    public AbstractWidgetBuilder setCharTyped(Function<ContextUtils.CharTypedContextW, Object> arg) {
        this.charTyped = arg;
        return this;
    }

    public AbstractWidgetBuilder setNextFocusPath(Function<ContextUtils.NextFocusPathContextW, Object> arg) {
        this.nextFocusPath = arg;
        return this;
    }

    public AbstractWidgetBuilder setGetCurrentFocusPath(Function<AbstractWidget, Object> arg) {
        this.getCurrentFocusPath = arg;
        return this;
    }

    public AbstractWidgetBuilder setKeyPressed(Function<ContextUtils.KeyPressedContextW, Object> arg) {
        this.keyPressed = arg;
        return this;
    }

    public AbstractWidgetBuilder setKeyReleased(Function<ContextUtils.KeyReleasedContextW, Object> arg) {
        this.keyReleased = arg;
        return this;
    }

    public AbstractWidgetBuilder setIsHoveredOrFocused(Function<ContextUtils.IsHoveredOrFocusedContextW, Object> arg) {
        this.isHoveredOrFocused = arg;
        return this;
    }

    public AbstractWidgetBuilder setMouseScrolled(Function<ContextUtils.MouseScrolledContextW, Object> arg) {
        this.mouseScrolled = arg;
        return this;
    }

    public AbstractWidgetBuilder setMouseDragged(Function<ContextUtils.MouseDraggedContextW, Object> arg) {
        this.mouseDragged = arg;
        return this;
    }

    public AbstractWidgetBuilder setClicked(Function<ContextUtils.ClickedContextW, Object> arg) {
        this.clicked = arg;
        return this;
    }

    public AbstractWidgetBuilder setIsValidClickButton(Function<ContextUtils.ValidClickButtonContext, Object> arg) {
        this.isValidClickButton = arg;
        return this;
    }

    public AbstractWidgetBuilder mouseClicked(Function<ContextUtils.MouseClickedContextW, Object> arg) {
        this.mouseClicked = arg;
        return this;
    }

    public AbstractWidgetBuilder mouseReleased(Function<ContextUtils.MouseClickedContextW, Object> arg) {
        this.mouseReleased = arg;
        return this;
    }

    public AbstractWidgetBuilder setOnValidClickButton(Consumer<ContextUtils.ValidClickButtonContext> arg) {
        this.onValidClickButton = arg;
        return this;
    }

    public AbstractWidgetBuilder onSetFocused(Consumer<ContextUtils.SetFocusedContext> arg) {
        this.onSetFocused = arg;
        return this;
    }

    public AbstractWidgetBuilder setFocused(Consumer<ContextUtils.SetFocusedContext> arg) {
        this.setFocused = arg;
        return this;
    }

    public AbstractWidgetBuilder onMouseMoved(Consumer<ContextUtils.MouseMovedContext> arg) {
        this.onMouseMoved = arg;
        return this;
    }

    public AbstractWidgetBuilder setMouseMoved(Consumer<ContextUtils.MouseMovedContext> arg) {
        this.mouseMoved = arg;
        return this;
    }

    public AbstractWidgetBuilder onPlayDownSound(Consumer<ContextUtils.PlayDownSoundContext> arg) {
        this.onPlayDownSound = arg;
        return this;
    }

    public AbstractWidgetBuilder setPlayDownSound(Consumer<ContextUtils.PlayDownSoundContext> arg) {
        this.playDownSound = arg;
        return this;
    }

    public AbstractWidgetBuilder setOnMouseReleased(Consumer<ContextUtils.MouseClickedContextW> arg) {
        this.onMouseReleased = arg;
        return this;
    }

    public AbstractWidgetBuilder setOnDrag(Consumer<ContextUtils.DragContext> arg) {
        this.onDrag = arg;
        return this;
    }

    public AbstractWidgetBuilder onInit(Consumer<ContextUtils.WidgetInitContext> arg) {
        this.onInit = arg;
        return this;
    }

    public AbstractWidgetBuilder setOnRelease(Consumer<ContextUtils.OnClickContext> arg) {
        this.onRelease = arg;
        return this;
    }

    public AbstractWidgetBuilder onClick(Consumer<ContextUtils.OnClickContext> arg) {
        this.onClick = arg;
        return this;
    }

    public AbstractWidgetBuilder render(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.render = arg;
        return this;
    }

    public AbstractWidgetBuilder onRender(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.onRender = arg;
        return this;
    }

    public AbstractWidgetBuilder renderWidget(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.renderWidget = arg;
        return this;
    }

    public AbstractWidgetBuilder onRenderWidget(Consumer<ContextUtils.ScreenRenderContextW> arg) {
        this.onRenderWidget = arg;
        return this;
    }

    public AbstractWidgetBuilder updateWidgetNarration(Consumer<ContextUtils.NarrationStateContextW> arg) {
        this.updateWidgetNarration = arg;
        return this;
    }

    public AbstractWidgetBuilder onUpdateWidgetNarration(Consumer<ContextUtils.NarrationStateContextW> arg) {
        this.onUpdateWidgetNarration = arg;
        return this;
    }

    @HideFromJS
    public AbstractWidgetJS build(int x, int y, int width, int height, Component message) {
        return new AbstractWidgetJS(this, menuBuilder, x, y, width, height, message);
    }

    public AbstractContainerBuilder<?> getMenuBuilder() {
        return menuBuilder;
    }
}
