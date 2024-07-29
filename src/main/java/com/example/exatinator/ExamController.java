package com.example.exatinator;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ExamController {
        @Autowired
        private RestTemplate restTemplate;
        private DiscoveryClient discoveryClient;

        @PostMapping("/exam")
        public Exam getExam(@RequestBody Map<String, Integer> spec) {
            List<Section> sections = spec.entrySet().stream()
                    .map(this::getUrl)
                    .map(url->restTemplate.getForObject(url,Questions[].class))
                    .map(Arrays::asList)
                    .map(Section::new)
                    .collect(Collectors.toList());
            return Exam.builder().title("Exam").sections(sections).build();
        }

//        private String getUrl(Map.Entry<String, Integer> entry) {
//            String serviceName = "MATHSERVICE"; // Имя сервиса в Eureka
//            String baseUrl = "http://" + serviceName + "/api/sections"; // Путь к эндпоинту
//            return baseUrl + "?key=" + entry.getKey() + "&value=" + entry.getValue();
//
//        }

    private String getUrl(Map.Entry<String, Integer> entry) {
        String serviceName = entry.getKey(); // Имя сервиса (например, "MATHSERVICE" или "HISTORYSERVICE")
        int amount = entry.getValue(); // Количество вопросов, которые нужно запросить
        return "http://" + serviceName + "/api/sections?amount=" + amount;
    }

}


