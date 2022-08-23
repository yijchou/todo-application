package problem1.controller.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandProcessorTest {

    private String[] testCommandLineInput1;
    private CommandProcessor testCommandProcessor;

    @BeforeEach
    void setUp() {
        testCommandProcessor = new CommandProcessor();
    }

    @Test
    void testCSVwArgument() throws InvalidArgumentException {
        testCommandLineInput1 = new String[]{"--csv-file", "/bin/todos", "--add-todo", "--todo-text", "Buy Milk"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput1);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }
    @Test
    void testCSVNoArgument() throws InvalidArgumentException {
        String[] testCommandLineInput2 = new String[]{"--csv-file", "--add-todo", "--todo-text", "Buy Milk"};
        try {
            CommandProcessor testCommandProcessor2 = new CommandProcessor(testCommandLineInput2);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("CSV File requires an argument!", e.getMessage());
        }
    }

    @Test
    void testTodoTextNoArgument() throws InvalidArgumentException {
        String[] testCommandLineInput3 = new String[]{"--csv-file", "/bin/todos", "--add-todo", "--todo-text"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput3);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Todo Text requires a description!", e.getMessage());
        }
    }

    @Test
    void testAddTodoNoTestNoCVSArg() throws InvalidArgumentException {
        String[] testCommandLineInput4 = new String[]{"--csv-file", "--add-todo"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput4);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("CSV File requires an argument!", e.getMessage());
        }
    }

    @Test
    void testTodoTextNoArgumentNoCVSArg() throws InvalidArgumentException {
        String[] testCommandLineInput5 = new String[]{"--csv-file", "--add-todo", "--todo-text"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput5);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("CSV File requires an argument!", e.getMessage());
        }
    }

    @Test
    void testCSVDiffSpot() throws InvalidArgumentException {
        String[] testCommandLineInput6 = new String[]{"--completed","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput6);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testDueDateCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput7 = new String[]{"--due","01/02/2022", "--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput7);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testDueDateNoArg() throws InvalidArgumentException {
        // test with CSV, due date, no argument
        String[] testCommandLineInput8 = new String[]{"--due","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput8);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("A date is needed!", e.getMessage());
        }
    }

    @Test
    void testDueDateWrongMonth() throws InvalidArgumentException {
        String[] testCommandLineInput8 = new String[]{"--due","20/02/2022", "--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput8);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid Date! The format must be mm/dd/yyyy.", e.getMessage());
        }
    }

    @Test
    void testDueDateWrongDay() throws InvalidArgumentException {
        String[] testCommandLineInput9 = new String[]{"--due","02/40/2022", "--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput9);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid Date! The format must be mm/dd/yyyy.", e.getMessage());
        }
    }

    @Test
    void testDueDateWrongYear() throws InvalidArgumentException {
        String[] testCommandLineInput10 = new String[]{"--due","02/04/1900", "--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput10);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Invalid Date! The format must be mm/dd/yyyy.", e.getMessage());
        }
    }
    @Test
    void testPriorityCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput11 = new String[]{"--priority","2","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput11);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testPriorityNoArg () throws InvalidArgumentException {
        String[] testCommandLineInput12 = new String[]{"--priority","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput12);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Priority needs an following value of 1, 2, or 3!", e.getMessage());
        }
    }

    @Test
    void testPriorityIncorrect() throws InvalidArgumentException {
        String[] testCommandLineInput13 = new String[]{"--priority","7", "--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput13);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Priority needs an following value of 1, 2, or 3!", e.getMessage());
        }
    }

    @Test
    void testCategoryCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput14 = new String[]{"--category","groceries","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput14);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testCategoryNoArg() throws InvalidArgumentException {
        String[] testCommandLineInput15 = new String[]{"--category","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput15);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Please name the Category!", e.getMessage());
        }
    }

    @Test
    void testCompleteCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput16 = new String[]{"--complete-todo","5","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput16);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testCompleteNoArg() throws InvalidArgumentException {
        String[] testCommandLineInput17 = new String[]{"--complete-todo","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput17);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("Complete todo needs a TODO ID to follow!", e.getMessage());
        }
    }

    @Test
    void testDisplayCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput19 = new String[]{"--display","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput19);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testDisplayShowIncomplete() throws InvalidArgumentException {
        String[] testCommandLineInput20 = new String[]{"--display","--show-incomplete","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput20);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testNoDisplayShowIncomplete() throws InvalidArgumentException {
        String[] testCommandLineInput21 = new String[]{"--show-incomplete","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput21);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("--display needs to be provided!", e.getMessage());
        }
    }

    @Test
    void testDisplayShowCategoryCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput22 = new String[]{"--display","--show-category","groceries","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput22);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testDisplayShowCategoryNoArg() throws InvalidArgumentException {
        String[] testCommandLineInput23 = new String[]{"--display","--show-category","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput23);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("A category name needs to be provided!", e.getMessage());
        }
    }

    @Test
    void testNoDisplayShowCategory() throws InvalidArgumentException {
        String[] testCommandLineInput24 = new String[]{"--show-category","groceries","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput24);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("--display needs to be provided!", e.getMessage());
        }
    }

    @Test
    void testDisplaySortDateCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput25 = new String[]{"--display","--sort-by-date","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput25);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testNoDisplaySortDate() throws InvalidArgumentException {
        String[] testCommandLineInput26 = new String[]{"--sort-by-date","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput26);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("--display needs to be provided!", e.getMessage());
        }
    }

    @Test
    void testNoDisplaySortDateWPriority() throws InvalidArgumentException {
        String[] testCommandLineInput27 = new String[]{"--display", "--sort-by-date","--sort-by-priority", "--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput27);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("You cannot sort by date and priority!", e.getMessage());
        }
    }

    @Test
    void testDisplaySortPriorityCorrect() throws InvalidArgumentException {
        String[] testCommandLineInput28 = new String[]{"--display","--sort-by-priority","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput28);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void testNoDisplaySortPriority() throws InvalidArgumentException {
        String[] testCommandLineInput29 = new String[]{"--sort-by-priority","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput29);
            fail("An Exception should've been thrown");
        } catch (InvalidArgumentException e) {
            assertEquals("--display needs to be provided!", e.getMessage());
        }
    }

    @Test
    void testManyArguments() throws InvalidArgumentException {
        String[] testCommandLineInput30 = new String[]{"--csv-file", "/bin/todos",
            "--add-todo", "--todo-text", "Buy Milk",
            "--complete-todo", "5",
            "--display","--sort-by-priority","--csv-file", "bin/todos"};
        try {
            testCommandProcessor.processArgs(testCommandLineInput30);
        } catch (InvalidArgumentException e) {
            fail("An Exception should not have been thrown");
        }
    }

    @Test
    void getCommands() throws InvalidArgumentException {
        String[] testCommandLineInput31 = new String[]{"--csv-file", "/bin/todos",
            "--add-todo", "--todo-text", "Buy Milk",
            "--complete-todo", "5",
            "--display","--sort-by-priority","--csv-file", "bin/todos"};
        CommandProcessor testCommandProcessor4 = new CommandProcessor(testCommandLineInput31);
        String expected_string = "[--sort-by-priority, --display, --csv-file, --add-todo, --todo-text, --complete-todo]";
        assertEquals(expected_string, testCommandProcessor4.getCommands().toString());
    }

    @Test
    void getParameters() throws InvalidArgumentException {
        String[] testCommandLineInput32 = new String[]{"--csv-file", "/bin/todos",
            "--add-todo", "--todo-text", "Buy Milk",
            "--complete-todo", "5",
            "--display","--sort-by-priority","--csv-file", "bin/todos"};
        CommandProcessor testCommandProcessor5 = new CommandProcessor(testCommandLineInput32);
        String expected_string = "{--csv-file=bin/todos, --todo-text=Buy Milk, --complete-todo=5}";
        assertEquals(expected_string, testCommandProcessor5.getParameters().toString());
    }

    @Test
    void testToString() throws InvalidArgumentException {
        String[] testCommandLineInput33 = new String[]{"--csv-file", "/bin/todos",
            "--add-todo", "--todo-text", "Buy Milk",
            "--complete-todo", "5",
            "--display","--sort-by-priority","--csv-file", "bin/todos"};
        CommandProcessor testCommandProcessor6 = new CommandProcessor(testCommandLineInput33);
        String expected_string = "CommandProcessor{commands=[--sort-by-priority, --display, " +
            "--csv-file, --add-todo, --todo-text, --complete-todo], " +
            "parameters={--csv-file=bin/todos, --todo-text=Buy Milk, --complete-todo=5}}";
        assertEquals(expected_string, testCommandProcessor6.toString());
    }
}