package base.http;

/**
 * Interface for those object that can be populated.
 */
public interface Populatable {

    /**
     * This method can be invoked after population in order to checks whether the population is valid.
     *
     * @throws PopulateException if this object has not been properly populated
     */
    void validate() throws PopulateException;

}
