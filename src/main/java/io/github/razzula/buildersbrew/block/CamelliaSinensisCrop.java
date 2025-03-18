package io.github.razzula.buildersbrew.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CropBlock;

import io.github.razzula.buildersbrew.registry.ModItems;

public class CamelliaSinensisCrop extends CropBlock {
    public CamelliaSinensisCrop(Properties properties) {
        super(properties);
    }

    @Override
    protected Item getBaseSeedId() {
        return ModItems.CAMELLIA_SINENSIS_SEEDS.get();
    }
}
