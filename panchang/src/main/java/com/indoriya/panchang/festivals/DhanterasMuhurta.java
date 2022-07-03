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

public class DhanterasMuhurta {
    public DhanterasMuhurta() {
    }

    public static double[] findDhanterasMuhurta(int dhanterasDateJd, Place place) {
        double dhanterasMuhStart = 0.0D;
        double dhanterasMuhEnd = 0.0D;
        double vrishabhaStart = 0.0D;
        double vrishabhaEnd = 0.0D;
        double sunset = Masa.getSunSet((double)dhanterasDateJd, place);
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)dhanterasDateJd, place);
        double[] nightMuhurta = Muhurta.getNightDivisons((double)dhanterasDateJd, place, sunset, 5);
        double pradoshStart = nightMuhurta[0];
        double pradoshEnd = nightMuhurta[1];
        double[] laganArray = AscTable.ascInADay((double)dhanterasDateJd, place);

        for(int i = 0; i < 24; i += 2) {
            if ((int)laganArray[i] == 2) {
                vrishabhaStart = i != 0 ? laganArray[i - 1] : laganArray[laganArray.length - 1];
                vrishabhaEnd = laganArray[i + 1];
                break;
            }
        }

        if ((int)tithi[0] == 28) {
            dhanterasMuhStart = vrishabhaStart > nightMuhurta[1] ? nightMuhurta[0] : (tithi[1] > vrishabhaStart ? vrishabhaStart : nightMuhurta[0]);
            dhanterasMuhEnd = tithi[1] > nightMuhurta[1] ? (nightMuhurta[1] > vrishabhaEnd ? vrishabhaEnd : nightMuhurta[1]) : tithi[1];
        } else {
            dhanterasMuhStart = tithi[1] > nightMuhurta[0] ? (tithi[1] > vrishabhaStart ? tithi[1] : vrishabhaStart) : (nightMuhurta[0] > vrishabhaStart ? nightMuhurta[0] : vrishabhaStart);
            dhanterasMuhEnd = vrishabhaEnd > nightMuhurta[1] ? nightMuhurta[1] : vrishabhaEnd;
        }

        return new double[]{dhanterasMuhStart, dhanterasMuhEnd, pradoshStart, pradoshEnd, vrishabhaStart, vrishabhaEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2016, 10, 28);
        double[] muhurta = findDhanterasMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Dhanteras muharta Start: " + objUtil.dms(muhurta[0]));
        System.out.println("Dhanteras muharta End  : " + objUtil.dms(muhurta[1]));
        System.out.println("Pradosh Start  : " + objUtil.dms(muhurta[2]));
        System.out.println("Pradosh End  : " + objUtil.dms(muhurta[3]));
        System.out.println("Vrishabha Start: " + objUtil.dms(muhurta[4]));
        System.out.println("Vrishabha End  : " + objUtil.dms(muhurta[5]));
    }
}
