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
package org.docksidestage.bizfw.basic.buyticket;

// done tanaryo javadocにauthorの追加をお願いします、せっかくなので自分の名前を刻んでください by jflute (2024/08/01)
/**
 * @author jflute
 * @author tanaryo
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // [memo] 変更する時に見やすいように、一元管理するために、定数化している by jflute
    // これは原始的なやり方ではあるけど、他にやりようがない場合はこのやり方がよく使われる。
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_COUNT = 1;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_COUNT = 2;
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private Integer quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // TODO tanaryo javadocでreturnを付けましょう by jflute (2024/08/05)
    /**
     * Buy one-day passport, method for park guest.
     *
     * @param handedMoney　The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return Ticket 入場チケット //Ticketインスタンスが認識されていない？
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + ONE_DAY_PRICE;
        } else { // first purchase
            salesProceeds = ONE_DAY_PRICE;
        }
        return new Ticket(ONE_DAY_PRICE,ONE_DAY_COUNT);
    }

    // [memo] お釣りにnullという概念もないので、intで問題ない (一方で、Integerでも問題はないけど)
    // 仮にすでにお釣りのプログラムがそこら中にあって、nullの概念もあってIntegerで広く使われてるなら合わせるとか。
    // done tanaryo javadoc, せめてpublicの重要だと思われるメソッドには追加お願いします (日本語でOK) by jflute (2024/08/01)

    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // [ふぉろー] TicketSoldOutExceptionとかTicketShortMoneyExceptionとかは、
    // システムのバグによるシステム例外ではなく、業務的にあり得るレアケースなので業務例外と言う。
    // _/_/_/_/_/_/_/_/_/_/
    
    // done tanaryo javadocの場合は//なくて良いです by jflute (2024/08/05)
    /**
     * 2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return TicketBuyResult 入場チケット
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < TWO_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + TWO_DAY_PRICE;
        } else { // first purchase
            salesProceeds = TWO_DAY_PRICE;
        }
        // done tanaryo [いいね] 変数でお釣りであることを示してるのがGood by jflute (2024/08/01)
        // (ただし個人差がある: ぼくも強調したいとき、しなくてもいいとき、ケースバイケースではある)
        int change = handedMoney - TWO_DAY_PRICE;
        return new TicketBuyResult(TWO_DAY_PRICE, change, TWO_DAY_COUNT);
    }

    // [ふぉろー] OneDayだけchange戻さない要件になってるけど、これはいいのか？と考えることは大事
    // done tanryo [読み物課題] SIとスタートアップの違いを知ろう by jflute (2024/08/05)
    // https://jflute.hatenadiary.jp/entry/20151007/sista

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
