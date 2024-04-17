import stdio
import stdrandom
import sys


# Generates and returns the public/private keys as a tuple (n, e, d). Prime numbers p and q
# needed to generate the keys are picked from the interval [lo, hi).
def keygen(lo, hi):
    primes_lohi = _primes(lo, hi)
    p = _choice(primes_lohi)
    q = _choice(primes_lohi)

    n = p * q
    m = (p - 1) * (q - 1)

    primes_2m = _primes(2, m)
    e = _choice(primes_2m)
    while m % e == 0:
        e = _choice(primes_2m)

    d = None
    for i in range(1, m):
        d = i
        if (e * d) % m == 1:
            break

    return (n, e, d)


# Encrypts x (int) using the public key (n, e) and returns the encrypted value.
def encrypt(x, n, e):
    return pow(x, e) % n


# Decrypts y (int) using the private key (n, d) and returns the decrypted value.
def decrypt(y, n, d):
    return pow(y, d) % n


# Returns the least number of bits needed to represent n.
def bitLength(n):
    return len(bin(n)) - 2


# Returns the binary representation of n expressed in decimal, having the given width, and padded
# with leading zeros.
def dec2bin(n, width):
    return format(n, '0%db' % width)


# Returns the decimal representation of n expressed in binary.
def bin2dec(n):
    return int(n, 2)


# Returns a list of primes from the interval [lo, hi).
def _primes(lo, hi):
    # Inserts numbers from interval [lo, hi) into numbers[]
    numbers = []
    for i in range(lo, hi):
        if i == 0 or i == 1:
            continue
        numbers += [i]

    # Iterates through numbers[] to find the prime numbers
    prime_numbers = []  # Holds found prime numbers
    for number in numbers:
        is_prime = True
        divisor = 2  # Divisor used in finding prime numbers
        while divisor <= number / divisor:
            if number % divisor == 0:
                # If i divides n, break (n is not a prime).
                is_prime = False
                break

            divisor += 1

        if is_prime:
            prime_numbers += [number]

    return prime_numbers


# Returns a list containing a random sample (without replacement) of k items from the list a.
def _sample(a, k):
    list_copy = []

    # If k == 0, then return an empty array
    # Self-explanatory
    if k == 0:
        return list_copy

    # Inserts requested items up to k
    for i in range(0, k):
        list_copy += [a[i]]
    stdio.writeln(str(list_copy))

    # If the requested amount of items is 1 or 2, shuffling them
    # would be unnecessary and therefore just returns the array
    if k == 1 or k == 2:
        return list_copy

    # Shuffles the array
    # Implementation of the shuffle function
    for i in range(0, len(list_copy)):
        rand_index = i + stdrandom.uniformInt(0, len(list_copy) - i)
        temp = list_copy[i]
        list_copy[i] = list_copy[rand_index]
        list_copy[rand_index] = temp

    return list_copy


# Returns a random item from the list a.
def _choice(a):
    return a[stdrandom.uniformInt(0, len(a))]


# Unit tests the library [DO NOT EDIT].
def _main():
    x = ord(sys.argv[1])
    n, e, d = keygen(25, 100)
    encrypted = encrypt(x, n, e)
    stdio.writef('encrypt(%c) = %d\n', x, encrypted)
    decrypted = decrypt(encrypted, n, d)
    stdio.writef('decrypt(%d) = %c\n', encrypted, decrypted)
    width = bitLength(x)
    stdio.writef('bitLength(%d) = %d\n', x, width)
    xBinary = dec2bin(x, width)
    stdio.writef('dec2bin(%d) = %s\n', x, xBinary)
    stdio.writef('bin2dec(%s) = %d\n', xBinary, bin2dec(xBinary))


if __name__ == '__main__':
    _main()
