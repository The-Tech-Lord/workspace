import math
import stdio
import sys
from blob_finder import BlobFinder
from picture import Picture


# Entry point
def main():
    pixels = int(sys.argv[1])
    tau = float(sys.argv[2])
    delta = float(sys.argv[3])
    
    prevBeads = BlobFinder(sys.argv[4], tau).getBeads(pixels)

    for i in sys.argv[5:]:
        currbeads = BlobFinder(i, tau).getBeads(pixels)
        for currBead in currbeads:
            if ():
                ...

if __name__ == '__main__':
    main()
