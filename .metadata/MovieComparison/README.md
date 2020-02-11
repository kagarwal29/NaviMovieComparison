### Project Title
Navi - Movie Comparison Automation Project

###Prerequisites
Project is a maven project. Have maven configured on your system if you want to run via command line. Otherwise, just import the project in Eclipse or Intellij. 

###Running Automation
1. Open the project.
2.  Select root project folder, and 'Run-As' maven build.
3.  Output of the automation will be printed in console.
4. You can also see the testNG report under ./target/surefire-reports/index.html

###Input
Director name provided under 'DirectorMovieComparisonDataProvider' class.

###Output
1. List and count of movies common between two Website i.e IMDB and LetterBoxD
2. List and count of movies present only on IMDB
3. List and count of movies present only on LetterBoxD
4. List and count of movies whose title is same but year of release is different.

###Website Under Test
IMDB and LETTERBOXD

###Versioning
Available on Github: <https://github.com/kagarwal29/NaviMovieComparison>

###Browsers Supported
Chrome, Firefox

###Author
Kapil Agarwal
