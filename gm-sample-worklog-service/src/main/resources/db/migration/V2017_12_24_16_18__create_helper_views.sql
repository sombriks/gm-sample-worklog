-- this is a good example on how to not expose sensitive data by having different models for different needs

create view 
  vwuser 
as select
  userid,
  username,
  userlogin,
  usercreation
from 
  user;
