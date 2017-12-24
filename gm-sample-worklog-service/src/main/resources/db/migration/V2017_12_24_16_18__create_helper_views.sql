
create view 
  vwuser 
as select
  userid,
  username,
  userlogin,
  usercreation
from 
  user;
