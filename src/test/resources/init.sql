DROP SCHEMA IF EXISTS bank CASCADE;
CREATE SCHEMA bank;

SET search_path TO bank;


CREATE TABLE account_types
(
    id              SERIAL PRIMARY KEY,

    name            varchar(256)
                    NOT NULL,

    credit_limit    numeric(12, 2) -- up to 1 000 000 000.00 roubles
                    NOT NULL
                    CHECK (credit_limit >= 0),

    credit_interval integer -- in months
                    NULL
                    CHECK (credit_interval >= 0),

    interest_yield_percent_per_year numeric(5, 2) -- up to 100.00
                                    NULL
                                    CHECK (interest_yield_percent_per_year > 0),

    interest_yield_interval integer -- in months
                            NULL
                            CHECK (interest_yield_interval > 0)

);

CREATE TABLE branches
(
    id  SERIAL PRIMARY KEY,

    name        varchar(256)
                NOT NULL,

    address     varchar(256)
                NOT NULL
);

CREATE table clients
(
    id SERIAL PRIMARY KEY,

    type                varchar(50) -- физизческое или юридическое лицо
        NOT NULL,

    name                varchar(256)
        NOT NULL,

    surname             varchar(256)
        NULL, -- for companies only

    middle_name         varchar(256)
        NULL, -- for companies only


    address             varchar(256)
        NOT NULL,

    phone_number        varchar(11) -- 79xx xxx xx xx
        NOT NULL,

    email               varchar(256)
        NOT NULL,

    date_of_birth       date
        NULL -- for companies only
);

CREATE TABLE accounts
(
    id              SERIAL
                    PRIMARY KEY,

    balance     numeric(12, 2) -- up to 1 000 000 000.00 roubles
                NOT NULL, -- TODO: constraint: if it's credit card, <=0, otherwise >= 0

    type    integer
            REFERENCES account_types ON DELETE RESTRICT
            NOT NULL,

    branch_id       integer
                    REFERENCES branches ON DELETE CASCADE
                    NOT NULL,

    client_id       integer
                    REFERENCES clients ON DELETE CASCADE
                    NOT NULL,

    interest_yield_receiver     integer
                                REFERENCES accounts ON DELETE SET DEFAULT
                                NULL -- null => to itself
                                DEFAULT NULL,
    is_active   boolean
                NOT NULL
);

CREATE TABLE credits
(
    id  SERIAL PRIMARY KEY,

    amount      numeric(12, 2) -- up to 1 000 000 000.00 roubles
                NOT NULL
                CHECK (amount > 0),

    account_id      integer
                    REFERENCES accounts ON DELETE CASCADE
                    NOT NULL,

    is_active       boolean
                    NOT NULL,

    timestamp       timestamp
                    NOT NULL
                    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE operations
(
    id      SERIAL PRIMARY KEY,

    amount      numeric(12, 2) -- up to 1 000 000 000.00 roubles
                NOT NULL
                CHECK (amount != 0),

    account_id      integer
                    REFERENCES accounts ON DELETE CASCADE
                    NOT NULL,

    timestamp       timestamp
                    NOT NULL
                    DEFAULT CURRENT_TIMESTAMP
);





INSERT INTO branches (name, address)
    VALUES
        ('Головной офис', 'г. Москва, бул. Цветной, д. 18'),
        ('Дополнительный офис «Красные Ворота»', 'г. Москва, ул. Садовая-Черногрязская, д. 3 Б, стр. 1'),
        ('Павелецкое отделение', 'г. Москва, ул. Валовая, д. 2—4/44, стр. 1'),
        ('Пустое отделение', 'г. Москва, ???');

INSERT INTO clients (type, name, surname, middle_name, address, phone_number, email, date_of_birth)
    VALUES
        ('Физическое лицо', 'Вячеслав', 'Арсеиньев', 'Германович',
         'Россия, г. Владивосток, Молодежная ул., д. 4 кв.157', '79529761915',
         'rimma2753@gmail.com', '05.06.1967'),
        ('Физическое лицо', 'Александр', 'Клепахов', 'Яковеевичевич',
         'Россия, г. Великий Новгород, Дорожная ул., д. 16 кв.23', '79452454843',
         'aleksandr.klepahov@ya.ru', '21.06.1979'),
        ('Физическое лицо', 'Рада', 'Моргунова', 'Леонтьевна',
         'Россия, г. Магнитогорск, Восточная ул., д. 8 кв.144', '79261792739',
         'rada22061967@rambler.ru', '22.06.1967'),
        ('Физическое лицо', 'Юлиан', 'Коченков', 'Александрович',
         'Россия, г. Орск, Луговой пер., д. 24 кв.67', '79265275801',
         'yulian14011988@outlook.com', '14.01.1988'),
        ('Физическое лицо', 'Максим', 'Чижиков', 'Геннадьевич',
         'Россия, г. Новочеркасск, Пролетарская ул., д. 25 кв.3', '79265657508',
         'maksim1966@rambler.ru', '19.10.1966'),
        ('Физическое лицо', 'Ирина', 'Перешивкина', 'Ивановна',
         'Россия, г. Иваново, Дачная ул., д. 5 кв.64', '79151539760',
         'irina.pereshivkina@gmail.com', '04.06.1994'),
        ('Физическое лицо', 'Виктор', 'Кривков', 'Аркадьевич',
         'Россия, г. Реутов, Дзержинского ул., д. 6 кв.185', '79255706154',
         'viktor.krivkov@yandex.ru', '13.07.1984'),
        ('Физическое лицо', 'Захар', 'Пярин', 'Прокопьевич',
         'Россия, г. Рубцовск, Спортивная ул., д. 1 кв.32', '79151539760',
         'zahar1985@rambler.ru', '12.04.1985'),
        ('Физическое лицо', 'Александр', 'Ерохин', 'Леонтьевич',
         'Россия, г. Шахты, Советский пер., д. 12 кв.78', '79261970681',
         'aleksandr11071973@yandex.ru', '11.07.1973'),
        ('Физическое лицо', 'Леонтий', 'Луковников', 'Никандрович',
         'Россия, г. Комсомольск-на-Амуре, Озерный пер., д. 25 кв.188', '79252903249',
         'leontiy3792@rambler.ru', '17.05.1978'),
        ('Юридическое лицо', 'СколькоСкинуть', NULL, NULL,
         'Москва, ул. Флотская д.11', '78005555555',
         'pr@yandex-team.ru', NULL),
        ('Юридическое лицо', 'Самокат', NULL, NULL,
         'Санкт-Петербург, улица Седова, дом 11, литер А', '78005050015',
         'pr@samokat.ru', NULL);

INSERT INTO account_types(name, credit_limit, credit_interval, interest_yield_percent_per_year, interest_yield_interval)
    VALUES
        ('Счет дебетовой карты', 0, NULL, 5, 1),
        ('Счет дебетовой карты с повышенным процентом на остаток', 0, NULL, 10, 1),
        ('Счет кредитной карты: маленький лимит', 10000, 1, NULL, NULL),
        ('Счет кредитной карты: средний лимит', 200000, 3, NULL, NULL),
        ('Счет кредитной карты: большой лимит', 500000, 6, NULL, NULL);

INSERT INTO accounts(balance, type, branch_id, client_id, interest_yield_receiver, is_active)
VALUES
    (-86527, 4, 1, 6, NULL, true),
    (194763, 1, 3, 8, NULL, true),
    (-119273, 4, 3, 4, NULL, true),
    (-4824, 3, 3, 4, NULL, true),
    (397808, 1, 2, 4, NULL, true),
    (0, 3, 2, 2, NULL, true),
    (67941, 1, 2, 11, NULL, true),
    (320082, 2, 1, 6, NULL, true),
    (-5559, 3, 3, 4, NULL, true),
    (-1090, 3, 2, 12, NULL, true),
    (0, 3, 3, 3, NULL, true),
    (475389, 1, 3, 8, NULL, true),
    (111158, 2, 1, 10, NULL, true),
    (-173068, 4, 2, 7, NULL, true),
    (372974, 1, 1, 8, NULL, true),
    (0, 1, 2, 7, NULL, true);

INSERT INTO credits(amount, account_id, is_active, timestamp)
VALUES
    (86527, 1, true, '2021-06-20 22:13:45'),
    (41334, 3, false, '2021-02-08 05:07:13'),
    (4421, 1, false, '2021-08-06 04:42:35'),
    (119273, 3, true, '2021-02-21 10:04:12'),
    (4824, 4, true, '2022-02-08 23:46:25'),
    (34412, 4, false, '2021-06-28 16:50:44'),
    (5559, 9, true, '2021-10-20 05:07:22'),
    (1090, 10, true, '2021-06-15 13:11:02'),
    (173068, 14, true, '2022-01-20 07:01:59');

INSERT INTO operations(amount, account_id, timestamp)
    VALUES
        (351310, 1, '2021-07-02 11:38:01'),
        (301270, 1, '2021-04-02 05:39:23'),
        (403129, 1, '2021-02-01 08:40:29'),
        (461576, 1, '2021-05-11 01:59:59'),
        (-371601, 1, '2022-03-11 14:51:10'),
        (282044, 2, '2021-02-18 07:32:29'),
        (-275737, 3, '2022-01-23 02:49:25'),
        (-147638, 3, '2021-11-08 07:37:24'),
        (54807, 3, '2021-08-29 03:41:31'),
        (310444, 3, '2021-01-10 18:58:40'),
        (464017, 3, '2021-03-27 11:16:04'),
        (-442784, 4, '2021-02-14 12:17:19'),
        (438577, 5, '2021-11-06 09:34:37'),
        (115485, 5, '2021-09-02 09:23:27'),
        (356143, 6, '2021-01-08 22:44:53'),
        (168425, 6, '2021-11-22 13:13:48'),
        (-343282, 7, '2021-05-13 11:50:43'),
        (-82467, 8, '2021-02-21 18:13:59'),
        (236699, 9, '2021-11-29 17:54:14'),
        (-465809, 10, '2021-03-09 23:06:13'),
        (-134973, 10, '2021-06-09 04:49:46'),
        (-70450, 11, '2021-06-30 12:41:53'),
        (16827, 11, '2021-11-27 06:57:52'),
        (-167372, 12, '2021-08-10 02:10:59'),
        (262026, 12, '2021-08-03 00:15:46'),
        (-7230, 13, '2021-09-15 12:45:24'),
        (-173524, 14, '2022-01-23 02:49:25');