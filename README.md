### Key things to know:
  - opencv uses BGR not RGB, so converting from RGB to anything is an error
  - HSV is better for maching vision than HSL and esp in our case because we do not want the lumens
  - just looking at a single value (Hue) and comparing it to a single target value may work but its not ideal
  - If you use inRange (blur or not) you should be able to easily identify the rectangle with the object.

### Notes on this example:
* You can load and run this in Android Studio (I hope) - you do not need a bot or anything else
* All input files are in `/input` and all output files are in `/output`.
* Each example has its own method
* Basic example method is the only one uncommented
* Each run is against an input image with no pink and one with our current cone shaped pink TSE

### Basic Example
This example does the following:

* converts the image to HSV
* calculates the mean of the pixels
* outputs the converted image
* outputs the mean values - first value in array is the Hue (color) value
* 145 to 155 is a rough range for pink

You can see that the Hue value is less than half of that because there is a lot of non-pink in the entire image.

###InRange Example
HSV is a color range, each color has a wide range within HSV and comparing JUST the hue to a single target value narrows your chances of recognition.  You want to use the inRange methods as described in these articles.

- [https://opencv-java-tutorials.readthedocs.io/en/latest/08-object-detection.html](https://opencv-java-tutorials.readthedocs.io/en/latest/08-object-detection.html)
- [https://pysource.com/2019/02/15/detecting-colors-hsv-color-space-opencv-with-python/](https://pysource.com/2019/02/15/detecting-colors-hsv-color-space-opencv-with-python/)
- [https://www.educba.com/opencv-hsv-range/](https://www.educba.com/opencv-hsv-range/)

This example does the following:

* everything basic does
* uses the defined values to find all pixels in our “range”, generates a mat where pixels in range are 1 (white) and not in our range are 0 (black)
* check out the generated image from this, its pretty cool
* does a non zero count on the resulting mat and outputs it

You can see the results of running this against an item with pink and not, the non-zero count is really high with our TSE

### Blur Example
It was suggested to blur the image before processing so we repeat the inRange example blurring it first.  The values look better but after looking at the generated blur images I am not sure if we want to use this.


What is NOT in these examples

* its looking at the whole picture, you should do the same technique on each of your boxes
* logic for determining which box has the element

