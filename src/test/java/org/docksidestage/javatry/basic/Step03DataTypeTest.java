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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic"; //参照型、メモリ番地はスタック領域、 "mystic"はヒープ領域に格納
        Integer land = 416; //参照型。416はヒープ領域に格納
        LocalDate piari = LocalDate.of(2001, 9, 4);//イミュータブル。参照型
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);//イミュータブル。参照型
        Boolean dstore = true; //参照型
        BigDecimal amba = new BigDecimal("9.4");//参照型。イミュータブル

        piari = piari.plusDays(1);//done tanaryo piariのdaysに1を加えたインスタンスを新たに生成？その変数名がpiari?
        // [1on1フォロー] yes, そして、piariの指し示すインスタンスがswitchされているというニュアンス
        land = piari.getYear();//done tanaryo piariのyaer取得。ここではインスタンスを新たに生成？
        // [1on1フォロー] int自体は新たにnewされるわけじゃないが、Integerに変換されるときにjava内部でIntegerを生成している
        bonvo = bonvo.plusMonths(1); //右辺のmonthは10
        land = bonvo.getMonthValue();//10
        land--;//9
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));//Bigdecimal型で18.4
            sea = String.valueOf(addedDecimal);//Bigdecimal型をストリング型に変更
        }
        log(sea); // your answer? => 18.4
    }
    //memo tanaryo 当たった。ここでは18.4は文字列。

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        //データ型は扱える値の範囲が異なる
        byte sea = 127; // max　
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';//done tanaryo boolean型でもtrueとfalse以外を代入できる？->右辺は比較演算子==を用いていて、trueかfalse
        // [1on1フォロー] miraco == 'a'自体が判定結果となるのでboolean
        if (dohotel && dstore >= piari) {
            //論理積。どちらもtrueの場合にtrue
            bonvo = sea;//127//拡大変換
            land = (short) bonvo;//縮小変換。longからshortへ
            bonvo = piari;//1//拡大変換
            sea = (byte) land;//縮小変換。shortからlandへ
            if (amba == 2.3D) {
                sea = (byte) amba;//縮小変換。doubleからbyteへ
            }
        }
        if ((int) dstore > piari) {
            sea = 0;
        }
        log(sea); // your answer? => ？？？
    }

    //TODO tanaryo 縮小変換の計算方法がわからない..

    //done tanaryo [読み物課題]プリミティブ型とラッパー型 by jflute (2024/07/18)
    // https://dbflute.seasar.org/ja/manual/topic/programming/java/beginners.html#primitivewrapper
    
    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        //St3ImmutableStageクラスをインスタンス化。stageNameにhangarをセット
        String sea = stage.getStageName();
        //stageのgetStageNameメソッドの実行結果をseaに代入。メソッドはstageNameを返す。
        log(sea); // your answer? => hangar
    }
    //当たった

    private static class St3ImmutableStage {

        private final String stageName;//stagenameはインスタンス変数
        //done tanaryo privateにする意味は？
        // [1on1フォロー] オブジェクト指向のカプセル化、まあ見せなくていいものは見せない方が間違いが生まれない
        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }
        //St3ImmutableStageメソッド定義。仮引数stagename。左辺のstagenameはインスタンス変数。仮引数と同名のため、thisをつけている。右辺は仮引数。
        public String getStageName() {
            return stageName;
        }
        //getStageNameメソッドを定義。引数はなし。インスタンス変数stagenameを返す。
    }
}
