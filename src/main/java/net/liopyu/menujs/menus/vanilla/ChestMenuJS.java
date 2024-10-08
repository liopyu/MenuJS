package net.liopyu.menujs.menus.vanilla;

import net.liopyu.menujs.builders.menu.menujs.AbstractContainerBuilderJS;
import net.liopyu.menujs.builders.menu.vanilla.ChestMenuBuilderJS;
import net.liopyu.menujs.util.ContextUtils;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static net.liopyu.menujs.util.MenuJSHelperClass.consumerCallback;
import static net.liopyu.menujs.util.MenuJSHelperClass.convertObjectToDesired;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ChestMenuJS extends ChestMenu {
    private final ChestMenuBuilderJS builder;
    private final Inventory playerInventory;

    public ChestMenuJS(ChestMenuBuilderJS builder, MenuType<?> pMenuType, int pContainerId, Inventory playerInventory, Container pContainer, int pRows) {
        super(pMenuType, pContainerId, playerInventory, pContainer, pRows);
        this.builder = builder;
        this.playerInventory = playerInventory;
        if (builder.onMenuInit != null) {
            var context = new ContextUtils.MenuBuilderContext<>(this, builder, pMenuType, pContainerId, playerInventory);
            consumerCallback(builder.onMenuInit, context, "Error in " + menuName() + "builder for field: onMenuInit.");
        }
        for (Slot slot : builder.slotList) {
            this.addSlot(slot);
        }
        for (DataSlot slot : builder.dataSlotList) {
            this.addDataSlot(slot);
        }
        for (ContainerData slot : builder.containerSlotList) {
            this.addDataSlots(slot);
        }
    }

    public ChestMenuBuilderJS getBuilder() {
        return builder;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public String menuName() {
        return this.builder.id.toString();
    }

    public Slot addSlot(int pSlot, int pX, int pY) {
        if (builder.container == null) {
            builder.setContainer();
        }
        var slot = new Slot(builder.container, pSlot, pX, pY);
        builder.slotList.add(slot);
        return slot;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        if (builder.setQuickMoveStack == null) return ItemStack.EMPTY;
        try {
            var context = new ContextUtils.PlayerIndexContext(player, i, this);
            var obj = convertObjectToDesired(builder.setQuickMoveStack.apply(context), "itemstack");
            if (obj != null) {
                return (ItemStack) obj;
            }
            MenuJSHelperClass.logErrorMessageOnce("Invalid return value for setQuickMoveStack from menu: " + menuName() + ". Value: " + obj + ". Must be an ItemStack. Defaulting to super method.");
        } catch (Exception e) {
            MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field setQuickMoveStack: " + menuName() + ".", e);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        if (builder.setStillValid == null) return false;
        try {
            var context = new ContextUtils.PlayerMenuContext(player, this);
            var obj = convertObjectToDesired(builder.setStillValid.apply(context), "boolean");
            if (obj != null) {
                return (boolean) obj;
            }
            MenuJSHelperClass.logErrorMessageOnce("Invalid return value for setStillValid from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method.");
        } catch (Exception e) {
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
                var context = new ContextUtils.PlayerIndexContext(pPlayer, pId, this);
                var obj = convertObjectToDesired(builder.clickMenuButton.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for clickMenuButton from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.clickMenuButton(pPlayer, pId));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field clickMenuButton: " + menuName() + ".", e);
            }
        }
        return super.clickMenuButton(pPlayer, pId);
    }


    @Override
    public void clicked(int pSlotId, int pButton, ClickType pClickType, Player pPlayer) {
        if (builder.onClicked != null) {
            final ContextUtils.SlotClickContext context = new ContextUtils.SlotClickContext(this, pSlotId, pButton, pClickType, pPlayer);
            consumerCallback(builder.onClicked, context, "Error in " + menuName() + "builder for field: onClicked.");
        } else super.clicked(pSlotId, pButton, pClickType, pPlayer);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        if (builder.setCanTakeItemForPickAll != null) {
            try {
                var context = new ContextUtils.ItemSlotContext(this, pStack, pSlot);
                var obj = convertObjectToDesired(builder.setCanTakeItemForPickAll.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for setCanTakeItemForPickAll from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.canTakeItemForPickAll(pStack, pSlot));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field setCanTakeItemForPickAll: " + menuName() + ".", e);
            }
        }
        return super.canTakeItemForPickAll(pStack, pSlot);
    }

    @Override
    public void removed(Player pPlayer) {
        if (builder.onItemRemoved != null) {
            var context = new ContextUtils.PlayerMenuContext(pPlayer, this);
            consumerCallback(builder.onItemRemoved, context, "Error in " + menuName() + "builder for field: onItemRemoved.");
        }
        if (builder.setItemRemoved != null) {
            var context = new ContextUtils.PlayerMenuContext(pPlayer, this);
            consumerCallback(builder.setItemRemoved, context, "Error in " + menuName() + "builder for field: setItemRemoved.");
        } else super.removed(pPlayer);
    }


    @Override
    public void slotsChanged(Container pContainer) {
        if (builder.onMenuSlotChanged != null) {
            var context = new ContextUtils.ContainerMenuContext(this, pContainer);
            consumerCallback(builder.onMenuSlotChanged, context, "Error in " + menuName() + "builder for field: onMenuSlotChanged.");
        }
        if (builder.setMenuSlotChanged != null) {
            var context = new ContextUtils.ContainerMenuContext(this, pContainer);
            consumerCallback(builder.setMenuSlotChanged, context, "Error in " + menuName() + "builder for field: setMenuSlotChanged.");
        } else super.slotsChanged(pContainer);
    }


    @Override
    public void initializeContents(int pStateId, List<ItemStack> pItems, ItemStack pCarried) {
        super.initializeContents(pStateId, pItems, pCarried);
        if (builder.onInitializeContents != null) {
            var context = new ContextUtils.ContainerUpdateContext(this, pStateId, pItems, pCarried);
            consumerCallback(builder.onInitializeContents, context, "Error in " + menuName() + "builder for field: onInitializeContents.");
        }
    }

    @Override
    public void setData(int pId, int pData) {
        super.setData(pId, pData);
        if (builder.onSetData != null) {
            var context = new ContextUtils.IndexDataContext(this, pId, pData);
            consumerCallback(builder.onSetData, context, "Error in " + menuName() + "builder for field: onSetData.");
        }
    }

    @Override
    public boolean moveItemStackTo(ItemStack pStack, int pStartIndex, int pEndIndex, boolean pReverseDirection) {
        if (builder.moveItemStackTo != null) {
            try {
                var context = new ContextUtils.TransferStackContext(this, pStack, pStartIndex, pEndIndex, pReverseDirection);
                var obj = convertObjectToDesired(builder.moveItemStackTo.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for moveItemStackTo from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.moveItemStackTo(pStack, pStartIndex, pEndIndex, pReverseDirection));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field moveItemStackTo: " + menuName() + ".", e);
            }
        }
        return super.moveItemStackTo(pStack, pStartIndex, pEndIndex, pReverseDirection);
    }


    @Override
    public boolean canDragTo(Slot pSlot) {
        if (builder.canDragTo != null) {
            try {
                var context = new ContextUtils.MenuSlotContext(this, pSlot);
                var obj = convertObjectToDesired(builder.canDragTo.apply(context), "boolean");
                if (obj != null) {
                    return (boolean) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for canDragTo from menu: " + menuName() + ". Value: " + obj + ". Must be a boolean. Defaulting to super method: " + super.canDragTo(pSlot));
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field canDragTo: " + menuName() + ".", e);
            }
        }
        return super.canDragTo(pSlot);

    }

    @Override
    public void setCarried(ItemStack pStack) {
        if (builder.setCarried != null) {
            var context = new ContextUtils.MenuItemStackContext(this, pStack);
            consumerCallback(builder.setCarried, context, "Error in " + menuName() + "builder for field: setCarried.");
        } else super.setCarried(pStack);
    }

    @Override
    public ItemStack getCarried() {
        if (builder.getCarried != null) {
            try {
                var obj = convertObjectToDesired(builder.getCarried.apply(this), "itemstack");
                if (obj != null) {
                    return (ItemStack) obj;
                }
                MenuJSHelperClass.logErrorMessageOnce("Invalid return value for getCarried from menu: " + menuName() + ". Value: " + obj + ". Must be an ItemStack. Defaulting to super method: " + super.getCarried());
            } catch (Exception e) {
                MenuJSHelperClass.logErrorMessageOnceCatchable("Error in menu builder for field getCarried: " + menuName() + ".", e);
            }
        }
        return super.getCarried();
    }
}