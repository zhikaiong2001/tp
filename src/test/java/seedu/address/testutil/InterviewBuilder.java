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
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.tag.Tag;

/**
 *
 */
public class InterviewBuilder {
    public static final String DEFAULT_DESCRIPTION = "technical interview";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2024, 01, 01);
    public static final LocalTime DEFAULT_START_TIME = LocalTime.of(10, 00);
    public static final LocalTime DEFAULT_END_TIME = LocalTime.of(12, 59);

    private Name DEFAULT_APPLICANT_NAME = new Name("head");
    private Name DEFAULT_INTERVIEWER_NAME = new Name("cube");
    private Phone DEFAULT_APPLICANT_PHONE_NUMBER = new Phone("12345678");
    private Phone DEFAULT_INTERVIEWER_PHONE_NUMBER = new Phone("87654321");
    private Email DEFAULT_APPLICANT_EMAIL = new Email("head@cube.com");
    private Email DEFAULT_INTERVIWER_EMAIL = new Email("cube@head.com");
    private Remark DEFAULT_REMARK = new Remark("");
    private Set<Tag> tags = new HashSet<>();
    private Applicant DEFAULT_APPLICANT = new Applicant(DEFAULT_APPLICANT_NAME, DEFAULT_APPLICANT_PHONE_NUMBER,
            DEFAULT_APPLICANT_EMAIL, DEFAULT_REMARK, new ApplicantStatus(ApplicantState.STAGE_ONE.toString()), tags);
    private Interviewer DEFAULT_INTERVIEWER = new Interviewer(DEFAULT_INTERVIEWER_NAME,
            DEFAULT_INTERVIEWER_PHONE_NUMBER, DEFAULT_INTERVIWER_EMAIL, DEFAULT_REMARK,
            new InterviewerStatus(InterviewerState.FREE.toString()),
            tags);
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private  LocalTime endTime;
    private Applicant applicant;
    private Interviewer interviewer;


    /**
     *
     */
    public InterviewBuilder() {
        applicant = DEFAULT_APPLICANT;
        interviewer = DEFAULT_INTERVIEWER;
        date = DEFAULT_DATE;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        description = DEFAULT_DESCRIPTION;
    }

    public InterviewBuilder(Interview interviewToCopy) {
        applicant = (Applicant) interviewToCopy.getApplicant();
        interviewer = (Interviewer) interviewToCopy.getInterviewer();
        date = interviewToCopy.getDate();
        startTime = interviewToCopy.getStartTime();
        endTime = interviewToCopy.getEndTime();
        description = interviewToCopy.getDescription();
    }

    public InterviewBuilder withApplicant(Applicant applicant) {
        this.applicant = applicant;
        return this;
    }

    public InterviewBuilder withInterviewer(Interviewer interviewer) {
        this.interviewer = interviewer;
        return this;
    }

    public InterviewBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public InterviewBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public InterviewBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public InterviewBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Interview build() {
        return new Interview(applicant, interviewer, date, startTime, endTime, description);
    }

    /**
     *
     */
    public Interview buildInterview() {
        return new Interview(applicant, interviewer, DEFAULT_DATE, DEFAULT_START_TIME,
                DEFAULT_END_TIME, DEFAULT_DESCRIPTION);
    }

}
