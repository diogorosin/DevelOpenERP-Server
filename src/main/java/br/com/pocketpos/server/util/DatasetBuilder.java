package br.com.pocketpos.server.util;

import java.util.List;

import br.com.pocketpos.server.bean.DatasetBean;
import br.com.pocketpos.server.orm.Catalog;
import br.com.pocketpos.server.orm.Company;
import br.com.pocketpos.server.orm.CompanyDevice;
import br.com.pocketpos.server.orm.MeasureUnit;
import br.com.pocketpos.server.orm.Product;
import br.com.pocketpos.server.orm.SubjectSubject;


public abstract interface DatasetBuilder {

	public abstract DatasetBuilder withCompany(Company company);

	public abstract DatasetBuilder withDevices(List<CompanyDevice> devices);

	public abstract DatasetBuilder withSubjects(List<SubjectSubject> subjects);

	public abstract DatasetBuilder withMeasureUnits(List<MeasureUnit> measureUnits);

	public abstract DatasetBuilder withProducts(List<Product> products);	

	public abstract DatasetBuilder withCatalogs(List<Catalog> catalogs);
	
	public abstract DatasetBean build();

}