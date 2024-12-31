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

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();//例外が発生した時点で、処理は中断される。
            sea.append("dockside");//この処理は実行されない
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? =>hangerbroadway
    }
    //当たった

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();//ここで例外起きると期待
            fail("no way here");//起きなかったら、failで失敗
        } catch (IllegalStateException e) {
            sea = e.getMessage();//throwerの例外メッセージを表示
        }
        log(sea); // your answer? =>oneman at showbase
    }
    //当たった

    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();//ここで例外発生
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? => クラス名:St7BasicExceptionThrower、メソッド名:oneman、40行目
    }
    //当たった

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");//Objectはすべてのスーパークラス、IllegalStateExceptionクラスでnew
        boolean sea = exp instanceof RuntimeException;//IllegalStateExceptionクラスはRuntimeExceptionクラスのサブクラス。クラスまたはサブクラスに該当すればtrueを返す。
        log(sea); // your answer? => true
    }
    //当たった

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;//Exception -> RuntimeException ->IllegalStateExceptionの関係性
        log(sea); // your answer? => true
    }
    //当たった。孫でも同様。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false、Errorは別
    }
    // [1on1でのふぉろー] ErrorとExceptionの違いを話した。
    // 量子もつれの話が意外にわかりやすかったかも (思いつき)。
    // 中断イベントとその中身を概念的にしっかり理解することがエラーハンドリングに大切。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true 、 Exception の親なので
    }

    // done jflute 次回1on1ここから (2024/12/20)
    // [1on1でのふぉろー] Throwableがインターフェースっぽい名前話
    // あと、Java標準クラスでis-aじゃない継承ある話、java.sql.Date
    // それを綺麗に解決している HashSet のお話。
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;//親 of 子はfalse、子 of　親（それ以上）はtrue
        log(sea); // your answer? => false
    }
    //当たった

    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";//trueならnull、falseなら"oneman"返す
            String lowerCase = land.toLowerCase();//小文字変換しようとしたけどlandがnull
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);//logクラスに例外クラスを入れるとstacktraceを返す
        }
        // your answer? => stacktrace
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        // [1on1でのふぉろー] 最近のJavaだと、NullPointerのメッセージでどの変数がnullだったか教えてくれる話
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";//!１個と一緒。falseなので、oneman
            String piari = !!!sea.equals("mystic") ? "plaza" : null;//こっちはnull
            int sum = land.length() + piari.length();//piari.length()でnullぽ
            log(sum);//出ない
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => ぬるぽのstacktrace
    }

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int land_length = land.length();
            int piari_lengthe = piari.length();
            log(land_length + piari_lengthe);
        } catch (NullPointerException e) {
            log(e);
        }
    }
    //文字数変換をそれぞれの行で行うことで特定

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        File file = new File(".");
        try {
            String sea = file.getCanonicalPath();
            log(sea);
        } catch (IOException e) {
            // TODO done tanaryo メッセージとスタックトレース出すときは、log(e)だけでOK by jflute (2024/12/26)
            // log()メソッドの中で、eが来たら、両方出すように作られているので。(これは知らなくて当然)
            log(e);
        }
        // [1on1でのふぉろー] チェック例外と非チェック例外のお話少し。
        // 理想的にはチェック例外は、catchを促してくれる良い文法ではあるが...実際使われていない(ことがほとんど)
        // ピンポイントで本当にcatchを促したい場面で使うにはいいけど、汎用的に使うとどうでもいいときもコンパイルエラーになりがち。
        // ...(その他あれこれ話して)
        // なので、javatryでも1問しか用意してない。
    }
    //うまくいっている気がする。エラーを起こしたい。
    // [1on1でのふぉろー] まあなかなかIOExceptionは起こせないので...自分でthrowしてcatchさせちゃってください。
    //  e.g. throw new IOException("aaaaaaaa");

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();//IllegalStateExceptionが生じた原因の例外なのでIllegalArgumentExceptionのことを指す
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? => Failed to call the third help method: symbol=-1
            log(land); // your answer? => IllegalArgumentException
            log(e); // your answer? =>最後の例外クラス名はNumberFormatException
        }
    }

    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe;//1
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {//secondで生じたスローをキャッチ
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol;//0
            symbol--;//-1
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);//-1でthrow
            }
        } catch (NumberFormatException e) {//不正な文字列を数値型に変換しようとすると生じる例外。thirdで生じた例外をキャッチ
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari");//ここの処理でthrow
        }
    }

    // [1on1でのふぉろー] 質問: 元の例外を引き継ぐ必要性は？
    // => 根本原因を知るため by tanaryo
    // 例外が例外を保持できる理由について話した。

    // TODO done tanaryo [読み物課題] エラーメッセージ読め読め大合唱 by jflute (2024/12/26)
    // https://jflute.hatenadiary.jp/entry/20130522/errorsinging

    // TODO jflute 次回ここから1on1 (2024/12/26)
    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();//SupercarClientクラスをnewして、buySupercarメソッドを実行。.buySupercar()でエラー起きてる
            fail("always exception but none");
        } catch (RuntimeException e) {//ざっくり例外だよーと言ってる
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            //例外メッセージから調査する
            //例外発生箇所は"The kawaii face is already unsupported so we cannot make it."
            //SpecialScrewManufacturerクラスの２９行目で発生
            //かわいい顔の（模様をした？）特別なねじはサポート外なので作れない
            //顧客がスーパーカーを注文するときに、特別なねじの仕様を要求したら、そのねじはサポート外なので作れないとネジ製造業者に言われた？
            //どこかでねじを指定しているのか
            //catogoryでpiariを指定したときに、それを元にハンドルIdが指定され、それを元にネジの仕様が決定している
            //
            //状況：顧客は特定のハンドルの仕様を指定して、スーパーカーを購入しようとしたがスーパーカーを購入できなかった。
            //根本原因：指定した特定のハンドルで使用されるねじがねじ製造業者でサポート外であり、そのねじを製造できなかった。
            // _/_/_/_/_/_/_/_/_/_/
            // TODO tanaryo [いいね] 根本原因という概念自体をわかってること自体が素晴らしい。 by jflute (2024/12/31)
            // この時点では、例外メッセージだけではなかなか全体のストーリーを読み取りにくかったかと思います。
            // 次のエクササイズでそれを完全しましょう。
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        // TODO tanaryo 例外の翻訳は段階的でとても良いと思います。ただ、途中のレイヤーが持ってる情報が全く出てないですね by jflute (2024/12/31)
        // "Failed to make steering wheel" とか、なんかのIDとか実行時の情報があると良いなと。
        // TODO tanaryo [読み物課題] 例外メッセージ、敬語で満足でもロスロスパターン by jflute (2024/12/31)
        // https://jflute.hatenadiary.jp/entry/20170804/explossloss
        // ↑一度、紹介したか忘れてしまいましたが、こういうことですね。
        //
        // TODO tanaryo これだけ丁寧に例外翻訳しているので、逆に全部がStateExceptionだとわかりにくさもあるので... by jflute (2024/12/31)
        // 既存の ScrewCannotMakeBySpecException みたいに、それぞれのレイヤーの例外も固有のものにしてみましょう。
        try {
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (IllegalStateException e) {
            log("Failed to buy supercar", e);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
            //causeを例外クラスに追加した
            // TODO tanaryo いいですね！せっかくなので、例外クラスのクラスJavaDocにauthor追加を(^^ by jflute (2024/12/31)
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            // TODO tanaryo [いいね] もうたなりょーさんであれば、既存コードがどれだけヤバかったかわかったことでしょう(^^ by jflute (2024/12/31)
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() {
        if (true) { // simulate something illegal
            String importantValue = "dummy"; // important to debug
            throw new IllegalStateException("something illegal: importantValue=" + importantValue);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        //Error：動作の継続が期待できない異常
        //Exception:動作の継続が期待できる異常
        //
        // _/_/_/_/_/_/_/_/_/_/
    }
}

// TODO tanaryo [いいね] 期待できるという表現良いですね。 by jflute (2024/12/31)
// 実際には継続できないこともありますが可能性があるということですね。

//例外について
//Throwableクラス：throw文で投げることができて、catch節で受け止められるクラス
//--- Error：動作の継続が期待できない
//--- Exception:動作の継続が期待できる

//コンパイラによってチェックされる例外（DB操作やネットワークといった外部要因による例外？）
//RuntimeException以外のException

//コンパイラによってチェックされない例外
//Error（いつどこでも起こりうる）とRuntimeException系（開発者の考えた仕様により生じる例外？）
//RuntimeException系に関して、チェックされないのでtrycatchなどは必須ではない。ただなにも対応してないと、例外発生時に原因を辿りづらく保守運用の観点で良くない。
