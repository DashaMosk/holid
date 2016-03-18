package com.epam;

import com.epam.entity.Booking;
import com.epam.entity.RoomCategory;
import com.epam.entity.Vacations;
import com.epam.services.VacationsService;
import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineAddEvent;
import org.primefaces.extensions.event.timeline.TimelineModificationEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Named
@ViewScoped
public class EditServerTimelineController implements Serializable {

    private TimelineModel model;
    private TimelineEvent event; // current event to be changed, edited, deleted or added
    private long zoomMax;
    private Date start;
    private Date end;
    private TimeZone timeZone = TimeZone.getTimeZone("Europe/Madrid");
    private boolean timeChangeable = true;

    @Autowired
    private VacationsService vacationsService;

    @PostConstruct
    protected void initialize() {
        // initial zooming is ca. one month to avoid hiding of event details (due to wide time range of events)
        zoomMax = 1000L * 60 * 60 * 24 * 360;

        // set initial start / end dates for the axis of the timeline (just for testing)
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        LocalDate localDate = LocalDate.now();
        cal.set(localDate.getYear(), Calendar.JANUARY, 1, 0, 0, 0);
        start = cal.getTime();
        cal.set(localDate.getYear(), Calendar.DECEMBER, 31, 0, 0, 0);
        end = cal.getTime();

        // create timeline model
        model = new TimelineModel();

        List<Vacations> vacationsList = vacationsService.getAll();
         for(Vacations vac : vacationsList) {
            LocalDate dStart = vac.getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dEnd = vac.getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            cal.set(dStart.getYear(), dStart.getMonthValue(), dStart.getDayOfMonth(), 0, 0, 0);
            Date begin = cal.getTime();
            cal.set(dEnd.getYear(), dEnd.getMonthValue(), dEnd.getDayOfMonth(), 23, 59, 59);
            Date endDate = cal.getTime();
            TimelineEvent event = new TimelineEvent(vac.getDateStart(), begin, endDate, true, vac.getUserName());
            model.add(event);

        }
    }

    public void onChange(TimelineModificationEvent e) {
        // get clone of the TimelineEvent to be changed with new start / end dates
        event = e.getTimelineEvent();

        // update booking in DB...

        // if everything was ok, no UI update is required. Only the model should be updated
        model.update(event);

        FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "The vacation data has been updated", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // otherwise (if DB operation failed) a rollback can be done with the same response as follows:
        // TimelineEvent oldEvent = model.getEvent(model.getIndex(event));
        // TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":mainForm:timeline");
        // model.update(oldEvent, timelineUpdater);
    }

    public void onEdit(TimelineModificationEvent e) {
        // get clone of the TimelineEvent to be edited
        event = e.getTimelineEvent();
    }

    public void onAdd(TimelineAddEvent e) {
        // get TimelineEvent to be added
        event = new TimelineEvent(e.getStartDate(), e.getStartDate(), e.getEndDate(), true, e.getGroup());

        // add the new event to the model in case if user will close or cancel the "Add dialog"
        // without to update details of the new event. Note: the event is already added in UI.
        model.add(event);
    }

    public void onDelete(TimelineModificationEvent e) {
        // get clone of the TimelineEvent to be deleted
        event = e.getTimelineEvent();
    }

    public void delete() {
        // delete booking in DB...

        // if everything was ok, delete the TimelineEvent in the model and update UI with the same response.
        // otherwise no server-side delete is necessary (see timelineWdgt.cancelDelete() in the p:ajax onstart).
        // we assume, delete in DB was successful
        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":timeline");
        model.delete(event, timelineUpdater);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The vacation has been deleted", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void saveDetails() {
        // save the updated booking in DB...

        // if everything was ok, update the TimelineEvent in the model and update UI with the same response.
        // otherwise no server-side update is necessary because UI is already up-to-date.
        // we assume, save in DB was successful
        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":timeline");
        model.update(event, timelineUpdater);

        FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "The vacation has been saved", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public TimelineModel getModel() {
        return model;
    }

    public TimelineEvent getEvent() {
        return event;
    }

    public void setEvent(TimelineEvent event) {
        this.event = event;
    }

    public long getZoomMax() {
        return zoomMax;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public boolean isTimeChangeable() {
        return timeChangeable;
    }

    public void toggleTimeChangeable() {
        timeChangeable = !timeChangeable;
    }

    public String getDeleteMessage() {
        String userName = ((Vacations) event.getData()).getUserName();
        if (userName == null) {
            return "Do you really want to delete the new vacation?";
        }

        return "Do you really want to delete the vacation for the user " + userName + "?";
    }

    public String showVacations() {
        return  "timeline.xhtml?faces-redirect=true";
    }

}