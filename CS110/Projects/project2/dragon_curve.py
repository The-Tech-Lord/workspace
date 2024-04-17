import stdio
import sys

# Inputs
number = int(sys.argv[1])

# Calculations
dragon = "F"
nogard = "F"
i_var = 1

while i_var <= number:
    temp = dragon
    dragon = temp + "L" + nogard
    nogard = temp + "R" + nogard
    i_var += 1

# Output
stdio.writeln(dragon)
