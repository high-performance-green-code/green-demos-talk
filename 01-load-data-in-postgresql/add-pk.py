i = 0

with open('/app/csv-data/yellow_tripdata_2022-01.csv', 'r') as source_file:
    with open('/app/csv-data/yellow_tripdata_with_pk_2022-01.csv', 'w') as file_with_pk:
        for l in source_file:
            line_with_id = ''
            if i == 0:
                line_with_id = 'id|' + l.strip() + '\n'
            else:
                line_with_id = str(i) + '|' + l.strip() + '\n'
            
            file_with_pk.write(line_with_id)
            i += 1

print("Finished")