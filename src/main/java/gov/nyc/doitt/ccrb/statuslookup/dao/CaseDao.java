package gov.nyc.doitt.ccrb.statuslookup.dao;

import gov.nyc.doitt.ccrb.statuslookup.domain.CaseEntity;

public interface CaseDao {
	public CaseEntity getCase(Integer case_id);
	public int getYearsToLookBack();
	public void setYearsToLookBack(int yearsToLookBack);
}
