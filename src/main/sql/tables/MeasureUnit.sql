CREATE TABLE "MeasureUnit" (

	"identifier" INTEGER DEFAULT NEXTVAL('MeasureUnitSequence') NOT NULL,

	"denomination" VARCHAR(20) NOT NULL,

	"acronym" VARCHAR(4) NOT NULL,

    "group" INTEGER NOT NULL

);