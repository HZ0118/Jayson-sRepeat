# --- !Ups

insert into user (email, name, password, role) values ('admin', 'Jayson Admin', 'admin', 'admin');
insert into user (email, name, password, role) values ('customer', 'Jayson Customer', 'customer', 'customer');

insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (1, 'America', 'New York', 'Dublin', '18/09/2017', 300, 400);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (2, 'China', 'Beijing', 'Dublin', '18/09/2017', 500, 700);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (3, 'Germany', 'Berlin', 'Dublin', '03/10/2017', 400, 580);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (4, 'Spain', 'Madrid', 'Dublin', '18/01/2018', 380, 460);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (5, 'Italy', 'Rome', 'Dublin', '28/03/2018', 200, 470);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (6, 'France', 'Paris', 'Dublin', '21/12/2017', 250, 350);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (7, 'Spain', 'Barcelona', 'Dublin', '02/11/2017', 350, 450);
insert into flight_schedule (flight_id, destination, dest_city, start, departure_date, seats, price) values (8, 'Japan', 'Tokyo', 'Dublin', '27/10/2017', 500, 680);

insert into basket (id, customer) values ('1', 'customer');