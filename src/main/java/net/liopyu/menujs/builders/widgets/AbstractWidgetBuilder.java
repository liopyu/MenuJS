package net.liopyu.menujs.builders.widgets;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.client.widgets.AbstractWidgetJS;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class AbstractWidgetBuilder {
    private final AbstractMenuContainerBuilder<?> menuBuilder;

    public AbstractWidgetBuilder(AbstractMenuContainerBuilder<?> builder){
        this.menuBuilder = builder;
    }
    public transient Consumer<ContextUtils.ValidClickButtonContext> onValidClickButton;

    public AbstractWidgetBuilder setOnValidClickButton(Consumer<ContextUtils.ValidClickButtonContext> arg) {
        this.onValidClickButton = arg;
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
