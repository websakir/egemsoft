create table loggers (
id int not null auto_increment,
end_point_name varchar(1000),
event_date datetime,
method varchar(250),
direction varchar(250),
header varchar(2000),
body varchar(2000),
primary key (id)
)