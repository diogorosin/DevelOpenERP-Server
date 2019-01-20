package br.com.developen.erp.util;

import java.util.List;

import br.com.developen.erp.bean.DatasetBean;
import br.com.developen.erp.orm.Catalog;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDevice;
import br.com.developen.erp.orm.CompanyPaymentMethod;
import br.com.developen.erp.orm.CompanyReceiptMethod;
import br.com.developen.erp.orm.MeasureUnit;
import br.com.developen.erp.orm.Progeny;
import br.com.developen.erp.orm.SubjectSubject;


public abstract interface DatasetBuilder {

	public abstract DatasetBuilder withCompany(Company company);

	public abstract DatasetBuilder withDevices(List<CompanyDevice> devices);

	public abstract DatasetBuilder withSubjects(List<SubjectSubject> subjects);

	public abstract DatasetBuilder withMeasureUnits(List<MeasureUnit> measureUnits);

	public abstract DatasetBuilder withProgenies(List<Progeny> progenies);

	public abstract DatasetBuilder withCatalogs(List<Catalog> catalogs);

	public abstract DatasetBuilder withReceiptMethods(List<CompanyReceiptMethod> receiptMethods);

	public abstract DatasetBuilder withPaymentMethods(List<CompanyPaymentMethod> paymentMethods);
	
	public abstract DatasetBean build();

}