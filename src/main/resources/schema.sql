drop database contest;

create database if not exists contest;

use contest;

create table if not exists utils
(
    id_utils              int primary key,
    is_schools_downloaded bool
);
insert into utils(id_utils, is_schools_downloaded) values (1, false);

create table if not exists voivodeships
(
    id_voivodeship int auto_increment primary key,
    name           varchar(100) unique not null
);

create table if not exists regions
(
    id_region int auto_increment primary key,
    name      varchar(100) not null
);

create table if not exists counties
(
    id_county      int auto_increment primary key,
    name           varchar(100) not null,
    id_voivodeship int          not null,
    id_region      int,
    foreign key (id_voivodeship) references voivodeships (id_voivodeship),
    foreign key (id_region) references regions (id_region)
);

create table if not exists communities
(
    id_community int auto_increment primary key,
    name         varchar(100) not null,
    id_county    int          not null,
    foreign key (id_county) references counties (id_county)
);

create table if not exists cities
(
    id_city      int auto_increment primary key,
    name         varchar(100) not null,
    id_community int          not null,
    foreign key (id_community) references communities (id_community)
);


create table if not exists categories
(
    id_category int auto_increment primary key,
    name        varchar(100) unique not null
);



create table if not exists roles
(
    id_role int auto_increment primary key,
    name    varchar(50) unique not null
);

create table if not exists languages
(
    id_language int auto_increment primary key,
    name        varchar(50) unique not null
);

create table if not exists titles
(
    id_title int auto_increment primary key,
    name     varchar(50) unique not null
);

create table if not exists type_of_schools
(
    id_type_of_school int auto_increment primary key,
    name              varchar(50) unique not null
);

create table if not exists users
(
    id_user       int auto_increment primary key,
    firstname     varchar(50)         not null,
    lastname      varchar(50)         not null,
    email         varchar(255) UNIQUE not null,
    `password`    varchar(255)        not null,
    phone         varchar(50) unique  not null,
    wants_to_rate bool,
    enabled       bool,
    id_title      int,
    id_role       int,
    foreign key (id_title) references titles (id_title),
    foreign key (id_role) references roles (id_role)
);

CREATE TABLE IF NOT EXISTS verification_tokens
(
    id_verification_token INT NOT NULL auto_increment PRIMARY KEY,
    id_user               INT,
    token                 VARCHAR(255),
    expiry_date           TIMESTAMP,
    foreign key (id_user) references users (id_user)
);

create table if not exists schools
(
    id_school            int auto_increment primary key,
    name                 varchar(255),
    voivodeship          varchar(255),
    county               varchar(255),
    community            varchar(255),
    city                 varchar(255),
    street               varchar(255),
    address              varchar(255),
    apartment_number     varchar(150),
    zip_code             varchar(255),
    post                 varchar(255),
    phone                varchar(255),
    email                varchar(255),
    headmaster_full_name varchar(255)
);

create table if not exists school_details
(
    id_school_detail     int auto_increment primary key,
    school_complex       varchar(255),
    id_category          int,
    id_type_of_school    int,
    id_title             int,
    id_region            int,
    headmaster_firstname varchar(255),
    headmaster_lastname  varchar(255),
    headmaster_email     varchar(255),
    foreign key (id_category) references categories (id_category),
    foreign key (id_type_of_school) references type_of_schools (id_type_of_school),
    foreign key (id_title) references titles (id_title),
    foreign key (id_region) references regions (id_region)
);

create table if not exists forms
(
    id_form          int auto_increment PRIMARY KEY,
    upload_date      TIMESTAMP,
    accepted_date    TIMESTAMP DEFAULT NULL,
    is_accepted      bool,
    id_user          int not null,
    id_school        int not null,
    id_school_detail int not null,
    foreign key (id_user) references users (id_user),
    foreign key (id_school) references schools (id_school),
    foreign key (id_school_detail) references school_details (id_school_detail)
);

ALTER TABLE forms
    AUTO_INCREMENT = 1000;

create table if not exists schools_classes
(
    id_school_class int auto_increment primary key,
    name            varchar(50) not null,
    id_title        int,
    firstname       varchar(50) not null,
    lastname        varchar(50) not null,
    students        int         not null,
    id_school       int         not null,
    id_language     int         not null,
    id_form         int         not null,
    foreign key (id_title) references titles (id_title),
    foreign key (id_school) references schools (id_school),
    foreign key (id_language) references languages (id_language),
    foreign key (id_form) references forms (id_form)
);

CREATE TABLE IF NOT EXISTS tasks
(
    id_task         int auto_increment primary key,
    name            varchar(50)   not null,
    score           decimal(4, 2) not null,
    id_school_class int           not null,
    foreign key (id_school_class) references schools_classes (id_school_class)
);
