#!/bin/bash

# This script automates stress testing for competitive programming problems.
# It continuously generates test cases, runs a main solution and a brute-force solution,
# and compares their outputs. The loop stops when a difference is found.

# Exit gracefully on Ctrl+C
trap "echo -e '\nScript interrupted by user. Exiting.'; exit" INT

# Counter for test cases
i=1

while true; do
    echo "--- Running Test Case #$i ---"

    # Step 1: Generate test case
    echo "  > Generating input..."
    g++ gen.cpp -o gen && ./gen >input.txt
    if [ $? -ne 0 ]; then
        echo "  [ERROR] Failed to generate test case. Exiting."
        break
    fi
    echo "  > Generated input: "
    bat input.txt #comment it if you don't want to see the input generated

    # Step 2: Run the main solution
    # Assumes Main.java reads from input.txt and writes to output.txt
    echo "  > Running main solution (Main.java)..."
    java Main.java
    if [ $? -ne 0 ]; then
        echo "  [ERROR] Failed to execute Main.java. Exiting."
        break
    fi

    # Step 3: Run the brute-force solution
    # Assumes brute.java reads from input.txt and writes to brute_output.txt
    echo "  > Running brute-force solution (brute.java)..."
    java brute.java
    if [ $? -ne 0 ]; then
        echo "  [ERROR] Failed to execute brute.java. Exiting."
        break
    fi

    # Step 4: Compare outputs
    echo "  > Comparing outputs..."
    diff_output=$(diff -wB output.txt brute_output.txt)

    if [ -n "$diff_output" ]; then
        echo
        echo "========================================="
        echo "  ❌ Difference found on Test Case #$i!"
        echo "========================================="
        echo
        echo "INPUT:"
        echo "-----------------------------------------"
        cat input.txt
        echo "-----------------------------------------"
        echo
        echo "YOUR OUTPUT (output.txt):"
        echo "-----------------------------------------"
        cat output.txt
        echo "-----------------------------------------"
        echo
        echo "CORRECT OUTPUT (brute_output.txt):"
        echo "-----------------------------------------"
        cat brute_output.txt
        echo "-----------------------------------------"
        echo
        echo "DIFFERENCE:"
        echo "-----------------------------------------"
        echo "$diff_output"
        echo "-----------------------------------------"
        break
    else
        echo "  ✅ Test Case #$i: Correct"
        echo
    fi

    i=$((i + 1))
done
