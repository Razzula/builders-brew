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
import io.github.razzula.buildersbrew.item.TeaType;
import io.github.razzula.buildersbrew.item.TeaFlavour;

public class FlavouringShapelessRecipe extends ShapelessRecipe {

    private final String flavourVariant;

    public FlavouringShapelessRecipe(ResourceLocation id, String group, CraftingBookCategory category, NonNullList<Ingredient> ingredients, ItemStack result, String flavourVariant) {
        super(id, group, category, result, ingredients);
        this.flavourVariant = flavourVariant;
    }

    @Override
    public ItemStack assemble(@Nonnull CraftingContainer container, @Nonnull RegistryAccess registryAccess) {
        ItemStack flavouredItem = ItemStack.EMPTY;

        // find tea fannings in the crafting grid
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            Item sourceItem = stack.getItem();
            if (sourceItem == ModItems.TEA_FANNINGS.get()) {
                flavouredItem = stack;
                break;
            }
        }

        // only allowed unflavoured, black fannings
        if (flavouredItem.isEmpty() || !flavouredItem.hasTag()) {
            return ItemStack.EMPTY;
        }

        CompoundTag tag = flavouredItem.getTag();
        if (tag == null
            || !tag.contains("TeaType")
            || !tag.contains("TeaFlavour")
        ) {
            return ItemStack.EMPTY;
        }

        String teaType = tag.getString("TeaType");
        String teaFlavour = tag.getString("TeaFlavour");
        if (!teaType.equals(String.valueOf(TeaType.BLACK))) {
            // only allow black tea to be flavoured
            return ItemStack.EMPTY;
        }
        if (!teaFlavour.equals(String.valueOf(TeaFlavour.STANDARD))) {
            // only allow unflavoured tea to be flavoured
            return ItemStack.EMPTY;
        }

        // create the output item and copy NBT
        ItemStack resultStack = getResultItem(registryAccess).copy();
        CompoundTag newTag = tag.copy();
        newTag.putString("TeaFlavour", this.flavourVariant);
        resultStack.setTag(newTag);

        return resultStack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.FLAVOURING_RECIPE_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<Recipe<?>> {
        private final ShapelessRecipe.Serializer shapelessSerializer = new ShapelessRecipe.Serializer();

        @Override
        public Recipe<?> fromJson(@Nonnull ResourceLocation id, @Nonnull JsonObject json) {
            ShapelessRecipe recipe = shapelessSerializer.fromJson(id, json);
            String flavourVariant = json.get("flavour_variant").getAsString();
            return new FlavouringShapelessRecipe(id, recipe.getGroup(), recipe.category(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY), flavourVariant);
        }

        @Override
        public Recipe<?> fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            ShapelessRecipe recipe = shapelessSerializer.fromNetwork(id, buffer);
            String flavourVariant = buffer.readUtf();
            return new FlavouringShapelessRecipe(id, recipe.getGroup(), recipe.category(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY), flavourVariant);
        }

        @Override
        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull Recipe<?> recipe) {
            if (recipe instanceof FlavouringShapelessRecipe flavouringRecipe) {
                shapelessSerializer.toNetwork(buffer, flavouringRecipe);
                buffer.writeUtf(flavouringRecipe.flavourVariant);
            }
        }
    }
}
