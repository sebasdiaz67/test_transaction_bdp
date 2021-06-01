-- INSERT ACCOUNTS
INSERT INTO accounts(number, type, creation_date) VALUES('1234567891','AHO', '2021-05-01');
INSERT INTO accounts(number, type, creation_date) VALUES('1234567892','AHO', '2021-05-01');
INSERT INTO accounts(number, type, creation_date) VALUES('1234567893','CTE', '2021-05-01');
--INSERT TRANSACTIONS
INSERT INTO TRANSACTIONS(comment, type, creation_date, account_id) VALUES('TRA-001', '', '2021-05-30',1);
INSERT INTO TRANSACTIONS(comment, type, creation_date, account_id) VALUES('TRA-002', '', '2021-05-28',1);
INSERT INTO TRANSACTIONS(comment, type, creation_date, account_id) VALUES('TRA-003', '', '2021-05-31',2);