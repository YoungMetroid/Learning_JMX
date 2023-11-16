package com.Chapter2;

import javax.management.*;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Iterator;

public class DateTimeService
        implements DynamicMBean{


    public static final boolean READABLE = true;
    public static final boolean WRITEABLE = true;
    public static final boolean ISIS = true;

    private String userConfiguredDate = null;
    private String userConfiguredTime = null;
    private MBeanAttributeInfo[] attributeInfo = new MBeanAttributeInfo[2];
    private MBeanConstructorInfo[] constructorInfo = new MBeanConstructorInfo[1];
    private MBeanOperationInfo[] operationInfo = new MBeanOperationInfo[2];
    private MBeanInfo mBeanInfo = null;

    public void setDate(String newValue)
    {
        userConfiguredDate = newValue;
    }
    public String getDate()
    {
        if(userConfiguredDate != null)
            return userConfiguredDate;

        Calendar rightNow = Calendar.getInstance();
        return DateFormat.getDateInstance().format(rightNow.getTime());
    }

    public void setTime(String newValue)
    {
        userConfiguredTime = newValue;
    }
    public String getTime()
    {
        if(userConfiguredTime != null)
            return userConfiguredTime;

        Calendar rightNow =  Calendar.getInstance();
        return DateFormat.getTimeInstance().format(rightNow.getTime());
    }
    public void stop()
    {
    }
    public void start()
    {
    }
    public Object getAttribute(String attributeName)
            throws AttributeNotFoundException,
            MBeanException,
            ReflectionException
    {
        if (attributeName == null)
        {
            IllegalArgumentException ex =
                    new IllegalArgumentException("Attribute name cannot be null");
            throw new RuntimeOperationsException(ex, "null attribute name");
        }
        if ("Date".equals(attributeName))
        {
            return getDate();
        }
        if ("Time".equals(attributeName))
        {
            return getTime();
        }
        throw(new AttributeNotFoundException("Invalid attribute:"
                + attributeName));
    }
    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException,
            InvalidAttributeValueException,
            MBeanException,
            ReflectionException
    {
        if (attribute == null)
        {
            IllegalArgumentException ex =
                    new IllegalArgumentException("Attribute cannot be null");
            throw new RuntimeOperationsException(ex, "null attribute");
        }
        String name = attribute.getName();
        Object value = attribute.getValue();
        if (name == null)
        {
            IllegalArgumentException ex =
                    new IllegalArgumentException("Attribute name cannot be null");
            throw new RuntimeOperationsException(ex, "null attribute name");
        }

        if (value == null)
        {
            IllegalArgumentException ex =
                    new IllegalArgumentException("Attribute value cannot be null");
            throw new RuntimeOperationsException(ex, "null attribute value");
        }

        try
        {
            Class stringCls = Class.forName("java.lang.String");
            if (stringCls.isAssignableFrom(value.getClass()) == false)
            {
                IllegalArgumentException ex =
                        new IllegalArgumentException("Invalid attribute value class");
                throw new RuntimeOperationsException(ex, "Invalid attribute value");
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        if ("Date".equals(name))
        {
            setDate(value.toString());
        }
        else if ("Time".equals(name))
        {
            setTime(value.toString());
        }
        else
        {
            throw(new AttributeNotFoundException("Invalid Attribute name;"
                    + name));
        }
    }

    public AttributeList getAttributes(String[] attributeNames)
    {
        if (attributeNames == null)
        {
            IllegalArgumentException ex =
                    new IllegalArgumentException("attributeNames cannot be null");
            throw new RuntimeOperationsException(ex, "null attribute names");
        }
        AttributeList resultList = new AttributeList();
        if (attributeNames.length == 0)
            return resultList;
        for (int i = 0; i < attributeNames.length; i++)
        {
            try
            {
                Object value = getAttribute((String) attributeNames[i]);
                resultList.add(new Attribute(attributeNames[i], value));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return (resultList);
    }

    public AttributeList setAttributes(AttributeList attributes)
    {
        if (attributes == null)
        {
            IllegalArgumentException ex =
                    new IllegalArgumentException("attributes cannot be null");
            throw new RuntimeOperationsException(ex, "null attribute list");
        }
        AttributeList resultList = new AttributeList();
        if (attributes.isEmpty())
            return resultList;
        for (Iterator i = attributes.iterator(); i.hasNext();)
        {
            Attribute attr = (Attribute) i.next();
            try
            {
                setAttribute(attr);
                String name = attr.getName();
                Object value = getAttribute(name);
                resultList.add(new Attribute(name, value));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return (resultList);
    }

    public Object invoke(String operationName, Object[] parameters, String[] signature) throws MBeanException, ReflectionException
    {
        if(operationName == null)
        {
            IllegalArgumentException ex = new IllegalArgumentException("Operation name cannot be null");
            throw new RuntimeOperationsException(ex, "null operation name");
        }
        if("stop".equals(operationName))
        {
            stop();
            return null;
        }
        else if("start".equals(operationName))
        {
            start();
            return null;
        }
        else
        {
            throw new ReflectionException(new NoSuchMethodException(operationName),
                    "Invalid operation name: "
                    + operationName);
        }
    }

    public MBeanInfo getMBeanInfo() {
        if(mBeanInfo != null)
        {
            return mBeanInfo;
        }

        attributeInfo[0] = new MBeanAttributeInfo("Date",
                String.class.getName(),
                "The current date",
                READABLE,
                WRITEABLE,
                !ISIS);
        attributeInfo[1] = new MBeanAttributeInfo("Time",
                String.class.getName(),
                "The current time",
                READABLE,
                WRITEABLE,
                !ISIS);

        Constructor[] constructors = this.getClass().getConstructors();
        constructorInfo[0] =
                new MBeanConstructorInfo("Constructs a DateTimeService object",
                        constructors[0]);

        MBeanParameterInfo[] parameters = null;

        operationInfo[0] = new MBeanOperationInfo("start",
                "Starts the DateTime service",
                parameters,
                "void",
                MBeanOperationInfo.ACTION);

        operationInfo[1] = new MBeanOperationInfo("stop",
                "Stops the DateTime service",
                parameters,
                "void",
                MBeanOperationInfo.ACTION);
        mBeanInfo = new MBeanInfo(this.getClass().getName(),
                "DateTime Service MBean",
                attributeInfo,
                constructorInfo,
                operationInfo,
                new MBeanNotificationInfo[0]);

        return (mBeanInfo);

    }
}
