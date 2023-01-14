package com.recommend.test;

import com.recommend.bean.Treatment;
import com.recommend.repository.TreatmentRepository;
import com.recommend.service.RecommendService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

public class TreatmentTest {

    @Autowired
    TreatmentRepository treatmentRepository;

    @Test
    public void testGetTreatment() {

        long id  = 1;
        Optional<Treatment> treatmentData = treatmentRepository.findById(id);
        if (treatmentData.isPresent()) {
            Treatment treatment = treatmentData.get();

            Treatment insertedArticle = treatmentRepository.save(treatment);
            assert  (insertedArticle.getId() == id);
        }
    }

    @Test
    public void testInsertTreatment() {

        Treatment treatment = treatmentRepository.save(new Treatment("aa", "bb", "cc",
                "2001-01-02", "cc", 10, "dd"));

        if (treatment != null) {
            assert(Objects.equals(treatment.getTitle(), "aa"));
            assert(Objects.equals(treatment.getContent(), "bb"));
            assert(Objects.equals(treatment.getAuthor(), "cc"));
            assert(Objects.equals(treatment.getPublish_time(), "2001-01-02"));
            assert(Objects.equals(treatment.getIllState(), "cc"));
            assert(Objects.equals(treatment.getMedicineCnt(), 10));
            assert(Objects.equals(treatment.getIllType(), "dd"));
        }
    }

    @Test
    public void testDelTreatment() {

        long id  = 1;
        treatmentRepository.deleteById(id);
        Optional<Treatment> treatmentData = treatmentRepository.findById(id);
        assert (!treatmentData.isPresent());

    }
}
