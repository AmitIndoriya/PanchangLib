//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;


import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class VijayaDashamiMuhurta {
    public VijayaDashamiMuhurta() {
    }

    public static double[] findVijayaDashamiMuhurta(int vijayaDashamiDateJd, Place place) {
        double vijayMuhurtaStart = 0.0D;
        double vijayMuhurtaEnd = 0.0D;
        double aprhanMuhurtaStart = 0.0D;
        double aprhanMuhurtaEnd = 0.0D;
        double[] muhurta = Muhurta.getDayDivisons((double)vijayaDashamiDateJd, place, Masa.getSunRise((double)vijayaDashamiDateJd, place), 15);
        vijayMuhurtaStart = muhurta[10];
        vijayMuhurtaEnd = muhurta[11];
        aprhanMuhurtaStart = muhurta[9];
        aprhanMuhurtaEnd = muhurta[12];
        return new double[]{vijayMuhurtaStart, vijayMuhurtaEnd, aprhanMuhurtaStart, aprhanMuhurtaEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int) Masa.toJulian(2016, 10, 11);
        double[] muhurta = findVijayaDashamiMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Vijay Muhurta Start : " + objUtil.dms(muhurta[0]));
        System.out.println("Vijay Muhurta End : " + objUtil.dms(muhurta[1]));
        System.out.println("Aprhan Muhurta Start : " + objUtil.dms(muhurta[2]));
        System.out.println("Aprhan Muhurta End : " + objUtil.dms(muhurta[3]));
    }
}
