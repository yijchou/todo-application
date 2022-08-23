package problem1.controller.toDoController;

import com.sun.tools.javac.comp.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.command.CommandProcessor;
import problem1.controller.command.InvalidArgumentException;
import problem1.model.IToDoList;
import problem1.model.ToDoList;

import static org.junit.jupiter.api.Assertions.*;

class TodoHandlerTest {

    String[] testCommandLineString1;
    String[] testCommandLineString2;
    String[] testCommandLineString3;
    String[] testCommandLineString4;
    String[] testCommandLineString5;
    String[] testCommandLineString6;
    String[] testCommandLineString7;
    String[] testCommandLineString8;
    CommandProcessor testCommandProcessor;
    CommandProcessor testCommandProcessor2;
    CommandProcessor testCommandProcessor3;
    CommandProcessor testCommandProcessor4;
    CommandProcessor testCommandProcessor5;
    CommandProcessor testCommandProcessor6;
    CommandProcessor testCommandProcessor7;
    CommandProcessor testCommandProcessor8;
    TodoHandler testTodoHandler;
    TodoHandler testTodoHandler2;
    TodoHandler testTodoHandler3;
    TodoHandler testTodoHandler4;
    TodoHandler testTodoHandler5;
    TodoHandler testTodoHandler6;
    TodoHandler testTodoHandler7;
    TodoHandler testTodoHandler8;
    Display testDisplay;
    IToDoList testToDoList;


    @BeforeEach
    void setUp() throws InvalidArgumentException {
        testCommandLineString1 = new String[] {"--csv-file", "/bin/todos.csv",
            "--add-todo", "--todo-text", "Buy Milk", "--completed", "--due", "01/20/2022", "--priority", "3",
            "--category", "groceries"};
        testCommandLineString2 = new String[] {"--csv-file", "/bin/todos.csv",
            "--add-todo", "--todo-text", "DO HW", "--due", "01/10/2022", "--priority", "2",
            "--category", "groceries", "--category", "personal"};

        testCommandLineString3 = new String[] {"--complete-todo", "1", "--csv-file", "/bin/todos.csv"};
        testCommandLineString4 = new String[] {"--display", "--show-incomplete", "--csv-file", "/bin/todos.csv"};

        testCommandLineString5 = new String[] {"--csv-file", "/bin/todos.csv",
            "--add-todo", "--todo-text", "study", "--due", "01/20/2022", "--priority", "1",
            "--category", "groceries", "--category", "personal"};

        testCommandLineString6 = new String[] {"--display", "--show-category", "personal", "--csv-file", "/bin/todos.csv"};
        testCommandLineString7 = new String[] {"--display", "--sort-by-date","--csv-file","/bin/todos.csv" };
        testCommandLineString8 = new String[] {"--display","--sort-by-priority", "--csv-file","/bin/todos.csv"  };

        testToDoList = new ToDoList();
        testDisplay = new Display();

        testCommandProcessor = new CommandProcessor(testCommandLineString1);
        testCommandProcessor2 = new CommandProcessor(testCommandLineString2);
        testCommandProcessor3 = new CommandProcessor(testCommandLineString3);
        testCommandProcessor4 = new CommandProcessor(testCommandLineString4);
        testCommandProcessor5 = new CommandProcessor(testCommandLineString5);
        testCommandProcessor6 = new CommandProcessor(testCommandLineString6);
        testCommandProcessor7 = new CommandProcessor(testCommandLineString7);
        testCommandProcessor8 = new CommandProcessor(testCommandLineString8);

        testTodoHandler = new TodoHandler(testCommandProcessor, testToDoList, testDisplay);
        testTodoHandler2 = new TodoHandler(testCommandProcessor2, testToDoList, testDisplay);
        testTodoHandler3 = new TodoHandler(testCommandProcessor3, testToDoList, testDisplay);
        testTodoHandler4 = new TodoHandler(testCommandProcessor4, testToDoList, testDisplay);
        testTodoHandler5 = new TodoHandler(testCommandProcessor5, testToDoList, testDisplay);
        testTodoHandler6 = new TodoHandler(testCommandProcessor6, testToDoList, testDisplay);
        testTodoHandler7 = new TodoHandler(testCommandProcessor7, testToDoList, testDisplay);
        testTodoHandler8 = new TodoHandler(testCommandProcessor8, testToDoList, testDisplay);


    }

    @Test
    void todoActionTestAddWithText() {
        assertEquals("study",testToDoList.getToDoList().get(0).getText());
    }

    @Test
    void todoActionTestAddWithComplete() {
        assertTrue(testToDoList.getToDoList().get(2).getCompleted());
    }

    @Test
    void todoActionTestAddWithDueDate() {
        assertEquals("2022-01-20",testToDoList.getToDoList().get(0).getDue().toString());
    }

    @Test
    void todoActionTestAddWithPriority() {
        assertEquals(1,testToDoList.getToDoList().get(0).getPriority());
    }

    @Test
    void todoActionTestAddWithCategory() {
        assertEquals("personal",testToDoList.getToDoList().get(0).getCategory());
    }

    @Test
    void todoActionTestCompleteToDo() {
        assertTrue(testToDoList.getToDoList().get(2).getCompleted());
    }

    @Test
    void todoActionTestDisplayIncomplete() {
        String expectedString = "ToDoList{toDoList=[ToDo{id=2, text='DO HW', completed=false, due=2022-01-10, priority=2, category='personal'}]}";
        assertEquals(expectedString, testTodoHandler4.getDisplayList().toString());
    }

    @Test
    void todoActionTestDisplayCategory() {
        String expectedString2 = "ToDoList{toDoList=[ToDo{id=2, text='DO HW', completed=false, due=2022-01-10, " +
            "priority=2, category='personal'}, ToDo{id=3, text='study', completed=false, due=2022-01-20, " +
            "priority=1, category='personal'}]}";
        assertEquals(expectedString2, testTodoHandler6.getDisplayList().toString());
    }

    @Test
    void todoActionTestDisplaySortByDate() {
        String expectedString3 = "ToDoList{toDoList=[ToDo{id=3, text='study', completed=false, due=2022-01-20, " +
            "priority=1, category='personal'}, ToDo{id=2, text='DO HW', completed=false, due=2022-01-10, " +
            "priority=2, category='personal'}, ToDo{id=1, text='Buy Milk', completed=true, due=2022-01-20, " +
            "priority=3, category='groceries'}]}";
        assertEquals(expectedString3, testTodoHandler7.getDisplayList().toString());
    }

    @Test
    void todoActionTestDisplaySortByPriority() {
        String expectedString4 = "ToDoList{toDoList=[ToDo{id=3, text='study', completed=false, due=2022-01-20, " +
            "priority=1, category='personal'}, ToDo{id=2, text='DO HW', completed=false, due=2022-01-10, " +
            "priority=2, category='personal'}, ToDo{id=1, text='Buy Milk', completed=true, due=2022-01-20, " +
            "priority=3, category='groceries'}]}";
        assertEquals(expectedString4, testTodoHandler8.getDisplayList().toString());
    }

}