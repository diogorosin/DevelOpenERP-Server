INSERT INTO "Address"("denomination", "number", "district", "city", "postalCode", "phone") VALUES ('Rua São Paulo', '109', 'São José', 4467, 89874000, 49991159377);
INSERT INTO "Subject"("active", "address") VALUES (true, 1);
INSERT INTO "Individual"("subject", "name") VALUES (1, 'Diogo Buzin Rosin');
INSERT INTO "User"("individual", "login", "password") VALUES (1, 'diogorosin@gmail.com', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918');
