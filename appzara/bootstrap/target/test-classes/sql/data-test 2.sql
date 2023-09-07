CREATE SCHEMA IF NOT EXISTS appzara;

INSERT INTO price (brand_id, start_date, end_date, product_id, amount, currency, rate, priority)
VALUES (
1,
parseDateTime('2020-06-14-00.00.00',  'yyyy-MM-dd-HH.mm.ss'),
parseDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-HH.mm.ss'),
35455,
35.50,
'EUR',
1,
0);

INSERT INTO price (brand_id, start_date, end_date, product_id, amount, currency, rate, priority)
VALUES (
1,
parseDateTime('2020-06-14-15.00.00',  'yyyy-MM-dd-HH.mm.ss'),
parseDATETIME('2020-06-14-18.30.00', 'yyyy-MM-dd-HH.mm.ss'),
35455,
25.45,
'EUR',
2,
1);


INSERT INTO price (brand_id, start_date, end_date, product_id, amount, currency, rate, priority)
VALUES (
1,
parseDateTime('2020-06-15-00.00.00',  'yyyy-MM-dd-HH.mm.ss'),
parseDATETIME('2020-06-15-11.00.00', 'yyyy-MM-dd-HH.mm.ss'),
35455,
30.50,
'EUR',
3,
1);

INSERT INTO price (brand_id, start_date, end_date, product_id, amount, currency, rate, priority)
VALUES (
1,
parseDateTime('2020-06-15-16.00.00',  'yyyy-MM-dd-HH.mm.ss'),
parseDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-HH.mm.ss'),
35455,
38.95,
'EUR',
4,
1);