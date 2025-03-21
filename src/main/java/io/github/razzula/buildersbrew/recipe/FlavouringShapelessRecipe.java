package io.github.razzula.buildersbrew.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import io.github.razzula.buildersbrew.item.TeaFlavour;
import io.github.razzula.buildersbrew.item.TeaType;
import io.github.razzula.buildersbrew.registry.ModItems;
import io.github.razzula.buildersbrew.registry.ModRecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class FlavouringShapelessRecipe extends ShapelessRecipe {

    private final String inputFlavour;
    private final String outputFlavour;

    public FlavouringShapelessRecipe(
        ResourceLocation id, String group, CraftingBookCategory category, NonNullList<Ingredient> ingredients, ItemStack result,
        String inputFlavour, String outputFlavour
    ) {
        super(id, group, category, result, ingredients);
        this.inputFlavour = inputFlavour;
        this.outputFlavour = outputFlavour;
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
        if (!teaFlavour.equals(this.inputFlavour)) {
            return ItemStack.EMPTY;
        }

        // create the output item and copy NBT
        ItemStack resultStack = getResultItem(registryAccess).copy();
        CompoundTag newTag = tag.copy();
        newTag.putString("TeaFlavour", this.outputFlavour);
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
            String inputFlavour = json.has("input_flavour") ? json.get("input_flavour").getAsString() : "STANDARD";
            String outputFlavour = json.get("output_flavour").getAsString();
            return new FlavouringShapelessRecipe(id, recipe.getGroup(), recipe.category(), recipe.getIngredients(),
                recipe.getResultItem(RegistryAccess.EMPTY), inputFlavour, outputFlavour
            );
        }

        @Override
        public Recipe<?> fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            ShapelessRecipe recipe = shapelessSerializer.fromNetwork(id, buffer);
            String inputFlavour = buffer.readUtf();
            String outputFlavour = buffer.readUtf();
            return new FlavouringShapelessRecipe(id, recipe.getGroup(), recipe.category(), recipe.getIngredients(),
                recipe.getResultItem(RegistryAccess.EMPTY), inputFlavour, outputFlavour
            );
        }

        @Override
        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull Recipe<?> recipe) {
            if (recipe instanceof FlavouringShapelessRecipe flavouringRecipe) {
                shapelessSerializer.toNetwork(buffer, flavouringRecipe);
                buffer.writeUtf(flavouringRecipe.inputFlavour);
                buffer.writeUtf(flavouringRecipe.outputFlavour);
            }
        }
    }
}
