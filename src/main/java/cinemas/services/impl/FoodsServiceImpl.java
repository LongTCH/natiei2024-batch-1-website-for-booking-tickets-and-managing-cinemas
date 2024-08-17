package cinemas.services.impl;

import cinemas.models.Food;
import cinemas.repositories.FoodsRepository;
import cinemas.services.FoodsService;

import java.util.List;

public class FoodsServiceImpl implements FoodsService {
    private FoodsRepository foodsRepository;
    public FoodsServiceImpl(FoodsRepository foodsRepository){this.foodsRepository = foodsRepository;}

    @Override
    public List<Food> getAllFoods() {
        return foodsRepository.findAll();
    }
}
