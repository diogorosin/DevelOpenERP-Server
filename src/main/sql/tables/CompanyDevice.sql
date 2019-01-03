CREATE TABLE "CompanyDevice" (

	"company" INTEGER NOT NULL,

	"device" INTEGER NOT NULL,

	"allow" BOOLEAN NOT NULL DEFAULT FALSE,

	"alias" VARCHAR(12) NOT NULL

);
