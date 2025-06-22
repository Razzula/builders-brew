package io.github.razzula.buildersbrew.recipe;

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

    private static CompoundTag lastInput = null;
    private String inputType = null;
    private String outputType = null;

    public FlavouredStonecuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, String inputType, String outputType) {
        super(id, group, ingredient, result);
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public FlavouredStonecuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(id, group, ingredient, result);
    }

    @Override
    public boolean matches(@Nonnull Container container, @Nonnull Level level) {
        if (container.getContainerSize() > 0) {
            ItemStack stack = container.getItem(0);
            boolean matches = ingredient.test(stack);

            CompoundTag tag = stack.getTag();
            if (level.isClientSide) {
                if (!stack.isEmpty() && matches) {
                    // cache input for GUI button
                    FlavouredStonecuttingRecipe.cacheInput(tag);
                }
            }
            if (tag.contains("TeaType") && inputType != null) {
                return matches && inputType.equals(tag.getString("TeaType"));
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

        // use correct NBT data for output
        CompoundTag tag = input.hasTag() ? input.getTag().copy() : new CompoundTag();
        if (!tag.contains("TeaFlavour") && inputType == null) {
            tag.putString("TeaFlavour", "STANDARD"); // some leaves are not flavoured, but all fannings must be
        } else if (outputType != null) {
            tag.putString("TeaType", outputType);
        }
        resultStack.setTag(tag.copy());

        return resultStack;
    }

    @Override
    public ItemStack getResultItem(@Nonnull RegistryAccess access) {
        ItemStack resultItem = super.getResultItem(access).copy();

        if (ingredient.getItems().length > 0) {
            // use correct NBT data for GUI button
            CompoundTag tag = lastInput;

            if (tag != null) {
                if (outputType != null) {
                    CompoundTag copy = tag.copy();
                    copy.putString("TeaType", outputType);
                    resultItem.setTag(copy);
                } else 
                    resultItem.setTag(tag.copy());
            }
        }

        return resultItem;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.FLAVOURING_RECIPE_SERIALIZER.get();
    }

    public static void cacheInput(CompoundTag input) {
        // TODO: replace global cache with per-player Map<UUID, ItemStack> for multiplayer safety.
        lastInput = input.copy();
    }

    public static void clearCache() {
        lastInput = null;
    }
}
