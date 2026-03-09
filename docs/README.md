# Mandy User Guide

Mandy is a friendly desktop chatbot that helps you manage your tasks efficiently. With Mandy, you can keep track of todos, deadlines, and events—all through a simple command-line interface.



## Quick Start

1. Ensure you have Java 11 or above installed.
2. Download the latest `Mandy.jar` from the [releases page](https://github.com/yourusername/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Mandy.
4. Open a terminal, navigate to that folder, and run:
   ```
   java -jar Mandy.jar
   ```
5. Type `?` to see a list of available commands.

## Features

### 1. View Help: `?`

Displays a list of all available commands and their usage.

**Example:**
```
?
```

**Expected output:**
```
 Here are the commands you can use:
   bye - Exit the program
   list - List all tasks
   mark <number> - Mark a task as done
   unmark <number> - Mark a task as not done
   delete <number> - Delete a task
   todo <description> - Add a todo task
   deadline <description> /by <time> - Add a deadline task
   event <description> /from <time> /to <time> - Add an event task
   schedule <date> - Show tasks scheduled on a specific date
   find <keyword> - Find tasks by keyword in description
   ? - Show this help message
 Date formats: yyyy-mm-dd, d/m/yyyy, d/m/yyyy HHmm, etc.
```

### 2. Add a Todo: `todo`

Adds a todo task with a description.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo read book
```

**Expected output:**
```
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
```

### 3. Add a Deadline: `deadline`

Adds a deadline task with a description and due date/time.

**Format:** `deadline DESCRIPTION /by DATE_TIME`

**Supported date/time formats:**
- `yyyy-MM-dd HH:mm` (e.g., `2025-12-31 23:59`)
- `d/M/yyyy HHmm` (e.g., `31/12/2025 2359`)
- `d-M-yyyy HH:mm` (e.g., `31-12-2025 23:59`)
- `MMM d yyyy HH:mm` (e.g., `Dec 31 2025 23:59`)
- Date-only formats (time defaults to midnight):
   - `yyyy-MM-dd`
   - `d/M/yyyy`
   - `d-M-yyyy`
   - `MMM d yyyy`

**Example:**
```
deadline submit report /by 2025-12-31 18:00
```

**Expected output:**
```
 Got it. I've added this task:
   [D][ ] submit report (by: Dec 31 2025, 18:00)
 Now you have 2 tasks in the list.
```

### 4. Add an Event: `event`

Adds an event task with a description, start date/time, and end date/time.

**Format:** `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`

**Example:**
```
event team meeting /from 2025-12-31 14:00 /to 2025-12-31 16:00
```

**Expected output:**
```
 Got it. I've added this task:
   [E][ ] team meeting (from: Dec 31 2025, 14:00 to: Dec 31 2025, 16:00)
 Now you have 3 tasks in the list.
```

### 5. List All Tasks: `list`

Displays all tasks currently stored.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] submit report (by: Dec 31 2025, 18:00)
 3.[E][ ] team meeting (from: Dec 31 2025, 14:00 to: Dec 31 2025, 16:00)
```

### 6. Mark a Task as Done: `mark`

Marks the task at the specified index as done.

**Format:** `mark INDEX`

**Example:**
```
mark 1
```

**Expected output:**
```
 Nice! I've marked this task as done:
   [T][X] read book
```

### 7. Unmark a Task: `unmark`

Marks the task at the specified index as not done.

**Format:** `unmark INDEX`

**Example:**
```
unmark 1
```

**Expected output:**
```
 OK, I've marked this task as not done yet:
   [T][ ] read book
```

### 8. Delete a Task: `delete`

Deletes the task at the specified index.

**Format:** `delete INDEX`

**Example:**
```
delete 2
```

**Expected output:**
```
 Noted. I've removed this task:
   [D][ ] submit report (by: Dec 31 2025, 18:00)
 Now you have 2 tasks in the list.
```

### 9. Find Tasks: `find`

Searches for tasks whose description contains the given keyword (case‑insensitive).

**Format:** `find KEYWORD`

**Example:**
```
find book
```

**Expected output:**
```
 Here are the matching tasks in your list:
 1.[T][ ] read book
```

### 10. View Schedule: `schedule`

Shows all tasks scheduled on a specific date. Includes deadlines due on that date and events that start, end, or span the date.

**Format:** `schedule DATE`

**Example:**
```
schedule 2025-12-31
```

**Expected output:**
```
Tasks scheduled on Dec 31 2025:
 1.[D][ ] submit report (by: Dec 31 2025, 18:00)
 2.[E][ ] team meeting (from: Dec 31 2025, 14:00 to: Dec 31 2025, 16:00)
```

### 11. Exit the Program: `bye`

Exits the chatbot and displays a goodbye message.

**Format:** `bye`

**Example:**
```
bye
```

**Expected output:**
```
Byeeeeee, see u soon ;)
```

## Data Storage

Mandy automatically saves your tasks to a file (`./data/duke.txt`) after every change. When you restart the application, your tasks are loaded from the same file. The file is created automatically if it does not exist.

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Help | `?` | `?` |
| Add Todo | `todo DESCRIPTION` | `todo buy groceries` |
| Add Deadline | `deadline DESCRIPTION /by DATE_TIME` | `deadline assignment /by 2025-12-31 23:59` |
| Add Event | `event DESCRIPTION /from START /to END` | `event concert /from 2025-12-31 20:00 /to 2025-12-31 22:00` |
| List Tasks | `list` | `list` |
| Mark Task | `mark INDEX` | `mark 1` |
| Unmark Task | `unmark INDEX` | `unmark 1` |
| Delete Task | `delete INDEX` | `delete 2` |
| Find Tasks | `find KEYWORD` | `find book` |
| View Schedule | `schedule DATE` | `schedule 2025-12-31` |
| Exit | `bye` | `bye` |

## FAQ

**Q: How do I edit a task?**  
A: Mandy does not support direct editing. You can delete the task and add a new one with the correct details.

**Q: Can I use natural language for dates?**  
A: Currently, Mandy supports only the date/time formats listed above. More flexible parsing may be added in future versions.

**Q: Where are my tasks stored?**  
A: Tasks are saved in `./data/duke.txt` relative to where you run the JAR file.

**Q: What happens if I enter an invalid command?**  
A: Mandy will show an error message with a hint about the correct format.

## Support

If you encounter any issues or have suggestions, please file an issue on the [GitHub repository](https://github.com/yourusername/ip/issues).

---

Happy task managing with Mandy! 🎉