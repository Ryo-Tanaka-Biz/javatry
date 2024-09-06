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

import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう) <br>
 * 
 * If ambiguous requirement exists, you can determine specification that seems appropriate. <br>
 * (要件が曖昧なところがあれば、適切だと思われる仕様を決めても良いです)
 * 
 * @author jflute
 * @author tanaryo
 */
public class Step05ClassTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
    }
    //当たった。quantityの初期値は10で1枚購入で9枚になる。buyOneDayPassportは1枚ずつ購入するメソッド。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 10000->7400
    }
    //当たった。salesProceedsで売上を管理。正しい売上は10000ではなく7400?
    //修正後7400に

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => null
    }
    //当たった。salesProceedsの初期値はnull。buyOneDayPassportメソッドで売上が加算されていく。

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => 9->10
    }
    //当たった。お金は足りていないけど、quantityは1引かれてしまう。
    //修正後10になった。

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
        }
        return booth.getQuantity();
    }
    //tyr-catch文

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here
    }
    //うまくいった。--quantityをお金足りない時の例外処理の後に移動させた。

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here
    }

    //7400になった。handedmoney->ONE_DAY_PRICEにした
    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        TicketBooth booth = new TicketBooth();
        int money = 14000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(money);
        int change = buyResult.getChange();
        Integer sea = booth.getSalesProceeds() + change;
        log(sea); // should be same as money ->14000
        // and show two-day passport quantity here
        log(booth.getQuantity());
    }
    //quantityは日数にかかわらず共通？
    // done tanaryo [いいね] ちゃんと迷ったことが素晴らしい by jflute (2024/08/01)
    //  共有パターン: ブースに白紙チケットが10枚あって、売るときに1dayとか書いて売る
    //  独立パターン: ブースに1dayチケット2dayチケットが別々に10枚あって、売るときそのまま売る
    // 独立は実装が難しくなるので、最初は共有でやってもいいかもと by jflute

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        // TODO jflute 次回1on1でフォロー (2024/08/15)
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        log(oneDayPassport.isAlreadyIn()); // should be false
        oneDayPassport.doInPark();
        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    //うまくいった。displayPriceのイメージが湧かない。価格の書かれたチケットを渡すイメージ？

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 20000;
        TicketBuyResult buyResult = booth.buyTwoDayPassport(handedMoney);
        Ticket twoDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }
    //test_class_letsFix_makeMethod_twodayメソッドでエラー。中身を修正。20000のlogはでた。
    //TicketクラスとTicketBuyResultクラスで機能を分けている？

    /**
     * Now you can use only one in spite of two-day passport, so fix Ticket to be able to handle plural days. <br>
     * (TwoDayPassportなのに一回しか利用できません。複数日数に対応できるようにTicketを修正しましょう)
     */
    public void test_class_moreFix_usePluralDays() {
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyResult = booth.buyTwoDayPassport(20000);
        Ticket twoDayPassport = buyResult.getTicket();
        log(twoDayPassport.getDisplayPrice()); // should be same as two-day price
        log(twoDayPassport.isAlreadyIn()); // should be false
        twoDayPassport.doInPark(); //1回目入場
        log(twoDayPassport.isAlreadyIn()); // should be true
        twoDayPassport.doOutPark(); //1回目退場
        log(twoDayPassport.isAlreadyIn()); // should be false
        twoDayPassport.doInPark(); //2回目入場
        log(twoDayPassport.isAlreadyIn()); // should be true
        twoDayPassport.doOutPark(); //2回目退場
        log(twoDayPassport.isAlreadyIn()); // should be true
    }
    // done tanaryo [いいね] 自己レビュー素晴らしい by jflute (2024/08/05)
    //複数日数は連日？それとも間隔開けても問題ない？
    //今回は簡単のため、間隔開けても問題ないとする。
    //日数の制限も実装する。2日用なので、2回利用できるとする
    //alreadyInのTrue or Falseで利用できるかを判断。もう使えないチケットはalreadyInがTrueとする。->変数名と若干ずれていて、違和感

    /**
     * Accurately determine whether type of bought ticket is two-day passport or not by if-statemet. (fix Ticket classes if needed) <br>
     * (買ったチケットの種別がTwoDayPassportなのかどうかをif文で正確に判定してみましょう。(必要ならTicketクラスたちを修正))
     */
    public void test_class_moreFix_whetherTicketType() {
        TicketBooth booth = new TicketBooth();
        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        showTicketIfNeeds(oneDayPassport.getTicketType());
        TicketBuyResult buyResult = booth.buyTwoDayPassport(15000);
        Ticket twoDayPassport = buyResult.getTicket();
        showTicketIfNeeds(twoDayPassport.getTicketType());
    }

    //うまくいった。dayCountで判別。
    //showTicketIfNeedsメソッドでチケット種別を判定するとして引数はticketTypeが望ましい。ticketクラスでticketTypeを返すようにしよう。
    //というかticketクラスの引数は全てticketTypeのgetterなので、引数をticketTypeにしてコンストラクタでticketTypeクラスとgetterを定義すればよさそう。

    private void showTicketIfNeeds(TicketType ticketType) {
        // done tanaryo すでにnightonlyが増えて、このif文に紛れが発生してしまっている by jflute (2024/08/15)
        // TODO tanaryo 価格が変わった時に1箇所だけ修正するだけ済むように (絶対に修正漏れ起こすから) by jflute (2024/08/22)
        // (ふぉろー: TicketBooth.TWO_DAY_PRICEを使う方法はお話した。とはいえユニーク(一意)に識別したいところ)
        if (ticketType.equals(TicketType.TWO_ALL_DAY)) {
            log("two-day passport");
        } else {
            log("other");
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_four() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 30000;
        TicketBuyResult buyResult = booth.buyFourDayPassport(handedMoney);
        Ticket fourDayPassport = buyResult.getTicket();
        int change = buyResult.getChange();
        log(fourDayPassport.getDisplayPrice() + change); // should be same as money
    }
    //TwoDayPassportと同様に考えた

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400), which can be used at only night. <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう。夜しか使えないようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        TicketBooth booth = new TicketBooth();
        TicketBuyResult buyResult = booth.buyNightOnlyTwoDayPassport(8000);
        Ticket nightOnlyTwoDayPassport = buyResult.getTicket();
        log(nightOnlyTwoDayPassport.getDisplayPrice()); // should be same as two-day price
        log(nightOnlyTwoDayPassport.isAlreadyIn()); // should be false
        // done tanaryo [いいね] 現在日時を細工してテストしやすいようにするという発想が素晴らしい by jflute (2024/08/15)
        nightOnlyTwoDayPassport.doInPark(); //1回目入場
        
        // TODO tanaryo したら現在日時を細工して動作確認するテストを待っております by jflute (2024/08/22)
    }
    //夜しか使えないとは？具体的に時間を指定する必要あり？ディズニーだと17時から入園できるチケットがある
    //今回は17時以降使えるチケットとする
    //終日チケットではない場合、時間を確認する。時間確認はどうやる？
    //17時より前だと入場できない
    //現在時刻で判別しているが、テストケースとしては時間指定して入場できるかできないかテストしたい->setNowTimeメソッドで時間をセット

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // your confirmation code here
    }

    /**
     * Write intelligent comments on source code to the main code in buyticket package. <br>
     * (buyticketパッケージのクラスに、気の利いたコメントを追加してみましょう)
     */
    public void test_class_moreFix_yourSuperComments() {
        // your confirmation code here
    }
}
