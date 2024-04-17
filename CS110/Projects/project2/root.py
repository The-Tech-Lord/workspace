import stdio
import sys

# Inputs
k_var = int(sys.argv[1])
c_var = float(sys.argv[2])
epsilon = float(sys.argv[3])

guess = c_var

# Calculations
function_1 = pow(guess, k_var) - c_var
function_2 = k_var * pow(guess, k_var - 1)

while abs(1 - c_var / pow(guess, k_var)) > epsilon:
    guess = guess - function_1 / function_2
    function_1 = pow(guess, k_var) - c_var
    function_2 = k_var * pow(guess, k_var - 1)

# Output
stdio.writeln(str(guess))
