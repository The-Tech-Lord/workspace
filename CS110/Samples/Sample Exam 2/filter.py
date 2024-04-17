from circle import Circle
import stdio
import sys

# Entry point
def main():
    h = float(sys.argv[1])
    k = float(sys.argv[2])
    r = float(sys.argv[3])

    c = Circle(h, k, r)
    is_contained = 0
    total = 0

    while not stdio.isEmpty():
        x_coor = stdio.readFloat()
        y_coor = stdio.readFloat()

        total += 1
        
        if c.contains(x_coor, y_coor):
           is_contained += 1 

    stdio.writeln(is_contained / total)

if __name__ == "__main__":
    main()
