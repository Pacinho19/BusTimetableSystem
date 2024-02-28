drop table if exists bus_route;
create table bus_route(
    id int primary key auto_increment,
    bus_id int not null,
    distance int not null,
    summary_time int not null
);

alter table bus_route add foreign key (bus_id) references bus (id);