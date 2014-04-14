# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table search_log_new (
  id                        bigint auto_increment not null,
  item_search_string        varchar(255),
  store_id                  integer,
  search_time               datetime,
  constraint pk_search_log_new primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table search_log_new;

SET FOREIGN_KEY_CHECKS=1;

