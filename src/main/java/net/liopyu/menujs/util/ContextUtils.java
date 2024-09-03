package net.liopyu.menujs.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class ContextUtils {
    public static class QuickStackContext{
        public final Player player;
        public final int index;
        public final AbstractContainerMenu menu;

        public QuickStackContext(Player player, int index, AbstractContainerMenu menu) {
            this.player = player;
            this.index = index;
            this.menu = menu;
        }
    }
    public static class StillValidContext{
        public final Player player;
        public final AbstractContainerMenu menu;

        public StillValidContext(Player player, AbstractContainerMenu menu) {
            this.player = player;
            this.menu = menu;
        }
    }
    public static class IndexContext{
        public final AbstractContainerMenu menu;
        public final int index;

        public IndexContext(int index,AbstractContainerMenu menu) {
            this.menu = menu;
            this.index = index;
        }
    }
}
