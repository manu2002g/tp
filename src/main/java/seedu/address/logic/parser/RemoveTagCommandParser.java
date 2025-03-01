package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemoveTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new RemoveTagCommand object
 */
public class RemoveTagCommandParser implements Parser<RemoveTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveTagCommand
     * and returns an RemoveTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CCA, PREFIX_EDUCATION,
                PREFIX_MODULE, PREFIX_INTERNSHIP);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTagCommand.MESSAGE_USAGE), ive);
        }

        List<Tag> education = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_EDUCATION), Tag.EDUCATION);
        List<Tag> internship = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_INTERNSHIP), Tag.INTERNSHIP);
        List<Tag> module = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_MODULE), Tag.MODULE);
        List<Tag> cca = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_CCA), Tag.CCA);

        return new RemoveTagCommand(index, education, internship, module, cca);
    }
}
