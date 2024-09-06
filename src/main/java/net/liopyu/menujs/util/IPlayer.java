package net.liopyu.menujs.util;

import dev.latvian.mods.rhino.util.RemapPrefixForJS;
import net.minecraft.resources.ResourceLocation;

@RemapPrefixForJS("menuJS$")
public interface IPlayer {
    void menuJS$setScreen(ResourceLocation registryName);
}
