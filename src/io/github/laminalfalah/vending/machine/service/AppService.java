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

import io.github.laminalfalah.vending.machine.annotation.Component;
import java.util.Scanner;

/**
 * @author laminalfalah on 15/02/23
 */

@Component
public class AppService {

    private static AppService instance;

    private final Scanner scanner;

    private final FoodService foodService;

    private final CartService cartService;

    public static AppService getInstance(Scanner scanner, FoodService foodService, CartService cartService) {
        if (instance == null) {
            instance = new AppService(scanner, foodService, cartService);
        }
        return instance;
    }

    public AppService(Scanner scanner, FoodService foodService, CartService cartService) {
        this.scanner = scanner;
        this.foodService = foodService;
        this.cartService = cartService;
    }

    protected Scanner getScanner() {
        return scanner;
    }

    public void run() {
        var choose = "";
        do {
            System.out.println("Vending Machine...");
            try {
                choose = foodService.showMenuFood();
                var chooseInt = Integer.parseInt(choose);
                if (chooseInt == 0) {
                    cartService.showCart();
                } else if (chooseInt <= foodService.count()) {
                    cartService.insertCart(chooseInt);
                }
            } catch (Exception ignored) {
                System.out.println();
            }
        } while (!choose.equalsIgnoreCase("q"));
        close();
    }

    protected void close() {
        getScanner().close();
        System.exit(0);
    }

}
