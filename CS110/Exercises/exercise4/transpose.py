import stdarray
import stdio


# Entry point (DO NOT EDIT).
def main():
    a = stdarray.readFloat2D()
    c = _transpose(a)
    for row in c:
        for v in row[:-1]:
            stdio.write(str(v) + ' ')
        stdio.writeln(row[-1])


# Returns the transpose of a.
def _transpose(a):
    # Get the dimensions of matrix a.
    m = len(a)  # number of rows in a
    n = len(a[0])  # number of columns in a

    # Create an n-by-m matrix c with all elements initialized to 0.0.
    c = stdarray.create2D(n, m, 0.0)

    stdio.writeln(str(a))
    stdio.writeln(str(c))

    # Fill in the elements of c such that c[i][j] = a[j][i], where 0 <= i < n and 0 <= j < m.
    for i in range(0, n):
        for j in range(0, m):
            c[i][j] = a[j][i]

    # Return c.
    return c


if __name__ == '__main__':
    main()
