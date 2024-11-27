/*
 * Copyright 2019-2022 the original author or authors.
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
import org.docksidestage.bizfw.basic.objanimal.drinking.Drinking;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.dbms.st6dbms.St6dbms;
import org.docksidestage.javatry.basic.st6.os.St6Mac;
import org.docksidestage.javatry.basic.st6.os.St6OldWindows;
import org.docksidestage.javatry.basic.st6.os.St6Windows;
import org.docksidestage.javatry.basic.st6.os.st6OperationSystem.St6OperationSystem;
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

    // done jflute 次回1on1ここから (2024/10/03)
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
        // done tanaryo [ふぉろー] Javaは実は完全なオブジェクト指向ではなく多重継承を排除しています。 by jflute (2024/10/14)
        // 多重継承は複雑になり過ぎて、人の限界を超えてしまって逆に良くないということで。
        // でもその失ってるものはあるわけで、LoudableやFastRunnerみたいな抽象化ができなくなってしまっています。
        // それを補完するのがインターフェース、インターフェースのポリモーフィズムっぽいことができる部分をうまく使って。
        // 厳密にはオブジェクト指向とは無関係の概念ですが、Javaの世界で同居して併用されています。
        // 元々は別々の概念(哲学)なわけなので、その役割にきっちりと線引きがあるわけでもないので、
        // すごく近い部分も出てきて紛らわしいこともあるわけですね。(抽象クラスとインターフェースの文法的な違いとか)
        //
        // オブジェクト指向とintefaceの使い分けパターン二つ:
        // o オブジェクトベースか？機能特化ベースか？ (Animalか？Loudableか？)
        // o Abstractインターフェースパターン (AbstractColorBox implements ColobBox)
        // それぞれのコンセプトの話もした。
        // (オブジェクト指向が持ってる外向けのメリットと内向けのメリットの役割分担: 外向けをinterfaceに任せる)
    }

    // done jflute 1on1次回ここから (2024/10/25)
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
        sea = seaAnimal instanceof Drinking;
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
        // done tanaryo PostgreSqlがMySqlを継承しているのは、DogがCatを継承しているようなものです by jflute (2024/10/14)
        // 継承は、is-aの関係で。Dog is a(n) Animal, 犬は動物である、が自然かどうか？
        // 動物は犬である、これはそうとも限らない。目覚まし時計は犬である、これは詩的な表現。
        // PostgreSqlはMySqlである、と言ってしっくりくるかどうか？
        // done tanaryo たかだか2行ですが「流れ」を再利用したいですね。間に処理が追加されて3行になっても1箇所修正で済むように by jflute (2024/10/14)
        // Animalの例を参考に。
        // これはこれでOK。だがしかし、ごめんなさい。↑このレビューは、MySQL/PostgreSQLクラスの中の話でした。
        St6dbms seaSql = new St6MySql();
        do_test_objectOriented_writing_generalization_extractToAbstract(seaSql);//should be "limit 40, 10"
        St6dbms landSql = new St6PostgreSql();
        do_test_objectOriented_writing_generalization_extractToAbstract(landSql);//should be "offset 40 limit 10"
    }

    // しっかりポリモーフィズムしてる！Good!
    private void do_test_objectOriented_writing_generalization_extractToAbstract(St6dbms st6dbms) {
        log(st6dbms.buildPagingQuery(10, 5));
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        St6OperationSystem macPath = new St6Mac("0724");
        St6OperationSystem oldWindowsPath = new St6OldWindows("0724");
        St6OperationSystem windowsPath = new St6Windows("0724");

        String macResourcePath = macPath.buildUserResourcePath("nakata");
        String oldWindowsResourcePath = oldWindowsPath.buildUserResourcePath("nakata");
        String windowsResourcePath = windowsPath.buildUserResourcePath("nakata");

        log(macResourcePath);//should be "/Users/0724/nakata"
        log(oldWindowsResourcePath);//should be " \Documents and Settigs\0724\nakata"
        log(windowsResourcePath);//should be "\Users\0724\nakata"
    }
    //切り出したprivateメソッドはどっちのクラスにおけば良い？
    //サブクラス特有の処理ならサブクラス、共通処理ならスーパークラスか
    // TODO jflute OSクラスを使って再利用の思考のエクササイズをします↑ (2024/11/15)

    // done tanaryo [宿題] IntelliJでtodoの一覧を表示するやり方を調べてください by jflute (2024/11/08)
    // 表示-> ツールウィンドウ -> TODOで表示可能。ショートカットはありませんでした。
    // ありがとうー by jflute (2024/11/15)
    // [1on1でのふぉろー] キーボードショートカットどれだけカスタマイズする話をした (しすぎ注意)

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
    // TODO jflute 次回1on1ここから (2024/11/15)
    //切り出した処理をサブクラスでどう扱うか
    //downhitpointがbarkのメソッド内に入っている
    //BarkingProcessクラスはインターフェースとして実装？
    //例えばbreatheInメソッドを切り出した場合、downHitPointはどうやって呼び出す？
    //protectedをpublicにすればBarkingProcessクラスで呼び出せそう
    //
    //構造としては

    // [1on1でのふぉろー] ↑上記のメモに対して... by jflute (2024/11/08)
    // o 理想的にはdownhitpoint/breatheInはpublicにはしたくない。(内部をいじることのできるメソッドなので: 簡単にAnimalを壊せてしまう)
    //  i ここはあくまでリファクタリングなので、リファクタリングのために安全性をロスするのは本末転倒なので。
    //  i ただ、どうしてもってときは、いったんの成果物としてpublicで実装でもOK、その後でまたレビューがされるということで。
    //
    // o エクササイズのコンセプトとしては、bark()の実装を切り出すということなので、BarkingProcessは実装が入らないといけない。
    //  i なので、少なくともインターフェースではない。
    //  i 別にインターフェースを導入しちゃいけないってわけじゃないけど、インターフェースだけではいけないってこと。

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
        //適切ではない。animalクラスで抽象メソッドや具象メソッドとして定義されていないメソッドがzombieクラスに存在する
        // _/_/_/_/_/_/_/_/_/_/
    }
}
