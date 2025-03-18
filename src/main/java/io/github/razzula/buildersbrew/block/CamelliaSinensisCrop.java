package io.github.razzula.buildersbrew.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;

import io.github.razzula.buildersbrew.registry.ModItems;

public class CamelliaSinensisCrop extends CropBlock {
    public CamelliaSinensisCrop(Properties properties) {
        super(properties);
    }

    @Override
    protected Item getBaseSeedId() {
        return ModItems.CAMELLIA_SINENSIS_SEEDS.get();
    }

    @Override
    public MutableComponent getName() {
        int age = this.defaultBlockState().getValue(this.getAgeProperty());

        if (age == 0) {
            return Component.translatable("block.buildersbrew.camellia_sinensis_crop.seed");
        } else if (age <= 2) {
            return Component.translatable("block.buildersbrew.camellia_sinensis_crop.sprout");
        } else {
            return Component.translatable("block.buildersbrew.camellia_sinensis_crop.bush");
        }
    }

    @Override
    public int getMaxAge() {
        return 7; // limits crop growth to 8 stages (0-7)
    }
}
