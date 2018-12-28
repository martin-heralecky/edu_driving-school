package cz.martinheralecky.edu.driving_school.model;

import java.time.LocalDate;

/**
 * The teacher entity.
 * <p>
 * This construct is immutable.
 */
public class Teacher extends Entity {
    protected final String firstName;
    protected final String surname;
    protected final String email;
    protected final String phoneNumber;
    protected final LocalDate birthDate;

    public Teacher(ID id, String firstName, String surname, String email, String phoneNumber, LocalDate birthDate) {
        super(id);

        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
