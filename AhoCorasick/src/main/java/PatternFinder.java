public interface PatternFinder {

    /**
     * Find the number of occurrence of the pattern to string
     *
     * @param pattern pattern, which occurrences we should to find
     * @param example string in which we should to find the occurrences
     * @return number of occurrences
     */
    int findConjunction(String pattern, String example);
}
