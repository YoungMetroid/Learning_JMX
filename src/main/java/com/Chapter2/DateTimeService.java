package com.Chapter2;

import javax.management.*;
import java.text.DateFormat;
import java.util.Calendar;

public class DateTimeService implements  DynamicMBean{


    public static final boolean READABLE = true;
    public static final boolean WRITEABLE = true;
    public static final boolean ISIS = true;
    private String userConfigurable = null;
    private String userConfiguredTime = null;
    private MBeanAttributeInfo[] attributeInfos = new MBeanAttributeInfo[2];
    private MBeanConstructorInfo[] constructorInfos = new MBeanConstructorInfo[1];
    private MBeanOperationInfo[]  operationInfos = new MBeanOperationInfo[2];
    private MBeanInfo mBeanInfo = null;

    public void setDate(String newValue)
    {
        userConfigurable = newValue;
    }

    public String getDate()
    {
        if(userConfigurable != null)
        {
            return userConfigurable;
        }
        Calendar rightNow = Calendar.getInstance();
        return DateFormat.getDateInstance().format(rightNow.getTime());
    }

    public String getTime()
    {
        if(userConfigurable != null)
        {
            return userConfiguredTime;
        }
        Calendar rightNow =  Calendar.getInstance();
        return DateFormat.getTimeInstance().format(rightNow.getTime());
    }

    public void stop()
    {

    }
    public void start()
    {

    }


    @Override
    public Object getAttribute(String attributeName) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if(attributeName == null)
        {
            IllegalArgumentException exception = new IllegalArgumentException("Attribute name cannot be null");
            throw new RuntimeException(exception,"null attribute name");
        }
        if(attributeName.equals("Date"))
        {
            return getDate();
        }
        if(attributeName.equals("Time"))
        {
            return getTime();
        }
        throw(new AttributeNotFoundException("Invalid attribute: " + attributeName));
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    @Override
    public AttributeList getAttributes(String[] string) {
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList list) {
        return null;
    }

    @Override
    public Object invoke(String s, Object[] objects, String[] strings) throws MBeanException, ReflectionException {
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return null;
    }
}
