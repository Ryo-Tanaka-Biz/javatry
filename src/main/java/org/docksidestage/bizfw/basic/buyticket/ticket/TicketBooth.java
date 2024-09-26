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
package org.docksidestage.bizfw.basic.buyticket.ticket;

// [ふぉろー] 質問が来てDBFluteとMySQLの絞り込みの仕組みの話をした by jflute (2024/09/06)
// INDEXの話も少しした。

// [ふぉろー] 質問が来て勉強の習慣についての話をした (2024/09/06)
// ガーがいけるときはガーで。ただ、つまみ食いにならないように、得たものをまとめるプロセスが必要。
// 将来はコツコツ能力が必要になるときはある。

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

    // done tanaryo TicketBoothは同じboothから複数の人が買うので、night買ったらみんなnightチェック走る by jflute (2024/08/15)
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
    // done tanaryo TicketBoothのインターフェース設計次第で... by jflute (2024/09/09)
    // メソッドを種別ごとに提供してメソッドを選んでもらう形式の方が呼びやすいと思うか、
    // そんなことは一個のメソッドで引数のenumで選択させるほうが呼びやすいと思うか、それ次第。
    // 一方で、既存コードが種別ごとメソッド方式だったので、変更するとなると多大な影響反映が想定されるので、
    // 通常はそこは変えないでリファクタリングをするというのが現実的ではある。(今回はたなりょうさんが頑張った)
    // 呼び出し先まで変えるリファクタリングというのは、ちょっと重たいリファクタリングになって、
    // テストとか動作確認とかの負担がそれなりに大きくなる可能性があるので、まずは内部に留めるのを第一段階にした方が良いとは思う。
    // [ふぉろー] もし、buyPassport()をprivateにするのであれば、doBuyPassport()というように先頭文字を変えて区別する方法がある。
    // TODO tanaryo 修行++: 在庫を分離する方式に変えると、さあどうなる by jflute (2024/09/09)

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
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException    When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        // done tanaryo 変数もticketTypeとかぼかしちゃった方が以降の処理がoneDay依存してないことが明示できるかなと by jflute (2024/08/29)
        return doBuyPassport(TicketType.ONE_ALL_DAY, handedMoney);
        // done tanaryo priceは変数に出してもいいんじゃないかなと (ショートカットでやってみて) by jflute (2024/08/29)
        // done tanaryo [読み物課題] リファクタリングは思考のツール by jflute (2024/08/29)
        // https://jflute.hatenadiary.jp/entry/20121202/1354442627
        //重複箇所をうまくまとめたい。このクラスはおつりを返さないしticketBuyResultを返すクラスに代替できる。そうなった場合、差分はチケット種別(TicketType ticketType = TicketType.~)だけ。
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
        return doBuyPassport(TicketType.TWO_ALL_DAY, handedMoney);
        // done tanaryo [いいね] 変数でお釣りであることを示してるのがGood by jflute (2024/08/01)
        // (ただし個人差がある: ぼくも強調したいとき、しなくてもいいとき、ケースバイケースではある)
    }

    /**
     * 夜限定2Dayパスポートを買う、パークゲスト用のメソッド。
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.TWO_NIGHT_DAY, handedMoney);
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
       return doBuyPassport(TicketType.FOUR_ALL_DAY, handedMoney);
    }

    /**
     * パスポートを買う、パークゲスト用の共通メソッド。
     * @param ticketType パスポートの種別 (NotNull)
     * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
     * @return チケットとお釣りなどを渡す (NotNull)
     * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
     * @throws TicketShortMoneyException 買うのに金額が足りなかったら
     */
    private TicketBuyResult doBuyPassport(TicketType ticketType, int handedMoney) {
        // TODO tanaryo さらに、この処理の流れを見通しよくするために、個々の処理をprivate化してみましょう by jflute (2024/09/26)
        // もちろん、IntelliJのショートカットを使って: option+command+M :: メソッドの抽出
        int price = ticketType.getPrice();
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + price;
        } else { // first purchase
            salesProceeds = price;
        }
        // done tanaryo [いいね] 変数でお釣りであることを示してるのがGood by jflute (2024/08/01)
        // (ただし個人差がある: ぼくも強調したいとき、しなくてもいいとき、ケースバイケースではある)
        int change = handedMoney - price;
        Ticket ticket = new Ticket(ticketType);
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
