package specs.theIntenet;

public class Person {
    private String lastName;
    private String firstName;
    private String email;
    private String due;
    private String website;

    public Person(String lastName, String firstName, String email, String due, String website) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.due = due;
        this.website = website;
    }

    public String getFullName() {
        return String.format("%s %s",this.firstName,this.lastName);
    }

    public Float getDue() {
        //'$50.00' ==> '50.00' => 50.00
        // String <=> number
        // decimals = float/double
        // non decimal => int/long
        return Float.parseFloat(due.replace("$",""));
    }
}
