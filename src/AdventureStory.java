//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           TestAdventureStory
// Files:           AdventureStory.java TestAdventureStory.java
// Course:          CS 200 Spring 2019
//
// Author:          Luke Houge
// Email:           lhouge@cs.wisc.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.*;
import java.io.*;
import java.lang.*;

public class AdventureStory {

    /**
     * Prompts the user for a value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int.
     * If the value read is between min and max (inclusive), that value is returned.
     * Otherwise, "Invalid value." terminated by a new line is output and the user is prompted
     * again.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        int inputInt = 0;
        boolean status = true;
        // keeps prompting for an int until valid is entered
        while (status) {
            // print out supplied prompt
            System.out.print(prompt);
            // checking if there is an int, and setting inputInt to that if so
            if (sc.hasNextInt()) {
                inputInt = sc.nextInt();
                sc.nextLine();
                // checking range with min and max
                if (inputInt >= min && inputInt <= max) {
                    status = false;
                    // if not valid, return -1
                }
                else {
                    System.out.println("Invalid value.");
                }
            }
            else {
                System.out.println("Invalid value.");
                if (sc.hasNextLine()) {
                    String temp = sc.nextLine();
                    if (temp.equals("")) {
                        continue;
                    }
                }
            }
        }
        return inputInt;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        // printing out the prompt passed in
        System.out.print(prompt);
        // setting input to the entered line
        String input = sc.nextLine();
        // loops through the input and finds the first character that is not whitespace, and
        // returns it in lowercase
        for (int i=0; i<input.length(); i++){
            if (Character.isLetterOrDigit(input.charAt(i))) {
                return Character.toLowerCase(input.charAt(i));
            }
        }
        // if not found, return the null character
        return '\u0000';
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input, removing any leading and
     * trailing whitespace.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user with leading and trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        // print prompt
        System.out.print(prompt);
        // get input
        String input = sc.nextLine();
        // return input trimmed
        return input.trim();
    }

    /**
     * Saves the current position in the story to a file.
     *
     * The format of the bookmark file is as follows:
     * Line 1: The value of Config.MAGIC_BOOKMARK
     * Line 2: The filename of the story file from storyFile
     * Line 3: The current room id from curRoom
     *
     * Note: use PrintWriter to print to the file.
     *
     * @param storyFile The filename containing the cyoa story.
     * @param curRoom The id of the current room.
     * @param bookmarkFile The filename of the bookmark file.
     * @return false on an IOException, and true otherwise.
     */
    public static boolean saveBookmark(String storyFile, String curRoom, String bookmarkFile) {
        // making a new file instance
        File file = new File(bookmarkFile);
        // making new printwriter instance
        PrintWriter writer = null;
        try {
            // setting the writer with the file
            writer = new PrintWriter(file);
            // setting the first line to the Config.Magic_Bookmark value
            writer.println(Config.MAGIC_BOOKMARK);
            // setting second line to the story file name
            writer.println(storyFile);
            // setting third line to the current room id
            writer.println(curRoom);
        }
        // catching a file not found exception
        catch (FileNotFoundException e) {
            return false;
        }
        finally {
            if ( writer != null ) {
                // if the writer did something, close it and write to the file
                writer.close();
            }
        }
        return true;
    }

    /**
     * Loads the story and current location from a file either a story file or a bookmark file.
     * NOTE: This method is partially implementd in Milestone 2 and then finished in Milestone 3.
     *
     * The type of the file will be determined by reading the first line of the file. The first
     * line of the file should be trimmed of whitespace.
     *
     * If the first line is Config.MAGIC_STORY, then the file is parsed using the parseStory method.
     * If the first line is Config.MAGIC_BOOKMARK, the the file is parsed using the parseBookmark
     * method.
     * Otherwise, print an error message, terminated by a new line, to System.out, displaying:
     * "First line: trimmedLineRead does not correspond to known value.", where trimmedLineRead is
     * the trimmed value of the first line from the file.
     *
     * If there is an IOException, print an error message, terminated by a new line, to System.out,
     * saying "Error reading file: fName", where fName is the value of the parameter.
     *
     * If there is an error reading the first line, print an error message, terminated by a new
     * line, to System.out, displaying: "Unable to read first line from file: fName", where fName is
     * the value of the parameter.
     *
     * This method will be partially implemented in Milestone #2 and completed in Milestone #3 as
     * described below.
     *
     * Milestone #2: Open the file, handling the IOExceptions as described above. Do not read the
     * the first line: Assume the file is a story file and call the parseStory method.
     *
     * Milestone #3: Complete the implementation of this method by reading the first line from the
     * file and following the rules of the method as described above.
     *
     * @param fName The name of the file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel
     *              ArrayList to rooms. Since the rooms can have multiple transitions, each room
     *              will be an ArrayList<String[]> with one String[] per transition with the
     *              overall structure being an ArrayList of ArrayLists of String[].
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is an IOException or a parsing error. Otherwise, true.
     */
    public static boolean parseFile(String fName, ArrayList<String[]> rooms,
                                    ArrayList<ArrayList<String[]> > trans,
                                    String[] curRoom) {
        Scanner sc;
        String line = null;
        try {
            // creating new file instance
            File file = new File(fName);
            // create a new scanner instance using that file
            sc = new Scanner(file);
            // read until the end using delimiter
            sc.useDelimiter("\\Z");
            // set the first line and trim
            line = sc.nextLine().trim();
        }
        // catching IO expection
        catch(IOException ex){
            System.out.println("Error reading file: " + fName);
            return false;
        }
        // catching any runtime exceptions
        catch(RuntimeException ex){
            System.out.println("Unable to read first line from file: " + fName);
            return false;
        }
        // call parseStory if the first line matches Config.MAGIC_STORY
        if (line.equals(Config.MAGIC_STORY)) {
            parseStory(sc, rooms, trans, curRoom);
        }
        // call parseBookmark if the first line matches Config.MAGIC_BOOKMARK
        else if (line.equals(Config.MAGIC_BOOKMARK)) {
            parseBookmark(sc, rooms, trans, curRoom);
        }
        // print error and return false if it matches neither
        else {
            System.out.println("First line: " + line + " does not correspond to known value.");
            return false;
        }
        return true;
    }

    /**
     * Loads the story and the current room from a bookmark file. This method assumes that the first
     * line of the file, containing Config.MAGIC_BOOKMARK, has already been read from the Scanner.
     *
     * The format of a bookmark file is as follows:
     * Line No: Contents
     *       1: Config.MAGIC_BOOKMARK
     *       2: Story filename
     *       3: Current room id
     *
     * As an example, the following contents would load the story Goldilocks.story and set the
     * current room to id 7.
     *
     * #!BOOKMARK
     * Goldilocks.story
     * 7
     *
     * Your method should not duplicate the code from the parseFile method. It must use the
     * parseFile method to populate the rooms and trans methods based on the contents of the story
     * filename read and trimmed from line 2 of the file. The value of for the cell at index 0 of
     * curRoom is the trimmed value read on line 3 of the file.
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details. A parallel ArrayList
     *              trans.
     * @param trans The ArrayList structure that will contain the transition details. A parallel
     *              ArrayList to rooms.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true.
     */
    public static boolean parseBookmark(Scanner sc, ArrayList<String[]> rooms,
                                        ArrayList<ArrayList<String[]> > trans,
                                        String[] curRoom) {
        String fName = null;
        Boolean flag = false;
        String room = null;
        try {
            // loading filename
            fName = sc.nextLine().trim();
            // loading room id
            room = sc.nextLine().trim();
            // calling parsefile with this info
            flag = parseFile(fName, rooms, trans, curRoom);
            curRoom[0] = room;
            // if parsefile returns error, return false
            if (!flag) {
                return false;
            }
        }
        // catching any runtime error and printing error message
        catch(RuntimeException ex){
            System.out.println("Unable to read first line from file: " + fName);
            return false;
        }
        return true;
    }

    /**
     * This method parses a story adventure file.
     *
     * The method will read the contents from the Scanner, line by line, and populate the parallel
     * ArrayLists rooms and trans. As such the story files have a specific structure. The order of
     * the rooms in the story file correspond to the order in which they will be stored in the
     * parallel ArrayLists.
     *
     * When reading the file line-by-line, whitespace at the beginning and end of the line should be
     * trimmed. The file format described below assumes that whitespace has been trimmed.
     *
     * Story file format:
     *
     * - Any line (outside of a room's description) that begins with a '#' is considered a comment
     *   and should be ignored.
     * - Room details begin with a line starting with 'R' followed by the room id, terminated with
     *   a ':'. Everything  after the first colon is the room title. The substrings of the room id
     *   and the room title should be trimmed.
     * - The room description begins on the line immediate following the line prefixed with 'R',
     *   containing the room id, and continues until a line of ";;;" is read.
     *   - The room description may be multi-line. Every line after the first one, should be
     *     prefixed with a newline character ('\n'), and concatenated to the previous description
     *     lines read for the current room.
     * - The room transitions begin immediately after the line of ";;;", and continue until a line
     *   beginning with 'R' is encountered. There are 3 types of transition lines:
     *   - 1 -- Terminal Transition: A terminal transition is either Config.SUCCESS or
     *                               Config.FAIL. This room is the end of the story.
     *                               This value should be stored as a transition with the String at
     *                               index Config.TRAN_DESC set to the value read. The rest of the
     *                               Strings in the transition String array should be null.
     *                               A room with a terminal transition can only have one transition
     *                               associated with it. Any additional transitions should result in
     *                               a parse error.
     *   - 2 -- Normal Transition: The line begins with ':' followed by the transition description,
     *                             followed by " -> " (note the spaces), followed by the room id to
     *                             transition to. For normal transitions (those without a transition
     *                             weight), set the value at index Config.TRAN_PROB to null.
     *   - 3 -- Weighted Transition: Similar to a normal transition except that there is a
     *                               probability weight associated with the transition. After the
     *                               room id (as described in the normal transition) is a '?'
     *                               followed by the probability weight.
     *   - You can assume that room ids do not contain a '?'.
     *   - You can assume that Config.SUCCESS and Config.FAIL do not start with a ':'.
     *
     * In the parallel ArrayLists rooms and trans, the internal structures are as follows:
     *
     * The String array structure for each room has a length of Config.ROOM_DET_LEN. The entries in
     * the array are as follows:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * The String array structure for each transition. Note that each room can have multiple
     * transitions, hence, the ArrayList of ArrayLists of String[]. The length of the String[] is
     * Config.TRAN_DET_LEN. The entries in the String[] are as follows:
     * Index               | Description
     * ------------------------------------------------------------------
     * Config.TRAN_DESC    | The transition description
     * Config.TRAN_ROOM_ID | The transition destination (id of the room)
     * Config.TRAN_PROB    | The probability weight for the transition
     *
     * If you encounter a line that violates the story file format, the method should print out an
     * error message, terminated by a new line, to System.out displaying:
     * "Error parsing file on line: lineNo: lineRead", where lineNo is the number of lines read
     * by the parseStory method (i.e. ignoring the magic number if Milestone #3), and lineRead is
     * the offending trimmed line read from the Scanner.
     *
     * After parsing the file, if rooms or trans have zero size, or they have different sizes, print
     * out an error message, terminated by a new line, to System.out displaying:
     * "Error parsing file: rooms or transitions not properly parsed."
     *
     * After parsing the file, if curRoom is not null, store the reference of the id of the room at
     * index 0 of the rooms ArrayList into the cell at index 0 of curRoom.
     *
     * Hint: This method only needs a single loop, reading the file line-by-line.
     *
     * Hint: To successfully parse the file, you will need to maintain a state of where you are in
     *       the file. I.e., are you parsing the description, parsing the transitions; is there an
     error; etc? One suggestion would be to use an enum to enumerate the different states.
     *
     * @param sc The Scanner object buffering the input file to read.
     * @param rooms The ArrayList structure that will contain the room details.
     * @param trans The ArrayList structure that will contain the transition details.
     * @param curRoom An array of at least length 1. The current room id will be stored in the cell
     *                at index 0.
     * @return false if there is a parsing error. Otherwise, true.
     */
    public static boolean parseStory(Scanner sc, ArrayList<String[]> rooms,
                                     ArrayList<ArrayList<String[]> > trans,
                                     String[] curRoom) {
        int count = 0;
        int tranCount = 0;
        rooms.clear();
        trans.clear();
        String line = sc.nextLine().trim();
        String tempLine = null;
        do {
            // checking if the templine is null, and if not set it to line
            if (tempLine != null) {
                line = tempLine;
            }
            // if the line is blank, or starts with # set it to the next line and trim (ignore)
            if (line.equals("") || line.charAt(0) == '#') {
                line = sc.nextLine().trim();
                continue;
            }
            // code for parsing room
            else if ((line.charAt(0) == 'R') && (Character.isDigit(line.charAt(1)))) {
                String[] temp = new String[Config.ROOM_DET_LEN];
                // sets the array position for the room ID to the substring between 'R' and ':'
                temp[Config.ROOM_ID] = line.substring(line.indexOf("R") + 1, line.indexOf(":")).trim();
                // Gets the rest of the string after first occurrence of a ':' char
                temp[Config.ROOM_TITLE] = line.substring(line.lastIndexOf(':') + 1).trim();
                // sets the room details section, and continues doing so until there is no more
                // lines, or until it is broken out of
                while (sc.hasNextLine()) {
                    line = sc.nextLine().trim();
                    // check if the line is terminal
                    if (line.equals(Config.FAIL) || line.equals(Config.SUCCESS)) {
                        // if so then using a temporary array for the transition, add the line
                        // and then append it to the arraylist at the current transition
                        String[] tempTran = new String[Config.TRAN_DET_LEN];
                        tempTran[Config.TRAN_DESC] = line;
                        ArrayList<String[]>  tempArrList = new ArrayList<String[]>();
                        trans.get(count).add(tempTran);
                        if (sc.hasNextLine()) {
                            line = sc.nextLine().trim();
                            trans.add(tempArrList);
                        }
                        // increment the counts
                        tranCount ++;
                        count ++;
                    }
                    // if the next line is still part of the description, add the line, replace
                    // all occurences of null, and add a new line at the end
                    else if (!(line.equals(";;;"))) {
                        temp[Config.ROOM_DESC] += line;
                        temp[Config.ROOM_DESC] = temp[Config.ROOM_DESC].replaceAll("null", "");
                        temp[Config.ROOM_DESC] += "\n";
                    }
                    // if the room description array entry is not empty, trim it and exit the loop
                    else if (temp[Config.ROOM_DESC] != null){
                        temp[Config.ROOM_DESC] = temp[Config.ROOM_DESC].trim();
                        break;
                    }
                    // if the next line signals the end, and none of the other if's were entered
                    // (meaning there was no description) make a blank entry for it
                    else if (line.equals(";;;")) {
                        temp[Config.ROOM_DESC] = "";
                    }
                }
                // append the temp arraylist to the rooms array list
                rooms.add(temp);
            }
            // code for parsing transitions
            else if (line.charAt(0) == ':') {
                do {
                    // using a temporary array for the transition, add all the elements and append
                    String[] tempTran = new String[Config.TRAN_DET_LEN];
                    // get the transition description between the ':' and '-->'
                    tempTran[Config.TRAN_DESC] = line.substring(line.indexOf(":") + 1,
                            line.indexOf("->")).trim();
                    // if there is a probability then get the id number, and the probability number
                    if (line.contains("?")) {
                        // get the transition number after '>' but before '?'
                        tempTran[Config.TRAN_ROOM_ID] = line.substring(line.indexOf(">") + 1,
                                line.lastIndexOf("?")).trim();
                        // get the probability after '?'
                        tempTran[Config.TRAN_PROB] =
                                line.substring(line.lastIndexOf("?") + 1).trim();
                    }
                    else {
                        // set the room ID to everything after '>' (given there is no probabilities)
                        tempTran[Config.TRAN_ROOM_ID] =
                                line.substring(line.indexOf(">") + 1).trim();
                    }
                    // add the temp array list to the main array list
                    ArrayList<String[]>  tempArrList = new ArrayList<String[]>();
                    trans.add(tempArrList);
                    trans.get(count).add(tempTran);
                    if (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                    }
                    // if no next line, break
                    else {
                        break;
                    }
                    // if the line is empty, break
                    if (line.equals("")) {
                        break;
                    }
                // keep looping while there is transition data
                } while (line.charAt(0) == ':');
                count ++;
            }
            // code for parsing terminals
            else if (line.equals(Config.FAIL) || line.equals(Config.SUCCESS)) {
                // using a temporary array, add the terminal
                String[] tempTran = new String[Config.TRAN_DET_LEN];
                tempTran[Config.TRAN_DESC] = line;
                ArrayList<String[]>  tempArrList = new ArrayList<String[]>();
                trans.get(count).add(tempTran);
                if (sc.hasNextLine()) {
                    line = sc.nextLine().trim();
                    trans.add(tempArrList);
                }
                tranCount ++;
                count ++;
            }
            // else skip that line and start loop again
            else {
                line = sc.nextLine().trim();
                continue;
            }
            // if the scanner does not have another line then set templine to line and clear line
            // in order to break out of the loop
            if (!(sc.hasNextLine())) {
                tempLine = line;
                line = null;
            }
        // loop while the line is not null
        } while (!(line == null));
        // set the curRoom array to the room ID in the rooms array list
        if (curRoom != null) {
            curRoom[0] = rooms.get(0)[Config.ROOM_ID];
        }
        // if the sizes of either are zero, or different size, print error message
        if ((trans.size() == 0) || (rooms.size() == 0)) {
            if (!(trans.size() == rooms.size())) {
                System.out.println("Error parsing file: rooms or transitions not properly parsed.");
                return false;
            }
        }
        // remove the arraylist if empty
        if (trans.get(trans.size()-1).size() == 0) {
            trans.remove(trans.size()-1);
        }
        return true;
    }

    /**
     * Returns the index of the given room id in an ArrayList of rooms.
     *
     * Each entry in the ArrayList contain a String array, containing the details of a room. The
     * String array structure, which has a length of Config.ROOM_DET_LEN, and has the following
     * entries:
     * Index              | Description
     * --------------------------------------------
     * Config.ROOM_ID     | The room id
     * Config.ROOM_TITLE  | The room's title
     * Config.ROOM_DESC   | The room's description
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The index of the room with the given id if found in rooms. Otherwise, -1.
     */
    public static int getRoomIndex(String id, ArrayList<String[]> rooms) {
        // loop through rooms
        for(String[] row : rooms) {
            // if the element for ID in the row matches the ID searching for, return its position
            if (id.equals(row[Config.ROOM_ID])) {
                int location = rooms.indexOf(row);
                return location;
            }
        }
        // if not found return -1
        return -1;
    }

    /**
     * Returns the room String array of the given room id in an ArrayList of rooms.
     *
     * Remember to avoid code duplication!
     *
     * @param id The room id to search for.
     * @param rooms The ArrayList of rooms.
     * @return The reference to the String array in rooms with the room id of id. Otherwise, null.
     */
    public static String[] getRoomDetails(String id, ArrayList<String[]> rooms) {
        // find the row that matches the ID and return it's details section
        for(String[] row : rooms) {
            if (id.equals(row[Config.ROOM_ID])) {
                return row;
            }
        }
        return null;
    }

    /**
     * Prints out a line of characters to System.out. The line should be terminated by a new line.
     *
     * @param len The number of times to print out c.
     * @param c The character to print out.
     */
    public static void printLine(int len, char c) {
        // print out the given character up to the len ammount of times, on the same line
        for (int i = 0; i < len; i++) {
            System.out.print(c);
        }
    }

    /**
     * Prints out a String to System.out, formatting it into lines of length no more than len
     * characters.
     *
     * This method will need to print the string out character-by-character, counting the number of
     * characters printed per line.
     * If the character to output is a newline, print it out and reset your counter.
     * If it reaches the maximum number of characters per line, len, and the next character is:
     *   - whitespace (as defined by the Character.isWhitespace method): print a new line
     *     character, and move onto the next character.
     *   - NOT a letter or digit (as defined by the Character.isLetterOrDigit method): print out the
     *     character, a new line, and move onto the next character.
     *   - Otherwise:
     *       - If the previous character is whitespace, print a new line then the character.
     *       - Otherwise, print a '-', a new line, and then the character.
     * Remember to reset the counter when starting a new line.
     *
     * After printing out the characters in the string, a new line is output.
     *
     * @param len The maximum number of characters to print out.
     * @param val The string to print out.
     */
    public static void printString(int len, String val) {
        System.out.println();
        int count = 0;
        // if the passed in string is null, return to main
        if (val == null) {
            return;
        }
        // loop through the string
        for (int i = 0; i < val.length(); i++){
            // if the value is a newline, print it
            if (val.charAt(i) == '\n') {
                System.out.print(val.charAt(i));
                count = 0;
                continue;
            }
            // if reached the max number of characters per line
            if (count == len-1) {
                // if its whitespace, print a new line
                if (Character.isWhitespace(val.charAt(i))) {
                    System.out.print("\n");
                    count = 0;
                }
                // if it is not a letter or digit, print it on a newline
                else if (!Character.isLetterOrDigit(val.charAt(i))) {
                    System.out.println(val.charAt(i));
                    count = 0;
                }
                else {
                    // else if the character before is whitespace print on a new line
                    if (Character.isWhitespace(val.charAt(i-1))) {
                        System.out.print("\n" + val.charAt(i));
                        count = 1;
                    }
                    // else if the character before was not whitespace, add a hyphen and print on
                    // a newline, adding to count
                    else {
                        System.out.println('-');
                        System.out.print(val.charAt(i));
                        count = 1;
                    }
                }
            }
            // if not at end of the line length, print the character
            else {
                System.out.print(val.charAt(i));
                count ++;
            }
        }
        System.out.println();
    }

    /**
     * This method prints out the room title and description to System.out. Specifically, it first
     * loads the room details, using the getRoomDetails method. If no room is found, the method
     * should return, avoiding any runtime errors.
     *
     * If the room is found, first a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is
     * output. Followed by the room's title, a new line, and the room's description. Both the title
     * and the description should be printed using the printString method with a maximum length of
     * Config.DISPLAY_WIDTH. Finally, a line of Config.LINE_CHAR of length Config.DISPLAY_WIDTH is
     * output.
     *
     * @param id Room ID to display
     * @param rooms ArrayList containing the room details.
     */
    public static void displayRoom(String id, ArrayList<String[]> rooms) {
        // call all the needed methods to print the room, given it is not null
        String[] roomDetails = getRoomDetails(id, rooms);
        if (roomDetails == null) {
            return;
        }
        printLine(Config.DISPLAY_WIDTH, Config.LINE_CHAR);
        printString(Config.DISPLAY_WIDTH, roomDetails[Config.ROOM_TITLE]);
        printString(Config.DISPLAY_WIDTH, roomDetails[Config.ROOM_DESC]);
        printLine(Config.DISPLAY_WIDTH, Config.LINE_CHAR);
        System.out.println();
    }

    /**
     * Prints out and returns the transitions for a given room.
     *
     * If the room ID of id cannot be found, nothing should be output to System.out and null should
     * be returned.
     *
     * If the room is a terminal room, i.e., the transition list is consists of only a single
     * transition with the value at index Config.TRAN_DESC being either Config.SUCCESS or
     * Config.FAIL, nothing should be printed out.
     *
     * The transitions should be output in the same order in which they are in the ArrayList, and
     * only if the transition probability (String at index TRAN_PROB) is null. Each transition
     * should be output on its own line with the following format:
     * idx) transDesc
     * where idx is the index in the transition ArrayList and transDesc is the String at index
     * Config.TRAN_DESC in the transition String array.
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param id The room id of the transitions to output and return.
     * @param rooms The ArrayList structure that contains the room details.
     * @param trans The ArrayList structure that contains the transition details.
     * @return null if the id cannot be found in rooms. Otherwise, the reference to the ArrayList of
     *         transitions for the given room.
     */
    public static ArrayList<String[]> displayTransitions(String id, ArrayList<String[]> rooms,
                                                         ArrayList<ArrayList<String[]> > trans) {
        // making sure nothing needed is null
        if (rooms == null || trans == null || id == null) {
            return null;
        }
        // calling get index of room
        int index = getRoomIndex(id, rooms);
        // if not found return null
        if (index == -1) {
            return null;
        }
        // if the size is only one, and the value is a terminal, return that
        else if ((trans.get(index).size() == 1)
                && ((trans.get(index).get(0)[Config.TRAN_DESC].equals(Config.FAIL))
                || (trans.get(index).get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)))) {
            return trans.get(index);
        }
        // else print out the room transitions if the probability is null
        else {
            for (int i = 0; i<trans.get(index).size(); i++) {
                if (trans.get(index).get(i)[Config.TRAN_PROB] == null) {
                    System.out.println((i)+") " + trans.get(index).get(i)[Config.TRAN_DESC]);
                }
            }
        }
        return trans.get(index);
    }

    /**
     * Returns the next room id, selected randomly based on the transition probability weights.
     *
     * If curTrans is null or the total sum of all the probability weights is 0, then return null.
     * Use Integer.parseInt to convert the Strings at index Config.TRAN_PROB of the transition
     * String array to integers. If there is a NumberFormatException, return null.
     *
     * It is important to follow the specifications of the random process exactly. Any deviation may
     * result in failed tests. The random transition work as follows:
     *   - Let totalWeight be the sum of the all the transition probability weights in curTrans.
     *   - Draw a random integer between 0 and totalWeight - 1 (inclusive) from rand.
     *   - From the beginning of the ArrayList curTrans, start summing up the transition probability
     *     weights.
     *   - Return the String at index Config.TRAN_ROOM_ID of the first transition that causes the
     *     running sum of probability weights to exceed the random integer.
     *
     * See parseStory method for the details of the transition String array.
     *
     * @param rand The Random class from which to draw random values.
     * @param curTrans The ArrayList structure that contains the transition details.
     * @return The room id that was randomly selected if the sum of probabilities is greater than 0.
     *         Otherwise, return null. Also, return null if there is a NumberFormatException.
     */
    public static String probTrans(Random rand, ArrayList<String[]> curTrans) {
        int total = 0;
        int random = 0;
        // check if null
        if (curTrans == null) {
            return null;
        }
        else {
            try {
                // add up all the values
                for (int i=0; i<curTrans.size(); i++) {
                    total += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
                }
                // if the total is 0, return null
                if (total == 0 ) {
                    return null;
                }
                // else set to a random value and return the first value that makes the new total
                // larger than the probability
                else {
                    random = rand.nextInt(total);
                    total = 0;
                    for (int i=0; i<curTrans.size(); i++) {
                        total += Integer.parseInt(curTrans.get(i)[Config.TRAN_PROB]);
                        if (total > random) {
                            return curTrans.get(i)[Config.TRAN_ROOM_ID];
                        }
                    }
                }
            }
            // catch number format error
            catch(NumberFormatException ex){
                return null;
            }
        }
        return null;
    }

    /**
     * This is the main method for the Story Adventure game. It consists of the main game loop and
     * play again loop with calls to the various supporting methods. This method will evolve over
     * the 3 milestones.
     *
     * The Scanner object to read from System.in and the Random object with a seed of c
     * will be created in the main method and used as arguments for the supporting methods as
     * required.
     *
     * Milestone #1:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #2:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - If the file is successfully parsed using the parseFile method:
     *            - Begin the game loop with the current room ID being that in the 0 index of the
     *              String array passed into the parseFile method as the 4th parameter
     *                 - Output the room details via the displayRoom method
     *                 - Output the transitions via the displayTransitions method
     *                 - If the current transition is not terminal:
     *                   - Prompt the user for a number between -1 and the number of transitions
     *                     minus 1, using the promptInt method with a prompt of "Choose: "
     *                   - If the returned value is -1:
     *                      - read a char using promptChar with a prompt of
     *                        "Are you sure you want to quit the adventure? "
     *                      - Set the current room ID to Config.FAIL if that character returned is 'y'
     *                   - Otherwise: Set the current room ID to the room ID at index
     *                                Config.TRAN_ROOM_ID of the selected transition.
     *                 - Otherwise, the current transition is terminal: Set the current room ID to
     *                   the terminal state in the transition String array.
     *            - Continue the game loop until the current room ID is Config.SUCCESS or
     *              Config.FAIL
     *            - If the current room ID is Config.FAIL, print out the message (terminated by a
     *              line): "You failed to complete the adventure. Better luck next time!"
     *            - Otherwise: print out the message (terminated by a line):
     *              "Congratulations! You successfully completed the adventure!"
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     *
     * Milestone #3:
     *   - Print out the welcome message: "Welcome to this choose your own adventure system!"
     *   - Begin the play again loop:
     *       - Prompt for a filename using the promptString method with the prompt:
     *         "Please enter the story filename: "
     *       - If the file is successfully parsed using the parseFile method:
     *            - Begin the game loop with the current room ID being that in the 0 index of the
     *              String array passed into the parseFile method as the 4th parameter
     *                 - Output the room details via the displayRoom method
     *                 - Output the transitions via the displayTransitions method
     *                 - If the current transition is not terminal:
     *                   - If the value returnd by the probTrans method is null:
     *                     - Prompt the user for a number between -2 and the number of transitions
     *                       minus 1, using the promptInt method with a prompt of "Choose: "
     *                     - If the returned value is -1:
     *                        - read a char using promptChar with a prompt of
     *                          "Are you sure you want to quit the adventure? "
     *                        - Set the current room ID to Config.FAIL if that character returned is
     *                          'y'
     *                     - If the returned value is -2:
     *                        - read a String using the promptString method with a prompt of:
     *                          "Bookmarking current location: curRoom. Enter bookmark filename: ",
     *                          where curRoom is the current room ID.
     *                        - Call the saveBookmark method and output (terminated by a new line):
     *                           - if successful: "Bookmark saved in fSave"
     *                           - if unsuccessful: "Error saving bookmark in fSave"
     *                       where fSave is the String returned by promptString.
     *                     - Otherwise: Set the current room ID to the room id at index
     *                                  Config.TRAN_ROOM_ID of the selected transition.
     *                   - Otherwise, the value returned by probTrans is not null: make this value
     *                     the current room ID.
     *            - Continue the game loop until the current room ID is Config.SUCCESS or
     *              Config.FAIL.
     *            - If the current room ID is Config.FAIL, print out the message (terminated by a
     *              line): "You failed to complete the adventure. Better luck next time!"
     *            - Otherwise: print out the message (terminated by a line):
     *              "Congratulations! You successfully completed the adventure!"
     *       - Prompt for a char using the promptChar method with the prompt:
     *         "Do you want to try again? "
     *   - Repeat until the character returned by promptChar is an 'n'
     *   - Print out "Thank you for playing!", terminated by a newline.
     * @param args Unused
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random(Config.SEED);
        ArrayList<String[]> arrRooms = new ArrayList<String[]>();
        ArrayList<ArrayList<String[]>> arrTrans = new ArrayList<ArrayList<String[]>>();
        Character returnedChar = null;
        boolean flag = true;
        boolean flag2 = false;
        boolean win = false;
        boolean loose = false;
        boolean returnedBoolean;
        String probTransReturn;
        String[] curRoom = new String[Config.ROOM_DET_LEN];
        String textWelcome = "Welcome to this choose your own adventure system!";
        String textPromptFile = "Please enter the story filename: ";
        String textWin = "Congratulations! You successfully completed the adventure!";
        String textLoose = "You failed to complete the adventure. Better luck next time!";
        String textQuit = "Are you sure you want to quit the adventure? ";
        String fName;
        String returnedString;

        // print welcome text
        System.out.println(textWelcome);
        // loop while the flag is true
        while (flag) {
            // set filename
            fName = promptString(sc, textPromptFile);
            // if parsed correctly, set flag2 to true
            if (parseFile(fName, arrRooms, arrTrans, curRoom)) {
                flag2 = true;
            }
            // loop while flag2 is true
            while (flag2) {
                ArrayList<String[]> currTrans = new ArrayList<String[]>();
                // get the current room
                int currIndex = getRoomIndex(curRoom[0], arrRooms);
                // reset win and loose
                win = false;
                loose = false;
                // if the index is not -1 then set the win/loose booleans based off the current
                // transition values
                if (currIndex != -1) {
                    currTrans = arrTrans.get(currIndex);
                    win = currTrans.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS);
                    loose = currTrans.get(0)[Config.TRAN_DESC].equals(Config.FAIL);
                }
                // otherwise loose is true
                else {
                    loose = true;
                }
                // display the room
                displayRoom(curRoom[0], arrRooms);
                // display transitions if the transitions are not terminal
                if (currTrans.size() > 0
                        && !currTrans.get(0)[Config.TRAN_DESC].equals(Config.SUCCESS)
                        && !currTrans.get(0)[Config.TRAN_DESC].equals(Config.FAIL)) {
                    displayTransitions(curRoom[0], arrRooms, arrTrans);
                }
                // if win and loose are false continue
                if (!win && !loose) {
                    // call probTrans
                    probTransReturn = probTrans(rand, arrTrans.get(currIndex));
                    // if null enter
                    if (probTransReturn == null) {
                        // call prompt int
                        int transChosen = promptInt(sc, "Choose: ", -2,
                                arrTrans.get(currIndex).size() - 1);
                        // if -1, quit if the user enters 'y' and set to loose
                        if (transChosen == -1) {
                            char quitChar = promptChar(sc, textQuit);
                            if (quitChar == 'y') {
                                curRoom[0] = Config.FAIL;
                                loose = true;
                            }
                        }
                        // if -2, run bookmark code
                        else  if (transChosen == -2) {
                            // create a temp array with room details
                            String[] roomDetails = getRoomDetails(curRoom[0], arrRooms);
                            // prompt for bookmark filename
                            String prompt = "Bookmarking current location: " +
                                    roomDetails[Config.ROOM_TITLE] +". Enter bookmark filename: ";
                            returnedString = promptString(sc, prompt);
                            // call save bookmark
                            returnedBoolean = saveBookmark(fName, curRoom[0], returnedString);
                            // print if saving bookmark was successful or not
                            if (returnedBoolean == true) {
                                System.out.println("Bookmark saved in " + returnedString);
                            }
                            else {
                                System.out.println("Error saving bookmark in " + returnedString);
                            }
                        }
                        // otherwise set the current room to the room ID at the choose value
                        else {
                            curRoom[0] = arrTrans.get(currIndex).get(transChosen)
                                    [Config.TRAN_ROOM_ID];
                        }
                    }
                    // otherwise set the room to what probTrans returned
                    else {
                        curRoom[0] = probTransReturn;
                    }
                }
                // print loose message is loose is true
                else if (loose) {
                    System.out.println(textLoose);
                    flag2 = false;
                }
                // otherwise print win message
                else {
                    System.out.println(textWin);
                    flag2 = false;
                }
            }
            // prompt for char to try again
            returnedChar = promptChar(sc, "Do you want to try again? ");
            // if 'n', print thank you message, and quit- otherwise continue
            if (returnedChar == 'n') {
                System.out.println("Thank you for playing!");
                return;
            }
            continue;
        }
    }
}