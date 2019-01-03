DELETE FROM "MeasureUnit";

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (1, 'MILíMETRO', 'mm', 0);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (2, 'CENTíMETRO', 'cm', 0);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (3, 'DECíMETRO', 'dm', 0);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (4, 'METRO', 'm', 0);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (5, 'DECÂMETRO', 'dam', 0);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (6, 'HECTÓMETRO', 'hm', 0);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (7, 'QUILÔMETRO', 'km', 0);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (01, 01, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (01, 02, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (01, 03, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (01, 04, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (01, 05, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (02, 01, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (02, 02, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (02, 03, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (02, 04, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (02, 05, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (02, 06, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 01, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 02, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 03, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 04, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 05, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 06, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (03, 07, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 01, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 02, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 03, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 04, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 05, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 06, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (04, 07, 0.001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 01, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 02, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 03, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 04, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 05, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 06, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (05, 07, 0.01);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 01, 100000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 02, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 03, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 04, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 05, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 06, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (06, 07, 0.1);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 01, 1000000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 02, 100000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 03, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 04, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 05, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 06, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (07, 07, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (08, 'MILIGRAMA', 'mg', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (09, 'CENTIGRAMA', 'cg', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (10, 'DECIGRAMA', 'dg', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (11, 'GRAMA', 'g', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (12, 'DECAGRAMA', 'dag', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (13, 'HECTOGRAMA', 'hg', 1);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (14, 'QUILOGRAMA', 'kg', 1);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (08, 08, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (08, 09, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (08, 10, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (08, 11, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (08, 12, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (09, 08, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (09, 09, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (09, 10, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (09, 11, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (09, 12, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (09, 13, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 08, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 09, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 10, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 11, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 12, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 13, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (10, 14, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 08, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 09, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 10, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 11, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 12, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 13, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (11, 14, 0.001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 08, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 09, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 10, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 11, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 12, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 13, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (12, 14, 0.01);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 08, 100000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 09, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 10, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 11, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 12, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 13, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (13, 14, 0.1);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 08, 1000000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 09, 100000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 10, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 11, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 12, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 13, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (14, 14, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (15, 'MILILITRO', 'ml', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (16, 'CENTILITRO', 'cl', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (17, 'DECILITRO', 'dl', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (18, 'LITRO', 'l', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (19, 'DECALITRO', 'dal', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (20, 'HECTOLITRO', 'hl', 2);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (21, 'QUILOLITRO', 'kl', 2);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (15, 15, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (15, 16, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (15, 17, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (15, 18, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (15, 19, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (16, 15, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (16, 16, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (16, 17, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (16, 18, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (16, 19, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (16, 20, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 15, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 16, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 17, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 18, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 19, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 20, 0.001);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (17, 21, 0.0001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 15, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 16, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 17, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 18, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 19, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 20, 0.01);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (18, 21, 0.001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 15, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 16, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 17, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 18, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 19, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 20, 0.1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (19, 21, 0.01);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 15, 100000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 16, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 17, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 18, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 19, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 20, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (20, 21, 0.1);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 15, 1000000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 16, 100000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 17, 10000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 18, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 19, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 20, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (21, 21, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (22, 'MILISSEGUNDO', 'ms', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (23, 'SEGUNDO', 's', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (24, 'MINUTO', 'min', 3);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (25, 'HORA', 'h', 3);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (22, 22, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (22, 23, 0.001);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (23, 22, 1000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (23, 23, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (23, 24, 0.02);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (24, 22, 60000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (24, 23, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (24, 24, 1);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (24, 25, 0.016);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (25, 22, 3600000);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (25, 23, 3600);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (25, 24, 60);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (25, 25, 1);

INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (26, 'UNIDADE', 'un',   4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (27, 'DEZENA',  'dez',  4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (28, 'DUZIA',   'dz',   4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (29, 'CENTENA', 'cent', 4);
INSERT INTO "MeasureUnit" ("identifier", "denomination", "acronym", "group") VALUES (30, 'MILHAR',  'mil',  4);

INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (27, 26, 10);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (28, 26, 12);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (29, 26, 100);
INSERT INTO "MeasureUnitMeasureUnit" ("from", "to", "factor") VALUES (30, 26, 1000);
