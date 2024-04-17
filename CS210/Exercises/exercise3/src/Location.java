import java.util.Arrays;

import stdlib.StdOut;

public class Location implements Comparable<Location> {
    private String name; // location name
    private double lat;  // latitude
    private double lon;  // longitude

    // Constructs a new location given its name, latitude, and longitude.
    public Location(String name, double lat, double lon) {
        this.name = name;
		this.lat = lat;
		this.lon = lon;
    }

    // Returns the great-circle distance between this location and other.
    public double distanceTo(Location other) {
		// sqrt of (x2 - x1)^2 + (y2 - y1)^2
		double distance = Math.sqrt(Math.pow(other.lat - this.lon, 2) + Math.pow(other.lat - this.lon, 2));
		
		return distance;
    }

    // Returns true if this location is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

		Location temp_other = (Location)other;
		if (this.lat == temp_other.lat && this.lon == temp_other.lon) {
			return true;
		}
		return false;
    }

    // Returns a string representation of this location.
    public String toString() {
        return this.name + " (" + this.lat + ", " + this.lon + ")";
    }

    // Returns a comparison of this location with other based on their respective distances to
    // the origin, Parthenon (Greece) @ 37.971525, 23.726726.
    public int compareTo(Location that) {
        Location Pantheon = new Location("Pantheon (Greece)", 37.971525, 23.726726);
		double this_dot_compare = this.distanceTo(Pantheon);
		double that_dot_compare = that.distanceTo(Pantheon);

		if (this_dot_compare > that_dot_compare) {
			// Greater than
			return 1;
		} else if (this_dot_compare < that_dot_compare) {
			// Less than
			return -1;
		} else {
			// Equal to
			return 0;
		}
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int rank = Integer.parseInt(args[0]);
        String name = args[1];
        double lat = Double.parseDouble(args[2]);
        double lon = Double.parseDouble(args[3]);
        Location[] wonders = new Location[7];
        wonders[0] = new Location("The Great Wall of China (China)", 40.6769, 117.2319);
        wonders[1] = new Location("Petra (Jordan)", 30.3286, 35.4419);
        wonders[2] = new Location("The Colosseum (Italy)", 41.8902, 12.4923);
        wonders[3] = new Location("Chichen Itza (Mexico)", 20.6829, -88.5686);
        wonders[4] = new Location("Machu Picchu (Peru)", -13.1633, -72.5456);
        wonders[5] = new Location("Taj Mahal (India)", 27.1750, 78.0419);
        wonders[6] = new Location("Christ the Redeemer (Brazil)", 22.9519, -43.2106);
        Arrays.sort(wonders);
        StdOut.println("Seven wonders, in the order of their distance to Parthenon (Greece):");
        for (Location wonder : wonders) {
            StdOut.println("  " + wonder);
        }
        Location loc = new Location(name, lat, lon);
        StdOut.print("wonders[" + rank + "] == " + loc + "? ");
        StdOut.println(wonders[rank].equals(loc));
    }
}
