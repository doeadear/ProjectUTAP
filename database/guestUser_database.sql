
create table guestUser
(
g_uid varchar(30) not null,
email varchar(30),
m_AvailableColor int,
m_BusyColor int,
m_UnavailableColor int,
m_MaxLotDistance double,
m_MaxWaitTime decimal(3,1),
notification varchar(20),
primary key(g_uid)
);


create table schedules
(
g_uid varchar(30) not null,
lot_id varchar(30),
user_feedback varchar(20),
primary key(g_uid,lot_id),
foreign key (g_uid) references user(g_uid),
foreign key (lot_id) references lots(lot_id)
);



create table lots
(
lot_id int not null,
location varchar(30),
status varchar (20),
facultyOnly varchar(5),
primary key (lot_id)

);


ALTER TABLE lots 
ADD CONSTRAINT status_check
CHECK (status IN ('UNAVAILABLE', 'BUSY','AVAILABLE'));

ALTER TABLE guestUser 
ADD CONSTRAINT notification_check
CHECK (notification IN ('ENABLED','DISABLED));