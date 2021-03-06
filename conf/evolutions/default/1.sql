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

create table booking (
  id                            bigint not null,
  customer_email                varchar(255),
  flight_flight_id              bigint,
  firstname                     varchar(255),
  lastname                      varchar(255),
  ticket_class                  varchar(255),
  no_of_tickets                 integer,
  price                         double,
  seat_count                    integer,
  constraint pk_booking primary key (id)
);
create sequence booking_seq;

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
  ticket_id                     bigint,
  price                         double,
  no_of_tickets                 integer,
  seats                         integer,
  constraint pk_order_item primary key (id)
);
create sequence order_item_seq;

create table payment (
  id                            bigint not null,
  ticket_id                     bigint,
  card_holder_name              varchar(255),
  card_number                   varchar(255),
  security_number               varchar(255),
  status                        boolean,
  constraint uq_payment_ticket_id unique (ticket_id),
  constraint pk_payment primary key (id)
);
create sequence payment_seq;

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
  confirm_password              varchar(255),
  street1                       varchar(255),
  street2                       varchar(255),
  town                          varchar(255),
  post_code                     varchar(255),
  credit_card                   varchar(255),
  constraint pk_user primary key (email)
);

alter table basket add constraint fk_basket_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;

alter table booking add constraint fk_booking_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;
create index ix_booking_customer_email on booking (customer_email);

alter table booking add constraint fk_booking_flight_flight_id foreign key (flight_flight_id) references flight_schedule (flight_id) on delete restrict on update restrict;
create index ix_booking_flight_flight_id on booking (flight_flight_id);

alter table order_item add constraint fk_order_item_order_id foreign key (order_id) references shop_order (id) on delete restrict on update restrict;
create index ix_order_item_order_id on order_item (order_id);

alter table order_item add constraint fk_order_item_basket_id foreign key (basket_id) references basket (id) on delete restrict on update restrict;
create index ix_order_item_basket_id on order_item (basket_id);

alter table order_item add constraint fk_order_item_ticket_id foreign key (ticket_id) references booking (id) on delete restrict on update restrict;
create index ix_order_item_ticket_id on order_item (ticket_id);

alter table payment add constraint fk_payment_ticket_id foreign key (ticket_id) references booking (id) on delete restrict on update restrict;

alter table shop_order add constraint fk_shop_order_customer_email foreign key (customer_email) references user (email) on delete restrict on update restrict;
create index ix_shop_order_customer_email on shop_order (customer_email);


# --- !Downs

alter table basket drop constraint if exists fk_basket_customer_email;

alter table booking drop constraint if exists fk_booking_customer_email;
drop index if exists ix_booking_customer_email;

alter table booking drop constraint if exists fk_booking_flight_flight_id;
drop index if exists ix_booking_flight_flight_id;

alter table order_item drop constraint if exists fk_order_item_order_id;
drop index if exists ix_order_item_order_id;

alter table order_item drop constraint if exists fk_order_item_basket_id;
drop index if exists ix_order_item_basket_id;

alter table order_item drop constraint if exists fk_order_item_ticket_id;
drop index if exists ix_order_item_ticket_id;

alter table payment drop constraint if exists fk_payment_ticket_id;

alter table shop_order drop constraint if exists fk_shop_order_customer_email;
drop index if exists ix_shop_order_customer_email;

drop table if exists basket;
drop sequence if exists basket_seq;

drop table if exists booking;
drop sequence if exists booking_seq;

drop table if exists flight_schedule;
drop sequence if exists flight_schedule_seq;

drop table if exists order_item;
drop sequence if exists order_item_seq;

drop table if exists payment;
drop sequence if exists payment_seq;

drop table if exists shop_order;
drop sequence if exists shop_order_seq;

drop table if exists user;

