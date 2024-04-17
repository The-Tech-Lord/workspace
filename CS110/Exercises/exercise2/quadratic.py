import math
import stdio
import sys

# Accept a (float), b (float), and c (float) as command-line arguments.
a_var = float(sys.argv[1])
b_var = float(sys.argv[2])
c_var = float(sys.argv[3])

if a_var == 0:
    # If a is 0, write the message "Value of a must not be 0" to standard output.
    stdio.writeln("Value of a must not be 0")
else:
    # Compute discriminant (b^2 - 4ac).
    discriminant = pow(b_var, 2) - 4 * a_var * c_var

    if discriminant < 0:
        # If discriminant is negative, write the message "Value of discriminant must not be
        # negative" to standard output.
        stdio.write("Value of discriminant must not be negative")
    else:
        # Compute the two roots of the quadratic equation ax^2 + bx + c = 0.
        root1 = (-b_var + math.sqrt(pow(b_var, 2) - 4 * a_var * c_var)) / 2 * a_var
        root2 = (-b_var - math.sqrt(pow(b_var, 2) - 4 * a_var * c_var)) / 2 * a_var

        # Write the two roots to standard output, separated by a space.
        stdio.writeln(str(root1) + " " + str(root2))
