package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NOT_DATE;
import static seedu.address.logic.Messages.MESSAGE_NOT_TIME;
import static seedu.address.logic.commands.CommandTestUtil.APPLICANT_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEWER_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DATE_INVALID;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_END_TIME_INVALID;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_START_TIME_INVALID;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICANT_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERVIEWER_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddInterviewCommand;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Interviewer;
import seedu.address.testutil.PersonBuilder;

public class AddInterviewCommandParserTest {
    private AddInterviewCommandParser parser = new AddInterviewCommandParser();
    @Test
    public void parse_interviewAllFieldsPresent_success() {
        Applicant head = new PersonBuilder().withName("head").withPhone("12345678")
                .withEmail("head@cube.com").withTags("Applicant").build_applicant();
        Interviewer cube = new PersonBuilder().withName("cube").withPhone("87654321")
                .withEmail("cube@head.com").withTags("Interviewer").withStatus("free").build_interviewer();
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand("technical", head.getPhone(),
                cube.getPhone(), LocalDate.of(2022, 11, 11),
                LocalTime.of(10, 00), LocalTime.of(11, 00));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INTERVIEW_DESCRIPTION + INTERVIEW_DATE + START_TIME
                + END_TIME + APPLICANT_PHONE + INTERVIEWER_PHONE, addInterviewCommand);

        // all fields present
        assertParseSuccess(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE + START_TIME + END_TIME
                + APPLICANT_PHONE + INTERVIEWER_PHONE, addInterviewCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, INTERVIEW_DATE + START_TIME
                        + END_TIME + APPLICANT_PHONE + INTERVIEWER_PHONE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + START_TIME
                + END_TIME + APPLICANT_PHONE + INTERVIEWER_PHONE, expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE
                + END_TIME + APPLICANT_PHONE + INTERVIEWER_PHONE, expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE
                + START_TIME + APPLICANT_PHONE + INTERVIEWER_PHONE, expectedMessage);

        // missing applicant phone number prefix
        assertParseFailure(parser, INTERVIEW_DESCRIPTION
                + INTERVIEW_DATE + START_TIME + END_TIME + INTERVIEWER_PHONE, expectedMessage);

        // missing interviewer phone number prefix
        assertParseFailure(parser, INTERVIEW_DESCRIPTION
                + INTERVIEW_DATE + START_TIME + END_TIME + APPLICANT_PHONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE_INVALID + START_TIME + END_TIME
                + APPLICANT_PHONE + INTERVIEWER_PHONE, MESSAGE_NOT_DATE);

        // invalid start time
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE
                + INTERVIEW_START_TIME_INVALID + END_TIME + APPLICANT_PHONE + INTERVIEWER_PHONE, MESSAGE_NOT_TIME);

        // invalid start time
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE
                + START_TIME + INTERVIEW_END_TIME_INVALID + APPLICANT_PHONE + INTERVIEWER_PHONE, MESSAGE_NOT_TIME);

        // invalid applicant phone number
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE + START_TIME + END_TIME
                        + INVALID_APPLICANT_PHONE + INTERVIEWER_PHONE,
                "Phone numbers should only contain numbers, and it should be at least 3 digits long");

        // invalid interviewer phone number
        assertParseFailure(parser, INTERVIEW_DESCRIPTION + INTERVIEW_DATE + START_TIME
                + END_TIME + APPLICANT_PHONE + INVALID_INTERVIEWER_PHONE,
                "Phone numbers should only contain numbers, and it should be at least 3 digits long");
    }
}
