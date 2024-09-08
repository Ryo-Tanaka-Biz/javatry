package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

// TODO done tanaryo ↑unusedのimportが残ってる "org.apache.tomcat.jni.Local" 間違えてLocalを補完しちゃったんだと思う by jflute (2024/09/06)
// TODO tanaryo classのjavadocコメントおねがいしますー by jflute (2024/09/06)

public enum TicketType {
    ONE_ALL_DAY(1, 7400, LocalTime.of(9, 0), LocalTime.of(22, 0)),
    TWO_ALL_DAY(2, 13200, LocalTime.of(9, 0), LocalTime.of(22, 0)),
    TWO_NIGHT_DAY(2, 7400, LocalTime.of(18, 0), LocalTime.of(22, 0)),
    FOUR_ALL_DAY(4, 22400, LocalTime.of(9, 0), LocalTime.of(22, 0)),;

    // [ふぉろー] enumはインスタンスの個数を制限された普通のクラスと思って良い。(+ Enumを継承) by jflute
    // TicketTypeのインスタンス自身で比較する、ということもできる。(Enumのequals()が==で実装されている)
    //  e.g. ticketType.equals(TicketType.ONE_ALL_DAY)
    //       (ticketType == TicketType.ONE_ALL_DAY という風に書いても同じ)
    // done tanaryo [読み物課題] equals()と "==" by jflute (2024/08/29)
    // https://dbflute.seasar.org/ja/manual/topic/programming/java/beginners.html#equalsequal
    
    private final int dayCount;
    private final int price;
    private final LocalTime startTime;
    private final LocalTime endTime;

    TicketType(int dayCount, int price, LocalTime startTime, LocalTime endTime) {
        this.dayCount = dayCount;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

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
