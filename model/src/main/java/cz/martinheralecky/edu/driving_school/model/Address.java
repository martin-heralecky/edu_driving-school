package cz.martinheralecky.edu.driving_school.model;

import java.io.Serializable;

/**
 * An address identified by street, city and ZIP code.
 * <p>
 * This construct is immutable.
 */
public class Address implements Serializable {
    protected final String street;
    protected final String city;
    protected final String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    /**
     * Compares all properties (street, city, zipCode).
     */
    @Override
    public boolean equals(Object obj) {
        var addr = ((Address) obj);
        return getStreet().equals(addr.getStreet()) &&
            getCity().equals(addr.getCity()) &&
            getZipCode().equals(addr.getZipCode());
    }

    /**
     * @return The address in "street, city zip_code" format.
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", getStreet(), getCity(), getZipCode());
    }
}
