import math
import stdio
import sys

# Inputs
x1 = math.radians(float(sys.argv[1])) # (x1, y1)
y1 = math.radians(float(sys.argv[2]))

x2 = math.radians(float(sys.argv[3])) # (x2, y2)
y2 = math.radians(float(sys.argv[4]))

# Calculations
distance = 6359.83 * math.acos(math.sin(x1) * math.sin(x2) + math.cos(x1) * math.cos(x2) * math.cos(y1 - y2))

# Output
stdio.writeln(str(distance))
