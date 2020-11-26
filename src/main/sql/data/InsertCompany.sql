INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode") 
VALUES ('Av Araucaria', 122, 'Centro', 4467, 89874000);

INSERT INTO "Contact"("phone", "cellPhone", "email", "webSite") 
VALUES (4936641234, null, null, 'www.mercadoxyz.com.br');

INSERT INTO "Company"("active", "address", "contact", "denomination", "fancyName", "couponTitle", "couponSubtitle") 
VALUES (true, 1, 1, 'Mercado XYZ LDTA', 'Mercado XYZ', 'Mercado', 'XYZ');

INSERT INTO "CompanyReceiptMethod"("company", "receiptMethod") 
VALUES (1, 'DIN');

INSERT INTO "CompanyPaymentMethod"("company", "paymentMethod") 
VALUES (1, 'DIN');


INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode") 
VALUES ('Rua São Paulo', '109', 'São José', 4467, 89874000);

INSERT INTO "Contact"("phone", "cellPhone", "email", "webSite") 
VALUES (null, 49991159377, 'diogorosin@gmail.com', null);

INSERT INTO "User"("active", "address", "contact", "name", "login", "password", "numericPassword", "company", "level") 
VALUES (true, 2, 2, 'Diogo Buzin Rosin', 'diogorosin@gmail.com', '5E112B71A8BEAED6B0A1F475DF800624F6178A69A1015261FE389497923351E6', '0190', 1, 70);
