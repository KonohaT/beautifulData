Setup Notes: 
Processing can be installed using Maven, as org.processing:core.3.3.7
Some versions of macOS apparently have issues with Processing, due to changes in Java 8. Windows and Linux seem to work fine. 

The Python end requires installation of NumPy and SciPy

The ML model requires training data which is too big for GitHub's size limit, so it's linked here and in a comment in main.py. Place it into the NetworkData folder inside of src (don't ask...).
https://drive.google.com/drive/folders/18ghm4khtX-jcQIHugLnb-xFa9-jxOTjy?usp=share_link


Use: 
First run Main.py in src/handwritten to generate the weight epoch files. Then run main.java in src/processing to generate the visualization. 
If there are already epoch files, delete them before starting. 

If interested, the ProcessingTest files show the progression of the code...

To generate images of each frame, uncomment line 104 in ProcessingSuccess. ImageMagick can then be used to combine them into a gif like the one shown. 

