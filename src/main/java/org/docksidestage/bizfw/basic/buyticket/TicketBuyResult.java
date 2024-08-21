package org.docksidestage.bizfw.basic.buyticket;

// done tanaryo タグコメントの上 (クラス宣言の直下) に一行空行を by jflute (2024/08/05)

// TODO done tanaryo ↑unusedのimport by jflute (2024/08/15)
// import文はクラスの登場人物相関図みたいなものなので、コード読む時に役に立つ話
/**
 * @author tanaryo
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done tanaryo daycountがfinal付いてない理由がない by jflute (2024/08/05)
    // done tanaryo ここではdaycountだけどTicketではdayCountなので一貫性がない by jflute (2024/08/05)
    private final int displayPrice; // written on ticket, park guest can watch this
    private final int change;
    private final Integer dayCount;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(int displayPrice, int change, int dayCount) {
        this.displayPrice = displayPrice;
        this.change = change;
        this.dayCount = dayCount;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        // TODO tanaryo getter(getメソッド)でその場でnewして戻すっていうのは一般的ではない by jflute (2024/08/15)
        // getterって、そこに置いてあるものをただ取るだけ、のイメージが強いので忘れ去られやすい(管理にしにくい)
        // 2回呼んではいけないメソッドになっているが、2回呼ばれちゃいそう (別インスタンスになっちゃう)
        // そもそも業務的にもチケットResultを受け取ったプログラム(お客さん)がチケットを増殖できる。
        return new Ticket(displayPrice, dayCount);
    }

    public int getChange(){
        return change;
    }
}
