package com.demo.lucar.bean;

import java.util.List;

public class ImageBean {

    /**
     * breeds : [{"weight":{"imperial":"5 - 10","metric":"2 - 5"},"id":"acur","name":"American Curl","cfa_url":"http://cfa.org/Breeds/BreedsAB/AmericanCurl.aspx","vetstreet_url":"http://www.vetstreet.com/cats/american-curl","vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/american-curl","temperament":"Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social","origin":"United States","country_codes":"US","country_code":"US","description":"Distinguished by truly unique ears that curl back in a graceful arc, offering an alert, perky, happily surprised expression, they cause people to break out into a big smile when viewing their first Curl. Curls are very people-oriented, faithful, affectionate soulmates, adjusting remarkably fast to other pets, children, and new situations.","life_span":"12 - 16","indoor":0,"lap":1,"alt_names":"","adaptability":5,"affection_level":5,"child_friendly":4,"dog_friendly":5,"energy_level":3,"grooming":2,"health_issues":1,"intelligence":3,"shedding_level":3,"social_needs":3,"stranger_friendly":3,"vocalisation":3,"experimental":0,"hairless":0,"natural":0,"rare":0,"rex":0,"suppressed_tail":0,"short_legs":0,"wikipedia_url":"https://en.wikipedia.org/wiki/American_Curl","hypoallergenic":0}]
     * id : ji-5E0VwY
     * url : https://cdn2.thecatapi.com/images/ji-5E0VwY.jpg
     * width : 1250
     * height : 702
     */

    public String id;
    public String url;
    public int width;
    public int height;
    public List<BreedsBean> breeds;

    public static class BreedsBean {
        /**
         * weight : {"imperial":"5 - 10","metric":"2 - 5"}
         * id : acur
         * name : American Curl
         * cfa_url : http://cfa.org/Breeds/BreedsAB/AmericanCurl.aspx
         * vetstreet_url : http://www.vetstreet.com/cats/american-curl
         * vcahospitals_url : https://vcahospitals.com/know-your-pet/cat-breeds/american-curl
         * temperament : Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social
         * origin : United States
         * country_codes : US
         * country_code : US
         * description : Distinguished by truly unique ears that curl back in a graceful arc, offering an alert, perky, happily surprised expression, they cause people to break out into a big smile when viewing their first Curl. Curls are very people-oriented, faithful, affectionate soulmates, adjusting remarkably fast to other pets, children, and new situations.
         * life_span : 12 - 16
         * indoor : 0
         * lap : 1
         * alt_names :
         * adaptability : 5
         * affection_level : 5
         * child_friendly : 4
         * dog_friendly : 5
         * energy_level : 3
         * grooming : 2
         * health_issues : 1
         * intelligence : 3
         * shedding_level : 3
         * social_needs : 3
         * stranger_friendly : 3
         * vocalisation : 3
         * experimental : 0
         * hairless : 0
         * natural : 0
         * rare : 0
         * rex : 0
         * suppressed_tail : 0
         * short_legs : 0
         * wikipedia_url : https://en.wikipedia.org/wiki/American_Curl
         * hypoallergenic : 0
         */

        public WeightBean weight;
        public String id;
        public String name;
        public String cfa_url;
        public String vetstreet_url;
        public String vcahospitals_url;
        public String temperament;
        public String origin;
        public String country_codes;
        public String country_code;
        public String description;
        public String life_span;
        public int indoor;
        public int lap;
        public String alt_names;
        public int adaptability;
        public int affection_level;
        public int child_friendly;
        public int dog_friendly;
        public int energy_level;
        public int grooming;
        public int health_issues;
        public int intelligence;
        public int shedding_level;
        public int social_needs;
        public int stranger_friendly;
        public int vocalisation;
        public int experimental;
        public int hairless;
        public int natural;
        public int rare;
        public int rex;
        public int suppressed_tail;
        public int short_legs;
        public String wikipedia_url;
        public int hypoallergenic;

        public static class WeightBean {
            /**
             * imperial : 5 - 10
             * metric : 2 - 5
             */

            public String imperial;
            public String metric;
        }
    }
}
