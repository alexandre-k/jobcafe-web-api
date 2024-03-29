INSERT INTO profession (label) VALUES ('Accountant');
INSERT INTO profession (label) VALUES ('Clerk Accountant');

INSERT INTO credit_card (issuer) VALUES ('MasterCard');
INSERT INTO credit_card (issuer) VALUES ('Visa');

INSERT INTO ticket_category (label) VALUES ('Tutorial');
INSERT INTO ticket_category (label) VALUES ('Guide');

INSERT INTO plan (label, price) VALUES ('Basic', 03.99);
INSERT INTO plan (label, price) VALUES ('Standard', 10.99);
INSERT INTO plan (label, price) VALUES ('Premium', 15.99);

INSERT INTO payment (card_issuer, cardholder_name, card_number, cvv, expiration_date, address, city, postal_code, state_province, country)
VALUES ('MasterCard', 'John Doe', '1111-1111-1111-1111', 111, STR_TO_DATE('2018/11/01', '%Y/%m/%d'), '1st street of Washington', 'Seattle', '98105', 'Washington', 'USA');
INSERT INTO payment (card_issuer, cardholder_name, card_number, cvv, expiration_date, address, city, postal_code, state_province, country)
VALUES ('Visa', 'Jane Doe', '2222-2222-2222-2222', 222, STR_TO_DATE('2010/12/01', '%Y/%m/%d'), 'Shimotakaido 8-21-5', 'Tokyo', 'Suginami', '112-2103', 'Tokyo');

INSERT INTO user (email, first_name, last_name, password, is_staff, profile_picture) VALUES ('sarah.connor@gmail.com', 'Sarah', 'Connor', 'nope', TRUE, 'http://localhost:8080/profile_picture.png');
INSERT INTO user (email, first_name, last_name, password, is_staff, profile_picture) VALUES ('david.bowie@gmail.com', 'David', 'Bowie', 'yeah', TRUE, 'http://localhost:8080/profile_picture.png');

INSERT INTO user (email, first_name, last_name, password, phone, profession_label, membership_label, payment_method_id, profile_picture)
VALUES ('john.doe@gmail.com', 'John', 'Doe', 'toto', '080-1111-2222', 'Accountant', 'Basic', 1, 'http://localhost:8080/profile_picture.png');
INSERT INTO user (email, first_name, last_name, password, phone, profession_label, membership_label, payment_method_id, profile_picture)
VALUES ('jane.doe@gmail.com', 'Jane', 'Doe', 'tototo', '091-3333-4444', 'Clerk Accountant', 'Premium', 2, 'http://localhost:8080/profile_picture.png');

INSERT INTO ticket (title, owner_email, operator_email, category_label, status) VALUES ('Houston, I got a problem', 'john.doe@gmail.com', 'sarah.connor@gmail.com', 'Tutorial', 'OPEN');
INSERT INTO ticket (title, owner_email, operator_email, category_label, status) VALUES ('I do not have anymore a problem', 'john.doe@gmail.com', 'sarah.connor@gmail.com', 'Guide', 'CLOSED');
INSERT INTO ticket (title, owner_email, operator_email, category_label, status) VALUES ('How to make a pizza', 'jane.doe@gmail.com', 'david.bowie@gmail.com', 'Guide', "CLOSED");

INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1);
INSERT INTO message (author_email, content, ticket_id) VALUES ('sarah.connor@gmail.com', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 1);
INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 1);

INSERT INTO message (author_email, content, ticket_id) VALUES ('jane.doe@gmail.com', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('david.bowie@gmail.com', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('david.bowie@gmail.com', 'Consequat interdum varius sit amet mattis vulputate enim nulla aliquet.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('jane.doe@gmail.com', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('jane.doe@gmail.com', 'Vitae congue mauris rhoncus aenean vel elit scelerisque mauris pellentesque.', 2);

INSERT INTO transaction (id, delivery_estimate, transactor_email) VALUES ('RF120941', '2018-12-05', 'john.doe@gmail.com');
INSERT INTO transaction (id, delivery_estimate, transactor_email) VALUES ('RF151093', '2018-12-15', 'jane.doe@gmail.com');
