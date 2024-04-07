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
    public static final LocalDate DEFAULT_DATE = LocalDate.now();
    public static final LocalTime DEFAULT_START_TIME = LocalTime.of(10, 00);
    public static final LocalTime DEFAULT_END_TIME = LocalTime.of(12, 59);

    public static final Name DEFAULT_APPLICANT_NAME = new Name("head");
    public static final Name DEFAULT_INTERVIEWER_NAME = new Name("cube");
    public static final Phone DEFAULT_APPLICANT_PHONE_NUMBER = new Phone("12345678");
    public static final Phone DEFAULT_INTERVIEWER_PHONE_NUMBER = new Phone("87654321");
    public static final Email DEFAULT_APPLICANT_EMAIL = new Email("head@cube.com");
    public static final Email DEFAULT_INTERVIWER_EMAIL = new Email("cube@head.com");
    public static final Remark DEFAULT_REMARK = new Remark("");
    public static final Set<Tag> DEFAULT_TAGS = new HashSet<>();
    public static final Applicant DEFAULT_APPLICANT = new Applicant(DEFAULT_APPLICANT_NAME,
            DEFAULT_APPLICANT_PHONE_NUMBER, DEFAULT_APPLICANT_EMAIL, DEFAULT_REMARK,
            new ApplicantStatus(ApplicantState.STAGE_ONE.toString()), DEFAULT_TAGS);
    public static final Interviewer DEFAULT_INTERVIEWER = new Interviewer(DEFAULT_INTERVIEWER_NAME,
            DEFAULT_INTERVIEWER_PHONE_NUMBER, DEFAULT_INTERVIWER_EMAIL, DEFAULT_REMARK,
            new InterviewerStatus(InterviewerState.FREE.toString()),
            DEFAULT_TAGS);
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Applicant applicant;
    private Interviewer interviewer;

    /**
     * Initializes the InterviewBuilder.
     */
    public InterviewBuilder() {
        applicant = DEFAULT_APPLICANT;
        interviewer = DEFAULT_INTERVIEWER;
        date = DEFAULT_DATE;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the InterviewBuilder with the data of {@code interviewToCopy}.
     */
    public InterviewBuilder(Interview interviewToCopy) {
        applicant = (Applicant) interviewToCopy.getApplicant();
        interviewer = (Interviewer) interviewToCopy.getInterviewer();
        date = interviewToCopy.getDate();
        startTime = interviewToCopy.getStartTime();
        endTime = interviewToCopy.getEndTime();
        description = interviewToCopy.getDescription();
    }

    /**
     * Sets the {@code Applicant} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withApplicant(Person applicant) {
        this.applicant = (Applicant) applicant;
        return this;
    }

    /**
     * Sets the {@code Interviewer} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withInterviewer(Person interviewer) {
        this.interviewer = (Interviewer) interviewer;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Interview} that we are building.
     */
    public InterviewBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Builds the {@code Interview} with the given {@code Applicant}, {@code Interviewer}, {@code Date},
     * {@code StartTime}, {@code EndTime}, {@code Description}
     */
    public Interview build() {
        return new Interview(applicant, interviewer, date, startTime, endTime, description);
    }

    /**
     * Builds an {@code Interview} with the current properties.
     */
    public Interview buildInterview() {
        return new Interview(applicant, interviewer, date, startTime, endTime, description);
    }
}
