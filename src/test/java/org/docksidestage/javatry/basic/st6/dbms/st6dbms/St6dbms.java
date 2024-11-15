package org.docksidestage.javatry.basic.st6.dbms.st6dbms;

// done tanaryo PostgreSQLはSQLである、MySQLはSQLであるは自然かどうか？ by jflute (2024/10/31)
// tips: いま存在しない新しい具象クラスを想像してみよう。他には？
// (オブジェクト指向は、"意味" を大切にした考え方である)
// 
// Database Management System => DBMS
//

/**
 * @author tanaryo
 */
public abstract class St6dbms {

    // done tanaryo 一個しかないし、素直にreturnでoffsetを戻すほうが素直かなと by jflute (2024/10/31)
    // かつ、インスタンス変数経由で値を渡すというのは、回り道をしているので可読性は落ちる。
    // 必要なときはまあしょうがないけど、ここではその必要がなさそうなので回り道は単なるロスになる。
    // あと、インスタンス変数を状態として扱うと、同時に複数のスレッドから呼び出された時に共有である変数がぐちゃっとなる。
    // それも必要なときはしょうがないというかぐちゃっとならないような策も別にあるんだけども、まあ必要なければ積極的にやる必要はない。
    // TODO done tanaryo do使って実処理を表してくれたのはGood, 一方で... by jflute (2024/11/08)
    // ここの処理って「offsetの計算」とズバリ言えるので、そっちの方が良いかなと。
    // (質問されたらこう答えるっていう答えをメソッドの名前にすると良い: それが概要だから)
    protected int calculateOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }

    public abstract String buildPagingQuery(int pageSize, int pageNumber);
}
