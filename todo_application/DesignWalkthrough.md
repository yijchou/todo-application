# Design 

The purpose of this program is to run a command-line TODO application. 
The user should be able to add TODOs and edit their TODOs within the command-line. 
Our program should be able to track the additions and updates of the TODOs and store them all in a CSV file. 

## MVC Architecture

### Model:
(TodDoList, ToDo) <br>
We use the builder pattern in the ToDo class. Builder pattern is used to construct a complex object (ToDo) step by step and 
the final step will return the object (ToDo). We choose to use it because the todo data structure has some fields that are optional 
and default.


### View: 
(Main) <br>
In our **_Main_** class, we are only passing primitive data types / data collections to the other class methods. It will first process 
the user's arguments from the command line, and then pass the csv file name into the csv Reader class. The reader will process all the
data entries from a given CSV file, and store them in a IToDoList ADT. We then call to the TodoHandler to complete the tasks given to
us from the user, and with that update the CSV by using the CSVUpdate class.


### Controller:
(Command/, CSV/, ToDoController/) <br>
(CommandProcessor, ReadCSV, UpdateCSV, TodoHandler, Converter, Display) <br>

The **_CommandProcessor_** takes the initial input from the command-line.
It makes sure that each input meets the requirements for the task the user is trying to achieve. This is done in the 
ProcessArgs function that originates from the **_ICommandProcessor_** Interface. The interface also holds the functions 
getCommands and getParameters that are implemented within the CommandProcessor with the help of numerous private Helper Methods.
Once the command-line arguments are verified, a list of commands is populated with the commands given from the input. 
In addition to this, if the command takes an argument, these will be placed in a Map and tied to their command key. If 
an input does bot follow the guidelines the **_InvalidArgumentException_** is thrown. 

The **_ReadCSV_** takes in the name of the CSV file as a String data type. Then, the Main class will call on the read() method,
which originates from the **_IReadCSV_** interface, and will begin the reading process. Firstly, it will read the first line of 
the CSV file, and make sure that it is not empty. If the file is not empty, then the first line will be used to create a 
Map<Integer, String> of key items. This will be stored as a paramter in the CSV class. Every other data entry in the CSV file 
will then be used to create an IToDoList of ToDo items. The read method will call on the Converter class to generate each of 
the ToDo items, converting the String data types into the corresponding parameters' data type in the ToDo class. Each ToDo 
object will be added to an IToDoList, which will be the final data collection that will be returned by the read() method. 

The **_Converter_** class takes in a String[] of attribute values, converts them into a valid data type, and then uses them
to generate an indvidual ToDo item. The converter uses the convert() method, which originates from the **_IConverter_** 
interface. For each of the String data types in the input String Array, the convert() method will check if it is a valid
data format, and then convert it into its corresponding data type. The convert method will then pass these data entries into
the ToDo Builder in the Model Package. The method will finally return the data entry as a ToDo object.

Once the input string has been put through the Command Processor, it is the job of the **_TodoHandler_** to handle what 
the user is asking the program to do based off of the input on the command line. With the method todoAction() from the 
interface **_ITodoHandler_**, the class will either add, complete, or display a todo. The other functions from the interface
checkCommands() and getCommandParameter() give the class the tool to check the previously mentioned command HashSet 
and parameters Map in order to know what to do. Once the class knows what to do it will call on the various helper methods
to get the task done. The Todo Handler uses the ToDo class and Builder to create a ToDo object, and the Handler also uses 
the Display class for implementing various Display options on a ToDoList. 

Once all of the user specified actions have been completed, the newly updated IToDoList will be passed into the **_UpdateCSV_**
class. This class takes in two input parameters: (1) a CSV file name as a String object, and (2) a IToDoList of updated ToDo items.
The **_UpdateCSV_** class implements the update() method from the **_IUpdateCSV_** interface. The update method takes in a 
Map<Integer, String> of key items. This is the keys that was originally generated from the CSV reader (**_ReadCSV_**). The update()
method will delete the current CSV file, and rewrite all the unchanged data entries as well as the updated to do items. The keys will 
be used to write the first line of the CSV file. The rest of the lines are generated by the data entries in the IToDoList. 
These entries will be reformatted to fit the CSV standardized formatting. 
