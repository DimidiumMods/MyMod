package net.dimidium.mymod.worldgen;

import net.dimidium.mymod.MyMod;
import net.dimidium.mymod.handler.registry.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.Arrays;
import java.util.List;

public class PlacedFeatures
{
    public static final ResourceKey<PlacedFeature> RUBBER_PLACED = createKey("rubber_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> getter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> RUBBER = getter.getOrThrow(ConfiguredFeatures.RUBBER);

        register(context, RUBBER_PLACED, RUBBER, Arrays.asList(PlacementUtils.HEIGHTMAP, PlacementUtils.filteredByBlockSurvival(BlockRegistry.RUBBER_SAPLING.get()), RarityFilter.onAverageOnceEvery(90)));
    }

    public static ResourceKey<PlacedFeature> createKey(String pName)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MyMod.MOD_ID, pName));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(feature, modifiers));
    }
}
