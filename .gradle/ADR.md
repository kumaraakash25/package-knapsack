Using widely used opencsv dependency for reading the CSV files 
Did not use the CSV mapping to bean directly because there is no header 

Reading the whole file first so that we can fail fast 