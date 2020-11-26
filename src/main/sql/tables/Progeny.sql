CREATE TABLE "Progeny" (

	"identifier" INTEGER DEFAULT NEXTVAL('ProgenySequence') NOT NULL,
	
	"active" BOOLEAN NOT NULL,

	"denomination" VARCHAR(50) NOT NULL,

	"company" INTEGER NOT NULL

);