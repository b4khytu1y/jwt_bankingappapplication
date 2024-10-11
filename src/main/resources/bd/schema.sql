INSERT INTO users (username, password) VALUES ('user1', '$2a$10$7b2a5kDxXXsloPUZVne8d.YK8Xj8eXEKdHVbRsfvLQWfR2q8SlBhG'); -- пароль: password123
INSERT INTO users (username, password) VALUES ('user2', '$2a$10$bR9qj6SgZ8TrmSyD4p5yC.d/jMm.HF1WQOKmd3WfrOaq5.W/s0Tme'); -- пароль: password456
INSERT INTO accounts (account_number, balance, account_type, user_id) VALUES ('001000001', 1000.00, 'CHECKING', 1);
INSERT INTO accounts (account_number, balance, account_type, user_id) VALUES ('001000002', 500.00, 'SAVING', 1);
INSERT INTO accounts (account_number, balance, account_type, user_id) VALUES ('001000003', 2000.00, 'CHECKING', 2);
INSERT INTO accounts (account_number, balance, account_type, user_id) VALUES ('001000004', 1500.00, 'SAVING', 2);
