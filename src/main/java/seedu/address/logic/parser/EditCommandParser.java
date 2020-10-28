package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModNameDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Cap;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD_NAME, PREFIX_GRADE, PREFIX_TAG, PREFIX_SEMESTER);

        if (argMultimap.getValue(PREFIX_MOD_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        ModuleName moduleName = ParserUtil.parseName(argMultimap.getValue(PREFIX_MOD_NAME).get());

        EditModNameDescriptor editModNameDescriptor = new EditCommand.EditModNameDescriptor();
        editModNameDescriptor.setName(moduleName);
        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editModNameDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        } else {
            editModNameDescriptor.setGrade(new Grade(Cap.NA.toString()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editModNameDescriptor::setTags);
        if (!editModNameDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (argMultimap.getValue(PREFIX_SEMESTER).isPresent()) {
            editModNameDescriptor.setSemester(ParserUtil.parseSemester(argMultimap.getValue(PREFIX_SEMESTER).get()));
        }

        return new EditCommand(moduleName, editModNameDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
