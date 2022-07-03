//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class AnantChaturdashiMuhurta {
    public AnantChaturdashiMuhurta() {
    }

    public static double[] findAnantChaturdashiMuhurta(int anantChaturdashiDateJd, Place place) {
        double muhuratStart = 0.0D;
        double muhuratEnd = 0.0D;
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)anantChaturdashiDateJd, place);
        double sunrise = Masa.getSunRise((double)anantChaturdashiDateJd, place);
        if ((int)tithi[0] == 14) {
            muhuratStart = sunrise;
            muhuratEnd = tithi[1];
        } else if (tithi.length > 2) {
            muhuratStart = tithi[1];
            muhuratEnd = tithi[3];
        }

        return new double[]{muhuratStart, muhuratEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2012, 9, 29);
        double[] muhurtaArr = findAnantChaturdashiMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Anant Chaturdashi Muharta Start: " + objUtil.dms(muhurtaArr[0]));
        System.out.println("Anant Chaturdashi Muhurta End: " + objUtil.dms(muhurtaArr[1]));
    }
}
