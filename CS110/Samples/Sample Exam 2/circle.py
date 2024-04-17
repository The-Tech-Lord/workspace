import math

class Circle:
    def __init__(self, h = 0, k = 0, r = 1):
        self.h = h  # x coor of center
        self.k = k  # y coor of center
        self.r = r  # radius

    def area(self):
        return math.pi * pow(self.r, 2)

    def circumference(self):
        return 2 * math.pi * self.r

    def contains(self, x, y):
        return True if (pow(self.h - x, 2) + pow(self.k - y, 2) <= pow(self.r, 2)) else False

    def scale(self, r):
        self.r = r
        return self

    def distanceTo(self, other):
        return math.sqrt(pow(self.h - other.h, 2) + pow(self.k - other.k, 2))

    def __eq__(self, other):
        return True if (self.h == other.h and self.k == other.k and self.r == other.r) else False

    def __lt__(self, other):
        return True if (self.area() < other.area()) else False

    def __str__(self):
        return "(" + str(self.h) + ", " + str(self.k) + ", " + str(self.r) + ")"

# Unit tests the data type
def _main():
    import stdio

    c = Circle()
    d = Circle(1, 1, 2)
    stdio.writeln(c)
    stdio.writeln(d)
    stdio.writeln(c.area())
    stdio.writeln(c.circumference())
    stdio.writeln(c.contains(1, 1))
    stdio.writeln(c.scale(2))
    stdio.writeln(c.distanceTo(d))
    stdio.writeln(c == d or c == c)
    stdio.writeln(d < c)

if __name__ == "__main__":
    _main()
