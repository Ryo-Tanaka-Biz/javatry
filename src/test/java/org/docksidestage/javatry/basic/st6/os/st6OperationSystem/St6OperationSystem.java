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
package org.docksidestage.javatry.basic.st6.os.st6OperationSystem;

/**
 * @author jflute
 */
public abstract class St6OperationSystem {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final String loginId;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(String loginId) {
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    // [1on1でのふぉろー] コメント、JavaDocなどやはりコードから読み取れないものを説明してくれてるので、しっかり注目していきましょう。
    // IntelliJだと、メソッド補完時にcontrol+Jを押すとJavaDocが表示される。 by jflute (2024/11/08)

    // done tanaryo [いいね] 教訓が活きてて良いです。メモ大事。 by jflute (2024/11/08)
    // done tanaryo 一方で、_todo使って誰が？いつ？の情報が自然と残るようにしましょう by jflute (2024/11/08)
    //サブクラスまで分けた。中身はこれから
    // done tanaryo 細かいですが、可視性を先に書くスタイルの方が若干多いかな？と思うので合わせてもらえればと by jflute (2024/11/15)
    // (少なくともjavatryの他のクラスはそうなので、合わせてもらえれば。そういうの合わせるって気遣いも大切)
    protected abstract String getFileSeparator();

    protected abstract String getUserDirectory();
}
