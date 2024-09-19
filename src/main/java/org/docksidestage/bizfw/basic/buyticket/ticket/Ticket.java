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

// TODO done tanaryo ↑unusedの警告 by jflute (2024/09/09)

/**
 * チッケト種別と表示価格を管理するクラス
 * @author jflute
 * @author tanaryo
 */
public class Ticket {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done tanaryo [いいね] 横の//すらすらコメントで変数の補足が素晴らしい by jflute (2024/08/15)
    // done tanaryo [いいね] 変数の定義順番、わかりやすくていいですね by jflute (2024/08/15)
    // done tanaryo dayCount, "残りの" っていうニュアンスが変数名にあるといいかなと by jflute (2024/08/29)
    // done tanaryo 日数を表現するとき、daysと複数形にする慣習が世界的にあるかなと by jflute (2024/09/06)
    // rename機能で一括変換できた！！！
    // ぜひ、IntelliJのRenameの機能を使ってリファクタリングしてみてください。
    // done tanaryo コメント内に変数名を入れないほうが良い、リファクタリングの追従がされないので by jflute (2024/09/06)
    // done tanaryo このくらいの量なら、finalなものとmutableなもので定義順分けたほうがわかりやすいかも by jflute (2024/09/06)
    private final TicketType ticketType;
    private final int displayPrice;// written on ticket, park guest can watch this
    // TODO tanaryo コメントアウトにはコメントを (何のニュアンスで残しているか？) ここだと勉強用とか、思い出とか、以前は、とか by jflute (2024/09/09)
    // 参考: https://x.com/jflute/status/1421056115983339520

//    private final LocalTime startTime;//開始時間

//    private Integer leftDays;//が0の場合入園できない
//    private boolean nowAlreadyIn;// trueは入園中を示す


    // done tanaryo ご自身で思っている通り、唐突にpublicフィールドで公開するのはちょっと危険と思う人が多い by jflute (2024/08/15)
    // テストコード以外の人(mainコード)も、これを使って細工できちゃうので怖い

    // done tanaryo staticなものは Attribute よりも上に定義するのがJavaの慣習になっています by jflute (2024/08/22)
    // done tanaryo 固定のオブジェクトなので、これはstatic finalで定義でOK by jflute (2024/08/15)

    // done tanaryo IntelliJで変数やメソッドの利用箇所を調べるショートカットを調べてみてください by jflute (2024/09/06)
    // shift + shift で　findusageと検索
    
    // done tanaryo もうsetterで変更できるようにしてるんだったら、変数自体はprivateでもいいのかなと by jflute (2024/09/06)
    //入園と退園は別クラスで管理。現在時刻は入園/退園時に引数で指定
//    PresentTime presentTime = new DefaultPresentTime();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(TicketType ticketType) {
        this.ticketType = ticketType;
        // [ふぉろー] 変数で持つか？欲しいときにTicketTypeから取得するか？ by jflute
        this.displayPrice = ticketType.getPrice(); // 別物と捉えることもできるので明示的に持ってもOKかなと
//        this.leftDays = ticketType.getDayCount(); // ここは完全に別物だし用途も違うので詰替えが正解
//        this.startTime = ticketType.getStartTime(); // 意味もほぼ同じなのでなくてもいい？(でも一概にダメではない、主役級だったら持つことも)
    }

    // done jflute 1on1にて続きフォロー (2024/08/15)
    // [ふぉろー] publicフィールドよりは、まだsetterの方が管理がしやすい by jflute
    // setの呼び出しを管理することができる: 例えば状態をチェックして例外をthrowしたり、処理を挟み込むことができる
    // publicフィールドは処理の挟み込みが全くできないので、全く無防備
    // とはいえ、これでもmainコードでsetを呼び出すことができちゃうので、ちょっと怖さはなくなってはいない
    //public void setNowTime(LocalTime specifiedTime) {
    //    this.nowTime = specifiedTime;
    //}
    
    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
//    public void doInPark() {
//        if (leftDays == 0) {
//            throw new IllegalStateException("This ticket is unavailable: displayedPrice=" + displayPrice);
//        }
//        // done tanaryo [いいね] nightと関係ないチケットではチェック処理走らないように工夫している、これは良い by jflute (2024/08/15)
//        // ↑night以外でも24時間ってわけじゃないから、一律時間帯をチェックするように変わっている by jflute (2024/08/29)
//        if (presentTime.getPresentTime().isBefore(startTime)) {
//            // done tanaryo デバッグ情報もあるといいかなと、startTimeとかpresentTimeとか判定に使った情報含めるといいかなと by jflute (2024/08/29)
//            // [ふぉろー] DBFluteの例外メッセージのよもやま話もした
//            throw new IllegalStateException("入場可能時間より前なので入場できません: (presentTime, startTime) = (" + presentTime.getPresentTime() + "," + startTime + ")");
//        }
//        if (nowAlreadyIn) {
//            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
//        }
//        --leftDays;
//        nowAlreadyIn = true;
//    }
    // done tanaryo 自己レビューでalreadyInの名前がしっくり来ない話 by jflute (2024/08/05)
    // 現時点では、alreadyInが2つの役割を持っている
    //  o nowIn = true;
    //  o alreadyUsedCompletely = true; // これって dayCount == 0 と言える？
    // alreadyIn を nowAlreadyIn にして...チケットを使い切った判定はdayCount == 0にするという選択肢もある
    // ちょっと考えてみましょう

    // ===================================================================================
    //                                                                             Out Park
    //                                                                             =======
//    public void doOutPark() {
//        if (!nowAlreadyIn) {
//            throw new IllegalStateException("Already out park by this ticket: displayedPrice=" + displayPrice);
//        }
//        // done tanaryo ifの空白が他のコードと合ってない by jflute (2024/08/05)
//        // done tanaryo 実質的に支障はあまり無さそうだが、最終日だけ帰った後nowAlreadyInがtrueのまんまが変というか必要あるか？ by jflute (2024/08/15)
//        // nowAlreadyInは、現在パーク内に入ってるかどうか？を純粋に表現する変数というイメージで作っていると思うので
//
//        nowAlreadyIn = false;
//    }

    // done tanaryo Accessor下あたりに、なんか空行ありなしのバランスがちょっと変 by jflute (2024/09/06)
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public TicketType getTicketType() {
        return ticketType;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

//    public Integer getLeftDays() {
//        return leftDays;
//    }
//
//    public boolean isAlreadyIn() {
//        return nowAlreadyIn;
//    }
//
//    public LocalTime getPresentTime() {
//        return presentTime.getPresentTime();
//    }

    // done tanaryo setterでpublicで提供しちゃうと、利用者も変えられちゃう by jflute (2024/09/06)
    //入園と退園は別クラスで管理。現在時刻は入園/退園時に引数で指定。これでパッケージスコープではあるかな？
    // (まだpackageスコープで少し隠していた方がマシかもしれない)
//    public void setPresentTime(LocalTime presentTime) {
//        this.presentTime = new VariablePresentTime(presentTime);
//    }
}
