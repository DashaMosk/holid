package holidaysht;

import com.epam.dao.HolidaysDao;
import com.epam.entity.Holidays;
import com.epam.services.HolidayService;
import com.epam.services.HolidayServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HolidayServiceImplTest {
	
	@Mock
	HolidaysDao holidaysDao;
	
	@InjectMocks
	HolidayService holidayService = new HolidayServiceImpl();
	
	@Test
	public void saveTest() {
		Holidays holiday = new Holidays();
		holidayService.save(holiday);
		verify(holidaysDao).save(holiday);
	}
	
	@Test
	public void editTest() {
		Holidays holiday = new Holidays();
		holidayService.edit(holiday);
		verify(holidaysDao).edit(holiday);
	}
	
	@Test
	public void deleteTest() {
		Holidays holiday = new Holidays();
		holidayService.delete(holiday);
		verify(holidaysDao).delete(holiday);
	}
	
	@Test
	public void findByIdTest() {
		when(holidaysDao.findById(anyInt())).thenReturn(null);
		assertNull(holidayService.findById(anyInt()));
		verify(holidaysDao).findById(anyInt());
	}
	
	@Test
	public void findByUserDateTest() {
		when(holidaysDao.findByUserDate(anyString(), any(Date.class))).thenReturn(null);
		assertNull(holidayService.findByUserDate(anyString(), any(Date.class)));
		verify(holidaysDao).findByUserDate(anyString(), any(Date.class));
		
	}
	
	@Test
	public void getAllTest() {
		when(holidaysDao.getAll()).thenReturn(null);
		assertNull(holidayService.getAll());
		verify(holidaysDao).getAll();
	}

}
