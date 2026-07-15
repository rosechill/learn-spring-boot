CREATE TABLE IF NOT EXISTS  categories
(
    id         bigint primary key auto_increment,
    user_id    bigint       not null,
    name       varchar(100) not null,
    type       varchar(10)  not null,
    icon       varchar(50),
    color      varchar(20),
    created_at timestamp    not null default current_timestamp,
    updated_at timestamp    not null default current_timestamp,
    deleted_at timestamp,
    constraint fk_category_user foreign key (user_id) references users (id) on delete cascade
);