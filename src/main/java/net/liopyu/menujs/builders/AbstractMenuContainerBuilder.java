package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractMenuContainerBuilder<T extends AbstractContainerMenu> extends BuilderBase<MenuType<T>> {
    public static List<AbstractMenuContainerBuilder<?>> thisList = new ArrayList<>();
    public transient Function<ContextUtils.QuickStackContext, Object> setQuickMoveStack;
    public transient Function<ContextUtils.StillValidContext, Object> setStillValid;
    public transient Function<ContextUtils.IndexContext, Object> isValidSlotIndex;

    public AbstractMenuContainerBuilder(ResourceLocation i) {
        super(i);
        thisList.add(this);
    }
    public AbstractMenuContainerBuilder<T> isValidSlotIndex(Function<ContextUtils.IndexContext, Object> function) {
        this.isValidSlotIndex = function;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setStillValid(Function<ContextUtils.StillValidContext, Object> function) {
        this.setStillValid = function;
        return this;
    }
    public AbstractMenuContainerBuilder<T> setQuickMoveStack(Function<ContextUtils.QuickStackContext, Object> stack) {
            this.setQuickMoveStack = stack;
            return this;
    }
    @Override
    public RegistryInfo<MenuType> getRegistryType() {
        return RegistryInfo.MENU;
    }

}
