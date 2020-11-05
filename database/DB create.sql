-- You need to be connected to postgres (the DB) to execute this part
drop database IF EXISTS st2eedb ;
CREATE DATABASE st2eedb OWNER adm ENCODING DEFAULT CONNECTION LIMIT -1;


GRANT ALL PRIVILEGES ON DATABASE st2eedb TO adm;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to adm;