package net.dimidium.mymod.handler.registry;

import net.dimidium.mymod.MyMod;
import net.dimidium.mymod.worldgen.placer.RubberFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unchecked")
public class FoliagePlacerTypesRegistry
{
    private static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MyMod.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<?>> RUBBER_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("rubber_foliage_placer", () ->
    {
        return new FoliagePlacerType(RubberFoliagePlacer.CODEC);
    });

    public static void registerFoliagePlacerTypes(IEventBus eventBus)
    {
        FOLIAGE_PLACER_TYPES.register(eventBus);
    }
}
