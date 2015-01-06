package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Deprecated
@XmlRootElement(name="List")
public class JaxbList<T>{
    protected ArrayList<T> list;

    public JaxbList(){}

    public JaxbList(ArrayList<T> list){
    	this.list=list;
    }

    @XmlElement(name="Item")
    public ArrayList<T> getList(){
    	return list;
    }
    
    
}
