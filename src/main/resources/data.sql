-- Insert data in users
INSERT INTO users (id, name, email) VALUES
(1, 'Alice Johnson', 'alice.johnson@example.com'),
(2, 'Bob Smith', 'bob.smith@example.com'),
(3, 'Charlie Davis', 'charlie.davis@example.com'),
(4, 'Diana Prince', 'diana.prince@example.com'),
(5, 'Ethan Clark', 'ethan.clark@example.com'),
(6, 'Fiona Lewis', 'fiona.lewis@example.com'),
(7, 'George Brown', 'george.brown@example.com'),
(8, 'Hannah White', 'hannah.white@example.com'),
(9, 'Ian Black', 'ian.black@example.com'),
(10, 'Julia Green', 'julia.green@example.com'),
(11, 'Kevin Adams', 'kevin.adams@example.com'),
(12, 'Laura Martin', 'laura.martin@example.com'),
(13, 'Michael Thompson', 'michael.thompson@example.com'),
(14, 'Nina Rodriguez', 'nina.rodriguez@example.com'),
(15, 'Oscar Lee', 'oscar.lee@example.com'),
(16, 'Paula Walker', 'paula.walker@example.com'),
(17, 'Quentin Harris', 'quentin.harris@example.com'),
(18, 'Rachel King', 'rachel.king@example.com'),
(19, 'Steven Young', 'steven.young@example.com'),
(20, 'Tina Scott', 'tina.scott@example.com'),
(21, 'Alice Smith', 'alice@example.com'),
(22, 'Bob Johnson', 'bob@example.com');
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE user_seq RESTART WITH 101;


-- Insert data in products
INSERT INTO products (id, name, base_price, rating) VALUES
(1, 'Football', 25.99, 5),
(2, 'Basketball', 29.99, 4),
(3, 'Tennis Racket', 89.99, 3),
(4, 'Soccer Jersey', 45.99, 1),
(5, 'Running Shoes', 69.99, 5),
(6, 'Baseball Glove', 55.99, 1),
(7, 'Golf Clubs', 399.99, 5),
(8, 'Yoga Mat', 19.99, 4),
(9, 'Cycling Helmet', 49.99, 2),
(10, 'Skateboard', 79.99, 1),
(11, 'Cricket Bat', 129.99, 3),
(12, 'Swimming Goggles', 14.99, 1),
(13, 'Boxing Gloves', 39.99, 2),
(14, 'Hockey Stick', 59.99, 3),
(15, 'Ski Boots', 199.99, 5),
(16, 'Snowboard', 249.99, 5),
(17, 'Martial Arts Uniform', 39.99, 1),
(18, 'Badminton Racket', 59.99, 1),
(19, 'Volleyball', 34.99, 2),
(20, 'Rowing Oar', 89.99, 3);
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE product_seq RESTART WITH 101;


-- Insert data in inventory_transactions
INSERT INTO inventory_transactions (id, product_id, type, timestamp, quantity, unit_price) VALUES
(1, 1, 'PURCHASE', '2025-01-21 09:00:00', 100, 15.00),
(2, 2, 'PURCHASE', '2025-01-31 10:00:00', 80, 18.00),
(3, 3, 'PURCHASE', '2025-02-01 11:00:00', 50, 60.00),
(4, 4, 'PURCHASE', '2025-02-01 12:00:00', 120, 30.00),
(5, 5, 'PURCHASE', '2025-02-01 13:00:00', 70, 45.00),
(6, 6, 'PURCHASE', '2025-02-01 14:00:00', 90, 35.00),
(7, 7, 'PURCHASE', '2025-02-01 15:00:00', 30, 250.00),
(8, 8, 'PURCHASE', '2025-02-01 16:00:00', 150, 10.00),
(9, 9, 'PURCHASE', '2025-02-01 17:00:00', 110, 30.00),
(10, 10, 'PURCHASE', '2025-02-01 18:00:00', 60, 50.00),
(11, 11, 'PURCHASE', '2025-02-02 09:00:00', 40, 80.00),
(12, 12, 'PURCHASE', '2025-02-02 10:00:00', 130, 8.00),
(13, 13, 'PURCHASE', '2025-02-02 11:00:00', 100, 25.00),
(14, 14, 'PURCHASE', '2025-02-02 12:00:00', 55, 40.00),
(15, 15, 'PURCHASE', '2025-02-02 13:00:00', 35, 120.00),
(16, 16, 'PURCHASE', '2025-02-02 14:00:00', 40, 160.00),
(17, 17, 'PURCHASE', '2025-02-02 15:00:00', 90, 20.00),
(18, 18, 'PURCHASE', '2025-02-02 16:00:00', 75, 35.00),
(19, 19, 'PURCHASE', '2025-02-02 17:00:00', 85, 22.00),
(20, 20, 'PURCHASE', '2025-02-02 18:00:00', 50, 60.00),
(21, 2, 'SALE', '2025-01-04 10:30:00', 20, 29.99),
(22, 4, 'SALE', '2025-02-05 11:00:00', 30, 45.99),
(23, 6, 'SALE', '2025-02-05 11:30:00', 25, 55.99),
(24, 8, 'SALE', '2025-02-05 12:00:00', 40, 19.99),
(25, 10, 'SALE', '2025-02-05 12:30:00', 10, 79.99),
(26, 12, 'SALE', '2025-02-05 13:00:00', 50, 14.99),
(27, 14, 'SALE', '2025-02-05 13:30:00', 15, 59.99),
(28, 16, 'SALE', '2025-02-05 14:00:00', 20, 249.99),
(29, 18, 'SALE', '2025-02-06 14:30:00', 30, 59.99),
(30, 20, 'SALE', '2025-02-15 15:00:00', 15, 89.99);
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE inventory_transaction_seq RESTART WITH 101;


-- Insert data in customers
INSERT INTO customers (id, name, email, phone, created_at) VALUES
(1, 'Mark Thompson', 'mark.thompson@example.com', '555-0101', '2025-02-10 10:00:00'),
(2, 'Laura Williams', 'laura.williams@example.com', '555-0102', '2025-02-10 10:00:00'),
(3, 'James Wilson', 'james.wilson@example.com', '555-0103', '2025-02-10 10:00:00'),
(4, 'Patricia Miller', 'patricia.miller@example.com', '555-0104', '2025-02-10 10:00:00'),
(5, 'Robert Moore', 'robert.moore@example.com', '555-0105', '2025-02-10 10:00:00'),
(6, 'Linda Taylor', 'linda.taylor@example.com', '555-0106', '2025-02-10 10:00:00'),
(7, 'Michael Anderson', 'michael.anderson@example.com', '555-0107', '2025-02-10 10:00:00'),
(8, 'Barbara Thomas', 'barbara.thomas@example.com', '555-0108', '2025-02-10 10:00:00'),
(9, 'William Jackson', 'william.jackson@example.com', '555-0109', '2025-02-10 10:00:00'),
(10, 'Elizabeth White', 'elizabeth.white@example.com', '555-0110', '2025-02-10 10:00:00'),
(11, 'David Harris', 'david.harris@example.com', '555-0111', '2025-02-10 10:00:00'),
(12, 'Jennifer Martin', 'jennifer.martin@example.com', '555-0112', '2025-02-10 10:00:00'),
(13, 'Richard Thompson', 'richard.thompson@example.com', '555-0113', '2025-02-10 10:00:00'),
(14, 'Susan Garcia', 'susan.garcia@example.com', '555-0114', '2025-02-10 10:00:00'),
(15, 'Joseph Martinez', 'joseph.martinez@example.com', '555-0115', '2025-02-10 10:00:00'),
(16, 'Margaret Robinson', 'margaret.robinson@example.com', '555-0116', '2025-02-10 10:00:00'),
(17, 'Charles Clark', 'charles.clark@example.com', '555-0117', '2025-02-10 10:00:00'),
(18, 'Dorothy Rodriguez', 'dorothy.rodriguez@example.com', '555-0118', '2025-02-10 10:00:00'),
(19, 'Thomas Lewis', 'thomas.lewis@example.com', '555-0119', '2025-01-10 10:00:00'),
(20, 'Sarah Lee', 'sarah.lee@example.com', '555-0120', '2025-01-10 10:00:00'),
(21, 'Charlie Brown', 'charlie@example.com', '123-456-7890', '2025-01-10 10:00:00'),
(22, 'Diana Prince',  'diana@example.com',   '234-567-8901', '2025-01-10 10:00:00');
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE customer_seq RESTART WITH 101;


-- Insert data in orders
INSERT INTO orders (id, customer_id, total_amount, amount_paid, pending_amount, status, created_at) VALUES
(1, 1,  99.99,  99.99,   0, 'COMPLETED', '2025-02-10 10:00:00'),
(2, 2, 149.99, 100.00, 49.99, 'PENDING',   '2025-02-10 10:15:00'),
(3, 3,  59.99,  59.99,   0, 'COMPLETED', '2025-02-10 10:30:00'),
(4, 4,  89.99,   0,    89.99, 'PENDING',   '2025-02-10 10:45:00'),
(5, 5, 129.99, 129.99,   0, 'COMPLETED', '2025-02-10 11:00:00'),
(6, 6, 199.99, 150.00, 49.99, 'PENDING',   '2025-02-10 11:15:00'),
(7, 7, 249.99, 249.99,   0, 'COMPLETED', '2025-02-10 11:30:00'),
(8, 8,  79.99,   0,    79.99, 'CANCELED',  '2025-02-10 11:45:00'),
(9, 9, 109.99, 109.99,   0, 'COMPLETED', '2025-02-10 12:00:00'),
(10, 10,  59.99,  30.00, 29.99, 'PENDING',   '2025-02-10 12:15:00'),
(11, 11, 139.99, 139.99,   0, 'COMPLETED', '2025-02-10 12:30:00'),
(12, 12,  99.99,  50.00, 49.99, 'PENDING',   '2025-02-10 12:45:00'),
(13, 13,  89.99,  89.99,   0, 'COMPLETED', '2025-02-10 13:00:00'),
(14, 14, 119.99,   0,   119.99, 'PENDING',   '2025-02-10 13:15:00'),
(15, 15, 159.99, 159.99,   0, 'COMPLETED', '2025-02-10 13:30:00'),
(16, 16,  89.99,  50.00, 39.99, 'PENDING',   '2025-02-10 13:45:00'),
(17, 17, 199.99, 199.99,   0, 'COMPLETED', '2025-02-10 14:00:00'),
(18, 18, 149.99,   0,   149.99, 'CANCELED',  '2025-02-10 14:15:00'),
(19, 19, 129.99, 129.99,   0, 'COMPLETED', '2025-02-10 14:30:00'),
(20, 20,  99.99,  99.99,   0, 'COMPLETED', '2025-02-10 14:45:00'),
(21, 1, 59.97, 59.97, 0, 'COMPLETED', '2025-01-06 12:00:00'),
(22, 2, 39.98, 20.00, 190000.98, 'PENDING', '2025-01-07 14:00:00');
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE order_seq RESTART WITH 101;


-- Insert data in order_items
INSERT INTO order_items (id, order_id, product_id, quantity, unit_price, discount) VALUES
(1, 1, 1, 2, 25.99,  0),
(2, 1, 2, 1, 29.99,  0),
(3, 2, 3, 1, 89.99, 10.00),
(4, 3, 4, 1, 45.99,  0),
(5, 4, 5, 1, 69.99,  5.00),
(6, 4, 6, 1, 55.99,  0),
(7, 5, 7, 1, 399.99,20.00),
(8, 5, 8, 2, 19.99,  0),
(9, 6, 9, 1, 49.99,  0),
(10, 7, 10, 1, 79.99,  0),
(11, 7, 11, 1, 129.99,10.00),
(12, 8, 12, 2, 14.99,  0),
(13, 9, 13, 1, 39.99,  0),
(14, 9, 14, 1, 59.99,  5.00),
(15, 10, 15, 1, 199.99, 0),
(16, 11, 16, 1, 249.99, 0),
(17, 12, 17, 1, 39.99,  0),
(18, 12, 18, 1, 59.99,  0),
(19, 13, 19, 2, 34.99,  0),
(20, 14, 20, 1, 89.99,  0),
(21, 15, 1, 3, 25.99,  5.00),
(22, 15, 3, 1, 89.99,  0),
(23, 16, 2, 1, 29.99,  0),
(24, 17, 4, 2, 45.99,  0),
(25, 18, 5, 1, 69.99,  0),
(26, 18, 6, 1, 55.99,  0),
(27, 19, 7, 1, 399.99,20.00),
(28, 20, 8, 1, 19.99,  0);
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE order_item_seq RESTART WITH 101;


-- Insert data in payments
INSERT INTO payments (id, order_id, method, timestamp) VALUES
(1, 1, 'CASH', '2025-02-10 10:05:00'),
(2, 2, 'CASH', '2025-02-10 10:20:00'),
(3, 3, 'CASH', '2025-02-10 10:35:00'),
(4, 4, 'CREDIT_CARDS', '2025-02-10 10:50:00'),
(5, 5, 'CREDIT_CARDS', '2025-02-10 11:05:00'),
(6, 6, 'CREDIT_CARDS', '2025-02-10 11:20:00'),
(7, 7, 'CREDIT_CARDS', '2025-02-10 11:35:00'),
(8, 8, 'CREDIT_CARDS', '2025-02-10 11:50:00'),
(9, 9, 'DEBIT_CARDS', '2025-02-10 12:05:00'),
(10, 10, 'DEBIT_CARDS', '2025-02-10 12:20:00'),
(11, 11, 'CHECKS', '2025-02-10 12:35:00'),
(12, 12, 'BANK_TRANSFERS', '2025-02-10 12:50:00'),
(13, 13, 'BANK_TRANSFERS', '2025-02-10 13:05:00'),
(14, 14, 'APPLE_PAY', '2025-02-10 13:20:00'),
(15, 15, 'APPLE_PAY', '2025-02-10 13:35:00'),
(16, 16, 'PAYPAL', '2025-02-10 13:50:00'),
(17, 17, 'PAYPAL', '2025-02-10 14:05:00'),
(18, 18, 'PAYPAL', '2025-02-10 14:20:00'),
(19, 19, 'PAYPAL', '2025-02-10 14:35:00'),
(20, 20, 'MASTER_CARD', '2025-02-10 14:50:00');
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE payment_seq RESTART WITH 101;


-- Insert data in expenses
INSERT INTO expenses (id, category, amount, description, timestamp, year, month ) VALUES
-- (1, 'Utilities',       300.00, 'Electricity and water bills',        '2025-02-15 08:00:00'),
-- (2, 'Rent',           2000.00, 'Monthly warehouse rent',               '2025-02-01 09:00:00'),
-- (3, 'Salaries',        5000.00, 'Staff salaries for February',          '2025-02-28 17:00:00'),
-- (4, 'Maintenance',      450.00, 'Equipment maintenance',                '2025-02-10 10:00:00'),
-- (5, 'Marketing',        600.00, 'Social media ads',                     '2025-02-12 12:00:00'),
-- (6, 'Insurance',        350.00, 'Business insurance',                   '2025-02-03 11:00:00'),
-- (7, 'Equipment Repair', 250.00, 'Repair of damaged equipment',          '2025-02-07 14:00:00'),
-- (8, 'Office Supplies',  150.00, 'Stationery and printer ink',           '2025-02-05 09:30:00'),
-- (9, 'Travel',           800.00, 'Team travel expenses',                 '2025-02-14 15:00:00'),
-- (10, 'Training',         400.00, 'Employee training session',            '2025-02-20 10:00:00'),
-- (11, 'Legal',            700.00, 'Legal consultation fees',              '2025-02-18 11:00:00'),
-- (12, 'IT Services',      550.00, 'IT support services',                  '2025-02-16 12:00:00'),
-- (13, 'Security',         300.00, 'Security system installation',         '2025-02-08 08:00:00'),
-- (14, 'Cleaning',         200.00, 'Office cleaning services',             '2025-02-06 07:30:00'),
-- (15, 'Advertising',      450.00, 'Local newspaper ads',                  '2025-02-11 09:00:00'),
-- (16, 'Consulting',       650.00, 'Business consulting fees',             '2025-02-13 14:00:00'),
-- (17, 'Utilities',        320.00, 'Gas bill',                             '2025-02-17 10:00:00'),
-- (18, 'Transportation',   400.00, 'Vehicle maintenance and fuel',         '2025-02-09 13:00:00'),
-- (19, 'Depreciation',     500.00, 'Depreciation of sports equipment',     '2025-02-21 16:00:00'),
-- (20, 'Miscellaneous',    150.00, 'Other expenses',                       '2025-02-22 10:00:00');
(1, 'Utilities',       760.00, 'Electricity and water bills',        '2025-02-15 08:00:00', 2025, 1),
(2, 'Rent',           2000.00, 'Monthly warehouse rent',               '2025-02-01 09:00:00', 2025, 1),
(3, 'Salaries',        5000.00, 'Staff salaries for February',          '2025-02-28 17:00:00', 2025, 1),
(4, 'Marketing',        650.00, 'Social media ads',                     '2025-02-12 12:00:00', 2025, 1),
(5, 'Utilities',       76.00, 'Electricity and water bills',        '2025-02-15 08:00:00', 2025, 2),
(6, 'Rent',           200.00, 'Monthly warehouse rent',               '2025-02-01 09:00:00', 2025, 2),
(7, 'Salaries',        500.00, 'Staff salaries for February',          '2025-02-28 17:00:00', 2025, 2),
(8, 'Marketing',        65.00, 'Social media ads',                     '2025-02-12 12:00:00', 2025, 2);
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE expense_seq RESTART WITH 101;


-- Insert data in notifications
INSERT INTO notification (id, message, product_id, created_at, is_read) VALUES
(1, 'Product create: Tennis Racket (Id: 3) created at 2025-02-01', 3, '2025-02-01 09:00:00', true),
(2, 'Product create: Football (Id: 1) created at 2025-02-12', 1, '2025-02-12 12:00:00', false),
(3, 'Product create: Basketball (Id: 2) created at 2025-02-28', 2, '2025-02-28 17:00:00', false);
-- Adjust the sequence to avoid conflicts with the initial data
ALTER SEQUENCE notification_seq RESTART WITH 101;
