package com.backend.pruebaavla.utils;

import com.backend.pruebaavla.models.Deal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Utils {

    public List<Deal> reverseList(List<Deal> deals){
        List<Deal> reverseList = new ArrayList<Deal>();
        for (int i = deals.size() -1; i >= 0; i--) {
            reverseList.add(deals.get(i));
        }
        System.out.println(reverseList.size());
        return reverseList;

    }
}
