package io.github.razzula.buildersbrew.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.nbt.CompoundTag;

import io.github.razzula.buildersbrew.registry.ModItems;
import io.github.razzula.buildersbrew.registry.ModRecipeSerializers;

public class FlavouredShapelessRecipe extends ShapelessRecipe {

    public FlavouredShapelessRecipe(ResourceLocation id, String group, CraftingBookCategory category, NonNullList<Ingredient> ingredients, ItemStack result) {
        super(id, group, category, result, ingredients);
    }

    @Override
    public ItemStack assemble(@Nonnull CraftingContainer container, @Nonnull RegistryAccess registryAccess) {
        ItemStack flavouredItem = ItemStack.EMPTY;

        // find the tea in the crafting grid
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            Item item = stack.getItem();
            if (item == ModItems.TEA_FANNINGS.get()
                || item == ModItems.TEA_BAG.get()
                || item == ModItems.TEA_BOX_ITEM.get()
                || item == ModItems.MUG_TEA.get()
            ) {
                flavouredItem = stack;
                break;
            }
        }

        // create the output item and copy NBT
        ItemStack resultStack = getResultItem(registryAccess).copy();
        if (!flavouredItem.isEmpty() && flavouredItem.hasTag()) {
            CompoundTag tag = flavouredItem.getTag();
            if (tag != null) {
                resultStack.setTag(tag.copy());
            }
        }

        return resultStack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.FLAVOURED_RECIPE_SERIALISER.get();
    }
}
