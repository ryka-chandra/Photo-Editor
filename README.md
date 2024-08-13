# Photo-Editor

## Overview

This project is a fully functional photo manipulation application built in Java. The application allows users to perform various image manipulations, such as rotating, adding borders, inverting colors, applying grayscale, adjusting contrast, reducing the color palette, and more. The application is divided into different components that handle various aspects of image processing, user interaction, and testing.

## Features

### Core Components
1. **Pixel.java**: 
   - Represents a single point of color in an image.
   - Implements constructors and methods such as `getRed()`, `getGreen()`, `getBlue()`, `getComponents()`, `distance()`, `toString()`, and `equals()`.

2. **PixelPicture.java**:
   - Manages the reading and writing of image data.
   - Provides a structured way to handle images as collections of `Pixel` objects.

3. **ColorMap.java**:
   - A map data structure that helps build histograms of color usage within an image.
   - Useful for tasks like reducing the color palette.

4. **PointQueue.java**:
   - Manages queues of integers, primarily used in advanced manipulations such as the flood fill algorithm.

5. **SimpleManipulations.java**:
   - Implements basic image manipulations:
     - `rotateCCW()`: Rotates the image counterclockwise.
     - `border()`: Adds a border around the image.
     - `invertColors()`: Inverts the colors of the image.
     - `grayScaleAverage()`: Converts the image to grayscale using the average method.
     - `scaleColors()`: Scales the colors of the image.
     - `alphaBlend()`: Blends the image with another using alpha transparency.

6. **AdvancedManipulations.java**:
   - Implements more complex image manipulations:
     - `adjustContrast()`: Adjusts the contrast of the image.
     - `reducePalette()`: Reduces the color palette of the image.
     - `blur()`: Applies a blurring effect to the image by averaging neighboring pixels.

7. **Effects.java**:
   - Implements advanced photo effects by combining and building on basic manipulations. These effects are designed to enhance the image and add unique visual styles.

### User Interface
1. **GUI.java**:
   - Provides a simple graphical user interface for the application.
   - Allows users to load, manipulate, and save images with ease.

### Testing
1. **ManipulateTest.java**:
   - Contains JUnit tests for the image manipulation methods, ensuring that all manipulations produce the expected results.

2. **MyPixelTest.java**:
   - Includes tests for the `Pixel` class, validating the behavior of its methods.

3. **ImageTest.java**:
   - Additional space for custom JUnit tests to ensure the robustness of the image manipulations.

## Conclusion

This project demonstrates a comprehensive approach to image manipulation in Java, utilizing object-oriented principles, advanced data structures, and GUI programming. The application is flexible, allowing for both basic and advanced manipulations, making it a valuable tool for photo editing and processing.
