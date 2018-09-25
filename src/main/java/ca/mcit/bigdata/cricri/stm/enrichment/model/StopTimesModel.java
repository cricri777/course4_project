package ca.mcit.bigdata.cricri.stm.enrichment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopTimesModel {
    private String trip_id;
    private String arrival_time;
    private String departure_time;
    private String stop_id;
    private String stop_sequence;
}
