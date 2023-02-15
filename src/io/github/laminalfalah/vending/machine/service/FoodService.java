package io.github.laminalfalah.vending.machine.service;

/*
 * Copyright (C) 2023 the original author laminalfalah All Right Reserved.
 *
 * io.github.laminalfalah.vending.machine.service
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

import static io.github.laminalfalah.vending.machine.utils.VendingMachineUtils.format;
import static io.github.laminalfalah.vending.machine.utils.VendingMachineUtils.print;

import io.github.laminalfalah.vending.machine.annotation.Component;
import io.github.laminalfalah.vending.machine.repository.FoodRepository;
import java.util.Scanner;

/**
 * @author laminalfalah on 15/02/23
 */

@Component
public class FoodService {

    private static final String LINE = "+----+----------+------+----------+%n";

    private static final String ALIGNMENT = "|%-4s|%-10s|%-6s|%-10s|%n";

    private final Scanner scanner;

    private final FoodRepository repository;

    public FoodService(Scanner scanner, FoodRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    public int count() {
        return repository.getFoods().size();
    }

    public String showMenuFood() {
        format(LINE);
        format(ALIGNMENT, "No.", "Menu", "Stock", "Price(Rp)");
        format(LINE);

        var foods = repository.getFoods();

        for (var i = 0; i < foods.size(); i++) {
            var foodName = foods.get(i).getName();
            var foodStock = foods.get(i).getStock();
            var foodPrice = "Rp. " + foods.get(i).getPrice();
            format(ALIGNMENT, i + 1 + ". ", foodName, foodStock, foodPrice);
        }

        format(LINE);
        print("Choose 1-" + foods.size());
        print(" or '0' view your cart");
        print(" or 'q' to exit.\n");
        print("Please choose: ");
        return scanner.nextLine();
    }

}
