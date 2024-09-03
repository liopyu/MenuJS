package net.liopyu.menujs.builders;

import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractMenuContainerBuilder<T extends AbstractContainerMenu> extends BuilderBase<MenuType<T>> {
    public static List<AbstractMenuContainerBuilder<?>> thisList = new ArrayList<>();
    public transient Function<ContextUtils.QuickStackContext, Object> setQuickMoveStack;
    public transient Function<ContextUtils.StillValidContext, Object> setStillValid;
    public transient Function<ContextUtils.IndexContext, Object> isValidSlotIndex;
    public final List<Slot> slotList = new ArrayList<>();
    public final List<ContainerData> containerSlotList = new ArrayList<>();
    public final List<DataSlot> dataSlotList = new ArrayList<>();
    public transient Consumer<ContextUtils.MenuBuilderContext<T>> onMenuInit;
    public transient Consumer<ContextUtils.ScreenBuilderContext<T>> onScreenInit;
    public AbstractMenuContainerBuilder(ResourceLocation i) {
        super(i);
        thisList.add(this);
    }

    public AbstractMenuContainerBuilder<T> onScreenInit(Consumer<ContextUtils.ScreenBuilderContext<T>> init) {
        this.onScreenInit = init;
        return this;
    }
    public AbstractMenuContainerBuilder<T> onMenuInit(Consumer<ContextUtils.MenuBuilderContext<T>> init) {
        this.onMenuInit = init;
        return this;
    }
    public AbstractMenuContainerBuilder<T> addDataSlot(DataSlot slot) {
        this.dataSlotList.add(slot);
        return this;
    }
    public AbstractMenuContainerBuilder<T> addContainerData(ContainerData slot) {
        this.containerSlotList.add(slot);
        return this;
    }
    public AbstractMenuContainerBuilder<T> addSlot(Slot slot) {
        this.slotList.add(slot);
        return this;
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
