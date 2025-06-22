package io.github.razzula.buildersbrew.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;

import io.github.razzula.buildersbrew.registry.ModItems;
import io.github.razzula.buildersbrew.registry.ModRecipeSerializers;

public class FlavouredSmeltingRecipe extends AbstractCookingRecipe {

    public FlavouredSmeltingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result,
                                   float experience, int cookingTime) {
        super(RecipeType.SMELTING, id, group, CookingBookCategory.MISC, ingredient, result, experience, cookingTime);
    }

    @Override
    public boolean matches(@Nonnull Container container, @Nonnull Level level) {
        ItemStack input = container.getItem(0);

        System.out.println("Checking smelting recipe match:");
        System.out.println(container);
        if (!input.isEmpty()) {
            System.out.println("Smelting match check:");
            System.out.println("Item: " + input);
            System.out.println("NBT: " + input.getTag());
        }

        return super.matches(container, level);
    }

    @Override
    public ItemStack assemble(@Nonnull Container container, @Nonnull RegistryAccess registryAccess) {
        ItemStack input = container.getItem(0);
        ItemStack result = getResultItem(registryAccess).copy();

        if (input.hasTag()) {
            result.setTag(input.getTag().copy());
        }

        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.FLAVOURED_RECIPE_SERIALISER.get();
    }
}
