//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;


import com.indoriya.panchang.util.Interpolate;
import com.indoriya.panchang.util.Util;

public class AscTable {
    double jd;
    Place place;
    protected double[] f2 = new double[12];
    protected double[] f3 = new double[12];
    double aya;
    double obliq;
    double sidtime;
    double lat;
    double longt;
    double b6;
    double h6;
    double z1;
    double z2;

    public AscTable() {
    }

    public AscTable(double jd, Place place, double hr, double min, double sec) {
        this.jd = jd;
        this.place = place;
        this.z1 = 3.14159265359D;
        this.z2 = this.z1 / 180.0D;
        this.longt = place.longitude;
        this.lat = place.latitude;
        this.b6 = this.jd_to_b6(jd);
        this.h6 = (hr + min / 60.0D + sec / 3600.0D - (12.0D + place.timezone)) / 24.0D;
        this.bhavInit();
    }

    protected void bhavInit() {
        this.aya = this.getLahiriAyan();
        this.obliq = 23.452294D - 0.0130125D * this.b6;
        double a0 = 24.0D * Util.fract(0.2769D + 100.00214D * this.b6);
        double b0 = this.h6 * 24.0D + 12.0D;
        double c0 = this.longt / 15.0D;
        this.sidtime = 24.0D * Util.fract((a0 + b0 + c0) / 24.0D);
        if (this.sidtime < 0.0D) {
            this.sidtime += 24.0D;
        }

        a0 = this.bhavspl(this.sidtime, this.lat);
        b0 = this.bhavspl(this.sidtime - 6.0D, 0.0D);
        c0 = (180.0D + b0 - a0) / 3.0D;
        if (b0 > a0) {
            c0 -= 120.0D;
        }

        double d0 = 60.0D - c0;
        this.bahvgnl(a0, c0, 1);
        this.bahvgnl(b0 + 180.0D, d0, 4);
        this.bahvgnl(a0 + 180.0D, c0, 7);
        this.bahvgnl(b0, d0, 10);
        this.f3[0] = (this.f2[11] + this.f2[0]) / 2.0D;
        double[] var10000;
        if (this.f2[0] < this.f2[11]) {
            var10000 = this.f3;
            var10000[0] += 180.0D;
        }

        if (this.f3[0] >= 360.0D) {
            var10000 = this.f3;
            var10000[0] -= 360.0D;
        }

        for(int i = 1; i < 12; ++i) {
            this.f3[i] = (this.f2[i - 1] + this.f2[i]) / 2.0D;
            if (this.f2[i] < this.f2[i - 1]) {
                var10000 = this.f3;
                var10000[i] += 180.0D;
            }

            if (this.f3[i] > 360.0D) {
                var10000 = this.f3;
                var10000[i] -= 360.0D;
            }
        }

    }

    protected void bahvgnl(double j0, double k0, int u) {
        for(int l = 0; l < 3; ++l) {
            double m0 = j0 + k0 * (double)l;
            if (m0 >= 360.0D) {
                m0 -= 360.0D;
            }

            int v = u + l - 1;
            this.f2[v] = m0;
        }

    }

    protected double bhavspl(double a0, double c0) {
        double r0 = this.aya;
        double w0 = this.obliq * this.z2;
        double b0 = a0 * 15.0D + 90.0D;
        if (b0 >= 360.0D) {
            b0 -= 360.0D;
        }

        a0 *= this.z1 / 12.0D;
        c0 *= this.z2;
        if (a0 == 0.0D && c0 == 0.0D) {
            return 90.0D;
        } else {
            double g0 = Math.atan(-Math.cos(a0) / (Math.sin(c0) * Math.sin(w0) / Math.cos(c0) + Math.sin(a0) * Math.cos(w0)));
            g0 /= this.z2;
            if (g0 < 0.0D) {
                g0 += 180.0D;
            }

            if (b0 - g0 > 75.0D) {
                g0 += 180.0D;
            }

            g0 -= r0;
            if (g0 < 0.0D) {
                g0 += 360.0D;
            }

            if (g0 > 360.0D) {
                g0 -= 360.0D;
            }

            return g0;
        }
    }

    protected double getLahiriAyan() {
        return 22.460148D + 1.396042D * this.b6 + 3.08E-4D * this.b6 * this.b6;
    }

    double jd_to_b6(double jd) {
        return (jd - 694025.0D - 1720995.0D - this.place.timezone / 24.0D) / 36525.0D;
    }

    double jd_to_b6(double jd, Place place) {
        return (jd - 694025.0D - 1720995.0D - place.timezone / 24.0D) / 36525.0D;
    }

    public static double[] ascAtSunRise(double jd, Place place) {
        double sunrise = Masa.getSunRise(jd, place);
        double[] lagna = new double[3];
        double[] offsets = new double[3];

        for(int i = 0; i < 3; ++i) {
            AscTable ascTable = new AscTable(jd, place, sunrise + (double)i, 0.0D, 0.0D);
            offsets[i] = (double)i;
            lagna[i] = ascTable.f2[0];
        }

        double[] y = Interpolate.unwrap_angles(lagna);
        double lagnaVal = Math.ceil(lagna[0] / 30.0D);
        double degreeToFind = lagnaVal * 30.0D;
        if (degreeToFind < y[0]) {
            degreeToFind += 360.0D;
        }

        double approx_end = Interpolate.inverse_lagrange(offsets, y, degreeToFind);
        double lagnaEndTime = approx_end + sunrise;
        return new double[]{lagnaVal, lagnaEndTime, lagna[0]};
    }

    public static double[] ascInADay(double jd, Place place) {
        double[] lagna = new double[26];
        double[] offsets = new double[26];
        double[] ascValueTime = new double[24];
        double sunrise = Masa.getSunRise(jd, place);

        for(int i = 0; i < 26; ++i) {
            AscTable ascTable = new AscTable(jd, place, sunrise + (double)i, 0.0D, 0.0D);
            offsets[i] = (double)i;
            lagna[i] = ascTable.f2[0];
        }

        double lagnaVal = Math.ceil(lagna[0] / 30.0D);
        double[] y = Interpolate.unwrap_anglesNew(lagna);
        double[] x = offsets;
        int count = 0;

        for(int i = 0; i < 12; ++i) {
            double degreeToFind = lagnaVal * 30.0D;
            if (degreeToFind < y[0]) {
                degreeToFind += 360.0D;
            }

            double approx_end = Interpolate.inverse_lagrange(x, y, degreeToFind);
            ascValueTime[count] = lagnaVal;
            ascValueTime[count + 1] = approx_end + sunrise;
            lagnaVal = lagnaVal++ < 12.0D ? lagnaVal : lagnaVal - 12.0D;
            count += 2;
        }

        return ascValueTime;
    }

    public static void main(String[] args) {
        Util util = new Util();
        Place place = new Place(28.6139D, 77.209D, 5.5D);
        double jd = Masa.toJulian(2019, 3, 28);
        Masa.getSunRise(jd, place);
        double[] ascAtSR = ascAtSunRise(jd, place);
        System.out.println("Rising Sign At SunRise : " + ascAtSR[0] + ", Sign End Time : " + ascAtSR[1] + ", Sign Deg at Sunrise: " + util.dms(ascAtSR[2]));
        double[] ascValTime = ascInADay(jd, place);

        for(int i = 0; i < 24; i += 2) {
            System.out.println("Rising Sign: " + ascValTime[i] + ", End at: " + util.dms(ascValTime[i + 1]));
        }

    }
}
