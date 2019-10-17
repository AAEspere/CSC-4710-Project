Aaron Espere - gb1962
I'm working on this project by myself.

To run, import project (either through WAR method or zip method). First, check the Modulepath. I have been switching between running this on my home PC and my Macbook, so its current Modulepath may not allow it to compile.

Check build path of jar files, I've been getting an error where the jdbc connector cannot be found, and making sure the build path is correct seems to remedy it some of the time. 

Then you just have to right click the project and click Run as > run on server > Apache Tomcat ____ > Finish.
It will bring you to index.jsp, where you should be able to run the operations. 