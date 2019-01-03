CREATE TABLE "Device" (

	"identifier" INTEGER DEFAULT NEXTVAL('DeviceSequence') NOT NULL,

	"active" BOOLEAN NOT NULL,

	"serialNumber" VARCHAR(16) NOT NULL,

	"manufacturer" VARCHAR(30) NOT NULL,

	"model" VARCHAR(20) NOT NULL

);
