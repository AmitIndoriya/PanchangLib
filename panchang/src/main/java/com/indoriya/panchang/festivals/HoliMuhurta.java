//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.AscTable;
import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class HoliMuhurta {
    public HoliMuhurta() {
    }

    public static double[] findHoliMuhurta(int holiDateJd, Place place) {
        double bhadrapunchaMoment = 0.0D;
        double bhadramukhaMoment = 0.0D;
        Masa objMasa = new Masa();
        double[] karnaBhadra = objMasa.karanaBhadra((double)holiDateJd, place);
        double bhadraDuration = (karnaBhadra[1] - karnaBhadra[0]) / 30.0D;
        double bhadrapunchaStart;
        double bhadrapunchaend;
        double bhadramukhaStart;
        double bhadramukhaend;
        if (karnaBhadra[2] == -1.0D) {
            bhadraDuration = (karnaBhadra[1] + 24.0D - karnaBhadra[0]) / 30.0D;
            bhadrapunchaStart = karnaBhadra[0] + bhadraDuration * 20.0D;
            bhadrapunchaend = karnaBhadra[0] + bhadraDuration * 23.0D;
            bhadramukhaStart = bhadrapunchaend;
            bhadramukhaend = karnaBhadra[0] + bhadraDuration * 28.0D;
            if (bhadrapunchaStart < 24.0D) {
                bhadrapunchaMoment = -1.0D;
            }

            if (bhadrapunchaend < 24.0D) {
                bhadramukhaMoment = -1.0D;
            }
        } else {
            bhadrapunchaStart = karnaBhadra[0] + bhadraDuration * 20.0D;
            bhadrapunchaend = karnaBhadra[0] + bhadraDuration * 23.0D;
            bhadramukhaStart = bhadrapunchaend;
            bhadramukhaend = karnaBhadra[0] + bhadraDuration * 28.0D;
            if (bhadrapunchaStart > 24.0D) {
                bhadrapunchaMoment = 1.0D;
            }

            if (bhadrapunchaend > 24.0D) {
                bhadramukhaMoment = 1.0D;
            }
        }

        double holimuhurtastart = Masa.getSunSet((double)holiDateJd, place);
        double[] nightdiv = Muhurta.getNightDivisons((double)holiDateJd, place, holimuhurtastart, 15);
        double holimuhurtaend = nightdiv[3];
        double[] tithiPurnima = objMasa.tithi((double)holiDateJd, place);
        if ((int)tithiPurnima[0] == 15) {
            if (tithiPurnima[1] > nightdiv[0] && tithiPurnima[1] < nightdiv[3]) {
                holimuhurtaend = tithiPurnima[1];
            }
        } else if (tithiPurnima.length > 2 && (int)tithiPurnima[2] == 15 && tithiPurnima[3] > nightdiv[0] && tithiPurnima[3] < nightdiv[3]) {
            holimuhurtaend = tithiPurnima[3];
        }

        if (karnaBhadra[1] > nightdiv[0] && karnaBhadra[1] > nightdiv[8]) {
            holimuhurtastart = bhadrapunchaStart;
            holimuhurtaend = bhadrapunchaend;
        } else if (karnaBhadra[1] > nightdiv[0] && karnaBhadra[1] > nightdiv[7]) {
            holimuhurtastart = bhadramukhaend;
            holimuhurtaend = karnaBhadra[1];
        } else if (bhadramukhaStart > nightdiv[0] && bhadramukhaStart < nightdiv[3]) {
            holimuhurtastart = karnaBhadra[1];
            holimuhurtaend = (nightdiv[8] - nightdiv[7]) / 2.0D + 24.0D;
        }

        return new double[]{holimuhurtastart, holimuhurtaend, bhadrapunchaMoment, bhadrapunchaStart, bhadrapunchaend, bhadramukhaMoment, bhadramukhaStart, bhadramukhaend};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        new Place(-33.86785D, 151.20732D, 10.0D);
        int jd = (int)Masa.toJulian(2016, 3, 22);
        findHoliMuhurta(jd, NewDelhi);
    }
}
