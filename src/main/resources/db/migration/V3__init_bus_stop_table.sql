drop table if exists bus_stop;
create table bus_stop(
    id int primary key auto_increment,
    name varchar(50) not null,
    address varchar(100) not null
);