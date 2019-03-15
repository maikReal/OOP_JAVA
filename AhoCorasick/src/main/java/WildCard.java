import java.util.List;

public interface WildCard {

    /**
     * Get all indexes from which the pattern starts
     *
     * @param pattern pattern which we should to find
     * @param example string from which we should to find the pattern
     * @return indexes from which pattern starts
     */
    List<Integer> getAllConjunctions(String pattern, String example);
}
