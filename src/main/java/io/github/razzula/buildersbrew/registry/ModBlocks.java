package io.github.razzula.buildersbrew.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.block.TeaBoxBlock;
import io.github.razzula.buildersbrew.block.CamelliaSinensisCrop;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BuildersBrew.MODID);


    // CROP
    public static final RegistryObject<Block> CAMELLIA_SINENSIS_CROP = BLOCKS.register("camellia_sinensis_crop",
        () -> new CamelliaSinensisCrop(
            BlockBehaviour.Properties.copy(Blocks.WHEAT)
            .noCollission()
            .randomTicks()
            .instabreak()
        )
    );

    // TEA BOXES
    public static final RegistryObject<Block> TEA_BOX = BLOCKS.register("tea_box",
            () -> new TeaBoxBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
