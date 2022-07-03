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

public class JanmashtamiMuhurta {
    public JanmashtamiMuhurta() {
    }

    public static double[] findJanmashtamiMuhurta(int JanmashtamiDateJd, Place place) {
        double nishitakaalstart = 0.0D;
        double nishitakaalend = 0.0D;
        double paranastart = 0.0D;
        double sunrise = Masa.getSunRise((double)JanmashtamiDateJd, place);
        double sunset = Masa.getSunSet((double)JanmashtamiDateJd, place);
        double[] muhurta = Muhurta.getNightDivisons((double)JanmashtamiDateJd, place, sunset, 15);
        nishitakaalstart = muhurta[7];
        nishitakaalend = muhurta[8];
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)(JanmashtamiDateJd + 1), place);
        paranastart = (int)tithi[0] == 23 ? tithi[1] : sunrise;
        double isParanaPM = paranastart > (sunrise + sunset) / 2.0D ? 1.0D : 0.0D;
        return new double[]{nishitakaalstart, nishitakaalend, paranastart, 1.0D, isParanaPM};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2015, 9, 5);
        findJanmashtamiMuhurta(jd, NewDelhi);
    }
}
