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
//TODO tanaka org.docksidestage.javatry.basicパッケージを定義

import java.math.BigDecimal;
//TODO tanaka java.math.BigDecimalクラスをインポート。以降はBigDecimalで呼び出せる。

import org.docksidestage.unit.PlainTestCase;
//TODO tanaka org.docksidestage.unit.PlainTestCaseクラスをインポート。以降はBigDecimalで呼び出せる。

// done tanaryo javatryではJavaDocのauthorのyour_name_hereのところ、ご自分の名前(アカウント名)でお願いします by jflute (2024/07/02)
// 一応、↓こういうポリシーがあります。ご協力お願いします。
// 3. 最低限のクラスJavaDoc | ハンズオンのコーディングポリシー
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step01VariableTest extends PlainTestCase {
//TODO tanaka  Step01VariableTestクラスを定義。PlainTestCaseクラスを継承。アクセス修飾子publicをつけることで他パッケージからも利用可能に。
    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }
    //TODO tanaka test_variable_basicメソッドを定義。引数はなし。voidにより戻り値のないメソッドの型に。
    //TODO tanaka Stringは文字列を表す型。
    //TODO tanaka logメソッドはPlainTestCase内で定義。protected修飾子をつけて定義しているため、PlainTestCaseクラスを継承したStep01VariableTestクラスでも利用可能。
    // TODO tanaka [いいね]おおぉ、そこまで読み込んでるの素晴らしいですね！まさしくそのとおりです by jflute (2024/07/02)

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8null:mai
    }
    //TODO tanaka 文字列との結合の場合、数値、nullは文字列("")に変換される？
    // done tanaryo [ふぉろー] ↑そのとおりです。型が違っても基本的に文字列に引っ張られます by jflute (2024/07/02)
    //TODO tanaka Integlerはクラス型で整数値を保持する。Integler.~~でメソッドを呼び出せる。またnullを代入できる。intはプリミティブ型で整数値を保持し、nullは代入できない。
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
    }
    // done tanaka 変数に変数を代入することができる。今回の場合、sea="oneman"？また59行目に sea = land;を追加すればoneman's dreamsとなる？
    // done tanaryo [ふぉろー]その通りです。landだけワンマンズドリームになってますから、landの値をseaに入れればそうなります by jflute (2024/07/02)
    // done tanaka 型を定義したら、2回目以降変数名のみで良い？
    // done tanaryo [ふぉろー]そういう解釈になります。厳密には、変数の宣言と代入というのは別の行為で... by jflute (2024/07/02)
    //  String sea; // これは変数の宣言 (変数の型宣言)
    //  sea = "mystic"; // これは代入
    // というように分かれているものですが、変数の宣言と同時に代入ができるようになっているので、
    //  String sea = "mystic";
    // というように一行(一ステートメント)で書くことができます。
    // "2回目以降変数名のみで良い" というのは、単に「代入だけ」をもう一回やるということですね。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
    }
    // done tanaka 変数に変数を代入できるが、seaが保持するのはlandではなく415?
    // done tanaryo [ふぉろー]いい視点ですね。sea = land と書いても、seaにlandを入れるわけではなく... by jflute (2024/07/02)
    // 「seaの中にlandの中に入ってるものを入れる」というニュアンスになりますので、seaとlandで何か関係性を持つわけではありません。
    // しかも、コンピューターの世界なので、landの中のものを移動してlandが空っぽになるわけでもなく、コピーされるような感覚ですね。
    // done tanaka land++はlandに1を足す
    // done tanaryo [ふぉろー]↑yes, インクリメントと呼びます。land = land + 1; と同じことやっています by jflute (2024/07/02)

    // done jflute 1on1にて変数の概念のフォロー予定 (2024/07/02)
    // [memo] インスタンスの話もした (2024/07/04)
    
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        // add():
        // Returns a BigDecimal whose value is (this + augend),
        // and whose scale is max(this.scale(), augend.scale()).
        // Returns: this + augend
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 417
    }
    //TODO tanaka BigDecimalは小数点以下を正確に扱うクラス。new演算子でクラスをインスタンス化。基本構文はクラス名 インスタンス名 = new コンストラクタ名([引数])。
    //TODO tanaka addメソッドはBigDecimalファイルで定義。
    // done tanaka 87行目で1を足している？88行目も1を足している？
    // done tanaryo ↑行番号はコメント入れるとズレちゃいますね(^^ by jflute (2024/07/02)
    
    // done jflute 1on1にてImmutableのフォロー予定 (2024/07/02)
    // Immutable/Mutableの違い: BigDecimalはImmutable

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;
    //TODO tanaka 変数を4つ定義

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null
    }
    //TODO tanaka 変数instanceBroadwayに値を代入していないためnullとなる
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0
    }
    //TODO tanaka 変数instanceDocksideに値を代入しておらず、かつintはプリミティブ型でnullを入れることはできないため0となる

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
    }
    //TODO tanaka Integerはクラス型でnullを入れることができるためnullとなる
    // done tanaryo [ほそく]↑クラス型をオブジェクト型とか参照型と読んだりもします by jflute (2024/07/02)
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|burn
    }
    //TODO tanaka メソッドや変数を後に定義しても問題なし。
    // done tanaka　なぜburnではなく、magician?
    // done jflute 1on1にて変数の概念をフォロー予定 (2024/07/02)
    // 質問: インスタンス化？ => 変数自体においてインスタンス化という言葉は使わないけど...
    // 引数の変数が作り上げられるのは、メソッドが呼ばれた瞬間に作られる(メモリ上に確保される)

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor416
    }
    //TODO tanaka 416いらなかった。seaはあくまで結合元でsea=harbor416とはなっていない。
    //TODo tanaka helpMethodArgumentImmutableMethodcallメソッドにlog(sea.concat(landStr));を追加したところ、harbor416が表示された

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }
    //TODO tanaka String.valueOfでstring型に変換
    //TODO tanaka concatメソッドは、引数で指定した文字列を「結合対象の文字列の最後に結合」するためのメソッド。
    //TODO tanaka sea.concat(landStr)は文字列seaの最後に文字列landstrを結合

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        //TODO tanaka StringBuilder クラスのインスタンスを作成
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416
    }
    //TODO tanaka StringBuilderで複数の文字列や他の値を連結し、最後に文字列として出力する
    //TODO tanaka 当たった。
    //TODO tanaka concatメソッドは元になる文字列自体を変更させるものではなく、「結合した新しいインスタンスを生む」
    //TODO tanaka appendメソッドはインスタンスを生成させず、元になる文字列自体を変更させる。そのためもともとインスタンスが用意されている状態で使う。
    //TODO tanaka appendメソッドではStringBuilderクラス、StringBufferクラスのいずれかを用いてインスタンスを作成。
    // done tanaryo ↑[いいね]素晴らしい考察ですね。Stringのcancat()とStringBuilderのappend()の違い着目されたのGoodです by jflute (2024/07/02)
    // done jflute 1on1にてインスタンスの概念についてフォロー予定 (2024/07/02)

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
    }
    //TODO tanaka 当たったけど、よくわからない。下のメソッドに log(sea);を追加したらharbor416と表示された。
    // done tanaryo [いいね]理解のメモありがとうございます。これは変数やインスタンス周りの話なので1on1で一気に説明しますね by jflute (2024/07/02)
    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        //TODO tanaka　自作クラスでto.stringメソッドを用いている。
        sea = new StringBuilder(seaStr).append(land);
        //TODO tanaka
    }
    //TODO tanaka toStringは文字列に変換するメソッド。nullを変換元に入れるとエラーが発生する。ここがvalueofメソッドとの違い
    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    int piari;
    public void test_variable_writing() {
        String sea = "mystic";
        Integer land = null;
        log(sea +","+land+","+piari);
        // define variables here
    }
    //TODO tanaka mystic,null,0と表示された
    // done tanaryo 厳密には、seaという変数の値を書き換えてしまっていますので、sea変数はそのままでlogに出せるといいですね by jflute (2024/07/02)
    // hint: 表示するためだけの新しい変数を作ってもいいですし、log()の引数部分のインラインで書いてもいいですし...
    // done tanaryo 直接「sea +","+land+","+piari」を代入

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * (メソッド終了時のlog(sea)とlog(star)の中身は？):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    // TODO tanaryo ↑log(star)が残骸？ by jflute (2024/07/11)
    int sea =15;
    public void test_variable_yourExercise() {
        int sea = this.sea;
        log(sea);
        // 15が出る
        // TODO tanaka インスタンス変数とローカル変数が同一の場合、インスタンス変数にアクセスするにはthis.をつける。
        // TODo tanaka this.をつけていないためここでは自身を参照代入している状態
        // done tanakryo ↑状況合ってますね！逆にこのコンパイルエラーをちゃんと直すエクササイズというのも面白いかも(^^ by jflute (2024/07/02)
    }
}
