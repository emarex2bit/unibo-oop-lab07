package it.unibo.nestedenum;

import java.util.Comparator;
/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    enum Month
    {
        JANUARY(31,1),
        FEBRUARY(28,2),
        MARCH(31,3),
        APRIL(30,4),
        MAY(31,5),
        JUNE(30,6),
        JULY(31,7),
        AUGUST(31,8),
        SEPTEMBER(30,9),
        OCTOBER(31,10),
        NOVEMBER(30,11),
        DECEMBER(31,12);

        public int days;
        public int number;

        private Month(int days, int number)
        {
            this.days = days;
            this.number = number;
        }

        static Month fromString(String str)
        {
            if (str == null || str.isBlank()) {
                throw new IllegalArgumentException("Month name cannot be null or empty");
            }

            String s = str.trim().toUpperCase();
            Month matched = null;

            for (Month m : values()) {
                if (m.name().startsWith(s)) {
                    if (matched != null) {
                        throw new IllegalArgumentException("Ambiguous month abbreviation: " + str);
                    }
                    matched = m;
                }
            }

            if (matched == null) {
                throw new IllegalArgumentException("Unknown month: " + str);
            }

            return matched;
        }
    }
    private class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(String arg0, String arg1) {
            Month m1 = Month.fromString(arg0);
            Month m2 = Month.fromString(arg1);
            if(m1.number == m2.number) return 0;
            return m1.number < m2.number ? -1 : +1;
        }

    }

    private class SortByDate implements Comparator<String>{

        @Override
        public int compare(String arg0, String arg1) {
            Month m1 = Month.fromString(arg0);
            Month m2 = Month.fromString(arg1);
            if(m1.days == m2.days) return 0;
            return m1.days < m2.days ? -1 : +1;
        }
        
    }

}


