package cinemas.dtos;

import java.util.ArrayList;
import java.util.List;

public class FoodSelectionFormDto {
    private List<FoodSelectionDto> foodSelections;

    public FoodSelectionFormDto() {
        this.foodSelections = new ArrayList<>();
    }

    public void addFoodSelection(FoodSelectionDto foodSelection) {
        foodSelections.add(foodSelection);
    }

    public List<FoodSelectionDto> getFoodSelections() {
        return foodSelections;
    }
}
