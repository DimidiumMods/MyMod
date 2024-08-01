package net.dimidium.mymod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Rubber extends Block implements BonemealableBlock
{
    private static final VoxelShape SHAPE = Shapes.box(0.2, 0.2, 0.2, 0.2, 0.2D, 0.2);
    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, BlockStateProperties.MAX_AGE_7);

    public Rubber()
    {
        super(BlockBehaviour.Properties.of().strength(5f, 5f).sound(SoundType.WOOD));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }

    public boolean isRandomlyTicking(BlockState pState)
    {
        return super.isRandomlyTicking(pState) || pState.getValue(AGE) != 7;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if (!pState.canSurvive(pLevel, pPos))
        {
            pLevel.destroyBlock(pPos, true);
        }

        super.tick(pState, pLevel, pPos, pRandom);

        int i = pState.getValue(AGE);

        if (i < 7 && pRandom.nextInt(5) == 0 && pLevel.getRawBrightness(pPos.above(), 0) >= 9)
        {
            pLevel.setBlock(pPos, pState.setValue(AGE, i + 1), 2);
        }

    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader pLevel, BlockPos pPos)
    {
        BlockState blockstate = pLevel.getBlockState(pPos.above());
        return blockstate.getBlock() instanceof LeavesBlock;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return (Integer)pState.getValue(AGE) < 7;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState)
    {

        int age = pState.getValue(AGE) + Mth.nextInt(pLevel.random, 2, 5);
        int maxAge = 7;

        if (age > maxAge)
        {
            age = maxAge;
        }

        pLevel.setBlock(pPos, this.defaultBlockState().setValue(AGE, age), 2);
    }
}
