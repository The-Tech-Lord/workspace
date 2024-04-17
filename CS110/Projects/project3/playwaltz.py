import stdaudio
import stdio
import sys
import stdarray

waltz_numbers = stdarray.create1D(32)

# Input Check Variables
total_measures = \
    minuet_bounds_hold = \
    trio_bounds_hold = 0

# Input Checking
while not stdio.isEmpty():
    # Checks minuet measure input bounds
    for iterator in range(0, 16):
        if total_measures > 32:
            sys.exit("A waltz must be exactly 32 characters")
        else:
            total_measures += 1

        minuet_bounds_hold = stdio.readInt()
        if 1 > minuet_bounds_hold > 176:
            sys.exit("A minuet measure must be from [1, 176]")
        else:
            waltz_numbers[iterator] = str(minuet_bounds_hold)

    # Checks trio measure input bounds
    for iterator in range(16, 32):
        if total_measures > 32:
            sys.exit("A waltz must be exactly 32 characters")
        else:
            total_measures += 1

        trio_bounds_hold = stdio.readInt()
        if 1 > trio_bounds_hold > 96:
            sys.exit("A trio measure must be from [1, 96]")
        else:
            waltz_numbers[iterator] = str(trio_bounds_hold)

    stdio.writeln(str(waltz_numbers))

# Audio
for iterator in range(0, 16):
    minuet_filename = "data/M" + waltz_numbers[iterator]
    stdio.writeln(str(minuet_filename))
#    stdaudio.playFile(minuet_filename)

for i in range(16, 32):
    trio_filename = "data/T" + waltz_numbers[iterator]
    stdio.writeln(str(trio_filename))
#    stdaudio.playFile(trio_filename)