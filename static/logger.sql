create table loggers (
id int not null auto_increment,
end_point_name varchar(1000),
event_date datetime,
method varchar(250),
direction varchar(250),
header mediumtext,
body mediumtext,
primary key (id)
)