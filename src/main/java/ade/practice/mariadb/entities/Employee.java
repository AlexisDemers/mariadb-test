package ade.practice.mariadb.entities;

public class Employee extends Entity {
    public String firstName;
    public String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + getId() + '\'' +
                ", firstName='" + lastName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
