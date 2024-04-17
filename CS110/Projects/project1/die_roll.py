import stdio
import stdrandom
import sys

# Inputs
die_sides = int(sys.argv[1])

# Calculations
sum_of_2rolls = stdrandom.uniformInt(1, die_sides + 1) + stdrandom.uniformInt(1, die_sides + 1)

# Outputs
stdio.writeln(str(sum_of_2rolls))
