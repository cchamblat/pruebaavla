package com.backend.pruebaavla.utils;


import com.backend.pruebaavla.models.Deal;
import com.backend.pruebaavla.models.Stage;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class Utils {

    public List<List<Deal>> orderDeals(List<Deal> deals){
        List<List<Deal>> orderDeal= new ArrayList<>();
        List<Deal> listCurrent = new ArrayList<>();
        String dealCurrent = deals.get(0).getDealId();
        listCurrent.add(deals.get(0));
        for (int j= 1; j < deals.size() ; j++){
            if (Objects.equals(dealCurrent, deals.get(j).getDealId())){
                listCurrent.add(deals.get(j));
            }else{
                listCurrent.sort(Comparator.comparing(Deal::getLogTime));
                List<Deal> auxList = new ArrayList<>(listCurrent);
                orderDeal.add(auxList);
                listCurrent.clear();
                dealCurrent = deals.get(j).getDealId();
                listCurrent.add(deals.get(j));
            }
        }
        listCurrent.sort(Comparator.comparing(Deal::getLogTime));
        orderDeal.add(listCurrent);
        return orderDeal;
    }

    public List<?> getTimes(List<List<Deal>> deals) throws ParseException {
        SimpleDateFormat toDate=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        TimeUnit time = TimeUnit.DAYS;
        List<Stage> stages  = new ArrayList<>();
        String timeInit = "";
        for (List<Deal> deal : deals) {
            for (int i=0; i<deal.size();i++) {
                if (Objects.equals(deal.get(i).getFieldKey(), "add_time") && Objects.equals(deal.get(i).getOldValue(), "")){
                    //Condicion cuando empieza el flujo
                    timeInit = deal.get(i).getLogTime();
                }
                else if (Objects.equals(deal.get(i).getFieldKey(), "stage_change_time") && Objects.equals(deal.get(i).getOldValue(), "")){
                    Date dateInit = toDate.parse(timeInit);
                    Date dateEnd = toDate.parse(deal.get(i).getLogTime());
                    long diff = dateEnd.getTime() - dateInit.getTime();
                    long differenceSeconds = time.convert(diff, TimeUnit.MINUTES);
                    System.out.println(differenceSeconds);
                    Stage stage = Stage.builder()
                            .stage(deal.get(i+1).getOldValue())
                            .time(differenceSeconds)
                            .build();
                    stages.add(stage);
                }

            }

        }
        return stages;
    }


}



