package io.github.razzula.buildersbrew.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class TeaBoxItem extends BlockItem {
    public TeaBoxItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        TeaFlavour type = TeaFlavour.getTeaFlavour(stack);
        tooltip.add(Component.literal("(" + type.getName() + ")"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
