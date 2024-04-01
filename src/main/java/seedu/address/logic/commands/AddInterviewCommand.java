package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Adds an Interview to Tether.
 */
public class AddInterviewCommand extends Command {

    public static final String COMMAND_WORD = AddPersonCommand.COMMAND_WORD + "_interview";

    public static final String MESSAGE_INFORMATION = "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME "
            + PREFIX_APPLICANT + "APPLICANT PHONE NUMBER"
            + PREFIX_INTERVIEWER + "INTERVIEWER PHONE NUMBER " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "technical interview "
            + PREFIX_DATE + "2022-11-11 "
            + PREFIX_START_TIME + "10:00 "
            + PREFIX_END_TIME + "11:00 "
            + PREFIX_APPLICANT + "88888888"
            + PREFIX_INTERVIEWER + "88889999";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to Tether. "
            + MESSAGE_INFORMATION;


    public static final String MESSAGE_SUCCESS = "New Interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This Interview already exists in Tether.";

    private String description;
    private Phone applicant;
    private Phone interviewer;

    // Format YYYY-MM-DD
    private LocalDate date;
    // Format HH:mm
    private LocalTime startTime;
    private LocalTime endTime;
    private Interview interview;
    private Person applicantSearch;
    private Person interviewerSearch;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Person}
     */
    public AddInterviewCommand(String description, Phone applicant, Phone interviewer, LocalDate date,
                               LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.applicant = applicant;
        this.interviewer = interviewer;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Result result = getResult(model);
        phoneNumberCheck(result.isFoundApplicant, result.isFoundInterviewer, result.isIncorrectApplicantPhone,
                result.isIncorrectInterviewerPhone);
        this.interview = new Interview(applicantSearch, interviewerSearch, date, startTime, endTime, description);
        if (model.hasInterview(interview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }
        model.addInterview(interview);
        model.sortInterview();
        applicantSearch.updateCurrentStatusToReflectScheduledInterview(model);
        interviewerSearch.updateCurrentStatusToReflectScheduledInterview(model, applicantSearch);
        return new CommandResult(String.format(MESSAGE_SUCCESS, "\n" + Messages.formatInterview(interview)));
    }

    private Result getResult(Model model) {
        List<Person> lastShownList = model.getFilteredPersonList();
        boolean isFoundApplicant = false;
        boolean isFoundInterviewer = false;
        boolean isIncorrectApplicantPhone = true;
        boolean isIncorrectInterviewerPhone = true;
        for (Person p : lastShownList) {
            if (p.getPhone().equals(applicant)) {
                isFoundApplicant = true;
            }
            if (isFoundApplicant) {
                if (p.getPersonType().equals("APPLICANT")) {
                    isIncorrectApplicantPhone = false;
                }
                applicantSearch = p;
                break;
            }
        }
        for (Person p : lastShownList) {
            if (p.getPhone().equals(interviewer)) {
                isFoundInterviewer = true;
            }
            if (isFoundInterviewer) {
                if (p.getPersonType().equals("INTERVIEWER")) {
                    isIncorrectInterviewerPhone = false;
                }
                interviewerSearch = p;
                break;
            }
        }
        Result result = new Result(isFoundApplicant, isFoundInterviewer,
                isIncorrectApplicantPhone, isIncorrectInterviewerPhone);
        return result;
    }

    private static class Result {
        public final boolean isFoundApplicant;
        public final boolean isFoundInterviewer;
        public final boolean isIncorrectApplicantPhone;
        public final boolean isIncorrectInterviewerPhone;

        public Result(boolean isFoundApplicant, boolean isFoundInterviewer, boolean isIncorrectApplicantPhone,
                      boolean isIncorrectInterviewerPhone) {
            this.isFoundApplicant = isFoundApplicant;
            this.isFoundInterviewer = isFoundInterviewer;
            this.isIncorrectApplicantPhone = isIncorrectApplicantPhone;
            this.isIncorrectInterviewerPhone = isIncorrectInterviewerPhone;
        }
    }

    private static void phoneNumberCheck(boolean isFoundApplicant,
                                         boolean isFoundInterviewer,
                                         boolean isIncorrectApplicantPhone,
                                         boolean isIncorrectInterviewerPhone) throws CommandException {
        if (!isFoundApplicant || !isFoundInterviewer) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_IN_LIST);
        }
        if (isIncorrectApplicantPhone && isIncorrectInterviewerPhone) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_INTERVIEWER_AND_APPLICANT_PHONE_NUMBER);
        }
        if (isIncorrectApplicantPhone) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER);
        }
        if (isIncorrectInterviewerPhone) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_INTERVIEWER_PHONE_NUMBER);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInterviewCommand)) {
            return false;
        }

        if (sameInputs((AddInterviewCommand) other)) {
            return true;
        }
        AddInterviewCommand otherInterviewCommmand = (AddInterviewCommand) other;
        return interview.equals(otherInterviewCommmand.interview);
    }

    private boolean sameInputs(AddInterviewCommand other) {
        return this.description.equals((other).description)
                && this.applicant.equals((other).applicant)
                && this.interviewer.equals((other).interviewer)
                && this.date.equals((other).date)
                && this.startTime.equals((other).startTime)
                && this.endTime.equals((other).endTime);
    }

    @Override
    public String toString() {
        return "Description: " + description + " applicant: " + applicant.toString() + " interviewer: "
                + interviewer.toString() + " date: " + date.toString() + " start: " + startTime.toString() + " end: "
                + endTime.toString();
    }
}
