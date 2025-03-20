package io.github.razzula.buildersbrew;

import io.github.razzula.buildersbrew.recipe.ModRecipeSerializers;
import io.github.razzula.buildersbrew.registry.ModBlocks;
import io.github.razzula.buildersbrew.registry.ModCreativeTabs;
import io.github.razzula.buildersbrew.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BuildersBrew.MODID)
public class BuildersBrew {
    public static final String MODID = "buildersbrew";

    public BuildersBrew() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register Blocks & Items
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModRecipeSerializers.SERIALIZERS.register(modEventBus);

    }
}
