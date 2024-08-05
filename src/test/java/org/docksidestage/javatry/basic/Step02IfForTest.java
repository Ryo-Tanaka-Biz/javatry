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
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }
    //MEMO tanaka 当たった。これは簡単。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }
    //MEMO tanaryo 当たった。(sea >= 903)も満たしているが、(sea >= 904)を満たさない場合に適用されることを考えると7
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        //memo tanaryo boolean型はtrueとfalseの2値をとる
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
    }
    // done jflute 次回コードリーディングの補足 (2024/07/11)
    // 目的があってコードを読むので、読まなくていいところを読まないようにする努力を
    // 漠然読み (全体構造の把握) をしてから、フォーカス当てて飛び飛び読みしていく
    //memo tanaryo 当たった。(!land)はlandがfalseの場合、(land)はlandがtrueの場合という意味。seaの値も正しく追えてた。最後のif文抜いたらseaは7になる。
    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? =>dockside
    }
    //memo tanaryo 当たった。prepareStageListは下の方で定義されている。要素数は4。
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
    }
    //memo tanaryo 拡張for文。当たった。ローカル変数stageに対し、stagelistのリストを1つずつ代入していく。最後はmagiclampのためseaにはmagiclampが代入される。
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
    }
    //memo tanaryo 当たった。continueはそれ以降の処理をスキップする。breakはループを抜ける。
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? =>dockside
    }
    //memo tanaryo 当たった。
    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> stageList = prepareStageList();
        List<String> a_list = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                a_list.add(stage);
            }
            a_list  = null;
        }
        log(a_list);
    }
    //memo tanaryo できた。[broadway, hangar, magiclamp]と表示された。
    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    // done tanaryo unusedがあります。IDEの警告できるだけ見ていきましょう by jflute (2024/08/01)
    public void test_iffor_refactor_foreach_to_forEach() {
        /*
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        */
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (stage.startsWith("br")) {
                return;
            }
            // [memo] toString()することで、内部の配列のコピー処理が走ってしまうので、直のindexOf()の方が速い by jflute
            //if (sb.indexOf("ga") >= 0) {
            //    return;
            //}
            if (sb.toString().contains("ga")) {
                return;
            }
            sb.setLength(0);
            sb.append(stage);
        });
        log(sb.toString()); // hangar
    }
    //done tanaka 同様の実行結果は得られた。froEach文ではcontinueはreturnで代替できる。breakは基本的に代替できない。forEach内で扱える変数はインスタンス変数?
    // done jflute そもそもforEach()とはなんぞや？ (2024/07/11)
    // done jflute もちょい時間あるときに、forEach()の中で外側のローカル変数への代入ができないのなぜ話する (2024/07/11)
    // done tanaryo もし、stageListにgaが一個も含まれてないケースだったら、同じ結果になりますか？ by jflute (2024/07/18)
    //  ->同じ結果にはならない。何も返されない。
    //  ->gaが含まれている場合、3つ目のif文にて、sbにhangarが追加。次のメソッド実行時に2つ目のif文でreturnされる。
    //  ->gaが含まれていない場合、sbには何も追加されず、forEach文が終了。

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * 出力される座標は？:
     *
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise_1() {
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if(y==2){
                    break;
                }
                log(x,y);
            }
        }
    }
    //memo tanaryo breakは直前(引数y)の繰り返し処理を抜ける。そのためy=2,3,4,を含む座標は出力されない。

    public void test_iffor_yourExercise_2() {
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if(y==2){
                    continue;
                }
                log(x,y);
            }
        }
    }
    //memo tanaryo continueは以降の処理をスキップし、次の要素のループ処理へ移る。そのためy=2を含む座標は出力されない。


    public void test_iffor_yourExercise_3() {
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                if(y==2){
                    return;
                }
                log(x,y);
            }
        }
    }
    //memo tanaryo returnはメソッドを抜ける。(0,0),(0,1),(0,2)ときて(0,2)が出力される前に終了する。そのため(0,0),(0,1)のみ。
    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
