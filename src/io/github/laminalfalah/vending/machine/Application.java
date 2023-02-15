package io.github.laminalfalah.vending.machine;

/*
 * Copyright (C) 2023 the original author laminalfalah All Right Reserved.
 *
 * io.github.laminalfalah.vending.machine
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

import io.github.laminalfalah.vending.machine.repository.CartRepository;
import io.github.laminalfalah.vending.machine.repository.FoodRepository;
import io.github.laminalfalah.vending.machine.service.AppService;
import io.github.laminalfalah.vending.machine.service.CartService;
import io.github.laminalfalah.vending.machine.service.FoodService;
import java.util.Scanner;

/**
 * @author laminalfalah on 15/02/23
 */

public class Application {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var foodRepository = FoodRepository.getInstance();
        var cartRepository = CartRepository.getInstance();
        var foodService = new FoodService(scanner, foodRepository);
        var cartService = new CartService(scanner, foodRepository, cartRepository);
        AppService.getInstance(scanner, foodService, cartService).run();
    }

}
