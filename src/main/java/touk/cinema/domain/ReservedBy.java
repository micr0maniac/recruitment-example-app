package touk.cinema.domain;

import java.util.regex.Pattern;

/**
 * Business requirements
 *
 * name and surname should each be at least three characters long, starting
 * with a capital letter. The surname could consist of two parts separated with a
 * single dash, in this case the second part should also start with a capital letter.
 */
public class ReservedBy {

    String firstName;
    String lastName;

    public ReservedBy(String firstName, String lastName) {
        Pattern pattern = Pattern.compile("^\\p{Lu}\\p{Ll}{2,} \\p{Lu}\\p{Ll}{2,}(-\\p{Lu}\\p{Ll}{2,})?$",
            Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS);

        if (!pattern.matcher(firstName + " " + lastName).matches()) {
            throw new InvalidNameException();
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static ReservedBy of(String fullName) {
        String[] parts = fullName.split(" ");
        return new ReservedBy(parts[0], parts[1]);
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}
