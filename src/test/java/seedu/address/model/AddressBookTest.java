package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPersonWithSamePhone_personWithSamePhoneInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withEmail("alice@example.org").build();
        assertTrue(addressBook.hasPersonWithSamePhone(editedAlice));
    }

    @Test
    public void hasPersonWithSamePhone_personWithDifferentPhoneInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person bob = new PersonBuilder().withPhone("11111111").build(); // Use a phone number not used by ALICE
        assertFalse(addressBook.hasPersonWithSamePhone(bob));
    }


    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void addInterview_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addInterview(null));
    }

    @Test
    public void addInterview_uniqueInterview_success() {
        Interview interview = new InterviewBuilder().buildInterview();
        addressBook.addInterview(interview);
        assertTrue(addressBook.hasInterview(interview));
    }

    @Test
    public void removeInterview_interviewDoesNotExist_throwsInterviewNotFoundException() {
        Interview interview = new InterviewBuilder().buildInterview();
        assertThrows(InterviewNotFoundException.class, () -> addressBook.removeInterview(interview));
    }

    @Test
    public void removeInterview_existingInterview_removesInterview() {
        Interview interview = new InterviewBuilder().buildInterview();
        addressBook.addInterview(interview);
        addressBook.removeInterview(interview);
        AddressBook expectedAddressBook = new AddressBook();
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void setInterviews_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setInterviews(null));
    }

    @Test
    public void setInterviews_list_replacesOwnListWithProvidedList() {
        Interview interview = new InterviewBuilder().buildInterview();
        List<Interview> interviewList = Collections.singletonList(interview);
        addressBook.setInterviews(interviewList);
        AddressBook expectedAddressBook = new AddressBook();
        expectedAddressBook.setInterviews(interviewList);
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void sortInterviews_sortsInterviewsByDateAndTime() {
        Interview earlierInterview = new InterviewBuilder().withDate(LocalDate.now())
                .withStartTime(LocalTime.of(9, 0)).buildInterview();
        Interview laterInterview = new InterviewBuilder().withDate(LocalDate.now())
                .withStartTime(LocalTime.of(10, 0)).buildInterview();
        addressBook.addInterview(laterInterview);
        addressBook.addInterview(earlierInterview);
        addressBook.sortInterviews();
        assertEquals(Arrays.asList(earlierInterview, laterInterview), addressBook.getInterviewList());
    }


    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Interview> getInterviewList() {
            throw new AssertionError("This method should not be called.");
        }
    }

}
