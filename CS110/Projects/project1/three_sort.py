import stdio
import sys

# Inputs
num1 = int(sys.argv[1])
num2 = int(sys.argv[2])
num3 = int(sys.argv[3])

# Calculations
min_val = min(num1, num2, num3)
max_val = max(num1, num2, num3)
middle_val = num1 + num2 + num3 - max_val - min_val

# Outputs
stdio.writeln(str(min_val) + " " + str(middle_val) + " " + str(max_val))

