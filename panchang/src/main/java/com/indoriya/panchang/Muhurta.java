//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;


import com.indoriya.panchang.util.Util;

public class Muhurta extends Masa {
    public Muhurta() {
    }

    public static void main(String[] args) {
        int jd = (int)toJulian(2015, 1, 26);
        Place place = new Place(28.61D, 77.23D, 5.5D);
        Util util = new Util();
        System.out.print("Sunrise: ");
        System.out.println(util.dms(getSunRise((double)jd, place)));
        System.out.print("Sunset: ");
        System.out.println(util.dms(getSunSet((double)jd, place)));
        double[] fifteenMuhurtas = getFifteenMuhurtaForDay((double)jd, place);
        System.out.print("Day Duration: ");
        System.out.println(dayDuration((double)jd, place));
        System.out.print("Night Duration: ");
        System.out.println(nightDuration((double)jd, place));
        System.out.print("Abhijit: ");
        System.out.println("from: " + util.dms(fifteenMuhurtas[7]) + " to: " + util.dms(fifteenMuhurtas[8]));
        int[] kulikaetc = getKulikaKantakaKalavelaYama((double)jd, place);
        System.out.print("Kulika: ");
        System.out.println("from: " + util.dms(fifteenMuhurtas[kulikaetc[0] - 1]) + " to: " + util.dms(fifteenMuhurtas[kulikaetc[0]]));
        System.out.print("Kantaka / Mrityu: ");
        System.out.println("from: " + util.dms(fifteenMuhurtas[kulikaetc[1] - 1]) + " to: " + util.dms(fifteenMuhurtas[kulikaetc[1]]));
        System.out.print("Kalavela / Ardhayaam: ");
        System.out.println("from: " + util.dms(fifteenMuhurtas[kulikaetc[2] - 1]) + " to: " + util.dms(fifteenMuhurtas[kulikaetc[2]]));
        System.out.print("Yamaghanta: ");
        System.out.println("from: " + util.dms(fifteenMuhurtas[kulikaetc[3] - 1]) + " to: " + util.dms(fifteenMuhurtas[kulikaetc[3]]));
        System.out.print("Dushta Muhurtas: ");
        int[] dushtaMuhurtas = getDushtaMuhurta((double)jd, place);
        System.out.println("from: " + util.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1]) + " to: " + util.dms(fifteenMuhurtas[dushtaMuhurtas[0]]));
        if (dushtaMuhurtas.length > 1) {
            System.out.println("from: " + util.dms(fifteenMuhurtas[dushtaMuhurtas[1] - 1]) + " to: " + util.dms(fifteenMuhurtas[dushtaMuhurtas[1]]));
        }

        System.out.println();
        double[] eightDivisions = getDayDivisons((double)jd, place, getSunRise((double)jd, place), 8);
        int[] rahuetc = getRahuYamaGulikaKaal((double)jd, place);
        System.out.print("Rahu Kaal Vela: ");
        System.out.println("from: " + util.dms(eightDivisions[rahuetc[0] - 1]) + " to: " + util.dms(eightDivisions[rahuetc[0]]));
        System.out.print("Gulika Kaal Vela: ");
        System.out.println("from: " + util.dms(eightDivisions[rahuetc[1] - 1]) + " to: " + util.dms(eightDivisions[rahuetc[1]]));
        System.out.print("Yamaganda Vela: ");
        System.out.println("from: " + util.dms(eightDivisions[rahuetc[2] - 1]) + " to: " + util.dms(eightDivisions[rahuetc[2]]));
        System.out.println();
        //System.out.println("Disha shoola: " + Constants.dishas[getDishaShoola((double)jd) - 1]);
        System.out.println();
        System.out.println("Tara Bala");
        print(getTaraBaliNakshatra(3));
        System.out.println();
        System.out.println("Chandra Bala");
        print(getChandraBaliRasi(2));
        System.out.println();
        System.out.print("15 Muhurtas - 1: ");
        printInDms(getFifteenMuhurtaForDay((double)jd, place));
        System.out.print("15 Muhurtas - 2: ");
        printInDms(getDayDivisons((double)jd, place, getSunRise((double)jd, place), 15));
        System.out.print("8 Chogadiyas of Day Name: ");
        //printConstants(getChogadiyaDayName((double)jd), Constants.dayChoghadiaName);
        System.out.print("8 Chogadiyas of Day: ");
        printInDms(getDayDivisons((double)jd, place, getSunRise((double)jd, place), 8));
        System.out.print("8 Chogadiyas of Night Name: ");
        //printConstants(getChogadiyaNightName((double)jd), Constants.nightChoghadiaName);
        System.out.print("8 Chogadiyas of Night: ");
        printInDms(getNightDivisons((double)jd, place, getSunSet((double)jd, place), 8));
    }

    public static double dayDuration(double jd, Place place) {
        return getSunSet(jd, place) - getSunRise(jd, place);
    }

    public static double nightDuration(double jd, Place place) {
        return getSunRise(jd + 1.0D, place) - getSunSet(jd, place) + 24.0D;
    }

    public static double[] getFifteenMuhurtaForDay(double jd, Place place) {
        return getDayDivisons(jd, place, getSunRise(jd, place), 15);
    }

    public static double[] getDayDivisons(double jd, Place place, double startingPoint, int noOfdivisions) {
        double[] muhurtas = new double[noOfdivisions + 1];
        double muhuratDuration = dayDuration(jd, place) / (double)noOfdivisions;
        double sunrise = startingPoint;
        muhurtas[0] = startingPoint;

        for(int i = 0; i < noOfdivisions; ++i) {
            sunrise += muhuratDuration;
            muhurtas[i + 1] = sunrise;
        }

        return muhurtas;
    }

    public static double[] getNightDivisons(double jd, Place place, double startingPoint, int noOfdivisions) {
        double[] muhurtas = new double[noOfdivisions + 1];
        double muhuratDuration = nightDuration(jd, place) / (double)noOfdivisions;
        double sunset = startingPoint;
        muhurtas[0] = startingPoint;

        for(int i = 0; i < noOfdivisions; ++i) {
            sunset += muhuratDuration;
            muhurtas[i + 1] = sunset;
        }

        return muhurtas;
    }

    public static int[] getKulikaKantakaKalavelaYama(double jd, Place place) {
        int[] kulika = new int[]{14, 12, 10, 8, 6, 4, 2};
        int[] kantak = new int[]{6, 4, 2, 14, 12, 10, 8};
        int[] kalavela = new int[]{8, 6, 4, 2, 14, 12, 10};
        int[] yamakantaka = new int[]{10, 8, 6, 4, 2, 14, 12};
        int vaara = vaara(jd);
        return new int[]{kulika[vaara], kantak[vaara], kalavela[vaara], yamakantaka[vaara]};
    }

    public static int[] getDurMuhurta(double jd, Place place) {
        int[][] day = new int[][]{{6, 7, 8, 10, 14}, {4, 6, 8, 9, 12, 13, 14}, {2, 4, 3, 6, 10}, {2, 4, 8, 9, 10, 14}, {2, 6, 12, 14, 15, 16}, {4, 5, 6, 9, 10, 12, 14}, {1, 2, 8, 10, 11, 12}};
        int vaara = vaara(jd);
        return day[vaara];
    }

    public static int[] getDushtaMuhurta(double jd, Place place) {
        int[][] day = new int[][]{{14}, {9, 12}, {4}, {8}, {6, 12}, {4, 9}, {1, 2}};
        int vaara = vaara(jd);
        return day[vaara];
    }

    public static int getRahuKaal(double jd, Place place) {
        int[] rahukaal = new int[]{8, 2, 7, 5, 6, 4, 3};
        int vaara = vaara(jd);
        return rahukaal[vaara];
    }

    public static int[] getRahuYamaGulikaKaal(double jd, Place place) {
        int[] rahukaal = new int[]{8, 2, 7, 5, 6, 4, 3};
        int[] gulikakaal = new int[]{7, 6, 5, 4, 3, 2, 1};
        int[] yamaganda = new int[]{5, 4, 3, 2, 1, 7, 6};
        int vaara = vaara(jd);
        return new int[]{rahukaal[vaara], gulikakaal[vaara], yamaganda[vaara]};
    }

    public static int getDishaShoola(double jd) {
        int[] shoola = new int[]{2, 1, 3, 3, 4, 2, 1};
        int vaara = vaara(jd);
        return shoola[vaara];
    }

    public static int[] getTaraBaliNakshatra(int todaysNak) {
        int[] taraBaliNakshatras = new int[18];
        int position = 0;

        for(int birthNakshatra = 1; birthNakshatra <= 27; ++birthNakshatra) {
            int distance = (todaysNak - birthNakshatra) % 9 + 1;
            if (distance <= 0) {
                distance += 9;
            }

            if (distance != 3 && distance != 5 && distance != 7) {
                taraBaliNakshatras[position++] = birthNakshatra;
            }
        }

        return taraBaliNakshatras;
    }

    public static int[] getChandraBaliRasi(int todaysRasi) {
        int[] chandraBaliRasi = new int[6];
        int position = 0;

        for(int birthRasi = 1; birthRasi <= 12; ++birthRasi) {
            int distance = (todaysRasi - birthRasi) % 12 + 1;
            if (distance <= 0) {
                distance += 12;
            }

            if (distance != 2 && distance != 4 && distance != 5 && distance != 8 && distance != 9 && distance != 12) {
                chandraBaliRasi[position++] = birthRasi;
            }
        }

        return chandraBaliRasi;
    }

    public static int[] getChogadiyaDayName(double jd) {
        int startDay = getStartChoghadiyaForDay(vaara(jd));
        int[] dayChoghadiaName = new int[8];
        int j = 0;

        int l;
        for(l = startDay; l < 7; ++j) {
            dayChoghadiaName[j] = l++;
        }

        for(l = 0; l <= startDay; ++l) {
            dayChoghadiaName[j] = l;
            ++j;
        }

        return dayChoghadiaName;
    }

    public static int[] getChogadiyaNightName(double jd) {
        int startNight = getStartChoghadiyaForNight(vaara(jd));
        int[] nightChoghadiaName = new int[8];
        int j = 0;

        int l;
        for(l = startNight; l < 7; ++j) {
            nightChoghadiaName[j] = l++;
        }

        for(l = 0; l <= startNight; ++l) {
            nightChoghadiaName[j] = l;
            ++j;
        }

        return nightChoghadiaName;
    }

    static int getStartChoghadiyaForDay(int day) {
        int startDay = 0;
        switch(day) {
        case 0:
            startDay = 0;
            break;
        case 1:
            startDay = 3;
            break;
        case 2:
            startDay = 6;
            break;
        case 3:
            startDay = 2;
            break;
        case 4:
            startDay = 5;
            break;
        case 5:
            startDay = 1;
            break;
        case 6:
            startDay = 4;
        }

        return startDay;
    }

    static int getStartChoghadiyaForNight(int day) {
        int startNight = 0;
        switch(day) {
        case 0:
            startNight = 0;
            break;
        case 1:
            startNight = 2;
            break;
        case 2:
            startNight = 4;
            break;
        case 3:
            startNight = 6;
            break;
        case 4:
            startNight = 1;
            break;
        case 5:
            startNight = 3;
            break;
        case 6:
            startNight = 5;
        }

        return startNight;
    }
}
