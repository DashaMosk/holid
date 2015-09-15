package com.bionic.edu;
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
 
@Named
@ViewScoped
public class ScheduleView implements Serializable {

	private static final long serialVersionUID = -6443743732704315328L;

	@Inject
	HolidayService holService;
	
    private ScheduleModel eventModel;
 
    private ScheduleEvent event = new DefaultScheduleEvent();

	public ScheduleView() {
		eventModel = new DefaultScheduleModel();
	}
	
	public void loadData() { 
		eventModel.clear();
		List<Holidays> events_ = holService.getAll();
     	for(Holidays h: events_) {
     		System.out.println(h);
     		DefaultScheduleEvent shEve = new DefaultScheduleEvent(h.gethUser(), h.gethDate(), h.gethDate());
     		shEve.setDescription(String.valueOf(h.getId()));
     		eventModel.addEvent(shEve);
     	}
	}
	
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
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
            holService.save(getHoliday());
            eventModel.addEvent(event);
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
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
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
    	hol.setIdUserSet(1); //temporary
    	return hol;    	
    }
}