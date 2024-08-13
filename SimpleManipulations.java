package org.cis1200;


public class SimpleManipulations {

    /**
     * Rotate a picture 90 degrees clockwise.
     *
     * For example, consider this bitmap, where each pixel is labeled by its
     * coordinates:
     *
     * (0, 0) (0, 1) (0, 2)
     * (1, 0) (1, 1) (1, 2)
     *
     * Rotating this bitmap CW will produce the following bitmap, with relabeled
     * coordinates:
     *
     * (1, 0) (0, 0)
     * (1, 1) (0, 1)
     * (1, 2) (0, 2)
     *
     * This method implements this "relabeling," copying pixels from their
     * old coordinates to their new coordinates.
     *
     * @param pic The original picture to rotate.
     * @return The rotated picture.
     */
    public static PixelPicture rotateCW(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] src = pic.getBitmap();
        Pixel[][] tgt = new Pixel[w][h]; // swap coordinates

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                tgt[col][h - row - 1] = src[row][col]; // swap coordinates
            }
        }

        return new PixelPicture(tgt);
    }

    /**
     * Rotate a picture 90 degrees counter-clockwise.
     *
     * For example, consider this bitmap, where each pixel is labeled by its
     * coordinates:
     *
     * (0, 0) (0, 1) (0, 2)
     * (1, 0) (1, 1) (1, 2)
     *
     * Rotating this bitmap CCW will produce the following bitmap, with
     * relabeled coordinates:
     *
     * (0, 2) (1, 2)
     * (0, 1) (1, 1)
     * (0, 0) (1, 0)
     *
     *
     * @param pic The original picture to rotate.
     * @return The rotated picture.
     */
    public static PixelPicture rotateCCW(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] src = pic.getBitmap();
        Pixel[][] tgt = new Pixel[w][h]; // swap coordinates

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                tgt[w - col - 1][row] = src[row][col]; // swap coordinates
            }
        }

        return new PixelPicture(tgt);
    }

    /**
     * Create a new image by adding a border to a specified image.
     *
     * @param pic         the original picture
     * @param borderWidth number of pixels in the border
     * @param borderColor color of the border.
     * @return a copy of the input picture with a border
     */
    public static PixelPicture border(
            PixelPicture pic, int borderWidth, Pixel borderColor
    ) { 
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] src = pic.getBitmap();
        Pixel[][] tgt = new Pixel[h + 2 * borderWidth][w + 2 * borderWidth];

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                tgt[row + borderWidth][col + borderWidth] = src[row][col];
            }
        }

        // top edge
        for (int col = 0; col < w + 2 * borderWidth; col++) {
            for (int row = 0; row < borderWidth; row++) {
                tgt[row][col] = borderColor;
            }
        }

        // left edge
        for (int col = 0; col < borderWidth; col++) {
            for (int row = 0; row < h + 2 * borderWidth; row++) {
                tgt[row][col] = borderColor;
            }
        }

        // right edge
        for (int col = w + borderWidth; col < w + 2 * borderWidth; col++) {
            for (int row = 0; row < h + 2 * borderWidth; row++) {
                tgt[row][col] = borderColor;
            }
        }

        // bottom edge
        for (int col = 0; col < w + 2 * borderWidth; col++) {
            for (int row = h + borderWidth; row < h + 2 * borderWidth; row++) {
                tgt[row][col] = borderColor;
            }
        }

        return new PixelPicture(tgt);
    }

    /**
     * Transforms a picture to its GrayScale equivalent using the luminosity
     * algorithm.
     *
     *
     * @param pic the original picture
     * @return a new picture that is the GrayScale equivalent of the original
     *         picture
     */
    public static PixelPicture grayScaleLuminosity(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();
                int avg = (int) Math.round(0.299 * r + 0.587 * g + 0.114 * b);
                bmp[row][col] = new Pixel(avg, avg, avg);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Create a new image by inverting the color of each pixel.
     *
     *
     * @param pic the picture to be inverted
     * @return new picture with inverted colors
     */
    public static PixelPicture invertColors(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();
                bmp[row][col] = new Pixel(255 - r, 255 - g, 255 - b);
            }
        }
        return new PixelPicture(bmp);

    }

    /**
     * Transform a colored picture to its grayscale equivalent using an averaging
     * algorithm.
     *
     * @param pic the original picture
     * @return new grayscale image
     */

    public static PixelPicture grayScaleAverage(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();
                int avg = (int) Math.round((r + g + b) / 3.0);
                bmp[row][col] = new Pixel(avg, avg, avg);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Scale the color components of a picture.
     *
     * @param pic     original image
     * @param rfactor red factor
     * @param gfactor green factor
     * @param bfactor blue factor
     * @return new image with scaled colors
     */
    public static PixelPicture scaleColors(
            PixelPicture pic, double rfactor, double gfactor, double bfactor
    ) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();
                bmp[row][col] = new Pixel((int) Math.round(
                    (r * rfactor)), (int) Math.round(
                        (g * gfactor)), (int) Math.round((b * bfactor)));
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Compute the weighted average of two integers.
     *
     * @param alpha weight
     * @param x     first integer
     * @param y     second integer
     * @return weighted average of x and y
     */
    public static int weightedAverage(double alpha, int x, int y) {
        return (int) Math.round(x * alpha + y * (1 - alpha));
    }

    /**
     * Blend two pictures together by taking a weighted average of each pixel.
     *
     * @param alpha weight
     * @param pic   first picture
     * @param f     second picture
     *
     * @return the blended image
     */
    public static PixelPicture alphaBlend(
            double alpha, PixelPicture pic, PixelPicture f
    ) {
        int wp = pic.getWidth();
        int hp = pic.getHeight();
        int wf = f.getWidth();
        int hf = f.getHeight();
        if (wp != wf || hp != hf) {
            return pic;
        }
        
        Pixel[][] fst = pic.getBitmap();
        Pixel[][] snd = f.getBitmap();

        for (int col = 0; col < wp; col++) {
            for (int row = 0; row < hp; row++) {
                Pixel p = fst[row][col];
                int r1 = p.getRed();
                int g1 = p.getGreen();
                int b1 = p.getBlue();
                Pixel p1 = snd[row][col];
                int r2 = p1.getRed();
                int g2 = p1.getGreen();
                int b2 = p1.getBlue();
                fst[row][col] = new Pixel(weightedAverage(
                    alpha, r1, r2), weightedAverage(
                        alpha, g1, g2), weightedAverage(
                            alpha, b1, b2));
            }
        }
        return new PixelPicture(fst);
    }

    /**
     * Adds dark edges to an image to draw interest to the center.
     *
     *
     * @param pic original image
     * @return new image with with dark edges
     */
    public static PixelPicture vignette(PixelPicture pic) {
        int w = pic.getWidth();
        int h = pic.getHeight();
        double cx = (w - 1) / 2.0;
        double cy = (h - 1) / 2.0; // cx, cy is center pixel in the image

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                double dx = (double) (col - cx);
                double dy = (double) (row - cy);

                double r = Math.sqrt(cx * cx + cy * cy);
                // check for division by zero
                if (r == 0) {
                    return pic;
                }

                double d = Math.sqrt((dx * dx) + (dy * dy)) / r;
                double factor = 1.0 - d * d;

                bmp[row][col] = new Pixel(
                        (int) Math.round(bmp[row][col].getRed() * factor),
                        (int) Math.round(bmp[row][col].getGreen() * factor),
                        (int) Math.round(bmp[row][col].getBlue() * factor)
                );

            }
        }
        return new PixelPicture(bmp);
    }
}
