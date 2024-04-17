import stdio
import sys

# Inputs
number = int(sys.argv[1])

# Calculations
index_i = 2

while index_i <= number:
    total = 0
    index_j = 1

    while index_j <= index_i / 2:
        if index_i % index_j == 0:
            total += index_j
        index_j += 1

    if total == index_i:
        stdio.writeln(str(index_i))

    index_i += 1
