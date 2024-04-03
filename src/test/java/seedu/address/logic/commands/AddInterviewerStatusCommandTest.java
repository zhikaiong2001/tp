package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_LAST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.testutil.PersonBuilder;

public class AddInterviewerStatusCommandTest {
    private static final String INTERVIEWER_FREE_STATUS_STUB = InterviewerState.FREE.toString();
    private static final String INTERVIEWER_BUSY_STATUS_STUB = InterviewerState.OCCUPIED +  " "
            + BENSON.getPhone().toString();
    private static final String INVALID_INTERVIEWER_BUSY_STATUS_STUB = InterviewerState.OCCUPIED + " 1";
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addStatusUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_LAST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatus(INTERVIEWER_BUSY_STATUS_STUB)
                .build_interviewer();
        AddInterviewerStatusCommand addInterviewerStatusCommand = new AddInterviewerStatusCommand(
                firstPerson.getPhone(), new InterviewerStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = String.format(AddInterviewerStatusCommand.MESSAGE_ADD_STATUS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addInterviewerStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_LAST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withStatus(INTERVIEWER_FREE_STATUS_STUB).build_interviewer();

        AddInterviewerStatusCommand addInterviewerStatusCommand = new AddInterviewerStatusCommand(
                firstPerson.getPhone(), new InterviewerStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = String.format(AddInterviewerStatusCommand.MESSAGE_ADD_STATUS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addInterviewerStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInterviewerPhoneUnfilteredList_failure() {
        AddInterviewerStatusCommand addInterviewerStatusCommand = new AddInterviewerStatusCommand(new Phone("111"),
                new InterviewerStatus(INTERVIEWER_FREE_STATUS_STUB));

        assertCommandFailure(addInterviewerStatusCommand, model, Messages.MESSAGE_INCORRECT_INTERVIEWER_PHONE_NUMBER);
    }

    @Test
    public void execute_invalidApplicantPhoneUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_LAST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatus(INVALID_INTERVIEWER_BUSY_STATUS_STUB)
                .build_interviewer();
        AddInterviewerStatusCommand addInterviewerStatusCommand = new AddInterviewerStatusCommand(
                firstPerson.getPhone(), new InterviewerStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandFailure(addInterviewerStatusCommand, model, expectedMessage);
    }

    @Test
    public void execute_notFoundApplicantUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_LAST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatus("interview with 99999999").build_interviewer();
        AddInterviewerStatusCommand addInterviewerStatusCommand = new AddInterviewerStatusCommand(
                firstPerson.getPhone(), new InterviewerStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandFailure(addInterviewerStatusCommand, model, expectedMessage);
    }

    @Test
    public void execute_passInterviewerPhoneInsteadOfApplicantPhoneInStatusUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_LAST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatus("interview with 87654321").build_interviewer();
        AddInterviewerStatusCommand addInterviewerStatusCommand = new AddInterviewerStatusCommand(
                firstPerson.getPhone(), new InterviewerStatus(editedPerson.getCurrentStatus()));
        String expectedMessage = Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandFailure(addInterviewerStatusCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        final AddInterviewerStatusCommand standardCommand = new AddInterviewerStatusCommand(firstPerson.getPhone(),
                new InterviewerStatus(INTERVIEWER_FREE_STATUS_STUB));

        // same values -> returns true
        AddInterviewerStatusCommand commandWithSameValues = new AddInterviewerStatusCommand(firstPerson.getPhone(),
                new InterviewerStatus(INTERVIEWER_FREE_STATUS_STUB));
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new AddInterviewerStatusCommand(secondPerson.getPhone(),
                new InterviewerStatus(INTERVIEWER_FREE_STATUS_STUB)));

        // different remark -> returns false
        assertNotEquals(standardCommand, new AddInterviewerStatusCommand(secondPerson.getPhone(),
                new InterviewerStatus(INTERVIEWER_FREE_STATUS_STUB)));
    }
}
