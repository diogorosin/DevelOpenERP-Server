package br.com.developen.erp.util;

import java.util.List;

import br.com.developen.erp.bean.DatasetBean;
import br.com.developen.erp.orm.Catalog;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDeviceSale;
import br.com.developen.erp.orm.CompanyPaymentMethod;
import br.com.developen.erp.orm.CompanyReceiptMethod;
import br.com.developen.erp.orm.Device;
import br.com.developen.erp.orm.MeasureUnit;
import br.com.developen.erp.orm.Progeny;
import br.com.developen.erp.orm.SubjectSubject;


public abstract interface CompanyDeviceDatasetBuilder {

	public abstract CompanyDeviceDatasetBuilder withCompany(Company company);

	public abstract CompanyDeviceDatasetBuilder withDevice(Device device);

	public abstract CompanyDeviceDatasetBuilder withAlias(String alias);

	public abstract CompanyDeviceDatasetBuilder withAllow(Boolean allow);

	public abstract CompanyDeviceDatasetBuilder withSubjects(List<SubjectSubject> subjects);

	public abstract CompanyDeviceDatasetBuilder withMeasureUnits(List<MeasureUnit> measureUnits);

	public abstract CompanyDeviceDatasetBuilder withProgenies(List<Progeny> progenies);

	public abstract CompanyDeviceDatasetBuilder withCatalogs(List<Catalog> catalogs);

	public abstract CompanyDeviceDatasetBuilder withReceiptMethods(List<CompanyReceiptMethod> receiptMethods);

	public abstract CompanyDeviceDatasetBuilder withPaymentMethods(List<CompanyPaymentMethod> paymentMethods);

	public abstract CompanyDeviceDatasetBuilder withSales(List<CompanyDeviceSale> sales);

	public abstract DatasetBean build();

}