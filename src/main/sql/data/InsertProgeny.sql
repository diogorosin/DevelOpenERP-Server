INSERT INTO "Progeny"("active", "denomination", "company") 
VALUES (true, 'Refrigerante Coca-Cola 350ml', 2);

INSERT INTO "Product"("progeny") 
VALUES (1);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "measureUnit", "price")
VALUES (1, 1, 0, 1, 'Coca-Cola 350ml', 26, 5.00);
/*------------------------------*/
INSERT INTO "Progeny"("active", "denomination", "company") 
VALUES (true, 'Vianda', 2);

INSERT INTO "Product"("progeny") 
VALUES (2);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "measureUnit", "price")
VALUES (2, 2, 0, 2, 'Vianda', 26, 15.00);
/*------------------------------*/
INSERT INTO "Progeny"("active", "denomination", "company") 
VALUES (true, 'A La Minuta', 2);

INSERT INTO "Product"("progeny") 
VALUES (3);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "measureUnit", "price")
VALUES (3, 2, 1, 3, 'A La Minuta', 26, 25.00);
/*------------------------------*/
INSERT INTO "Progeny"("active", "denomination", "company")
VALUES (true, 'Combo Vianda + Coca-Cola 350ml', 2);

INSERT INTO "Product"("progeny") 
VALUES (4);

INSERT INTO "ProductProduct"("parent", "child", "active", "quantity")
VALUES (4, 1, true, 1);

INSERT INTO "ProductProduct"("parent", "child", "active", "quantity")
VALUES (4, 3, true, 1);

INSERT INTO "Merchandise"("product", "catalog", "position", "reference", "label", "measureUnit", "price")
VALUES (4, 3, 2, 4, 'Vianda + Coca-Cola 350ml', 26, 25.00);
