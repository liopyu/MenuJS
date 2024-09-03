package net.liopyu.menujs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.liopyu.menujs.builders.container.AbstractMenuContainerBuilderJS;

public class MenuJSPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        RegistryInfo.MENU.addType("menujs:basic", AbstractMenuContainerBuilderJS.class, AbstractMenuContainerBuilderJS::new);
    }
}
