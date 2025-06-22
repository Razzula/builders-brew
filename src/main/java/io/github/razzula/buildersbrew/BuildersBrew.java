package io.github.razzula.buildersbrew;

import io.github.razzula.buildersbrew.registry.ModBlocks;
import io.github.razzula.buildersbrew.registry.ModCreativeTabs;
import io.github.razzula.buildersbrew.registry.ModItems;
import io.github.razzula.buildersbrew.registry.ModRecipeSerializers;
import io.github.razzula.buildersbrew.registry.ModConfiguredFeatures;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.data.event.GatherDataEvent;
// import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(BuildersBrew.MODID)
public class BuildersBrew {
    public static final String MODID = "buildersbrew";

    public BuildersBrew(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register Blocks & Items
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModRecipeSerializers.SERIALIZERS.register(modEventBus);

    }

//     @SubscribeEvent
//     public static void gatherData(GatherDataEvent event) {
//         // Register data providers
//         event.getGenerator().addProvider(new ModConfiguredFeatures.Provider(event.getGenerator()));
//     }
}
