package io.github.razzula.buildersbrew;

import io.github.razzula.buildersbrew.registry.ModBlocks;
import io.github.razzula.buildersbrew.registry.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

        // Add items to the creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.TEST_BLOCK_ITEM.get());
        }
    }
}
