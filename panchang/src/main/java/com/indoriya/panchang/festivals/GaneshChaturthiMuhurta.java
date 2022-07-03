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

public class GaneshChaturthiMuhurta {
    public GaneshChaturthiMuhurta() {
    }

    public static double[] findGaneshChaturthiMuhurta(int ganeshChaturthiMuhurtaDateJd, Place place) {
        double madhyanStart = 0.0D;
        double MadhyanEnd = 0.0D;
        double avoidMoonStart = 0.0D;
        double avoidMoonEnd = 0.0D;
        double avoidMoonStart2 = 0.0D;
        double avoidMoonEnd2 = 0.0D;
        double avoidMoon2day = 0.0D;
        double sunRise = Masa.getSunRise((double)ganeshChaturthiMuhurtaDateJd, place);
        double[] muhurta = Muhurta.getDayDivisons((double)ganeshChaturthiMuhurtaDateJd, place, sunRise, 5);
        Masa objMasa = new Masa();
        CMoon objCMoon = new CMoon();
        double[] tithiStartEnd = objMasa.tithiStartEndTime((double)ganeshChaturthiMuhurtaDateJd, place);
        double[] moon = objCMoon.getMoonRiseSetTime((double)ganeshChaturthiMuhurtaDateJd, place);
        madhyanStart = muhurta[2];
        MadhyanEnd = muhurta[3];
        double[] tithiNextday;
        if ((int)tithiStartEnd[0] == 4) {
            avoidMoonStart = moon[0];
            avoidMoonEnd = moon[2];
            if ((double)((int)tithiStartEnd[3]) == -1.0D) {
                tithiNextday = objCMoon.getMoonRiseSetTime((double)(ganeshChaturthiMuhurtaDateJd - 1), place);
                if (tithiStartEnd[1] < tithiNextday[2]) {
                    avoidMoonStart2 = tithiStartEnd[1];
                    avoidMoonEnd2 = tithiNextday[2];
                    avoidMoon2day = -1.0D;
                }
            }
        } else if ((int)tithiStartEnd[0] == 3) {
            avoidMoonStart = tithiStartEnd[2];
            avoidMoonEnd = moon[2];
            if (tithiStartEnd.length <= 4) {
                tithiNextday = objMasa.tithi((double)(ganeshChaturthiMuhurtaDateJd + 1), place);
                if ((int)tithiNextday[0] == 4) {
                    double[] moonNextDay = objCMoon.getMoonRiseSetTime((double)(ganeshChaturthiMuhurtaDateJd + 1), place);
                    if (tithiNextday[1] > moonNextDay[0]) {
                        avoidMoonStart2 = moonNextDay[0];
                        avoidMoonEnd2 = moonNextDay[2];
                        avoidMoon2day = 1.0D;
                    }
                }
            }
        }

        return new double[]{madhyanStart, MadhyanEnd, avoidMoonStart, avoidMoonEnd, avoidMoonStart2, avoidMoonEnd2, avoidMoon2day};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2009, 8, 23);
        findGaneshChaturthiMuhurta(jd, NewDelhi);
    }

    private double hms(double hh, double mm, double ss) {
        return hh + mm / 60.0D + ss / 3600.0D;
    }
}
