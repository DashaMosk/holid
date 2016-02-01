package com.epam;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.entity.Holidays;
import com.epam.services.HolidayService;

@Named
@ViewScoped
public class ScheduleView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient HolidayService holService;
	
	@Autowired
	transient LoginController loginController;
	
    private transient ScheduleModel eventModel;
 
    private transient ScheduleEvent event = new DefaultScheduleEvent();

	public ScheduleView() {
		eventModel = new DefaultScheduleModel();
	}
	
	public void loadData() { 
		eventModel.clear();
		List<Holidays> events_ = holService.getAll();
     	for(Holidays h: events_) {
     		System.out.println(h.gethUser() + " "+ h.getId() + " "+ h.gethDate());
     		DefaultScheduleEvent shEve = new DefaultScheduleEvent(h.gethUser(), h.gethDate(), h.gethDate());
     		shEve.setDescription(String.valueOf(h.getId()));
     		eventModel.addEvent(shEve);
     	}
	}
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);        
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null) {
        	Holidays holiday = getHoliday();
        	Holidays holidayInBase =  holService.findByUserDate(holiday.gethUser(), holiday.gethDate());
        	if (holidayInBase == null) { //there is no information in database 
        		holService.save(holiday);
        		Holidays savedHoliday = holService.findByUserDate(holiday.gethUser(), holiday.gethDate());
        		DefaultScheduleEvent shEve =  new DefaultScheduleEvent(savedHoliday.gethUser(), savedHoliday.gethDate(), savedHoliday.gethDate());
        		shEve.setDescription(String.valueOf(savedHoliday.getId()));
        		eventModel.addEvent(shEve);
        	}
        }
        else {
            holService.edit(getHoliday());
            eventModel.updateEvent(event);
        }         
        event = new DefaultScheduleEvent();
    }
    
    public void deleteEvent(ActionEvent actionEvent) {  	
        if(event.getId() != null) {
            holService.delete(getHoliday());
            eventModel.deleteEvent(event);
            event = new DefaultScheduleEvent();
        }
    }
     
    public void onEventSelect(SelectEvent selectEvent) {    	
    		event = (ScheduleEvent) selectEvent.getObject();
    }
    
    public String showSchedule() {
    	return  "../schedule.xhtml?faces-redirect=true";
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
    	String title = loginController.getUsername();
        event = new DefaultScheduleEvent(title, (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
/*    public void onEventMove(ScheduleEntryMoveEvent eventEM) {
    	FacesMessage message = null;
    	int id = Integer.valueOf(eventEM.getScheduleEvent().getDescription());
    	Holidays hol =  holService.findById(id);
    	System.out.println(hol.getId() +", "+hol.gethDate());
    	if(eventEM.getDayDelta() > 0) {
    		hol.sethDate(Date.from(hol.gethDate().toInstant().plusSeconds(86400*eventEM.getDayDelta())));
    		System.out.println(hol.gethDate());
    	} else {
    		hol.sethDate(Date.from(hol.gethDate().toInstant().minusSeconds(86400*(-eventEM.getDayDelta()))));
    	}   
    	
    	if(loginController.isLoggedIn()) {
    		holService.edit(hol);
    		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Changes saved, Day delta:" + eventEM.getDayDelta()); 
    	} else {
    		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saving changes", "You should sign in for saving changes"); 
    	}
    	addMessage(message);      
    }*/
    
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());        
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());        
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private Holidays getHoliday() {
    	Holidays hol = new Holidays();
    	if (event.getId() != null) {
    		hol.setId(Integer.valueOf(event.getDescription()));
    	}
    	hol.sethDate(event.getStartDate());
    	hol.sethUser(event.getTitle());
    	hol.setDateSet(Timestamp.valueOf(LocalDateTime.now()));
    	hol.setIdUserSet(loginController.getUser().getIdUser());
    	System.out.println(hol.getId() + " "+ hol.gethUser() + hol.gethDate());
    	return hol;    	
    }
}