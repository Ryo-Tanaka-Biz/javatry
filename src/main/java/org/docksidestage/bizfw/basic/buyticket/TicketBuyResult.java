package org.docksidestage.bizfw.basic.buyticket;

// done tanaryo タグコメントの上 (クラス宣言の直下) に一行空行を by jflute (2024/08/05)

// done tanaryo ↑unusedのimport by jflute (2024/08/15)
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
    private final int change;
    private final Ticket ticket;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(int change, Ticket ticket) {
        // TODO done tanaryo 一般的に、あまりコンストラクターで(小さくても)業務的な処理は入れない慣習がある by jflute (2024/08/22)
        // ここでも悪くはないけど、多くの人がこういう風には実装しない可能性がある。
        // チケットを用意する処理自体もれっきとした業務処理なので、どちらかというとBoothの責任と考える人が多いかも。
        this.change = change;
        this.ticket = ticket;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        // done tanaryo getter(getメソッド)でその場でnewして戻すっていうのは一般的ではない by jflute (2024/08/15)
        // getterって、そこに置いてあるものをただ取るだけ、のイメージが強いので忘れ去られやすい(管理にしにくい)
        // 2回呼んではいけないメソッドになっているが、2回呼ばれちゃいそう (別インスタンスになっちゃう)
        // そもそも業務的にもチケットResultを受け取ったプログラム(お客さん)がチケットを増殖できる。
        //[tanaryo] コンストラクタでnewする
        return ticket;
    }

    public int getChange(){
        return change;
    }
}
