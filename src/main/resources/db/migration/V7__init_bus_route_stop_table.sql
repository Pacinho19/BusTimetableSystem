drop table if exists bus_route_stop;
create table bus_route_stop(
    id int primary key auto_increment,
    number int not null,
    bus_route_id int not null,
    bus_stop_id int not null,
    arrival_time time not null,
    departure_time time
);

alter table bus_route_stop add foreign key (bus_route_id) references bus_route (id);
alter table bus_route_stop add foreign key (bus_stop_id) references bus_stop (id);