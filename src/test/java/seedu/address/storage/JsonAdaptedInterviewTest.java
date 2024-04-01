package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_NOT_DATE;
import static seedu.address.storage.JsonAdaptedInterview.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.TECH_INTERVIEW;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Name;

public class JsonAdaptedInterviewTest {
    private static final String INVALID_DATE = "2024-02-30"; // Invalid date
    private static final String INVALID_START_TIME = "25:00"; // Invalid time
    private static final String INVALID_END_TIME = "26:00"; // Invalid time

    private static final String VALID_DESCRIPTION = TECH_INTERVIEW.getDescription();
    private static final String VALID_DATE = TECH_INTERVIEW.getDate().toString();
    private static final String VALID_START_TIME = TECH_INTERVIEW.getStartTime().toString();
    private static final String VALID_END_TIME = TECH_INTERVIEW.getEndTime().toString();
    private static final JsonAdaptedPerson VALID_APPLICANT = new JsonAdaptedPerson(TECH_INTERVIEW.getApplicant());
    private static final JsonAdaptedPerson VALID_INTERVIEWER = new JsonAdaptedPerson(TECH_INTERVIEW.getInterviewer());

    @Test
    public void toModelType_validInterviewDetails_returnsInterview() throws Exception {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(TECH_INTERVIEW);
        assertEquals(TECH_INTERVIEW, interview.toModelType());
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_DESCRIPTION, null, VALID_START_TIME,
                        VALID_END_TIME, VALID_APPLICANT, VALID_INTERVIEWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);

    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_DESCRIPTION, VALID_DATE, null,
                        VALID_END_TIME, VALID_APPLICANT, VALID_INTERVIEWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);

    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_DESCRIPTION, VALID_DATE, VALID_START_TIME,
                        null, VALID_APPLICANT, VALID_INTERVIEWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);

    }
}
