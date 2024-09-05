package net.liopyu.menujs.builders.container;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.AbstractMenuTypeJS;
import net.liopyu.menujs.menus.AbstractMenuContainerJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

import java.util.ArrayList;
import java.util.List;


public class AbstractMenuContainerBuilderJS extends AbstractMenuContainerBuilder<AbstractMenuContainerJS> {


    public AbstractMenuContainerBuilderJS(ResourceLocation i) {
        super(i);
    }

    @Override
    public MenuType<AbstractMenuContainerJS> createObject() {
        return new AbstractMenuTypeJS<>(this).get();
    }
}