CREATE TABLE "Company" (

	"identifier" INTEGER DEFAULT NEXTVAL('CompanySequence') NOT NULL,

    "active" BOOLEAN NOT NULL,

    "address" INTEGER NOT NULL,

    "denomination" VARCHAR(100) NOT NULL,

    "fancyName" VARCHAR(32),

	"couponTitle" VARCHAR(32),

	"couponSubtitle" VARCHAR(32)

);