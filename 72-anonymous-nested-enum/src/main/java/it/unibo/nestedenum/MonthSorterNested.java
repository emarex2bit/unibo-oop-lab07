package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }

    enum Month
    {
        JENUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEMPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER;

        Month fromString(String str)
        {
            String[] min = {"J", "F", "MAR", "AP", "MAY", "JUN", "JUL", "AU", "S", "O", "N", "D"};
            var s = str.toUpperCase(); 
            for (int j = 0; j < values().length; j++) {
                var m = min[j];
                var month = values()[j].toString().toUpperCase();
                for (int i = 0; i < m.length() && i < s.length(); i++) {
                    if(m.charAt(i) != s.charAt(i)) 
                    {
                        break;
                    }
                    else if(i == m.length())
                    {
                        var s1 = s.substring(m.length());
                        var m1 = month.substring(m.length());

                        if(s1.length() > m1.length()) throw new IllegalArgumentException();
                        else if(s1.length() == m1.length()){
                            if(s1.equals(m1)){
                                return values()[j];
                            } else {
                                throw new IllegalArgumentException();
                            }
                        }
                        else {
                            for (int k = 0; k < s1.length(); k++) {
                                if(s1.charAt(k) != m1.charAt(k))
                                {
                                    throw new IllegalArgumentException();
                                }
                            }
                            return values()[j];
                        }
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }
}


