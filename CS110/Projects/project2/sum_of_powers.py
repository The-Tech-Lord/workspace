import stdio
import sys

# Inputs
number = int(sys.argv[1])
exponent = int(sys.argv[2])

# Calculations
total = 0
i_var = 1

while i_var <= number:
    total += pow(i_var, exponent)
    i_var += 1

# Output
stdio.writeln(total)
