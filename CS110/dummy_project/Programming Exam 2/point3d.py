import math
import stdio

class Point3D:
    def __init__(self, x=0, y=0, z=0):
         self._x = x
         self._y = y
         self._z = z

    def getX(self):
        return self._x

    def getY(self):
        return self._y

    def getZ(self):
        return self._z

    def distToOrigin(self):
        # Distance forumla for 3D space in regards to the origin
        return math.sqrt(pow(self._x - 0, 2) + pow(self._y - 0, 2) + pow(self._z - 0, 2))

    def distTo(self, other):
        # Distance formula for 3D space in regards to another point
        return math.sqrt(pow(self._x - other._x, 2) + pow(self._y - other._y, 2) + pow(self._z - other._z, 2))

    def flip(self):
        # Create a new object and set multiply the values by -1
        flipped_point = self
        flipped_point._x *= -1
        flipped_point._y *= -1
        flipped_point._z *= -1

        return flipped_point

    def __lt__(self, other):
        # Returns True if self's distance to origin is less than other's distance to origin
        return True if (self.distToOrigin() < other.distToOrigin()) else False

    def __eq__(self, other):
        # Returns True if self's distance to origin is equal to other's distance to origin
        return True if (self.distToOrigin() == other.distToOrigin()) else False

    def __str__(self):
        # Returns the string representation of self (Point)
        return "(" + str(self._x) + ", " + str(self._y) + ", " + str(self._z) + ")"

def _main():
    p1 = Point3D(1, 0)
    p2 = Point3D(0, 1)
    stdio.writeln(p1)
    stdio.writeln(p2)
    stdio.writeln(p1.distTo(p2))
    stdio.writeln(p1.flip())
    stdio.writeln(p1 < p2)
    stdio.writeln(p1 == p2)

if __name__ == "__main__":
    _main()
