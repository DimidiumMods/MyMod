package net.dimidium.mymod.handler.registry;

import net.dimidium.mymod.MyMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry
{
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyMod.MOD_ID);

    public static final DeferredItem<BlockItem> RUBBER = ITEMS.register("rubber", () -> new BlockItem(BlockRegistry.RUBBER.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUBBER_LOG = ITEMS.register("rubber_log", () -> new BlockItem(BlockRegistry.RUBBER_LOG.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUBBER_WOOD = ITEMS.register("rubber_wood", () -> new BlockItem(BlockRegistry.RUBBER_WOOD.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRIPPED_RUBBER_LOG = ITEMS.register("stripped_rubber_log", () -> new BlockItem(BlockRegistry.STRIPPED_RUBBER_LOG.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRIPPED_RUBBER_WOOD = ITEMS.register("stripped_rubber_wood", () -> new BlockItem(BlockRegistry.STRIPPED_RUBBER_WOOD.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUBBER_PLANKS = ITEMS.register("rubber_planks", () -> new BlockItem(BlockRegistry.RUBBER_PLANKS.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUBBER_LEAVES = ITEMS.register("rubber_leaves", () -> new BlockItem(BlockRegistry.RUBBER_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUBBER_SAPLING = ITEMS.register("rubber_sapling", () -> new BlockItem(BlockRegistry.RUBBER_SAPLING.get(), new Item.Properties()));

    public static void registerItems(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
