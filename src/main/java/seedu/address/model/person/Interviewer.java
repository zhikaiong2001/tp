package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.person.enums.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents an Interviewer in Tether.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interviewer extends Person {

    private final Type type = Type.INTERVIEWER;

    private final List<InterviewerStatus> upcomingInterviews = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Interviewer(Name name, Phone phone, Email email, Remark remark, InterviewerStatus status, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
        this.tags.add(new Tag("Interviewer"));
        if (!status.value.equals(InterviewerState.FREE.toString())) {
            parseAndAddStatus(status);
        }
    }

    @Override
    public String getPersonType() {
        return type.toString();
    }

    private void parseAndAddStatus(InterviewerStatus status) {
        String[] newlineSeparatedStatusArray = status.value.split("\n");
        for (String individualStatus : newlineSeparatedStatusArray) {
            upcomingInterviews.add(new InterviewerStatus(individualStatus));
        }
    }

    /**
     * Changes the status of this interviewer to free only if they had an interview before.
     *
     * @param model the location of the interviewer to be edited.
     */
    public void updateCurrentStatusToReflectDeletedInterview(Model model, Person applicantScheduled) {
        String scheduledApplicantName = applicantScheduled.getName().toString().toLowerCase();
        for (Iterator<InterviewerStatus> iterator = upcomingInterviews.iterator(); iterator.hasNext();) {
            String status = iterator.next().toString();
            if (status.contains(scheduledApplicantName)) {
                iterator.remove();
                break;
            }
        }
        /*
         * Need to find this interviewer by reference equality and replace them for the change in status to reflect
         * in the gui immediately
         */
        model.setPerson(this, this);
    }

    /**
     * Changes the status of this interviewer to interview with [applicant name] only if the status is free currently.
     *
     * @param model the location of the interviewer to be edited.
     * @param applicantScheduled the applicant whom this interviewer is scheduled with.
     */
    public void updateCurrentStatusToReflectScheduledInterview(Model model, Person applicantScheduled) {
        upcomingInterviews.add(new InterviewerStatus(InterviewerState.OCCUPIED + " " + applicantScheduled.getName()));
        /*
            Need to find this interviewer by reference equality and replace them for the change in status to reflect
            in the gui immediately
         */
        model.setPerson(this, this);
    }

    @Override
    public String getCurrentStatus() {
        if (upcomingInterviews.isEmpty()) {
            return InterviewerState.FREE.toString();
        } else {
            return stringifyInterviewStatuses();
        }
    }

    private String stringifyInterviewStatuses() {
        StringBuilder interviewStatusesString = new StringBuilder();
        int numberOfScheduledInterviews = upcomingInterviews.size();
        for (int i = 0; i < numberOfScheduledInterviews; i++) {
            if (i == numberOfScheduledInterviews - 1) {
                interviewStatusesString.append(upcomingInterviews.get(i));
            } else {
                interviewStatusesString.append(upcomingInterviews.get(i)).append("\n");
            }
        }
        return interviewStatusesString.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
