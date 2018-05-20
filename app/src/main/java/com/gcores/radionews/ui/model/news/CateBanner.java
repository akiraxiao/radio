package com.gcores.radionews.ui.model.news;

public class CateBanner {

    /**
     * Copyright 2018 bejson.com
     */


        private int id;
        private String name;
        private String desc;
        private String show_name;
        private String type;
        private String specific_type;
        private String logo_url;
        private String background_url;
        private int subscriptors_num;
        private int originals_num;

        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getDesc() {
            return desc;
        }

        public void setShow_name(String show_name) {
            this.show_name = show_name;
        }
        public String getShow_name() {
            return show_name;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public void setSpecific_type(String specific_type) {
            this.specific_type = specific_type;
        }
        public String getSpecific_type() {
            return specific_type;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }
        public String getLogo_url() {
            return logo_url;
        }

        public void setBackground_url(String background_url) {
            this.background_url = background_url;
        }
        public String getBackground_url() {
            return background_url;
        }

        public void setSubscriptors_num(int subscriptors_num) {
            this.subscriptors_num = subscriptors_num;
        }
        public int getSubscriptors_num() {
            return subscriptors_num;
        }

        public void setOriginals_num(int originals_num) {
            this.originals_num = originals_num;
        }
        public int getOriginals_num() {
            return originals_num;
        }



}
