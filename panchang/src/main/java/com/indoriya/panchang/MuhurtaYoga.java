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

public class MuhurtaYoga extends Masa {
    protected int arraySize;
    protected int year;
    protected Place place;
    double jd;
    int[] moon_month;
    int[] nakshatram_sunrise;
    HashMap<String, Double> muhurtaYogaDayMapJD = new HashMap();

    public MuhurtaYoga() {
    }

    public HashMap<String, int[]> muhurtaYogaInYear(int yearOfMUhurta, Place place) {
        super.place = place;
        this.place = place;
        this.year = yearOfMUhurta;
        this.arraySize = 368;
        this.moon_month = new int[this.arraySize];
        this.nakshatram_sunrise = new int[this.arraySize];
        this.jd = toJulian(this.year - 1, 12, 31);
        this.assignArrays(this.jd, this.place);
        return this.muhurtaYogaFindMap();
    }

    void assignArrays(double jdP, Place place) {
        for(int i = 0; i < this.arraySize; ++i) {
            this.nakshatram_sunrise[i] = (int)this.nakshatra(jdP + (double)i, place)[0];
            int[] x = this.masa(jdP + (double)i, place);
            if (x[1] != 1) {
                this.moon_month[i] = x[0];
            } else {
                this.moon_month[i] = 13;
            }
        }

    }

    public HashMap<String, int[]> muhurtaYogaFindMap() {
        LinkedHashMap<String, int[]> muhurtaYogaDayMap = new LinkedHashMap();
        int amritCount = 0;
        int sarvarthaCount = 0;

        for(int i = 1; i < this.arraySize - 1; ++i) {
            int dayNum = Masa.vaara(this.jd + (double)i);
            int moonMoonthNum = this.moon_month[i];
            int nakshatraNum = this.nakshatram_sunrise[i];
            int nakshatraNumNextDay = this.nakshatram_sunrise[i + 1];
            String localKey = "";
            localKey = "gurupushya_yoga";
            if (dayNum == 4 && (nakshatraNum == 7 || nakshatraNum == 8)) {
                if (nakshatraNumNextDay != 7) {
                    this.putKeyInMapCheckContain(localKey, moonMoonthNum, this.jd + (double)i);
                }
            } else if (dayNum == 4 && nakshatraNum == 6 && nakshatraNumNextDay == 8) {
                this.putKeyInMapCheckContain(localKey, moonMoonthNum, this.jd + (double)i);
            }

            localKey = "ravipushya_yoga";
            if (dayNum == 0 && (nakshatraNum == 7 || nakshatraNum == 8)) {
                if (nakshatraNumNextDay != 7) {
                    this.putKeyInMapCheckContain(localKey, moonMoonthNum, this.jd + (double)i);
                }
            } else if (dayNum == 0 && nakshatraNum == 6 && nakshatraNumNextDay == 8) {
                this.putKeyInMapCheckContain(localKey, moonMoonthNum, this.jd + (double)i);
            }

            localKey = "amrit_siddhi_yoga";
            switch(dayNum) {
            case 0:
                if (nakshatraNum != 12 && nakshatraNum != 13) {
                    if (nakshatraNum == 11 && nakshatraNumNextDay == 13) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 12) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
                break;
            case 1:
                if (nakshatraNum != 4 && nakshatraNum != 5) {
                    if (nakshatraNum == 3 && nakshatraNumNextDay == 5) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 4) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
                break;
            case 2:
                if (nakshatraNum != 27 && nakshatraNum != 1) {
                    if (nakshatraNum == 26 && nakshatraNumNextDay == 1) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 27) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
                break;
            case 3:
                if (nakshatraNum != 16 && nakshatraNum != 17) {
                    if (nakshatraNum == 15 && nakshatraNumNextDay == 17) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 16) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
                break;
            case 4:
                if (nakshatraNum != 7 && nakshatraNum != 8) {
                    if (nakshatraNum == 6 && nakshatraNumNextDay == 8) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 7) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
                break;
            case 5:
                if (nakshatraNum != 26 && nakshatraNum != 27) {
                    if (nakshatraNum == 25 && nakshatraNumNextDay == 27) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 26) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
                break;
            case 6:
                if (nakshatraNum != 3 && nakshatraNum != 4) {
                    if (nakshatraNum == 2 && nakshatraNumNextDay == 4) {
                        ++amritCount;
                        this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 3) {
                    ++amritCount;
                    this.putKeyInMap(localKey, amritCount, this.jd + (double)i);
                }
            }

            localKey = "Sarvartha_siddhi_yoga";
            switch(dayNum) {
            case 0:
                if (nakshatraNum != 12 && nakshatraNum != 13) {
                    if (nakshatraNum == 11 && nakshatraNumNextDay == 13) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 12) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 18 && nakshatraNum != 19) {
                    if (nakshatraNum == 17 && nakshatraNumNextDay == 19) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 18) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 11 && nakshatraNum != 12) {
                    if (nakshatraNum == 10 && nakshatraNumNextDay == 12) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 11) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 20 && nakshatraNum != 21) {
                    if (nakshatraNum == 19 && nakshatraNumNextDay == 21) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 20) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 25 && nakshatraNum != 26) {
                    if (nakshatraNum == 24 && nakshatraNumNextDay == 26) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 25) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 7 && nakshatraNum != 8) {
                    if (nakshatraNum == 6 && nakshatraNumNextDay == 8) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 7) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 27 && nakshatraNum != 1) {
                    if (nakshatraNum == 26 && nakshatraNumNextDay == 1) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 27) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
                break;
            case 1:
                if (nakshatraNum != 21 && nakshatraNum != 22) {
                    if (nakshatraNum == 20 && nakshatraNumNextDay == 22) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 21) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 3 && nakshatraNum != 4) {
                    if (nakshatraNum == 2 && nakshatraNumNextDay == 4) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 3) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 4 && nakshatraNum != 5) {
                    if (nakshatraNum == 3 && nakshatraNumNextDay == 5) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 4) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 7 && nakshatraNum != 8) {
                    if (nakshatraNum == 6 && nakshatraNumNextDay == 8) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 7) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 16 && nakshatraNum != 17) {
                    if (nakshatraNum == 15 && nakshatraNumNextDay == 17) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 16) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
                break;
            case 2:
                if (nakshatraNum != 27 && nakshatraNum != 1) {
                    if (nakshatraNum == 26 && nakshatraNumNextDay == 1) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 27) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 25 && nakshatraNum != 26) {
                    if (nakshatraNum == 24 && nakshatraNumNextDay == 26) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 25) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 2 && nakshatraNum != 3) {
                    if (nakshatraNum == 1 && nakshatraNumNextDay == 3) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 2) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 8 && nakshatraNum != 9) {
                    if (nakshatraNum == 7 && nakshatraNumNextDay == 9) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 8) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
                break;
            case 3:
                if (nakshatraNum != 3 && nakshatraNum != 4) {
                    if (nakshatraNum == 2 && nakshatraNumNextDay == 4) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 3) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 16 && nakshatraNum != 17) {
                    if (nakshatraNum == 15 && nakshatraNumNextDay == 17) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 16) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 12 && nakshatraNum != 13) {
                    if (nakshatraNum == 11 && nakshatraNumNextDay == 13) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 12) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 2 && nakshatraNum != 3) {
                    if (nakshatraNum == 1 && nakshatraNumNextDay == 3) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 2) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 4 && nakshatraNum != 5) {
                    if (nakshatraNum == 3 && nakshatraNumNextDay == 5) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 4) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
                break;
            case 4:
                if (nakshatraNum != 26 && nakshatraNum != 27) {
                    if (nakshatraNum == 25 && nakshatraNumNextDay == 27) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 26) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 16 && nakshatraNum != 17) {
                    if (nakshatraNum == 15 && nakshatraNumNextDay == 17) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 16) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 27 && nakshatraNum != 1) {
                    if (nakshatraNum == 26 && nakshatraNumNextDay == 1) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 27) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 6 && nakshatraNum != 7) {
                    if (nakshatraNum == 5 && nakshatraNumNextDay == 7) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 6) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 7 && nakshatraNum != 8) {
                    if (nakshatraNum == 6 && nakshatraNumNextDay == 8) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 7) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
                break;
            case 5:
                if (nakshatraNum != 26 && nakshatraNum != 27) {
                    if (nakshatraNum == 25 && nakshatraNumNextDay == 27) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 26) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 16 && nakshatraNum != 17) {
                    if (nakshatraNum == 15 && nakshatraNumNextDay == 17) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 16) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 27 && nakshatraNum != 1) {
                    if (nakshatraNum == 26 && nakshatraNumNextDay == 1) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 27) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 6 && nakshatraNum != 7) {
                    if (nakshatraNum == 5 && nakshatraNumNextDay == 7) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 6) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 21 && nakshatraNum != 22) {
                    if (nakshatraNum == 20 && nakshatraNumNextDay == 22) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 21) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
                break;
            case 6:
                if (nakshatraNum != 21 && nakshatraNum != 22) {
                    if (nakshatraNum == 20 && nakshatraNumNextDay == 22) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 21) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 3 && nakshatraNum != 4) {
                    if (nakshatraNum == 2 && nakshatraNumNextDay == 4) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 3) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }

                if (nakshatraNum != 14 && nakshatraNum != 15) {
                    if (nakshatraNum == 13 && nakshatraNumNextDay == 15) {
                        ++sarvarthaCount;
                        this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                    }
                } else if (nakshatraNumNextDay != 14) {
                    ++sarvarthaCount;
                    this.putKeyInMap(localKey, sarvarthaCount, this.jd + (double)i);
                }
            }
        }

        ValueComparator fvc = new ValueComparator(this.muhurtaYogaDayMapJD);
        TreeMap<String, Double> sorted_map = new TreeMap(fvc);
        sorted_map.putAll(this.muhurtaYogaDayMapJD);
        Iterator var13 = sorted_map.entrySet().iterator();

        while(var13.hasNext()) {
            Entry<String, Double> entry = (Entry)var13.next();
            int[] dateArray = fromJulian((Double)entry.getValue());
            if (dateArray[0] == this.year) {
                muhurtaYogaDayMap.put((String)entry.getKey(), dateArray);
            }
        }

        return muhurtaYogaDayMap;
    }

    void putKeyInMap(String key, int moonMonth, double jdDate) {
        this.muhurtaYogaDayMapJD.put(key + "_" + moonMonth, jdDate);
    }

    void putKeyInMapCheckContain(String key, int moonMonth, double jdDate) {
        String locKey = key + "_" + moonMonth;
        if (!this.muhurtaYogaDayMapJD.containsKey(locKey)) {
            this.muhurtaYogaDayMapJD.put(locKey, jdDate);
        } else {
            this.muhurtaYogaDayMapJD.put(locKey + "_1", jdDate);
        }

    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        new Place(40.71427D, -74.00597D, -5.0D);
        new Place(-33.86785D, 151.20732D, 10.0D);
        new HashMap();
        MuhurtaYoga objMuhurtaYoga = new MuhurtaYoga();
        HashMap<String, int[]> festivalslist = objMuhurtaYoga.muhurtaYogaInYear(2017, NewDelhi);
        System.out.println("\nMuhurta & Yoga List : \n");
        Iterator var7 = festivalslist.entrySet().iterator();

        while(var7.hasNext()) {
            Entry m = (Entry)var7.next();
            int[] dt = (int[])festivalslist.get(m.getKey());
            System.out.println(m.getKey() + "  " + dt[2] + "-" + dt[1] + "-" + dt[0]);
        }

    }
}
