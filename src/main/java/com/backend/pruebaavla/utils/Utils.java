package com.backend.pruebaavla.utils;


import com.backend.pruebaavla.models.Deal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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


}



