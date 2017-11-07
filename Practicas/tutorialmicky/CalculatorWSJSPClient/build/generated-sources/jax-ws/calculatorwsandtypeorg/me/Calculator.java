
package calculatorwsandtypeorg.me;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "calculator", targetNamespace = "http://me.CalculatorWSandtypeorg/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Calculator {


    /**
     * 
     * @param i
     * @param j
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://me.CalculatorWSandtypeorg/", className = "calculatorwsandtypeorg.me.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://me.CalculatorWSandtypeorg/", className = "calculatorwsandtypeorg.me.AddResponse")
    @Action(input = "http://me.CalculatorWSandtypeorg/calculator/addRequest", output = "http://me.CalculatorWSandtypeorg/calculator/addResponse")
    public int add(
        @WebParam(name = "i", targetNamespace = "")
        int i,
        @WebParam(name = "j", targetNamespace = "")
        int j);

}
