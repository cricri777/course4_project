//package ca.mcit.bigdata.cricri.stm.enrichment.dao;
//
//import com.google.protobuf.ServiceException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class StmHBaseDAO {
//
//    @Value("${hbase.conf.file}")
//    private String hbaseSite;
//
//    public void getHbaseInfo(){
//        Configuration config = HBaseConfiguration.create();
//
//        String path = hbaseSite;
//        config.addResource(new Path(path));
//
//        try {
//            HBaseAdmin.checkHBaseAvailable(config);
//        } catch (Exception e) {
//            log.warn("Exception while checking Hbase Availability {}", e);
//        }
//    }
//}
