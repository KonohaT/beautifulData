This was our final project for Computer Science II (CP222) at Colorado College. 

This is a machine learning visualizer that displays the neural network of a simple, digit classifying neural network. The dataset was from the MNIST digit classifier data set, the ML backend was made in python, and the visualizing frontend was made in Java. We trained the digit classifier in python using 10 epochs with 64 inputs representing each pixel of an 8x8 digit image, 1 hidden layer with 50 neurons, and 10 outputs representing the numbers 0-9 the model predicts. The backend would send its weight and neuron values via CSV file to the frontend, where Java would scrape it. 

Here are the visuals created from this project:


Blue lines represent positive weights, and orange lines represent negative weights. The thicker the line, the closer the absolute weight value is to 1. 

![success](https://user-images.githubusercontent.com/71115970/221233290-85bde198-41b0-406b-83e9-502de676b1e9.gif)




![final](https://user-images.githubusercontent.com/71115970/221233336-9f9a5fae-92b0-445e-bc09-eada6d42bcec.png)



![SkyNet](https://user-images.githubusercontent.com/71115970/221233385-4a89ff0b-3d51-43b1-8307-77203cfd5ab2.png)




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

