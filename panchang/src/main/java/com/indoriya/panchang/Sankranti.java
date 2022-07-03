package com.indoriya.panchang;

import com.indoriya.panchang.util.Interpolate;
import com.indoriya.panchang.util.Util;

public class Sankranti extends HoroHelper {
    public Sankranti() {
    }

    public static void main(String[] args) {
        int year2017 = 2017;
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        new Place(-33.86785D, 151.20732D, 10.0D);
        Sankranti sankranti = new Sankranti();
        double[] sankrantiArray = sankranti.findSankranti(10, year2017, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Sankranti Moment :" + objUtil.dms(sankrantiArray[0]));
        System.out.println("PKSTART :" + objUtil.dms(sankrantiArray[3]));
        System.out.println("PKEND :" + objUtil.dms(sankrantiArray[4]));
        System.out.println("MAHAPKSTART :" + objUtil.dms(sankrantiArray[5]));
        System.out.println("MAHAPKEND :" + objUtil.dms(sankrantiArray[6]));
        System.out.println(sankrantiArray[2] == 0.0D ? "Sankranti Celebrate : Same Day" : "Sankranti Celebrate : Next Day");
    }

    public double[] findSankranti(int sankranti, int year, Place place) {
        this.place = place;
        int month = sankranti - 9;
        if (month < 0) {
            month += 12;
        }

        int jd = (int) Masa.toJulian(year, month, 14);
        double isSankrantiMomentNextDay = 0.0D;
        double isSankrantiCelebNextDay = 0.0D;
        double[] offsets = new double[]{0.25D, 0.5D, 0.75D, 1.0D};
        double[] solar_long = new double[offsets.length];
        int i = 0;
        double[] var17 = offsets;
        int var16 = offsets.length;

        for (int var15 = 0; var15 < var16; ++var15) {
            double t = var17[var15];
            solar_long[i] = this.solar_longitude_nir((double) jd + t);
            ++i;
        }

        double[] y = Interpolate.unwrap_angles(solar_long);
        double approx_end = Interpolate.inverse_lagrange(offsets, y, 30.0D * (double) (sankranti - 1));
        double sankranti_time = approx_end * 24.0D;
        if (sankranti_time > 24.0D) {
            isSankrantiMomentNextDay = 1.0D;
            sankranti_time -= 24.0D;
        }

        if (sankranti_time < 0.0D) {
            isSankrantiMomentNextDay = -1.0D;
            sankranti_time += 24.0D;
        }

        double sunrise = Masa.getSunRise((double) jd, place);
        double sunset = Masa.getSunSet((double) jd, place);
        double madhyahan = 12.5D;
        double pkstart = 0.0D;
        double pkend = 0.0D;
        double mahapkstart = 0.0D;
        double mahapkend = 0.0D;
        if (sankranti_time < sunset) {
            if (sankranti_time < sunrise) {
                pkstart = sunrise;
                pkend = madhyahan;
                mahapkstart = sunrise;
                mahapkend = sunrise + 2.0D;
            } else if (sankranti_time < madhyahan) {
                pkstart = sankranti_time < sunrise ? sunrise : sankranti_time;
                pkend = madhyahan;
                mahapkstart = pkstart;
                mahapkend = pkstart + 0.4D;
            } else {
                pkstart = sankranti_time;
                pkend = sunset;
                mahapkstart = sankranti_time;
                mahapkend = sankranti_time + 0.4D;
            }
        } else {
            pkstart = sunrise;
            pkend = madhyahan;
            mahapkstart = sunrise;
            mahapkend = sunrise + Util.ghati(5.0D);
            isSankrantiCelebNextDay = 1.0D;
        }

        return new double[]{sankranti_time, isSankrantiMomentNextDay, isSankrantiCelebNextDay, pkstart, pkend, mahapkstart, mahapkend};
    }
}
