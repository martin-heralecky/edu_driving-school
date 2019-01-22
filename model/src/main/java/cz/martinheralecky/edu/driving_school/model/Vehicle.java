package cz.martinheralecky.edu.driving_school.model;

/**
 * The vehicle entity.
 * <p>
 * This construct is immutable.
 */
public class Vehicle extends Entity {
    protected final String licensePlate;
    protected final String make;
    protected final String model;
    protected final int year;
    protected final String color;

    public Vehicle(ID id, String licensePlate, String make, String model, int year, String color) {
        super(id);

        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }
}
