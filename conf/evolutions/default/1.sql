# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table basket (
  id                            bigint not null,
  customer_email                varchar(255),
  constraint uq_basket_customer_email unique (customer_email),
  constraint pk_basket primary key (id)
);
create sequence basket_seq;

create table flight_schedule (
  flight_id                     bigint not null,
  destination                   varchar(255),
  dest_city                     varchar(255),
  start                         varchar(255),
  departure_date                varchar(255),
  seats                         integer,
  price                         double,
  constraint pk_flight_schedule primary key (flight_id)
);
create sequence flight_schedule_seq;

create table order_item (
  id                            bigint not null,
  order_id                      bigint,
  basket_id                     bigint,
  flight_flight_id              bigint,
  quantity                      integer,
  price                         double,
  constraint pk_order_item primary key (id)
);
create sequence order_item_seq;

create table shop_order (
  id                            bigint not null,
  order_date                    timestamp,
  customer_email                varchar(255),
  constraint pk_shop_order primary key (id)
);
create sequence shop_order_seq;

create table user (
  role                          varchar(255),
  email                         varchar(255) not null,
  name                          varchar(255),
  password                      varchar(255),
  street1                       varchar(255),
  street2                       varchar(255),
  town                          varchar(255),
  post_code                     varchar(255),
  credit_card                   varchar(255),
  constraint pk_user primary key (email)
);

alter table basket add constraint fk_basket_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;

alter table order_item add constraint fk_order_item_order_id foreign key (order_id) references shop_order (id) on delete restrict on update restrict;
create index ix_order_item_order_id on order_item (order_id);

alter table order_item add constraint fk_order_item_basket_id foreign key (basket_id) references basket (id) on delete restrict on update restrict;
create index ix_order_item_basket_id on order_item (basket_id);

alter table order_item add constraint fk_order_item_flight_flight_id foreign key (flight_flight_id) references flight_schedule (flight_id) on delete restrict on update restrict;
create index ix_order_item_flight_flight_id on order_item (flight_flight_id);

alter table shop_order add constraint fk_shop_order_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;
create index ix_shop_order_customer_email on shop_order (customer_email);


# --- !Downs

alter table basket drop constraint if exists fk_basket_customer_email;

alter table order_item drop constraint if exists fk_order_item_order_id;
drop index if exists ix_order_item_order_id;

alter table order_item drop constraint if exists fk_order_item_basket_id;
drop index if exists ix_order_item_basket_id;

alter table order_item drop constraint if exists fk_order_item_flight_flight_id;
drop index if exists ix_order_item_flight_flight_id;

alter table shop_order drop constraint if exists fk_shop_order_customer_email;
drop index if exists ix_shop_order_customer_email;

drop table if exists basket;
drop sequence if exists basket_seq;

drop table if exists flight_schedule;
drop sequence if exists flight_schedule_seq;

drop table if exists order_item;
drop sequence if exists order_item_seq;

drop table if exists shop_order;
drop sequence if exists shop_order_seq;

drop table if exists user;

