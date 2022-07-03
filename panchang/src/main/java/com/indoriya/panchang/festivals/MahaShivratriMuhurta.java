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

public class MahaShivratriMuhurta {
    public MahaShivratriMuhurta() {
    }

    public static void main(String[] args) {
        new Place(28.6139D, 77.209D, 5.5D);
        Place NewYork = new Place(40.71427D, -74.00597D, -5.0D);
        int jd = (int)Masa.toJulian(2016, 3, 7);
        findMahaShivratriMuhurta(jd, NewYork);
    }

    public static double[] findMahaShivratriMuhurta(int mahaShivratriDateJd, Place place) {
        double sunrise = Masa.getSunRise((double)mahaShivratriDateJd, place);
        double sunset = Masa.getSunSet((double)mahaShivratriDateJd, place);
        double[] mahaShivratriNightDiv = Muhurta.getNightDivisons((double)mahaShivratriDateJd, place, sunset, 15);
        double[] mahaShivratriNextDayDiv = Muhurta.getDayDivisons((double)(mahaShivratriDateJd + 1), place, sunrise, 4);
        Masa objMasa = new Masa();
        double[] titihi = objMasa.tithi((double)(mahaShivratriDateJd + 1), place);
        double nishitakaalend;
        double paranaend;
        if ((int)titihi[0] == 29) {
            nishitakaalend = mahaShivratriNightDiv[8];
            if (titihi[1] > mahaShivratriNextDayDiv[3]) {
                paranaend = mahaShivratriNextDayDiv[3];
            } else {
                paranaend = titihi[1];
            }
        } else {
            paranaend = 0.0D;
            nishitakaalend = mahaShivratriNightDiv[8];
            double[] titihiChaturdashi = objMasa.tithi((double)mahaShivratriDateJd, place);
            if ((int)titihiChaturdashi[0] == 29) {
                if (titihiChaturdashi[1] > mahaShivratriNightDiv[7] && titihiChaturdashi[1] < mahaShivratriNightDiv[8]) {
                    nishitakaalend = titihiChaturdashi[1];
                }
            } else if (titihiChaturdashi.length > 2 && (int)titihiChaturdashi[2] == 29 && titihiChaturdashi[3] > mahaShivratriNightDiv[7] && titihiChaturdashi[3] < mahaShivratriNightDiv[8]) {
                nishitakaalend = titihiChaturdashi[3];
            }
        }

        return new double[]{mahaShivratriNightDiv[7], nishitakaalend, sunrise, paranaend, 1.0D};
    }
}
