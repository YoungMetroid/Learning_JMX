package com.Chapter2;

import javax.management.*;

public interface DynamicMBean {

    Object getAttribute (String attribute)
            throws AttributeNotFoundException, MBeanException, ReflectionException;

    void setAttribute(Attribute attribute)
        throws AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException,ReflectionException;

    AttributeList getAttributes(String[] attrbutes);

    AttributeList setAttributes(AttributeList list);

    Object invoke(String s, Object[] objects , String[] strings )
        throws MBeanException, ReflectionException;


    MBeanInfo getMBeanInfo();



}
