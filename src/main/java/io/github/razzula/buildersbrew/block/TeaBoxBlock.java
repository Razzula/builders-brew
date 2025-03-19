package io.github.razzula.buildersbrew.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class TeaBoxBlock extends Block {
    public TeaBoxBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    }
}
