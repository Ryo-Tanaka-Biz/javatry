package org.docksidestage.javatry.basic.st6.os;

public class St6PathOperationSystem extends St6OperationSystem {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6PathOperationSystem(String osType, String loginId) {
        super(osType, loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    // TODO done tanaryo [教訓] 時間が経つと、絶対に忘れないだろうと思ってたものも忘れる by jflute (2024/10/31)
    // なので、todoコメントがある、ひとこと「やりかけ」書くだけでも全然違う (もちろん詳細もあったらいいけど)
    // (高度なお仕事をどんどんしていくうえで、こういう意識は大事になる)
    @Override
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }
}
