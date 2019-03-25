# football-league

A command line java application that generates a football league table based on a file of result inputs.

## build
     ./gradlew build -i 

## test
     ./gradlew test -i 
     
## run with gradle
    ./gradlew run --args 'src/test/resources/input-sample.txt'
     

## run with java
    ./gradlew build
    java -jar build/libs/football-league-0.1.0.jar src/test/resources/input-sample.txt
