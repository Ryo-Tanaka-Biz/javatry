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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author tanaryo
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? =>over
    }
    //当たった。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());//returnでoverを返しているから()の中身はover。メソッド終了後はmystic
        runnableSomething();
        log(sea); // your answer? => mysmys
    }
    //当たった。
    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");//mysmys
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage(); //St4MutableStageクラスをインスタンス化
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);//mutableインスタンスのstagenameにmysticをセット
        if (!land) {
            sea = sea + mutable.getStageName().length();//length()メソッドは文字列の文字数をint型で返す。stageNameはmysticで第2項は6。
        }
        log(sea); // your answer? => 910
    }
    //当たった。

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;//右辺は0
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {//100回繰り返す
            goToPark();
        }
        ++sea;//罠
        sea = inParkCount;//右辺は100
        log(sea); // your answer? => 100
    }
    //当たった。罠を見破れた。

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }
    //罠

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "\"");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }
    private boolean availableLogging = true;

    // TODO tanaryo publicじゃなくてprivateでいいかなと (自分のクラスからしか呼ばれてないので) by jflute (2024/07/25)
    // [memo] メソッドは、デフォルトprivateな感覚で、必要に応じて公開範囲を広げていくって感じでOK
    public String replaceAwithB(String string){
        return string.replace("A","B");
    }
    public String replaceCwithB(String string){
        return string.replace("C","B");
    }

    // [memo] quoteという引数の名前、とても良い！ by jflute
    public String quote(String string,String quote){
        // [memo] これはこれで良い発想なので良いです。by jflute
        // 一方で、本気でやるならquoteBySingle(), quoteByDouble()とかでメソッドで絞る
        if (quote != "'" && quote != "\""){
            availableLogging = false;
            log("quoteメソッドの第二引数には引用符を使用してください");
        }
        return quote + string + quote;
    }
    public boolean isAvailableLogging(){
        return availableLogging;
    }
    public void showSea(String sea){
        log(sea);
    }
    // TODO tanaryo 変な空行空いてる (体裁も整えましょう) by jflute (2024/07/25)


}
    //予想通り'BBB'が出力された。quoteメソッドで第二引数が引用符ではない場合の処理も含めた。
