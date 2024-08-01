package net.dimidium.mymod.worldgen;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers
{
    public static final TreeGrower RUBBER_TREE = new TreeGrower("rubber", Optional.empty(), Optional.of(ConfiguredFeatures.RUBBER), Optional.empty());
}
