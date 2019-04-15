INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Linha Castelo Branco', null, 'Interior', 4496, 89906000, null);
INSERT INTO "Subject"("active", "address") VALUES (true, 4);
INSERT INTO "Organization"("subject", "denomination", "fancyName") VALUES (4, 'Camping Paraiso LTDA ME', 'Camping Lisot');
INSERT INTO "Company"("organization", "couponTitle", "couponSubtitle") VALUES (4, 'Camping', 'Lisot');
INSERT INTO "CompanyReceiptMethod"("company", "receiptMethod") VALUES (4, 'DIN');
INSERT INTO "CompanyPaymentMethod"("company", "paymentMethod") VALUES (4, 'DIN');
INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Rua São Paulo', '109' , 'São José', 4467, 89874000, 49991159377);
INSERT INTO "SubjectSubject"("parent", "child", "address", "level") VALUES (4, 1, 5, 7);
INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Linha Castelo Branco', null, 'Interior', 4496, 89906000, 49999221933);
INSERT INTO "SubjectSubject"("parent", "child", "address", "level") VALUES (4, 2, 6, 5);
INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Rua André Zago', '342', 'São Jorge', 4564, 89900000, 49991015531);
INSERT INTO "SubjectSubject"("parent", "child", "address", "level") VALUES (4, 3, 7, 1);



