package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewOverallStatisticsCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_viewOverallStatisticsUnfilteredList_success() {
        ViewOverallStatisticsCommand viewOverallStatisticsCommand = new ViewOverallStatisticsCommand();

        String expectedMessage = "Number of applicants: 8"
                + " (Resume review: 7, Pending interview: 1, Completed interview: 0, Waiting list: 0, Accepted: 0"
                + ", Rejected: 0)" + "\nNumber of interviewers: "
                + "1" + " (Free: 0, Busy: 1)" + "\nNumber of interviews: 0";

        assertCommandSuccess(viewOverallStatisticsCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_viewOverallStatisticsFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        ViewOverallStatisticsCommand viewOverallStatisticsCommand = new ViewOverallStatisticsCommand();

        String expectedMessage = "Number of applicants: 8"
                + " (Resume review: 7, Pending interview: 1, Completed interview: 0, Waiting list: 0, Accepted: 0"
                + ", Rejected: 0)" + "\nNumber of interviewers: "
                + "1" + " (Free: 0, Busy: 1)" + "\nNumber of interviews: 0";

        assertCommandSuccess(viewOverallStatisticsCommand, model, expectedMessage, model);
    }

    @Test
    public void equals() {
        final ViewOverallStatisticsCommand standardCommand = new ViewOverallStatisticsCommand();
        final ViewOverallStatisticsCommand standardCommandCopy = new ViewOverallStatisticsCommand();

        // same object -> return true
        assertEquals(standardCommand, standardCommand);

        // same type -> return true
        assertEquals(standardCommand, standardCommandCopy);

        // null -> return false
        assertNotEquals(standardCommand, null);

        // different types -> return false
        assertNotEquals(standardCommand, new ClearCommand());

    }
}
