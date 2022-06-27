//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

import com.indoriya.panchang.suntimes.SunTimes;
import com.indoriya.panchang.suntimes.Time;
import com.indoriya.panchang.util.Util;
import java.util.Arrays;
import java.util.List;

public class Masa extends HoroHelper {
    public Masa() {
    }

    public int getTithiD(double jd) {
        double tithi = (this.lunar_longitude(jd) - this.solar_longitude(jd)) / 12.0D;
        if (tithi < 0.0D) {
            tithi += 30.0D;
        }

        return (int)tithi + 1;
    }

    public int getNakshatraD(double jd) {
        return (int)(this.lunar_longitude(jd) * 0.075D) + 1;
    }

    public int getYogaD(double jd) {
        double yoga = (this.lunar_longitude(jd) + this.solar_longitude(jd)) * 0.075D;
        if (yoga > 27.0D) {
            yoga -= 27.0D;
        }

        return (int)yoga + 1;
    }

    public int getKaranD(double jd) {
        double karan = (this.lunar_longitude(jd) - this.solar_longitude(jd)) * 0.075D;
        if (karan < 0.0D) {
            karan += 360.0D;
        }

        return (int)(karan / 6.0D) + 1;
    }

    public static double sunriseJd(double jd, Place place) {
        return jd + getSunRise(jd, place) / 24.0D;
    }

    public static double getSunRise(double jd, Place place) {
        int[] fromJul = fromJulian(jd);
        int yr = fromJul[0];
        int mon = fromJul[1];
        int date = fromJul[2];
        double lg = place.longitude;
        double lt = place.latitude;
        double tz = place.timezone;
        return getSunRise(yr, mon, date, lg, lt, tz);
    }

    public static double getSunSet(double jd, Place place) {
        int[] fromJul = fromJulian(jd);
        int yr = fromJul[0];
        int mon = fromJul[1];
        int date = fromJul[2];
        double lg = place.longitude;
        double lt = place.latitude;
        double tz = place.timezone;
        return getSunSet(yr, mon, date, lg, lt, tz);
    }

    public static double getSunRise(int yr, int mon, int date, double lg, double lt, double tz) {
        double sunrise = 0.0D;

        try {
            Time t1 = SunTimes.getSunriseTimeUTC(yr, mon, date, lg, lt, 90.83333333333333D);
            sunrise = decimal(t1.getHour(), t1.getMinute(), t1.getSecond()) + tz;
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        Mod mod24 = new Mod(24);
        return mod24.correct(sunrise);
    }

    public static double getSunSet(int yr, int mon, int date, double lg, double lt, double tz) {
        double sunset = 0.0D;

        try {
            Time t2 = SunTimes.getSunsetTimeUTC(yr, mon, date, lg, lt, 90.83333333333333D);
            sunset = decimal(t2.getHour(), t2.getMinute(), t2.getSecond()) + tz;
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        Mod mod24 = new Mod(24);
        return mod24.correct(sunset);
    }

    public static double decimal(int deg, int min, int sec) {
        double temp = (double)((deg * 60 + min) * 60 + sec);
        double res = temp / 3600.0D;
        return res;
    }

    public double[] nakshatra(double jd, Place place) {
        this.place = place;
        double lat = place.latitude;
        double lon = place.longitude;
        double tz = place.timezone;
        double rise = sunriseJd(jd, place);
        double[] offsets = new double[]{0.0D, 0.25D, 0.5D, 0.75D, 1.0D};
        double[] longitudes = new double[offsets.length];
        int i = 0;
        double[] var19 = offsets;
        int var18 = offsets.length;

        double nak;
        for(int var17 = 0; var17 < var18; ++var17) {
            nak = var19[var17];
            longitudes[i] = this.lunar_longitude_nir(rise + nak) % 360.0D;
            ++i;
        }

        nak = Math.ceil(longitudes[0] * 27.0D / 360.0D);
        double[] y = this.unwrap_angles(longitudes);
        double approx_end = this.inverse_lagrange(offsets, y, nak * 360.0D / 27.0D);
        double ends = (rise - jd + approx_end) * 24.0D;
        double[] answer = new double[]{nak, ends};
        double nak_tmrw = Math.ceil(longitudes[longitudes.length - 1] * 27.0D / 360.0D);
        if (nak_tmrw < nak) {
            nak_tmrw += 27.0D;
        }

        boolean isSkipped = (nak_tmrw - nak) % 27.0D > 1.0D;
        if (isSkipped) {
            double leap_nak = nak + 1.0D;
            approx_end = this.inverse_lagrange(offsets, longitudes, leap_nak * 360.0D / 27.0D);
            double nak_ends = (rise - jd + approx_end) * 24.0D;
            return new double[]{nak, ends, this.mod(leap_nak, 27.0D), nak_ends};
        } else {
            return answer;
        }
    }

    double mod(double value, double base) {
        double modulus = value % base;
        return (int)value == (int)base ? base : modulus;
    }

    double[] kppan(double jd, Place place) {
        double[] y1 = new double[]{7.0D, 20.0D, 6.0D, 10.0D, 7.0D, 18.0D, 16.0D, 19.0D, 17.0D};
        double lat = place.latitude;
        double lon = place.longitude;
        double tz = place.timezone;
        double rise = sunriseJd(jd, place);
        double[] offsets = new double[]{0.0D, 0.25D, 0.5D, 0.75D, 1.0D};
        double[] longitudes = new double[offsets.length];
        int i = 0;
        double[] var20 = offsets;
        int var19 = offsets.length;

        double nak;
        for(int var18 = 0; var18 < var19; ++var18) {
            nak = var20[var18];
            longitudes[i] = this.lunar_longitude_nir(rise + nak) % 360.0D;
            ++i;
        }

        nak = Math.ceil(longitudes[0] * 27.0D / 360.0D);
        double[] y = this.unwrap_angles(longitudes);
        double pointToSearch = (nak - 1.0D) * 360.0D / 27.0D + y1[(int)nak - 1] / 120.0D * 13.33333D;
        double approx_end = this.inverse_lagrange(offsets, y, pointToSearch);
        double ends = (rise - jd + approx_end) * 24.0D;
        double[] answer = new double[]{nak, ends};
        return answer;
    }

    public double[] moonsign(double jd, Place place) {
        this.place = place;
        double lat = place.latitude;
        double lon = place.longitude;
        double tz = place.timezone;
        double rise = sunriseJd(jd, place);
        double[] offsets = new double[]{0.0D, 0.6D, 1.2D, 1.8D, 2.4D};
        double[] longitudes = new double[offsets.length];
        int i = 0;
        double[] var19 = offsets;
        int var18 = offsets.length;

        double nak;
        for(int var17 = 0; var17 < var18; ++var17) {
            nak = var19[var17];
            longitudes[i] = this.lunar_longitude_nir(rise + nak) % 360.0D;
            ++i;
        }

        nak = Math.ceil(longitudes[0] * 12.0D / 360.0D);
        double[] y = this.unwrap_angles(longitudes);
        double approx_end = this.inverse_lagrange(offsets, y, nak * 360.0D / 12.0D);
        double ends = (rise - jd + approx_end) * 24.0D;
        double[] answer = new double[]{nak, ends};
        return answer;
    }

    public double[] yoga(double jd, Place place) {
        this.place = place;
        double rise = sunriseJd(jd, place);
        double lunar_long = this.lunar_longitude_nir(rise) % 360.0D;
        double solar_long = this.solar_longitude_nir(rise) % 360.0D;
        double total = (lunar_long + solar_long) % 360.0D;
        double yog = Math.ceil(total * 27.0D / 360.0D);
        double degrees_left = yog * 13.333333333333334D - total;
        double[] offsets = new double[]{0.25D, 0.5D, 0.75D, 1.0D};
        double[] lunar_long_diff = new double[offsets.length];
        double[] solar_long_diff = new double[offsets.length];
        int i = 0;
        double[] var24 = offsets;
        int var23 = offsets.length;

        for(int var22 = 0; var22 < var23; ++var22) {
            double t = var24[var22];
            lunar_long_diff[i] = this.degreeDifference(this.lunar_longitude(rise + t), this.lunar_longitude(rise));
            solar_long_diff[i] = this.degreeDifference(this.solar_longitude(rise + t), this.solar_longitude(rise));
            ++i;
        }

        i = 0;
        double[] total_motion = new double[offsets.length];
        double[] var25 = offsets;
        int var43 = offsets.length;

        for(var23 = 0; var23 < var43; ++var23) {
            double var10000 = var25[var23];
            total_motion[i] = lunar_long_diff[i] + solar_long_diff[i];
            ++i;
        }

        double approx_end = this.inverse_lagrange(offsets, total_motion, degrees_left);
        double ends = (rise + approx_end - jd) * 24.0D;
        double[] answer = new double[]{yog, ends};
        double lunar_long_tmrw = this.lunar_longitude_nir(rise + 1.0D) % 360.0D;
        double solar_long_tmrw = this.solar_longitude_nir(rise + 1.0D) % 360.0D;
        double total_tmrw = (lunar_long_tmrw + solar_long_tmrw) % 360.0D;
        double tomorrow = Math.ceil(total_tmrw * 27.0D / 360.0D);
        if (tomorrow < yog) {
            tomorrow += 27.0D;
        }

        boolean isSkipped = (tomorrow - yog) % 27.0D > 1.0D;
        if (isSkipped) {
            double leap_yog = yog + 1.0D;
            degrees_left = leap_yog * 13.333333333333334D - total;
            approx_end = this.inverse_lagrange(offsets, total_motion, degrees_left);
            double ends_leap_yoga = (rise + approx_end - jd) * 24.0D;
            return new double[]{yog, ends, this.mod(leap_yog, 27.0D), ends_leap_yoga};
        } else {
            return answer;
        }
    }

    int karanaold(double jd, Place place) {
        this.place = place;
        double rise = sunriseJd(jd, place);
        double solar_long = this.solar_longitude(rise);
        double lunar_long = this.lunar_longitude(rise);
        double moon_phase = this.degreeDifference(lunar_long, solar_long);
        double today = Math.ceil(moon_phase / 6.0D);
        double degrees_left = today * 6.0D - moon_phase;
        return (int)today;
    }

    public double[] karana(double jd, Place place) {
        this.place = place;
        double rise = sunriseJd(jd, place);
        double moon_phase = this.lunar_phase(rise);
        double today = Math.ceil(moon_phase / 6.0D);
        double degrees_left = today * 6.0D - moon_phase;
        double[] offsets = new double[]{0.25D, 0.5D, 0.75D, 1.0D};
        double[] lunar_long_diff = new double[offsets.length];
        double[] solar_long_diff = new double[offsets.length];
        int i = 0;
        double[] var20 = offsets;
        int var19 = offsets.length;

        for(int var18 = 0; var18 < var19; ++var18) {
            double t = var20[var18];
            lunar_long_diff[i] = this.degreeDifference(this.lunar_longitude(rise + t), this.lunar_longitude(rise));
            solar_long_diff[i] = this.degreeDifference(this.solar_longitude(rise + t), this.solar_longitude(rise));
            ++i;
        }

        i = 0;
        double[] relative_motion = new double[offsets.length];
        double[] var21 = offsets;
        int var36 = offsets.length;

        for(var19 = 0; var19 < var36; ++var19) {
            double var10000 = var21[var19];
            relative_motion[i] = lunar_long_diff[i] - solar_long_diff[i];
            ++i;
        }

        double approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
        double ends = (rise + approx_end - jd) * 24.0D;
        double[] answer = new double[]{today, ends};
        double moon_phase_tmrw = this.lunar_phase(rise + 1.0D);
        double tomorrow = Math.ceil(moon_phase_tmrw / 6.0D);
        if (tomorrow < today) {
            tomorrow += 60.0D;
        }

        boolean isSkipped = (tomorrow - today) % 60.0D > 1.0D;
        if (isSkipped) {
            double leap_tithi = today + 1.0D;
            degrees_left = leap_tithi * 6.0D - moon_phase;
            approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
            double ends_leap_tithi = (rise + approx_end - jd) * 24.0D;
            return new double[]{today, ends, this.mod(leap_tithi, 60.0D), ends_leap_tithi};
        } else {
            return answer;
        }
    }

    public double[] karanaNew(double jd, Place place) {
        this.place = place;
        double rise = sunriseJd(jd, place);
        double moon_phase = this.lunar_phase(rise);
        double today = Math.ceil(moon_phase / 6.0D);
        double degrees_left = today * 6.0D - moon_phase;
        double[] offsets = new double[]{0.25D, 0.5D, 0.75D, 1.0D};
        double[] lunar_long_diff = new double[offsets.length];
        double[] solar_long_diff = new double[offsets.length];
        int i = 0;
        double[] var20 = offsets;
        int var19 = offsets.length;

        for(int var18 = 0; var18 < var19; ++var18) {
            double t = var20[var18];
            lunar_long_diff[i] = this.degreeDifference(this.lunar_longitude(rise + t), this.lunar_longitude(rise));
            solar_long_diff[i] = this.degreeDifference(this.solar_longitude(rise + t), this.solar_longitude(rise));
            ++i;
        }

        i = 0;
        double[] relative_motion = new double[offsets.length];
        double[] var21 = offsets;
        int var44 = offsets.length;

        for(var19 = 0; var19 < var44; ++var19) {
            double var10000 = var21[var19];
            relative_motion[i] = lunar_long_diff[i] - solar_long_diff[i];
            ++i;
        }

        double approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
        double ends = (rise + approx_end - jd) * 24.0D;
        double[] answer = new double[]{today, ends};
        double moon_phase_tmrw = this.lunar_phase(rise + 1.0D);
        double tomorrow = Math.ceil(moon_phase_tmrw / 6.0D);
        if (tomorrow < today) {
            tomorrow += 60.0D;
        }

        boolean isSkipped = (tomorrow - today) % 60.0D > 1.0D;
        if (isSkipped) {
            double leap_tithi = today + 1.0D;
            degrees_left = leap_tithi * 6.0D - moon_phase;
            approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
            double ends_leap_tithi = (rise + approx_end - jd) * 24.0D;
            double todaySecond = this.mod(leap_tithi, 60.0D);
            boolean isSkipped2 = (tomorrow - todaySecond) % 60.0D > 1.0D;
            if (isSkipped2) {
                double leap_tithiSkip = todaySecond + 1.0D;
                degrees_left = leap_tithiSkip * 6.0D - moon_phase;
                approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
                double ends_leap_tithiSkip = (rise + approx_end - jd) * 24.0D;
                double todayThird = this.mod(leap_tithiSkip, 60.0D);
                return new double[]{today, ends, todaySecond, ends_leap_tithi, todayThird, ends_leap_tithiSkip};
            } else {
                return new double[]{today, ends, todaySecond, ends_leap_tithi};
            }
        } else {
            return answer;
        }
    }

    public double[] karanaBhadra(double jd, Place place) {
        this.place = place;
        double rise = sunriseJd(jd, place);
        double moon_phase = this.lunar_phase(rise);
        double today = Math.ceil(moon_phase / 6.0D);
        double degrees_left = today * 6.0D - moon_phase;
        double degrees_left_start = 0.0D;
        if ((int)today == 29) {
            degrees_left_start = 6.0D - (today * 6.0D - moon_phase);
        } else if ((int)today == 28) {
            degrees_left_start = today * 6.0D - moon_phase;
            degrees_left = today * 6.0D - moon_phase + 6.0D;
        } else if ((int)today == 30) {
            degrees_left_start = 12.0D - (today * 6.0D - moon_phase);
        } else {
            if ((int)today != 27) {
                return new double[]{0.0D, 0.0D, 0.0D};
            }

            degrees_left_start = today * 6.0D - moon_phase + 6.0D;
            degrees_left = today * 6.0D - moon_phase + 12.0D;
        }

        double[] offsets = new double[]{0.25D, 0.5D, 0.75D, 1.0D};
        double[] lunar_long_diff = new double[offsets.length];
        double[] solar_long_diff = new double[offsets.length];
        int i = 0;
        double[] var22 = offsets;
        int var21 = offsets.length;

        for(int var20 = 0; var20 < var21; ++var20) {
            double t = var22[var20];
            lunar_long_diff[i] = this.degreeDifference(this.lunar_longitude(rise + t), this.lunar_longitude(rise));
            solar_long_diff[i] = this.degreeDifference(this.solar_longitude(rise + t), this.solar_longitude(rise));
            ++i;
        }

        i = 0;
        double[] relative_motion = new double[offsets.length];
        double[] var23 = offsets;
        int var33 = offsets.length;

        for(var21 = 0; var21 < var33; ++var21) {
            double var10000 = var23[var21];
            relative_motion[i] = lunar_long_diff[i] - solar_long_diff[i];
            ++i;
        }

        double approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
        double ends = (rise + approx_end - jd) * 24.0D;
        double karanStartDate = 0.0D;
        double approx_start = this.inverse_lagrange(offsets, relative_motion, degrees_left_start);
        double start = (rise - approx_start - jd) * 24.0D;
        if ((int)today == 28) {
            start = (rise + approx_start - jd) * 24.0D;
        } else if ((int)today == 30) {
            ends -= (ends - start) / 2.0D;
        } else if ((int)today == 27) {
            start = (rise + approx_start - jd) * 24.0D;
        }

        if (start < 0.0D) {
            karanStartDate = -1.0D;
            start += 24.0D;
        }

        double[] answer = new double[]{start, ends, karanStartDate};
        return answer;
    }

    public double[] bhadraStartEndTime(double jd, Place place) {
        List<Integer> bhList = Arrays.asList(8, 15, 22, 29, 36, 43, 50, 57);
        double start = 0.0D;
        double end = 0.0D;
        double karanStartDate = 0.0D;
        double[] bhadrArray = this.karanaNew(jd, place);
        int karanaNum = (int)bhadrArray[0];
        if (bhList.contains(karanaNum)) {
            end = bhadrArray[1];
            bhadrArray = this.karanaNew(jd - 1.0D, place);
            if ((int)bhadrArray[0] == karanaNum - 1) {
                start = bhadrArray[1];
            } else if (bhadrArray.length > 2) {
                if ((int)bhadrArray[2] == karanaNum - 1) {
                    start = bhadrArray[3];
                } else if (bhadrArray.length > 4 && (int)bhadrArray[4] == karanaNum - 1) {
                    start = bhadrArray[5];
                }
            }

            if (start < 24.0D) {
                karanStartDate = -1.0D;
            }
        } else if (bhadrArray.length > 2) {
            karanaNum = (int)bhadrArray[2];
            if (bhList.contains(karanaNum)) {
                start = bhadrArray[1];
                end = bhadrArray[3];
            }

            if (bhadrArray.length > 4) {
                karanaNum = (int)bhadrArray[4];
                if (bhList.contains(karanaNum)) {
                    start = bhadrArray[3];
                    end = bhadrArray[5];
                }
            }
        }

        if (start > 24.0D) {
            start -= 24.0D;
        }

        return new double[]{start, end, karanStartDate};
    }

    public double[] panchakStartEndTime(double jd, Place place) {
        List<Integer> nakList = Arrays.asList(23, 24, 25, 26, 27);
        double start = 0.0D;
        double end = 0.0D;
        double panchakStartDate = 0.0D;
        double panchakEndDate = 0.0D;
        double[] panchakArray = this.nakshatra(jd, place);
        int nakshatraNum = (int)panchakArray[0];
        int nakshatraNumStart = (int)panchakArray[0];
        int nakshatraNumEnd = (int)panchakArray[0];
        double jdStart = jd;
        double jdEnd = jd;
        double[] panchakArrayEnd = panchakArray;
        if (nakList.contains(nakshatraNum)) {
            int i;
            double[] temp;
            for(i = 1; i < 7; ++i) {
                temp = this.nakshatra(--jdStart, place);
                if (!nakList.contains((int)temp[0])) {
                    ++jdStart;
                    break;
                }

                nakshatraNumStart = (int)temp[0];
                --panchakStartDate;
            }

            for(i = 1; i < 7; ++i) {
                temp = this.nakshatra(++jdEnd, place);
                if (!nakList.contains((int)temp[0])) {
                    --jdEnd;
                    break;
                }

                panchakArrayEnd = temp;
                nakshatraNumEnd = (int)temp[0];
                ++panchakEndDate;
            }
        }

        if (nakList.contains(nakshatraNumStart)) {
            double[] moonArray = this.moonsign(jdStart - 1.0D, place);
            if ((int)moonArray[0] == 10) {
                start = moonArray[1];
                if (start < 24.0D) {
                    --panchakStartDate;
                }
            } else {
                moonArray = this.moonsign(jdStart, place);
                start = moonArray[1];
            }
        }

        if (nakList.contains(nakshatraNumEnd)) {
            end = panchakArrayEnd[1];
        } else if (panchakArrayEnd.length > 2) {
            nakshatraNumEnd = (int)panchakArrayEnd[2];
            if (nakList.contains(nakshatraNumEnd)) {
                end = panchakArrayEnd[3];
            }
        }

        if (start > 24.0D) {
            start -= 24.0D;
        }

        return new double[]{start, end, panchakStartDate, panchakEndDate};
    }

    public static int vaara(double jd) {
        return (int)(Math.ceil(jd + 1.0D) % 7.0D);
    }

    public double[] tithi(double jd, Place place) {
        this.place = place;
        double tz = place.timezone;
        double rise = sunriseJd(jd, place);
        double moon_phase = this.lunar_phase(rise);
        double today = Math.ceil(moon_phase / 12.0D);
        double degrees_left = today * 12.0D - moon_phase;
        double[] offsets = new double[]{0.25D, 0.5D, 0.75D, 1.0D};
        double[] lunar_long_diff = new double[offsets.length];
        double[] solar_long_diff = new double[offsets.length];
        int i = 0;
        double[] var22 = offsets;
        int var21 = offsets.length;

        for(int var20 = 0; var20 < var21; ++var20) {
            double t = var22[var20];
            lunar_long_diff[i] = this.degreeDifference(this.lunar_longitude(rise + t), this.lunar_longitude(rise));
            solar_long_diff[i] = this.degreeDifference(this.solar_longitude(rise + t), this.solar_longitude(rise));
            ++i;
        }

        i = 0;
        double[] relative_motion = new double[offsets.length];
        double[] var23 = offsets;
        int var36 = offsets.length;

        for(var21 = 0; var21 < var36; ++var21) {
            double var10000 = var23[var21];
            relative_motion[i] = lunar_long_diff[i] - solar_long_diff[i];
            ++i;
        }

        double approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
        double ends = (rise + approx_end - jd) * 24.0D;
        double[] answer = new double[]{today, ends};
        double moon_phase_tmrw = this.lunar_phase(rise + 1.0D);
        double tomorrow = Math.ceil(moon_phase_tmrw / 12.0D);
        if (tomorrow < today) {
            tomorrow += 30.0D;
        }

        boolean isSkipped = (tomorrow - today) % 30.0D > 1.0D;
        if (isSkipped) {
            double leap_tithi = today + 1.0D;
            degrees_left = leap_tithi * 12.0D - moon_phase;
            approx_end = this.inverse_lagrange(offsets, relative_motion, degrees_left);
            double ends_leap_tithi = (rise + approx_end - jd) * 24.0D;
            return new double[]{today, ends, this.mod(leap_tithi, 30.0D), ends_leap_tithi};
        } else {
            return answer;
        }
    }

    public double[] tithiStartEndTime(double jd, Place place) {
        double[] tithiPrevArray = this.tithi(jd - 1.0D, place);
        double[] tithiArray = this.tithi(jd, place);
        double tithiStartDate = 0.0D;
        double tithiend = tithiArray[1];
        double tithistart;
        if (tithiPrevArray.length > 2) {
            tithistart = tithiPrevArray[3];
        } else {
            tithistart = tithiPrevArray[1];
        }

        if ((int)tithiArray[0] == (int)tithiPrevArray[0]) {
            tithiPrevArray = this.tithi(jd - 2.0D, place);
            if (tithiPrevArray.length > 2) {
                tithistart = tithiPrevArray[3] - 24.0D;
            } else {
                tithistart = tithiPrevArray[1] - 24.0D;
            }

            tithiStartDate = -1.0D;
        } else if (tithistart < 24.0D) {
            tithiStartDate = -1.0D;
        } else if (tithistart > 24.0D) {
            tithistart -= 24.0D;
        }

        return tithiArray.length > 2 ? new double[]{tithiArray[0], tithistart, tithiend, tithiStartDate, tithiArray[2], tithiend, tithiArray[3]} : new double[]{tithiArray[0], tithistart, tithiend, tithiStartDate};
    }

    public double[] nakshatraStartEndTime(double jd, Place place) {
        double[] nakshatraPrevArray = this.nakshatra(jd - 1.0D, place);
        double[] nakshatraArray = this.nakshatra(jd, place);
        double nakshatraStartDate = 0.0D;
        double nakshatraend = nakshatraArray[1];
        double nakshatrastart;
        if (nakshatraPrevArray.length > 2) {
            nakshatrastart = nakshatraPrevArray[3];
        } else {
            nakshatrastart = nakshatraPrevArray[1];
        }

        if ((int)nakshatraArray[0] == (int)nakshatraPrevArray[0]) {
            nakshatraPrevArray = this.nakshatra(jd - 2.0D, place);
            if (nakshatraPrevArray.length > 2) {
                nakshatrastart = nakshatraPrevArray[3] - 24.0D;
            } else {
                nakshatrastart = nakshatraPrevArray[1] - 24.0D;
            }

            nakshatraStartDate = -1.0D;
        } else if (nakshatrastart < 24.0D) {
            nakshatraStartDate = -1.0D;
        } else if (nakshatrastart > 24.0D) {
            nakshatrastart -= 24.0D;
        }

        return nakshatraArray.length > 2 ? new double[]{nakshatraArray[0], nakshatrastart, nakshatraend, nakshatraStartDate, nakshatraArray[2], nakshatraend, nakshatraArray[3]} : new double[]{nakshatraArray[0], nakshatrastart, nakshatraend, nakshatraStartDate};
    }

    double degreeDifference(double degreeFrom, double degree) {
        double difference = degreeFrom - degree;
        if (difference < 0.0D) {
            difference += 360.0D;
        }

        return difference;
    }

    public int[] masa(double jd, Place place) {
        this.place = place;
        double critical = sunriseJd(jd, place);
        double ti = (double)this.getTithiD(critical);
        double last_new_moon = this.new_moon(critical, ti, -1);
        double next_new_moon = this.new_moon(critical, ti, 1);
        double this_solar_month = this.raasi(last_new_moon);
        double next_solar_month = this.raasi(next_new_moon);
        int is_leap_month = this_solar_month == next_solar_month ? 1 : 0;
        int maasa = (int)this_solar_month + 1;
        if (maasa > 12) {
            maasa %= 12;
        }

        return new int[]{maasa, is_leap_month};
    }

    public int[] masaPurnimanta(double jd, Place place) {
        this.place = place;
        double critical = sunriseJd(jd, place);
        double ti = (double)this.getTithiD(critical);
        double last_new_moon = this.new_moon(critical, ti, -1);
        double next_new_moon = this.new_moon(critical, ti, 1);
        double this_solar_month = this.raasi(last_new_moon);
        double next_solar_month = this.raasi(next_new_moon);
        double last_full_moon = this.full_moon(jd, ti, -1);
        double tithi_full_moon = (double)this.getTithiD(last_full_moon);
        double new_moon_after_full_moon = this.new_moon(last_full_moon, tithi_full_moon, 1);
        double solar_month__newmoon_after_fullmoon = this.raasi(new_moon_after_full_moon);
        int is_leap_month = this_solar_month == next_solar_month ? 1 : 0;
        int maasa = (int)this_solar_month + 1;
        if (maasa > 12) {
            maasa %= 12;
        }

        int maasa_purnimanta = (int)solar_month__newmoon_after_fullmoon + 1;
        if (maasa_purnimanta > 12) {
            maasa_purnimanta %= 12;
        }

        return new int[]{maasa, is_leap_month, maasa_purnimanta};
    }

    double ahargana(double jd) {
        return jd - 588465.5D;
    }

    public int[] elapsed_year(double jd, Place place, int maasa_num) {
        this.place = place;
        double sidereal_year = 365.25636D;
        double ahar = this.ahargana(jd);
        int solar_num = (int)this.raasi(sunriseJd(jd, place));
        int kali = (int)((ahar + (double)((4 - maasa_num) * 30)) / sidereal_year);
        int kali_solar = (int)((ahar + (double)((4 - solar_num) * 30)) / sidereal_year) + 1;
        int saka = kali - 3179;
        int vikrama = saka + 135;
        return new int[]{kali, saka, vikrama, kali_solar};
    }

    public int samvatsara(double jd, Place place, int maasa_num, boolean isNorth) {
        int kali = this.elapsed_year(jd, place, maasa_num)[0];
        byte offset;
        if (isNorth) {
            offset = 0;
        } else {
            offset = 14;
        }

        if (kali >= 4009) {
            kali = (kali - offset) % 60;
        }

        int samvat = (kali + 27 + (kali * 211 - 108) / 18000) % 60;
        if (isNorth) {
            --samvat;
            if (samvat < 1) {
                samvat += 60;
            }
        }

        return samvat;
    }

    public int ritu(int masa_num) {
        return (masa_num - 1) / 2;
    }

    public int ritu_drik(double jd, Place place) {
        double critical = sunriseJd(jd, place);
        int masa_num = (int)this.raasi_sayan(critical);
        int rituNum = masa_num / 2;
        if (place.latitude >= 0.0D) {
            if (rituNum > 5) {
                return 0;
            }
        } else {
            rituNum += 3;
            if (rituNum > 5) {
                return rituNum - 6;
            }
        }

        return rituNum;
    }

    int SuryaAyana1(int masa_num) {
        return (masa_num - 1) / 2;
    }

    public int SuryaAyana(double jd) {
        double sun = this.solar_longitude_nir(jd);
        return sun >= 90.0D && sun < 270.0D ? 1 : 0;
    }

    double new_moon(double jd, double tithi_, int opt) {
        double start = 0.0D;
        if (opt == 0) {
            opt = -1;
        }

        if (opt == -1) {
            start = jd - tithi_;
        }

        if (opt == 1) {
            start = jd + (30.0D - tithi_);
        }

        double[] x = new double[17];

        for(int offset = 0; offset < 17; ++offset) {
            x[offset] = -2.0D + (double)offset / 4.0D;
        }

        double[] y = new double[17];

        for(int i = 0; i < 17; ++i) {
            y[i] = this.lunar_phase(start + x[i]);
        }

        y = this.unwrap_angles(y);
        double y0 = this.inverse_lagrange(x, y, 360.0D);
        return start + y0;
    }

    double full_moon(double jd, double tithi_, int opt) {
        double start = 0.0D;
        if (opt == 0) {
            opt = -1;
        }

        if (opt == -1) {
            start = tithi_ <= 15.0D ? jd - tithi_ - 15.0D : jd - (tithi_ - 15.0D);
        }

        if (opt == 1) {
            start = tithi_ <= 15.0D ? jd + 15.0D - tithi_ : jd + (30.0D - tithi_) + 15.0D;
        }

        double[] x = new double[17];

        for(int offset = 0; offset < 17; ++offset) {
            x[offset] = -2.0D + (double)offset / 4.0D;
        }

        double[] y = new double[17];

        for(int i = 0; i < 17; ++i) {
            y[i] = this.lunar_phase(start + x[i]);
        }

        y = this.unwrap_angles(y);
        double y0 = this.inverse_lagrange(x, y, 180.0D);
        return start + y0;
    }

    public double inverse_lagrange(double[] x, double[] y, double ya) {
        double total = 0.0D;

        for(int i = 0; i < x.length; ++i) {
            double numer = 1.0D;
            double denom = 1.0D;

            for(int j = 0; j < x.length; ++j) {
                if (j != i) {
                    numer *= ya - y[j];
                    denom *= y[i] - y[j];
                }
            }

            total += numer * x[i] / denom;
        }

        return total;
    }

    double raasi(double jd) {
        double solar_nirayana = this.solar_longitude_nir(jd);
        return Math.ceil(solar_nirayana / 30.0D);
    }

    double raasi_sayan(double jd) {
        double solar_long = this.solar_longitude(jd);
        return Math.ceil(solar_long / 30.0D);
    }

    double getRasiFromLongitude(double longitude) {
        return Math.ceil(longitude / 30.0D);
    }

    public int sunSign(double jd) {
        return (int)this.getRasiFromLongitude(this.solar_longitude_nir(jd));
    }

    public int getPaksha1(double jd) {
        double phase = this.lunar_phase(jd);
        return phase < 180.0D ? 0 : 1;
    }

    public int getPaksha(double jd, Place place) {
        this.place = place;
        double rise = sunriseJd(jd, place);
        double phase = this.lunar_phase(rise);
        return phase < 180.0D ? 0 : 1;
    }

    public int solarMonth(double jd, Place place) {
        return (int)this.raasi(jd);
    }

    public static double sunsetJd(double jd, Place place) {
        return jd + getSunSet(jd, place) / 24.0D;
    }

    public static double moonriseJd(double jd, Place place) {
        CMoon objCMoon = new CMoon();
        return jd + objCMoon.getMoonRiseSetTime(jd, place)[0] / 24.0D;
    }

    double getNakshtra(double jd) {
        double lmoon = this.lunar_longitude_nir(jd);
        return (double)((int)(1.0D + Math.floor(lmoon / 13.333333333333334D)));
    }

    double getKarana(double jd) {
        double lmoon = this.lunar_longitude_nir(jd);
        return (double)((int)(1.0D + Math.floor(lmoon / 60.0D)));
    }

    double getYoga(double jd) {
        double lmoon = this.lunar_longitude_nir(jd) + this.solar_longitude_nir(jd);
        double a = 1.0D + Math.floor(lmoon / 60.0D);
        System.out.println(a);
        return (double)((int)(1.0D + Math.floor(lmoon / 60.0D)));
    }

    double lunar_phase(double jd) {
        double solar_long = this.solar_longitude(jd);
        double lunar_long = this.lunar_longitude(jd);
        double moon_phase = (lunar_long - solar_long) % 360.0D;
        if (moon_phase < 0.0D) {
            moon_phase += 360.0D;
        }

        return moon_phase;
    }

    protected double[] unwrap_angles(double[] angles) {
        double[] result = angles;

        for(int i = 1; i < angles.length; ++i) {
            if (result[i] < result[i - 1]) {
                result[i] += 360.0D;
            }
        }

        return result;
    }

    public int jd(int d, int m, int y) {
        if (m < 3) {
            m += 12;
            --y;
        }

        int a = y / 100;
        float b = 30.6F * (float)(m + 1);
        int l = (int)b;
        int j = 365 * y + y / 4 + l + 2 - a + a / 4 + d;
        return j;
    }

    public static double toJulian(int year, int month, int day, int h, int mt, int s, double timezone) {
        return toJulian(year, month, day) + ((double)h + (double)mt / 60.0D + (double)s / 3600.0D - (12.0D + timezone)) / 24.0D;
    }

    public static double toJulian(int year, int month, int day) {
        int JGREG = 588829;
        double HALFSECOND = 0.5D;
        int julianYear = year;
        if (year < 0) {
            julianYear = year + 1;
        }

        int julianMonth;
        if (month > 2) {
            julianMonth = month + 1;
        } else {
            --julianYear;
            julianMonth = month + 13;
        }

        double julian = Math.floor(365.25D * (double)julianYear) + Math.floor(30.6001D * (double)julianMonth) + (double)day + 1720995.0D;
        if (day + 31 * (month + 12 * year) >= JGREG) {
            int ja = (int)(0.01D * (double)julianYear);
            julian += (double)(2 - ja) + 0.25D * (double)ja;
        }

        return Math.floor(julian);
    }

    public static int[] fromJulian(double injulian) {
        int JGREG = 588829;
        double HALFSECOND = 0.5D;
        double julian = injulian + HALFSECOND / 86400.0D;
        int ja = (int)julian;
        if (ja >= JGREG) {
            int jalpha = (int)(((double)(ja - 1867216) - 0.25D) / 36524.25D);
            ja = ja + 1 + jalpha - jalpha / 4;
        }

        int jb = ja + 1524;
        int jc = (int)(6680.0D + ((double)(jb - 2439870) - 122.1D) / 365.25D);
        int jd = 365 * jc + jc / 4;
        int je = (int)((double)(jb - jd) / 30.6001D);
        int day = jb - jd - (int)(30.6001D * (double)je);
        int month = je - 1;
        if (month > 12) {
            month -= 12;
        }

        int year = jc - 4715;
        if (month > 2) {
            --year;
        }

        if (year <= 0) {
            --year;
        }

        return new int[]{year, month, day};
    }

    void yogaTest() {
        int may22 = (int)toJulian(2013, 5, 22);
        int date2 = (int)toJulian(2013, 1, 18);
        int date3 = (int)toJulian(1985, 6, 9);
        new Place(60.17D, 24.935D, 2.0D);
        Place bangalore = new Place(12.972D, 77.594D, 5.5D);
        int jul1 = (int)toJulian(2014, 7, 1);
        print(this.yoga((double)jul1, bangalore));
    }

    void tithiTest() {
        Place helsinki = new Place(60.17D, 24.935D, 2.0D);
        int date1 = (int)toJulian(2009, 7, 15);
        Place bangalore = new Place(12.972D, 77.594D, 5.5D);
        printTithi(this.tithi((double)date1, bangalore));
        int date2 = (int)toJulian(2013, 1, 18);
        printTithi(this.tithi((double)date2, bangalore));
        int date3 = (int)toJulian(1985, 6, 9);
        printTithi(this.tithi((double)date3, bangalore));
        printTithi(this.tithi((double)date2, helsinki));
        int apr24 = (int)toJulian(2010, 4, 24);
        double[] tithi5 = this.tithi((double)apr24, bangalore);
        printTithi(tithi5);
        int feb3 = (int)toJulian(2013, 2, 3);
        printTithi(this.tithi((double)feb3, bangalore));
        int apr19 = (int)toJulian(2013, 4, 19);
        printTithi(this.tithi((double)apr19, helsinki));
        int apr20 = (int)toJulian(2013, 4, 20);
        printTithi(this.tithi((double)apr20, helsinki));
        int apr21 = (int)toJulian(2013, 4, 21);
        printTithi(this.tithi((double)apr21, helsinki));
    }

    static void printTithi(double[] tithi) {
        Util util = new Util();
        System.out.print("Tithi " + tithi[0] + " ends: " + util.dms(tithi[1]));
        if (tithi.length > 3) {
            System.out.print(", Kshya Tithi " + tithi[2] + " ends: " + util.dms(tithi[3]));
        }

        System.out.println();
    }

    static void print(double[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " | ");
        }

        System.out.println();
    }

    static void printInDms(double[] array) {
        Util util = new Util();

        for(int i = 0; i < array.length; ++i) {
            System.out.print(util.dms(array[i]) + " | ");
        }

        System.out.println();
    }

    static void print(int[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " | ");
        }

        System.out.println();
    }

    static void printConstants(int[] array, String[] constantArrNam) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(constantArrNam[array[i]] + " | ");
        }

        System.out.println();
    }

    void samvatsaraTest() {
        int date1 = (int)toJulian(1979, 4, 11);
        Place agra = new Place(27.0D, 78.0D, 5.5D);
        int maasa_num = this.masa((double)date1, agra)[0];
    }

    void masaTest() {
        new Place(12.972D, 77.594D, 5.5D);
        Place helsinki = new Place(60.17D, 24.935D, 2.0D);
        int may21 = (int)toJulian(2012, 5, 21);
        System.out.println(helsinki);
        System.out.println(this.masa((double)may21, helsinki)[0]);
    }

    void nakshatraTest() {
        int date1 = (int)toJulian(2009, 7, 15);
        Place bangalore = new Place(12.972D, 77.594D, 5.5D);
        Place shillong = new Place(25.569D, 91.883D, 5.5D);
        int date2 = (int)toJulian(2013, 1, 18);
        int date3 = (int)toJulian(1985, 6, 9);
        int date4 = (int)toJulian(2009, 6, 21);
        print(this.nakshatra((double)date1, bangalore));
        print(this.nakshatra((double)date2, bangalore));
        print(this.nakshatra((double)date3, bangalore));
        print(this.nakshatra((double)date4, shillong));
    }

    public static void main(String[] args) {
        Masa phoro = new Masa();
        int mar26 = (int)toJulian(2016, 2, 18);
        int mar5 = (int)toJulian(2016, 6, 21);
        int jd = (int)toJulian(2039, 5, 4);
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        double[] karanArray = phoro.karanaNew((double)jd, NewDelhi);
        print(karanArray);

        for(int i = 0; i <= 30; ++i) {
            double[] bhdSET = phoro.bhadraStartEndTime((double)(jd + i), NewDelhi);
            if (bhdSET[0] != 0.0D) {
                int[] var10 = fromJulian((double)(jd + i));
            }
        }

        phoro.printDaily();
    }

    void printDaily() {
        new Masa();
        Util util = new Util();
        int jd = (int)toJulian(2018, 11, 1);
        Place agra = new Place(28.61D, 77.23D, 5.5D);
        System.out.println(this.place);
        System.out.print("Date: ");
        print(fromJulian((double)jd));
        System.out.print("Sunrise: ");
        System.out.println(util.dms(getSunRise((double)jd, agra)));
        System.out.print("Sunset: ");
        System.out.println(util.dms(getSunSet((double)jd, agra)));
        CMoon mm = new CMoon();
        double[] d = mm.getMoonRiseSetTime((double)jd, agra);
        System.out.print("Moonrise: ");
        System.out.println(util.dms(d[0]));
        System.out.print("Moonset: ");
        System.out.println(util.dms(d[2]));
        System.out.print("Sunsign: ");
        System.out.println(this.getRasiFromLongitude(this.solar_longitude_nir((double)jd)));
        System.out.print("Moonign: ");
        print(this.moonsign((double)jd, agra));
        System.out.print("Surya Ayana: ");
        //System.out.println(Constants.ayanas[this.SuryaAyana((double)jd)]);
        int maasa_num = this.masa((double)jd, agra)[0];
        System.out.print("Ritu : ");
        //System.out.println(Constants.ritus[this.ritu(maasa_num)]);
        System.out.print("Month : ");
        //System.out.print(Constants.masa[maasa_num - 1]);
        if (this.masa((double)jd, agra)[1] == 1) {
            System.out.print(" Adhik maas");
        }

        System.out.println();
        System.out.print("Paksha : ");
        System.out.print("Tithi : ");
        print(this.tithi((double)jd, agra));
        System.out.print("Nakshatra : ");
        print(this.nakshatra((double)jd, agra));
        System.out.print("Yoga : ");
        print(this.yoga((double)jd, agra));
        System.out.print("Karan : ");
        print(this.karana((double)jd, agra));
    }
}
