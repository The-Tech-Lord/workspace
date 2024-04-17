import stdio
import sys

# Inputs
number = int(sys.argv[1])

# Calculations
a_var = 1
b_var = 1
i_var = 3

while i_var <= number:
    temp = a_var
    a_var = b_var
    b_var += temp
    i_var += 1

# Output
stdio.writeln(b_var)