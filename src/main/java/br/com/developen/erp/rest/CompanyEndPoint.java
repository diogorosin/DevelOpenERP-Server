package br.com.developen.erp.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import br.com.developen.erp.bean.CompanyBean001;
import br.com.developen.erp.bean.ExceptionBean001;
import br.com.developen.erp.exception.InvalidRangeException;
import br.com.developen.erp.orm.Company;
import br.com.developen.erp.orm.CompanyDAO;
import br.com.developen.erp.orm.Level;
import br.com.developen.erp.util.HibernateUtil;
import br.com.developen.erp.util.I18N;
import br.com.developen.erp.util.Range;
import br.com.developen.erp.util.RangeUtil;

@Path("/company")
@Authentication(level=Level.CASHIER)
public class CompanyEndPoint {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@Context HttpServletRequest request){

		Session session = HibernateUtil.getSessionFactory().openSession();

		CompanyDAO companyDAO = new CompanyDAO(session);

		int rowCount = -1;

		List<Range> rangeList = new ArrayList<>();

		String rangeString = request.getHeader("Range");

		if (rangeString != null) {

			rowCount = companyDAO.getCount();

			try{

				rangeList = RangeUtil.splitRanges(rangeString, rowCount);

			} catch (InvalidRangeException e){

				return Response.status(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE).
						entity(new ExceptionBean001(I18N.get(I18N.REQUESTED_RANGE_NOT_SATISFIABLE))).
						build();

			}

		}		

		if (rangeList.isEmpty()) {

			List<CompanyBean001> companyBeans = new ArrayList<CompanyBean001>();

			for (Company company : companyDAO.list()){

				CompanyBean001 companyBean = new CompanyBean001();

				companyBean.setIdentifier(company.getIdentifier());

				companyBean.setDenomination(company.getDenomination());

				companyBean.setFancyName(company.getFancyName());

				companyBean.setActive(company.getActive());

				companyBean.setCouponTitle(company.getCouponTitle());				

				companyBean.setCouponSubtitle(company.getCouponSubtitle());

				companyBeans.add(companyBean);


			}

			return Response.status(Response.Status.OK).
					entity(companyBeans).
					build();

		} else {

			List<CompanyBean001> companyBeans = new ArrayList<CompanyBean001>();

			for (Range myRange : rangeList) {

				for (Company company : companyDAO.list(myRange.getStart()-1, (myRange.getEnd()-myRange.getStart())+1)){

					CompanyBean001 companyBean = new CompanyBean001();

					companyBean.setIdentifier(company.getIdentifier());

					companyBean.setActive(company.getActive());

					companyBean.setDenomination(company.getDenomination());

					companyBean.setFancyName(company.getFancyName());
					
					companyBean.setCouponTitle(company.getCouponTitle());				

					companyBean.setCouponSubtitle(company.getCouponSubtitle());

					companyBeans.add(companyBean);

				}

			}

			rangeString = rangeList.stream().map(Object::toString)
					.collect(Collectors.joining(","));

			return Response.
					status(Response.Status.PARTIAL_CONTENT).
					header("Accept-Ranges", "rows").
					header("Content-Range", "rows=" + rangeString + "/" + rowCount).
					entity(companyBeans).
					build();

		}

	}

}