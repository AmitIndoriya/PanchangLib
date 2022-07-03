//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;


import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Place;

public class AkshayaTritiyaMuhurta {
    public AkshayaTritiyaMuhurta() {
    }

    public static double[] findAkshayaTritiyaMuhurta(int akshayaTriDateJd, Place place) {
        double akshayaTriStart = 0.0D;
        double akshayaTriEnd = 0.0D;
        Masa objMasa = new Masa();
        double sunrise = Masa.getSunRise((double)akshayaTriDateJd, place);
        double sunset = Masa.getSunSet((double)akshayaTriDateJd, place);
        double[] tithi = objMasa.tithi((double)akshayaTriDateJd, place);
        double midOfDay = (sunrise + sunset) / 2.0D;
        if ((int)tithi[0] == 3) {
            akshayaTriStart = sunrise;
            akshayaTriEnd = tithi[1] < midOfDay ? tithi[1] : midOfDay;
        } else if ((int)tithi[0] == 2) {
            akshayaTriStart = tithi[1];
            akshayaTriEnd = midOfDay;
        }

        return new double[]{akshayaTriStart, akshayaTriEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2015, 4, 21);
        findAkshayaTritiyaMuhurta(jd, NewDelhi);
    }
}
