package gov.nyc.doitt.ccrb.statuslookup.dao;

import java.util.Calendar;
import java.util.Date;

import gov.nyc.doitt.ccrb.statuslookup.domain.CaseEntity;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("caseDAO")
@Transactional
public class CaseDaoImpl implements CaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Value("${ccrbsl.yearstolookback}")
	private int yearsToLookBack;
	
	@Override
	public CaseEntity getCase(Integer id) {
		//Make sure result is lte yearsToLookBack years
		Date today=new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.YEAR, -yearsToLookBack);
		Date ago = cal.getTime();
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(CaseEntity.class);
		criteria.add(Restrictions.eq("case_id", id));
		criteria.add(Restrictions.ge("recieved_date", ago));
		return (CaseEntity) criteria.uniqueResult();
	}
	
	@Override
	public void setYearsToLookBack(int yearsToLookBack) {
		this.yearsToLookBack = yearsToLookBack;
	}

	@Override
	public int getYearsToLookBack() {
		return yearsToLookBack;
	}
	
	
}
