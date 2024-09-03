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

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;

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
        this.menu.addSlotListener(this);
    }

    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }
    public String menuName(){
        return this.builder.id.toString();
    }
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        if (builder.renderBg != null) {
            final ContextUtils.ScreenRenderContext<T> context = new ContextUtils.ScreenRenderContext<>(this,guiGraphics,v,i,i1);
            consumerCallback(builder.renderBg, context, "[EntityJS]: Error in " + menuName() + "builder for field: renderBg.");
        }
    }

    @Override
    public void slotChanged(AbstractContainerMenu abstractContainerMenu, int i, ItemStack itemStack) {
        if (builder.onScreenSlotChanged != null) {
            final ContextUtils.MenuItemContext<T> context = new ContextUtils.MenuItemContext<>(this,getMenu(),i,itemStack);
            consumerCallback(builder.onScreenSlotChanged, context, "[EntityJS]: Error in " + menuName() + "builder for field: onScreenSlotChanged.");
        }
    }

    @Override
    public void dataChanged(AbstractContainerMenu abstractContainerMenu, int i, int i1) {
        if (builder.onDataChanged != null) {
            final ContextUtils.DataChangedContext<T> context = new ContextUtils.DataChangedContext<>(getMenu(),this,i,i1);
            consumerCallback(builder.onDataChanged, context, "[EntityJS]: Error in " + menuName() + "builder for field: onDataChanged.");
        }
    }
}
