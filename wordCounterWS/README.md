# ReadmeSample

## Info

WordCounter sample project

## APIs

### Text

API to make a RandomText API request to generate dummy text. 

Accepted parameters:
- start - indicates the start number of paragraphs
- end - indicates the end number of paragraphs
- countMin - indicates min number of words in each paragraph
- countMax - indicates max number of words in each paragraph

Response:
- frequentWord - the word that was the most frequent in the paragraphs 
- apSize - the average size of a paragraph 
- appTime - the average time spent analyzing a paragraph
- tpTime - total processing time to generate the final response

Example: http://localhost:8082/wordCounter/text?start=5&end=10&countMin=2&countMax=30

### History

API to obtain last 10 computation results

Example: http://localhost:8082/wordCounter/history

## Execution

### As Eclipse Project

You can import inside eclipse and run as 'Java Application' the class 'Application' 

### As jar

You can generate a jar with maven and the command: mvn clean package

And start the project with the java command: java -jar target/text-0.0.1-SNAPSHOT-*.jar