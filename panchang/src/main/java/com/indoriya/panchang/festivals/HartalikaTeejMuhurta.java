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

public class HartalikaTeejMuhurta {
    public HartalikaTeejMuhurta() {
    }

    public static double[] findHartalikaTeejMuhurta(int hartalikaTeejDateJd, Place place) {
        double sunrise = Masa.getSunRise((double)hartalikaTeejDateJd, place);
        double sunset = Masa.getSunSet((double)hartalikaTeejDateJd, place);
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)hartalikaTeejDateJd, place);
        double[] dayMuhurta = Muhurta.getDayDivisons((double)hartalikaTeejDateJd, place, sunrise, 5);
        double[] nightMuhurta = Muhurta.getNightDivisons((double)hartalikaTeejDateJd, place, sunset, 5);
        double pratahkalStart = dayMuhurta[0];
        double pratahkalEnd = dayMuhurta[1];
        double pradoshStart = nightMuhurta[0];
        double pradoshEnd = nightMuhurta[1];
        if ((int)tithi[0] == 3) {
            if (tithi[1] >= pradoshStart && tithi[1] <= pradoshEnd) {
                pradoshEnd = tithi[1];
            } else if (tithi[1] <= pradoshStart) {
                pradoshStart = 0.0D;
                pradoshEnd = 0.0D;
            }
        }

        return new double[]{pratahkalStart, pratahkalEnd, pradoshStart, pradoshEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2018, 9, 12);
        double[] muhurta = findHartalikaTeejMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Pratahkal Start : " + objUtil.dms(muhurta[0]));
        System.out.println("Pratahkal End  : " + objUtil.dms(muhurta[1]));
        System.out.println("Pradosh Start  : " + objUtil.dms(muhurta[2]));
        System.out.println("Pradosh End  : " + objUtil.dms(muhurta[3]));
    }
}
