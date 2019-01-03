CREATE TABLE "ProductProduct" (

	"parent" INTEGER NOT NULL,

	"child" INTEGER NOT NULL,

	"active" BOOLEAN NOT NULL,

	"quantity" NUMERIC(14,4) DEFAULT 0

);
