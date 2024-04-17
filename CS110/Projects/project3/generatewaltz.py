import stdarray
import stdrandom
import stdio

# Inserting values in Minuet
minuet_values = stdarray.create2D(11, 16)
for row in range(0, 11):
    for col in range(0, 16):
        minuet_values[row][col] = stdio.readInt()

# Inserting values in Trio
trio_values = stdarray.create2D(6, 16)
for row in range(0, 6):
    for col in range(0, 16):
        trio_values[row][col] = stdio.readInt()

# Debugging
# stdio.writeln(str(minuet_values))
# stdio.writeln()
# stdio.writeln(str(trio_values))
# stdio.writeln()

# Outputting
minuet_die1 = stdrandom.uniformInt(0, 6)
minuet_die2 = stdrandom.uniformInt(0, 6)

for index in range(0, 16):
    stdio.write(str(minuet_values[minuet_die1 + minuet_die2][index]) + " ")

trio_die = stdrandom.uniformInt(0, 6)

for index in range(0, 16):
    stdio.write(str(trio_values[trio_die][index]) + " ")