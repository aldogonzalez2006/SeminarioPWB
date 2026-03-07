create table category
(
    category_id int auto_increment
        primary key,
    category    varchar(100) not null,
    tag         varchar(100) not null,
    status      tinyint      not null,
    constraint ux_category
        unique (category),
    constraint ux_tag
        unique (tag)
);