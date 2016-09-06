package com.example.awang.weatherbuddymosby.citysearch;

import java.util.List;

/**
 * Created by awang on 2016-08-08.
 */
public class CitySuggestionBean {
    private List<Prediction> predictions ;

    private String status;

    public void setPredictions(List<Prediction> predictions){
        this.predictions = predictions;
    }
    public List<Prediction> getPredictions(){
        return this.predictions;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    public class Prediction {
        private String description;

        private String id;

        private List<Matched_substring> matched_substrings ;

        private String place_id;

        private String reference;

        private List<Term> terms ;

        private String[] type;

        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setMatched_substrings(List<Matched_substring> matched_substrings){
            this.matched_substrings = matched_substrings;
        }
        public List<Matched_substring> getMatched_substrings(){
            return this.matched_substrings;
        }
        public void setPlace_id(String place_id){
            this.place_id = place_id;
        }
        public String getPlace_id(){
            return this.place_id;
        }
        public void setReference(String reference){
            this.reference = reference;
        }
        public String getReference(){
            return this.reference;
        }
        public void setTerms(List<Term> terms){
            this.terms = terms;
        }
        public List<Term> getTerms(){
            return this.terms;
        }
        public void setTypes(String[] type){
            this.type = type;
        }
        public String[] getTypes(){
            return this.type;
        }

    }
    
    public class Term {
        private int offset;

        private String value;

        public void setOffset(int offset){
            this.offset = offset;
        }
        public int getOffset(){
            return this.offset;
        }
        public void setValue(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }

    }

    public class Matched_substring {
        private int length;

        private int offset;

        public void setLength(int length){
            this.length = length;
        }
        public int getLength(){
            return this.length;
        }
        public void setOffset(int offset){
            this.offset = offset;
        }
        public int getOffset(){
            return this.offset;
        }

    }
}
