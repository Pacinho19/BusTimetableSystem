drop table if exists bus_route;
create table bus_route(
    id int primary key auto_increment,
    distance int not null,
    summary_time int not null
);