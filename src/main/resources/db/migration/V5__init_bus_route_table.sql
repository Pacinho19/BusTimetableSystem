drop table if exists bus_route;
create table bus_route(
    id int primary key auto_increment,
    bus_id int not null,
    initial_bus_stop int not null,
    final_bus_stop int not null
);

alter table bus_route add foreign key (bus_id) references bus (id);
alter table bus_route add foreign key (initial_bus_stop) references bus_stop (id);
alter table bus_route add foreign key (final_bus_stop) references bus_stop (id);