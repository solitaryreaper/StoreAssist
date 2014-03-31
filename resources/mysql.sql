use storeassist;



create table item_location

(

    item_id long,

    store_id long,

    location_id long

);



create table location

(

    id long,

    section_id int,

    aisle_shelf_id int

);



create table section

(

    id int not null,

    name varchar(255) not null

);



create table aisle_shelf

(

    id int not null,

    aisle_name varchar(255) not null,

    shelf_name varchar(255) not null

);



create table store

(

    id long not null,

    name varchar(255) not null,

    address varchar(1000) not null,

    zip int not null,

    city varchar(255) not null

);



create table item_info

(

    item_id long not null,

    store_id long not null,

    name varchar(255) not null,

    price float not null,

    quantity int not null,

    brand varchar(255)

);



-- Populate data here

insert into store values (1,"Fresh Madison Market", "703 University Avenue", 53715, "Madison");

insert into item_info values(1,1,"McClure's Garlic Dill Pickles", 15.99, 12, "McClure");

insert into item_location values(1,1,1);

insert into location values(1,1,1);

insert into section values(1, "Pickles");

insert into aisle_shelf values(1, "A2", "2");
