
package com.coder.dream.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>notifyServiceConsume complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="notifyServiceConsume">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://ws.dream.coder.com/}ServiceConsumeNotifyReq" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notifyServiceConsume", propOrder = {
    "arg0"
})
public class NotifyServiceConsume {

    protected ServiceConsumeNotifyReq arg0;

    /**
     * 获取arg0属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ServiceConsumeNotifyReq }
     *     
     */
    public ServiceConsumeNotifyReq getArg0() {
        return arg0;
    }

    /**
     * 设置arg0属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceConsumeNotifyReq }
     *     
     */
    public void setArg0(ServiceConsumeNotifyReq value) {
        this.arg0 = value;
    }

}
