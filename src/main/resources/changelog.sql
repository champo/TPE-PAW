alter table users alter column password type char(64);
--Adding missing columns to properties table 
alter table properties add address varchar(50);
alter table properties add antiquity smallint;
--Renaming sold column to much useful and semantically correct column published
alter table properties rename sold to published;
update properties set published = not (select published from properties a where a.id = properties.id);