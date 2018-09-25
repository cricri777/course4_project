package ca.mcit.bigdata.cricri.stm.enrichment.service;

import ca.mcit.bigdata.cricri.stm.enrichment.dao.StmHiveDAO;
import ca.mcit.bigdata.cricri.stm.enrichment.dao.StopTimesCsvDAO;
import ca.mcit.bigdata.cricri.stm.enrichment.model.EnrichedTripModel;
import ca.mcit.bigdata.cricri.stm.enrichment.model.StopTimesModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StmService {

    @Autowired
    private StmHiveDAO stmHiveDAO;

    @Autowired
    private StopTimesCsvDAO stopTimesCsvDAO;

    public String execute() {
        Map<String, EnrichedTripModel> enrichedTripModelMap = stmHiveDAO.fetchEnrichTripModelMapTripID();

        // TODO put into hbase;


        //load StopTimesModel
        Map<String, List<StopTimesModel>> stopTimesModelList = stopTimesCsvDAO.loadObjectList(enrichedTripModelMap);


        // load up enrichTrip with StopTime
        //TODO enrich to hbase
        enrichedTripModelMap.entrySet()
                .stream()
                .limit(1000)
                .forEach(e -> {
                    if(stopTimesModelList.containsKey(e.getKey())){
                        e.getValue().setStopTimesModelList(stopTimesModelList.get(e.getKey()));
                        log.debug("set EnrichTrip with StopTime {}", e.getValue());
                    }
                });

        // TODO fetch from hbase
        // TODO load to hive

        return "";
    }

}
