INSERT INTO profession (label) VALUES ('Accountant');
INSERT INTO profession (label) VALUES ('Clerk Accountant');

INSERT INTO credit_card_issuer (issuer) VALUES ('MasterCard');
INSERT INTO credit_card_issuer (issuer) VALUES ('Visa');

INSERT INTO ticket_category (label) VALUES ('Tutorial');
INSERT INTO ticket_category (label) VALUES ('Guide');

INSERT INTO subscription_plan (label, price, tax) VALUES ('Basic Plan', 03.99, 8.20);
INSERT INTO subscription_plan (label, price, tax) VALUES ('Standard Plan', 10.99, 9.43);
INSERT INTO subscription_plan (label, price, tax) VALUES ('Premium Plan', 15.99, 10.45);


INSERT INTO juser (email, first_name, last_name, password, is_staff, profile_picture)
VALUES ('sarah.connor@gmail.com', 'Sarah', 'Connor', 'nope', TRUE, 'https://jobcafe-bucket.s3-ap-northeast-1.amazonaws.com/profile_picture.png');
INSERT INTO juser (email, first_name, last_name, password, is_staff, profile_picture)
VALUES ('david.bowie@gmail.com', 'David', 'Bowie', 'yeah', TRUE, 'https://jobcafe-bucket.s3-ap-northeast-1.amazonaws.com/profile_picture.png');

INSERT INTO juser (email, first_name, last_name, password, phone, profession_label, membership_label, profile_picture)
VALUES ('john.doe@gmail.com', 'John', 'Doe', '10e06b990d44de0091a2113fd95c92fc905166af147aa7632639c41aa7f26b1620c47443813c605b924c05591c161ecc35944fc69c4433a49d10fc6b04a33611', '080-1111-2222', 'Accountant', 'Basic Plan', 'https://jobcafe-bucket.s3-ap-northeast-1.amazonaws.com/profile_picture.png');
INSERT INTO juser (email, first_name, last_name, password, phone, profession_label, membership_label, profile_picture)
VALUES ('jane.doe@gmail.com', 'Jane', 'Doe', '28949888ab4351793b519c5c11cf0aa24d429cab3b83d16762f7eb41ef0e9ab36a8795908305e91b407c210e78d5b5c9e201ec001a0d6a199246ed95f49548ae', '091-3333-4444', 'Clerk Accountant', 'Premium Plan', 'https://jobcafe-bucket.s3-ap-northeast-1.amazonaws.com/profile_picture.png');

INSERT INTO payment_method (card_issuer, cardholder_name, card_number, cvv, expiration_date, address, city, postal_code, state_province, country, payer_email)
VALUES ('MasterCard', 'John Doe', '1111-1111-1111-1111', 111, PARSEDATETIME('2018/11/01', 'yyyy/mm/dd'), '1st street of Washington', 'Seattle', '98105', 'Washington', 'USA', 'john.doe@gmail.com');
INSERT INTO payment_method (card_issuer, cardholder_name, card_number, cvv, expiration_date, address, city, postal_code, state_province, country, payer_email)
VALUES ('Visa', 'Jane Doe', '2222-2222-2222-2222', 222, PARSEDATETIME('2010/12/01', 'yyyy/mm/dd'), 'Shimotakaido 8-21-5', 'Tokyo', 'Suginami', '112-2103', 'Tokyo', 'jane.doe@gmail.com');

INSERT INTO ticket (title, owner_email, operator_email, category_label, is_open) VALUES ('Houston, I got a problem', 'john.doe@gmail.com', 'sarah.connor@gmail.com', 'Tutorial', true);
INSERT INTO ticket (title, owner_email, operator_email, category_label, is_open) VALUES ('How to make a pizza', 'john.doe@gmail.com', 'david.bowie@gmail.com', 'Guide', false);
INSERT INTO ticket (title, owner_email, operator_email, category_label, is_open) VALUES ('I do not have anymore a problem', 'john.doe@gmail.com', 'sarah.connor@gmail.com', 'Guide', false);

INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1);
INSERT INTO message (author_email, content, ticket_id) VALUES ('sarah.connor@gmail.com', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 1);
INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 1);

INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('david.bowie@gmail.com', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('david.bowie@gmail.com', 'Consequat interdum varius sit amet mattis vulputate enim nulla aliquet.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 2);
INSERT INTO message (author_email, content, ticket_id) VALUES ('john.doe@gmail.com', 'Vitae congue mauris rhoncus aenean vel elit scelerisque mauris pellentesque.', 2);

INSERT INTO plan_order (id, delivery_estimate, transactor_email, plan_label) VALUES ('RF120941', '2018-12-05', 'john.doe@gmail.com', 'Basic Plan');
INSERT INTO plan_order (id, delivery_estimate, transactor_email, plan_label) VALUES ('RF151093', '2018-12-15', 'jane.doe@gmail.com', 'Standard Plan');
