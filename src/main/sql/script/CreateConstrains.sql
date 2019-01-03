/*PRIMARY KEYS*/
ALTER TABLE "Country" ADD CONSTRAINT "CountryPK" PRIMARY KEY("identifier");

ALTER TABLE "State" ADD CONSTRAINT "StatePK" PRIMARY KEY("identifier");

ALTER TABLE "City" ADD CONSTRAINT "CityPK" PRIMARY KEY("identifier");

ALTER TABLE "Address" ADD CONSTRAINT "AddressPK" PRIMARY KEY("identifier");

ALTER TABLE "Subject" ADD CONSTRAINT "SubjectPK" PRIMARY KEY("identifier");

ALTER TABLE "SubjectSubject" ADD CONSTRAINT "SubjectSubjectPK" PRIMARY KEY("parent", "child");

ALTER TABLE "Individual" ADD CONSTRAINT "IndividualPK" PRIMARY KEY("subject");

ALTER TABLE "Organization" ADD CONSTRAINT "OrganizationPK" PRIMARY KEY("subject");

ALTER TABLE "Company" ADD CONSTRAINT "CompanyPK" PRIMARY KEY("organization");

ALTER TABLE "User" ADD CONSTRAINT "UserPK" PRIMARY KEY("individual");

ALTER TABLE "Token" ADD CONSTRAINT "TokenPK" PRIMARY KEY("identifier");

ALTER TABLE "Catalog" ADD CONSTRAINT "CatalogPK" PRIMARY KEY("identifier");

ALTER TABLE "MeasureUnit" ADD CONSTRAINT "MeasureUnitPK" PRIMARY KEY("identifier");

ALTER TABLE "MeasureUnitMeasureUnit" ADD CONSTRAINT "MeasureUnitMeasureUnitPK" PRIMARY KEY("from", "to");

ALTER TABLE "Progeny" ADD CONSTRAINT "ProgenyPK" PRIMARY KEY("identifier");

ALTER TABLE "Service" ADD CONSTRAINT "ServicePK" PRIMARY KEY("progeny");

ALTER TABLE "Product" ADD CONSTRAINT "ProductPK" PRIMARY KEY("progeny");

ALTER TABLE "ProductProduct" ADD CONSTRAINT "ProductProductPK" PRIMARY KEY("parent", "child");

ALTER TABLE "Merchandise" ADD CONSTRAINT "MerchandisePK" PRIMARY KEY("product");

ALTER TABLE "Device" ADD CONSTRAINT "DevicePK" PRIMARY KEY("identifier"); 

ALTER TABLE "CompanyDevice" ADD CONSTRAINT "CompanyDevicePK" PRIMARY KEY("company", "device");

ALTER TABLE "ReceiptMethod" ADD CONSTRAINT "ReceiptMethodPK" PRIMARY KEY("identifier");

ALTER TABLE "PaymentMethod" ADD CONSTRAINT "PaymentMethodPK" PRIMARY KEY("identifier");

ALTER TABLE "CompanyReceiptMethod" ADD CONSTRAINT "CompanyReceiptMethodPK" PRIMARY KEY("company", "receiptMethod");

ALTER TABLE "CompanyPaymentMethod" ADD CONSTRAINT "CompanyPaymentMethodPK" PRIMARY KEY("company", "paymentMethod");

/*FOREIGN KEYS*/

ALTER TABLE "State" ADD CONSTRAINT "StateCountryFK" FOREIGN KEY("country") REFERENCES "Country"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "City" ADD CONSTRAINT "CityStateFK" FOREIGN KEY("state") REFERENCES "State"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Address" ADD CONSTRAINT "AddressCityFK" FOREIGN KEY("city") REFERENCES "City"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Subject" ADD CONSTRAINT "SubjectAddressFK" FOREIGN KEY("address") REFERENCES "Address"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "SubjectSubject" ADD CONSTRAINT "SubjectSubjectParentFK" FOREIGN KEY("parent") REFERENCES "Subject"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "SubjectSubject" ADD CONSTRAINT "SubjectSubjectChildFK" FOREIGN KEY("child") REFERENCES "Subject"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "SubjectSubject" ADD CONSTRAINT "SubjectSubjectAddressFK" FOREIGN KEY("address") REFERENCES "Address"("identifier") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "Individual" ADD CONSTRAINT "IndividualSubjectFK" FOREIGN KEY("subject") REFERENCES "Subject"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Organization" ADD CONSTRAINT "OrganizationSubjectFK" FOREIGN KEY("subject") REFERENCES "Subject"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Company" ADD CONSTRAINT "CompanyOrganizationFK" FOREIGN KEY("organization") REFERENCES "Organization"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "User" ADD CONSTRAINT "UserIndividualFK" FOREIGN KEY("individual") REFERENCES "Individual"("subject") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Token" ADD CONSTRAINT "TokenCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("organization") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Token" ADD CONSTRAINT "TokenSubjectSubjectFK" FOREIGN KEY("company", "user") REFERENCES "SubjectSubject"("parent", "child") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "Catalog" ADD CONSTRAINT "CatalogCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("organization") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "MeasureUnitMeasureUnit" ADD CONSTRAINT "MeasureUnitFromFK" FOREIGN KEY("from") REFERENCES "MeasureUnit"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "MeasureUnitMeasureUnit" ADD CONSTRAINT "MeasureUnitToFK" FOREIGN KEY("to") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Progeny" ADD CONSTRAINT "ProgenyCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("organization") ON DELETE CASCADE ON 
UPDATE CASCADE;

ALTER TABLE "Service" ADD CONSTRAINT "ServiceProgenyFK" FOREIGN KEY("progeny") REFERENCES "Progeny"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Service" ADD CONSTRAINT "ServiceCatalogFK" FOREIGN KEY("catalog") REFERENCES "Catalog"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Service" ADD CONSTRAINT "ServiceMeasureUnitFK" FOREIGN KEY("measureUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Product" ADD CONSTRAINT "ProductProgenyFK" FOREIGN KEY("progeny") REFERENCES "Progeny"("identifier") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductWidthUnitFK" FOREIGN KEY("widthUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductHeightUnitFK" FOREIGN KEY("heightUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductLengthUnitFK" FOREIGN KEY("lengthUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductContentUnitFK" FOREIGN KEY("contentUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductGrossWeightUnitFK" FOREIGN KEY("grossWeightUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Product" ADD CONSTRAINT "ProductNetWeightUnitFK" FOREIGN KEY("netWeightUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "ProductProduct" ADD CONSTRAINT "ProductProductProductFK" FOREIGN KEY("parent") REFERENCES "Product"("progeny") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "ProductProduct" ADD CONSTRAINT "ProductProductPartFK" FOREIGN KEY("child") REFERENCES "Product"("progeny") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Merchandise" ADD CONSTRAINT "MerchandiseProductFK" FOREIGN KEY("product") REFERENCES "Product"("progeny") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "Merchandise" ADD CONSTRAINT "MerchandiseCatalogFK" FOREIGN KEY("catalog") REFERENCES "Catalog"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "Merchandise" ADD CONSTRAINT "MerchandiseMeasureUnitFK" FOREIGN KEY("measureUnit") REFERENCES "MeasureUnit"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "CompanyDevice" ADD CONSTRAINT "CompanyDeviceCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("organization") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "CompanyDevice" ADD CONSTRAINT "CompanyDeviceDeviceFK" FOREIGN KEY("device") REFERENCES "Device"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "CompanyReceiptMethod" ADD CONSTRAINT "CompanyReceiptMethodCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("organization") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "CompanyReceiptMethod" ADD CONSTRAINT "CompanyReceiptMethodReceiptMethodFK" FOREIGN KEY("receiptMethod") REFERENCES "ReceiptMethod"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "CompanyPaymentMethod" ADD CONSTRAINT "CompanyPaymentMethodCompanyFK" FOREIGN KEY("company") REFERENCES "Company"("organization") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "CompanyPaymentMethod" ADD CONSTRAINT "CompanyPaymentMethodPaymentMethodFK" FOREIGN KEY("paymentMethod") REFERENCES "PaymentMethod"("identifier") ON DELETE RESTRICT ON UPDATE CASCADE;
