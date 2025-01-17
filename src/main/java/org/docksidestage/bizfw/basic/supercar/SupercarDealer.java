/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

import org.docksidestage.bizfw.basic.supercar.SupercarManufacturer.Supercar;
import org.docksidestage.bizfw.basic.supercar.exception.SteeringWheelManufacturingException;
import org.docksidestage.bizfw.basic.supercar.exception.SupercarManufacturingException;

/**
 * The dealer(販売業者) of supercar.
 * @author jflute
 */
public class SupercarDealer {

    public Supercar orderSupercar(String clientRequirement) {
        // TODO done tanaryo try/catch, if文ごとにやるんじゃなく、if文の外に定義したら一個になるんじゃないかな？ by jflute (2025/01/05)
        SupercarManufacturer supercarManufacturer = createSupercarManufacturer();
        String catalogKey;

        if (clientRequirement.contains("steering wheel is like sea")) {
            catalogKey = "piari";
        } else if (clientRequirement.contains("steering wheel is useful on land")) {
            catalogKey = "land";
        } else if (clientRequirement.contains("steering wheel has many shop")) {
            catalogKey = "piari";
        } else {
            throw new IllegalStateException("Cannot understand the client requirement: " + clientRequirement);
        }
        //try-catchを外に出す
        try {
            return supercarManufacturer.makeSupercar(catalogKey);
        } catch (SteeringWheelManufacturingException e) {
            throw new SupercarManufacturingException("Failed to make supercar. clientRequirement:" + clientRequirement, e);
        }
    }

    protected SupercarManufacturer createSupercarManufacturer() {
        return new SupercarManufacturer();
    }
}
