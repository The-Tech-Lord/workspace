import math
import stdio
import sys

# Inputs
latitude_psi = float(sys.argv[1])     # y
longitude_lambda = float(sys.argv[2]) # x

# Calculations
x = longitude_lambda
y = math.log((1 + math.sin(math.radians(latitude_psi))) / (1 - math.sin(math.radians(latitude_psi)))) / 2

# Output
stdio.writeln(str(x) + " " + str(y))
