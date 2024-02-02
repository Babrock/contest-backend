insert into utils(id_utils, is_schools_downloaded) values (1, false);

insert into titles (name)
values
    ('dr'),
    ('inż'),
    ('mgr inż'),
    ('lic'),
    ('mgr');

insert into languages (name)
values
    ('angielski'),
    ('francuski'),
    ('hiszpański'),
    ('niemiecki'),
    ('włoski');

insert into type_of_schools (name)
values
    ('Podstawowa Klasy V'),
    ('Podstawowa Klasy VI'),
    ('Podstawowa Klasy VIII'),
    ('Licea Klasy I'),
    ('Technika Klasy I');

insert into roles (name)
values
    ('ROLE_ADMIN'),
    ('ROLE_COORDINATOR'),
    ('ROLE_COORDINATOR_REGION'),
    ('ROLE_COORDINATOR_SCHOOL');

LOAD DATA INFILE '/var/lib/mysql-files/voivodeships.csv'
INTO TABLE voivodeships
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/var/lib/mysql-files/counties.csv'
INTO TABLE counties
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(id_county, name, id_voivodeship);

LOAD DATA INFILE '/var/lib/mysql-files/communities.csv'
INTO TABLE communities
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/var/lib/mysql-files/cities.csv'
INTO TABLE cities
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE '/var/lib/mysql-files/categories.csv'
INTO TABLE categories
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

# LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/contestTables/regions.csv'
# INTO TABLE regions
# FIELDS TERMINATED BY ','
# ENCLOSED BY '"'
# LINES TERMINATED BY '\n'
# IGNORE 1 LINES;

-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/contestTables/schools.csv'
-- INTO TABLE schools
-- FIELDS TERMINATED BY ','
-- ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 rows
-- (id_school, name, id_city, street, address, apartment_number, zip_code, post, phone, email, id_headmaster, 	@id_category, school_complex, @id_type_of_school)
-- set
-- 	id_category = nullif (NULLIF(@id_category, ''),0),
-- 	id_type_of_school = nullif (NULLIF(@id_type_of_school, ''),0);

# SHOW VARIABLES LIKE "secure_file_priv"; -- wskasuje miejsce gdzie powinny być tabele do wczytania




