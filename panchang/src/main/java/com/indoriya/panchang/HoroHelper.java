//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;


import com.indoriya.panchang.util.Util;

public class HoroHelper {
    double z1 = 3.14159265359D;
    double z2;
    double ps;
    double pt;
    protected Place place;

    public HoroHelper() {
        this.z2 = this.z1 / 180.0D;
        this.place = new Place(27.0D, 78.0D, 5.5D);
    }

    double jd_to_b6(double jd) {
        return (jd - 694025.0D - 1720995.0D - (12.0D + this.place.timezone) / 24.0D) / 36525.0D;
    }

    double jd_to_b6(double jd, Place place) {
        return (jd - 694025.0D - 1720995.0D - (12.0D + place.timezone) / 24.0D) / 36525.0D;
    }

    double b6_to_jd(double b6) {
        return b6 * 36525.0D + 694025.0D + 1720995.0D;
    }

    protected double ayan(double jd) {
        double b6 = this.jd_to_b6(jd);
        return 22.460148D + 1.396042D * b6 + 3.08E-4D * b6 * b6;
    }

    double solar_longitude(double jd) {
        return (this.sun(this.jd_to_b6(jd)) + this.ayan(jd)) % 360.0D;
    }

    double lunar_longitude(double jd) {
        return (this.moon(this.jd_to_b6(jd)) + this.ayan(jd)) % 360.0D;
    }

    double solar_longitude_nir(double jd) {
        return this.sun(this.jd_to_b6(jd)) % 360.0D;
    }

    double lunar_longitude_nir(double jd) {
        return this.moon(this.jd_to_b6(jd)) % 360.0D;
    }

    protected double moon(double b6) {
        double g1 = 360.0D * Util.fract(0.71455D + 99.99826D * b6);
        double h1 = 258.76D + 0.323D * b6;
        double a0 = 360.0D * Util.fract(0.68882D + 1336.851353D * b6);
        double b0 = 360.0D * Util.fract(0.8663D + 11.298994D * b6 - 3.0E-5D * b6 * b6);
        double c0 = 360.0D * Util.fract(0.65756D - 5.376495D * b6);
        if (c0 < 0.0D) {
            c0 += 360.0D;
        }

        double g0 = this.z2 * (a0 - b0);
        double e0 = this.z2 * (g1 - h1);
        double d0 = this.z2 * (a0 - g1);
        double f0 = this.z2 * (a0 - c0);
        double l0 = a0 + 6.2888D * Math.sin(g0) + 0.2136D * Math.sin(2.0D * g0) + 0.01D * Math.sin(3.0D * g0) + 1.274D * Math.sin(2.0D * d0 - g0) + 0.0085D * Math.sin(4.0D * d0 - 2.0D * g0);
        l0 = l0 - 0.0347D * Math.sin(d0) + 0.6583D * Math.sin(2.0D * d0) + 0.0039D * Math.sin(4.0D * d0) - 0.1856D * Math.sin(e0) - 0.0021D * Math.sin(2.0D * e0) + 0.0052D * Math.sin(g0 - d0);
        l0 = l0 - 0.0588D * Math.sin(2.0D * g0 - 2.0D * d0) + 0.0572D * Math.sin(2.0D * d0 - g0 - e0) + 0.0533D * Math.sin(g0 + 2.0D * d0) + 0.0458D * Math.sin(2.0D * d0 - e0) + 0.041D * Math.sin(g0 - e0) - 0.0305D * Math.sin(g0 + e0);
        l0 = l0 - 0.0237D * Math.sin(2.0D * f0 - g0) - 0.0153D * Math.sin(2.0D * f0 - 2.0D * d0) + 0.0107D * Math.sin(4.0D * d0 - g0) - 0.0079D * Math.sin(-g0 + e0 + 2.0D * d0) - 0.0068D * Math.sin(e0 + 2.0D * d0) + 0.005D * Math.sin(e0 + d0);
        l0 = l0 - 0.0023D * Math.sin(g0 + d0) + 0.004D * Math.sin(2.0D * g0 + 2.0D * d0) + 0.004D * Math.sin(g0 - e0 + 2.0D * d0) - 0.0037D * Math.sin(3.0D * g0 - 2.0D * d0) - 0.0026D * Math.sin(g0 - 2.0D * d0 + 2.0D * f0) + 0.0027D * Math.sin(2.0D * g0 - e0);
        l0 = l0 - 0.0024D * Math.sin(2.0D * g0 + e0 - 2.0D * d0) + 0.0022D * Math.sin(2.0D * d0 - 2.0D * e0) - 0.0021D * Math.sin(2.0D * g0 + e0) + 0.0021D * Math.sin(c0 * this.z2) + 0.0021D * Math.sin(2.0D * d0 - g0 - 2.0D * e0);
        l0 = l0 - 0.0018D * Math.sin(g0 + 2.0D * d0 - 2.0D * f0) + 0.0012D * Math.sin(4.0D * d0 - g0 - e0) - 8.0E-4D * Math.sin(3.0D * d0 - g0);
        double r0 = this.z2 * 2.0D * (l0 - c0);
        double d3 = l0 - 0.1143D * Math.sin(r0) + 0.004D;
        if (d3 >= 360.0D) {
            d3 -= 360.0D;
        }

        if (d3 < 0.0D) {
            d3 += 360.0D;
        }

        return d3;
    }

    protected double sun(double b6) {
        double g0 = 360.0D * Util.fract(0.71455D + 99.99826D * b6);
        double h0 = 258.76D + 0.323D * b6;
        double p0 = 0.0D;
        double e0 = 0.016751D - 4.2E-5D * b6;
        double q0 = 0.0D;
        double a0 = 1.0D;
        int pno = 1;
        return this.planet(g0, h0, p0, e0, q0, a0, pno);
    }

    protected double planet(double pg, double ph, double pp, double pe, double pq, double pa, int pno) {
        double pm = pg - ph;
        if (pm < 0.0D) {
            pm += 360.0D;
        }

        double pb = pm * this.z2;
        double pf = pb + pe * Math.sin(pb);

        double pc;
        double pd;
        do {
            pc = pf - pe * Math.sin(pf) - pb;
            pd = 1.0D - pe * Math.cos(pf);
            pf -= pc / pd;
        } while(Math.abs(pc / pd) > 0.01D);

        double pr = pa * (1.0D - pe * Math.cos(pf));
        double e1 = Math.atan(pe / Math.sqrt(1.0D - pe * pe));
        double e2 = this.z1 / 4.0D - e1 / 2.0D;
        double e3 = Math.tan(e2);
        double e4 = Math.tan(pf / 2.0D);
        double v1 = Math.atan(e4 / e3);
        if (v1 < 0.0D) {
            v1 += this.z1;
        }

        double pv = 2.0D * v1;
        pc = ph * this.z2;
        pd = pp * this.z2;
        pb = pq * this.z2;
        double pj = pv + pc;
        double pk = pj - pd;
        double pl = 1.0D - Math.cos(pb);
        double px = (Math.cos(pj) + Math.sin(pk) * Math.sin(pd) * pl) * pr;
        double py = (Math.sin(pj) - Math.sin(pk) * Math.cos(pd) * pl) * pr;
        if (pno == 1) {
            this.ps = px;
            this.pt = py;
        }

        pc = this.ps + px;
        pd = this.pt + py;
        pm = Math.atan(pd / pc) / this.z2;
        if (pc < 0.0D) {
            pm += 180.0D;
        } else if (pd < 0.0D) {
            pm += 360.0D;
        }

        return pm;
    }
}
