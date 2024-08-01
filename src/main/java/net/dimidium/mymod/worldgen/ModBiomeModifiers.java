package net.dimidium.mymod.worldgen;

import net.dimidium.mymod.MyMod;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.holdersets.AndHolderSet;
import net.neoforged.neoforge.registries.holdersets.NotHolderSet;
import net.neoforged.neoforge.registries.holdersets.OrHolderSet;

import java.util.List;

public class ModBiomeModifiers
{
    protected static final ResourceKey<BiomeModifier> RUBBER_PLACED = createKey("rubber_placed");

    private static ResourceKey<BiomeModifier> createKey(String path)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MyMod.MOD_ID, path));
    }

    public static void bootstrap(BootstrapContext<BiomeModifier> context)
    {
        HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderSet.Named<Biome> isForest = biomeGetter.getOrThrow(BiomeTags.IS_FOREST);
        HolderSet.Named<Biome> isWet = biomeGetter.getOrThrow(Tags.Biomes.IS_WET_OVERWORLD);
        HolderSet.Named<Biome> isHot = biomeGetter.getOrThrow(Tags.Biomes.IS_HOT_OVERWORLD);
        HolderSet.Named<Biome> isCold = biomeGetter.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD);
        HolderSet<Biome> forestNotHotColdOrWet = new AndHolderSet(List.of(isForest, new ModBiomeModifiers.NotHolderSetWrapper(new OrHolderSet(List.of(isWet, isHot, isCold)))));

        context.register(RUBBER_PLACED, new BiomeModifiers.AddFeaturesBiomeModifier(forestNotHotColdOrWet, HolderSet.direct(new Holder[]{placedGetter.getOrThrow(PlacedFeatures.RUBBER_PLACED)}), GenerationStep.Decoration.VEGETAL_DECORATION));;
    }

    public static class NotHolderSetWrapper<T> extends NotHolderSet<T>
    {
        public NotHolderSetWrapper(HolderSet<T> value)
        {
            super((HolderLookup.RegistryLookup<T>) null, value);
        }

        public boolean canSerializeIn(HolderOwner<T> holderOwner)
        {
            return true;
        }
    }
}
