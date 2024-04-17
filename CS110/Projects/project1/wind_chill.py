import stdio
import sys

# Inputs
temperature = float(sys.argv[1])
wind_speed = float(sys.argv[2])

# Calculations
wind_chill = 35.74 + (0.6215 * temperature) + ((0.4275 * temperature - 35.75) * (wind_speed ** 0.16))

# Outputs
stdio.writeln(wind_chill)
