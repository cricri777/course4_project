package ca.mcit.bigdata.cricri.stm.enrichment.dao;

import ca.mcit.bigdata.cricri.stm.enrichment.model.EnrichedTripModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class StmHiveDAO {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    @Value("${hive.uri}")
    private String hiveUri;

    @Value("${hive.username}")
    private String hiveUsername;

    @Value("${hive.password}")
    private String hivePassword;

    public Map<String, EnrichedTripModel> fetchEnrichTripModelMapTripID() {

        //trip_id
        Map<String , EnrichedTripModel> enrichedTripModelMap = new HashMap<>();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(hiveUri, hiveUsername, hivePassword);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM ext_Trips T JOIN ext_calendar_dates CD, ext_frequencies F WHERE  T.service_id=CD.service_id AND F.trip_id=T.trip_id";
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                EnrichedTripModel enrichedTripModel = EnrichedTripModel.builder()
                        .route_id(res.getString("route_id"))
                        .service_id(res.getString("service_id"))
                        .trip_id(res.getString("trip_id"))
                        .trip_headsign(res.getString("trip_headsign"))
                        .direction_id(res.getString("direction_id"))
                        .shape_id(res.getString("shape_id"))
                        .wheelchair_accessible(res.getString("wheelchair_accessible"))
                        .note_fr(res.getString("note_fr"))
                        .note_en(res.getString("note_en"))
                        .calendar_date(res.getString("calendar_date"))
                        .exception_type(res.getString("exception_type"))
                        .start_time(res.getString("start_time"))
                        .end_time(res.getString("end_time"))
                        .headway_secs(res.getString("headway_secs"))
                        .build();
                enrichedTripModelMap.put(enrichedTripModel.getTrip_id() , enrichedTripModel);
            }

        } catch (SQLException e) {
            log.warn("query hive failed", e);
        }

        return enrichedTripModelMap;
    }


}
