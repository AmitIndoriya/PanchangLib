//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

public class TithiFinder extends Muhurta {
    public TithiFinder() {
    }

    public static void main(String[] args) {
        Place place = new Place(28.61D, 77.23D, 5.5D);
        TithiFinder tf = new TithiFinder();
        tf.findFestival(2015, place);
    }

    public double[] findFestival(int year, Place place) {
        double[] amantaMonthEnd = new double[15];
        double[] purnimantaMonthEnd = new double[15];
        int[] isLeapMonth = new int[15];
        double[] solarMonth = new double[15];
        double jd = toJulian(year, 3, 3);
        this.place = place;
        double ti = (double)this.getTithiD(jd);
        double critical = sunriseJd(jd, place);
        double last_new_moon = this.new_moon(critical, ti, -1);
        double last_full_moon = this.full_moon(critical, ti, -1);
        double this_solar_month = this.raasi(last_new_moon);
        int maasa = (int)this_solar_month + 1;
        if (maasa > 12) {
            maasa %= 12;
        }

        amantaMonthEnd[0] = last_new_moon;
        purnimantaMonthEnd[0] = last_full_moon;
        solarMonth[0] = (double)maasa;
        System.out.println("i: 0, amantaMonthEnd: " + amantaMonthEnd[0] + ", purnimantaMonthEnd: " + purnimantaMonthEnd[0] + ", isLeapMonth: " + isLeapMonth[0] + ", solarMonth: " + solarMonth[0]);

        for(int i = 0; i < amantaMonthEnd.length - 1; ++i) {
            double next_new_moon = this.new_moon(critical, ti, 1);
            double next_full_moon = this.full_moon(critical, ti, 1);
            double next_solar_month = this.raasi(next_new_moon);
            double avgMonthDuration = next_new_moon - last_new_moon;
            int is_leap_month = this_solar_month == next_solar_month ? 1 : 0;
            maasa = (int)this_solar_month + 1;
            if (maasa > 12) {
                maasa %= 12;
            }

            amantaMonthEnd[i + 1] = next_new_moon;
            purnimantaMonthEnd[i + 1] = next_full_moon;
            isLeapMonth[i + 1] = is_leap_month;
            solarMonth[i + 1] = (double)maasa;
            jd += avgMonthDuration;
            last_new_moon = next_new_moon;
            this_solar_month = next_solar_month;
            ti = (double)this.getTithiD(jd);
            critical = sunriseJd(jd, place);
            String meString = fromJulian(amantaMonthEnd[i + 1])[0] + ":" + fromJulian(amantaMonthEnd[i + 1])[1] + ":" + fromJulian(amantaMonthEnd[i + 1])[2];
            String meString2 = fromJulian(purnimantaMonthEnd[i + 1])[0] + ":" + fromJulian(purnimantaMonthEnd[i + 1])[1] + ":" + fromJulian(purnimantaMonthEnd[i + 1])[2];
            System.out.println("i+1: " + (i + 1) + ", amantaMonthEnd: " + meString + ", purnimantaMonthEnd: " + meString2 + ", isLeapMonth: " + isLeapMonth[i + 1] + ", solarMonth: " + solarMonth[i + 1] + ", ti: " + ti + ", jd: " + jd + ", critical: " + critical);
        }

        return amantaMonthEnd;
    }
}
