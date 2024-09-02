package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenuContainerBuilder<T extends AbstractContainerMenu> extends BuilderBase<MenuType<T>> {
    public static List<AbstractMenuContainerBuilder<?>> thisList = new ArrayList<>();
    public AbstractMenuContainerBuilder(ResourceLocation i) {
        super(i);
        thisList.add(this);
    }

    @Override
    public RegistryInfo<MenuType> getRegistryType() {
        return RegistryInfo.MENU;
    }

}
