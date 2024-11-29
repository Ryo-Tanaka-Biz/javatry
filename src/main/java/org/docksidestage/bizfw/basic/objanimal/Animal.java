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
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

// TODO donetanaryo せっかくなので自信もって author を入れて記帳してください^^ by jflute (2024/11/28)

/**
 * The object for animal(動物).
 * @author jflute
 * @author tanaryo
 */
public abstract class Animal implements Loudable {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        return executeBarkingProcess().bark();
    }
    //overrideはメソッド単位でしか行えないため、メソッドを切り分ける
    protected BarkingProcess executeBarkingProcess() {
        return new BarkingProcess(this);
    }
    // TODO tanaryo こっちだけは簡単にpublicじゃなくprotectedに戻せると思う by jflute (2024/11/28)
    public abstract String getBarkWord();

    // TODO tanaryo [読み物課題] 比較という点からちょっとこじつけだけど、これ大事なのでぜひ読んでください by jflute (2024/11/28)
    // // デバッグパターン: うごかない、ほかうごくなら、ただひかく
    // https://jflute.hatenadiary.jp/entry/20180811/comparingdebug

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // TODO tanaryo 修行++: こっちは、ちょっとpublicをprotectedに戻すのは難しいのでstep8やってからでもいいかも by jflute (2024/11/28)
    public void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
