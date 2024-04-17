import stdio
import sys

# Inputs
p_var = int(sys.argv[1])
q_var = int(sys.argv[2])

# Calculations
while p_var % q_var != 0:
    temp = p_var
    p_var = q_var
    q_var = temp % q_var

# Output
stdio.writeln(q_var)
