CREATE TABLE test.table1 (
INDEX (f05(5) DESC),
f01 INTEGER,
f02 INTEGER,
f03 INTEGER,
INDEX (f01),
INDEX i01 (f02),
INDEX i02 USING BTREE (f03),
INDEX i03 USING HASH (f04),
f04 INTEGER,
f05 VARCHAR(10),
f06 INTEGER
);
