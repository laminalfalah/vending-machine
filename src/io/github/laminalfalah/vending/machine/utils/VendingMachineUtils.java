package io.github.laminalfalah.vending.machine.utils;

/*
 * Copyright (C) 2023 the original author laminalfalah All Right Reserved.
 *
 * io.github.laminalfalah.vending.machine.utils
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

/**
 * @author laminalfalah on 16/02/23
 */

public final class VendingMachineUtils {

    private VendingMachineUtils() {}

    public static void format(String value) {
        System.out.format(value);
    }

    public static void format(String alignment, Object... values) {
        System.out.format(alignment, values);
    }

    public static <T> void print(T value) {
        System.out.print(value);
    }

    public static <T> void println(T value) {
        System.out.println(value);
    }
}
