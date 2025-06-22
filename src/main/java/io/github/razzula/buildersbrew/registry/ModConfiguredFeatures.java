package io.github.razzula.buildersbrew.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import io.github.razzula.buildersbrew.registry.ModConfiguredFeatures;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_TREE = register("lemon_tree");

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("buildersbrew", name));
    }

    // public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
    //     BlockState log = ModBlocks.LEMON_LOG.get().defaultBlockState();
    //     BlockState leaves = ModBlocks.LEMON_LEAVES.get().defaultBlockState();

    //     TreeConfiguration config = new TreeConfiguration.TreeConfigurationBuilder(
    //             new SimpleStateProvider(log),
    //             new StraightTrunkPlacer(5, 2, 0),
    //             new SimpleStateProvider(leaves),
    //             new BlobFoliagePlacer(2, 0, 2),
    //             new TwoLayersFeatureSize(1, 0, 1)
    //         ).build();

    //     context.register(LEMON_TREE, new ConfiguredFeature<>(Feature.TREE, config));
    // }
}
