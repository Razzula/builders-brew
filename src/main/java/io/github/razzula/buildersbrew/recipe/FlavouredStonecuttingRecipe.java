package io.github.razzula.buildersbrew.recipe;

import java.util.UUID;

import javax.annotation.Nonnull;

import io.github.razzula.buildersbrew.registry.ModRecipeSerializers;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.Level;

public class FlavouredStonecuttingRecipe extends StonecutterRecipe  {

    private static ItemStack lastInput = ItemStack.EMPTY;

    public FlavouredStonecuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(id, group, ingredient, result);
    }

    @Override
    public boolean matches(@Nonnull Container container, @Nonnull Level level) {
        if (container.getContainerSize() > 0) {
            ItemStack stack = container.getItem(0);
            boolean matches = ingredient.test(stack);

            if (level.isClientSide) {
                if (matches) {
                    // cache input for GUI button
                    FlavouredStonecuttingRecipe.cacheInput(stack);
                }
                else {
                    FlavouredStonecuttingRecipe.clearCache();
                }
            }

            return matches;
        }
        return false;
    }

    @Override
    public ItemStack assemble(@Nonnull Container container, @Nonnull RegistryAccess registryAccess) {
        if (container.getContainerSize() == 0) {
            return ItemStack.EMPTY;
        }

        ItemStack input = container.getItem(0);
        ItemStack resultStack = super.assemble(container, registryAccess).copy();

        if (input.hasTag()) {
            // use correct NBT data for output
            CompoundTag tag = input.getTag();
            if (tag != null) {
                resultStack.setTag(tag.copy());
            }
        }

        return resultStack;
    }

    @Override
    public ItemStack getResultItem(@Nonnull RegistryAccess access) {
        ItemStack resultItem = super.getResultItem(access).copy();

        if (ingredient.getItems().length > 0) {
            // use correct NBT data for GUI button
            ItemStack input = lastInput;

            if (input.hasTag()) {
                CompoundTag tag = input.getTag();
                if (tag != null) {
                    resultItem.setTag(tag.copy());
                }
            }
        }

        return resultItem;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.FLAVOURING_RECIPE_SERIALIZER.get();
    }

    public static void cacheInput(ItemStack input) {
        // TODO: replace global cache with per-player Map<UUID, ItemStack> for multiplayer safety.
        lastInput = input.copy();
    }

    public static void clearCache() {
        lastInput = ItemStack.EMPTY;
    }
}
