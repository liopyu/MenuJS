package net.liopyu.menujs.mixin;

import dev.latvian.mods.kubejs.util.UtilsJS;
import net.liopyu.menujs.menus.menujs.AbstractMenuContainerJS;
import net.liopyu.menujs.util.IPlayer;
import net.liopyu.menujs.util.MenuJSHelperClass;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.liopyu.menujs.builders.AbstractContainerBuilder.thisList;
import static net.liopyu.menujs.builders.AbstractMenuTypeJS.getMenuForBuilder;

@Mixin(value = Player.class, remap = true)
public class PlayerMixin implements IPlayer {
    @Unique
    private Player menuJS$Player = (Player) (Object) this;

    @Override
    public void menuJS$setScreen(ResourceLocation registryName) {
        var builder = thisList.get(registryName);
        if (builder != null) {
            if (menuJS$Player instanceof ServerPlayer serverPlayer) {
                NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider(
                        (id, inv, p) -> getMenuForBuilder(builder, id, inv),
                        Component.empty()
                ));
            } else {
                MenuJSHelperClass.logWarningMessageOnce("Cannot set screen on client level. A ServerPlayer is required.");
            }
        }
    }
}
