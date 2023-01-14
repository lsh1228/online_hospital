package com.recommend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.recommend.bean.Treatment;
import com.recommend.repository.TreatmentRepository;
import com.recommend.repository.impl.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

    @Autowired
    TreatmentRepository treatmentRepository;

    SearchRepository searchRepository = new SearchRepository();

    // get all
    @GetMapping("/list")
    public ResponseEntity<List<Treatment>> getAllArticles() {
        try {
            List<Treatment> treatments = new ArrayList<Treatment>();
            treatmentRepository.findAll().forEach(treatments::add);
            if (treatments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(treatments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get
    @GetMapping("/getById/{id}")
    public ResponseEntity<Treatment> getArticleById(@PathVariable("id") long id) {

        Optional<Treatment> TreatmentData = treatmentRepository.findById(id);

        System.out.println(TreatmentData.get().getTitle());
        if (TreatmentData.isPresent()) {
            return new ResponseEntity<>(TreatmentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add
    @PostMapping("/add")
    public String createArticle(@RequestBody Treatment treatment) {

        try {
            String author = "admin";
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String publish_time = dateFormat.format(date);
            if (treatment.getAuthor() != null) {
                author = treatment.getAuthor();
            }
            if (treatment.getPublish_time() != null && !treatment.getPublish_time().equals("")) {
                publish_time = treatment.getPublish_time();
            }
            System.out.println(treatment.toString());
            treatmentRepository.save(new Treatment(treatment.getTitle(), treatment.getContent(), author,
                    publish_time, treatment.getIllState(), treatment.getMedicineCnt(),
                    treatment.getIllType()));
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    // update
    @PostMapping("/update/{id}")
    public String updateTreatment(@PathVariable("id") long id, @RequestBody Treatment treatment2) {

        Optional<Treatment> treatmentData = treatmentRepository.findById(id);
        if (treatmentData.isPresent()) {
            Treatment treatment = treatmentData.get();
            treatment.setTitle(treatment2.getTitle());
            treatment.setContent(treatment2.getContent());
            treatment.setAuthor(treatment2.getAuthor());
            treatment.setPublish_time(treatment2.getPublish_time());
            treatment.setIllState(treatment2.getIllState());
            treatment.setIllType(treatment2.getIllType());
            treatment.setMedicineCnt(treatment2.getMedicineCnt());


            Treatment insertedArticle = treatmentRepository.save(treatment);
            if (insertedArticle.getId() == id) {
                return "ok";
            }
        }

        return "fail";
    }

    @DeleteMapping("/del/{id}")
    public String deleteTreatment(@PathVariable("id") long id) {
        try {
            treatmentRepository.deleteById(id);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Treatment>> searchTreatment(@PathVariable("title") String title) {
        try {
            List<Treatment> treatments = new ArrayList<>();
            if (title == null || title == "") {
                treatmentRepository.findAll().forEach(treatments::add);
            } else {
                treatments = searchRepository.getSearchArticle(title);
            }
            if (treatments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(treatments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}