import rsa
import stdio
import sys


# Entry point.
def main():
    # Private key input
    n = int(sys.argv[1])
    e = int(sys.argv[2])

    # Provides the width
    bits_width = rsa.bitLength(n)

    # String input
    message = stdio.readAll()

    # Encrypts each character
    for c in range(0, len(message)):
        x = ord(message[c])

        # Encrypts x, converts the encrypted decimal number to binary and
        # writes to standard output
        stdio.write(str(rsa.dec2bin(rsa.encrypt(x, n, e), bits_width)))
    stdio.writeln()


if __name__ == '__main__':
    main()
