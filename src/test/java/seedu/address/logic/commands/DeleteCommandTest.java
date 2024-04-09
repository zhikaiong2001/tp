package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPhoneUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(personToDelete.getPhone());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPhoneUnfilteredList_throwsCommandException() {
        Phone invalidPhone = new Phone("999");
        DeleteCommand deleteCommand = new DeleteCommand(invalidPhone);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_IN_LIST);
    }

    @Test
    public void execute_validPhoneFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(personToDelete.getPhone());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPhoneFilteredList_throwsCommandException() {
        Person targetSecondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Phone invalidPhone = targetSecondPerson.getPhone();

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        DeleteCommand deleteCommand = new DeleteCommand(invalidPhone);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_NOT_IN_LIST);
    }

    @Test
    public void execute_personHasNoScheduledInterviews_deletesSuccessfully() throws CommandException {
        Person personToDelete = new PersonBuilder().build();
        ModelStub modelStub = new ModelStubWithPersonAndNoInterviews(personToDelete);

        Phone targetPhone = personToDelete.getPhone();
        DeleteCommand deleteCommand = new DeleteCommand(targetPhone);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, Messages
                .format(personToDelete));

        CommandResult commandResult = deleteCommand.execute(modelStub);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_personHasScheduledInterviews_throwsCommandException() {
        Person personWithScheduledInterviews = new PersonBuilder().build_applicant();
        Interview scheduledInterview = new InterviewBuilder().withApplicant(personWithScheduledInterviews).build();
        ModelStub modelStub = new ModelStubWithPersonAndScheduledInterview(personWithScheduledInterviews,
                scheduledInterview);

        Phone targetPhone = personWithScheduledInterviews.getPhone();
        DeleteCommand deleteCommand = new DeleteCommand(targetPhone);

        assertCommandFailure(deleteCommand, modelStub, Messages.MESSAGE_PERSON_HAS_INTERVIEW);
    }


    private class ModelStubWithPersonAndNoInterviews extends ModelStub {
        private List<Person> persons = new ArrayList<>();

        ModelStubWithPersonAndNoInterviews(Person person) {
            this.persons.add(person);
        }

        @Override
        public boolean hasPerson(Person person) {
            return this.persons.contains(person);
        }

        @Override
        public void deletePerson(Person target) {
            // This method can remain empty as its execution is what's being tested
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            // Simulate no interviews in the model
            return FXCollections.observableArrayList();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            // Return the list containing the scheduled interview
            return FXCollections.observableArrayList(persons);
        }
    }

    private class ModelStubWithPersonAndScheduledInterview extends ModelStub {

        private List<Interview> interviews = new ArrayList<>();
        private List<Person> persons = new ArrayList<>();


        ModelStubWithPersonAndScheduledInterview(Person person, Interview interview) {
            this.persons.add(person);
            this.interviews.add(interview);
        }

        @Override
        public boolean hasPerson(Person person) {
            return this.persons.contains(person);
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            // Return the list containing the scheduled interview
            return FXCollections.observableArrayList(interviews);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            // Return the list containing the scheduled interview
            return FXCollections.observableArrayList(persons);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }





    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortInterview() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInterview(Interview target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            throw new AssertionError("This method should not be called.");
        }
    }


    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new Phone("94351253"));
        DeleteCommand deleteSecondCommand = new DeleteCommand(new Phone("88888888"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new Phone("94351253"));
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
        Phone targetPhone = new Phone("94351253");
        DeleteCommand deleteCommand = new DeleteCommand(targetPhone);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetPhone=" + targetPhone + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
