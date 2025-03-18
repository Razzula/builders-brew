package io.github.razzula.buildersbrew.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class TestBlock extends Block {
    public TestBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    }
}
