CREATE TABLE "CompanyDeviceSaleItem" (

	"company" INTEGER NOT NULL,

	"device" INTEGER NOT NULL,

	"sale" INTEGER NOT NULL,

	"item" INTEGER NOT NULL,

	"progeny" INTEGER NOT NULL,

	"quantity" NUMERIC(14,4) NOT NULL,

	"measureUnit" INTEGER NOT NULL,

	"price" NUMERIC(14,2) NOT NULL,

	"total" NUMERIC(14,2) NOT NULL

);
