import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.min;

public class AhoCorasick implements PatternFinder, WildCard {

    /**
     * Realisation of Z-function, which calculate the biggest occurances of the substring  since some index to whole
     * concatenated string. For example, for concatenated string `aa??aba$ababacaba`, Z-function will
     * [0,0,0,0,3,0,1,0,5,0,7,0,1,0,3,0,1,1]
     *
     * @param str concatenated string `pattern$text`
     * @return Z-function
     */
    private static int[] getZarr(String str) {

        int n = str.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r) {
                z[i] = min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n) {
                char c = str.charAt(i + z[i]);
                if (str.charAt(z[i]) == c || str.charAt(z[i]) == '?' && c != '$') {
                    ++z[i];
                } else {
                    break;
                }
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;

    }

    @Override
    public int findConjunction(String pattern, String example) {

        return getAllConjunctions(pattern, example).size();
    }

    @Override
    public List<Integer> getAllConjunctions(String pattern, String example) {
        String concatString = pattern + "$" + example;
        int[] Z = getZarr(concatString);

        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = pattern.length(); i < Z.length; ++i) {
            if (Z[i] == pattern.length()) {
                int index = i - pattern.length() - 1;
                indexes.add(index);
            }
        }

        return indexes;
    }
}
