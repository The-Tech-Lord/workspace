import math
import stdio
import sys

# Inputs
lambda_events = float(sys.argv[1])
time = float(sys.argv[2])

# Calculations
probability = math.exp(-lambda_events * time)

# Outputs
stdio.writeln(str(probability))
