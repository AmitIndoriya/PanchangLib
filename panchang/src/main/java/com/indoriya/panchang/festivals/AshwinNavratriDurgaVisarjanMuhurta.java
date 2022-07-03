//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class AshwinNavratriDurgaVisarjanMuhurta {
    public AshwinNavratriDurgaVisarjanMuhurta() {
    }

    public static double[] findAshwinNavratriDurgaVisarjanMuhurta(int durgaVisarjanDateJd, Place place) {
        double visarjanStart = 0.0D;
        double visarjanEnd = 0.0D;
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)durgaVisarjanDateJd, place);
        double[] dayMuhurta = Muhurta.getDayDivisons((double)durgaVisarjanDateJd, place, Masa.getSunRise((double)durgaVisarjanDateJd, place), 5);
        if ((int)tithi[0] == 10) {
            visarjanStart = dayMuhurta[0];
            visarjanEnd = tithi[1] > dayMuhurta[1] ? dayMuhurta[1] : tithi[1];
        } else {
            visarjanStart = dayMuhurta[0];
            visarjanEnd = dayMuhurta[2];
        }

        return new double[]{visarjanStart, visarjanEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2015, 3, 29);
        double[] muhurtaArr = findAshwinNavratriDurgaVisarjanMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Durga Visarjan Muharta Start: " + objUtil.dms(muhurtaArr[0]));
        System.out.println("Durga Visarjan Muharta End: " + objUtil.dms(muhurtaArr[1]));
    }
}
