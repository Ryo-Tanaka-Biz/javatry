package org.docksidestage.javatry.basic.st6.os;

import org.docksidestage.javatry.basic.st6.os.st6OperationSystem.St6OperationSystem;

/**
 * @author tanaryo
 */
public class St6Mac extends St6OperationSystem {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String osType;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6Mac(String loginId, String osType) {
        super(loginId);
        this.osType = osType;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    // done tanaryo [教訓] 時間が経つと、絶対に忘れないだろうと思ってたものも忘れる by jflute (2024/10/31)
    // なので、todoコメントがある、ひとこと「やりかけ」書くだけでも全然違う (もちろん詳細もあったらいいけど)
    // (高度なお仕事をどんどんしていくうえで、こういう意識は大事になる)
    @Override
    protected String getFileSeparator() {
        validateValue(osType);
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        validateValue(osType);
        return "/Users/" + loginId;
    }
}
