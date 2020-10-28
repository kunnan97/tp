package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.COM_ORG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.module.ModuleName;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();
    private final ModuleName moduleName = COM_ORG.getModuleName();
    private final String userInputModuleName = "CS2100";

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        System.out.println(new DeleteCommand(moduleName));
        assertParseSuccess(parser, userInputModuleName, new DeleteCommand(moduleName));
    }
}
