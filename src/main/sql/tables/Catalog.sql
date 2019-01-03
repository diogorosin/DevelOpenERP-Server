CREATE TABLE "Catalog" (

	"identifier" INTEGER DEFAULT NEXTVAL('CatalogSequence') NOT NULL,

    	"position" INTEGER NOT NULL DEFAULT 0,

	"denomination" VARCHAR(20) NOT NULL,

    	"company" INTEGER NOT NULL

);
