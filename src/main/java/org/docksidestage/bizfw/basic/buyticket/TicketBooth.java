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

    // done tanaryo static finalの定数は、スネークケースで統一することが多い by jflute (2024/08/15)

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
    // done tanaryo 1度指摘されたことは、他で似た箇所がないかどうか確認する習慣を by jflute (2024/08/22)
    /**
     * Buy one-day passport, method for park guest.
     *
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @return 入場チケット (NotNull)
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // TODO tanaryo 変数もticketTypeとかぼかしちゃった方が以降の処理がoneDay依存してないことが明示できるかなと by jflute (2024/08/29)
        TicketType oneAllDay= TicketType.ONE_ALL_DAY;
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        // TODO tanaryo priceは変数に出してもいいんじゃないかなと (ショートカットでやってみて) by jflute (2024/08/29)
        // TODO tanaryo [読み物課題] リファクタリングは思考のツール by jflute (2024/08/29)
        // https://jflute.hatenadiary.jp/entry/20121202/1354442627
        if (handedMoney < oneAllDay.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + oneAllDay.getPrice();
        } else { // first purchase
            salesProceeds = oneAllDay.getPrice();
        }
        return new Ticket(oneAllDay.getPrice(),oneAllDay.getDayCount(), oneAllDay.getStartTime(), oneAllDay.getEndTime());
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
    // done tanaryo "チケットとお釣りなどを渡す" とか "e.g. チケット, お釣り" とか項目の個数を断定しない方が長持ちする by jflute (2024/08/22)
    /**
     * 2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        TicketType twoAllDay= TicketType.TWO_ALL_DAY;
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < twoAllDay.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + twoAllDay.getPrice();
        } else { // first purchase
            salesProceeds = twoAllDay.getPrice();
        }
        // done tanaryo [いいね] 変数でお釣りであることを示してるのがGood by jflute (2024/08/01)
        // (ただし個人差がある: ぼくも強調したいとき、しなくてもいいとき、ケースバイケースではある)
        int change = handedMoney - twoAllDay.getPrice();
        Ticket ticket = new Ticket(twoAllDay.getPrice(),twoAllDay.getDayCount(),twoAllDay.getStartTime(),twoAllDay.getEndTime());
        return new TicketBuyResult(change, ticket);
    }

    /**
     * 夜限定2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        TicketType twoNightDay= TicketType.TWO_NIGHT_DAY;
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < twoNightDay.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + twoNightDay.getPrice();
        } else { // first purchase
            salesProceeds = twoNightDay.getPrice();
        }
        // done tanaryo [いいね] 変数でお釣りであることを示してるのがGood by jflute (2024/08/01)
        // (ただし個人差がある: ぼくも強調したいとき、しなくてもいいとき、ケースバイケースではある)
        int change = handedMoney - twoNightDay.getPrice();
        Ticket ticket = new Ticket(twoNightDay.getPrice(),twoNightDay.getDayCount(),twoNightDay.getStartTime(), twoNightDay.getEndTime());
        return new TicketBuyResult(change, ticket);
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
        TicketType fourAllDay= TicketType.FOUR_ALL_DAY;
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < fourAllDay.getPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + fourAllDay.getPrice();
        } else { // first purchase
            salesProceeds = fourAllDay.getPrice();
        }
        int change = handedMoney - fourAllDay.getPrice();
        Ticket ticket = new Ticket(fourAllDay.getPrice(),fourAllDay.getDayCount(),fourAllDay.getStartTime(),fourAllDay.getEndTime());
        return new TicketBuyResult(change, ticket);
    }
    
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // [ふぉろー] Ticketのコンストラクターが引数4つで多いかな？という質問に対して... by jflute
    // 仕方ない時は仕方ないので、引数指定間違いに気をつけながら指定するしかない。
    // 一方で、今回はTicketTypeの情報を渡しているだけなので、TicketType自体を渡していいんじゃないかと。
    // Ticket側のコンストラクターで振り分けるってすればマッピング一箇所になってバグの温床が減ることにつながる。
    // 一方で一方で、TicketType自体を保持しちゃってもいいかと。(dayCountだけ可変なのでSNAPSHOTを保持)
    // _/_/_/_/_/_/_/_/_/_/

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
