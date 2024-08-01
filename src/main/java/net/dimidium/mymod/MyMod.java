package net.dimidium.mymod;

import com.mojang.logging.LogUtils;
import net.dimidium.mymod.handler.registry.BlockRegistry;
import net.dimidium.mymod.handler.registry.FoliagePlacerTypesRegistry;
import net.dimidium.mymod.handler.registry.ItemRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(MyMod.MOD_ID)
public class MyMod
{
    public static final String MOD_ID = "mymod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public MyMod(IEventBus modEventBus)
    {
        BlockRegistry.registerBlocks(modEventBus);
        FoliagePlacerTypesRegistry.registerFoliagePlacerTypes(modEventBus);
        ItemRegistry.registerItems(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            event.accept(BlockRegistry.RUBBER_LOG);
            event.accept(BlockRegistry.RUBBER_SAPLING);
            event.accept(BlockRegistry.RUBBER_LEAVES);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(BlockRegistry.RUBBER_LEAVES.get(), RenderType.CUTOUT);
            });
        }
    }
}
