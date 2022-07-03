//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.AscTable;
import com.indoriya.panchang.CMoon;
import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class NarakChaturdashiMuhurta {
    public NarakChaturdashiMuhurta() {
    }

    public static double[] findNarakChaturdashiMuhurta(int narakChaturdashiJd, Place place) {
        double narakChMuhStart = 0.0D;
        double narakChMuhEnd = 0.0D;
        double[] nightMuhurta = Muhurta.getNightDivisons((double)(narakChaturdashiJd - 1), place, Masa.getSunSet((double)(narakChaturdashiJd - 1), place), 15);
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)(narakChaturdashiJd - 1), place);
        CMoon objCMoon = new CMoon();
        double moonRise = objCMoon.getMoonRiseSetTime((double)(narakChaturdashiJd - 1), place)[0];
        narakChMuhStart = moonRise > nightMuhurta[13] ? moonRise : nightMuhurta[13];
        if (tithi.length > 2) {
            if (tithi[3] > narakChMuhStart && tithi[3] < nightMuhurta[15]) {
                narakChMuhStart = tithi[3];
            }
        } else if (tithi[1] > narakChMuhStart && tithi[1] < nightMuhurta[15]) {
            narakChMuhStart = tithi[1];
        }

        narakChMuhEnd = nightMuhurta[15] - 24.0D;
        if (narakChMuhStart > 24.0D) {
            narakChMuhStart -= 24.0D;
        }

        return new double[]{narakChMuhStart, narakChMuhEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2016, 10, 29);
        double[] muhurtaArr = findNarakChaturdashiMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Narak Chaturdashi Muharta Start: " + objUtil.dms(muhurtaArr[0]));
        System.out.println("Narak Chaturdashi Muharta End: " + objUtil.dms(muhurtaArr[1]));
    }
}
