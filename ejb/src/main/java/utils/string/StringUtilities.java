package utils.string;

public class StringUtilities {
    /**
     * Returns a string build with the string values of the elements in the array.
     *
     * @return <code>"{value0,value1,...}"</code> if the parameter is not null, where valuen is the string version of element array[n]
     */
    public static <T> String arrayAsString(T[] array) {
        final StringBuilder builder = new StringBuilder();
        if (array == null) {
            builder.append("null");
        } else {
            builder.append('{');
            boolean first = true;
            for (T element : array) {
                if (first) {
                    first = false;
                } else {
                    builder.append(',');
                }
                builder.append(element);
            }
            builder.append('}');
        }
        return builder.toString();
    }
}
