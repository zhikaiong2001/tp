package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.interview.Interview;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Interview objects.
 */
public class InterviewBuilder {
    public static final String DEFAULT_DESCRIPTION = "technical interview";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2024, 01, 01);
    public static final LocalTime DEFAULT_START_TIME = LocalTime.of(10, 00);
    public static final LocalTime DEFAULT_END_TIME = LocalTime.of(12, 59);

    private Person applicant;
    private Person interviewer;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    /**
     * Initializes the InterviewBuilder with default details.
     */
    public InterviewBuilder() {
        Name applicantName = new Name("Alice");
        Phone applicantPhone = new Phone("12345678");
        Email applicantEmail = new Email("alice@example.com");
        Remark emptyRemark = new Remark("");
        Set<Tag> tags = new HashSet<>();
        this.applicant = new Applicant(applicantName, applicantPhone, applicantEmail, emptyRemark,
                new ApplicantStatus(ApplicantState.STAGE_ONE.toString()), tags);
        Name interviewName = new Name("Bob");
        Phone interviewerPhone = new Phone("87654321");
        Email interviewerEmail = new Email("bob@example.com");
        this.interviewer = new Interviewer(interviewName, interviewerPhone, interviewerEmail, emptyRemark,
                new InterviewerStatus(InterviewerState.FREE.toString()), tags);
        this.date = DEFAULT_DATE;
        this.startTime = DEFAULT_START_TIME;
        this.endTime = DEFAULT_END_TIME;
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Sets the applicant of the {@code Interview} that we are building.
     */
    public InterviewBuilder withApplicant(Person applicant) {
        this.applicant = applicant;
        return this;
    }

    /**
     * Sets the interviewer of the {@code Interview} that we are building.
     */
    public InterviewBuilder withInterviewer(Person interviewer) {
        this.interviewer = interviewer;
        return this;
    }

    /**
     * Sets the date of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the start time of the {@code Interview} that we are building.
     */
    public InterviewBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the end time of the {@code Interview} that we are building.
     */
    public InterviewBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Sets the description of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds an {@code Interview} with the current properties.
     */
    public Interview buildInterview() {
        return new Interview(applicant, interviewer, date, startTime, endTime, description);
    }
}
