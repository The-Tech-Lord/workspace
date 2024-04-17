import stdio
import sys

# Accept x (int), y (int), and z (int) as command-line arguments.
x = int(sys.argv[1])
y = int(sys.argv[2])
z = int(sys.argv[3])

# Set expr to an expression which is True if each of x, y, and z is less than or equal to the sum
# of the other two, and False otherwise.

if x <= y + z and y <= x + z and z <= x + y:
    expr = True
else:
    expr = False

# Write expr to standard output.
stdio.writeln(expr)
