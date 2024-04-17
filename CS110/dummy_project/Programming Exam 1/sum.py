import stdarray
import stdio

# Standard Input
inputs = stdarray.create1D(10)
iterator = 0

'''
Due to how strings worked in Python, I decided to create a counter
variable as a work around this issue.
'''
integer_counter = 0

while not stdio.isEmpty():
    inputs[iterator] = stdio.readInt()
    iterator += 1
    integer_counter += 1

# Sorting
'''
int_inputs was created to hold actual integer values. This is due to the original inputs array
having potentially holding values of 'None'.
'''
int_inputs = stdarray.create1D(integer_counter)

for index in range(integer_counter):
    int_inputs[index] = inputs[index]

# Calculations
result = 0
for index in int_inputs:
    if index % 2 == 0:
        continue
    else:
        result += pow(int(index), 2)

# Outputting
stdio.writeln(str(result))
