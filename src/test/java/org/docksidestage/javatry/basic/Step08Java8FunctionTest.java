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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

// done tanaryo javadocのauthorお願いします by jflute (2024/12/31)

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        // done tanaryo [いいね] 一つ一つしっかり理解されているようで素晴らしいです。 by jflute (2024/12/31)
        // コールバックってこういうことで、ただのクラス引数が「その場定義のクラス引数」になってコールバックになると。
        // [memo tanaryo]
        // コールバック関数（Consumer<>のaccept）は親関数（helpCallbackConsumer）の引数として渡される。
        // このときコールバック関数に関数型インターフェースを用いて、匿名クラスやラムダ式で記述することで「その場定義できる引数」となる

        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title));
        // broadway
        // dogside:over
        // hangar

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() {
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        });
        // 匿名クラスを使用。このとき抽象メソッドは実装しないといけない。new（クラス名()）{実装}の形。
        // broadway
        // dogside:over
        // hangar

        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        });
        // ラムダ式を利用。（引数）-> {実装}の形。
        // 引数の型は指定しなくて良い（今回だとString）。型推論って言葉が存在していた。
        // メソッド名も明示しなくて良い。関数型インターフェースは１つの抽象メソッドしか持っていないから。

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));
        //実装箇所が１行の場合、{}を省略できる。return文の場合、returnはつけない（つけたらエラー起きた）。

        // your answer? => yes
        //関数型インターフェース（抽象メソッドを１つだけ持っている）をどう実装するか
        // 1.実名クラス
        // 2.匿名クラス new（クラス名）{実装}
        // 3.ラムダ式 （引数）-> {実装} 実装１行なら{}省略可能

        // cannot reassign because it is used at callback process
        //title = "wave";
    }

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor,broadway,dogside,hangar,lost river
        //当たった。1個前のテストと実装内容が異なることに注意
    }

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? =>number:7
        //functionインターフェースは入力型Tを受け取り、戻り値型Rを返す。
    }

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
        //引数をIntegerで受け取り、Stringで返す。
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に（{}なし）
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        //        helpCallbackSupplier(new Supplier<String>() { // sea
        //            public String get() {
        //                return "broadway";
        //            }
        //        });
        helpCallbackSupplier(() -> {
            return "broadway";
        });

        //        helpCallbackSupplier(() -> { // land
        //            return "dockside";
        //        });
        helpCallbackSupplier(() -> "dockside");
        // return は書かなくて良い

        helpCallbackSupplier(() -> {
            return "hangar";
        }); // piari

        // done tanaryo [いいね] 地味ですが、コードの見た目を調整するのにexpression/blockを使い分けたりします by jflute (2024/12/31)
        // expressionでいいものであっても、ときにコールバック内のコード量によってはblockにしたりとか。
        // DBFluteのConditionBeanの ExistsReferrer とかはその例:
        // https://dbflute.seasar.org/ja/manual/function/ormapper/conditionbean/query/existsreferrer.html#implflow
        // まあ、そんな頻繁にあるわけじゃないですが。Stream APIとかだったらガンガンexpressionで書きますので。
        // [memo tanaryo]見た目重視でblockを使うこともある。
    }

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
        //Supplierインターフェースは引数を受け取らず、値を返す。
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName());//1,broadway
        }
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {//nullならfalse
            St8Member member = optMember.get();//getはクラスの持つ値を返す
            log(member.getMemberId(), member.getMemberName());//1,broadway
        }
        // your answer? => Yes。
        //当たった。Optionalクラスを用いた場合の書き換えをここでは理解

        // done jflute 1on1にてOptionalの概念的な話、あとJavaでの導入のお話 (2024/12/31)
        // DBFluteハンズオンでセクション２のところで一緒に話をしようかな。
        // 一番のコンセプトだけした。
        // あと、Java8より前でOptionalが出てこなかった理由は？(推測だけど)のお話。
    }

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());//1,broadway
        }
        optMember.ifPresent(member -> {
            log(member.getMemberId(), member.getMemberName());
        });

        //{}外してみた
        optMember.ifPresent(member -> log(member.getMemberId(), member.getMemberName()));

        //匿名クラスで書いてみた
        optMember.ifPresent(new Consumer<St8Member>() {
            public void accept(St8Member member) {
                log(member.getMemberId(), member.getMemberName());
            }
        });

        // your answer? =>　yes
        //２種類のifPresentの書き換えをここでは理解。
        //前者は引数もたず、true or falseを返す。
        //後者はconsumer型を引数に持ち、値が存在していたらacceptメソッドを実行する。
        //引数にラムダ式があったら考えること
        //引数に関数型インターフェースを使っているな！
        //インスタンスが生成または再利用されているな！
        //ここでは抽象メソッドを実装しているな！

        // done tanaryo [読み物課題] Java8 なら OptionalEntity | DBFlute by jflute (2024/12/31)
        // https://dbflute.seasar.org/ja/manual/function/ormapper/behavior/select/selectentity.html#java8
        // ちょっと、アドバンスな話ですが、Optionalの理解を深めるために読んでみてください。
        // ざっと読みました。DBFluteのハンズオンもやりつつ理解を深めようと思います。 by tanaryo (2025/01/04)
        // done jflute 1on1にて、DBFluteのOptionalと標準のOptionalの違いについて補足 (2024/12/31)
    }

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */
    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // done tanaryo [tips] DBFluteのJava6版だと、こんな感じで null からもしれない変数を戻します by jflute (2024/12/31)
        // traditional style
        //Optionalはjava8から使えるもので、それ以前だとこのようにnullを扱っていたのですね。。。。  by tanaryo (2024/01/04)
        St8Member oldmemberFirst = facade.oldselectMember(1);
        String sea;
        if (oldmemberFirst != null) {
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal();
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason();//"music"
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        // done tanaryo [tips] DBFluteのJava8版からだと、こんな感じで Optional を戻します by jflute (2024/12/31)
        //DBfluteハンズオンをやるときに思い出すようにしておきます！　by tanaryo (2025/01/04)
        Optional<St8Member> optMemberFirst = facade.selectMember(1);

        // done tanaryo [読み物課題] map() and flatMap() | DBFlute by jflute (2024/12/31)
        // https://dbflute.seasar.org/ja/manual/topic/programming/java/java8/mapandflat.html
        // 読むというか図なんですけど、しっかり細かい挙動まで意識して見ているので、図もすぐに理解できるんじゃないかなと。
        // 後半に記述してある、streamAPIのflatmapについての疑問が解消しました。by tanaryo(2025/01/01)

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())
                //functionにて、St8Member型を受け取り、St8Withdrawal型を返す。
                //mapにてSt8Withdrawal型をoptionalでラップして返す

                .map(wdl -> wdl.oldgetPrimaryReason())
                //functionにて、St8Withdrawal型を受け取り、String型を返す。
                //mapにてString型をoptionalでラップする

                .orElse("*no reason: someone was not present");
        //String型のoptionalがnullだったらotherを返す

        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                //functionにて、St8Member型を受け取り、optionalでラップされたSt8Withdrawal型を返す。
                //flatmapにてoptionalでラップされたSt8Withdrawal型をそのまま返す

                .flatMap(wdl -> wdl.getPrimaryReason())
                //functionにて、St8Withdrawal型を受け取り、optionalでラップされたString型を返す。
                //flatmapにてoptionalでラップされたString型をそのまま返す

                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");
        //これまでと同じ結果

        String dstore = facade.selectMember(2)
                //2の場合、reasonはnull

                .flatMap(mb -> mb.getWithdrawal()).map(wdl -> wdl.oldgetPrimaryReason()).orElse("*no reason: someone was not present");
        //otherが入る

        String amba = facade.selectMember(3)
                .flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");
        //3の場合、withdrawalはnull
        //St8WithdrawalがnullでStringもnull

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2).flatMap(mb -> mb.getWithdrawal()).map(wdl -> wdl.getWithdrawalId()) // ID here //12
                .orElse(defaultWithdrawalId);
        //2の場合、reasonはnull
        //reasonじゃなくてIDに変換

        log(sea); // your answer? => music
        log(land); // your answer? => music
        log(piari); // your answer? => music
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => *no reason: someone was not present
        log(amba); // your answer? => *no reason: someone was not present
        log(miraco); // your answer? => 12
    }

    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2);
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over"));
        //supplierにて、IllegalStateExceptionを返す
        //orElseThrowにて、IllegalStateExceptionをスローする
        //orElseThrowはラップ元のオブジェクトを返す。nullなら例外をスローする。
        //stageIdを4とか5にすると例外発生。

        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });
            //reasonはnullなので例外がスローされる
            sea = reason;//ここは実行されない
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => wave
        //当たった。

        // done jflute 1on1にて、orElseThrow() と get() のお話 (2024/12/31)
    }

    // done jflute 後でレビュー (2024/12/31)
    // done jflute Stream APIの理解問題なさそうなので、1on1にてコンセプト的な話を少しする (2025/01/05)
    // [1on1でのふぉろー] 他の言語のコンセプトとかの話もして、Stream API が全世界承認のものか？の話
    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {
                oldfilteredNameList.add(member.getMemberName());
            }
        }
        //各々のメンバーに対して、withdrawalがnullかどうか調べる
        //nullじゃなかったら、名前を追加。broadwayとdocksideが追加される

        String sea = oldfilteredNameList.toString();
        //リストをString型に
        log(sea); // your answer? => [broadway,dockside]

        List<String> filteredNameList = memberList.stream()
                //リストをストリームに変換

                .filter(mb -> mb.getWithdrawal().isPresent())
                //Predicateはtrue or falseを返す
                //filterはtrueとなる要素だけを選択して新しいコレクションとして返す。メンバー1,2の要素を持つストリームが返ってくる
                //filterの実装箇所はReferencePipeline.java？

                .map(mb -> mb.getMemberName())
                //functionにて、St8Member型を受け取り、String型を返す。
                //mapにて各要素に対してfunctionを適用し、新しいストリームに格納して返す。要素はbroadwayとdockside

                .collect(Collectors.toList());
        //collectにてストリーム内の要素をListに変換して返す

        String land = filteredNameList.toString();
        log(land); // your answer? => [broadway,dockside]
    }

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        int sea = memberList.stream()

                .filter(mb -> mb.getWithdrawal().isPresent())
                //Id3はnullなので脱落。

                .flatMap(mb -> mb.getPurchaseList().stream())
                //functionにて、streamに変換
                //[1,2] -> [(1と2を合わせた購入リスト)]みたいなイメージ？
                //実際は[(1-1),(1-2),(1-3),(1-4)]みたいな感じか
                //裏側でどんな処理がされているか
                //Id1に対し、リスト取得、[1,2,3,4]
                //Id2に対し、リスト取得、[]
                //リスト結合？[1,2,3,4]？
                //空配列の扱いは？

                .filter(pur -> pur.getPurchaseId() > 100)
                //脱落なし

                .mapToInt(pur -> pur.getPurchasePrice())
                //[100,200,200,300]のIntStream

                .distinct()
                //重複消去。[100,200,300]

                .sum();//600
        log(sea); // your answer? => 600
    }

    // *Stream API will return at Step12 again, it's worth the wait!
}

//コールバック処理とは
//特定の処理が終了したときに指定されたメソッドが呼び出される仕組み

//Arraylistクラス
//要素を追加するたびに自動的にサイズが長くなるコンテナクラス
//new Arraylist<>() 要素の型や、要素の個数を指定しなくてもOK
//addメソッドで要素の追加。sizeメソッドで要素の個数を調べる。
