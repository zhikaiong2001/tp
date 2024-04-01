package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.person.enums.Type;

/**
 * Displays preselected person and interview statistics for the currently filtered person and interview lists.
 */
public class ViewOverallStatisticsCommand extends Command {

    public static final String COMMAND_WORD = "view_overall_statistics";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays overall statistics for the currently "
            + "displayed list.";

    /**
     * Creates a ViewOverallStatisticsCommand
     */
    public ViewOverallStatisticsCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String statisticsOverview = retrieveStatistics(model);
        return new CommandResult(statisticsOverview);
    }

    private String retrieveStatistics(Model model) {
        // String counts of applicants, interviewers, pending interviews
        String numberOfApplicants = getFilteredPersonListCount(model, person ->
                person.getPersonType().equals(Type.APPLICANT.toString()));
        String numberOfInterviewers = getFilteredPersonListCount(model, person ->
                person.getPersonType().equals(Type.INTERVIEWER.toString()));
        String numberOfInterviews = "" + model.getAddressBook().getInterviewList().size();

        // String count of interviewers by status
        String numberOfFreeInterviewers = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(InterviewerState.FREE.toString()));
        String numberOfOccupiedInterviewers = "" + (Integer.parseInt(numberOfInterviewers)
                - Integer.parseInt(numberOfFreeInterviewers));

        // String of applicants by status
        String numberOfApplicantsByStatus = retrieveApplicantStatusStatistics(model);

        return "Number of applicants: "
                + numberOfApplicants + " (" + numberOfApplicantsByStatus + ")"
                + "\nNumber of interviewers: "
                + numberOfInterviewers + " (Free: " + numberOfFreeInterviewers + ", Busy: "
                + numberOfOccupiedInterviewers + ")\nNumber of interviews: "
                + numberOfInterviews;
    }

    private String getFilteredPersonListCount(Model model, Predicate<Person> predicate) {
        return "" + model.getAddressBook().getPersonList().stream().parallel().filter(predicate).count();
    }

    private String retrieveApplicantStatusStatistics(Model model) {
        String numberOfReviewableApplicants = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(ApplicantState.STAGE_ONE.toString()));
        String numberOfApplicantsPendingIntv = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(ApplicantState.STAGE_TWO.toString()));
        String numberOfApplicantsCompletedIntv = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(ApplicantState.STAGE_THREE.toString()));
        String numberOfApplicantsWaitlisted = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(ApplicantState.OUTCOME_ONE.toString()));
        String numberOfApplicantsAccepted = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(ApplicantState.OUTCOME_TWO.toString()));
        String numberOfApplicantsRejected = getFilteredPersonListCount(model, person ->
                person.getCurrentStatus().equals(ApplicantState.OUTCOME_THREE.toString()));

        return "Resume review: " + numberOfReviewableApplicants
                + ", Pending interview: " + numberOfApplicantsPendingIntv
                + ", Completed interview: " + numberOfApplicantsCompletedIntv
                + ", Waiting list: " + numberOfApplicantsWaitlisted
                + ", Accepted: " + numberOfApplicantsAccepted
                + ", Rejected: " + numberOfApplicantsRejected;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof ViewOverallStatisticsCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
