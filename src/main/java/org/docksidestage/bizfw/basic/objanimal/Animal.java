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

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;

// done tanaryo せっかくなので自信もって author を入れて記帳してください^^ by jflute (2024/11/28)

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
        return createBarkingProcess().bark();
    }
    //overrideはメソッド単位でしか行えないため、メソッドを切り分ける
    // [1on1でのふぉろー] Good. こういうのを「Factoryメソッド」と言います。(インスタンスを生成する工場メソッド)
    // TODO done tanaryo executeは実行するときに使うもの感ある、のでオーソドックスではcreate/prepare by jflute (2024/12/06)
    // createはインスタンスを作ってるというニュアンスをメソッド名に入れちゃう。
    // prepareはインスタンスを作ってるかどうかは問わず、単に準備しました、みたいな。(抽象度の違い)
    protected BarkingProcess createBarkingProcess() {
        return new BarkingProcess(this);
    }
    // done tanaryo こっちだけは簡単にpublicじゃなくprotectedに戻せると思う by jflute (2024/11/28)
    // TODO tanaryo 本当にgetBarkWord()の中身も隠蔽した上で実現できます (しかもstep8とか要らない: 基礎文法) by jflute (2024/12/06)
    // downHitPointとの違いは？
    //getBarkWordは単純に文字列を返している
    //文字列を隠せば良い？
    //泣き声データを管理するクラスやリソースを作成してBarkingProcessで取ってくる？

    protected abstract String getBarkWord();

    // [1on1でのフォロー] これはこれで発想としては良い、業務として辻褄合わせてしまうという by jflute
    public String speak() {
        return getBarkWord();
    }

    // done tanaryo [読み物課題] 比較という点からちょっとこじつけだけど、これ大事なのでぜひ読んでください by jflute (2024/11/28)
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
