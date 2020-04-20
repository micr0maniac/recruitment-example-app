package touk.cinema.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservedByTest {

    @Test
    public void lengthTest() {
        Assertions.assertThatCode(() -> {
            String sufficientLengthFullName = "Abc Def";
            ReservedBy.of(sufficientLengthFullName);
        }).doesNotThrowAnyException();

        assertThrows(InvalidNameException.class, () -> {
            String tooShortFirstName = "Ab Cdef";
            ReservedBy.of(tooShortFirstName);
        });

        assertThrows(InvalidNameException.class, () -> {
            String tooShortLastName = "Abc De";
            ReservedBy.of(tooShortLastName);
        });
    }

    @Test
    public void capitalLetterTest() {
        Assertions.assertThatCode(() -> {
            String validName = "Lorem Ipsum";
            ReservedBy.of(validName);
        }).doesNotThrowAnyException();

        assertThrows(InvalidNameException.class, () -> {
            String lowerFirstName = "lorem Ipsum";
            ReservedBy.of(lowerFirstName);
        });

        assertThrows(InvalidNameException.class, () -> {
            String lowerLastName = "Lorem ipsum";
            ReservedBy.of(lowerLastName);
        });
    }

    @Test
    public void lastNameFormatTest() {
        Assertions.assertThatCode(() -> {
            String validLastName = "Lorem Ipsum";
            ReservedBy.of(validLastName);
        }).doesNotThrowAnyException();

        Assertions.assertThatCode(() -> {
            String twoPartsLastName = "Lorem Ipsum-Dolor";
            ReservedBy.of(twoPartsLastName);
        }).doesNotThrowAnyException();

        assertThrows(InvalidNameException.class, () -> {
            String threePartsLastName = "Lorem Ipsum-Dolor-Sit";
            ReservedBy.of(threePartsLastName);
        });
    }

    @Test
    public void unicodeTest() {
        Assertions.assertThatCode(() -> {
            String nameWithPolishCharacters = "Aaęąęąść Eąąśśćśąć";
            ReservedBy.of(nameWithPolishCharacters);
        }).doesNotThrowAnyException();
    }

}