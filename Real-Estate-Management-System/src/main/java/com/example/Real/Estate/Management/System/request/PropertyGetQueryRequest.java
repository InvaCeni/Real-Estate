package com.example.Real.Estate.Management.System.request;

public class PropertyGetQueryRequest {

        private String searchTerm;
        private String sort;
        private String order;
        private Integer limit;
        private Integer startIndex;

        public String getSearchTerm() {
            return searchTerm;
        }

        public void setSearchTerm(String searchTerm) {
            this.searchTerm = searchTerm;
        }



        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getStartIndex() {
            return startIndex;
        }

        @Override
        public String toString() {
            return "PropertyGetQueryRequest{" +
                    "searchTerm='" + searchTerm + '\'' +
                    ", sort='" + sort + '\'' +
                    ", order='" + order + '\'' +
                    ", limit=" + limit +
                    ", startIndex=" + startIndex +
                    '}';
        }

        public void setStartIndex(Integer startIndex) {
            this.startIndex = startIndex;
        }

}
