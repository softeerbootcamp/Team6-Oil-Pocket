USE
`oilpocket_db`;

drop table if exists `users`;
drop table if exists `gas_detail`;
drop table if exists `gas_station`;


CREATE TABLE users
(
    user_no  BIGINT       NOT NULL AUTO_INCREMENT,
    id       VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender   VARCHAR(255) NOT NULL,
    age      VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_no)
);


create table gas_station
(
    station_no bigint       not null auto_increment primary key,
    area       varchar(255) not null,
    name       varchar(255) not null,
    address    varchar(255) not null,
    brand      varchar(255) not null,
    is_self    boolean      not null
);

create table gas_detail
(
    detail_no    bigint primary key auto_increment,
    station_no   bigint       not null,
    gas_type     varchar(255) not null,
    price        integer      not null,
    created_date date         not null,
    foreign key (station_no) references gas_station (station_no)
);
insert into gas_station(area, name, address, brand, is_self)
values ("서울 종로구", "㈜지에스이앤알 평창주유소", "평창문화로 135", "현대오일뱅크", true);

insert into gas_detail (station_no, gas_type, price, created_date) values (1, "GASOLINE", 1659, now());


