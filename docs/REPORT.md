# TASK REPORT
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
    <img src="images/items-to-store.png" width="1000px" alt="items-to-store">
</p>

### 4.Duplicates have to be stored in separate table with soft delete flag
<p align="left">
    <img src="images/table-for-duplicates.png" width="1000px" alt="table-for-duplicates">
</p>
*deletedAt* and *deletedBy* fields used as flags for soft deletion, SQL query should be executed with/without filtering by this fields

### 5.Once a day the table have to be cleaned.
<p align="left">
    <img src="images/database-cleanup.png" width="1000px" alt="database-cleanup">
</p>