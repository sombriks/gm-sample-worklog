-- basic data needed before any other information gets produced

insert into logstatus (logstatusid, logstatusdescription) values (1,'OPEN');
insert into logstatus (logstatusid, logstatusdescription) values (2,'FINISHED');
insert into logstatus (logstatusid, logstatusdescription) values (3,'MODIFIED');

insert into eventtype (eventtypeid, eventtypedescription) values (1,'USER_REGISTER');
insert into eventtype (eventtypeid, eventtypedescription) values (2,'USER_LOGIN');
insert into eventtype (eventtypeid, eventtypedescription) values (3,'USER_UPDATE');
insert into eventtype (eventtypeid, eventtypedescription) values (4,'USER_REMOVE');

insert into eventtype (eventtypeid, eventtypedescription) values (5,'WORKLOG_CREATE');
insert into eventtype (eventtypeid, eventtypedescription) values (6,'WORKLOG_CLOSE');
insert into eventtype (eventtypeid, eventtypedescription) values (7,'WORKLOG_MODIFY');
insert into eventtype (eventtypeid, eventtypedescription) values (8,'WORKLOG_REMOVE');
