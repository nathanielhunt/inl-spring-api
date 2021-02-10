package com.inl.rest;

import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors; 
import java.util.stream.Stream;
import java.util.Comparator;

import com.inl.rest.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.Query;

@RestController
public class ConditionController {
    
    @Autowired
    ConditionRepository conditionRepository;

    @GetMapping("/getConditionsByStudy")
    public List<Condition> getConditionsByStudy(@RequestHeader("study") String study)
    {
        return conditionRepository.findByStudy(study);
    }
    
    @GetMapping("/getWeightedConditionByStudy")
    public Condition getWeightedConditionByStudy(@RequestHeader("study") String study)
    {
        List<Condition> conds = conditionRepository.findByStudy(study);
        Comparator<Condition> comp = Comparator.comparing(Condition::getWeightedness);
        return conds.stream().max(comp).get();
    }
    
    @GetMapping("/getAllCbalStudies")
    public List<String> getDistinctStudy()
    {
        return conditionRepository.findDistinctStudy();
    }    

}