package io.github.razzula.buildersbrew.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.block.TeaBoxBlock;
import io.github.razzula.buildersbrew.block.CamelliaSinensisCrop;
import io.github.razzula.buildersbrew.world.tree.LemonTreeGrower;

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
        () -> new TeaBoxBlock(BlockBehaviour.Properties.of().noOcclusion())
    );

    // LEMON
    public static final RegistryObject<Block> LEMON_SAPLING = BLOCKS.register("lemon_sapling",
        () -> new SaplingBlock(new LemonTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
    );
    public static final RegistryObject<Block> LEMON_LOG = BLOCKS.register("lemon_log",
        () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> LEMON_PLANKS = BLOCKS.register("lemon_planks",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS))
    );
    public static final RegistryObject<Block> LEMON_LEAVES = BLOCKS.register("lemon_leaves",
        () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
