package net.liopyu.menujs.builders.menu.vanilla;

import net.liopyu.menujs.builders.AbstractContainerBuilder;
import net.liopyu.menujs.builders.AbstractMenuTypeJS;
import net.liopyu.menujs.menus.menujs.AbstractMenuContainerJS;
import net.liopyu.menujs.menus.vanilla.ChestMenuJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class ChestMenuBuilderJS extends AbstractContainerBuilder<ChestMenuJS> {
    public transient int rows;

    public ChestMenuBuilderJS(ResourceLocation i) {
        super(i);
        rows = 3;
    }

    public ChestMenuBuilderJS setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public MenuType<ChestMenuJS> createObject() {
        return new AbstractMenuTypeJS<>(this).get();
    }
}