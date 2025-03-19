package io.github.razzula.buildersbrew.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public enum TeaFlavour {
    STANDARD("Proper Brew"),
    DECAF("Decaf"),
    HARD("Hard Water"),
    GOLD("Gold");
    // BEDTIME("Bedtime"),
    // BISCUIT("Biscuit"),
    // JAMMY("Toast & Jam");

    private final String name;

    TeaFlavour(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private static final String TAG_TEA_FLAVOUR = "TeaFlavour";

    public static void setTeaFlavour(ItemStack stack, TeaFlavour type) {
        stack.getOrCreateTag().putString(TAG_TEA_FLAVOUR, type.name());
    }

    public static TeaFlavour getTeaFlavour(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_TEA_FLAVOUR)) {
            try {
                return TeaFlavour.valueOf(tag.getString(TAG_TEA_FLAVOUR));
            } catch (IllegalArgumentException e) {
                return STANDARD;
            }
        }
        return STANDARD;
    }
}