import stdarray
import stdio

# Accept all the strings from standard input and store them in a list a.
a = []
while not stdio.isEmpty():
    a += stdio.readString()

# Reverse a
for i in range(0, len(a) // 2):
    # Iterate over half of the list a...

    # Exchange element at i in a with the element at len(a) - i - 1.
    temp = a[i]
    a[i] = a[len(a) - i - 1]
    a[len(a) - i - 1] = temp

# Write a to standard output.
for i in range(0, len(a)):
    if i + 1 < len(a):
        # If i is not the last column, write a[i] with a space after.
        stdio.write(str(a[i]) + " ")
    else:
        # Otherwise, write the element with a newline after.
        stdio.writeln(str(a[i]))
