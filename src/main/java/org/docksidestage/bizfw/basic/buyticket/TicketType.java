package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

// done tanaryo ↑unusedのimportが残ってる "org.apache.tomcat.jni.Local" 間違えてLocalを補完しちゃったんだと思う by jflute (2024/09/06)
// done tanaryo classのjavadocコメントおねがいしますー by jflute (2024/09/06)

/**
 * パスポートの種別を持つ、列挙型のクラス。
 * @author tanaryo
 */
public enum TicketType {
    /**
     * 終日1デーパスポート
     */
    ONE_ALL_DAY(1, 7400, LocalTime.of(9, 0), LocalTime.of(22, 0)),

    /**
     * 終日2デーパスポート
     */
    TWO_ALL_DAY(2, 13200, LocalTime.of(9, 0), LocalTime.of(22, 0)),

    /**
     * 夜限定2デーパスポート
     */
    TWO_NIGHT_DAY(2, 7400, LocalTime.of(18, 0), LocalTime.of(22, 0)),

    /**
     * 終日4デーパスポート
     */
    FOUR_ALL_DAY(4, 22400, LocalTime.of(9, 0), LocalTime.of(22, 0));

    // [ふぉろー] enumはインスタンスの個数を制限された普通のクラスと思って良い。(+ Enumを継承) by jflute
    // TicketTypeのインスタンス自身で比較する、ということもできる。(Enumのequals()が==で実装されている)
    //  e.g. ticketType.equals(TicketType.ONE_ALL_DAY)
    //       (ticketType == TicketType.ONE_ALL_DAY という風に書いても同じ)
    // done tanaryo [読み物課題] equals()と "==" by jflute (2024/08/29)
    // https://dbflute.seasar.org/ja/manual/topic/programming/java/beginners.html#equalsequal

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // [ふぉろー] beginTime, endTime話、正解はないけど個人的にはbegin(アルファベット順とかを気にする) by jflute
    // あと、名前をあえて文字数違うの選んだりとか区別しやすい単語を使っていく話した。
    private final int dayCount;//日数
    private final int price;//価格
    private final LocalTime startTime;//入園時間
    private final LocalTime endTime;//退園時間

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    TicketType(int dayCount, int price, LocalTime startTime, LocalTime endTime) {
        this.dayCount = dayCount;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDayCount() {
        return dayCount;
    }

    public int getPrice() {
        return price;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
