package problem1.controller.toDoController;

import problem1.controller.command.CommandProcessor;
import problem1.model.IToDoList;
import problem1.model.ToDo;
import java.time.LocalDate;
import java.util.Objects;


/**
 * A TodoHandler class
 * This handler needs to:
 * complete actions from commandline parser:
 * ADD todos
 * COMPLETE todos
 * DISPLAY todos
 */
public class TodoHandler implements ITodoHandler{
    private CommandProcessor commandProcessor;
    private IToDoList listOfTodos;
    private IDisplay display;
    private IToDoList newList;

    /**
     * Constructor for TodoHandler.
     * @param commandProcessor - command line processor.
     * @param listOfTodos - the list of todos
     */
    public TodoHandler(CommandProcessor commandProcessor, IToDoList listOfTodos, IDisplay display) {
        this.commandProcessor = commandProcessor;
        this.listOfTodos = listOfTodos;
        this.display = display;

        todoAction();
    }

    /**
     * A Getter for the list of todos that are updated in the Handler
     * @return the List of todos
     */
    public IToDoList getListOfTodos() {
        return listOfTodos;
    }

    /**
     * This function can add todos, complete todos and display todos
     */
    @Override
    public void todoAction() {
        if (this.checkCommand(CommandProcessor.ADD_TODO)){
            this.AddTodo();
        }
        if (this.checkCommand(CommandProcessor.COMPLETE_TODO)){
            this.CompleteTodo();
        }
        if (this.checkCommand(CommandProcessor.DISPLAY)){
            this.DisplayTodo();
        }
    }

    /**
     * This function checks if the command is in the original commandline input
     * @param command the command that is being looked for
     * @return boolean true or false
     */
    @Override
    public boolean checkCommand(String command){
        return (this.commandProcessor.getCommands().contains(command));
    }

    /**
     * This function gets the parameters of a command if it exists
     * @param key the command that is the key to the parameter
     * @return the parameter
     */
    @Override
    public String getCommandParameter(String key){
        return (this.commandProcessor.getParameters().get(key));
    }

    /**
     * This method is used to create a new To-do object and add it to the list
     */
    private void AddTodo() {
        String newTodoText = getCommandParameter(CommandProcessor.TODO_TEXT);
        ToDo.ToDoBuilder  newTodoBuilder = new ToDo.ToDoBuilder(newTodoText);

        if (checkCommand(CommandProcessor.COMPLETED)) {
            newTodoBuilder.addCompleted(Boolean.TRUE);
        }

        if (checkCommand(CommandProcessor.DUE)) {
            String stringDate = getCommandParameter(CommandProcessor.DUE);
            LocalDate newDate = getDueDate(stringDate);
            newTodoBuilder.addDue(newDate);
        }

        if (checkCommand(CommandProcessor.PRIORITY)) {
            String stringPriority = getCommandParameter(CommandProcessor.PRIORITY);
            Integer newPriority = Integer.parseInt(stringPriority);
            newTodoBuilder.addPriority(newPriority);
        }

        if (checkCommand(CommandProcessor.CATEGORY)) {
            String newCategory = getCommandParameter(CommandProcessor.CATEGORY);
            newTodoBuilder.addCategory(newCategory);
        }

        newTodoBuilder.build();
        ToDo newTodo = new ToDo(newTodoBuilder);
        this.listOfTodos.addToDo(newTodo);
        newTodo.setID(this.listOfTodos.generateId(newTodo));
    }

    /**
     * This function is used to complete a to-do that has already been created
     */
    private void CompleteTodo() {
        String stringTodo = getCommandParameter(CommandProcessor.COMPLETE_TODO);
        Integer integerTodoID = Integer.parseInt(stringTodo);

        for (ToDo todo : this.listOfTodos.getToDoList()) {
            if (todo.getId() == integerTodoID) {
                todo.setCompleted(Boolean.TRUE);
            }
        }
    }

    /**
     * This function is used to display a to-do given the specifications by the user
     */
    private void DisplayTodo(){
        IToDoList displayList = this.listOfTodos;
        if (this.checkCommand(CommandProcessor.SHOW_INCOMPLETE)){
            displayList = this.display.filterByIncomplete(displayList);
        }
        if (this.checkCommand(CommandProcessor.SHOW_CATEGORY)){
            String categoryToShow = getCommandParameter(CommandProcessor.SHOW_CATEGORY);
            displayList = this.display.filterByCategory(displayList, categoryToShow);
        }
        if (this.checkCommand(CommandProcessor.SORT_BY_DATE)){
            displayList = this.display.sortByDate(displayList);
        }
        if (this.checkCommand(CommandProcessor.SORT_BY_PRIORITY)){
            displayList = this.display.sortByPriority(displayList);
        }
        for(ToDo item: displayList.getToDoList()){
            System.out.println(item.toString());
        }

        setDisplayList(displayList);
    }

    /**
     * Set the current display list that has been modified
     * @param newList the modified list form the DisplayToDo function
     */
    public void setDisplayList(IToDoList newList){
        this.newList = newList;
    }

    /**
     * Get the current display list that has been modified
     * @return the edited list
     */
    public IToDoList getDisplayList(){
        return this.newList;
    }

    /**
     * Convert the string date into a local date object and return it
     * @param s the original date string
     * @return the new local date object
     */
    private LocalDate getDueDate(String s){
        Integer dateIntArray[] = this.commandProcessor.convertMonthStringToInteger(s);
        try{
            return LocalDate.of(dateIntArray[2], dateIntArray[0], dateIntArray[1]);
        }
        catch (NumberFormatException due){
            return null;
        }
    }

    /**
     * equals method checks if two objects are the same
     * @param o as another object
     * @return if objects are the same, as a Boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoHandler)) return false;
        TodoHandler that = (TodoHandler) o;
        return Objects.equals(commandProcessor, that.commandProcessor) && Objects.equals(listOfTodos, that.listOfTodos)
            && Objects.equals(display, that.display) && Objects.equals(newList, that.newList);
    }

    /**
     * hashCode method returns the hash code value of the ToDoHandler class
     * @return the hash code value of the ToDoHandler class, as Integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(commandProcessor, listOfTodos, display, newList);
    }

    /**
     * toString method for the ToDoHandler class
     * @return a String representation of the ToDoHandler
     */
    @Override
    public String toString() {
        return "TodoHandler{" +
            "listOfTodos=" + listOfTodos +
            '}';
    }
}