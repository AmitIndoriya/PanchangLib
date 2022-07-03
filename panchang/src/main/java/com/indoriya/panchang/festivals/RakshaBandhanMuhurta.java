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

public class RakshaBandhanMuhurta {
    public RakshaBandhanMuhurta() {
    }

    public static double[] findRakshaBandhanMuhurta(int rakshaBandhanDateJd, Place place) {
        double rakhsaBandhanStart = 0.0D;
        double rakhsaBandhanend = 0.0D;
        double aparahanMuhStart = 0.0D;
        double aparahanMuhEnd = 0.0D;
        double pradoshMuhStart = 0.0D;
        double pradoshMuhEnd = 0.0D;
        Masa objMasa = new Masa();
        double[] tithiPurnima = objMasa.tithi((double)rakshaBandhanDateJd, place);
        double sunrise = Masa.getSunRise((double)rakshaBandhanDateJd, place);
        double[] bhadra = objMasa.bhadraStartEndTime((double)rakshaBandhanDateJd, place);
        double[] aparahanMuhurta = Muhurta.getDayDivisons((double)rakshaBandhanDateJd, place, Masa.getSunRise((double)rakshaBandhanDateJd, place), 15);
        double[] pradoshMuhurta = Muhurta.getNightDivisons((double)rakshaBandhanDateJd, place, Masa.getSunSet((double)rakshaBandhanDateJd, place), 15);
        if ((int)tithiPurnima[0] == 15) {
            if (bhadra[1] > sunrise) {
                rakhsaBandhanStart = bhadra[1];
                if (tithiPurnima[1] > pradoshMuhurta[3]) {
                    rakhsaBandhanend = pradoshMuhurta[3];
                    pradoshMuhStart = pradoshMuhurta[0];
                    pradoshMuhEnd = pradoshMuhurta[3];
                } else {
                    if (bhadra[1] < pradoshMuhurta[0] && tithiPurnima[1] > pradoshMuhurta[0]) {
                        pradoshMuhStart = pradoshMuhurta[0];
                        pradoshMuhEnd = tithiPurnima[1];
                    }

                    rakhsaBandhanend = tithiPurnima[1];
                }

                if (tithiPurnima[1] > aparahanMuhurta[9]) {
                    if (bhadra[1] > aparahanMuhurta[9] && bhadra[1] < aparahanMuhurta[12]) {
                        aparahanMuhStart = bhadra[1];
                        aparahanMuhEnd = aparahanMuhurta[12];
                    } else if (bhadra[1] < aparahanMuhurta[9]) {
                        aparahanMuhStart = aparahanMuhurta[9];
                        aparahanMuhEnd = aparahanMuhurta[12];
                    }
                }
            } else if (tithiPurnima[1] > pradoshMuhurta[3]) {
                rakhsaBandhanStart = sunrise;
                rakhsaBandhanend = pradoshMuhurta[3];
                aparahanMuhStart = aparahanMuhurta[9];
                aparahanMuhEnd = aparahanMuhurta[12];
                pradoshMuhStart = pradoshMuhurta[0];
                pradoshMuhEnd = pradoshMuhurta[3];
            } else {
                rakhsaBandhanStart = sunrise;
                rakhsaBandhanend = tithiPurnima[1];
                if (tithiPurnima[1] > aparahanMuhurta[9] && tithiPurnima[1] < aparahanMuhurta[12]) {
                    aparahanMuhStart = aparahanMuhurta[9];
                    aparahanMuhEnd = tithiPurnima[1];
                } else if (tithiPurnima[1] > aparahanMuhurta[12]) {
                    aparahanMuhStart = aparahanMuhurta[9];
                    aparahanMuhEnd = aparahanMuhurta[12];
                }

                if (tithiPurnima[1] > pradoshMuhurta[0] && tithiPurnima[1] < pradoshMuhurta[3]) {
                    pradoshMuhStart = pradoshMuhurta[0];
                    pradoshMuhEnd = tithiPurnima[1];
                } else if (tithiPurnima[1] > pradoshMuhurta[3]) {
                    pradoshMuhStart = pradoshMuhurta[0];
                    pradoshMuhEnd = pradoshMuhurta[3];
                }
            }
        } else if ((int)tithiPurnima[0] == 14) {
            if (bhadra[1] > tithiPurnima[1]) {
                if (bhadra[1] > aparahanMuhurta[9] && bhadra[1] < aparahanMuhurta[12]) {
                    aparahanMuhStart = bhadra[1];
                    aparahanMuhEnd = aparahanMuhurta[12];
                } else if (bhadra[1] < aparahanMuhurta[9]) {
                    aparahanMuhStart = aparahanMuhurta[9];
                    aparahanMuhEnd = aparahanMuhurta[12];
                }

                if (bhadra[1] > pradoshMuhurta[0] && bhadra[1] < pradoshMuhurta[3]) {
                    pradoshMuhStart = bhadra[1];
                    pradoshMuhEnd = pradoshMuhurta[3];
                } else if (bhadra[1] < pradoshMuhurta[0]) {
                    pradoshMuhStart = pradoshMuhurta[0];
                    pradoshMuhEnd = pradoshMuhurta[3];
                    rakhsaBandhanStart = bhadra[1];
                    rakhsaBandhanend = pradoshMuhurta[3];
                }

                if (rakhsaBandhanStart == 0.0D && aparahanMuhStart == 0.0D && pradoshMuhStart == 0.0D) {
                    rakhsaBandhanStart = bhadra[1];
                }
            } else if (tithiPurnima[1] < aparahanMuhurta[9]) {
                aparahanMuhStart = aparahanMuhurta[9];
                aparahanMuhEnd = aparahanMuhurta[12];
                pradoshMuhStart = pradoshMuhurta[0];
                pradoshMuhEnd = pradoshMuhurta[3];
            } else if (tithiPurnima[1] < pradoshMuhurta[0]) {
                pradoshMuhStart = pradoshMuhurta[0];
                pradoshMuhEnd = pradoshMuhurta[3];
            }
        }

        return new double[]{rakhsaBandhanStart, rakhsaBandhanend, aparahanMuhStart, aparahanMuhEnd, pradoshMuhStart, pradoshMuhEnd};
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        int jd = (int)Masa.toJulian(2012, 8, 2);
        findRakshaBandhanMuhurta(jd, NewDelhi);
    }
}
