package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 */
public class TypicalInterviews {

    public static final Interview TECH_INTERVIEW = new InterviewBuilder().withApplicant(
            (Applicant) TypicalPersons.ALICE)
            .withInterviewer((Interviewer) TypicalPersons.CUBE)
            .withDate(LocalDate.of(2024, 01, 01))
            .withStartTime(LocalTime.of(10, 00)).withEndTime(LocalTime.of(12, 00))
            .withDescription("Technical interview for software engineer position").buildInterview();

    public static final Interview HR_INTERVIEW = new InterviewBuilder().withApplicant(
            (Applicant) TypicalPersons.BENSON)
            .withInterviewer((Interviewer) TypicalPersons.CUBE)
            .withDate(LocalDate.of(2024, 01, 02))
            .withStartTime(LocalTime.of(13, 00)).withEndTime(LocalTime.of(14, 00))
            .withDescription("HR interview for culture fit assessment").buildInterview();

    private TypicalInterviews() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical interviews.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person p: getTypicalPersons()) {
            ab.addPerson(p);
        }
        for (Interview interview : getTypicalInterviews()) {
            ab.addInterview(interview);
        }
        return ab;
    }

    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(TECH_INTERVIEW, HR_INTERVIEW));
    }
}
