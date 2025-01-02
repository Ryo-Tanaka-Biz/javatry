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
package org.docksidestage.javatry.colorbox;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author tanaryo
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() { // example, so begin from the next method
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);//firstのボックス
            BoxColor boxColor = colorBox.getColor();//colorクラスの変数名は定義されていない
            String colorName = boxColor.getColorName();//green
            int answer = colorName.length();//5
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }
    //当たった。インターフェースとか抽象クラスの存在をざっと把握。あとで問題を解くに従いちゃんと見ていく。

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMax_colorSize() {
        //カラーボックスのリストを取得
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        //カラーボックスの色の名前のリストを取得
        List<String> sortedColorBoxNameList = colorBoxList.stream().map(colorBox -> colorBox.getColor().getColorName())
                //文字数多い順に並び変え
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())).collect(Collectors.toList());
        //要素0を取得
        String longestColorBoxName = sortedColorBoxNameList.get(0);
        int answer = longestColorBoxName.length();
        log(answer + " (" + longestColorBoxName + ")");
        log("sortedColorBoxNameList:" + sortedColorBoxNameList);

        //max使ってやってみる。max(Comparator.comparingInt(String::length))の意味をあとでちゃんと調べる（2025/01/02）
        Optional<String> maxColorBoxNameList =
                colorBoxList.stream().map(colorBox -> colorBox.getColor().getColorName()).max(Comparator.comparingInt(String::length));
        log("maxColorBoxNameList:" + maxColorBoxNameList.get());

        //最大値を取得して、それに該当する文字数のListを取得する
        int maxLength = maxColorBoxNameList.get().length();
        List<String> longestStringList = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor().getColorName())
                .filter(s -> s.length() == maxLength)
                .distinct()
                .collect(Collectors.toList());
        log("longestStringList:" + longestStringList);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        //どんな値が入っている？
        //colorBox
        //- BoxColor
        //  - colorName(string)
        //- BoxSize
        //  - height(int)
        //  - width(int)
        //  - depth(int)
        //- BoxSpace
        //  - content (object)

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        //文字列なので、colorNameとcontentそれぞれでリスト作成
        List<String> colorBoxNameList =
                colorBoxList.stream().map(colorBox -> colorBox.getColor().getColorName()).collect(Collectors.toList());
        List<String> colorBoxSpaceContentStringList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .collect(Collectors.toList());

        //リスト結合
        List<String> stringColorBoxList =
                Stream.concat(colorBoxNameList.stream(), colorBoxSpaceContentStringList.stream()).collect(Collectors.toList());

        //最大文字数取得
        int maxLength = stringColorBoxList.stream().max(Comparator.comparingInt(String::length)).get().length();

        //最大文字数に一致するリスト作成
        List<String> maxStringList =
                stringColorBoxList.stream().filter(s -> s.length() == maxLength).distinct().collect(Collectors.toList());

        log("colorBoxNameList:" + colorBoxNameList);
        log("colorBoxSpaceContentStringList:" + colorBoxSpaceContentStringList);
        log("maxStringList:" + maxStringList + ", maxLength:" + maxLength);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (latter if same length) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (同じ長さのものがあれば後の方を))
     */
    public void test_length_findSecondMax_contentToString() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        //colorName
        List<String> colorBoxNameList =
                colorBoxList.stream().map(colorBox -> colorBox.getColor().getColorName()).collect(Collectors.toList());

        //height
        List<String> colorBoxSizeHeightList = colorBoxList.stream()
                .map(colorbox -> colorbox.getSize().getHeight())
                .map(integer -> integer.toString())
                .collect(Collectors.toList());

        //width
        List<String> colorBoxSizeWidthList = colorBoxList.stream()
                .map(colorbox -> colorbox.getSize().getWidth())
                .map(integer -> integer.toString())
                .collect(Collectors.toList());

        //depth
        List<String> colorBoxSizeDepthList = colorBoxList.stream()
                .map(colorbox -> colorbox.getSize().getDepth())
                .map(integer -> integer.toString())
                .collect(Collectors.toList());

        //content
        List<String> colorBoxSpaceContentList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object != null)
                .map(object -> object.toString())
                .collect(Collectors.toList());

        //結合と文字数多い順に並び変え
        List<String> sortedStringColorBoxList =
                Stream.of(colorBoxNameList, colorBoxSizeHeightList, colorBoxSizeWidthList, colorBoxSizeDepthList, colorBoxSpaceContentList)
                        .flatMap(list -> list.stream())
                        .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
                        .collect(Collectors.toList());
        //２番目を取得
        String secondLongestColorBoxName = sortedStringColorBoxList.get(1);
        int answer = secondLongestColorBoxName.length();
        log(sortedStringColorBoxList);
        log(answer + " (" + secondLongestColorBoxName + ")");//182文字
        //Map型が出力された
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        //文字列なので、colorNameとcontentそれぞれでリスト作成
        List<String> colorBoxNameList =
                colorBoxList.stream().map(colorBox -> colorBox.getColor().getColorName()).collect(Collectors.toList());
        List<String> colorBoxSpaceContentStringList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .collect(Collectors.toList());

        //合計算出
        int totalStringColorBoxLength =
                Stream.concat(colorBoxNameList.stream(), colorBoxSpaceContentStringList.stream()).mapToInt(String::length).sum();
        log("totalStringColorBoxLength:" + totalStringColorBoxLength);//71文字
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        //ColorBoxのレイヤーからmapで変換して別クラスに変換すると、colorNameを辿れないので、
        //"water"で始まるかどうかは、ColorBoxクラスに対してfilterをかけて調べる
        //そのあと、ColorBoxからcolorNameにmapする
        //ColorBox:BoxSpace は1:Nの関係。なのでanyMatch使う。(memo anyMatchはbooleanを返す)
        //ColorBox:colorNameは1:1の関係。
        List<String> filteredColorName = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList()
                        .stream()
                        .anyMatch(boxSpace -> boxSpace.getContent() instanceof String && boxSpace.getContent()
                                .toString()
                                .startsWith("Water")))
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());
        log("filteredColorName:" + filteredColorName);//red
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<String> filteredColorBoxSpaceContentStringList = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .filter(string -> string.split("ど", -1).length - 1 >= 2)
                .collect(Collectors.toList());

        //どを2回以上含む文字列をキー、最後に現れる文字数を値としてMap型で表現。
        //[雑談] 確かアラビア語は右から左に読む言語だったので、javaのindexofを使ったら違和感ありそうだと思いました。。
        Map<String, Integer> filteredColorBoxSpaceContentStringMap = filteredColorBoxSpaceContentStringList.stream()
                .collect(Collectors.toMap(string -> string,
                        string -> string.chars().mapToObj(a -> (char) a).collect(Collectors.toList()).lastIndexOf('ど') + 1));

        log("filteredColorBoxSpaceContentStringMap:" + filteredColorBoxSpaceContentStringMap);

    }

    // ===================================================================================
    //                                                                 Welcome to Guardian
    //                                                                 ===================
    /**
     * What is total length of text of GuardianBox class in color-boxes? <br>
     * (カラーボックスの中に入っているGuardianBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToGuardian() {
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // these are very difficult exercises so you can skip
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
    }
}
