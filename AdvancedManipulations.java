package org.cis1200;

public class AdvancedManipulations {

    /**
     * Change the contrast of a picture.
     *
     * @param pic        the original picture
     * @param multiplier the factor by which each color component
     *                   of each pixel should be scaled
     * @return the new adjusted picture
     * 
     */
    public static PixelPicture adjustContrast(
            PixelPicture pic, double multiplier
    ) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        int avgIntensity = 0;
        int totalPixels = w * h * 3;
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                avgIntensity += p.getRed();
                avgIntensity += p.getGreen();
                avgIntensity += p.getBlue();
            }
        }
        avgIntensity = avgIntensity / totalPixels;
        int redValue;
        int greenValue;
        int blueValue;
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                redValue = (int) Math.round(
                    (p.getRed() - avgIntensity) * multiplier) + avgIntensity;
                greenValue = (int) Math.round(
                    (p.getGreen() - avgIntensity) * multiplier) + avgIntensity;
                blueValue = (int) Math.round(
                    (p.getBlue() - avgIntensity) * multiplier) + avgIntensity;
                bmp[row][col] = new Pixel(redValue, greenValue, blueValue);
            }
        }
        return new PixelPicture(bmp);
    }

    /**
     * Reduce a picture to its most common colors.
     *
     * @param pic       the original picture
     * @param numColors the maximum number of colors that can be used in the
     *                  reduced picture
     * @return the new reduced picture
     */
    
    public static PixelPicture reducePalette(PixelPicture pic, int numColors) {
        ColorMap m = new ColorMap();
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                if (m.contains(p)) {
                    m.put(p, m.getValue(p) + 1);
                } else {
                    m.put(p, 1);
                }
            }
        }

        Pixel[] sortedPixels = m.getSortedPixels();
        Pixel[] pallete = new Pixel[Math.min(numColors, sortedPixels.length)];
        for (int i = 0; i < pallete.length; i++) {
            pallete[i] = sortedPixels[i];
        }

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {
                Pixel p = bmp[row][col];
                Pixel closestColor = pallete[0];
                int minDist = p.distance(closestColor);
                
                for (int i = 0; i < pallete.length; i++) {
                    Pixel color = pallete[i];
                    int dist = p.distance(color);
                    if (dist < minDist) {
                        minDist = dist;
                        closestColor = color;
                    }
                }

                bmp[row][col] = closestColor;
            }
        }
        
        return new PixelPicture(bmp);
    }

    /**
     * This method blurs an image.
     *
     * @param pic    The picture to be blurred.
     * @param radius The radius of the blurring box.
     * @return A blurred version of the original picture.
     */
    public static PixelPicture blur(PixelPicture pic, int radius) {
        int w = pic.getWidth();
        int h = pic.getHeight();

        Pixel[][] bmp = pic.getBitmap();
        Pixel[][] bmpEmpty = new Pixel[h][w];

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                bmpEmpty[row][col] = average(bmp, col, row, radius);
            }
        }

        return new PixelPicture(bmpEmpty);
    }

    public static Pixel average(Pixel[][] bmp, int col, int row, int radius) {
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int count = 0;

        int w = bmp.length;
        int h = bmp[0].length;

        for (int i = row - radius; i <= row + radius; i++) {
            for (int j = col - radius; j <= col + radius; j++) {
                if (i >= 0 && i < w && j >= 0 && j < h) {
                    Pixel p = bmp[i][j];
                    totalRed += p.getRed();
                    totalBlue += p.getBlue();
                    totalGreen += p.getGreen();
                    count++;
                }
            }
        }

        int avgRed = (int) Math.round((float) totalRed / count);
        int avgBlue = (int) Math.round((float) totalBlue / count);
        int avgGreen = (int) Math.round((float) totalGreen / count);

        return new Pixel(avgRed, avgGreen, avgBlue);
    }
