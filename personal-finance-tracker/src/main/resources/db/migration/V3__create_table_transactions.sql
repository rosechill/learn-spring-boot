create table transactions
(
    id               bigint primary key auto_increment,
    user_id          bigint         not null,
    category_id      bigint         not null,
    type             varchar(10)    not null,
    amount           decimal(15, 2) not null,
    description      text,
    transaction_date date           not null,
    created_at       timestamp      not null default current_timestamp,
    updated_at       timestamp      not null default current_timestamp,
    deleted_at       timestamp,
    constraint fk_transaction_user foreign key (user_id) references users (id) on delete cascade,
    constraint fk_transaction_category foreign key (category_id) references categories (id) on delete restrict
);