package net.liopyu.menujs.menus;

import net.liopyu.menujs.builders.AbstractMenuContainerBuilderJS;
import net.liopyu.menujs.util.ContextUtils;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.OptionalInt;

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;
import static net.liopyu.menujs.util.MenuJSHelperClass.convertObjectToDesired;
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AbstractMenuContainerJS extends AbstractContainerMenu {
    private final AbstractMenuContainerBuilderJS builder;
    private final Inventory playerInventory;
    public AbstractMenuContainerJS(AbstractMenuContainerBuilderJS builder, @Nullable MenuType<?> pMenuType, int pContainerId, Inventory playerInventory) {
        super(pMenuType, pContainerId);
        this.builder = builder;
        this.playerInventory = playerInventory;
        var context = new ContextUtils.MenuBuilderContext<>(builder,pMenuType,pContainerId,playerInventory);
        builder.onMenuInit.accept(context);
        for (Slot slot : builder.slotList){
            this.addSlot(slot);
        }
        for (DataSlot slot : builder.dataSlotList){
            this.addDataSlot(slot);
        }
        for (ContainerData slot : builder.containerSlotList){
            this.addDataSlots(slot);
        }

    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public String menuName(){
          return this.builder.id.toString();
    }
    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        if (builder.setQuickMoveStack == null) return ItemStack.EMPTY;
        try {
            var context = new ContextUtils.PlayerIndexContext(player,i,this);
            var obj = convertObjectToDesired(builder.setQuickMoveStack.apply(context), "itemstack");
            if (obj != null) {
                return (ItemStack) obj;
            }
            MenuJSHelperClass.logErrorMessageOnce("Invalid return value for setQuickMoveStack from menu: " + menuName() + ". Value: " + obj + ". Must be an ItemStack. Defaulting to super method.");
        }catch (Exception e) {
            MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field setQuickMoveStack: " + menuName() + ".", e);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        if (builder.setStillValid == null) return false;
        try {
            var context = new ContextUtils.StillValidContext(player,this);
            var obj = convertObjectToDesired(builder.setStillValid.apply(context), "boolean");
            if (obj != null) {
                return (boolean) obj;
            }
            MenuJSHelperClass.logErrorMessageOnce("Invalid return value for setStillValid from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method.");
        }catch (Exception e) {
            MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field setStillValid: " + menuName() + ".", e);
        }
        return false;
    }

    @Override
    public boolean isValidSlotIndex(int pSlotIndex) {
        if (builder.isValidSlotIndex != null) {
            try {
                var context = new ContextUtils.IndexContext(pSlotIndex, this);
                var obj = convertObjectToDesired(builder.isValidSlotIndex.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for isValidSlotIndex from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.isValidSlotIndex(pSlotIndex));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field isValidSlotIndex: " + menuName() + ".", e);
            }
        }
        return super.isValidSlotIndex(pSlotIndex);
    }
   

    @Override
    public boolean clickMenuButton(Player pPlayer, int pId) {
        if (builder.clickMenuButton != null) {
            try {
                var context = new ContextUtils.PlayerIndexContext(pPlayer,pId,this);
                var obj = convertObjectToDesired(builder.clickMenuButton.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for clickMenuButton from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.clickMenuButton(pPlayer,pId));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field clickMenuButton: " + menuName() + ".", e);
            }
        }
        return super.clickMenuButton(pPlayer,pId);
    }


    @Override
    public void clicked(int pSlotId, int pButton, ClickType pClickType, Player pPlayer) {
        if (builder.onClicked != null) {
            final ContextUtils.SlotClickContext context = new ContextUtils.SlotClickContext(this,pSlotId,pButton,pClickType,pPlayer);
            consumerCallback(builder.onClicked, context, "[EntityJS]: Error in " + menuName() + "builder for field: onClicked.");
        }else super.clicked(pSlotId, pButton, pClickType, pPlayer);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return super.canTakeItemForPickAll(pStack, pSlot);
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
    }

    @Override
    protected void clearContainer(Player pPlayer, Container pContainer) {
        super.clearContainer(pPlayer, pContainer);
    }

    @Override
    public void slotsChanged(Container pContainer) {
        super.slotsChanged(pContainer);
    }

    @Override
    public void setItem(int pSlotId, int pStateId, ItemStack pStack) {
        super.setItem(pSlotId, pStateId, pStack);
    }

    @Override
    public void initializeContents(int pStateId, List<ItemStack> pItems, ItemStack pCarried) {
        super.initializeContents(pStateId, pItems, pCarried);
    }

    @Override
    public void setData(int pId, int pData) {
        super.setData(pId, pData);
    }

    @Override
    protected boolean moveItemStackTo(ItemStack pStack, int pStartIndex, int pEndIndex, boolean pReverseDirection) {
        return super.moveItemStackTo(pStack, pStartIndex, pEndIndex, pReverseDirection);
    }

    @Override
    protected void resetQuickCraft() {
        super.resetQuickCraft();
    }

    @Override
    public boolean canDragTo(Slot pSlot) {
        return super.canDragTo(pSlot);
    }

    @Override
    public void setCarried(ItemStack pStack) {
        super.setCarried(pStack);
    }

    @Override
    public ItemStack getCarried() {
        return super.getCarried();
    }

    @Override
    public void suppressRemoteUpdates() {
        super.suppressRemoteUpdates();
    }

    @Override
    public void resumeRemoteUpdates() {
        super.resumeRemoteUpdates();
    }

    @Override
    public void transferState(AbstractContainerMenu pMenu) {
        super.transferState(pMenu);
    }

    @Override
    public OptionalInt findSlot(Container pContainer, int pSlotIndex) {
        return super.findSlot(pContainer, pSlotIndex);
    }

    @Override
    public int getStateId() {
        return super.getStateId();
    }

    @Override
    public int incrementStateId() {
        return super.incrementStateId();
    }
}
