import stdio
import sys

# Inputs
month = int(sys.argv[1])
day = int(sys.argv[2])
year = int(sys.argv[3])

# Calculations
y0 = year - (14 - month) // 12
x0 = y0 + (y0 // 4) - (y0 // 100) + (y0 // 400)
m0 = month + 12 * ((14 - month) // 12) - 2
days_of_the_week = (day + x0 + (31 * m0 // 12)) % 7

# Output
stdio.writeln(days_of_the_week)