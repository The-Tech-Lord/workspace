import math
import stdarray
import stdio


# Entry point (DO NOT EDIT).
def main():
    x = stdarray.readFloat1D()
    y = stdarray.readFloat1D()
    stdio.writeln(_distance(x, y))


# Returns the Euclidean distance between x and y
def _distance(x, y):
    # Set dist to 0.0.
    dist = 0.0

    for i in range(0, len(x)):
        # Iterate over x (or y)...

        # Add square of (x[i] - y[i]) to dist.
        dist += pow(x[i] - y[i], 2)

    # Return the square root of dist.
    return math.sqrt(dist)


if __name__ == '__main__':
    main()
