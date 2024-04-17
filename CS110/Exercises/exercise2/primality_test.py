import stdio
import sys

# Accept n (int) as command-line argument.
number = int(sys.argv[1])

# Set i (potential divisor of n) to 2.
divisor_i = 2

prime = True
while divisor_i <= number / divisor_i:
    # As long as i is less than or equal to n / i...

    if number % divisor_i == 0:
        # If i divides n, break (n is not a prime).
        prime = False
        break

    # Increment i by 1.
    divisor_i += 1  # Wish I could use ++

if prime:
    # If you got here by exhausting the loop, n is prime. Write True to standard output.
    stdio.writeln("True")
else:
    # If you got here by prematurely exiting the loop, n is not a prime. Write False to standard
    # output.
    stdio.writeln("False")
