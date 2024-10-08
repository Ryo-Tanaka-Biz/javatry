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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.presenttime.DefaultPresentTimeManager;
import org.docksidestage.bizfw.basic.buyticket.ticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.ticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.ticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.ticket.TicketReader;
import org.docksidestage.bizfw.basic.objanimal.*;
import org.docksidestage.bizfw.basic.objanimal.drink.Drink;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.dbms.st6sql.St6Sql;
import org.docksidestage.unit.PlainTestCase;

// done tanaryo unusedのimportあり↑ by jflute (2024/09/26)

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        // done tanaryo このへんに、TicketBoothであった不具合と同じものがある by jflute (2024/09/26)
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        --quantity;
        salesProceeds = handedMoney;

        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        int displayPrice = oneDayPrice;//右辺はquantityではなくoneDayPrice
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);//quantity -> displayPriceに変更
        }
        alreadyIn = true;

        //
        // [final process]
        //
        // [ふぉろー] プログラマーとして
        // o できるだけそもそもこういう間違いやすいメソッドを作らない(努力)
        // o どうしても呼び出さないといけないとき...
        //  i 危ないなっていう意識を高めて、集中力増して指差し確認
        //  i 危ないなっていう勘所を知ってることが前提 (これは経験から来るもの)
        //  i (優秀なひとこそ、なにげに地味なことちゃんとやってる by jflute)
        // [ふぉろー] オブジェクト指向として
        // o 情報をバラけて扱うとプログラミングにおいて不都合が発生しやすい
        // o なのでオブジェクトという概念を見出して、それをプログラミングに反映させている
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);//引数の順序がおかしい
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // simulation: only logging here (normally e.g. DB insert)
            showTicketBooth(quantity, salesProceeds);//引数おかしい
            showYourTicket(displayPrice, alreadyIn);//引数おかしい
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        TicketBuyResult ticketBuyResult = booth.buyOneDayPassport(10000);
        Ticket ticket = ticketBuyResult.getTicket();
        TicketReader ticketReader = new TicketReader(ticket);
        //booth.buyOneDayPassport(10000); // as temporary, remove if you finished step05
        //Ticket ticket = new Ticket(7400); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticketReader.doInPark(new DefaultPresentTimeManager());

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket, ticketReader);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket, TicketReader ticketReader) {
        if (ticketReader.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket, ticketReader);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket, TicketReader ticketReader) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticketReader.isAlreadyIn());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    //特定の状態と振る舞いを持ったまとまりのこと
    // _/_/_/_/_/_/_/_/_/_/
    // [ふぉろー] オブジェクトとは？の話をした by jflute (2024/09/26)
    // その「まとまり」をいかに見出すか？がポイントだし難しいこと。
    // それをきっちり見出してから、継承とかポリモーフィズムとかがあると言って良い。

    // done jflute 次回1on1ここから (2024/09/26)
    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog(); //サブクラスの型でnew
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
    }

    //当たった。barkメソッドでdownHitPoint()が3回呼び出されている。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Cat();
        //スーパークラスでnewしたインスタンスを受け取る(扱ってる)。
        //スーパークラスに定義されたメソッドしか呼び出せないが、
        //サブクラスでオーバーライドされている場合は、サブクラスのメソッドが呼ばれる
        //複数存在するサブクラスを同じ型で利用できることがメリット？
        // [ふぉろー] ↑現実の世界でたくさん使われてる話 (受付から見た新卒、客から見た店員とか)
        //
        //@Overrideの役割：メソッドが正しくオーバーライドされているかをコンパイル時にチェックしてくれる
        // [ふぉろー] @Overrideの挙動と歴史の話をした。他の言語の話も少し。
        //
        BarkedSound sound = animal.bark();//ここはanimalクラスのメソッド
        String sea = sound.getBarkWord();//ここはdogクラスのメソッド
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();//animal型でdogインスタンス作成。以降は前のメソッドと同様。
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
        //サブクラス型をスーパークラス型に代入(アップキャスト)
        //型はスーパークラスだが、インスタンス自体はサブクラス
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();//スーパークラスのメソッド
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        //downHitPointはcatでオーバーライド。ヒットポイントが偶数ならさらに1引く
        //１回目で9、2回目で8->7、3回目で6->5
        log(land); // your answer? => 5
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
    }
    //当たった
    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        //複数存在するサブクラスを同じ型で利用できる
        // [ふぉろー] yes, つまり利用する側のメリットになる
        // 利用する側が最低限のことしか知らなくて良い (e.g. 受付から見た新卒)
        // _/_/_/_/_/_/_/_/_/_/
        // [ふぉろー] プログラミングは論理思考のトレーニングにもつながる (学び方次第で)
    }

    // TODO jflute 次回1on1ここから (2024/10/03)
    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();//loudable->(implements)->Animal ->(extends)->Zombie
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        //Zombie型にキャスト。barkメソッドはインターフェースで定義されていないため、loudable型では呼び出せない。
        log(land); // your answer? => uooo
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
    }


    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();//Catの元はFastRunner
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        //変数が指定したクラスのインスタンスであるかをチェック
        //または変数が指定したインターフェースを実装したクラスのインスタンスであるかチェック
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        Animal seaAnimal = new Dog();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        //多重継承できるか(抽象クラス：不可、インターフェース：可）
        //メソッドの実装を持つか(抽象クラス：持つ、インターフェース：持たない）
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        Animal seaAnimal = new Fish();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => false
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    //コンクリートクラスとは全てのメソッドが実装されているクラス
    public void test_objectOriented_polymorphism_makeInterface() {
        Animal seaAnimal = new Dog();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => True
        sea = seaAnimal instanceof Drink;
        log(sea); // your answer? => True
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */


    public void test_objectOriented_writing_generalization_extractToAbstract() {
        St6Sql seaSql = new St6MySql();
        St6Sql landSql = new St6PostgreSql();
        String sea = seaSql.buildPagingQuery(10,5);
        String land = landSql.buildPagingQuery(10,5);
        log(sea);//should be "limit 40, 10"
        log(land);//should be "offset 40 limit 10"
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        //
        // _/_/_/_/_/_/_/_/_/_/
    }
}
