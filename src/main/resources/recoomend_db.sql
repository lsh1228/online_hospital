mysql -u root -p --default-character-set=utf8


create database recommend_db default character set utf8;

create table tag_recommend (
    id bigint not null auto_increment,
    tag varchar(50),
    medicine varchar(50),
    ratio float,
    primary key(id)
) engine=InnoDB default charset=utf8;


create table hospital_user (
    id bigint not null auto_increment,
    name varchar(50),
    email varchar(50),
    password varchar(64),
    sex int(5),
    age int(10),
    role varchar(20),
    primary key(Id),
    unique key index_user (name)
) engine=InnoDB default charset=utf8;


create table treatment (
   id bigint not null auto_increment,
   title varchar(50),
   author varchar(50),
   publish_time varchar(50),
   content varchar(2000),
   ill_type varchar(50),
   ill_state varchar(50),
   medicine_cnt  int,
   primary key(Id)
) engine=InnoDB default charset=utf8;
