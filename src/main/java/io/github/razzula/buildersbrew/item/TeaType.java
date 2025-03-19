package io.github.razzula.buildersbrew.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public enum TeaType {
    WHITE("Fresh", "White"),
    GREEN("Withered", "Green"),
    OOLONG("Rolled", "Oolong"),
    BLACK("Fermented", "Black");

    private final String freshName;
    private final String driedName;

    TeaType(String freshName, String driedName) {
        this.freshName = freshName;
        this.driedName = driedName;
    }

    public String getFreshName() {
        return this.freshName;
    }

    public String getDriedName() {
        return this.driedName;
    }

    private static final String TAG_TEA_TYPE = "TeaType";

    public static void setTeaType(ItemStack stack, TeaType type) {
        stack.getOrCreateTag().putString(TAG_TEA_TYPE, type.name());
    }

    public static TeaType getTeaType(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_TEA_TYPE)) {
            try {
                return TeaType.valueOf(tag.getString(TAG_TEA_TYPE));
            } catch (IllegalArgumentException e) {
                return WHITE;
            }
        }
        return WHITE;
    }
}