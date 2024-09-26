package org.docksidestage.bizfw.basic.buyticket.presenttime;

import java.time.LocalTime;

/**
 * 現在時刻を取得するインターフェース。
 * @author tanaryo
 */
public interface PresentTimeManager {
    // done tanaryo interface名とメソッドで戻すものが同じ名前だと概念が区別されてなくて紛らわしい by jflute (2024/09/19)
    // 概念としてPresentTimeと、値としてのPresentTime(LocalTime)が一緒になっているので会話もしづらさそう。
    // インタフェースがそのままだとしたら: e.g. getTime(), getPresentLocalTime()
    // 値(メソッド)がそのままだとしたら: e.g. PresentTimeManager, PresentTimeProvider, PresentProvider
    // 気に入ったやり方を、IntelliJのrenameの機能を使ってリファクタリングしてみてください。
    LocalTime getPresentTime();
}
