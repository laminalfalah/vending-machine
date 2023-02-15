package io.github.laminalfalah.vending.machine.repository;

/*
 * Copyright (C) 2023 the original author laminalfalah All Right Reserved.
 *
 * io.github.laminalfalah.vending.machine.repository
 *
 * This is part of the VendingMachine.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.github.laminalfalah.vending.machine.annotation.Component;
import io.github.laminalfalah.vending.machine.model.Food;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laminalfalah on 15/02/23
 */

@Component
public class FoodRepository {

    private static final List<Food> foods = new ArrayList<>(5);

    private static FoodRepository instance;

    static {
        foods.add(Food.builder().name("Biskuit").price(6000).stock(10).build());
        foods.add(Food.builder().name("Chips").price(8000).stock(10).build());
        foods.add(Food.builder().name("Oreo").price(10000).stock(10).build());
        foods.add(Food.builder().name("Tango").price(12000).stock(10).build());
        foods.add(Food.builder().name("Cokelat").price(15000).stock(10).build());
    }

    public static FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    private FoodRepository() {}

    public List<Food> getFoods() {
        return foods;
    }

    public Food getFoodById(int id) {
        return foods.get(id);
    }

    public void updateFoodById(int id, int stock) {
        var food = foods.get(id);
        var oldStock = food.getStock();
        var newStock = oldStock - stock;
        foods.get(id).setStock(newStock);
    }

}
