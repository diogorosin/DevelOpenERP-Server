package br.com.pocketpos.server.util;

import java.util.List;

import br.com.pocketpos.server.bean.DatasetBean;
import br.com.pocketpos.server.orm.Catalog;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.CompanyPayment;
import br.com.pocketpos.server.orm.MeasureUnit;
import br.com.pocketpos.server.orm.Progeny;
import br.com.pocketpos.server.orm.SubjectSubject;


public abstract interface DatasetBuilder {

	public abstract DatasetBuilder withCompany(Company company);

	public abstract DatasetBuilder withDevices(List<CompanyDevice> devices);

	public abstract DatasetBuilder withSubjects(List<SubjectSubject> subjects);

	public abstract DatasetBuilder withMeasureUnits(List<MeasureUnit> measureUnits);

	public abstract DatasetBuilder withProgenies(List<Progeny> progeny);	

	public abstract DatasetBuilder withCatalogs(List<Catalog> catalogs);
	
	public abstract DatasetBuilder withPayments(List<CompanyPayment> payments);
	
	public abstract DatasetBean build();

}