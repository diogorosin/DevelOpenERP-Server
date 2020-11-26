CREATE TABLE "Address" (

	"identifier" INTEGER DEFAULT NEXTVAL('AddressSequence') NOT NULL,

	"denomination" VARCHAR(100),

	"number" VARCHAR(5),

	"complement" VARCHAR(10),

	"district" VARCHAR(100),

	"postalCode" INTEGER,

	"city" INTEGER

);