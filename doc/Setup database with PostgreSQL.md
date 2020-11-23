# Setup database with PostgreSQL

For the first time, you need to follow every step. After that, you'll only need to run scripts from [Database setup](#database-setup).



## User setup

You need to create a user (`adm`) to interact with the database.  We assume that you know how to run script to PostgreSQL engine through the shell or the IDE.

Run the script `User Setup.sql` while being connected to Postgres database.

## Database setup

The creation of the database is separated in two scripts, because they will not be executed in the same database.

### Database in itself

Run the script `DB create.sql` while being connected to Postgres database.

### DDL

Run the script `JEE db.sql` while being connected to st2eedb database.

### Populate

Run the script `Populate.sql` while being connected to st2eedb database.
