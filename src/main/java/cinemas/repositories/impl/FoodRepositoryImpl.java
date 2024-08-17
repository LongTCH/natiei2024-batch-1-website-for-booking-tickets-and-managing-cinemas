package cinemas.repositories.impl;

import cinemas.models.Food;
import cinemas.repositories.FoodsRepository;
import org.springframework.stereotype.Repository;

@Repository("foodsRepository")
public class FoodRepositoryImpl extends BaseRepositoryImpl<Food, Integer> implements FoodsRepository {
    protected FoodRepositoryImpl() {
        super(Food.class);
    }
}
