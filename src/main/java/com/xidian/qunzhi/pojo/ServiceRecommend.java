package com.xidian.qunzhi.pojo;

import javax.persistence.*;

@Table(name = "service_recommend")
public class ServiceRecommend {
    @Id
    private Integer id;

    @Column(name = "function_name")
    private String functionName;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "code_url")
    private String codeUrl;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return function_name
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * @param functionName
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * @return service_name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return code_url
     */
    public String getCodeUrl() {
        return codeUrl;
    }

    /**
     * @param codeUrl
     */
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}