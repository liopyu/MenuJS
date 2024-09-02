package net.liopyu.menujs.builders;

import net.liopyu.menujs.menus.AbstractMenuContainerJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;


public class AbstractMenuContainerBuilderJS extends AbstractMenuContainerBuilder<AbstractMenuContainerJS> {
    public AbstractMenuContainerBuilderJS(ResourceLocation i) {
        super(i);
    }
    @Override
    public MenuType<AbstractMenuContainerJS> createObject() {
        return new BasicMenuType(this).get();
    }
}