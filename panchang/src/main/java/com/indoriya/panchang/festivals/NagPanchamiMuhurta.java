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

public class NagPanchamiMuhurta {
    public NagPanchamiMuhurta() {
    }

    public static double[] findNagPanchamiMuhurta(int nagPanchamiDateJd, Place place) {
        double nagPanchamiStart = 0.0D;
        double nagPanchamiEnd = 0.0D;
        Masa objMasa = new Masa();
        double sunrise = Masa.getSunRise((double)nagPanchamiDateJd, place);
        double triMuhurta = Muhurta.getDayDivisons((double)nagPanchamiDateJd, place, sunrise, 15)[3];
        double[] titihi = objMasa.tithi((double)nagPanchamiDateJd, place);
        if ((int)titihi[0] == 5) {
            nagPanchamiStart = sunrise;
            if (titihi[1] > triMuhurta) {
                nagPanchamiEnd = triMuhurta;
            } else {
                nagPanchamiEnd = titihi[1];
            }
        } else if ((int)titihi[0] == 4) {
            nagPanchamiStart = titihi[1];
            if (titihi[1] > triMuhurta) {
                nagPanchamiEnd = Masa.getSunSet((double)nagPanchamiDateJd, place);
            } else {
                nagPanchamiEnd = triMuhurta;
            }
        }

        return new double[]{nagPanchamiStart, nagPanchamiEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2015, 8, 19);
        findNagPanchamiMuhurta(jd, NewDelhi);
    }
}
