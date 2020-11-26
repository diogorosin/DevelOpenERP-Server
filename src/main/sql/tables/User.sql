CREATE TABLE "User" (

    "identifier" INTEGER DEFAULT NEXTVAL('UserSequence') NOT NULL,

    "active" BOOLEAN NOT NULL,

    "image" VARCHAR(200),

    "address" INTEGER NOT NULL,

    "contact" INTEGER NOT NULL,    

	"name" VARCHAR(100) NOT NULL,

    "login" VARCHAR(254) NOT NULL,

    "password" VARCHAR(64) NOT NULL,

    "numericPassword" VARCHAR(4) NOT NULL,

    "company" INTEGER NOT NULL,

    "level" INTEGER NOT NULL

);