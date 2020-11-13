#!/bin/sh
for f in scripts/*; 
do 
	docker cp $f pg-docker:/home/; 
done
echo "Please, restart the database before all"
docker exec -it -w /home pg-docker ./run.sh;