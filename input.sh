#!/bin/bash

# File to write
FILE="input.txt"

# Get clipboard content using wl-paste and save to file
if wl-paste >"$FILE"; then
    echo "Clipboard content written to $FILE"
else
    echo "Error: Could not retrieve clipboard content."
fi
