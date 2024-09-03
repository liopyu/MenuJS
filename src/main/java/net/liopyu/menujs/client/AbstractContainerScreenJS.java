package net.liopyu.menujs.client;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.util.ContextUtils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@OnlyIn(Dist.CLIENT)
public class AbstractContainerScreenJS<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> implements ContainerListener {
    private final AbstractMenuContainerBuilder<T> builder;
    private final Inventory playerInventory;

    public AbstractContainerScreenJS(AbstractMenuContainerBuilder<T> builder, T pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.builder = builder;
        this.playerInventory = pPlayerInventory;
    }



    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    protected void init() {
        super.init();
        var context = new ContextUtils.ScreenBuilderContext<>(this,builder,getMenu(),getPlayerInventory(),getTitle());
        builder.onScreenInit.accept(context);
    }

    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

    }

    @Override
    public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {

    }

    @Override
    public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int i1) {

    }
}
