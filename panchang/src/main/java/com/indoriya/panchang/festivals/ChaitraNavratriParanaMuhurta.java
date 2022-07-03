//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;
public class ChaitraNavratriParanaMuhurta {
    public ChaitraNavratriParanaMuhurta() {
    }

    public static double findChaitraNavratriParanaMuhurta(int paranaDateJd, Place place) {
        double paranaStart = 0.0D;
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)paranaDateJd, place);
        if ((int)tithi[0] == 9) {
            double sunset = Masa.getSunSet((double)paranaDateJd, place);
            if (tithi[1] > sunset) {
                paranaStart = Masa.getSunRise((double)(paranaDateJd + 1), place);
            } else {
                paranaStart = tithi[1];
            }
        } else {
            paranaStart = Masa.getSunRise((double)paranaDateJd, place);
        }

        return paranaStart;
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2015, 3, 29);
        findChaitraNavratriParanaMuhurta(jd, NewDelhi);
    }
}
