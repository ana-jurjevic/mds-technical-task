CREATE TABLE IF NOT EXISTS stock (
    id SERIAL PRIMARY KEY,
    code varchar(255) NULL,
    established_on timestamp(6) NULL,
    "name" varchar(255) NULL,
    CONSTRAINT unique_code UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS stock_history (
    id SERIAL PRIMARY KEY,
    adjusted_close float8 NULL,
    "close" float8 NULL,
    "date" timestamp(6) NULL,
    high float8 NULL,
    low float8 NULL,
    "open" float8 NULL,
    volume int8 NULL,
    stock_id int4 NOT NULL
);

ALTER TABLE stock_history ADD CONSTRAINT stock_history_stock_constraint FOREIGN KEY (stock_id) REFERENCES public.stock(id);
