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
    user_name varchar(50),
    start_date varchar(50),
    end_date varchar(50),
    symptom varchar(500),
    have_medicine varchar(50),
    other_disease varchar(50),
    result varchar(50),
    primary key(Id)
) engine=InnoDB default charset=utf8;
