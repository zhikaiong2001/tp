package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Adds an Interview to the talent tracker.
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the talent tracker. "
            + MESSAGE_INFORMATION;


    public static final String MESSAGE_SUCCESS = "New Interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This Interview already exists in the talent tracker";
    private Interview interview;

    /**
     * Creates an AddInterviewCommand to add the specified {@code Person}
     */
    public AddInterviewCommand(Interview interview) {
        this.interview = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        boolean isFoundApplicant = false;
        boolean isFoundInterviewer = false;
        boolean isCorrectApplicantPhone = true;
        boolean isCorrectInterviewerPhone = true;
        Phone targetApplicantPhone = interview.getApplicant().getPhone();
        Phone targetInterviewerPhone = interview.getInterviewer().getPhone();
        Person applicantSearch = null;
        Person interviewerSearch = null;

        for (Person p : lastShownList) {
            if (p.getPhone().equals(targetApplicantPhone)) {
                isFoundApplicant = true;
            }

            if (isFoundApplicant) {
                if (p.getPersonType().equals("APPLICANT")) {
                    isCorrectApplicantPhone = false;
                }
                applicantSearch = p;
                break;
            }

        }
        for (Person p : lastShownList) {
            if (p.getPhone().equals(targetInterviewerPhone)) {
                isFoundInterviewer = true;
            }

            if (isFoundInterviewer) {
                if (p.getPersonType().equals("INTERVIEWER")) {
                    isCorrectInterviewerPhone = false;
                }
                interviewerSearch = p;
                break;
            }
        }

        if (interview.getStartTime().isAfter(interview.getEndTime())) {
            throw new CommandException(MESSAGE_INVALID_END_TIME);
        }


        if (!isFoundApplicant || !isFoundInterviewer) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_IN_LIST);
        }
        if (isCorrectApplicantPhone && isCorrectInterviewerPhone) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_INTERVIEWER_AND_APPLICANT_PHONE_NUMBER);
        }
        if (isCorrectApplicantPhone) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_APPLICANT_PHONE_NUMBER);
        }
        if (isCorrectInterviewerPhone) {
            throw new CommandException(Messages.MESSAGE_INCORRECT_INTERVIEWER_PHONE_NUMBER);
        }

        if (model.hasInterview(interview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }


        model.addInterview(interview);

        return new CommandResult(String.format(MESSAGE_SUCCESS, "\n" + Messages.formatInterview(interview)));
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

        AddInterviewCommand otherInterviewCommmand = (AddInterviewCommand) other;
        return interview.equals(otherInterviewCommmand.interview);
    }

    @Override
    public String toString() {
//        return "Description: " + description + " applicant: " + applicant.toString() + " interviewer: "
//                + interviewer.toString() + " date: " + date.toString() + " start: " + startTime.toString() + " end: "
//                + endTime.toString();
        return new ToStringBuilder(this)
                .add("interview", interview)
                .toString();
    }
}
