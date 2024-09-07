package net.liopyu.menujs.client.screens;

import net.liopyu.menujs.builders.AbstractContainerBuilder;
import net.liopyu.menujs.builders.menu.vanilla.ChestMenuBuilderJS;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;

public interface IScreenContainerJS {
    AbstractContainerScreen<?> getScreen();

    AbstractContainerBuilder<?> getBuilder();

    Inventory getPlayerInventory();

    default void setImageWidth(int imageWidth) {
        getScreen().imageWidth = imageWidth;
    }

    default void setImageHeight(int imageHeight) {
        getScreen().imageHeight = imageHeight;
    }

    default int getImageWidth() {
        return getScreen().imageWidth;
    }

    default int getImageHeight() {
        return getScreen().imageHeight;
    }

    default void setTopPos(int topPos) {
        getScreen().topPos = topPos;
    }

    default int getTopPos() {
        return getScreen().topPos;
    }

    default void setLeftPos(int leftPos) {
        getScreen().leftPos = leftPos;
    }

    default int getLeftPos() {
        return getScreen().leftPos;
    }


    default void setInventoryLabelY(int i) {
        getScreen().inventoryLabelY = i;
    }

    default void setInventoryLabelX(int i) {
        getScreen().inventoryLabelX = i;
    }

    default int getInventoryLabelY() {
        return getScreen().inventoryLabelY;
    }

    default int getInventoryLabelX() {
        return getScreen().inventoryLabelX;
    }

    default void setTitleLabelY(int i) {
        getScreen().titleLabelY = i;
    }

    default void setTitleLabelX(int i) {
        getScreen().titleLabelX = i;
    }

    default int getTitleLabelY() {
        return getScreen().titleLabelY;
    }

    default int getTitleLabelX() {
        return getScreen().titleLabelX;
    }
}
