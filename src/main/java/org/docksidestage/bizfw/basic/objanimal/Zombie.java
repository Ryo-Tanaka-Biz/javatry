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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.barking.ZombieBarkingProcess;

/**
 * The object for zombie(ゾンビ).
 * @author jflute
 */
public class Zombie extends Animal {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final ZombieDiary zombieDiary = new ZombieDiary();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Zombie() {
        super();
    }

    @Override
    protected int getInitialHitPoint() {
        return -1; // magic number for infinity hit point
    }

    public static class ZombieDiary {

        private int breatheInCount;

        public void countBreatheIn() {
            ++breatheInCount;
        }

        public int getBreatheInCount() {
            return breatheInCount;
        }
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    // TODO tanaryo オーバーライドの粒度、本当に最低限だけをオーバーライドしたい by jflute (2024/11/28)
    // 現状だと、bark()の流れをコピーしてきてしまっている。仮にsuperのbark()で処理が追加された場合に追従できない。
    // (BarkingProcessという切り出しまでやっておいて処理の追加ってしないだろうけど、でもできてしまうのでそれを防ぎたい)
    @Override
    protected BarkingProcess executeBarkingProcess() {
        return new ZombieBarkingProcess(this);
    }
    // done tanaryo [ふぉろー] hint1: オブジェクト指向はもっと自由で... by jflute (2024/11/15)
    // 階層構造にしていいオブジェクトは一つ(の概念)だけってわけじゃない。
    @Override
    public String getBarkWord() {
        return "uooo"; // what in English?
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    public void downHitPoint() {
        // do nothing, infinity hit point
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public ZombieDiary getZombieDiary() {
        return zombieDiary;
    }
}
