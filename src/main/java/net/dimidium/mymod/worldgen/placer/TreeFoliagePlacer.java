package net.dimidium.mymod.worldgen.placer;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;

public abstract class TreeFoliagePlacer extends BlobFoliagePlacer
{
    private static BlockState treeContents;

    public TreeFoliagePlacer(BlockState treeContents)
    {
        super(ConstantInt.of(2), ConstantInt.of(0), 4);
        TreeFoliagePlacer.treeContents  = treeContents;
    }
}
