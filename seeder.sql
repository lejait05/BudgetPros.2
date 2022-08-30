USE budgetpros_db;

INSERT INTO user(email, first_name, last_name, password, phone_number, username)
VALUES ('chris@gmail.com', 'Chris', 'Merritt', 'chris1', 8329649608, 'ChrisTest1');

INSERT INTO budget_categories(title)
VALUES ('none'),
       ('electrical'),
       ('mortgage'),
       ('gas'),
       ('fuel'),
       ('water'),
       ('loan'),
       ('groceries');

INSERT INTO transaction_types(name)
VALUES ('one-time deposit'),
       ('one-time expense'),
       ('recurring income'),
       ('recurring expense');

INSERT INTO goals(current_amount, date_created, end_date, goal_amount, title, user_id) VALUES (200, '2022-03-13', '2022-12-12', 3000, 'Summer Vacation', 1);

INSERT INTO transactions(amount, memo, title, budget_categories_id,  transaction_types_id, user_id, goal_id)
VALUES (130.20, 'ejjkwbcdicuebowxeujxnajclnascjxnqwejudc n', 'Texaco',5, 2, 1, null),
       (3000.00, 'monthly income', 'Paycheck', 1, 3, 1, null),
       (200.00, 'savings for trip', 'save for trip', 1, 1, 1, 1);


