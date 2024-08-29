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
    // done tanaryo static finalの定数は、スネークケースで統一することが多い by jflute (2024/08/15)
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;
    private static final int FOUR_DAY_COUNT = 4;
    private static final int FOUR_DAY_PRICE = 22400;

    // TODO tanaryo TicketBoothは同じboothから複数の人が買うので、night買ったらみんなnightチェック走る by jflute (2024/08/15)
    // さらに、staticになっているので...複数のTicketBoothで共有されている変数になっている。
    // 一つのTicketBoothでnightが買われたら、世界中のTicketBoothのTicketでnightチェックが走る。

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
    // done tanaryo javadocでreturnを付けましょう by jflute (2024/08/05)
    // done tanaryo @paramのhandedMoneyが全角空白で別の名前になってしまっている "handedMoney　The" by jflute (2024/08/15)
    // done tanaryo @return, 戻り値はTicketであることがメソッド定義からわかるのでjavadocで明示不要 by jflute (2024/08/15)
    //  引数は複数ある => 特定しないといけない
    //  戻り値は一個しかない => 特定する必要はない
    // (ちなみに、引数も別に型をjavadocで明示しているわけじゃない)
    // javadocをパースする側の視点で考えると、必要不要がしっくり来る。
    // TODO done tanaryo 1度指摘されたことは、他で似た箇所がないかどうか確認する習慣を by jflute (2024/08/22)
    /**
     * Buy one-day passport, method for park guest.
     *
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return 入場チケット (NotNull)
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
    // done tanaryo @returnにも (NotNull) というマークを付けるようにお願いします by jflute (2024/08/15)
    // もし、nullを許す戻り値であれば (NullAllowed)
    // 呼び出し側の知りたい情報の代表格として、引数や戻り値がnullがあり得るかどうか？というのがある
    // done tanaryo ここは@returnは入場チケットというよりかは... by jflute (2024/08/15)
    // TODO done tanaryo "チケットとお釣りなどを渡す" とか "e.g. チケット, お釣り" とか項目の個数を断定しない方が長持ちする by jflute (2024/08/22)
    /**
     * 2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
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

    /**
     * 夜限定2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < NIGHT_ONLY_TWO_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + NIGHT_ONLY_TWO_DAY_PRICE;
        } else { // first purchase
            salesProceeds = NIGHT_ONLY_TWO_DAY_PRICE;
        }
        // done tanaryo [いいね] 変数でお釣りであることを示してるのがGood by jflute (2024/08/01)
        // (ただし個人差がある: ぼくも強調したいとき、しなくてもいいとき、ケースバイケースではある)
        int change = handedMoney - NIGHT_ONLY_TWO_DAY_PRICE;
        return new TicketBuyResult(NIGHT_ONLY_TWO_DAY_PRICE, change, TWO_DAY_COUNT);
    }

    // [ふぉろー] OneDayだけchange戻さない要件になってるけど、これはいいのか？と考えることは大事
    // done tanryo [読み物課題] SIとスタートアップの違いを知ろう by jflute (2024/08/05)
    // https://jflute.hatenadiary.jp/entry/20151007/sista

    /**
     * 4Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < FOUR_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + FOUR_DAY_PRICE;
        } else { // first purchase
            salesProceeds = FOUR_DAY_PRICE;
        }
        int change = handedMoney - FOUR_DAY_PRICE;
        return new TicketBuyResult(FOUR_DAY_PRICE, change, FOUR_DAY_COUNT);
    }

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
