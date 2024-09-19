package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

// TODO tanaryo インスタンスの単位や粒度を示す説明がjavadocにあるといいかなと by jflute (2024/09/09)
/**
 * 入園情報と退園情報を管理するクラス
 * @author tanaryo
 */
public class TicketReader {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final TicketType ticketType;

    // TODO done tanaryo leftDays, ない場合に0を入れてるのであればintでもOK, もしくはIntegerだったら(NotNull)のコメント欲しい by jflute (2024/09/09)
    // TODO done tanaryo inTime, outTime, nullに関する補足があると嬉しい by jflute (2024/09/09)
    //  e.g. private LocalTime inTime;//は入園した時間を示す (NullAllowed: 入園前)
    private int leftDays;//が0の場合入園できない
    private boolean nowAlreadyIn;// trueは入園中を示す
    private LocalTime inTime;//は入園した時間を示す (NullAllowed: 入園前)
    private LocalTime outTime;//は退園した時間を示す (NullAllowed: 入園前)

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // [ふぉろー] 同じチケットに対して複数のTicketReaderを作ることができちゃうというのはデメリットになるかも by jflute
    // リファクタリングで得られたものもあれば、失ったものもあるということはよくあるので、その学びになれば良い。
    // TODO done tanaryo ticketの@param書こう (JavaDoc書くからには正しい形式で書こう) by jflute (2024/09/09)
    /**
     * 1回目の入園時に作成する
     * @param ticket 入場チケット (NotNull)
     */
    public TicketReader(Ticket ticket) {
        this.ticketType = ticket.getTicketType();
        this.leftDays = ticketType.getDayCount();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // TODO tanaryo 修行++: 結局、インを呼ぶプログラム側が引数で現在日時を細工できてしまう by jflute (2024/09/09)
    // テストだけじゃなくmainコード側のインを呼ぶプログラムには本来隠蔽したい。
    // ただ、step6をやってからチャレンジした方が良いかも。(そこは任せます)
    public void doInPark(PresentTime presentTime) {
        if (leftDays == 0) {
            throw new IllegalStateException("This ticket is unavailable: leftDays=" + leftDays);
        }
        if (presentTime.getPresentTime().isBefore(ticketType.getStartTime())) {
            throw new IllegalStateException("入場可能時間より前なので入場できません: (presentTime, startTime) = (" + presentTime.getPresentTime() + "," + ticketType.getStartTime() + ")");
        }
        // TODO tanaryo endのチェックは？ by jflute (2024/09/09)
        if (nowAlreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: inTime=" + inTime);
        }
        --leftDays;
        nowAlreadyIn = true;
        inTime = presentTime.getPresentTime();
    }

    // ===================================================================================
    //                                                                             Out Park
    //                                                                             =======
    public void doOutPark(PresentTime presentTime) {
        if (!nowAlreadyIn) {
            throw new IllegalStateException("Already out park by this ticket: outTime=" +outTime);
        }
        // done tanaryo ifの空白が他のコードと合ってない by jflute (2024/08/05)
        // done tanaryo 実質的に支障はあまり無さそうだが、最終日だけ帰った後nowAlreadyInがtrueのまんまが変というか必要あるか？ by jflute (2024/08/15)
        // nowAlreadyInは、現在パーク内に入ってるかどうか？を純粋に表現する変数というイメージで作っていると思うので
        nowAlreadyIn = false;
        outTime = presentTime.getPresentTime();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isAlreadyIn() {
        return nowAlreadyIn;
    }

    public LocalTime getInTime() {
        return inTime;//入園時間を上書きしてしまっているので、1回目入場と2回目入場をどちらも記録できたら良い
    }

    public LocalTime getOutTime() {
        return outTime;
    }
}
