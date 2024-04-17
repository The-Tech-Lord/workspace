import stdio
import sys

# Variables
big_g = 6.674e-11

# Inputs
m1 = float(sys.argv[1])
m2 = float(sys.argv[2])
r = float(sys.argv[3])

# Calculations
grav_force = big_g * ((m1 * m2) / (r ** 2))

# Outputs
stdio.writeln(grav_force)
