drop table if exists bus_route_stop;
create table bus_route_stop(
    id int primary key auto_increment,
    number int not null,
    bus_route_id int not null,
    bus_stop_id int not null,
    previous_bus_route_stop_id int,
    minutes_from_start int not null
);

alter table bus_route_stop add foreign key (bus_route_id) references bus_route (id);
alter table bus_route_stop add foreign key (bus_stop_id) references bus_stop (id);
alter table bus_route_stop add foreign key (previous_bus_route_stop_id) references bus_route_stop (id);