import rsa
import stdio
import sys


# Entry point.
def main():
    lo = int(sys.argv[1])
    hi = int(sys.argv[2])

    for i in rsa.keygen(lo, hi):
        stdio.write(str(i) + " ")
    stdio.writeln()

if __name__ == '__main__':
    main()
