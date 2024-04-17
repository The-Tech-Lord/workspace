import stdio
import math
import sys

# Variables
temperature = float(sys.argv[1])
wind_speed = float(sys.argv[2])
wind_chill = 0 # Default Value

# Calculations
if temperature > 50:
    stdio.writeln("Value of t must be <= 50 F")
elif wind_speed <= 3:
    stdio.writeln("Value of v must be > 3 mph")
else:
    wind_chill = 35.74 + 0.6215 * temperature + (0.4275 * temperature - 35.75) * pow(wind_speed, 0.16)
    stdio.writeln(wind_chill)