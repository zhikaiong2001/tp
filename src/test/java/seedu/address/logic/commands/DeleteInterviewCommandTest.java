package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteInterviewCommand.MESSAGE_DELETE_INTERVIEW_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalInterviews.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteInterviewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Interview interviewToDelete = model.getFilteredInterviewList().get(INDEX_FIRST_INTERVIEW.getZeroBased());
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW.getZeroBased());
        String expectedMessage = String.format(MESSAGE_DELETE_INTERVIEW_SUCCESS,
                Messages.formatInterview(interviewToDelete));
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteInterview(interviewToDelete);
        assertCommandSuccess(deleteInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(-2);
        assertCommandFailure(deleteInterviewCommand, model, Messages.MESSAGE_INTERVIEW_NOT_IN_LIST);
    }


    @Test
    public void equals() {
        DeleteInterviewCommand deleteFirstCommand = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW.getZeroBased());
        DeleteInterviewCommand deleteSecondCommand = new DeleteInterviewCommand(INDEX_SECOND_INTERVIEW.getZeroBased());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInterviewCommand deleteFirstCommandCopy = new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW.getZeroBased());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        int targetINT = INDEX_FIRST_INTERVIEW.getZeroBased();
        DeleteInterviewCommand deleteCommand = new DeleteInterviewCommand(targetINT);
        String expected = DeleteInterviewCommand.class.getCanonicalName() + "{targetINT=" + targetINT + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
