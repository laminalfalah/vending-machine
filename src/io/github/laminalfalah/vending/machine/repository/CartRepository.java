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
import io.github.laminalfalah.vending.machine.model.Cart;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author laminalfalah on 15/02/23
 */

@Component
public class CartRepository {

    private static final List<Cart> carts = new ArrayList<>(5);

    private static CartRepository instance;

    public static CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }

    private CartRepository() {}

    public List<Cart> getCarts() {
        return carts;
    }

    public void save(int foodId, int quantity) {
        carts.add(Cart.builder().foodId(foodId).quantity(quantity).build());
    }

    public Optional<Cart> findByFoodId(int foodId) {
        return carts.stream()
            .filter(cart -> Objects.equals(cart.getFoodId(), foodId))
            .findFirst();
    }

    public void removeCart() {
        carts.clear();
    }

}
