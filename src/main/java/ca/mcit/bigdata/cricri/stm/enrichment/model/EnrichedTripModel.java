package ca.mcit.bigdata.cricri.stm.enrichment.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EnrichedTripModel {

    //generic
    private String route_id;
    private String service_id;
    private String trip_id;

    //trips
    private String trip_headsign;
    private String direction_id;
    private String shape_id;
    private String wheelchair_accessible;
    private String note_fr;
    private String note_en;

    //calendar_dates
    private String calendar_date; //date has been replaced by calendar_date to prevent date collision
    private String exception_type;

    //frequencies
    private String start_time;
    private String end_time;
    private String headway_secs;

    //stop_times
    List<StopTimesModel> stopTimesModelList;
}
