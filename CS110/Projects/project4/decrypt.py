import rsa
import stdio
import sys


# Entry point.
def main():
    # Private key input
    n = int(sys.argv[1])
    d = int(sys.argv[2])

    # Provides the width
    bits_width = rsa.bitLength(n)

    message = stdio.readAll()
    message_len = len(message)

    for i in range(0, message_len - 1, bits_width):
        substring = ""
        for j in range(i, i + bits_width):
            substring += message[j]
        y = rsa.bin2dec(substring)
        stdio.write(chr(rsa.decrypt(y, n, d)))


if __name__ == '__main__':
    main()
