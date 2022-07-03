//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;


import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

public class AmritSiddhiYogaMuhurta {
    public AmritSiddhiYogaMuhurta() {
    }

    public static double[] findAmritSiddhiYogaMuhurta(double jdNak, Place place) {
        Masa objMasa = new Masa();
        double[] nakshatraArray = objMasa.nakshatraStartEndTime(jdNak, place);
        int vaara = Masa.vaara(jdNak);
        double[] retNakArray = new double[2];
        switch(vaara) {
        case 0:
            if ((int)nakshatraArray[0] == 12) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 13) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 11) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
            break;
        case 1:
            if ((int)nakshatraArray[0] == 4) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 5) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 3) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
            break;
        case 2:
            if ((int)nakshatraArray[0] == 27) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 1) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 26) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
            break;
        case 3:
            if ((int)nakshatraArray[0] == 16) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 17) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 15) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
            break;
        case 4:
            if ((int)nakshatraArray[0] == 7) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 8) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 6) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
            break;
        case 5:
            if ((int)nakshatraArray[0] == 26) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 27) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 25) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
            break;
        case 6:
            if ((int)nakshatraArray[0] == 3) {
                retNakArray[0] = nakshatraArray[2];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            } else if ((int)nakshatraArray[0] == 4) {
                retNakArray[0] = Masa.getSunRise(jdNak, place);
                retNakArray[1] = nakshatraArray[2];
            } else if (nakshatraArray.length > 4 && (int)nakshatraArray[0] == 2) {
                retNakArray[0] = nakshatraArray[6];
                retNakArray[1] = Masa.getSunRise(jdNak + 1.0D, place) + 24.0D;
            }
        }

        return retNakArray;
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2020, 11, 14);
        double[] muhurta = findAmritSiddhiYogaMuhurta((double)jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Muharta Start: " + objUtil.dms(muhurta[0]));
        System.out.println("Muharta End  : " + objUtil.dms(muhurta[1]));
    }
}
