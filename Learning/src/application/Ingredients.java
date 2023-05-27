package application;

public class Ingredients {
	String recipeIngredient;
	String recipeQuantity;
	
	public String getRecipeIngredient() {
		return recipeIngredient;
	}
	public void setRecipeIngredient(String recipeIngredient) {
		this.recipeIngredient = recipeIngredient;
	}
	public String getRecipeQuantity() {
		return recipeQuantity;
	}
	public void setRecipeQuantity(String recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}
	
	public Ingredients(String recipeIngredient, String recipeQuantity) {
		super();
		this.recipeIngredient = recipeIngredient;
		this.recipeQuantity = recipeQuantity;
	}
}
