import math
import stdio
import sys

# Inputs
theta1 = float(sys.argv[1])
num1 = float(sys.argv[2])
num2 = float(sys.argv[3])

# Calculations
theata2_final_value = math.asin((math.sin(math.radians(theta1)) * num1) / num2)

# Outputs
stdio.writeln(str(math.degrees(theata2_final_value)))
