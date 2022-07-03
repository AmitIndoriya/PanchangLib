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

public class GovardhanMuhurta {
    public GovardhanMuhurta() {
    }

    public static double[] findgovardhanMuhurta(int govardhanDateJd, Place place) {
        double prathaKalMuhStart = 0.0D;
        double prathaKalMuhEnd = 0.0D;
        double sayanKalMuhStart = 0.0D;
        double sayanKalMuhEnd = 0.0D;
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)govardhanDateJd, place);
        double[] dayMuhurta = Muhurta.getDayDivisons((double)govardhanDateJd, place, Masa.getSunRise((double)govardhanDateJd, place), 15);
        if ((int)tithi[0] == 1) {
            prathaKalMuhStart = dayMuhurta[0];
            prathaKalMuhEnd = dayMuhurta[3];
            if (tithi[1] > dayMuhurta[12]) {
                sayanKalMuhStart = dayMuhurta[12];
                sayanKalMuhEnd = dayMuhurta[15] > tithi[1] ? tithi[1] : dayMuhurta[15];
            }
        } else {
            if (dayMuhurta[3] > tithi[1]) {
                prathaKalMuhStart = tithi[1];
                prathaKalMuhEnd = dayMuhurta[3];
            }

            sayanKalMuhStart = dayMuhurta[12] > tithi[1] ? dayMuhurta[12] : tithi[1];
            sayanKalMuhEnd = dayMuhurta[15];
        }

        return new double[]{prathaKalMuhStart, prathaKalMuhEnd, sayanKalMuhStart, sayanKalMuhEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2016, 10, 31);
        double[] muhurtaArr = findgovardhanMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("PrathaKal Muhurta Start: " + objUtil.dms(muhurtaArr[0]));
        System.out.println("PrathaKal Muharta End: " + objUtil.dms(muhurtaArr[1]));
        System.out.println("SayanKal Muharta Start: " + objUtil.dms(muhurtaArr[2]));
        System.out.println("SayanKal Muharta End: " + objUtil.dms(muhurtaArr[3]));
    }
}
