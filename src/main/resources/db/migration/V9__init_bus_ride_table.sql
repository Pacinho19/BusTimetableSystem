drop table if exists bus_ride;
create table bus_ride(
    id int primary key auto_increment,
    bus_id int not null,
    bus_route_id int not null,
    arrive_time time not null
);


alter table bus_ride add foreign key (bus_id) references bus (id);
alter table bus_ride add foreign key (bus_route_id) references bus_route(id);