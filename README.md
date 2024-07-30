.pse files are pseudo codes.

Every .pse file starts with DESCRIPTION, INPUT and OUTPUT sections of the algorithm.

Every code is written in a way that a called function is defined right below the calling function.

Eg:
```
SUM_FOUR_NUMBERS(a, b, c, d):
    return SUM(a, b) + SUM(c, d)

SUM(a, b):
    return a + b
```

Function names are defined using UPPERCASE_LETTERS.

Indentations mark start/end of blocks (like in Python).

For some algorithms you can find language-specific implementations