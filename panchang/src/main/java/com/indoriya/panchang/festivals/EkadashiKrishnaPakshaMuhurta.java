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

public class EkadashiKrishnaPakshaMuhurta {
    public EkadashiKrishnaPakshaMuhurta() {
    }

    public static double[] findEkadashiKrishnaPakshaMuhurta(int ekadashiDateJd, Place place) {
        double ekadashiEndTime = 0.0D;
        double dwadashiStartTime = 0.0D;
        double dwadashiEndTime = 0.0D;
        Masa objMasa = new Masa();
        double nextDaySunrise = Masa.getSunRise((double)(ekadashiDateJd + 1), place);
        double[] nextDayDiv = Muhurta.getDayDivisons((double)(ekadashiDateJd + 1), place, nextDaySunrise, 15);
        double paranaStart = nextDaySunrise;
        double paranaEnd = nextDayDiv[3];
        double[] tithi = objMasa.tithi((double)ekadashiDateJd, place);
        if (tithi[0] == 26.0D) {
            ekadashiEndTime = tithi[1];
        } else if (tithi.length > 2 && tithi[2] == 26.0D) {
            ekadashiEndTime = tithi[3];
        }

        double[] dwadashiTithi = objMasa.tithiStartEndTime((double)(ekadashiDateJd + 1), place);
        if (dwadashiTithi[0] == 27.0D) {
            dwadashiStartTime = dwadashiTithi[1];
            dwadashiEndTime = dwadashiTithi[3] == -1.0D ? dwadashiTithi[2] + 24.0D : dwadashiTithi[2];
        } else if (tithi.length > 3 && tithi[3] == 27.0D) {
            dwadashiStartTime = dwadashiTithi[4];
            dwadashiEndTime = dwadashiTithi[5];
        }

        double hariVasaraEndTime = (dwadashiEndTime - dwadashiStartTime) / 4.0D + ekadashiEndTime;
        double asd = nextDaySunrise + 24.0D;
        if (hariVasaraEndTime > asd) {
            if (hariVasaraEndTime > 24.0D) {
                hariVasaraEndTime -= 24.0D;
            }

            if (hariVasaraEndTime > paranaEnd) {
                paranaStart = nextDayDiv[9];
                paranaEnd = nextDayDiv[12];
            } else {
                paranaStart = hariVasaraEndTime;
            }
        } else {
            hariVasaraEndTime = 0.0D;
        }

        return new double[]{paranaStart, paranaEnd, hariVasaraEndTime, 1.0D};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        int jd = (int)Masa.toJulian(2018, 5, 11);
        double[] muhurta = findEkadashiKrishnaPakshaMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Muharta Start: " + objUtil.dms(muhurta[0]));
        System.out.println("Muharta End  : " + objUtil.dms(muhurta[1]));
        System.out.println("Hari Time  : " + objUtil.dms(muhurta[2]));
    }
}
