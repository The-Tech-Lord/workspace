import stdio
import sys

# Accept n (int) as command-line argument.
number = int(sys.argv[1])

# Set count (number of primes <= n) to 0
prime_count = 0

for i in range(2, number + 1):
    prime = True
    # for each i from [2, n]...

    # Set j (potential divisor of i) to 2.
    divisor_j = 2

    while divisor_j <= i / divisor_j:
        # As long as j is less than or equal to i / j...

        if i % divisor_j == 0:
            # If j divides i, break (i is not a prime).
            prime = False
            break

        # Increment j by 1.
        divisor_j += 1

    # If you got here by exhausting the while loop, i is a prime, so increment count by 1.
    if prime:
        prime_count += 1

# Write count to standard output.
stdio.writeln(str(prime_count))
