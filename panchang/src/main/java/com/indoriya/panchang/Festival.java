//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

import com.indoriya.panchang.util.ValueComparator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Festival extends Masa {
    protected int arraySize;
    protected int year;
    protected Place place;
    double jd;
    int[] sun_month;
    int[] moon_month;
    int[] tithi_sunrise;
    int[] nakshatram_sunrise;
    HashMap<String, Double> festivalDayMapJD = new HashMap();

    public Festival() {
    }

    public HashMap<String, int[]> festivalsInYear(int yearOfPanchang, Place place) {
        super.place = place;
        this.place = place;
        this.year = yearOfPanchang;
        this.arraySize = 368;
        this.sun_month = new int[this.arraySize];
        this.moon_month = new int[this.arraySize];
        this.tithi_sunrise = new int[this.arraySize];
        this.nakshatram_sunrise = new int[this.arraySize];
        this.jd = toJulian(this.year - 1, 12, 31);
        this.assignArrays(this.jd, this.place);
        return this.festivalsFindMap();
    }

    void assignArrays(double jdP, Place place) {
        for(int i = 0; i < this.arraySize; ++i) {
            double sunRiseJd = sunriseJd(jdP + (double)i, place);
            this.sun_month[i] = this.solarMonth(sunRiseJd, place);
            this.tithi_sunrise[i] = this.getTithiD(sunRiseJd);
            this.nakshatram_sunrise[i] = (int)this.nakshatra(jdP + (double)i, place)[0];
            int[] x = this.masa(jdP + (double)i, place);
            if (x[1] != 1) {
                this.moon_month[i] = x[0];
            } else {
                this.moon_month[i] = 13;
            }
        }

    }

    public HashMap<String, int[]> festivalsFindMap() {
        LinkedHashMap<String, int[]> festival_day_list = new LinkedHashMap();
        int sankrantiMonth = 10;
        int countSawanPVrat = 0;
        int countSawanAVrat = 0;

        for(int i = 1; i < this.arraySize - 1; ++i) {
            int intTithiSunrise = this.tithi_sunrise[i];
            int intTithiSunriseNextDay = this.tithi_sunrise[i + 1];
            int intTithiSunrisePrevDay = this.tithi_sunrise[i - 1];
            int intSunMonth = this.sun_month[i];
            int intSunMonthPrevDay = this.sun_month[i - 1];
            int intNakshatraSunRise = this.nakshatram_sunrise[i];
            int intNakshatraSunRiseNextDay = this.nakshatram_sunrise[i + 1];
            int intMoonMonth = this.moon_month[i];
            int intMoonMonthPrevDay = this.moon_month[i - 1];
            double doubleDateJd = this.jd + (double)i;
            double doubleSunRise = Masa.getSunRise(doubleDateJd, this.place);
            double doubleSunRiseNextDay = Masa.getSunRise(doubleDateJd + 1.0D, this.place);
            double doubleSunSet = Masa.getSunSet(doubleDateJd, this.place);
            double doubleSunSetNextDay = Masa.getSunSet(doubleDateJd + 1.0D, this.place);
            int intWeekDay = Masa.vaara(doubleDateJd);
            int t_10;
            if (intSunMonth == 1 && intSunMonthPrevDay == 12) {
                t_10 = this.solarMonth(doubleDateJd - 0.0104D, this.place);
                if (t_10 == 1) {
                    this.festivalDayMapJD.put("baisakhi", doubleDateJd - 1.0D);
                } else {
                    this.festivalDayMapJD.put("baisakhi", doubleDateJd);
                }
            }

            if (intSunMonth == 5 && this.sun_month[i + 2] == 5 && (intNakshatraSunRise == 21 || intNakshatraSunRise == 22)) {
                if (intNakshatraSunRise == 21 && intNakshatraSunRiseNextDay == 22) {
                    this.festivalDayMapJD.put("onam", doubleDateJd + 1.0D);
                } else if (intNakshatraSunRise == 21 && intNakshatraSunRiseNextDay == 23) {
                    this.festivalDayMapJD.put("onam", doubleDateJd);
                } else if (intNakshatraSunRise == 22) {
                    this.festivalDayMapJD.put("onam", doubleDateJd);
                }
            }

            if (intSunMonth == sankrantiMonth && intSunMonthPrevDay == (sankrantiMonth != 1 ? sankrantiMonth - 1 : 12)) {
                if (sankrantiMonth != 4 && sankrantiMonth != 10) {
                    t_10 = this.solarMonth(doubleDateJd - 0.0104D, this.place);
                    if (t_10 == sankrantiMonth) {
                        this.festivalDayMapJD.put("sankranti_" + sankrantiMonth, doubleDateJd - 1.0D);
                    } else {
                        this.festivalDayMapJD.put("sankranti_" + sankrantiMonth, doubleDateJd);
                    }
                }

                ++sankrantiMonth;
                if (sankrantiMonth == 13) {
                    sankrantiMonth = 1;
                }
            }

            if (intSunMonth == 10 && intSunMonthPrevDay == 9) {
                t_10 = this.solarMonth(sunriseJd(doubleDateJd, this.place) - 0.67D, this.place);
                if (t_10 == 10) {
                    this.festivalDayMapJD.put("uttarayanam", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("makar_sankranti", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("pongal", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("sankranti_10", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("lohri", doubleDateJd - 2.0D);
                } else {
                    this.festivalDayMapJD.put("uttarayanam", doubleDateJd);
                    this.festivalDayMapJD.put("makar_sankranti", doubleDateJd);
                    this.festivalDayMapJD.put("pongal", doubleDateJd);
                    this.festivalDayMapJD.put("sankranti_10", doubleDateJd);
                    this.festivalDayMapJD.put("lohri", doubleDateJd - 1.0D);
                }
            }

            if (intSunMonth == 4 && intSunMonthPrevDay == 3) {
                this.festivalDayMapJD.put("sankranti_4", doubleDateJd - 1.0D);
            }

            String key;
            if (intTithiSunrise == 11 || intTithiSunrise == 12) {
                key = "ekadashi_1_" + intMoonMonth;
                if (intTithiSunriseNextDay == 11) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else if (intTithiSunrise == 11) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                } else if (intTithiSunrise == 12) {
                    this.putKeyInMapCheckContain(key, doubleDateJd - 1.0D, intMoonMonth);
                }

                if (key.equals("ekadashi_1_4")) {
                    this.festivalDayMapJD.put("ashadhi_ekadashi", (Double)this.festivalDayMapJD.get("ekadashi_1_4"));
                }
            }

            if (intTithiSunrise == 26 || intTithiSunrise == 27) {
                key = "ekadashi_2_" + intMoonMonth;
                if (intTithiSunriseNextDay == 26) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else if (intTithiSunrise == 26) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                } else if (intTithiSunrise == 27) {
                    this.putKeyInMapCheckContain(key, doubleDateJd - 1.0D, intMoonMonth);
                }
            }

            if (intTithiSunrise == 14 || intTithiSunrise == 15) {
                key = "purnima_" + intMoonMonth;
                if (intTithiSunrise == 15) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                } else if (intTithiSunriseNextDay == 15) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else if (intTithiSunriseNextDay == 16) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                }
            }

            if (intTithiSunrise == 29 || intTithiSunrise == 30) {
                key = "amavasya_" + intMoonMonth;
                if (intTithiSunrise == 30) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                } else if (intTithiSunriseNextDay == 30) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else if (intTithiSunriseNextDay == 1) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                }
            }

            int t_21;
            int t_22;
            if (intTithiSunrise == 18 || intTithiSunrise == 19) {
                key = "sankashti_chaturthi_" + intMoonMonth;
                CMoon objCMoon = new CMoon();
                t_21 = this.getTithiD(doubleDateJd + objCMoon.getMoonRiseSetTime(doubleDateJd, this.place)[0] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + objCMoon.getMoonRiseSetTime(doubleDateJd + 1.0D, this.place)[0] / 24.0D);
                if (t_21 == 19 && t_22 == 19) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                } else if (t_21 == 19 && t_22 != 19) {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                } else if (t_21 != 19 && t_22 == 19) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                }
            }

            int t_12;
            if (intTithiSunrise == 28 || intTithiSunrise == 29) {
                key = "masik_shivratri_" + intMoonMonth;
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[7] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[8] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 15)[8] / 24.0D);
                if (t_12 != 29 && t_21 != 29) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else if (t_22 == 29) {
                    this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                } else {
                    this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                }
            }

            int t_23;
            if (intTithiSunrise == 12 || intTithiSunrise == 13) {
                key = "pradosh_1_" + intMoonMonth;
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[0] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[1] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[0] / 24.0D);
                t_23 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[1] / 24.0D);
                if (t_12 == 13 || t_21 == 13) {
                    if (t_22 == 13 && t_23 == 13) {
                        this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                    } else {
                        this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                    }
                }
            }

            if (intTithiSunrise == 27 || intTithiSunrise == 28) {
                key = "pradosh_2_" + intMoonMonth;
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[0] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[1] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[0] / 24.0D);
                t_23 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[1] / 24.0D);
                if (t_12 == 28 || t_21 == 28) {
                    if (t_22 == 28 && t_23 == 28) {
                        this.putKeyInMapCheckContain(key, doubleDateJd + 1.0D, intMoonMonth);
                    } else {
                        this.putKeyInMapCheckContain(key, doubleDateJd, intMoonMonth);
                    }
                }
            }

            if (intMoonMonth == 1 && intMoonMonthPrevDay != 1) {
                this.festivalDayMapJD.put("hindu_new_year", doubleDateJd);
                this.festivalDayMapJD.put("ugadi", doubleDateJd);
                this.festivalDayMapJD.put("gudi_padwa", doubleDateJd);
                this.festivalDayMapJD.put("chaitra_navratri_ghatasthapana", doubleDateJd);
                this.festivalDayMapJD.put("chaitra_navratri", doubleDateJd);
                if (intTithiSunriseNextDay == 3) {
                    this.festivalDayMapJD.put("cheti_chand", doubleDateJd);
                } else if (intTithiSunriseNextDay == 1 && this.tithi_sunrise[i + 2] == 2) {
                    this.festivalDayMapJD.put("cheti_chand", doubleDateJd + 2.0D);
                } else {
                    this.festivalDayMapJD.put("cheti_chand", doubleDateJd + 1.0D);
                }

                if (intTithiSunrise == 2) {
                    this.festivalDayMapJD.put("hindu_new_year", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("ugadi", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("gudi_padwa", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("chaitra_navratri_ghatasthapana", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("chaitra_navratri", doubleDateJd - 1.0D);
                    this.festivalDayMapJD.put("cheti_chand", doubleDateJd);
                }
            }

            int tithiSunrise;
            int ni;
            String key1;
            String key2;
            if (intMoonMonth == 1 && intMoonMonthPrevDay != 1) {
                key = "";
                key1 = "";
                key2 = "";
                t_22 = 0;
                t_23 = 1;

                for(ni = 0; ni < 12; ++ni) {
                    key = "chaitra_navratri_" + (ni + 1);
                    key1 = "chaitra_navratri_" + (ni + 2);
                    key2 = "chaitra_navratri_" + (ni + 3);
                    tithiSunrise = this.tithi_sunrise[i + t_22];
                    if (ni == 0 && tithiSunrise == 2) {
                        this.festivalDayMapJD.put("chaitra_navratri_1", doubleDateJd - 1.0D);
                        this.festivalDayMapJD.put("chaitra_navratri_2", doubleDateJd - 1.0D);
                        ++t_23;
                        ni = 1;
                    } else if (tithiSunrise >= 9) {
                        if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 - 1] == 7) {
                            if (this.paranaVart(doubleDateJd + (double)t_22, this.place, 9.0D)) {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                                this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22);
                            } else {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                                this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put(key2, doubleDateJd + (double)t_22 + 1.0D);
                                this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22 + 1.0D);
                            }

                            ++t_22;
                        } else if (tithiSunrise == 10 && this.tithi_sunrise[i + t_22 - 1] == 8) {
                            this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                            this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22);
                            ++t_22;
                        } else if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 + 1] == 10) {
                            if (this.paranaVart(doubleDateJd + (double)t_22, this.place, 9.0D)) {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22);
                            } else {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22 + 1.0D);
                                this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22 + 1.0D);
                            }

                            ++t_22;
                        } else if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 + 1] == 11) {
                            this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22);
                            ++t_22;
                        } else if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 + 1] == 9) {
                            this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22 + 1.0D);
                            this.festivalDayMapJD.put("chaitra_navratri_parana", doubleDateJd + (double)t_22 + 1.0D);
                            ++t_22;
                        }
                    } else if (tithiSunrise == t_23) {
                        this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                        ++t_23;
                        ++t_22;
                    } else if (tithiSunrise == t_23 - 1) {
                        this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                        ++t_23;
                        ++t_22;
                    } else if (tithiSunrise == t_23 + 1) {
                        this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                        ++t_23;
                    }
                }
            }

            if (intMoonMonth == 1 && (intTithiSunrise == 8 || intTithiSunrise == 9)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[2] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[3] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[2] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[3] / 24.0D);
                if (t_10 == 9 || t_12 == 9) {
                    if (t_21 != 9 && t_22 != 9) {
                        if (t_10 == 8) {
                            if (t_21 == 10) {
                                this.festivalDayMapJD.put("ram_navami", doubleDateJd);
                            }
                        } else {
                            this.festivalDayMapJD.put("ram_navami", doubleDateJd);
                        }
                    } else {
                        this.festivalDayMapJD.put("ram_navami", doubleDateJd + 1.0D);
                    }
                }
            }

            if (intMoonMonth == 1 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
                if (intTithiSunrise == 15 && (intTithiSunrisePrevDay == 14 || intTithiSunrisePrevDay == 13)) {
                    this.festivalDayMapJD.put("chaitra_hanuman_jayanti", doubleDateJd);
                } else if (intTithiSunrise == 14 && intTithiSunriseNextDay == 16) {
                    this.festivalDayMapJD.put("chaitra_hanuman_jayanti", doubleDateJd);
                }
            }

            if (intMoonMonth == 2 && (intTithiSunrise == 2 || intTithiSunrise == 3)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[3] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[3] / 24.0D);
                if (intTithiSunrise == 3 && intTithiSunrisePrevDay != 3 && t_10 == 3) {
                    this.festivalDayMapJD.put("akshaya_tritiya", doubleDateJd);
                } else if (intTithiSunrise == 2 && t_12 == 3) {
                    this.festivalDayMapJD.put("akshaya_tritiya", doubleDateJd + 1.0D);
                } else if (intTithiSunrise == 2 && t_12 != 3) {
                    this.festivalDayMapJD.put("akshaya_tritiya", doubleDateJd);
                } else if (intTithiSunrise == 2 && intTithiSunriseNextDay == 4) {
                    this.festivalDayMapJD.put("akshaya_tritiya", doubleDateJd);
                }
            }

            if (intMoonMonth == 4 && (intTithiSunrise == 2 || intTithiSunrise == 3)) {
                if (intTithiSunrise == 2) {
                    if (intTithiSunriseNextDay == 2) {
                        this.festivalDayMapJD.put("jagannath_rath_yatra", doubleDateJd + 1.0D);
                    } else {
                        this.festivalDayMapJD.put("jagannath_rath_yatra", doubleDateJd);
                    }
                } else if (intTithiSunrise == 3 && intTithiSunrisePrevDay == 1) {
                    this.festivalDayMapJD.put("jagannath_rath_yatra", doubleDateJd - 1.0D);
                }
            }

            if (intMoonMonth == 4 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[2] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[2] / 24.0D);
                if (intTithiSunrise == 15 && intTithiSunrisePrevDay != 15 && t_10 == 15) {
                    this.festivalDayMapJD.put("guru_purnima", doubleDateJd);
                } else if (intTithiSunrise == 14 && t_12 == 15) {
                    this.festivalDayMapJD.put("guru_purnima", doubleDateJd + 1.0D);
                } else if (intTithiSunrise == 14 && t_12 != 15) {
                    this.festivalDayMapJD.put("guru_purnima", doubleDateJd);
                } else if (intTithiSunrise == 14 && intTithiSunriseNextDay == 16) {
                    this.festivalDayMapJD.put("guru_purnima", doubleDateJd);
                }
            }

            if (intMoonMonth == 4 && intTithiSunrise > 15) {
                if (intTithiSunrise == 16) {
                    this.festivalDayMapJD.put("sawan_purnimanta_start", doubleDateJd);
                } else if (intTithiSunrise == 17 && intTithiSunrisePrevDay == 15) {
                    this.festivalDayMapJD.put("sawan_purnimanta_start", doubleDateJd);
                }

                if (intWeekDay == 1) {
                    ++countSawanPVrat;
                    this.festivalDayMapJD.put("sawansomwarvrat_purnimanta_" + countSawanPVrat, doubleDateJd);
                }
            }

            if (intMoonMonth == 5 && intTithiSunrise < 16) {
                if (intTithiSunrise == 15) {
                    this.festivalDayMapJD.put("sawan_purnimanta_end", doubleDateJd);
                } else if (intTithiSunrise == 14 && intTithiSunriseNextDay == 16) {
                    this.festivalDayMapJD.put("sawan_purnimanta_end", doubleDateJd);
                }

                if (intWeekDay == 1) {
                    ++countSawanPVrat;
                    this.festivalDayMapJD.put("sawansomwarvrat_purnimanta_" + countSawanPVrat, doubleDateJd);
                }
            }

            if (intMoonMonth == 5 && intMoonMonthPrevDay != 5) {
                this.festivalDayMapJD.put("sawan_amanta_start", doubleDateJd);
            }

            if (intMoonMonth == 6 && intMoonMonthPrevDay != 6) {
                this.festivalDayMapJD.put("sawan_amanta_end", doubleDateJd - 1.0D);
            }

            if (intMoonMonth == 5 && intWeekDay == 1) {
                ++countSawanAVrat;
                this.festivalDayMapJD.put("sawansomwarvrat_amanta_" + countSawanAVrat, doubleDateJd);
            }

            if (intMoonMonth == 5) {
                if (intTithiSunrise == 3 || intTithiSunrise == 4) {
                    if (intTithiSunrise == 3) {
                        if (intTithiSunriseNextDay == 3) {
                            this.festivalDayMapJD.put("hariyali_teej", doubleDateJd + 1.0D);
                        } else {
                            this.festivalDayMapJD.put("hariyali_teej", doubleDateJd);
                        }
                    } else if (intTithiSunrise == 4 && intTithiSunrisePrevDay == 2) {
                        this.festivalDayMapJD.put("hariyali_teej", doubleDateJd - 1.0D);
                    }
                }

                if (intTithiSunrise == 18 || intTithiSunrise == 19) {
                    if (intTithiSunrise == 18) {
                        if (intTithiSunriseNextDay == 18) {
                            this.festivalDayMapJD.put("kajari_teej", doubleDateJd + 1.0D);
                        } else {
                            this.festivalDayMapJD.put("kajari_teej", doubleDateJd);
                        }
                    } else if (intTithiSunrise == 19 && intTithiSunrisePrevDay == 17) {
                        this.festivalDayMapJD.put("kajari_teej", doubleDateJd - 1.0D);
                    }
                }
            }

            if (intMoonMonth == 5 && (intTithiSunrise == 4 || intTithiSunrise == 5)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[2] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[3] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[3] / 24.0D);
                if (intTithiSunrise == 4 && t_10 == 5 && t_21 != 5) {
                    this.festivalDayMapJD.put("nag_panchami", doubleDateJd);
                } else if (intTithiSunrise == 5 && intTithiSunrisePrevDay != 5 && t_12 == 5) {
                    this.festivalDayMapJD.put("nag_panchami", doubleDateJd);
                } else if (intTithiSunrise == 4 && t_21 == 5) {
                    this.festivalDayMapJD.put("nag_panchami", doubleDateJd + 1.0D);
                } else if (intTithiSunrise == 4 && t_21 != 5) {
                    this.festivalDayMapJD.put("nag_panchami", doubleDateJd);
                } else if (intTithiSunrise == 4 && intTithiSunriseNextDay == 6) {
                    this.festivalDayMapJD.put("nag_panchami", doubleDateJd);
                }
            }

            if (intMoonMonth == 5 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[3] / 24.0D);
                if (intTithiSunrise == 15 && t_10 == 15) {
                    this.festivalDayMapJD.put("raksha_bandhan", doubleDateJd);
                } else if (intTithiSunrise == 14 && t_10 == 15 && intTithiSunriseNextDay == 16) {
                    this.festivalDayMapJD.put("raksha_bandhan", doubleDateJd);
                } else {
                    this.festivalDayMapJD.put("raksha_bandhan", doubleDateJd - 1.0D);
                }
            }

            if (intMoonMonth == 5 && (intTithiSunrise == 22 || intTithiSunrise == 23)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[7] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[8] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 15)[7] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 15)[8] / 24.0D);
                if ((t_10 == 23 || t_12 == 23) && (t_21 == 23 || t_22 == 23)) {
                    this.festivalDayMapJD.put("janmashtami", doubleDateJd + 1.0D);
                } else if ((t_10 == 23 || t_12 == 23) && t_21 != 23) {
                    this.festivalDayMapJD.put("janmashtami", doubleDateJd);
                }
            }

            if (intMoonMonth == 6) {
                if (intTithiSunrise == 3 || intTithiSunrise == 4) {
                    t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[2] / 24.0D);
                    t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[3] / 24.0D);
                    t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[2] / 24.0D);
                    t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[3] / 24.0D);
                    if (t_10 == 4 && t_12 == 4 && t_21 == 4 && t_22 == 4) {
                        this.festivalDayMapJD.put("ganesh_chaturthi", doubleDateJd);
                    } else if (t_10 == 4 && t_12 == 4) {
                        this.festivalDayMapJD.put("ganesh_chaturthi", doubleDateJd);
                    } else if (t_12 != 4 && t_21 == 4) {
                        this.festivalDayMapJD.put("ganesh_chaturthi", doubleDateJd + 1.0D);
                    } else if (t_12 == 4 && t_21 != 4) {
                        this.festivalDayMapJD.put("ganesh_chaturthi", doubleDateJd);
                    } else if (t_10 != 4 && t_12 == 4 && t_21 == 4 && t_22 == 4) {
                        this.festivalDayMapJD.put("ganesh_chaturthi", doubleDateJd + 1.0D);
                    }
                }

                if (intTithiSunrise == 3 || intTithiSunrise == 4) {
                    if (intTithiSunrise == 3) {
                        if (intTithiSunriseNextDay == 3) {
                            this.festivalDayMapJD.put("hartalika_teej", doubleDateJd + 1.0D);
                        } else {
                            this.festivalDayMapJD.put("hartalika_teej", doubleDateJd);
                        }
                    } else if (intTithiSunrise == 4 && intTithiSunrisePrevDay == 2) {
                        this.festivalDayMapJD.put("hartalika_teej", doubleDateJd - 1.0D);
                    }
                }

                if (intTithiSunrise == 13 || intTithiSunrise == 14) {
                    t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[2] / 24.0D);
                    t_12 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[2] / 24.0D);
                    if (t_10 == 14) {
                        this.festivalDayMapJD.put("anant_chaturdashi", doubleDateJd);
                    } else if (intTithiSunrise == 13 && t_12 != 14) {
                        this.festivalDayMapJD.put("anant_chaturdashi", doubleDateJd);
                    } else if (intTithiSunrise == 13 && intTithiSunriseNextDay == 15) {
                        this.festivalDayMapJD.put("anant_chaturdashi", doubleDateJd);
                    }
                }
            }

            if (intMoonMonth == 7 && intMoonMonthPrevDay != 7) {
                this.festivalDayMapJD.put("ashwin_navratri_ghatasthapana", doubleDateJd);
                this.festivalDayMapJD.put("ashwin_navratri", doubleDateJd);
                key = "";
                key1 = "";
                key2 = "";
                t_22 = 0;
                t_23 = 1;

                for(ni = 0; ni < 12; ++ni) {
                    key = "ashwin_navratri_" + (ni + 1);
                    key1 = "ashwin_navratri_" + (ni + 2);
                    key2 = "ashwin_navratri_" + (ni + 3);
                    tithiSunrise = this.tithi_sunrise[i + t_22];
                    if (ni == 0 && tithiSunrise == 2) {
                        this.festivalDayMapJD.put("ashwin_navratri_ghatasthapana", doubleDateJd - 1.0D);
                        this.festivalDayMapJD.put("ashwin_navratri", doubleDateJd - 1.0D);
                        this.festivalDayMapJD.put("ashwin_navratri_1", doubleDateJd - 1.0D);
                        this.festivalDayMapJD.put("ashwin_navratri_2", doubleDateJd - 1.0D);
                        ++t_23;
                        ni = 1;
                    } else if (tithiSunrise >= 9) {
                        if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 - 1] == 7) {
                            if (this.paranaVart(doubleDateJd + (double)t_22, this.place, 9.0D)) {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                                this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22 + 1.0D);
                            } else {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                                this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put(key2, doubleDateJd + (double)t_22 + 1.0D);
                                this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22 + 1.0D);
                                this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22 + 1.0D);
                            }

                            ++t_22;
                        } else if (tithiSunrise == 10 && this.tithi_sunrise[i + t_22 - 1] == 8) {
                            this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                            this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22);
                            ++t_22;
                        } else if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 + 1] == 10) {
                            if (this.paranaVart(doubleDateJd + (double)t_22, this.place, 9.0D)) {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22 + 1.0D);
                            } else {
                                this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                                this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22 + 1.0D);
                                this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22 + 1.0D);
                                this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22 + 1.0D);
                            }

                            ++t_22;
                        } else if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 + 1] == 11) {
                            this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22);
                            ++t_22;
                        } else if (tithiSunrise == 9 && this.tithi_sunrise[i + t_22 + 1] == 9) {
                            this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                            this.festivalDayMapJD.put(key1, doubleDateJd + (double)t_22 + 1.0D);
                            this.festivalDayMapJD.put("ashwin_navratri_parana", doubleDateJd + (double)t_22 + 1.0D);
                            this.festivalDayMapJD.put("ashwin_navratri_durgavisarjan", doubleDateJd + (double)t_22 + 1.0D);
                            ++t_22;
                        }
                    } else if (tithiSunrise == t_23) {
                        this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                        ++t_23;
                        ++t_22;
                    } else if (tithiSunrise == t_23 - 1) {
                        this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22);
                        ++t_23;
                        ++t_22;
                    } else if (tithiSunrise == t_23 + 1) {
                        this.festivalDayMapJD.put(key, doubleDateJd + (double)t_22 - 1.0D);
                        ++t_23;
                    }
                }
            }

            if (intMoonMonth == 7) {
                if ((intTithiSunrise == 6 || intTithiSunrise == 7) && !this.festivalDayMapJD.containsKey("navpatrika")) {
                    t_10 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[2] / 24.0D);
                    t_12 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[3] / 24.0D);
                    if (intTithiSunriseNextDay == 7 && (t_10 == 7 || t_12 == 7)) {
                        this.festivalDayMapJD.put("navpatrika", doubleDateJd + 1.0D);
                    } else if (intTithiSunriseNextDay != 6) {
                        this.festivalDayMapJD.put("navpatrika", doubleDateJd);
                    }
                }

                if ((intTithiSunrise == 5 || intTithiSunrise == 6) && !this.festivalDayMapJD.containsKey("kalparambha")) {
                    t_10 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[2] / 24.0D);
                    t_12 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 15)[3] / 24.0D);
                    if (intTithiSunriseNextDay == 6 && (t_10 == 6 || t_12 == 6)) {
                        this.festivalDayMapJD.put("kalparambha", doubleDateJd + 1.0D);
                        this.festivalDayMapJD.put("durga_puja_Calendar", doubleDateJd + 1.0D);
                    } else if (intTithiSunriseNextDay != 5) {
                        this.festivalDayMapJD.put("kalparambha", doubleDateJd);
                        this.festivalDayMapJD.put("durga_puja_Calendar", doubleDateJd);
                    }
                }
            }

            if (intMoonMonth == 7 && (intTithiSunrise == 8 || intTithiSunrise == 9) && !this.festivalDayMapJD.containsKey("durga_puja_navami")) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[12] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[13] / 24.0D);
                if (t_10 != 9 && t_12 != 9) {
                    this.festivalDayMapJD.put("durga_puja_navami", doubleDateJd + 1.0D);
                } else {
                    this.festivalDayMapJD.put("durga_puja_navami", doubleDateJd);
                }

                if (intTithiSunrise == 8) {
                    this.festivalDayMapJD.put("durga_puja_ashtami", doubleDateJd);
                } else {
                    this.festivalDayMapJD.put("durga_puja_ashtami", doubleDateJd - 1.0D);
                }
            }

            if (intMoonMonth == 7 && (intTithiSunrise == 9 || intTithiSunrise == 10) && !this.festivalDayMapJD.containsKey("vijayadashami")) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[3] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[4] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[3] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[1] / 24.0D);
                boolean isSravanNak = false;
                if (intTithiSunriseNextDay == 10 && intNakshatraSunRiseNextDay == 22) {
                    double nakET = this.nakshatra(doubleDateJd + 1.0D, this.place)[1];
                    double aprhanT = Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[3];
                    if (nakET > aprhanT) {
                        isSravanNak = true;
                    }
                }

                if (t_10 != 10 && t_21 != 10 && isSravanNak) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd + 1.0D);
                } else if (t_10 == 10 && t_21 != 10 && isSravanNak) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd + 1.0D);
                } else if (t_10 == 10 && t_21 != 10 && !isSravanNak) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd);
                } else if (t_12 != 10 && t_21 == 10 && isSravanNak) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd + 1.0D);
                } else if (t_12 == 10 && t_22 == 10 && intNakshatraSunRiseNextDay == 22) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd + 1.0D);
                } else if (t_10 == 10 && t_21 == 10) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd);
                } else if (t_10 != 10 && t_21 == 10) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd + 1.0D);
                } else if (t_10 == 10 && t_21 != 10) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd);
                } else if (t_10 != 10 && t_21 != 10) {
                    this.festivalDayMapJD.put("vijayadashami", doubleDateJd);
                }
            }

            if (intMoonMonth == 7 && (intTithiSunrise == 18 || intTithiSunrise == 19) && !this.festivalDayMapJD.containsKey("karva_chauth")) {
                CMoon objCMoon = new CMoon();
                t_12 = this.getTithiD(doubleDateJd + objCMoon.getMoonRiseSetTime(doubleDateJd, this.place)[0] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + objCMoon.getMoonRiseSetTime(doubleDateJd + 1.0D, this.place)[0] / 24.0D);
                if (t_12 == 19 && t_21 == 19) {
                    this.festivalDayMapJD.put("karva_chauth", doubleDateJd);
                } else if (t_12 == 19 && t_21 != 19) {
                    this.festivalDayMapJD.put("karva_chauth", doubleDateJd);
                } else if (t_12 != 19 && t_21 == 19) {
                    this.festivalDayMapJD.put("karva_chauth", doubleDateJd + 1.0D);
                } else {
                    this.festivalDayMapJD.put("karva_chauth", doubleDateJd + 1.0D);
                }
            }

            if (intMoonMonth == 7 && (intTithiSunrise == 27 || intTithiSunrise == 28)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[0] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[1] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[0] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[1] / 24.0D);
                if (t_10 == 28 || t_12 == 28) {
                    if (t_21 != 28 && t_22 != 28) {
                        this.festivalDayMapJD.put("dhanteras", doubleDateJd);
                        this.festivalDayMapJD.put("diwali_puja_Calendar", doubleDateJd);
                    } else {
                        this.festivalDayMapJD.put("dhanteras", doubleDateJd + 1.0D);
                        this.festivalDayMapJD.put("diwali_puja_Calendar", doubleDateJd + 1.0D);
                    }
                }
            }

            if (intMoonMonth == 7 && (intTithiSunrise == 28 || intTithiSunrise == 29) && !this.festivalDayMapJD.containsKey("narak_chaturdashi")) {
                t_10 = this.getTithiD(doubleDateJd - 1.0D + Muhurta.getNightDivisons(doubleDateJd - 1.0D, this.place, doubleSunSet, 15)[13] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd - 1.0D + Muhurta.getNightDivisons(doubleDateJd - 1.0D, this.place, doubleSunSet, 15)[14] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[13] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[13] / 24.0D);
                if (t_10 != 29 && t_12 != 29 && intTithiSunrise != 29) {
                    if (t_21 != 29 && t_22 != 29 && intTithiSunriseNextDay != 29) {
                        if (intTithiSunrise == 28 && intTithiSunriseNextDay == 28) {
                            this.festivalDayMapJD.put("narak_chaturdashi", doubleDateJd + 2.0D);
                        } else {
                            this.festivalDayMapJD.put("narak_chaturdashi", doubleDateJd);
                        }
                    } else {
                        this.festivalDayMapJD.put("narak_chaturdashi", doubleDateJd + 1.0D);
                    }
                } else {
                    this.festivalDayMapJD.put("narak_chaturdashi", doubleDateJd);
                }
            }

            if (intMoonMonth == 7 && (intTithiSunrise == 29 || intTithiSunrise == 30) && !this.festivalDayMapJD.containsKey("diwali")) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[0] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[1] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[0] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[1] / 24.0D);
                if (t_21 != 30 && t_22 != 30) {
                    if (t_10 != 30 && t_12 != 30) {
                        if (intTithiSunrise == 30) {
                            this.festivalDayMapJD.put("diwali", doubleDateJd);
                        } else if (intTithiSunrise == 29 && intTithiSunriseNextDay == 1) {
                            this.festivalDayMapJD.put("diwali", doubleDateJd);
                        }
                    } else {
                        this.festivalDayMapJD.put("diwali", doubleDateJd);
                    }
                } else {
                    this.festivalDayMapJD.put("diwali", doubleDateJd + 1.0D);
                }
            }

            if (intMoonMonth == 8 && intMoonMonthPrevDay != 8 && (intTithiSunrise == 1 || intTithiSunrise == 2) && !this.festivalDayMapJD.containsKey("govardhan")) {
                t_10 = this.getTithiD(doubleDateJd - 1.0D + Muhurta.getDayDivisons(doubleDateJd - 1.0D, this.place, Masa.getSunRise(doubleDateJd - 1.0D, this.place), 15)[14] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 15)[9] / 24.0D);
                if (t_12 == 1) {
                    this.festivalDayMapJD.put("govardhan", doubleDateJd);
                } else if (t_10 == 1) {
                    this.festivalDayMapJD.put("govardhan", doubleDateJd - 1.0D);
                }
            }

            if (intMoonMonth == 8 && (intTithiSunrise == 1 || intTithiSunrise == 2)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[3] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[4] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[3] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[4] / 24.0D);
                if (t_10 == 2 || t_12 == 2) {
                    if (t_21 != 2 && t_22 != 2) {
                        this.festivalDayMapJD.put("bhai_dooj", doubleDateJd);
                    } else {
                        this.festivalDayMapJD.put("bhai_dooj", doubleDateJd + 1.0D);
                    }
                }
            }

            if (intMoonMonth == 11 && (intTithiSunrise == 28 || intTithiSunrise == 29) && !this.festivalDayMapJD.containsKey("maha_shivratri")) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[7] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 15)[8] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 15)[8] / 24.0D);
                if (t_10 != 29 && t_12 != 29) {
                    this.festivalDayMapJD.put("maha_shivratri", doubleDateJd + 1.0D);
                } else if (t_21 == 29) {
                    this.festivalDayMapJD.put("maha_shivratri", doubleDateJd + 1.0D);
                } else {
                    this.festivalDayMapJD.put("maha_shivratri", doubleDateJd);
                }
            }

            if (intMoonMonth == 11 && (intTithiSunrise == 4 || intTithiSunrise == 5)) {
                t_10 = this.getTithiD(doubleDateJd - 1.0D + Muhurta.getDayDivisons(doubleDateJd - 1.0D, this.place, Masa.getSunRise(doubleDateJd - 1.0D, this.place), 5)[2] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[2] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + Muhurta.getDayDivisons(doubleDateJd, this.place, doubleSunRise, 5)[5] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[5] / 24.0D);
                if (t_12 == 5 || t_21 == 5) {
                    if (t_22 == 5) {
                        this.festivalDayMapJD.put("vasant_panchami", doubleDateJd + 1.0D);
                        this.festivalDayMapJD.put("saraswati_puja", doubleDateJd + 1.0D);
                    } else if (t_10 != 5) {
                        this.festivalDayMapJD.put("vasant_panchami", doubleDateJd);
                        this.festivalDayMapJD.put("saraswati_puja", doubleDateJd);
                    }
                }
            }

            if (intMoonMonth == 12 && (intTithiSunrise == 14 || intTithiSunrise == 15)) {
                t_10 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[0] / 24.0D);
                t_12 = this.getTithiD(doubleDateJd + Muhurta.getNightDivisons(doubleDateJd, this.place, doubleSunSet, 5)[1] / 24.0D);
                t_21 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[0] / 24.0D);
                t_22 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getNightDivisons(doubleDateJd + 1.0D, this.place, doubleSunSetNextDay, 5)[1] / 24.0D);
                t_23 = this.getTithiD(doubleDateJd + 1.0D + Muhurta.getDayDivisons(doubleDateJd + 1.0D, this.place, doubleSunRiseNextDay, 5)[4] / 24.0D);
                if (t_10 == 15 || t_12 == 15) {
                    if (t_21 != 15 && t_22 != 15 && t_23 != 15) {
                        this.festivalDayMapJD.put("holika_dahan", doubleDateJd);
                        this.festivalDayMapJD.put("holi", doubleDateJd + 1.0D);
                    } else {
                        this.festivalDayMapJD.put("holika_dahan", doubleDateJd + 1.0D);
                        this.festivalDayMapJD.put("holi", doubleDateJd + 2.0D);
                    }
                }
            }
        }

        this.festivalDayMapJD.put("subhas_chandra_bose_jayanti", toJulian(this.year, 1, 23));
        this.festivalDayMapJD.put("republic_day", toJulian(this.year, 1, 26));
        this.festivalDayMapJD.put("banks_holiday", toJulian(this.year, 4, 1));
        this.festivalDayMapJD.put("ambedkar_jayanti", toJulian(this.year, 4, 14));
        this.festivalDayMapJD.put("independence_day", toJulian(this.year, 8, 15));
        this.festivalDayMapJD.put("gandhi_jayanti", toJulian(this.year, 10, 2));
        this.festivalDayMapJD.put("children_day", toJulian(this.year, 11, 14));
        this.festivalDayMapJD.put("english_new_year", toJulian(this.year, 1, 1));
        this.festivalDayMapJD.put("merry_christmas", toJulian(this.year, 12, 25));
        ValueComparator fvc = new ValueComparator(this.festivalDayMapJD);
        TreeMap<String, Double> sorted_map = new TreeMap(fvc);
        sorted_map.putAll(this.festivalDayMapJD);
        Iterator var38 = sorted_map.entrySet().iterator();

        while(var38.hasNext()) {
            Entry<String, Double> entry = (Entry)var38.next();
            int[] dateArray = fromJulian((Double)entry.getValue());
            if (dateArray[0] == this.year) {
                festival_day_list.put((String)entry.getKey(), dateArray);
            }
        }

        return festival_day_list;
    }

    void putKeyInMapCheckContain(String key, double jd, int moonMonth) {
        if (!this.festivalDayMapJD.containsKey(key)) {
            this.festivalDayMapJD.put(key, jd);
        } else if ((moonMonth == 9 || moonMonth == 10) && (Double)this.festivalDayMapJD.get(key) != jd && (Double)this.festivalDayMapJD.get(key) != jd - 1.0D && (Double)this.festivalDayMapJD.get(key) != jd - 2.0D) {
            if (!this.festivalDayMapJD.containsKey(key + "_2")) {
                this.festivalDayMapJD.put(key + "_2", jd);
            } else if ((Double)this.festivalDayMapJD.get(key + "_2") != jd && (Double)this.festivalDayMapJD.get(key + "_2") != jd - 1.0D && (Double)this.festivalDayMapJD.get(key + "_2") != jd - 2.0D) {
                this.festivalDayMapJD.put(key + "_2", jd);
            }
        }

    }

    boolean paranaVart(double jd, Place place, double tithi) {
        double[] tithiParana = this.tithi(jd, place);
        double sunSet = getSunSet(jd, place);
        if ((int)tithi == (int)tithiParana[0]) {
            return tithiParana[1] <= sunSet;
        } else if (tithiParana.length > 2 && (int)tithi == (int)tithiParana[2]) {
            return tithiParana[3] <= sunSet;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        new Place(-33.86785D, 151.20732D, 10.0D);
        Festival objFestival = new Festival();
        new HashMap();
        HashMap<String, int[]> festivalslist = objFestival.festivalsInYear(2018, NewDelhi);
        System.out.println("\nFestivals List : \n");
        Iterator var7 = festivalslist.entrySet().iterator();

        while(var7.hasNext()) {
            Entry m = (Entry)var7.next();
            int[] dt = (int[])festivalslist.get(m.getKey());
            System.out.println(m.getKey() + "  " + dt[2] + "-" + dt[1] + "-" + dt[0]);
        }

    }
}
