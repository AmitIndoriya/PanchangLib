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

public class AshwinNavratriGhatasthapanaMuhurta {
    public AshwinNavratriGhatasthapanaMuhurta() {
    }

    public static double[] findAshwinNavratriGhatasthapanaMuhurta(int ghatasthapanaDateJd, Place place) {
        double ghatasthapanaStart = 0.0D;
        double ghatasthapanaEnd = 0.0D;
        Masa objMasa = new Masa();
        double[] yoga = objMasa.yoga((double)ghatasthapanaDateJd, place);
        double sunrise = Masa.getSunRise((double)ghatasthapanaDateJd, place);
        double[] tithi = objMasa.tithi((double)ghatasthapanaDateJd, place);
        double dayFistPart = Muhurta.getDayDivisons((double)ghatasthapanaDateJd, place, sunrise, 3)[1];
        double[] laganSunRise;
        if ((int)yoga[0] == 27 && (int)tithi[0] == 1) {
            if (Masa.vaara((double)ghatasthapanaDateJd) != 3) {
                laganSunRise = Muhurta.getFifteenMuhurtaForDay((double)ghatasthapanaDateJd, place);
                if (yoga[1] > laganSunRise[7]) {
                    ghatasthapanaStart = sunrise;
                    ghatasthapanaEnd = dayFistPart;
                } else if (yoga[1] < dayFistPart - 0.86D) {
                    ghatasthapanaStart = yoga[1];
                    ghatasthapanaEnd = dayFistPart;
                } else {
                    ghatasthapanaStart = laganSunRise[7];
                    ghatasthapanaEnd = laganSunRise[8];
                }
            } else {
                ghatasthapanaStart = sunrise;
                ghatasthapanaEnd = dayFistPart;
            }
        } else if ((int)tithi[0] == 1) {
            laganSunRise = AscTable.ascAtSunRise((double)ghatasthapanaDateJd, place);
            ghatasthapanaStart = sunrise;
            if ((int)laganSunRise[0] == 6) {
                double laganDur = laganSunRise[1] - sunrise;
                if (laganDur > 0.86D) {
                    ghatasthapanaEnd = laganSunRise[1];
                } else if ((int)yoga[0] == 26) {
                    if (yoga[1] > sunrise + 0.86D && yoga[1] < dayFistPart) {
                        ghatasthapanaEnd = yoga[1];
                    } else {
                        ghatasthapanaEnd = dayFistPart;
                    }
                } else {
                    ghatasthapanaEnd = dayFistPart;
                }
            } else {
                ghatasthapanaEnd = dayFistPart;
            }
        } else if (tithi.length > 2) {
            ghatasthapanaStart = tithi[1];
            ghatasthapanaEnd = dayFistPart;
        }

        return new double[]{ghatasthapanaStart, ghatasthapanaEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2016, 10, 1);
        double[] muhurta = findAshwinNavratriGhatasthapanaMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Ghatasthapana muharta Start: " + objUtil.dms(muhurta[0]));
        System.out.println("Ghatasthapana muharta End  : " + objUtil.dms(muhurta[1]));
    }
}
