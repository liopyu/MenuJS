package net.liopyu.menujs.builders;

import net.liopyu.menujs.menus.AbstractMenuContainerJS;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class BasicMenuType<B extends AbstractContainerMenu> {
    private final AbstractMenuContainerBuilder<?> builder;
    public <T extends AbstractMenuContainerBuilder<B>> BasicMenuType(T builder) {
    this.builder = builder;
    }

    public MenuType<AbstractMenuContainerJS> get() {
        return new MenuType<>((pContainerId, pPlayerInventory) ->
                new AbstractMenuContainerJS((AbstractMenuContainerBuilderJS) builder, builder.get(), pContainerId,pPlayerInventory),
                FeatureFlags.VANILLA_SET);

    }

}