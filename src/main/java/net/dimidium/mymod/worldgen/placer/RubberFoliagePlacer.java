package net.dimidium.mymod.worldgen.placer;

import com.mojang.serialization.MapCodec;
import net.dimidium.mymod.handler.registry.BlockRegistry;
import net.dimidium.mymod.handler.registry.FoliagePlacerTypesRegistry;
import net.dimidium.mymod.worldgen.ConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class RubberFoliagePlacer extends TreeFoliagePlacer
{
    private static BlockState treeContents;

    public static final MapCodec<RubberFoliagePlacer> CODEC = MapCodec.unit(() -> {
        return new RubberFoliagePlacer(BlockRegistry.RUBBER.get().defaultBlockState());
    });

    public RubberFoliagePlacer(BlockState treeContents)
    {
        super(treeContents);
    }

    public static BlockState getTreeContents()
    {
        return treeContents;
    }

    public static void setTreeContents(BlockState treeContents)
    {
        RubberFoliagePlacer.treeContents = treeContents;
    }

    protected FoliagePlacerType<?> type()
    {
        return FoliagePlacerTypesRegistry.RUBBER_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset)
    {
        for(int i = offset; i >= offset - foliageHeight; --i)
        {
            int j = Math.max(foliageRadius + attachment.radiusOffset() - 1 - i / 2, 0);
            this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), j - 1, i + 1, attachment.doubleTrunk());
            this.placeContentRow(level, blockSetter, random, config, attachment.pos(), 1, offset - 4, attachment.doubleTrunk());
        }

    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig)
    {
        return 4;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge)
    {
        return false;
    }


    protected void placeContentRow(LevelSimulatedReader pLevel, FoliageSetter foliageSetter, RandomSource randomsource, TreeConfiguration treeconfig, BlockPos pos, int p_161443_, int p_161444_, boolean p_161445_)
    {
        int i = p_161445_ ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int j = -p_161443_; j <= p_161443_ + i; ++j)
        {
            for(int k = -p_161443_; k <= p_161443_ + i; ++k)
            {
                if (!this.shouldSkipLocationSigned(randomsource, j, p_161444_, k, p_161443_, p_161445_))
                {
                    blockpos$mutableblockpos.setWithOffset(pos, j, p_161444_, k);
                    this.tryPlaceFruit(pLevel, foliageSetter, randomsource, treeconfig, blockpos$mutableblockpos.setWithOffset(pos, j, p_161444_, k));
                }
            }
        }

    }

    protected static boolean tryPlaceLeaf(LevelSimulatedReader pLevel, FoliageSetter foliageSetter, RandomSource randomsource, TreeConfiguration treeconfig, BlockPos pos)
    {
        if (validTreePos(pLevel, pos))
        {
            foliageSetter.set(pos, treeconfig.foliageProvider.getState(randomsource, pos));
            return true;
        }

        else
        {
            return false;
        }
    }

    protected void tryPlaceFruit(LevelSimulatedReader pLevel, FoliageSetter foliageSetter, RandomSource randomsource, TreeConfiguration treeconfig, BlockPos pos)
    {
        if (validTreePos(pLevel, pos))
        {
            foliageSetter.set(pos, BlockRegistry.RUBBER.get().defaultBlockState());
        }

    }


    public static boolean validTreePos(LevelSimulatedReader pLevel, BlockPos pos)
    {
        return pLevel.isStateAtPosition(pos, (state) -> { return !state.hasProperty(LeavesBlock.PERSISTENT) || !(Boolean)state.getValue(LeavesBlock.PERSISTENT);}) && TreeFeature.validTreePos(pLevel, pos);
    }
}
