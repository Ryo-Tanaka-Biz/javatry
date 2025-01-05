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
    protected int hitPoint;// is HP
    protected Runnable runnable;

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
        return createBarkingProcess().bark(getBarkWord());
    }
    //overrideはメソッド単位でしか行えないため、メソッドを切り分ける
    // [1on1でのふぉろー] Good. こういうのを「Factoryメソッド」と言います。(インスタンスを生成する工場メソッド)
    // done tanaryo executeは実行するときに使うもの感ある、のでオーソドックスではcreate/prepare by jflute (2024/12/06)
    // createはインスタンスを作ってるというニュアンスをメソッド名に入れちゃう。
    // prepareはインスタンスを作ってるかどうかは問わず、単に準備しました、みたいな。(抽象度の違い)
    protected BarkingProcess createBarkingProcess() {
        return new BarkingProcess(() -> downHitPoint());
    }
    // done tanaryo こっちだけは簡単にpublicじゃなくprotectedに戻せると思う by jflute (2024/11/28)
    // done tanaryo 本当にgetBarkWord()の中身も隠蔽した上で実現できます (しかもstep8とか要らない: 基礎文法) by jflute (2024/12/06)
    // downHitPointとの違いは？
    //getBarkWordは単純に文字列を返している
    //文字列を隠せば良い？
    //泣き声データを管理するクラスやリソースを作成してBarkingProcessで取ってくる？
    //パッケージ移動とサブクラス以外にあるのか。。。。。
    //もはやBarkingProcessからアクセスしない？
    // done tanaryo hint1: "downHitPointとの違いは？" という問いがとても良い by jflute (2024/12/13)
    // getBarkWord(): BarkingProcessは、戻ってきた文字列が欲しいだけ (中の処理の実行タイミングは関係ない)
    // downHitPoint(): BarkingProcessは、downHitPoint()を適所タイミングで実行しないといけない
    // hint2: 基礎文法と書いたけど、基礎基礎文法でどうにかなる
    // hint3: 修正しても、全体クラス構造は何にも変わらない
    // hint4: step4とかくらいの文法知識
    //
    // [1on1でのフォロー] 自力で灯台下暗しを発見してくれた！
    // プログラマー (ITエンジニア) は、こういうの見逃しがち、難しいこと考えすぎなことがよくある。
    // これをjavatryで体験できたのが良かった良かった。 by jflute
    //
    // 質問: どうやって導くか？ by tanaryo
    // BarkingProcessの欲しいもの整理整頓が基本で、"戻ってきた文字列が欲しいだけ" がわかれば引数につながる。
    // 引数がつながらないケースは、覚えた文法(武器)が頭の中で整理整頓できてなくて取り出せないのかも。
    // ゲームでアイテム欄を整理整頓するのと同じく、学んだことを整理整頓して頭に置いておくのが大切。
    //
    // いい思い出にしてもらえればと。
    //[memo tanaryo 2025/1/1]
    // animal.downHitPointで呼び出すのはprotectedだとどう頑張っても無理か
    // barkingProcessをnewした時点で、animalクラスから辿る以外でdownHitPointにアクセスできるようにしたい
    // barkingProcessをnewするタイミングで関数型インターフェースでdownHitPointを実行するように定義して引数に渡す

    protected abstract String getBarkWord();

    // [1on1でのフォロー] これはこれで発想としては良い、業務として辻褄合わせてしまうという by jflute

    // done tanaryo [読み物課題] 比較という点からちょっとこじつけだけど、これ大事なのでぜひ読んでください by jflute (2024/11/28)
    // // デバッグパターン: うごかない、ほかうごくなら、ただひかく
    // https://jflute.hatenadiary.jp/entry/20180811/comparingdebug

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    // done tanaryo 修行++: こっちは、ちょっとpublicをprotectedに戻すのは難しいのでstep8やってからでもいいかも by jflute (2024/11/28)
    // TODO tanaryo あっ、完璧にできてる^^) by jflute (2025/01/05)
    // TODO jflute 1on1にて、オブジェクト指向の構造とコールバックのコラボについて、というかオブジェクト指向の線引きって何だ？話も (2025/01/05)
    protected void downHitPoint() {
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
