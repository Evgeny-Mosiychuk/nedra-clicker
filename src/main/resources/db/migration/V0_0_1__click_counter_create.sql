create table if not exists click_counter (id  serial not null, clicks_count int8, primary key (id));

insert into click_counter(id, clicks_count) values (1, 0);
commit;