package io.github.razzula.buildersbrew.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public enum TeaFlavour {
    STANDARD("Proper Brew"),
    DECAF("Decaf"),
    HARD("Hard Water"),
    GOLD("Gold"),
    BEDTIME("Bedtime"),
    BISCUIT("Malty Biscuit"),
    CARAMEL("Caramelised Biscuit"),
    JAMMY("Toast & Jam"),
    BREAKFAST("Breakfast"),
    // BREAKTIME("Breakfast"), // https://www.yorkshiretea.co.uk/brew-news/new-places-to-find-our-new-brews
    STRONG("Proper Strong"); //https://www.yorkshiretea.com.au/our-teas#proper-strong

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