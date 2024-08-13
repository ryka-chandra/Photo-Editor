package org.cis1200;

public class Pixel implements Comparable<Pixel> {

    /**
     * The {@code Pixel} representing the RGB color black.
     */
    public static final Pixel BLACK = new Pixel(0, 0, 0);

    /**
     * The {@code Pixel} representing the RGB color blue.
     */
    public static final Pixel BLUE = new Pixel(0, 0, 255);

    /**
     * The {@code Pixel} representing the RGB color red.
     */
    public static final Pixel RED = new Pixel(255, 0, 0);

    /**
     * The {@code Pixel} representing the RGB color green.
     */
    public static final Pixel GREEN = new Pixel(0, 255, 0);

    /**
     * The {@code Pixel} representing the RGB color white.
     */
    public static final Pixel WHITE = new Pixel(255, 255, 255);

    private int red;
    private int green;
    private int blue;

    /**
     * Create a new pixel with the provided color components.
     *
     * @param r the red component of the pixel
     * @param g the green component of the pixel
     * @param b the blue component of the pixel
     */
    public Pixel(int r, int g, int b) {
        
        if (r < 0) {
            this.red = 0;
        } else if (r > 255) {
            this.red = 255;
        } else {
            this.red = r;
        }

        if (g < 0) {
            this.green = 0;
        } else if (g > 255) {
            this.green = 255;
        } else {
            this.green = g;
        }

        if (b < 0) {
            this.blue = 0;
        } else if (b > 255) {
            this.blue = 255;
        } else {
            this.blue = b;
        }
    }

    /**
     * Create a new pixel with the provided color components, specified as an
     * array. 
     * @param c the array of components
     */

    public int clip(int x) {
        if (x < 0) {
            return 0;
        } else if (x > 255) {
            return 255;
        } else {
            return x;
        }
    }

    public Pixel(int[] c) {
        if (c == null) {
            this.red = 0;
            this.green = 0;
            this.blue = 0;
        } else if (c.length == 1) {
            this.red = clip(c[0]);
            this.green = 0;
            this.blue = 0;
        } else if (c.length == 2) {
            this.red = clip(c[0]);
            this.green = clip(c[1]);
            this.blue = 0;
        } else {
            if (c[0] < 0) {
                this.red = 0;
            } else if (c[0] > 255) {
                this.red = 255;
            } else {
                this.red = c[0];
            }
            
            if (c[1] < 0) {
                this.green = 0;
            } else if (c[1] > 255) {
                this.green = 255;
            } else {
                this.green = c[1];
            }
            
            if (c[2] < 0) {
                this.blue = 0;
            } else if (c[2] > 255) {
                this.blue = 255;
            } else {
                this.blue = c[2];
            }
        }
    }

    /**
     * Accessor for the red component of the pixel.
     *
     * @return the int value of the red component
     */
    public int getRed() {
        return red; // Implement this method
    }

    /**
     * Accessor for the green component of the pixel.
     *
     * @return the int value of the green component
     */
    public int getGreen() {
        return green; // Implement this method
    }

    /**
     * Accessor for the blue component of the pixel.
     *
     * @return the int value of the blue component
     */
    public int getBlue() {
        return blue; // Implement this method
    }

    /**
     * Accessor for the pixel's components as an array of 3 integers, where
     * index 0 is red, index 1 is green, and index 2 is blue.
     *
     *
     * @return an int array representing the pixel's components
     */
    public int[] getComponents() {
        int[] components = {this.getRed(), this.getGreen(), this.getBlue()};
        return components;
    }

    /**
     * Determines the level of similarity between this pixel and another by
     * summing the absolute values of the differences between corresponding
     * components of the two pixels. Distance to a null pixel is defined as -1.
     *
     *
     * @param px the other pixel with which to compare
     * @return the sum of the differences in each of the color components
     */
    public int distance(Pixel px) {
        if (px == null) {
            return -1;
        }
        return Math.abs(this.red - px.red) + Math.abs(
            this.green - px.green) + Math.abs(this.blue - px.blue);
    }

    /**
     * Returns a string representation of this pixel. The string should
     * comma separate the rgb values and surround them with parentheses.
     * <p>
     * @return a string representation of this pixel
     */
    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")";
    }

    /**
     * Compares the RGB values of the current Pixel with another to check if
     * they are the same (and thus whether the two Pixels equal each other)
     *
     * @param px The pixel being compared with this
     * @return whether the two pixels contain the same components
     */
    public boolean sameRGB(Pixel px) {
        return this.distance(px) == 0;
    }

    /**
     * Checks whether this pixel has the same components as the given Object.
     * If the other object is not a Pixel, then the method returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (getClass() == other.getClass()) {
            return this.sameRGB((Pixel) other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 0;
        int[] components = getComponents();

        for (int k = 0; k < components.length; k++) {
            h += k * 255 + components[k];
        }
        return h;
    }

    @Override
    public int compareTo(Pixel o) {
        int rc = getRed() - o.getRed();
        int gc = getGreen() - o.getGreen();
        int bc = getBlue() - o.getBlue();

        if (rc != 0) {
            return rc;
        } else if (gc != 0) {
            return gc;
        } else {
            return bc;
        }
    }
}
