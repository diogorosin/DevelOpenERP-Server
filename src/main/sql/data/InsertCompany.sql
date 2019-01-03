INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Avenida Sul Brasil', '385', 'Centro', 4467, 89874000, 4936643500);
INSERT INTO "Subject"("active", "address") VALUES (true, 2);
INSERT INTO "Organization"("subject", "denomination", "fancyName") VALUES (2, 'Restaurante Degustare ME', 'Restaurante Degustare');
INSERT INTO "Company"("organization", "couponTitle", "couponSubtitle") VALUES (2, 'Restaurante', 'Degustare');
INSERT INTO "CompanyReceiptMethod"("company", "receiptMethod") VALUES (2, 'DIN');
INSERT INTO "CompanyPaymentMethod"("company", "paymentMethod") VALUES (2, 'DIN');
INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Rua São Paulo', '109', 'São José', 4467, 89874000, 49991159377);
INSERT INTO "SubjectSubject"("parent", "child", "address", "level") VALUES (2, 1, 3, 6);
