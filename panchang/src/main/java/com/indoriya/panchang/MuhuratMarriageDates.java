//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

import com.indoriya.panchang.util.ValueComparator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class MuhuratMarriageDates extends Masa {
    protected int arraySize;
    protected int year;
    protected Place place;
    double jd;
    int[] sun_month;
    int[] moon_month;
    int[] tithi;
    int[] nakshatra;
    int[] yoga;
    int[] karana;
    HashMap<String, Double> muhuratMarriageMapJD = new HashMap();

    public MuhuratMarriageDates() {
    }

    public HashMap<String, int[]> MuhuratMarriageDatesInYear(int yearOfMUhurta, Place place) {
        super.place = place;
        this.place = place;
        this.year = yearOfMUhurta;
        this.arraySize = 368;
        this.sun_month = new int[this.arraySize];
        this.moon_month = new int[this.arraySize];
        this.tithi = new int[this.arraySize];
        this.nakshatra = new int[this.arraySize];
        this.yoga = new int[this.arraySize];
        this.karana = new int[this.arraySize];
        this.jd = toJulian(this.year - 1, 12, 31);
        this.assignArrays(this.jd, this.place);
        return this.muhuratMarriageFindMap();
    }

    void assignArrays(double jdP, Place place) {
        for(int i = 0; i < this.arraySize; ++i) {
            this.sun_month[i] = this.solarMonth(sunriseJd(jdP + (double)i, place), place);
            this.tithi[i] = (int)this.tithi(jdP + (double)i, place)[0];
            this.nakshatra[i] = (int)this.getNakshtra(jdP + (double)i);
            this.yoga[i] = (int)this.yoga(jdP + (double)i, place)[0];
            this.karana[i] = (int)this.karana(jdP + (double)i, place)[0];
            int[] x = this.masa(jdP + (double)i, place);
            if (x[1] != 1) {
                this.moon_month[i] = x[0];
            } else {
                this.moon_month[i] = 13;
            }
        }

    }

    public HashMap<String, int[]> muhuratMarriageFindMap() {
        LinkedHashMap<String, int[]> muhuratMarriageMap = new LinkedHashMap();
        int muhuratCount = 0;
        String localKey = "muhurat_marriage_";
        Set<Integer> marriageSunMonths = new HashSet(Arrays.asList(1, 2, 3, 8, 10, 11));
        Set<Integer> marriageNakshatra1 = new HashSet(Arrays.asList(3, 4, 9, 11, 12, 14, 16, 18, 20, 25, 26));
        Set<Integer> marriageNakshatra = new HashSet(Arrays.asList(4, 5, 10, 12, 13, 15, 17, 19, 21, 26, 27));
        new HashSet(Arrays.asList(6, 9, 10, 13, 17, 19, 26, 27));
        new HashSet(Arrays.asList(24, 25, 26, 27));
        Set<Integer> marriageAvoidTithi = new HashSet(Arrays.asList(4, 9, 14, 19, 24, 29));

        for(int i = 1; i < this.arraySize - 1; ++i) {
            int sunMonthNum = this.sun_month[i];
            int dayNum = Masa.vaara(this.jd + (double)i);
            int moonMoonthNum = this.moon_month[i];
            int tithiNum = this.tithi[i];
            int nakshatraNum = this.nakshatra[i];
            int var10000 = this.yoga[i];
            var10000 = this.karana[i];
            double nakJD = this.jd + (double)i;
            if (marriageSunMonths.contains(sunMonthNum) && moonMoonthNum != 13 && (marriageNakshatra1.contains(nakshatraNum) || marriageNakshatra.contains(nakshatraNum))) {
                double nakTime = this.nakshatra(this.jd + (double)i, this.place)[1];
                if (nakTime > 24.0D) {
                    ++nakJD;
                }

                if (!marriageAvoidTithi.contains(tithiNum)) {
                    ++muhuratCount;
                    this.muhuratMarriageMapJD.put(localKey + muhuratCount, nakJD);
                }
            }
        }

        ValueComparator fvc = new ValueComparator(this.muhuratMarriageMapJD);
        TreeMap<String, Double> sorted_map = new TreeMap(fvc);
        sorted_map.putAll(this.muhuratMarriageMapJD);
        Iterator var25 = sorted_map.entrySet().iterator();

        while(var25.hasNext()) {
            Entry<String, Double> entry = (Entry)var25.next();
            int[] dateArray = fromJulian((Double)entry.getValue());
            if (dateArray[0] == this.year) {
                muhuratMarriageMap.put((String)entry.getKey(), dateArray);
            }
        }

        return muhuratMarriageMap;
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        new Place(-33.86785D, 151.20732D, 10.0D);
        new HashMap();
        MuhuratMarriageDates objMuhurtaYoga = new MuhuratMarriageDates();
        HashMap<String, int[]> festivalslist = objMuhurtaYoga.MuhuratMarriageDatesInYear(2017, NewDelhi);
        System.out.println("\nMarriage Muhurats : \n");
        Iterator var7 = festivalslist.entrySet().iterator();

        while(var7.hasNext()) {
            Entry m = (Entry)var7.next();
            int[] dt = (int[])festivalslist.get(m.getKey());
            System.out.println(m.getKey() + "  " + dt[2] + "-" + dt[1] + "-" + dt[0]);
        }

    }
}
