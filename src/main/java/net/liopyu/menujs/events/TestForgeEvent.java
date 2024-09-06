package net.liopyu.menujs.events;

import net.liopyu.menujs.MenuJS;
import net.liopyu.menujs.util.IPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = MenuJS.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestForgeEvent {

    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.RightClickItem event) {
        if (!event.getLevel().isClientSide() && event.getItemStack().getItem() == Items.DIAMOND) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                if (serverPlayer instanceof IPlayer iServerPlayer) {
                    iServerPlayer.menuJS$setScreen(new ResourceLocation("kubejs:test"));
                }
            }
        }
    }

}
