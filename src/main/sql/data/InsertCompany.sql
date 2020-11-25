INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") 
VALUES ('Rua São Paulo', '109', 'São José', 4467, 89874000, 49991159377);

INSERT INTO "User"("active", "address", "name", "login", "password", "numericPassword", "lastLoggedInCompany") 
VALUES (true, 1, 'Diogo Buzin Rosin', 'diogorosin@gmail.com', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', '0190', null);

INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") 
VALUES ('Linha Castelo Branco', null, 'Interior', 4496, 89906000, null);

INSERT INTO "Company"("active", "address", "denomination", "fancyName", "couponTitle", "couponSubtitle") 
VALUES (true, 2, 'Camping Paraiso LTDA ME', 'Camping Lisot', 'Camping', 'Lisot');

INSERT INTO "CompanyReceiptMethod"("company", "receiptMethod") 
VALUES (1, 'DIN');

INSERT INTO "CompanyPaymentMethod"("company", "paymentMethod") 
VALUES (1, 'DIN');

INSERT INTO "CompanyUser"("company", "user", "level", "since") 
VALUES (1, 1, 7, NOW());