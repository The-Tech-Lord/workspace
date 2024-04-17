import math

import stdarray
import stdio
import sys

# Accept n (int) as command-line argument.
n = int(sys.argv[1])

# Accept n floats from standard input and store them in a list x.
x = stdarray.create1D(n, False)
for i in range(0, n):
    x[i] = stdio.readFloat()

# Accept n floats from standard input and store them in a list y.
y = stdarray.create1D(n, False)
for i in range(0, n):
    y[i] = stdio.readFloat()

# Set distance to 0.0.
distance = 0.0

# Compute the squared Euclidean distance between x and y.
for i in range(0, n):
    # Add square of (x[i] - y[i]) to distance.
    distance += pow(x[i] - y[i], 2)

# Set distance to the square root of itself.
distance = math.sqrt(distance)

# Write distance to standard output.
stdio.writeln(str(distance))
