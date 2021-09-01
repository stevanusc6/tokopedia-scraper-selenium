# Tokopedia Scraper Handphone and Tablet

## Requirement
* jdk 1.8
* Maven
* Chrome Driver (https://chromedriver.chromium.org/downloads) 
current version I used : 92.0.4515.107
  
## Project Build
```mvn clean install```

If any error during install re-download chrome driver and put in the same directory as project.

## JAR Build
```mvn clean compile assembly:single```

## How to run it
1. Copy chromedriver to target folder
2. run java -jar tokopedia-1.0-SNAPSHOT-jar-with-dependencies.jar
3. result file will be named result.txt
