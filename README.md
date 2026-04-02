# Competitive Programming Templates

This repository contains a collection of competitive-programming templates and utilities
in C++, Java and Python. It is organised to be a personal snippet library for solving
algorithmic problems quickly during contests.

- code.cpp : C++ contest template (reads from input.txt when local)
- Main.java : Java contest template with many helper classes
- python_templates/: Python implementations of common DS/algorithms (LCA, segment tree, DSU, ...)
- JAVA_Templates/: Java templates (segment tree, trie, etc.)
- run_cpp.sh / run_java.sh : helper scripts to build/run locally

## Running tests

- Python templates test

```bash
pytest -q
```

- Java templates test

```java
javac -d out JAVA_Templates/*.java tests/java/*.java
java -ea -cp out TestDSU
```
