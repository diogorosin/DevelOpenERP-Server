CREATE TABLE "Subject" (

    "identifier" INTEGER DEFAULT NEXTVAL('SubjectSequence') NOT NULL,

    "active" BOOLEAN NOT NULL,

    "address" INTEGER NOT NULL,
    
    "company" INTEGER NOT NULL

);