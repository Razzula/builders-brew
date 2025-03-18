package io.github.razzula.buildersbrew.registry;

import io.github.razzula.buildersbrew.BuildersBrew;
import io.github.razzula.buildersbrew.block.TestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BuildersBrew.MODID);

    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", TestBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
