package io.github.laminalfalah.vending.machine.model;

/*
 * Copyright (C) 2023 the original author laminalfalah All Right Reserved.
 *
 * io.github.laminalfalah.vending.machine.model
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

import java.io.Serializable;

/**
 * @author laminalfalah on 15/02/23
 */

public class Cart implements Serializable {

    private Integer foodId;

    private Integer quantity;

    public static Builder builder() {
        return new Builder();
    }

    public Cart(Integer foodId, Integer quantity) {
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static class Builder {

        private Integer foodId;

        private Integer quantity;

        public Builder foodId(Integer foodId) {
            this.foodId = foodId;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Cart build() {
            return new Cart(foodId, quantity);
        }

    }

}
