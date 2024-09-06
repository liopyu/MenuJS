package net.liopyu.menujs.mixin;

import dev.latvian.mods.kubejs.command.KubeJSCommands;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.menus.AbstractMenuContainerJS;
import net.liopyu.menujs.util.IServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;

import static net.liopyu.menujs.builders.AbstractMenuContainerBuilder.thisList;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin implements IServerPlayer {
    @Override
    public void menuJS$setScreen(ServerPlayer player, String registryName) {
        var builder = thisList.get(new ResourceLocation(registryName));
        if (builder != null) {
            NetworkHooks.openScreen(player, new SimpleMenuProvider(
                    (id, inv, p) -> new AbstractMenuContainerJS(UtilsJS.cast(builder), builder.get(), id, inv),
                    Component.empty()
            ));
        }
    }
}
