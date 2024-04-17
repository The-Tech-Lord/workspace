import stdio
import math
import sys

# Inputs
month = int(sys.argv[1])
day = int(sys.argv[2])
year = int(sys.argv[3])

# Variable Check
if month > 12 or month == 0:
    stdio.writeln("Value of month must be greater than 0 and no greater than 12")
elif day > 31 or day == 0:
    stdio.writeln("Value of day must be greater than 0 and no greater than 31")
else:
    # Calculations
    y0_var = year - (14 - month) // 12
    x0_var = y0_var + (y0_var // 4) - (y0_var // 100) + (y0_var // 400)
    m0_var = month + 12 * ((14 - month) // 12) - 2
    day_of_the_week = (day + x0_var + 31 * m0_var // 12) % 7

    # Control Flow
    if day_of_the_week == 0:
        stdio.writeln("Sunday")
    elif day_of_the_week == 1:
        stdio.writeln("Monday")
    elif day_of_the_week == 2:
        stdio.writeln("Tuesday")
    elif day_of_the_week == 3:
        stdio.writeln("Wednesday")
    elif day_of_the_week == 4:
        stdio.writeln("Thursday")
    elif day_of_the_week == 5:
        stdio.writeln("Friday")
    elif day_of_the_week == 6:
        stdio.writeln("Saturday")
    else:
        stdio.writeln("Error")