//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class BhaiDoojMuhurta {
    public BhaiDoojMuhurta() {
    }

    public static double[] findBhaiDoojMuhurta(int bhaiDoojDateJd, Place place) {
        double bhaiDoojMuhStart = 0.0D;
        double bhaiDoojMuhEnd = 0.0D;
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)bhaiDoojDateJd, place);
        double[] dayMuhurta = Muhurta.getDayDivisons((double)bhaiDoojDateJd, place, Masa.getSunRise((double)bhaiDoojDateJd, place), 5);
        if ((int)tithi[0] == 2) {
            bhaiDoojMuhStart = dayMuhurta[3];
            bhaiDoojMuhEnd = tithi[1] > dayMuhurta[4] ? dayMuhurta[4] : tithi[1];
        } else if ((int)tithi[0] == 1) {
            bhaiDoojMuhStart = tithi[1] > dayMuhurta[3] ? tithi[1] : dayMuhurta[3];
            bhaiDoojMuhEnd = dayMuhurta[4];
        } else {
            bhaiDoojMuhStart = dayMuhurta[3];
            bhaiDoojMuhEnd = dayMuhurta[4];
        }

        return new double[]{bhaiDoojMuhStart, bhaiDoojMuhEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2010, 11, 7);
        double[] muhurtaArr = findBhaiDoojMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Bhai Dooj Muharta Start: " + objUtil.dms(muhurtaArr[0]));
        System.out.println("Bhai Dooj Muharta End: " + objUtil.dms(muhurtaArr[1]));
    }
}
