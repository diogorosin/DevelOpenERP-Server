package br.com.developen.erp.orm;

public enum Status {
	
    A("A"),
    F("F"),
    C("C");
 
    private String status;
 
    Status(String status) {
    	
        this.status = status;

    }

    public String getUrl() {

        return status;

    }   

}