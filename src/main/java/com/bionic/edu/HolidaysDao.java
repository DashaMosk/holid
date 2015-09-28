package com.bionic.edu;
import java.util.Date;
import java.util.List;

public interface HolidaysDao {
	public void save(Holidays holiday);
	public void edit(Holidays holiday);
	public void delete(Holidays holiday);
	public Holidays findById(int id);
	public List<Holidays> getAll();
	public Holidays findByUserDate(String user, Date date);
}
