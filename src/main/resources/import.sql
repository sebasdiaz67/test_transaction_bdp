-- INSERT ACCOUNTS
INSERT INTO account(id, number, type, creation_date) VALUES(1, '1234567891','AHO', '2021-05-01');
INSERT INTO account(id, number, type, creation_date) VALUES(2, '1234567892','AHO', '2021-05-01');
INSERT INTO account(id, number, type, creation_date) VALUES(3, '1234567893','CTE', '2021-05-01');
--INSERT TRANSACTIONS
INSERT INTO TRANSACTIONS(id, comment, type, creation_date, account_id) VALUES(1001, 'TRA-001', '', '2021-05-30',1);
INSERT INTO TRANSACTIONS(id, comment, type, creation_date, account_id) VALUES(1002, 'TRA-002', '', '2021-05-28',1);
INSERT INTO TRANSACTIONS(id, comment, type, creation_date, account_id) VALUES(1003, 'TRA-003', '', '2021-05-31',2);