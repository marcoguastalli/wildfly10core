package utils.string;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class PropertiesUtils {

    /**
     * <p>Receives a property like A:P1=R1,P2=R2;B:P3=R3,P4=R4<br>
     * and returns a map with keys A,B and in the values a map with the P-R pair values<br>
     * Using classess passed by parameter, may extend Enum in which case toString will be used to create them</p>
     *
     * @param property              - the property to parse
     * @param rowSeparator          - separator for diferent rows, default "*" (asterisk)
     * @param keySeparator          - sepator between key and list of values, default ":" (colon)
     * @param pairKeyValueSeparator - separator between pairs of key-value values, default ";" (semmicolon)
     * @param valueSeparator        - separator between keys and values, default "=" (equals)
     * @param valueClass            - class to use for values
     * @param keyClass              - class to use for keys
     * @param rowClass              - class to use for row values
     * @throws InvocationTargetException - upon failure creating the class or enum
     * @throws NoSuchMethodException     - upon failure creating the class or enum
     * @throws InstantiationException    - upon failure creating the class or enum
     * @throws IllegalAccessException    - upon failure creating the class or enum
     * @returns map of the parsed values, empty map if there's nothing to parse
     */
    public static <P, Q, R> Map<R, Map<Q, P>> parseListValues(String property,
                                                              String rowSeparator, String keySeparator,
                                                              String pairKeyValueSeparator, String valueSeparator,
                                                              Class<P> valueClass,
                                                              Class<Q> keyClass,
                                                              Class<R> rowClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Map<R, Map<Q, P>> theProperty = new HashMap<R, Map<Q, P>>();
        String rowSep = StringUtils.defaultIfEmpty("\\*", rowSeparator);
        String keySep = StringUtils.defaultIfEmpty(":", keySeparator);
        String pairSep = StringUtils.defaultIfEmpty(";", pairKeyValueSeparator);
        String valueSep = StringUtils.defaultIfEmpty("=", valueSeparator);

        if (StringUtils.isNotBlank(property)) {
            String[] stringList = property.split(rowSep);
            for (String stringRow : stringList) {
                String[] listOfPairs = stringRow.split(keySep);
                String[] pairs = listOfPairs[1].split(pairSep);
                Map<Q, P> mapPair = new HashMap<Q, P>();
                for (String pair : pairs) {
                    String[] keyAndValue = pair.split(valueSep);
                    P valueObj = construct(valueClass, keyAndValue[1]);
                    Q keyObj = construct(keyClass, keyAndValue[0]);
                    mapPair.put(keyObj, valueObj);
                }
                R rowKeyObj = construct(rowClass, listOfPairs[0]);
                theProperty.put(rowKeyObj, Collections.unmodifiableMap(mapPair));
            }
        }
        return Collections.unmodifiableMap(theProperty);
    }

    private static <E> E construct(Class<E> clazz, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        E valueObj = null;
        if (clazz.isEnum()) {
            for (E e : clazz.getEnumConstants()) {
                if (e.toString().equals(value)) {
                    valueObj = e;
                    break;
                }
            }
        } else {
            valueObj = clazz.getConstructor(String.class).newInstance(value);
        }
        return valueObj;
    }

    /**
     * <p>Parses the property received using default separators</p>
     *
     * @param property   - the property to parse
     * @param valueClass - class to use for values
     * @param keyClass   - class to use for keys
     * @param rowClass   - class to use for row values
     * @throws InvocationTargetException - upon failure creating the class or enum
     * @throws NoSuchMethodException     - upon failure creating the class or enum
     * @throws InstantiationException    - upon failure creating the class or enum
     * @throws IllegalAccessException    - upon failure creating the class or enum
     * @returns map of the parsed values, empty map if there's nothing to parse
     */
    public static <P, Q, R> Map<R, Map<Q, P>> parseListValues(String property, Class<P> valueClass, Class<Q> keyClass, Class<R> rowClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return parseListValues(property, null, null, null, null, valueClass, keyClass, rowClass);
    }

    /**
     * <p>Receives a property like X=A,Y=B,Z=C,W=D<br>
     * and returns a map with keys X,Y,Z,W and values A,B,C,D<br>
     * Using classess passed by parameter, may extend Enum in which case toString will be used to create them</p>
     *
     * @param property       - the property to parse
     * @param keySeparator   - separator between keys (default is ";")
     * @param valueSeparator - separator between values (default is "=")
     * @param keyClass       - class to use for keys
     * @param valueClass     - class to use for values
     * @return map of the parsed values, empty map if there's nothing to parse
     * @throws InvocationTargetException - upon failure creating the class or enum
     * @throws NoSuchMethodException     - upon failure creating the class or enum
     * @throws InstantiationException    - upon failure creating the class or enum
     * @throws IllegalAccessException    - upon failure creating the class or enum
     */
    public static <P, Q> ConcurrentMap<P, Q> parseValues(String property, String keySeparator, String valueSeparator, Class<P> keyClass, Class<Q> valueClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConcurrentMap<P, Q> theProperty = new ConcurrentHashMap<P, Q>();
        String keySep = StringUtils.defaultIfEmpty(";", keySeparator);
        String valSep = StringUtils.defaultIfEmpty("=", valueSeparator);

        if (StringUtils.isNotBlank(property)) {
            for (String value : property.split(keySep)) {
                String[] keyAndValue = value.split(valSep);
                theProperty.put(construct(keyClass, keyAndValue[0]), construct(valueClass, keyAndValue[1]));
            }
        }
        return theProperty;
    }

    /**
     * <p>Parses the property received using default values</p>
     *
     * @param property   - the property to parse
     * @param keyClass   - class to use for keys
     * @param valueClass - class to use for values
     * @return map of the parsed values, empty map if there's nothing to parse
     * @throws InvocationTargetException - upon failure creating the class or enum
     * @throws NoSuchMethodException     - upon failure creating the class or enum
     * @throws InstantiationException    - upon failure creating the class or enum
     * @throws IllegalAccessException    - upon failure creating the class or enum
     * @see #parseValues
     */
    public static <P, Q> ConcurrentMap<P, Q> parseValues(String property, Class<P> keyClass, Class<Q> valueClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return parseValues(property, ";", "=", keyClass, valueClass);
    }
}
