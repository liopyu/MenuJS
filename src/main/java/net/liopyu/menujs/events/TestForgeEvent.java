package net.liopyu.menujs.events;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.MenuJS;
import net.liopyu.menujs.builders.AbstractMenuContainerBuilder;
import net.liopyu.menujs.builders.container.AbstractMenuContainerBuilderJS;
import net.liopyu.menujs.menus.AbstractMenuContainerJS;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

import static net.liopyu.menujs.builders.container.AbstractMenuContainerBuilderJS.thisList;


@Mod.EventBusSubscriber(modid = MenuJS.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestForgeEvent {

    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.RightClickItem event) {
        if (!event.getLevel().isClientSide() && event.getItemStack().getItem() == Items.DIAMOND) {
            Player player = event.getEntity();
            if (player instanceof ServerPlayer serverPlayer) {
                openCustomMenu(serverPlayer);
            }
        }
    }

    public static void openCustomMenu(ServerPlayer player) {
        for (AbstractMenuContainerBuilder<?> builder : thisList) {
            NetworkHooks.openScreen(player, new SimpleMenuProvider(
                    (id, inv, p) -> new AbstractMenuContainerJS(UtilsJS.cast(builder), builder.get(), id, inv),
                    Component.literal("test")
            ));
        }

    }
}
