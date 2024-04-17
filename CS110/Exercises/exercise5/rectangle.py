import stdio
import sys
from interval import Interval


# A data type to represent a rectangle as two (x and y) intervals.
class Rectangle:
    # Constructs a new rectangle given the x and y intervals.
    def __init__(self, xint, yint):
        self._xint = xint
        self._yint = yint

    # Returns the area of this rectangle.
    def area(self):
        return (abs(self._xint._lbound - self._xint._ubound) * abs(self._yint._lbound - self._yint._ubound))

    # Returns the perimeter of this rectangle.
    def perimeter(self):
        return (abs(self._xint._lbound - self._xint._ubound) * 2) + (abs(self._yint._lbound - self._yint._ubound) * 2)

    # Returns True if this rectangle contains the point (x, y), and False otherwise.
    def contains(self, x, y):
        if (self._xint._lbound <= x <= self._xint._ubound and self._yint._lbound <= y <= self._yint._ubound):
            return True
        else:
            return False

    # Returns True if this rectangle intersects other, and False otherwise.
    def intersects(self, other):
        if (self._xint._lbound <= other._xint._lbound <= self._xint._ubound or self._yint._lbound <= other._yint._lbound <= self._yint._ubound):
            return True
        elif (self._xint._lbound <= other._xint._ubound <= self._xint._ubound or self._yint._lbound <= other._yint._ubound <= self._yint._ubound):
            return True
        else:
            return False

    # Returns a string representation of this rectangle.
    def __str__(self):
        return "[" + str(self._xint._lbound) + ", " + str(self._xint._ubound) + "] x [" + str(self._yint._lbound) + ", " + str(self._yint._ubound) + "]" 


# Unit tests the data type (DO NOT EDIT).
def _main():
    x = float(sys.argv[1])
    y = float(sys.argv[2])
    rectangles = []
    while not stdio.isEmpty():
        lbound1 = stdio.readFloat()
        rbound1 = stdio.readFloat()
        lbound2 = stdio.readFloat()
        rbound2 = stdio.readFloat()
        rectangles += [Rectangle(Interval(lbound1, rbound1), Interval(lbound2, rbound2))]
    for i in range(len(rectangles)):
        stdio.writef('Area(%s) = %f\n', rectangles[i], rectangles[i].area())
        stdio.writef('Perimeter(%s) = %f\n', rectangles[i], rectangles[i].perimeter())
        if rectangles[i].contains(x, y):
            stdio.writef('%s contains (%f, %f)\n', rectangles[i], x, y)
    for i in range(len(rectangles)):
        for j in range(i + 1, len(rectangles)):
            if rectangles[i].intersects(rectangles[j]):
                stdio.writef('%s intersects %s\n', rectangles[i], rectangles[j])


if __name__ == '__main__':
    _main()
