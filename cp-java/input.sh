#!/bin/bash

# File to overwrite
FILE="input.txt"

# Get the clipboard content using xclip and overwrite the file
xclip -selection clipboard -o > "$FILE"

echo "Content of clipboard has been written to $FILE"
