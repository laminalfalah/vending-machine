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
import static io.github.laminalfalah.vending.machine.utils.VendingMachineUtils.println;

import io.github.laminalfalah.vending.machine.annotation.Component;
import io.github.laminalfalah.vending.machine.model.Cart;
import io.github.laminalfalah.vending.machine.repository.CartRepository;
import io.github.laminalfalah.vending.machine.repository.FoodRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author laminalfalah on 15/02/23
 */

@Component
public class CartService {

    private static final String LINE = "+----+----------+---------+-------------+%n";

    private static final String ALIGNMENT = "|%-4s|%-10s|%-9s|%-13s|%n";

    private final Scanner scanner;

    private final FoodRepository foodRepository;

    private final CartRepository cartRepository;

    public CartService(Scanner scanner, FoodRepository foodRepository, CartRepository cartRepository) {
        this.scanner = scanner;
        this.foodRepository = foodRepository;
        this.cartRepository = cartRepository;
    }

    public void insertCart(int foodId) {
        var option = true;
        var food = foodRepository.getFoodById(foodId - 1);
        if (food.getStock() < 1) {
            print("Stock is empty !");
            scanner.nextLine();
            option = false;
        }
        while (option) {
            try {
                print("Please amount: ");
                var amount = Integer.parseInt(scanner.nextLine());
                if (amount < 1) {
                    println("Your input must be more than Zero !");
                } else if (amount > food.getStock()) {
                    println("Your amount more than stock available !");
                } else {
                    upsertToCart(foodId, amount);
                    scanner.nextLine();
                    option = false;
                }
            } catch (NumberFormatException e) {
                println("Wrong input. Only accept input number");
            }
        }
    }

    private void upsertToCart(int foodId, int amount) {
        var existsCart = cartRepository.findByFoodId(foodId);
        if (existsCart.isEmpty()) {
            cartRepository.save(foodId, amount);
        } else {
            var cart = existsCart.get();
            cart.setQuantity(amount);
        }
        print("Please check your cart! [Enter]");
    }

    public void showCart() {
        var carts = cartRepository.getCarts();

        printCartHeader();

        if (carts.isEmpty()) {
            printCartBodyEmpty();
        } else {
            confirmCheckout(printCartBody(carts));
        }
    }

    private void printCartHeader() {
        format(LINE);
        format(ALIGNMENT, "No.", "Food", "Quantity", "Subtotal(Rp)");
        format(LINE);
    }

    private void printCartBodyEmpty() {
        format("|%-39s|%n", "Cart is empty");
        format(LINE);
        scanner.nextLine();
    }

    private int printCartBody(List<Cart> carts) {
        var total = 0;
        var totalQuantity = 0;

        for (var i = 0; i < carts.size(); i++) {
            var food = foodRepository.getFoodById(carts.get(i).getFoodId() - 1);
            var foodName = food.getName();
            var quantity = carts.get(i).getQuantity();
            var subtotal = quantity * food.getPrice();
            total += subtotal;
            totalQuantity += quantity;
            format(ALIGNMENT, i + 1 + ".", foodName, quantity, "Rp. " + subtotal);
        }

        format(LINE);
        format(ALIGNMENT, "", "Total", totalQuantity, "Rp. " + total);
        format(LINE);

        return total;
    }

    private void confirmCheckout(int total) {
        var confirm = "";
        do {
            print("Do you do checkout Y/n? ");
            confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                checkout(total);
            }
        } while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n"));
    }

    private void checkout(int total) {
        var option = true;
        var tempMoney = 0;
        var typeMoneyAccepted = List.of(2000, 5000, 10000, 20000, 50000);
        var denominations = Arrays.toString(typeMoneyAccepted.toArray());
        println("Accept denomination " + denominations);
        do {
            try {
                print("Please input your money: ");
                var money = Integer.parseInt(scanner.nextLine());
                var checkMoney = typeMoneyAccepted.stream().filter(value -> value.equals(money)).findAny();
                if (checkMoney.isPresent()) {
                    tempMoney += money;
                    if (tempMoney >= total) {
                        if (tempMoney - total == 0) {
                            execute();
                            showBalance(tempMoney);
                            println("Successfully for payment. Thank you :)");
                            tempMoney = 0;
                            scanner.nextLine();
                            option = false;
                        } else {
                            showBalance(tempMoney);
                            println("The machine can't refunds Rp. " + (tempMoney - total) + " from total Rp. " + total);
                            println("Please take your money, and input money again !");
                            tempMoney = 0;
                        }
                    } else {
                        showBalance(tempMoney);
                    }
                } else {
                    println("The Machine only received " + Arrays.toString(typeMoneyAccepted.toArray()));
                }
            } catch (NumberFormatException e) {
                println("Wrong input. Only accept input number");
            }
        } while (option);
    }

    private void execute() {
        var carts = cartRepository.getCarts();
        for (var cart: carts) {
            foodRepository.updateFoodById(cart.getFoodId() - 1, cart.getQuantity());
        }
        cartRepository.removeCart();
    }

    private void showBalance(int balance) {
        println("Your balance: Rp. " + balance);
    }

}
