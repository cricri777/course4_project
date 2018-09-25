package ca.mcit.bigdata.cricri.stm.enrichment.dao;

import ca.mcit.bigdata.cricri.stm.enrichment.model.EnrichedTripModel;
import ca.mcit.bigdata.cricri.stm.enrichment.model.StopTimesModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Component
public class StopTimesCsvDAO {

    @Value("${csv.stoptimes.file}")
    private String stopTimeFilePath;

    public Map<String, List<StopTimesModel>> loadObjectList(Map<String, EnrichedTripModel> enrichedTripModelMap) {
        Map<String, List<StopTimesModel>> resultStopTimesListMap = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(stopTimeFilePath))) {
            stream.forEach(line -> {
                String[] splitLine = line.split(",");
                if (splitLine.length == 5) {
                    StopTimesModel stopTimesModel = StopTimesModel.builder()
                            .trip_id(splitLine[0])
                            .arrival_time(splitLine[1])
                            .departure_time(splitLine[2])
                            .stop_id(splitLine[3])
                            .stop_sequence(splitLine[4])
                            .build();


                    if (resultStopTimesListMap.containsKey(stopTimesModel.getTrip_id())) {
                        List<StopTimesModel> stopTimesModelList = resultStopTimesListMap.get(stopTimesModel.getTrip_id());
                        stopTimesModelList.add(stopTimesModel);
                        resultStopTimesListMap.put(stopTimesModel.getTrip_id(), stopTimesModelList);

                    } else {
                        List<StopTimesModel> stopTimesModelList = new ArrayList<>();
                        stopTimesModelList.add(stopTimesModel);
                        resultStopTimesListMap.put(stopTimesModel.getTrip_id(), stopTimesModelList);
                    }
//                    log.debug("new stopTimesModel loaded{}", stopTimesModel);

                } else {
                    log.warn("csvfile {} format unexpected");
                }


            });

        } catch (IOException e) {
            log.warn("failed to stream csv file {} : {}", stopTimeFilePath, e);
        }
        return resultStopTimesListMap;
    }
}
