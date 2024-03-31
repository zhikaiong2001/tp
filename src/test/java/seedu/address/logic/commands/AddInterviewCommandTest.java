package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

public class AddInterviewCommandTest {
    @Test
    public void equals() {
        Applicant head = new PersonBuilder().withName("head").withPhone("12345678")
                .withEmail("head@cube.com").withTags("Applicant").build_applicant();
        Interviewer cube = new PersonBuilder().withName("cube").withPhone("87654321")
                .withEmail("cube@head.com").withTags("Interviewer").withStatus("free").build_interviewer();
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand("technical", head.getPhone(),
                cube.getPhone(), LocalDate.of(2022, 11, 11),
                LocalTime.of(10, 00), LocalTime.of(11, 00));

        // same object -> returns true
        assertTrue(addInterviewCommand.equals(addInterviewCommand));

        // different types -> returns false
        assertFalse(addInterviewCommand.equals(1));

        // null -> returns false
        assertFalse(addInterviewCommand.equals(null));

    }

    @Test
    public void toStringMethod() {
        Applicant head = new PersonBuilder().withName("head").withPhone("12345678")
                .withEmail("head@cube.com").withTags("Applicant").build_applicant();
        Interviewer cube = new PersonBuilder().withName("cube").withPhone("87654321")
                .withEmail("cube@head.com").withTags("Interviewer").withStatus("free").build_interviewer();
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand("tech", head.getPhone(),
                cube.getPhone(), LocalDate.of(2022, 11, 11),
                LocalTime.of(10, 00), LocalTime.of(11, 00));
        String expected = "Description: tech" + " applicant: 12345678" + " interviewer: 87654321"
                + " date: 2022-11-11" + " start: 10:00" + " end: 11:00";
        assertEquals(expected, addInterviewCommand.toString());
    }
}
