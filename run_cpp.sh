#!/bin/bash

# Set filenames
SRC="code.cpp"
EXE="code.out"
INPUT="input.txt"
OUTPUT="output.txt"
DEBUG="dbg.txt"

# Compile the C++ source file with debug info
echo "Compiling $SRC..."
g++ -Wall -Wextra -O2 "$SRC" -o "$EXE" 2>"$DEBUG"

# Check if compilation was successful
if [[ $? -ne 0 ]]; then
    echo "Compilation failed. See $DEBUG for errors."
    exit 1
fi

echo "Running $EXE..."

# Run the program with input and redirect stdout and stderr
./"$EXE" <"$INPUT" >"$OUTPUT" 2>>"$DEBUG"

# Function to display file contents
display_file() {
    local filename="$1"
    if [[ -f "$filename" ]]; then
        echo -e "\nContents of $filename:"
        cat "$filename"
    else
        echo "$filename does not exist."
    fi
}

# Display results
display_file "$OUTPUT"
display_file "$DEBUG"

# Prompt for exit
echo -e "\nPress any key to exit..."
read -n 1 -s
