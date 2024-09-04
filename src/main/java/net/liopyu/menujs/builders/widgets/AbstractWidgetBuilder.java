package net.liopyu.menujs.builders.widgets;

import net.liopyu.menujs.client.widgets.AbstractWidgetJS;
import net.minecraft.network.chat.Component;

public class AbstractWidgetBuilder {

    public AbstractWidgetBuilder(){

    }
    public AbstractWidgetJS build(int x, int y, int width, int height, Component message) {
        return new AbstractWidgetJS(this,x, y, width, height, message);
    }
}
