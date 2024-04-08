package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApplicantPersonCommand;
import seedu.address.logic.commands.AddApplicantStatusCommand;
import seedu.address.logic.commands.AddInterviewCommand;
import seedu.address.logic.commands.AddInterviewerPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterPersonsByStatusCommand;
import seedu.address.logic.commands.FindEmailCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.FindPhoneCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.ViewOverallStatisticsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.Remark;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add_applicant() throws Exception {
        Applicant person = new PersonBuilder().build_applicant();
        AddApplicantPersonCommand command = (AddApplicantPersonCommand) parser.parseCommand(
                PersonUtil.getAddApplicantCommand(person));
        assertEquals(new AddApplicantPersonCommand(person), command);
    }

    @Test
    public void parseCommand_add_interview() throws Exception {
        Applicant head = new PersonBuilder().withName("head").withPhone("12345678")
                .withEmail("head@cube.com").withTags("Applicant").build_applicant();
        Interviewer cube = new PersonBuilder().withName("cube").withPhone("87654321")
                .withEmail("cube@head.com").withTags("Interviewer").withStatus("free").build_interviewer();
        AddInterviewCommand addInterviewCommand = new AddInterviewCommand("technical", head.getPhone(),
                cube.getPhone(), LocalDate.of(2022, 11, 11),
                LocalTime.of(10, 00), LocalTime.of(11, 00));
        AddInterviewCommand command = (AddInterviewCommand) parser.parseCommand(
                "add_interview desc/technical date/2022-11-11 st/10:00 et/11:00 a/12345678 i/87654321");
        assertEquals(addInterviewCommand, command);
    }

    @Test
    public void parseCommand_add_interviewer() throws Exception {
        Interviewer person = new PersonBuilder().withStatus("free").build_interviewer();
        AddInterviewerPersonCommand command = (AddInterviewerPersonCommand) parser.parseCommand(
                PersonUtil.getAddInterviewerCommand(person));
        assertEquals(new AddInterviewerPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " 87652533");
        assertEquals(new DeleteCommand(new Phone("87652533")), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_addApplicantStatus() throws Exception {
        String phone = "98362254";
        String status = ApplicantState.STAGE_ONE.toString();
        AddApplicantStatusCommand command = (AddApplicantStatusCommand) parser.parseCommand(
                AddApplicantStatusCommand.COMMAND_WORD + " " + phone + " s/" + status);
        assertEquals(command, new AddApplicantStatusCommand(new Phone(phone), new ApplicantStatus(status)));
    }

    @Test
    public void parseCommand_find_email() throws Exception {
        List<String> keywords = Arrays.asList("foo@email.com", "bar@email.com", "baz@email.com");
        FindEmailCommand command = (FindEmailCommand) parser.parseCommand(
                FindEmailCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindEmailCommand(new EmailContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_find_name() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindNameCommand command = (FindNameCommand) parser.parseCommand(
                FindNameCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_find_phone() throws Exception {
        List<String> keywords = Arrays.asList("123", "456", "789");
        FindPhoneCommand command = (FindPhoneCommand) parser.parseCommand(
                FindPhoneCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPhoneCommand(new PhoneContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterPersonsByStatus() throws Exception {
        FilterPersonsByStatusCommand command = (FilterPersonsByStatusCommand) parser.parseCommand(
                FilterPersonsByStatusCommand.COMMAND_WORD + " resume review");
        assertEquals(command, new FilterPersonsByStatusCommand(
                new ApplicantStatus(ApplicantState.STAGE_ONE.toString())));
    }

    @Test
    public void parseCommand_viewOverallStatistics() throws Exception {
        ViewOverallStatisticsCommand command = (ViewOverallStatisticsCommand) parser.parseCommand(
                ViewOverallStatisticsCommand.COMMAND_WORD);
        assertEquals(new ViewOverallStatisticsCommand(), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(INDEX_FIRST_PERSON, remark), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
