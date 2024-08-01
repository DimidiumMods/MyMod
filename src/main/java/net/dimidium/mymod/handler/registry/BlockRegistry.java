package net.dimidium.mymod.handler.registry;

import net.dimidium.mymod.MyMod;
import net.dimidium.mymod.block.Rubber;
import net.dimidium.mymod.block.RubberWood;
import net.dimidium.mymod.worldgen.ModTreeGrowers;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MyMod.MOD_ID);

    public static final DeferredBlock<Block> RUBBER = BLOCKS.register("rubber", Rubber::new);
    public static final DeferredBlock<LeavesBlock> RUBBER_LEAVES = BLOCKS.register("rubber_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<RotatedPillarBlock> RUBBER_LOG = BLOCKS.register("rubber_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> RUBBER_PLANKS = BLOCKS.register("rubber_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<SaplingBlock> RUBBER_SAPLING = BLOCKS.register("rubber_sapling", () -> new SaplingBlock(ModTreeGrowers.RUBBER_TREE, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
            .strength(0.2F)
            .randomTicks()
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> RUBBER_WOOD = BLOCKS.register("rubber_wood", () -> new RubberWood(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_LOG = BLOCKS.register("stripped_rubber_log", () -> new RubberWood(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_WOOD = BLOCKS.register("stripped_rubber_wood", () -> new RubberWood(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));


    public static void registerBlocks(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
