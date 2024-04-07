package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.PersonBuilder;


public class InterviewTest {

    private final InterviewBuilder interviewBuilder = new InterviewBuilder();
    private final PersonBuilder personBuilder = new PersonBuilder();

    @Test
    public void equals() {
        Interview baseInterview = interviewBuilder.buildInterview();

        // Create variations of the interview using the builder for testing
        Interview interviewWithDifferentApplicant = interviewBuilder
                .withApplicant(personBuilder.withPhone("11111111").build_applicant())
                .buildInterview();
        Interview interviewWithDifferentInterviewer = interviewBuilder
                .withInterviewer(personBuilder.withPhone("22222222").withStatus("free").build_interviewer())
                .buildInterview();
        Interview interviewWithDifferentDate = interviewBuilder
                .withDate(LocalDate.of(2024, 2, 2))
                .buildInterview();
        Interview interviewWithDifferentStartTime = interviewBuilder
                .withStartTime(LocalTime.of(11, 0))
                .buildInterview();
        Interview interviewWithDifferentEndTime = interviewBuilder
                .withEndTime(LocalTime.of(13, 0))
                .buildInterview();

        // Standard equality checks
        assertTrue(baseInterview.equals(baseInterview));

        // Different attributes checks
        assertFalse(baseInterview.equals(null));
        assertFalse(baseInterview.equals(new Object()));

        // Different applicant checks
        assertFalse(baseInterview.equals(interviewWithDifferentApplicant));

        // Different interviewer checks
        assertFalse(baseInterview.equals(interviewWithDifferentInterviewer));

        // Different date checks
        assertFalse(baseInterview.equals(interviewWithDifferentDate));

        // Different start time checks
        assertFalse(baseInterview.equals(interviewWithDifferentStartTime));

        // Different end time checks
        assertFalse(baseInterview.equals(interviewWithDifferentEndTime));
    }

    @Test
    public void isSameInterview_sameAttributes_returnsTrue() {
        Interview baseInterview = interviewBuilder.buildInterview();
        Interview sameAttributesInterview = new InterviewBuilder()
                .withApplicant(baseInterview.getApplicant())
                .withInterviewer(baseInterview.getInterviewer())
                .withDate(baseInterview.getDate())
                .withStartTime(baseInterview.getStartTime())
                .withEndTime(baseInterview.getEndTime())
                .withDescription(baseInterview.getDescription())
                .buildInterview();

        assertTrue(baseInterview.isSameInterview(sameAttributesInterview));
    }

    @Test
    public void isSameInterview_differentDescription_returnsTrue() {
        Interview baseInterview = interviewBuilder.buildInterview();
        Interview differentDescriptionInterview = new InterviewBuilder()
                .withDescription("A different description")
                .buildInterview();

        // Assuming that a different description does not make two interviews "different"
        assertTrue(baseInterview.isSameInterview(differentDescriptionInterview));
    }

    @Test
    public void isSameInterview_differentDate_returnsFalse() {
        Interview baseInterview = interviewBuilder.buildInterview();
        Interview differentDateInterview = new InterviewBuilder()
                .withDate(LocalDate.of(2025, 1, 1))
                .buildInterview();

        assertFalse(baseInterview.isSameInterview(differentDateInterview));
    }

    @Test
    public void toString_correctFormat() {
        Interview baseInterview = interviewBuilder.buildInterview();
        String expectedString = " ------Interview------\n"
                + "Applicant: head\n"
                + "Interviewer: cube\n"
                + "Date: "
                + LocalDate.now().toString()
                + "\n"
                + "Start: 10:00 "
                + "End: 12:59\n"
                + "Description: technical interview";

        assertEquals(expectedString, baseInterview.toString());
    }


}
