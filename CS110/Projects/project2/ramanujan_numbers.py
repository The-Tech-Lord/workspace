import stdio
import sys

# Inputs
number = int(sys.argv[1])

# Calculations
a = 1

while 1 <= pow(a, 3) <= number:
    b = a + 1

    while pow(b, 3) <= number - pow(a, 3):
        c = a + 1

        while pow(c, 3) <= number:
            d = c + 1

            while pow(d, 3) <= number - pow(c, 3):
                if pow(a, 3) + pow(b, 3) == pow(c, 3) + pow(d, 3):
                    number_result = pow(a, 3) + pow(b, 3)
                    stdio.writeln(str(number_result) + " = " + str(a) + "^3 + " + str(b) + "^3 = " + str(c) + "^3 + " + str(d) + "^3")

                d += 1
            c += 1
        b += 1
    a += 1
