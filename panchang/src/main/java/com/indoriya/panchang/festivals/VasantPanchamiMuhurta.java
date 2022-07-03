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

public class VasantPanchamiMuhurta {
    public VasantPanchamiMuhurta() {
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2016, 2, 12);
        findVasantPanchamiMuhurta(jd, NewDelhi);
    }

    public static double[] findVasantPanchamiMuhurta(int vasantPanchamiDateJd, Place place) {
        double sunrise = Masa.getSunRise((double)vasantPanchamiDateJd, place);
        double[] vasantDayDivision = Muhurta.getDayDivisons((double)vasantPanchamiDateJd, place, sunrise, 2);
        Masa objMasa = new Masa();
        double[] titihi = objMasa.tithi((double)vasantPanchamiDateJd, place);
        double muhurtaStart;
        if (titihi[0] == 5.0D) {
            muhurtaStart = sunrise;
        } else {
            muhurtaStart = titihi[1];
        }

        return new double[]{muhurtaStart, vasantDayDivision[1]};
    }
}
