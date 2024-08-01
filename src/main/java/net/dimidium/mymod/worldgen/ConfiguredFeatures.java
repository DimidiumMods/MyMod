package net.dimidium.mymod.worldgen;

import net.dimidium.mymod.MyMod;
import net.dimidium.mymod.handler.registry.BlockRegistry;
import net.dimidium.mymod.worldgen.placer.RubberFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER = createKey("rubber");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, RUBBER, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockRegistry.RUBBER_LOG.get()), new StraightTrunkPlacer(4, 1, 2), BlockStateProvider.simple(BlockRegistry.RUBBER_LEAVES.get()), new RubberFoliagePlacer(BlockRegistry.RUBBER.get().defaultBlockState()), new TwoLayersFeatureSize(1, 0, 2)).build());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MyMod.MOD_ID, pName));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> pContext, ResourceKey<ConfiguredFeature<?, ?>> pKey, F pFeature, FC pConfig)
    {
        pContext.register(pKey, new ConfiguredFeature(pFeature, pConfig));
    }
}
