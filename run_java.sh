#!/bin/bash

# Run the Java program
java Main.java

# Function to display file contents if they exist
display_file() {
    local filename="$1"
    if [[ -f "$filename" ]]; then
        echo -e "\nContents of $filename:"
        cat "$filename"
    else
        echo "$filename does not exist."
    fi
}

# Display the contents of output.txt and dbg.txt
display_file "output.txt"
display_file "dbg.txt"

# Prompt the user to press any key to exit
echo -e "\nPress any key to exit..."
read -n 1 -s  # Wait for a single keypress without showing it
