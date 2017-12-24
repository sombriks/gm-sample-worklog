-- initial structure

create table user (
  userid integer primary key auto_increment,
  username varchar(255) not null,
  userhash varchar(255) not null,
  userlogin varchar(255) unique not null,
  usercreation datetime not null default now()
);

create table logstatus (
  logstatusid integer primary key auto_increment,
  logstatusdescription varchar(255) not null,
);

create table worklog (
  worklogid integer primary key auto_increment,
  worklogstart datetime not null default now(),
  worklogfinish datetime, 
  worklogcreation datetime not null default now(),
  logstatusid integer not null default 1,
  userid integer not null,

  foreign key (logstatusid) references logstatus(logstatusid),
  foreign key (userid) references user(userid) on delete cascade,

  constraint checkwork check (worklogfinish > worklogstart)
);

create table eventtype (
  eventtypeid integer primary key auto_increment,
  eventtypedescription varchar(255) not null,
);

create table eventlog (
  eventlogid integer primary key auto_increment,
  eventlogcreation datetime not null default now(),
  userid integer not null,
  eventtypeid integer not null,
  eventlogdescription varchar(255) not null,
  
  foreign key (userid) references user(userid) on delete cascade,
  foreign key (eventtypeid) references eventtype(eventtypeid)
);
