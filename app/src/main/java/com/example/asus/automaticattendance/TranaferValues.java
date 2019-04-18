package com.example.asus.automaticattendance;

public class TranaferValues {
    private String name,tecName,email,username,password,id,code,eCode,courseTrack,batch,section,dept;
    public String getId(){return id;}

    public void  setId(String id){ this.id=id;}

    public String getBatch(){return batch;}

    public void  setBatch(String batch){ this.batch=batch;}

    public String getCode(){return code;}

    public void  setCode(String code){ this.code=code;}

    public void  setSection(String section){ this.section=section;}

    public String getSection(){return section;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTecName() {
        return tecName;
    }

    public void setTecName(String name) {
        this.tecName = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
