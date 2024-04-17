import math
import stdio
import sys

# Inputs
radius = float(sys.argv[1])

# Variables
area = 4 * math.pi * pow(radius, 2)  # 4 * pi * r^2
volume = (4 * math.pi * pow(radius, 3)) / 3

# Output
stdio.writeln("area = " + str(area))
stdio.writeln("volume = " + str(volume))