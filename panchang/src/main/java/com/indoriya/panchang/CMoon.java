//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

public class CMoon {
    double PI = 3.141592653589793D;
    double DR;
    double K1;
    boolean Moonrise;
    boolean Moonset;
    int[] Rise_time;
    int[] Set_time;
    double Rise_az;
    double Set_az;
    double[] Sky;
    double[] RAn;
    double[] Dec;
    double[] VHz;

    public CMoon() {
        this.DR = this.PI / 180.0D;
        this.K1 = 15.0D * this.DR * 1.0027379D;
        this.Moonrise = false;
        this.Moonset = false;
        this.Rise_time = new int[2];
        this.Set_time = new int[2];
        this.Rise_az = 0.0D;
        this.Set_az = 0.0D;
        this.Sky = new double[]{0.0D, 0.0D, 0.0D};
        this.RAn = new double[]{0.0D, 0.0D, 0.0D};
        this.Dec = new double[]{0.0D, 0.0D, 0.0D};
        this.VHz = new double[]{0.0D, 0.0D, 0.0D};
    }

    public double[] getMoonRiseSetTime(double jd, Place place) {
        double rise = Masa.getSunRise(jd, place);
        jd -= 2451545.5D;
        double[] returnValues = new double[4];
        double lat = place.latitude;
        double lon = place.longitude;
        double timeZone = place.timezone;
        double[][] mp = new double[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                mp[i][j] = 0.0D;
            }
        }

        lon /= 360.0D;
        double tz = timeZone / 24.0D * -1.0D;
        double t0 = this.lst(lon, jd, tz);
        jd += tz;

        for(int k = 0; k < 3; ++k) {
            this.moon(jd);
            mp[k][0] = this.Sky[0];
            mp[k][1] = this.Sky[1];
            mp[k][2] = this.Sky[2];
            jd += 0.5D;
        }

        if (mp[1][0] <= mp[0][0]) {
            mp[1][0] += 2.0D * this.PI;
        }

        if (mp[2][0] <= mp[1][0]) {
            mp[2][0] += 2.0D * this.PI;
        }

        this.RAn[0] = mp[0][0];
        this.Dec[0] = mp[0][1];
        this.Moonrise = false;
        this.Moonset = false;
        double ph = 0.0D;

        for(double l = rise - 1.0D; l < 24.0D + rise; ++l) {
            ph = (l + 1.0D) / 24.0D;
            this.RAn[2] = this.interpolate(mp[0][0], mp[1][0], mp[2][0], ph);
            this.Dec[2] = this.interpolate(mp[0][1], mp[1][1], mp[2][1], ph);
            this.VHz[2] = this.testRiseSetmoon(l, timeZone, t0, lat, mp[1][2]);
            this.RAn[0] = this.RAn[2];
            this.Dec[0] = this.Dec[2];
            this.VHz[0] = this.VHz[2];
        }

        returnValues[0] = (double)this.Rise_time[0] + (double)this.Rise_time[1] / 60.0D;
        returnValues[1] = this.Rise_az;
        returnValues[2] = (double)this.Set_time[0] + (double)this.Set_time[1] / 60.0D;
        returnValues[3] = this.Set_az;
        if (this.Moonrise) {
            returnValues[1] = this.Rise_az;
        } else {
            returnValues[1] = 0.0D;
        }

        if (this.Moonset) {
            returnValues[3] = this.Set_az;
        } else {
            returnValues[3] = 0.0D;
        }

        return returnValues;
    }

    public double[] getMoonRiseTime(double jd, Place place) {
        double rise = Masa.getSunRise(jd, place);
        jd -= 2451545.5D;
        double[] returnValues = new double[2];
        double lat = place.latitude;
        double lon = place.longitude;
        double timeZone = place.timezone;
        double[][] mp = new double[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                mp[i][j] = 0.0D;
            }
        }

        lon /= 360.0D;
        double tz = timeZone / 24.0D * -1.0D;
        double t0 = this.lst(lon, jd, tz);
        jd += tz;

        for(int k = 0; k < 3; ++k) {
            this.moon(jd);
            mp[k][0] = this.Sky[0];
            mp[k][1] = this.Sky[1];
            mp[k][2] = this.Sky[2];
            jd += 0.5D;
        }

        if (mp[1][0] <= mp[0][0]) {
            mp[1][0] += 2.0D * this.PI;
        }

        if (mp[2][0] <= mp[1][0]) {
            mp[2][0] += 2.0D * this.PI;
        }

        this.RAn[0] = mp[0][0];
        this.Dec[0] = mp[0][1];
        this.Moonrise = false;
        this.Moonset = false;
        double ph = 0.0D;

        for(double l = rise; l < 24.0D + rise; ++l) {
            ph = (l + 1.0D) / 24.0D;
            this.RAn[2] = this.interpolate(mp[0][0], mp[1][0], mp[2][0], ph);
            this.Dec[2] = this.interpolate(mp[0][1], mp[1][1], mp[2][1], ph);
            this.VHz[2] = this.testRiseSetmoon(l, timeZone, t0, lat, mp[1][2]);
            this.RAn[0] = this.RAn[2];
            this.Dec[0] = this.Dec[2];
            this.VHz[0] = this.VHz[2];
        }

        returnValues[0] = (double)this.Rise_time[0] + (double)this.Rise_time[1] / 60.0D;
        returnValues[1] = this.Rise_az;
        if (this.Moonrise) {
            returnValues[1] = this.Rise_az;
        } else {
            returnValues[1] = 0.0D;
        }

        return returnValues;
    }

    public double[] getMoonSetTime(double jd, Place place) {
        double rise = Masa.getSunRise(jd, place);
        jd -= 2451545.5D;
        double[] returnValues = new double[2];
        double lat = place.latitude;
        double lon = place.longitude;
        double timeZone = place.timezone;
        double[][] mp = new double[3][3];

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                mp[i][j] = 0.0D;
            }
        }

        lon /= 360.0D;
        double tz = timeZone / 24.0D * -1.0D;
        double t0 = this.lst(lon, jd, tz);
        jd += tz;

        for(int k = 0; k < 3; ++k) {
            this.moon(jd);
            mp[k][0] = this.Sky[0];
            mp[k][1] = this.Sky[1];
            mp[k][2] = this.Sky[2];
            jd += 0.5D;
        }

        if (mp[1][0] <= mp[0][0]) {
            mp[1][0] += 2.0D * this.PI;
        }

        if (mp[2][0] <= mp[1][0]) {
            mp[2][0] += 2.0D * this.PI;
        }

        this.RAn[0] = mp[0][0];
        this.Dec[0] = mp[0][1];
        this.Moonset = false;
        double ph = 0.0D;

        for(double l = rise; l < 24.0D + rise; ++l) {
            ph = (l + 1.0D) / 24.0D;
            this.RAn[2] = this.interpolate(mp[0][0], mp[1][0], mp[2][0], ph);
            this.Dec[2] = this.interpolate(mp[0][1], mp[1][1], mp[2][1], ph);
            this.VHz[2] = this.testRiseSetmoon(l, timeZone, t0, lat, mp[1][2]);
            this.RAn[0] = this.RAn[2];
            this.Dec[0] = this.Dec[2];
            this.VHz[0] = this.VHz[2];
        }

        returnValues[0] = (double)this.Set_time[0] + (double)this.Set_time[1] / 60.0D;
        returnValues[1] = this.Set_az;
        if (this.Moonset) {
            returnValues[1] = this.Set_az;
        } else {
            returnValues[1] = 0.0D;
        }

        return returnValues;
    }

    private void moon(double jd) {
        double h = 0.606434D + 0.03660110129D * jd;
        double m = 0.374897D + 0.03629164709D * jd;
        double f = 0.259091D + 0.0367481952D * jd;
        double d = 0.827362D + 0.03386319198D * jd;
        double n = 0.347343D - 1.4709391E-4D * jd;
        double g = 0.993126D + 0.0027377785D * jd;
        h -= Math.floor(h);
        m -= Math.floor(m);
        f -= Math.floor(f);
        d -= Math.floor(d);
        n -= Math.floor(n);
        g -= Math.floor(g);
        h = h * 2.0D * this.PI;
        m = m * 2.0D * this.PI;
        f = f * 2.0D * this.PI;
        d = d * 2.0D * this.PI;
        n = n * 2.0D * this.PI;
        g = g * 2.0D * this.PI;
        double v = 0.39558D * Math.sin(f + n);
        v += 0.082D * Math.sin(f);
        v += 0.03257D * Math.sin(m - f - n);
        v += 0.01092D * Math.sin(m + f + n);
        v += 0.00666D * Math.sin(m - f);
        v -= 0.00644D * Math.sin(m + f - 2.0D * d + n);
        v -= 0.00331D * Math.sin(f - 2.0D * d + n);
        v -= 0.00304D * Math.sin(f - 2.0D * d);
        v -= 0.0024D * Math.sin(m - f - 2.0D * d - n);
        v += 0.00226D * Math.sin(m + f);
        v -= 0.00108D * Math.sin(m + f - 2.0D * d);
        v -= 7.9E-4D * Math.sin(f - n);
        v += 7.8E-4D * Math.sin(f + 2.0D * d + n);
        double u = 1.0D - 0.10828D * Math.cos(m);
        u -= 0.0188D * Math.cos(m - 2.0D * d);
        u -= 0.01479D * Math.cos(2.0D * d);
        u += 0.00181D * Math.cos(2.0D * m - 2.0D * d);
        u -= 0.00147D * Math.cos(2.0D * m);
        u -= 0.00105D * Math.cos(2.0D * d - g);
        u -= 7.5E-4D * Math.cos(m - 2.0D * d + g);
        double w = 0.10478D * Math.sin(m);
        w -= 0.04105D * Math.sin(2.0D * f + 2.0D * n);
        w -= 0.0213D * Math.sin(m - 2.0D * d);
        w -= 0.01779D * Math.sin(2.0D * f + n);
        w += 0.01774D * Math.sin(n);
        w += 0.00987D * Math.sin(2.0D * d);
        w -= 0.00338D * Math.sin(m - 2.0D * f - 2.0D * n);
        w -= 0.00309D * Math.sin(g);
        w -= 0.0019D * Math.sin(2.0D * f);
        w -= 0.00144D * Math.sin(m + n);
        w -= 0.00144D * Math.sin(m - 2.0D * f - n);
        w -= 0.00113D * Math.sin(m + 2.0D * f + 2.0D * n);
        w -= 9.4E-4D * Math.sin(m - 2.0D * d + g);
        w -= 9.2E-4D * Math.sin(2.0D * m - 2.0D * d);
        double s = w / Math.sqrt(u - v * v);
        this.Sky[0] = h + Math.atan(s / Math.sqrt(1.0D - s * s));
        s = v / Math.sqrt(u);
        this.Sky[1] = Math.atan(s / Math.sqrt(1.0D - s * s));
        this.Sky[2] = 60.40974D * Math.sqrt(u);
    }

    private double testRiseSetmoon(double k, double zone, double t0, double lat, double plx) {
        double[] ha = new double[]{0.0D, 0.0D, 0.0D};
        if (this.RAn[2] < this.RAn[0]) {
            this.RAn[2] += 2.0D * this.PI;
        }

        ha[0] = t0 - this.RAn[0] + k * this.K1;
        ha[2] = t0 - this.RAn[2] + k * this.K1 + this.K1;
        ha[1] = (ha[2] + ha[0]) / 2.0D;
        this.Dec[1] = (this.Dec[2] + this.Dec[0]) / 2.0D;
        double s = Math.sin(this.DR * lat);
        double c = Math.cos(this.DR * lat);
        double z = Math.cos(this.DR * (90.567D - 41.685D / plx));
        if (k <= 0.0D) {
            this.VHz[0] = s * Math.sin(this.Dec[0]) + c * Math.cos(this.Dec[0]) * Math.cos(ha[0]) - z;
        }

        this.VHz[2] = s * Math.sin(this.Dec[2]) + c * Math.cos(this.Dec[2]) * Math.cos(ha[2]) - z;
        if (this.sgn(this.VHz[0]) == this.sgn(this.VHz[2])) {
            return this.VHz[2];
        } else {
            this.VHz[1] = s * Math.sin(this.Dec[1]) + c * Math.cos(this.Dec[1]) * Math.cos(ha[1]) - z;
            double a = 2.0D * this.VHz[2] - 4.0D * this.VHz[1] + 2.0D * this.VHz[0];
            double b = 4.0D * this.VHz[1] - 3.0D * this.VHz[0] - this.VHz[2];
            double d = b * b - 4.0D * a * this.VHz[0];
            if (d < 0.0D) {
                return this.VHz[2];
            } else {
                d = Math.sqrt(d);
                double e = (-b + d) / (2.0D * a);
                if (e > 1.0D || e < 0.0D) {
                    e = (-b - d) / (2.0D * a);
                }

                double time = k + e + 0.0D;
                int hr = (int)Math.floor(time);
                int min = (int)Math.floor((time - (double)hr) * 60.0D);
                double hz = ha[0] + e * (ha[2] - ha[0]);
                double nz = -Math.cos(this.Dec[1]) * Math.sin(hz);
                double dz = c * Math.sin(this.Dec[1]) - s * Math.cos(this.Dec[1]) * Math.cos(hz);
                double az = Math.atan2(nz, dz) / this.DR;
                if (az < 0.0D) {
                    az += 360.0D;
                }

                if (this.VHz[0] < 0.0D && this.VHz[2] > 0.0D) {
                    this.Rise_time[0] = hr;
                    this.Rise_time[1] = min;
                    this.Rise_az = az;
                    this.Moonrise = true;
                }

                if (this.VHz[0] > 0.0D && this.VHz[2] < 0.0D) {
                    this.Set_time[0] = hr;
                    this.Set_time[1] = min;
                    this.Set_az = az;
                    this.Moonset = true;
                }

                return this.VHz[2];
            }
        }
    }

    private double interpolate(double f0, double f1, double f2, double p) {
        double a = f1 - f0;
        double b = f2 - f1 - a;
        double f = f0 + p * (2.0D * a + b * (2.0D * p - 1.0D));
        return f;
    }

    private int sgn(double x) {
        byte rv;
        if (x > 0.0D) {
            rv = 1;
        } else if (x < 0.0D) {
            rv = -1;
        } else {
            rv = 0;
        }

        return rv;
    }

    private double lst(double lon, double jd, double z) {
        double s = 24110.5D + 8640184.813D * jd / 36525.0D + 86636.6D * z + 86400.0D * lon;
        s /= 86400.0D;
        s -= Math.floor(s);
        return s * 360.0D * this.DR;
    }
}
