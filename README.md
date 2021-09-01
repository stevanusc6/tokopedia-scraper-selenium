# Tokopedia Scraper Handphone and Tablet

## Requirement
* jdk 1.8
* Maven
* Chrome Driver (https://chromedriver.chromium.org/downloads) 
current version I used : 92.0.4515.107

## First Things
Download chrome driver suiteable your os.
and put in the same directory as project.
  
## Project Build
```mvn clean install```

## JAR Build
```mvn clean compile assembly:single```

## How to run it
1. Copy chromedriver to target folder
2. run java -jar tokopedia-1.0-SNAPSHOT-jar-with-dependencies.jar
3. result file will be named result.txt

Successfuly build using MacOS Big Sur
