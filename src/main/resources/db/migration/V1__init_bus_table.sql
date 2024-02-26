drop table if exists bus;
create table bus(
    id int primary key auto_increment,
    number int not null,
    number_of_seats int not null,
    number_of_standing_seats int not null
);