package net.dimidium.mymod.data;

import net.dimidium.mymod.MyMod;
import net.dimidium.mymod.handler.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStatesGenerator extends BlockStateProvider
{
    public BlockStatesGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, MyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        axisBlock(BlockRegistry.RUBBER_LOG.get());
        simpleBlock(BlockRegistry.RUBBER_LEAVES.get());
    }
}
