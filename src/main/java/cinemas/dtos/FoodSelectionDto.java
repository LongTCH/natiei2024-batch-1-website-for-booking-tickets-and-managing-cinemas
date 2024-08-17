package cinemas.dtos;

import cinemas.models.Food;

public class FoodSelectionDto {
    private Food food;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
