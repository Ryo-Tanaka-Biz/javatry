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
    @Override
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }
}
