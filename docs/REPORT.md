# TASK REPORT
## GENERAL REQUIREMENTS:
### 1. The BD screenshots
Main weather table:
<p align="left">
    <img src="images/items-to-store.png" width="1000px" alt="items-to-store">
</p>

Duplicates weather table:
<p align="left">
    <img src="images/table-for-duplicates.png" width="1000px" alt="table-for-duplicates">
</p>
Ivr table:
<p align="left">
    <img src="images/db-country-mapping.png" width="1000px" alt="db-country-mapping">
</p>

### 2. The CSV file screenshot with duplicates
<p align="left">
    <img src="images/duplicates-in-csv-file.png" width="1000px" alt="duplicates-in-csv-file">
</p>

### 3.Pipeline processing
Stream-Sets record processing
<p align="left">
    <img src="images/streams-sets-processing.gif" width="1000px" alt="stream-sets-processing">
</p>
Stream-Sets API response after record processing
<p align="left">
    <img src="images/stream-sets-response.gif" width="1000px" alt="stream-sets-response">
</p>

### 4.The access to pipeline json(s)
[Pipeline Json with Response](https://github.com/klindziukp/sas-data/blob/master/docs/stream-sets/sas-ta-weather-pipeline.json) <br>
[Pipeline Json with DB Mapping](https://github.com/klindziukp/sas-data/blob/master/docs/stream-sets/sas-ta-weather-pipeline-with-cm.json)

## ADDITIONAL REQUIREMENTS:
### 1.Only the following fields have to be stored: wind_speed, description, weather, temperature, feels_like, humidity, city, country
<p align="left">
    <img src="images/items-to-store.png" width="1000px" alt="items-to-store">
</p>

### 2.Duplicates have to be stored in a separate CSV file on a local hard drive
<p align="left">
    <img src="images/duplicates-in-csv-file.png" width="1000px" alt="duplicates-in-csv-file">
</p>

### 3.All temperature values should be stored in Celsius
<p align="left">
    <img src="images/temperature-mapper.png" width="1000px" alt="temperature-mapper">
</p>
<p align="left">
    <img src="images/items-to-store.png" width="1000px" alt="items-to-store">
</p>

### 4.Duplicates have to be stored in separate table with soft delete flag
<p align="left">
    <img src="images/table-for-duplicates.png" width="1000px" alt="table-for-duplicates">
</p>
__deletedAt__ and __deletedBy__ fields used as flags for soft deletion, SQL query should be executed with/without filtering by this fields

### 5.Once a day the table have to be cleaned.
<p align="left">
    <img src="images/database-cleanup.png" width="1000px" alt="database-cleanup">
</p>

### 6.The country name has to be in full name. The additional column with IVR code has to be added
<p align="left">
    <img src="images/jdbc-producer-for-country-mapping.png" width="1000px" alt="jdbc-country-mapping">
</p>
<p align="left">
    <img src="images/db-country-mapping.png" width="1000px" alt="db-country-mapping">
</p>
<p align="left">
    <img src="images/items-to-store.png" width="1000px" alt="items-to-store">
</p>