package seedu.address.model.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;
import seedu.address.testutil.InterviewBuilder;


public class UniqueInterviewListTest {

    @Test
    public void contains_nullInterview_throwsNullPointerException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.contains(null));
    }

    @Test
    public void contains_interviewNotInList_returnsFalse() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview interview = new InterviewBuilder().buildInterview();
        assertFalse(uniqueInterviewList.contains(interview));
    }

    @Test
    public void add_nullInterview_throwsNullPointerException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.add(null));
    }

    @Test
    public void add_duplicateInterview_throwsDuplicateInterviewException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview interview = new InterviewBuilder().buildInterview();
        uniqueInterviewList.add(interview);
        assertThrows(DuplicateInterviewException.class, () -> uniqueInterviewList.add(interview));
    }

    @Test
    public void remove_nullInterview_throwsNullPointerException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.remove(null));
    }

    @Test
    public void remove_interviewDoesNotExist_throwsInterviewNotFoundException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview interview = new InterviewBuilder().buildInterview();
        assertThrows(InterviewNotFoundException.class, () -> uniqueInterviewList.remove(interview));
    }

    @Test
    public void remove_existingInterview_removesInterview() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview interview = new InterviewBuilder().buildInterview();
        uniqueInterviewList.add(interview);
        uniqueInterviewList.remove(interview);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_nullList_throwsNullPointerException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterviews((List<Interview>) null));
    }

    @Test
    public void setInterviews_nullUniqueInterviewList_throwsNullPointerException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        assertThrows(NullPointerException.class, () -> uniqueInterviewList.setInterviews((UniqueInterviewList) null));
    }

    @Test
    public void setInterviews_uniqueInterviewList_replacesOwnListWithProvidedUniqueInterviewList() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.add(new InterviewBuilder().buildInterview());
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        Interview interview = new InterviewBuilder().withDescription("Another interview").buildInterview();
        expectedUniqueInterviewList.add(interview);
        uniqueInterviewList.setInterviews(expectedUniqueInterviewList);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void setInterviews_listWithDuplicateInterviews_throwsDuplicateInterviewException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        List<Interview> listWithDuplicateInterviews = Arrays.asList(new InterviewBuilder()
                .buildInterview(), new InterviewBuilder().buildInterview());
        assertThrows(DuplicateInterviewException.class, () -> uniqueInterviewList
                .setInterviews(listWithDuplicateInterviews));
    }

    @Test
    public void setInterview_targetInterviewNotInList_throwsInterviewNotFoundException() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview targetInterview = new InterviewBuilder().buildInterview();
        Interview editedInterview = new InterviewBuilder().withDescription("Edited interview").buildInterview();
        assertThrows(InterviewNotFoundException.class, () -> uniqueInterviewList
                .setInterview(targetInterview, editedInterview));
    }

    @Test
    public void setInterview_editedInterviewHasDifferentIdentity_success() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview originalInterview = new InterviewBuilder().buildInterview();
        uniqueInterviewList.add(originalInterview);
        Interview editedInterview = new InterviewBuilder().withDescription("Edited interview").buildInterview();
        uniqueInterviewList.setInterview(originalInterview, editedInterview);
        UniqueInterviewList expectedUniqueInterviewList = new UniqueInterviewList();
        expectedUniqueInterviewList.add(editedInterview);
        assertEquals(expectedUniqueInterviewList, uniqueInterviewList);
    }

    @Test
    public void sortInterviewsByDate() {
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        Interview interview1 = new InterviewBuilder().withDate(LocalDate.of(2024, 12, 25))
                .withStartTime(LocalTime.of(10, 0)).buildInterview();
        Interview interview2 = new InterviewBuilder().withDate(LocalDate.of(2024, 12, 24))
                .withStartTime(LocalTime.of(9, 0)).buildInterview();
        uniqueInterviewList.add(interview1);
        uniqueInterviewList.add(interview2);
        uniqueInterviewList.sortInterviewsByDate();
        ObservableList<Interview> expectedSortedInterviews = FXCollections.observableArrayList();
        expectedSortedInterviews.add(interview2); // Earlier date should come first
        expectedSortedInterviews.add(interview1);
        assertEquals(expectedSortedInterviews, uniqueInterviewList.asUnmodifiableObservableList());
    }

}

