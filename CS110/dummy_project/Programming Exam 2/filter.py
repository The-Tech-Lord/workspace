from point3d import Point3D
import stdio
import sys

def main():
    r = float(sys.argv[1])
    points = []  # Array to store the points <= to r

    while not stdio.isEmpty():
        # Read float inputs from standard input
        x = stdio.readFloat()
        y = stdio.readFloat()
        z = stdio.readFloat()

        # Create an object using read float values
        point = Point3D(x, y, z)

        if point.distToOrigin() <= r:
            points += [point]

    for i in range(0, len(points)):
        for j in range(i + 1, len(points)):  # j acts as the indexes that are in front of i
            if points[i].getX() < points[j].getX() and points[i].getY() < points[j].getY() and points[i].getZ() < points[j].getZ():
                temp = points[j]
                points[j] = points[i]
                points[i] = temp
                break

    for i in points:
        stdio.writeln(i.__str__())

if __name__ == "__main__":
    main()
