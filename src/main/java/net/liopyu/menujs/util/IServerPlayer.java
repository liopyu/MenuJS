package net.liopyu.menujs.util;

import dev.latvian.mods.rhino.util.RemapPrefixForJS;
import net.minecraft.server.level.ServerPlayer;

@RemapPrefixForJS("menuJS$")
public interface IServerPlayer {
    void menuJS$setScreen(ServerPlayer player, String registryName);
}
