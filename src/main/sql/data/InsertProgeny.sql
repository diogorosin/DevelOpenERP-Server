INSERT INTO "Progeny"("active", "denomination", "company") 
VALUES (true, 'Refrigerante Coca-Cola 350ml', 2);

INSERT INTO "Product"("progeny", "stockUnit") 
VALUES (1, 26);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "price")
VALUES (1, 1, 0, 1, 'Coca-Cola 350ml', 5.00);
/*------------------------------*/
INSERT INTO "Progeny"("active", "denomination", "company") 
VALUES (true, 'Vianda', 2);

INSERT INTO "Product"("progeny", "stockUnit") 
VALUES (2, 26);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "price")
VALUES (2, 2, 0, 2, 'Vianda', 15.00);
/*------------------------------*/
INSERT INTO "Progeny"("active", "denomination", "company") 
VALUES (true, 'A La Minuta', 2);

INSERT INTO "Product"("progeny", "stockUnit") 
VALUES (3, 14);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "price")
VALUES (3, 2, 1, 3, 'A La Minuta', 25.00);
/*------------------------------*/
INSERT INTO "Progeny"("active", "denomination", "company")
VALUES (true, 'Combo Vianda + Coca-Cola 350ml', 2);

INSERT INTO "Product"("progeny", "stockUnit") 
VALUES (4, 26);

INSERT INTO "ProductProduct"("parent", "child", "active", "quantity")
VALUES (4, 1, true, 1);

INSERT INTO "ProductProduct"("parent", "child", "active", "quantity")
VALUES (4, 3, true, 1);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "price")
VALUES (4, 3, 2, 4, 'Vianda + Coca-Cola 350ml', 25.00);