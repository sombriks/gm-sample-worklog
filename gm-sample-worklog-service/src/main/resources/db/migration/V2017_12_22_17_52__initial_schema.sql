
create table user (
  userid integer primary key auto_increment,
  username varchar(255) not null,
  userhash varchar(255) not null,
  userlogin varchar(255) unique not null,
  usercreation datetime not null default now(),
  userlastaccess datetime not null default now()
);

create table worklog (
  worklogid integer primary key auto_increment,
  worklogstart datetime not null default now(),
  worklogfinish datetime, 
  worklogcreation datetime not null default now(),
  userid integer not null,
  foreign key (userid) references user(userid),
  constraint chkwork check (worklogfinish > worklogstart)
);